package cn.gdeng.nst.server.order.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.gdeng.nst.api.dto.order.OrderInfoDTO;
import cn.gdeng.nst.api.dto.order.SourceExamineDTO;
import cn.gdeng.nst.api.dto.task.PushMsgDto;
import cn.gdeng.nst.api.server.order.SourceExamineService;
import cn.gdeng.nst.api.vo.order.SourceExamineVo;
import cn.gdeng.nst.dao.BaseDao;
import cn.gdeng.nst.entity.nst.MqError;
import cn.gdeng.nst.entity.nst.OrderInfoTransEntity;
import cn.gdeng.nst.entity.nst.SourceExamineEntity;
import cn.gdeng.nst.entity.nst.SourceShipperEntity;
import cn.gdeng.nst.enums.MqConstants;
import cn.gdeng.nst.enums.MsgCons;
import cn.gdeng.nst.enums.OrderInfoPrePayStatus;
import cn.gdeng.nst.enums.OrderInfoStatusEnum;
import cn.gdeng.nst.enums.OrderInfoTransCloseReasonEnum;
import cn.gdeng.nst.enums.OrderInfoTransStatusEnum;
import cn.gdeng.nst.enums.PushConstants;
import cn.gdeng.nst.enums.SourceExamineStatusEnum;
import cn.gdeng.nst.util.admin.web.DateUtil;
import cn.gdeng.nst.util.server.InterfaceUtil;
import cn.gdeng.nst.util.server.JacksonUtil;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.BizException;
import cn.gdeng.nst.util.web.api.Des3;
import cn.gdeng.nst.util.web.api.GSONUtils;
import cn.gdeng.nst.util.web.api.GdProperties;
import cn.gdeng.nst.util.web.api.ObjectResult;
import cn.gdeng.nst.util.web.api.SerializeUtil;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.bean.ProducerBean;
import com.gudeng.framework.dba.transaction.annotation.Transactional;
/**
 * 验货
 * @author kwang
 *
 */
