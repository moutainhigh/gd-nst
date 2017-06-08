package cn.gdeng.nst.server.order.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import com.aliyun.openservices.ons.api.SendResult;
import com.aliyun.openservices.ons.api.bean.ProducerBean;
import com.gudeng.framework.dba.transaction.annotation.Transactional;

import cn.gdeng.nst.api.dto.member.MemberSendDto;
import cn.gdeng.nst.api.dto.order.OrderDto;
import cn.gdeng.nst.api.server.order.DemoService;
import cn.gdeng.nst.dao.BaseDao;
import cn.gdeng.nst.entity.nst.MqError;
import cn.gdeng.nst.entity.nst.SysRegisterUser;
import cn.gdeng.nst.enums.MqConstants;
import cn.gdeng.nst.enums.MsgCons;
import cn.gdeng.nst.util.server.DateUtil;
import cn.gdeng.nst.util.server.jodis.JodisTemplate;
import cn.gdeng.nst.util.server.jodis.RedisCallBack;
import cn.gdeng.nst.util.server.jodis.ResultMap;
import cn.gdeng.nst.util.web.api.ApiPage;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.BizException;
import cn.gdeng.nst.util.web.api.GSONUtils;
import cn.gdeng.nst.util.web.api.SerializeUtil;
import redis.clients.jedis.Jedis;


/**
 *功能描述：
 */
@Service
public class DemoServiceImpl implements DemoService, MessageListener {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	private BaseDao<?> baseDao;
	@Resource
    private JodisTemplate jodisTemplate;
	@Resource
	private ProducerBean sourceShipperProducer;

	@Override
	public ApiResult<SysRegisterUser> getRedis(OrderDto param) throws Exception {
		ApiResult<SysRegisterUser> result = new ApiResult<SysRegisterUser>();
		
		/* 以下两种情形推荐使用 “回调形式”：
		 * 1、对于需要重复操作缓存，使用回调形式，减缓缓存池的重复读取和释放压力。
		 * 2、jodisTemplate只提供了最常用的缓存操作方法，如果提供的方法无法满足对缓存的操作，也应使用回调形式，直接操作jedis对象。
		 * 
		 * 附加：缓存操作完成后，无需用户手动释放资源，jodisTemplate模板对象自己会释放。
		 */
		ResultMap map = jodisTemplate.exec(new RedisCallBack() {

			@Override
			public Object invoke(Jedis jedis) {

				//缓存测试
				String key = "order000001";
				jedis.setex(key,120, "13700000000");
				String val = jedis.get(key);
				Map<String, String> map = jedis.hgetAll("aaaa");
				ResultMap result = new ResultMap();
				
				return result;
			
			}
			
		});
		
		String val = map.getByKey("val");
		Date currentDate = map.getByKey("currentDate");
		
		//简单的打印下当前时间
		System.out.println("currentDate--" + currentDate);
		
		//DB测试
		Map<String,Object> map2 = new HashMap<String,Object>();
		map2.put("userID", "admin001");
		SysRegisterUser user= baseDao.queryForObject("SysRegisterUser.get", map, SysRegisterUser.class);
		user.setMobile(val);
		result.setResult(user);
		return result;
	}

	@Override
	public ApiResult<ApiPage> queryUserListByPage(ApiPage page){
		// 创建返回结果对象
		ApiResult<ApiPage> result =new ApiResult<ApiPage>();
		try {
			//获取结果集
			List<SysRegisterUser> list= baseDao.queryForList("SysRegisterUser.getByCondition", page.getParaMap(), SysRegisterUser.class);
			//获取结果集总数
			int total=baseDao.queryForObject("SysRegisterUser.getTotal",page.getParaMap(), Integer.class);
			//将结果封装到分页对象中
			page.setRecordList(list)
				.setRecordCount(total);
			//将分页封装到返回结果
			return result.setResult(page);
		} catch (Exception e) {
			logger.error("",e);
			return result.withError(MsgCons.C_50000, MsgCons.M_50000);
		}
	}
	
