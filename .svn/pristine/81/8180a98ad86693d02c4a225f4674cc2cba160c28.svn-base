package cn.gdeng.nst.admin.server.admin.mq.provider;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.config.annotation.Service;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.OnExceptionContext;
import com.aliyun.openservices.ons.api.SendCallback;
import com.aliyun.openservices.ons.api.SendResult;
import com.aliyun.openservices.ons.api.bean.ProducerBean;

import cn.gdeng.nst.admin.server.admin.mq.error.MqErrorAction;
import cn.gdeng.nst.admin.service.admin.mq.provider.MemberCerMqProviderService;
import cn.gdeng.nst.api.dto.task.PushMsgDto;
import cn.gdeng.nst.dao.BaseDao;
import cn.gdeng.nst.enums.MqConstants;
import cn.gdeng.nst.util.web.api.SerializeUtil;

/**
 * 会员认证消息提供者
 * @author yangjj
 *
 */
@Service
public class MemberCerMqProviderImpl implements MemberCerMqProviderService{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private BaseDao<?> baseDao;
    @Resource
    private ProducerBean msgPushProducer;
    
    @Override
    public void sendMemberCerAppMsg(String keyId,final PushMsgDto dto) {
        try{
            Message msg = new Message(msgPushProducer.getProperties().getProperty(MqConstants.TOPIC),MqConstants.TAG,SerializeUtil.serialize(dto));
            msg.setKey(keyId);
            msgPushProducer.sendAsync(msg, new SendCallback(){
                @Override
                public void onSuccess(SendResult sendResult) {
                    logger.info("MQ:sendMemberCerAppMsg推送成功 " + sendResult);
                }

                @Override
                public void onException(OnExceptionContext context) {
                    logger.error("MQ:sendMemberCerAppMsg推送异常", context.getException());
                    //异常数据存入表 mq_error
                    MqErrorAction.insertMqError(baseDao,dto,MqConstants.TOPIC_PUSH,"MQ:sendMemberCerAppMsg推送异常");
                }
            });
        }catch(Exception ex){
            logger.error("sendMemberCerAppMsg推送异常", ex);
            MqErrorAction.insertMqError(baseDao,dto,MqConstants.TOPIC_PUSH,"sendMemberCerAppMsg推送异常");
        }       
    }

}