@Service
public class SourceExamineServiceImpl implements SourceExamineService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private InterfaceUtil interfaceUtil;
    @Resource
    private BaseDao<?> baseDao;
	@Resource
	private ProducerBean appProducer;
	@Autowired
	private GdProperties gdProperties;

    /**
	 * 根据运单ID查询验货 orderNo
	 * @param dto
	 * @return
	 */
	@Override
	public ApiResult<SourceExamineVo> querysourceExamine(SourceExamineDTO dto)
			throws BizException {
		if(dto.getOrderNo()==null){
			throw new BizException(MsgCons.C_20001, MsgCons.M_20001);
		}
		SourceExamineVo vo=baseDao.queryForObject("SourceExamine.queryByCondition", dto, SourceExamineVo.class);
        ApiResult<SourceExamineVo> apiResult = new ApiResult<SourceExamineVo>(vo,MsgCons.C_10000,MsgCons.M_10000);
        return apiResult;
	}
	
	/**
	 * 验货保存
	 * @param dto   orderNo
	 * @return
	 */
	@Override
	@Transactional
	public ApiResult<Long> saveSourceExamine(SourceExamineDTO dto) throws BizException{
		if(dto.getOrderNo()==null||dto.getStatus()==null){
			throw new BizException(MsgCons.C_20001, MsgCons.M_20001);
		}
		OrderInfoDTO orderInfoDTO=baseDao.queryForObject("OrderInfo.getByOrderNo", dto, OrderInfoDTO.class);
		if(!orderInfoDTO.getPrePayStatus().equals(OrderInfoPrePayStatus.PAID.getCode())){
			throw new BizException(MsgCons.C_23021, MsgCons.M_23021);
		}
		int count=baseDao.queryForObject("SourceExamine.selectByOrderId",orderInfoDTO, Integer.class);
		if(count>0){
			throw new BizException(MsgCons.C_23019, MsgCons.M_23019);	
		}
		if(dto.getDescription()!=null){
	        	if(dto.getDescription().length()>200){
					throw new BizException(MsgCons.C_23018, MsgCons.M_23018);
				}
	     }
		//通知谷登平台SENDMAP
		Map<String, Object> sendMap =new HashMap<String, Object>();
		sendMap.put("orderNo", dto.getSourceId());//注谷登平台orderNo就是SourceId
		sendMap.put("cancelReason", dto.getDescription());
		sendMap.put("userId", dto.getCreateUserId());
		dto.setOrderInfoId(orderInfoDTO.getId());
	     Map<String, Object> UpdateParam =new HashMap<String, Object>();
	     Long  re=0l;
		if(dto.getStatus().equals(SourceExamineStatusEnum.NOT_PASS.getCode())){
			if(dto.getDescription()==null){
				throw new BizException(MsgCons.C_23017, MsgCons.M_23017);
			}
			 //给gdeng后台通知  验货不通过为3 
			 sendMap.put("type", "3");
			 //添加验货信息
			 re= baseDao.persist(analysisImageList(dto), Long.class);
			 //关闭货源状态
		     UpdateParam.put("id", orderInfoDTO.getId());
		     //修改运单状态
		     UpdateParam.put("orderStatus", OrderInfoStatusEnum.CLOSED.getCode()); 
		     UpdateParam.put("transStatus",OrderInfoTransStatusEnum.EXAMINED_NOT_PASS.getCode());
		     UpdateParam.put("closeReason",OrderInfoTransCloseReasonEnum.EXAMINE_GOODS_NOT_PASS.getCode());
			 baseDao.execute("OrderInfo.update2", UpdateParam); 
			 constractTransAndSave(orderInfoDTO, OrderInfoTransStatusEnum.EXAMINED_NOT_PASS.getCode());
		}else{ 
			 //给gdeng后台通知  验货通过为2
			 sendMap.put("type", "2");
		     re= baseDao.persist(analysisImageList(dto), Long.class);
			 constractTransAndSave(orderInfoDTO, OrderInfoTransStatusEnum.SENT.getCode());
			 UpdateParam.put("id", orderInfoDTO.getId());
			 UpdateParam.put("transStatus",OrderInfoTransStatusEnum.SENT.getCode());
			 baseDao.execute("OrderInfo.update2", UpdateParam);
		} 
      
        Map<String, Object> param =new HashMap<String, Object>();
        param.put("id", dto.getSourceId());
		SourceShipperEntity sourceShipperEntity=baseDao.queryForObject("OrderBefore.querySourceShipper", param, SourceShipperEntity.class);
        //保存后通知
        String r = null;
    	Integer reCode = null;
    	try { 
    		r=interfaceUtil.sendSourceExaminePassStatus(des3Map(sendMap));
    		reCode = pubMethod(r).getCode();
    		if(reCode==MsgCons.C_10000){
    			logger.info("SourceExamineServiceImpl 验货通知成功");	
    		}else{
    			this.insertMqError(sendMap,"验货通知失败:"+pubMethod(r).getMsg(),gdProperties.getsourceExaminePassStatusUrl(),Integer.parseInt(dto.getCreateUserId()));
    			logger.error("SourceExamineServiceImpl 通知失败");	
    		}
		} catch (Exception e) {
			this.insertMqError(sendMap,"验货通知调用失败"+r,gdProperties.getsourceExaminePassStatusUrl(),Integer.parseInt(dto.getCreateUserId()));
			logger.error("SourceExamineServiceImpl 通知调用失败");	
		}
        //保存后MQ推送
        sendSourceExaminePassStatus(sourceShipperEntity.getAssignMemberId(),dto.getStatus());
        ApiResult<Long> apiResult = null;
		if(re>0){
			apiResult=	new ApiResult<Long>(re,MsgCons.C_10000,MsgCons.M_10000);
		}else{
			apiResult=	new ApiResult<Long>(re,MsgCons.C_20000,MsgCons.M_20000);
		}
		return apiResult;
	}

	
	/**
	 * DTO解析
	 * @param dto
	 * @return
	 */
	public SourceExamineEntity analysisImageList(SourceExamineDTO dto){
		SourceExamineEntity entity=new SourceExamineEntity();
		entity.setOrderId(dto.getOrderInfoId());
		entity.setStatus(dto.getStatus());
		entity.setDescription(dto.getDescription());
		entity.setCreateUserId(dto.getCreateUserId());
		entity.setCreateTime(new Date());
		if(dto.getImageUrlList()!=null){
           switch (dto.getImageUrlList().size()) {
			case 1:
				entity.setImageUrl(dto.getImageUrlList().get(0));
				break;
		    case 2:
		    	entity.setImageUrl(dto.getImageUrlList().get(0));
		    	entity.setImageUrl2(dto.getImageUrlList().get(1));
				break;
		    case 3:
		    	entity.setImageUrl(dto.getImageUrlList().get(0));
		    	entity.setImageUrl2(dto.getImageUrlList().get(1));
		    	entity.setImageUrl3(dto.getImageUrlList().get(2));
				break;
		    case 4:
		    	entity.setImageUrl(dto.getImageUrlList().get(0));
		    	entity.setImageUrl2(dto.getImageUrlList().get(1));
		    	entity.setImageUrl3(dto.getImageUrlList().get(2));
		    	entity.setImageUrl4(dto.getImageUrlList().get(3));
				break;
		    case 5:
		    	entity.setImageUrl(dto.getImageUrlList().get(0));
		    	entity.setImageUrl2(dto.getImageUrlList().get(1));
		    	entity.setImageUrl3(dto.getImageUrlList().get(2));
		    	entity.setImageUrl4(dto.getImageUrlList().get(3));
		    	entity.setImageUrl5(dto.getImageUrlList().get(4));
				break;
			default:
				break;
		   }
		}
		return entity;
	}

    /**
     * 验货通知 
     * @param memberId
     * @param passStatus
     * @throws BizException
     */
	private void sendSourceExaminePassStatus(Integer memberId,Byte passStatus)throws BizException {
		PushMsgDto dto=new PushMsgDto();
		dto.setBizId(memberId);
		dto.setMemberId(memberId);
		if(passStatus.equals(SourceExamineStatusEnum.PASS.getCode())){
		dto.setMsgType(PushConstants.MSG_TYPE_5);
		dto.setContent(MsgCons.M_28017);
		}else{
		dto.setMsgType(PushConstants.MSG_TYPE_8);
		dto.setContent(MsgCons.M_28018);	
		}
		Message msg = new Message(appProducer.getProperties().getProperty(MqConstants.TOPIC),MqConstants.TAG,SerializeUtil.serialize(dto));
		msg.setKey(dto.getMemberId().toString());
		try {
		   logger.info("验货通过状态MQ发送时间:{},memberId:{},message:{}",new Object[]{DateFormatUtils.format(new Date(), DateUtil.DATE_FORMAT_DATETIME),memberId,JSON.toJSONString(msg)});
		   appProducer.send(msg);
		} catch (Exception e) {
			logger.error("验货通过状态MQ error",e);
			//异常数据存入表 mq_error
			this.insertMqError(dto,memberId,MqConstants.TOPIC_ORDER_INFO);
		}
	}
	
	/**
	 * 异常数据存入表 mq_error
	 * @param dto
	 */
	private void insertMqError(Object dto,Integer memberId,Integer topic){
		MqError mqError = new MqError();
		mqError.setBizType(MqConstants.BIZ_TYPE_1);
		mqError.setTopic(topic);
		mqError.setMemberId(memberId);
		mqError.setContent(GSONUtils.toJson(dto,false));
		mqError.setCreateUserId(1);
		baseDao.execute("MqError.insert", mqError);
	}
	
    public void insertMqError(Map<String, Object> sendMap,String remark,String url,Integer memberId){
	        MqError mqError = new MqError();
	        mqError.setBizType(MqConstants.BIZ_TYPE_2);
	    	mqError.setMemberId(memberId);
	    	mqError.setHttpUrl(url);
	        mqError.setContent(GSONUtils.toJson(sendMap,false));
	        mqError.setRemark(remark);
	        baseDao.execute("MqError.insert", mqError);
	 }
	
	/** 构造订单物流信息并保存
	 * @param OrderInfoDTO 订单信息DTO
	 * @param transStatus 物流状态
	 * @param closeReason 关闭原因。如果物流状态不属于关闭状态，则直接传入null即可。
	 * @return  保存的物流信息id
	 */
	private Long constractTransAndSave(OrderInfoDTO orderInfoDTO, Byte transStatus) {
		OrderInfoTransEntity transEntity = constractTransBaseData(orderInfoDTO);
		transEntity.setTransStatus(transStatus);
		return  baseDao.persist(transEntity, Long.class);
	}
	
	/** 构造订单-物流信息的基本数据
	 * @param orderInfoDTO
	 * @return
	 */
	private OrderInfoTransEntity constractTransBaseData(OrderInfoDTO orderInfoDTO) {
		OrderInfoTransEntity transEntity = new OrderInfoTransEntity();
		transEntity.setOrderInfoId(orderInfoDTO.getId());
		transEntity.setOrderNo(orderInfoDTO.getOrderNo());
		transEntity.setSourceId(orderInfoDTO.getSourceId());
		transEntity.setIsDeleted((byte)0);
		transEntity.setOperateTime(new Date());
		transEntity.setCreateTime(new Date());
		transEntity.setUpdateTime(new Date());
		return transEntity;
	}
	
	
	 public  Map<String, Object> des3Map(Map<String, Object> map){
		   String json = GSONUtils.getGson().toJson(map);
			Map<String, Object> temp = new HashMap<String, Object>();
			try {
				temp.put("param", Des3.encode(json));
			} catch (Exception e) {
				return map;
			}
	   return temp;
 }

		/**
		 * 公共处理方法 参数值 传入调用接口 返回字符串
		 * 
		 * @param url
		 * @param map
		 * @return
		 * @throws Exception
		 */
		private ApiResult<?> pubMethod(String str) throws Exception {
			// 农速通api返回结果类
			ApiResult<?> apiResult = new ApiResult<>();
			// 谷登api返回结果类
			ObjectResult result = null;
			// 将返回结果解密
			String resultStr = Des3.decode(str);
			// 将解密后的结果 装换result
			result = JacksonUtil.str2Obj(resultStr, ObjectResult.class);
			logger.info("------------------------------------------------外部接口调用返回CODE: "+result.getStatusCode());	
			// 处理返回结果
			if (result.getStatusCode() == 0) {
				apiResult.setCodeMsg(MsgCons.C_10000, result.getMsg());
			} else {
				apiResult.withError(MsgCons.C_20000, result.getMsg());
			}
			return apiResult;
		}
	
}