	/**
	 * MQ消费者
	 */
	public Action consume(Message message, ConsumeContext context) {
		logger.debug("消费时间"+System.currentTimeMillis());
		logger.debug(JSON.toJSONString(message));
		logger.debug(JSON.toJSONString(context));
        try {
        	MemberSendDto dto = (MemberSendDto) SerializeUtil.unserialize(message.getBody());
        	logger.debug(JSON.toJSONString(dto));
        	Map<String, Object> dtoMap = GSONUtils.fromJsonToMapObj(GSONUtils.getGson().toJson(dto));
        	dtoMap.put("updateTime", DateUtil.getDate(dto.getUpdateTime(), DateUtil.DATE_FORMAT_DATETIME));
//        	if(0 == dto.getCrud()){
//        		baseDao.execute("MemberInfo.insertBaseInfo", dtoMap);
//        	}else{
//        		baseDao.execute("MemberInfo.updateBaseInfo", dtoMap);
//        	}
            return Action.CommitMessage;
        }catch (Exception e) {
        	logger.error("message:",JSON.toJSONString(message));
        	logger.error(JSON.toJSONString(context),e);
            return Action.ReconsumeLater;
        }
    }
	
	/**
	 * MQ生产者测试
	 */
	@Override
	@Transactional
	public ApiResult<Object> mqProvider(MemberSendDto memberSendDto) throws BizException {
			long start = System.currentTimeMillis();	        
			for(int i = 1; i < 2; i++){
				MemberSendDto dto1 = new MemberSendDto();
		        dto1.setMemberId(i*3000);
		        dto1.setCrud(0);
		        dto1.setMobile("137"+i);
		        dto1.setRealName("新增 ");
		        dto1.setRegetype(2);
		        dto1.setStatus(0);
		        dto1.setUpdateTime(new Date());
		        Message msg1 = new Message(sourceShipperProducer.getProperties().getProperty(MqConstants.TOPIC),MqConstants.TAG,SerializeUtil.serialize(dto1));
		        msg1.setKey(dto1.getMemberId()+"");
		        
		        //定时消息测试
		        long time = System.currentTimeMillis()+10*60*1000;
		        msg1.setStartDeliverTime(time);
		        logger.info("发送定时："+time);
		        
		        SendResult sendResult = null;
				try {
					sendResult = sourceShipperProducer.send(msg1);
					logger.info(sendResult.getMessageId());
					//throw new Exception();
				} catch (Exception e) {
					logger.error("",e);
					//异常数据存入表 mq_error
					insertMqError(dto1);
				}
		        
			}
			logger.info("mqProvider time:"+(System.currentTimeMillis()-start)+"---------------------");
	        return new ApiResult<Object>().setCodeMsg(11111, "send msg ok");
	}
	
	/**
	 * 异常数据存入表 mq_error
	 * @param dto
	 */
	private void insertMqError(MemberSendDto dto){
		MqError mqError = new MqError();
		mqError.setBizType(MqConstants.BIZ_TYPE_1);
		mqError.setTopic(MqConstants.TOPIC_SOURCE_SHIPPER);
		mqError.setMemberId(1);
		mqError.setContent(GSONUtils.toJson(dto,false));
		mqError.setCreateUserId(1);
		baseDao.execute("MqError.insert", mqError);
	}

//	/**
//	 * MQ生产者测试
//	 */
//	@Override
//	@Transactional
//	public ApiResult<Object> mqProvider(MemberSendDto memberSendDto) throws BizException {
//		ApiResult<Object> result =new ApiResult<Object>();
//		if(0==0){
//			result.withError(MsgCons.C_20001, MsgCons.M_20001);
//		}
//		if(1==1){
//			//throw new BizException(MsgCons.C_20001, MsgCons.M_20001);
//		}
//		saveTest();
//		saveTest2();
//		return result;
//	}
//	
//	private void saveTest() throws BizException {
//		SysRegisterUser user = new SysRegisterUser();
//		user.setUpdateUserID("123");
//		user.setUserName("张三");
//		user.setMobile("1111111111111");
//		user.setOrgUnitId("123");
//		user.setUserID("DCF11504947C429A85C623DFDEF6664B");
//		baseDao.execute("SysRegisterUser.update", user);
//	}
//	private void saveTest2() throws BizException {
//		SysRegisterUser user = new SysRegisterUser();
//		user.setUpdateUserID("456");
//		user.setUserName("李四");
//		user.setMobile("1111111111111");
//		user.setOrgUnitId("456");
//		user.setUserID("A9CB43DBE9BC4AF4B28066267CED58B7");
//		baseDao.execute("SysRegisterUser.update", user);
//		//int a = 1/0;
//	}
}
