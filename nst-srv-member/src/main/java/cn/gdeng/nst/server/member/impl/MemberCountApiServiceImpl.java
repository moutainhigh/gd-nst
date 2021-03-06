package cn.gdeng.nst.server.member.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.gdeng.nst.api.dto.member.MemberCountDTO;
import cn.gdeng.nst.api.server.member.MemberCountApiService;
import cn.gdeng.nst.api.vo.member.MemberCountCompanyVo;
import cn.gdeng.nst.api.vo.member.MemberCountDriverVo;
import cn.gdeng.nst.dao.BaseDao;
import cn.gdeng.nst.entity.nst.MemberCountEntity;
import cn.gdeng.nst.entity.nst.MemberInfoEntity;
import cn.gdeng.nst.enums.MqConstants;
import cn.gdeng.nst.enums.MsgCons;
import cn.gdeng.nst.server.member.impl.mq.error.MqErrorAction;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.BizException;
import cn.gdeng.nst.util.web.api.SerializeUtil;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
/**
 * 用户统计信息
 * @author kwang 
 *
 */
@Service
public class MemberCountApiServiceImpl implements MemberCountApiService ,MessageListener {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private BaseDao<?> baseDao;
    
    /**
     * 查物流公司统计信息
     * @param MemberCountDTO
     */
	@Override
	public ApiResult<MemberCountCompanyVo> findByCompanyMemberId(
			MemberCountDTO dto) throws BizException {
		if(null==dto.getMemberId()){
			throw new BizException(MsgCons.C_20001, MsgCons.M_20001);
		}
		MemberCountCompanyVo vo = baseDao.queryForObject("MemberCountApi.findByCompanyMemberId", dto, MemberCountCompanyVo.class);     
        if(null==vo){
        	vo=new MemberCountCompanyVo();
        }
		ApiResult<MemberCountCompanyVo> apiResult = new ApiResult<MemberCountCompanyVo>(vo,MsgCons.C_10000,MsgCons.M_10000);
        return apiResult;
	}
    
	 /**
     * 查司机统计信息
     * @param MemberCountDTO
     */
	@Override
	public ApiResult<MemberCountDriverVo> findByDriverMemberId(
			MemberCountDTO dto) throws BizException {
		if(null==dto.getMemberId()){
			throw new BizException(MsgCons.C_20001, MsgCons.M_20001);
		}
		MemberCountDriverVo vo = baseDao.queryForObject("MemberCountApi.findByDriverMemberId", dto, MemberCountDriverVo.class);     
		 if(null==vo){
	        	vo=new MemberCountDriverVo();
	        }
		ApiResult<MemberCountDriverVo> apiResult = new ApiResult<MemberCountDriverVo>(vo,MsgCons.C_10000,MsgCons.M_10000);
        return apiResult;
	}
	
	/**
	 * MQ消费者
	 */
	public Action consume(Message message, ConsumeContext context) {
		logger.debug("memberCount消费:"+JSON.toJSONString(message));
		logger.debug("memberCount消费:"+JSON.toJSONString(context));
		MemberCountDTO dto = (MemberCountDTO) SerializeUtil.unserialize(message.getBody());
		try {
			if(null==dto.getMemberId()){
				MqErrorAction.insertMqError(baseDao,message,MqConstants.TOPIC_ORDER_INFO, "会员Id为NULL");
				logger.error("memberCount消费message:", JSON.toJSONString(message));
				logger.error("memberCount消费"+JSON.toJSONString(context));
				return Action.ReconsumeLater;
			}
			//用户统计数据是否存在
			Integer count=baseDao.queryForObject("MemberCountApi.findMemberCountByMemberIdCount", dto, Integer.class);
			if(count==0){
				//不存在的添加一条
				MemberCountEntity  me=new MemberCountEntity();
				me.setMemberId(dto.getMemberId());
				MemberInfoEntity paramEntity = new MemberInfoEntity();
				paramEntity.setId(dto.getMemberId());
				MemberInfoEntity resultEntity = baseDao.find(MemberInfoEntity.class, paramEntity);
			   if(null!=resultEntity){
				   me.setRegTime(resultEntity.getCreateTime());
			   }else{
					logger.error("MemberInfo信息为空时memberCount消费message:", JSON.toJSONString(message));   
			   }
			long re=baseDao.persist(me, Long.class);
			if(re>0){
				baseDao.execute("MemberCountApi.updateMemberCount", dto);
			}
		    }else{
		    	//存在的 修改
		    	baseDao.execute("MemberCountApi.updateMemberCount", dto);
		    }
			return Action.CommitMessage;
		} catch (Exception e) {
			logger.error("memberCount消费message:", JSON.toJSONString(message));
			logger.error("memberCount消费"+JSON.toJSONString(context), e);
			MqErrorAction.insertMqError(baseDao,dto,MqConstants.TOPIC_ORDER_INFO, "会员统计消费错误");
			return Action.ReconsumeLater;
		}
	}

}
