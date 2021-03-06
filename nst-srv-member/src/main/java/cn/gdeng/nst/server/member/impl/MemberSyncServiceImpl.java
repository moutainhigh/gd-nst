package cn.gdeng.nst.server.member.impl;

import java.util.HashMap;
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
import com.gudeng.framework.dba.transaction.annotation.Transactional;

import cn.gdeng.nst.api.server.member.MemberSyncService;
import cn.gdeng.nst.dao.BaseDao;
import cn.gdeng.nst.entity.nst.MqError;
import cn.gdeng.nst.enums.MqConstants;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.GSONUtils;
import cn.gdeng.nst.util.web.api.SerializeUtil;

/**
 * 会员同步 从gudeng member_baseInfo同步至gd-nst member_info 通过MQ或者数据库同步
 * 
 * @author znf 20160806
 *
 */
@Service
public class MemberSyncServiceImpl implements MemberSyncService, MessageListener {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	private BaseDao<?> baseDao;

	/**
	 * 无用接口
	 */
	@Override
	@Transactional
	public ApiResult<Object> syncByMemberId(Map<String, Object> param) throws Exception {
		return null;
	}

	/**
	 * MQ消费者
	 */
	public Action consume(Message message, ConsumeContext context) {
		logger.debug(JSON.toJSONString(message));
		logger.debug(JSON.toJSONString(context));
		Map<String, String> dtoMap = new HashMap<>();
		try {
			dtoMap = GSONUtils.fromJsonToMapStr(SerializeUtil.unserialize(message.getBody()) + "");
			if(dtoMap.containsKey("crud")){
			    String crud= dtoMap.get("crud").toString();
	            if (0 == Integer.parseInt(crud)) {
	                baseDao.execute("MemberInfo.insertBaseInfo", dtoMap);
	            } else {
	                baseDao.execute("MemberInfo.updateBaseInfo", dtoMap);
	            }
			}			
			return Action.CommitMessage;
		} catch (Exception e) {
			logger.error("message:", JSON.toJSONString(message));
			logger.error(JSON.toJSONString(context), e);
			insertMqError(dtoMap);
			return Action.ReconsumeLater;
		}
	}
	
	/**
	 * 异常数据存入表 mq_error
	 * @param dto
	 */
	private void insertMqError(Map<String, String> map){
		MqError mqError = new MqError();
		mqError.setBizType(MqConstants.BIZ_TYPE_0);
		mqError.setTopic(MqConstants.TOPIC_MEMBER_INFO);
		mqError.setContent(GSONUtils.toJson(map,false));
		baseDao.execute("MqError.insert", mqError);
	}
}
