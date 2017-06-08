package cn.gdeng.nst.server.mq.push;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;

import cn.gdeng.nst.api.dto.task.PushMsgDto;
import cn.gdeng.nst.dao.BaseDao;
import cn.gdeng.nst.entity.nst.MemberInfoEntity;
import cn.gdeng.nst.entity.nst.MqError;
import cn.gdeng.nst.enums.MqConstants;
import cn.gdeng.nst.enums.MsgCons;
import cn.gdeng.nst.enums.PushConstants;
import cn.gdeng.nst.util.server.push.AndroidNotification;
import cn.gdeng.nst.util.server.push.AndroidUnicast;
import cn.gdeng.nst.util.server.push.IOSUnicast;
import cn.gdeng.nst.util.server.push.PushClient;
import cn.gdeng.nst.util.web.api.GSONUtils;
import cn.gdeng.nst.util.web.api.SerializeUtil;

/**
 * app友盟消息推送
 * @author nfzhang 20160801
 *
 */
public class PushListener implements  MessageListener {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	private BaseDao<?> baseDao;
	private Properties properties;
	private PushClient client = new PushClient();

	/**
	 * MQ消费者
	 * 消费需要推送的消息
	 */
	public Action consume(Message message, ConsumeContext context) {
		logger.debug(JSON.toJSONString(message));
		logger.debug(JSON.toJSONString(context));
		PushMsgDto dto = null;
        try {
        	dto = (PushMsgDto) SerializeUtil.unserialize(message.getBody());
        	logger.debug(JSON.toJSONString(dto));
        	if(null == dto.getMemberId()){
        		insertMqError(dto,MsgCons.M_26000);
        		return Action.CommitMessage;
        	}
        	Map<String, Object> paramMap = new HashMap<String, Object>();
        	paramMap.put("memberId", dto.getMemberId());
        	MemberInfoEntity memberInfoEntity = baseDao.queryForObject("MemberInfo.findDeviceTokenById", paramMap,MemberInfoEntity.class);
        	if(null == memberInfoEntity){
        		insertMqError(dto,MsgCons.M_26001);
        		return Action.CommitMessage;
        	}
        	if(null == memberInfoEntity.getDeviceTokens()
        			|| null == memberInfoEntity.getDeviceType()){
        		insertMqError(dto,MsgCons.M_26002);
        		return Action.CommitMessage;
        	}
        	switch(memberInfoEntity.getDeviceType()){
        		case PushConstants.DEVICE_TYPE_0 : sendMsgAndriod(memberInfoEntity,dto); break;
        		case PushConstants.DEVICE_TYPE_1 : sendMsgIos(memberInfoEntity,dto); break;
        		default:insertMqError(dto,MsgCons.M_26003);;
        	}
            return Action.CommitMessage;
        }catch (Exception e) {
        	logger.error("message:",JSON.toJSONString(message));
        	logger.error(JSON.toJSONString(context),e);
        	insertMqError(dto,e.getMessage());
            return Action.ReconsumeLater;
        }
    }
	/**
	 * 消息推送-android
	 * @param memberInfoEntity
	 * @throws Exception
	 */
	private void sendMsgAndriod(MemberInfoEntity memberInfoEntity,PushMsgDto dto) throws Exception{
		AndroidUnicast unicast = new AndroidUnicast(properties.getProperty(PushConstants.APP_TYPE_ANDROID_KEY+memberInfoEntity.getDeviceApp())
				,properties.getProperty(PushConstants.APP_TYPE_ANDROID_SECRET+memberInfoEntity.getDeviceApp()));
		unicast.setDeviceToken(memberInfoEntity.getDeviceTokens());
		//unicast.setTicker("Android unicast ticker");
		unicast.setTitle(MsgCons.M_26005);
		unicast.setText(dto.getContent());
		unicast.setTicker("农速通通知");
		unicast.goAppAfterOpen();
		unicast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
		if("true".equals(properties.getProperty(PushConstants.PRODUCTION_MODE))){
			unicast.setProductionMode();
		}else{
			unicast.setTestMode();
		}
		unicast.setExtraField(PushConstants.MSG_TYPE, dto.getMsgType()+"");
		unicast.setExtraField(PushConstants.BIZ_ID, dto.getBizId()+"");
		if(null != dto.getBizMap()){
			unicast.setExtraField(PushConstants.BIZ_MAP, GSONUtils.toJson(dto.getBizMap(), false));
		}
		
		boolean flag = client.send(unicast);
		if(!flag){
			insertMqError(dto,MsgCons.M_26006);
		}
	}
	/**
	 * 消息推送-IOS
	 * @param memberInfoEntity
	 * @throws Exception
	 */
	private void sendMsgIos(MemberInfoEntity memberInfoEntity,PushMsgDto dto) throws Exception{
		IOSUnicast unicast = new IOSUnicast(properties.getProperty(PushConstants.APP_TYPE_IOS_KEY+memberInfoEntity.getDeviceApp())
				,properties.getProperty(PushConstants.APP_TYPE_IOS_SECRET+memberInfoEntity.getDeviceApp()));
		unicast.setDeviceToken(memberInfoEntity.getDeviceTokens());
		unicast.setAlert(dto.getContent());
		unicast.setBadge(0);
		unicast.setSound("default");
		if("true".equals(properties.getProperty(PushConstants.PRODUCTION_MODE))){
			unicast.setProductionMode();
		}else{
			unicast.setTestMode();
		}
		unicast.setCustomizedField(PushConstants.MSG_TYPE, dto.getMsgType()+"");
		unicast.setCustomizedField(PushConstants.BIZ_ID, dto.getBizId()+"");
		if(null != dto.getBizMap()){
			unicast.setCustomizedField(PushConstants.BIZ_MAP, GSONUtils.toJson(dto.getBizMap(), false));
		}
		
		boolean flag = client.send(unicast);
		if(!flag){
			insertMqError(dto,MsgCons.M_26007);
		}
	}
	/**
	 * 异常数据存入表 mq_error
	 * @param dto
	 */
	private void insertMqError(PushMsgDto dto,String remark){
		MqError mqError = new MqError();
		mqError.setBizType(MqConstants.BIZ_TYPE_0);
		mqError.setTopic(MqConstants.TOPIC_PUSH);
		mqError.setMemberId(dto.getMemberId());
		mqError.setContent(GSONUtils.toJson(dto,false));
		mqError.setCreateUserId(0);
		mqError.setRemark(remark);
		baseDao.execute("MqError.insert", mqError);
	}
	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	
}
