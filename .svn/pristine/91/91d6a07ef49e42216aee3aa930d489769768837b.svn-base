package org.gudeng.commerce.info.service.impl;

import java.util.Properties;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.SendResult;

import cn.gdeng.nst.api.dto.task.PushMsgDto;
import cn.gdeng.nst.enums.PushConstants;
import cn.gdeng.nst.util.web.api.SerializeUtil;

public class ProducerTest {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.ProducerId, "PID_dev_nst_msg_push");//您在控制台创建的Producer ID
        properties.put(PropertyKeyConst.AccessKey,"FmQ1FZSfeGcLxl13");// AccessKey 阿里云身份验证，在阿里云服务器管理控制台创建
        properties.put(PropertyKeyConst.SecretKey, "t3QfuZjugirBJraeD8TIz1G5cpTfUY");// SecretKey 阿里云身份验证，在阿里云服务器管理控制台创建
        properties.setProperty(PropertyKeyConst.SendMsgTimeoutMillis, "3000");//设置发送超时时间，单位毫秒
        Producer producer = ONSFactory.createProducer(properties);
        // 在发送消息前，必须调用start方法来启动Producer，只需调用一次即可。
        producer.start();
    
        PushMsgDto dto1 = new PushMsgDto();
        dto1.setMemberId(1);
        dto1.setBizId(5);
        dto1.setContent("您的证件审核不通过，请您修改");
        dto1.setMsgType(PushConstants.MSG_TYPE_1);
        Message msg1 = new Message("dev_nst_msg_push","TagA",SerializeUtil.serialize(dto1));
        SendResult sendResult1 = producer.send(msg1);
        
        //System.out.println(sendResult1);
        System.out.println(sendResult1);
        // 在应用退出前，销毁Producer对象
        // 注意：如果不销毁也没有问题
        producer.shutdown();
    }
}