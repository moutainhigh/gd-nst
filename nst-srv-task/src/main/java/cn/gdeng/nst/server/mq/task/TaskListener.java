package cn.gdeng.nst.server.mq.task;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;

import cn.gdeng.nst.api.dto.task.TaskDto;
import cn.gdeng.nst.api.server.order.OrderInfoService;
import cn.gdeng.nst.api.server.source.GoodsProvidereDubboMQServie;
import cn.gdeng.nst.dao.BaseDao;
import cn.gdeng.nst.entity.nst.MqError;
import cn.gdeng.nst.enums.MqConstants;
import cn.gdeng.nst.util.server.DateUtil;
import cn.gdeng.nst.util.web.api.GSONUtils;
import cn.gdeng.nst.util.web.api.SerializeUtil;

/**
 * 定时消息处理MQ   （消费者）
 * @author huangjianhua  2016年8月17日  下午3:50:00
 * @version 1.0
 */
public class TaskListener implements  MessageListener {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	private OrderInfoService orderInfoService;
	@Resource
	private GoodsProvidereDubboMQServie goodsProvidereDubboMQServie;
	@Resource
	private BaseDao<?> baseDao;
	@Override
	public Action consume(Message message, ConsumeContext context) {
		logger.info("定时消息处理MQ 消费时间:{},message:{},context:{}",new Object[]{DateFormatUtils.format(new Date(), DateUtil.DATE_FORMAT_DATETIME),
				JSON.toJSONString(message),JSON.toJSONString(context)});
		TaskDto dto= null;
		try {
			dto=(TaskDto) SerializeUtil.unserialize(message.getBody());
			Map<String, Object> map=new HashMap<>();
			map.put("id",  dto.getBizId());
			switch(dto.getTaskType().intValue()){
				case MqConstants.TASK_TYPE_0 : orderInfoService.orderReceiveTimeOut(map); break;
				case MqConstants.TASK_TYPE_1 : goodsProvidereDubboMQServie.goodsAssignmentMQ(dto.getBizId()); break;
				default : insertMqError(dto,"未知定时消息类型");
			}
			return Action.CommitMessage;
		} catch (Exception e) {
			logger.error("定时消息处理MQ  异常:{}",e);
			insertMqError(dto,e.getMessage());
            return Action.ReconsumeLater;
		}
	}
	
	/**
	 * 异常数据存入表 mq_error
	 * @param dto
	 */
	private void insertMqError(TaskDto dto,String remark){
		try {
			MqError mqError = new MqError();
			mqError.setBizType(MqConstants.BIZ_TYPE_0);
			mqError.setTopic(MqConstants.TOPIC_TASK);
			mqError.setContent(GSONUtils.toJson(dto,false));
			mqError.setCreateUserId(0);
			mqError.setRemark(remark);
			baseDao.execute("MqError.insert", mqError);
		} catch (Exception e) {
			logger.error("",e);
		}
	}
}
