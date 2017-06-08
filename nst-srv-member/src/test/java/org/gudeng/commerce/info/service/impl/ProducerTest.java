package org.gudeng.commerce.info.service.impl;

import java.util.Date;
import java.util.Properties;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.SendResult;

import cn.gdeng.nst.api.dto.member.MemberCountDTO;
import cn.gdeng.nst.api.dto.member.MemberSendDto;
import cn.gdeng.nst.util.web.api.SerializeUtil;

public class ProducerTest {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.ProducerId, "PID_dev_nst_order_info");//您在控制台创建的Producer ID
        properties.put(PropertyKeyConst.AccessKey,"FmQ1FZSfeGcLxl13");// AccessKey 阿里云身份验证，在阿里云服务器管理控制台创建
        properties.put(PropertyKeyConst.SecretKey, "t3QfuZjugirBJraeD8TIz1G5cpTfUY");// SecretKey 阿里云身份验证，在阿里云服务器管理控制台创建
        //properties.setProperty(PropertyKeyConst.SendMsgTimeoutMillis, "3000");//设置发送超时时间，单位毫秒
        Producer producer = ONSFactory.createProducer(properties);
        // 在发送消息前，必须调用start方法来启动Producer，只需调用一次即可。
        producer.start();
        //循环发送消息
//        for (int i = 0; i < 5; i++){
//            Message msg = new Message( //
//                // Message Topic
//                "nst_member_info",
//                // Message Tag 可理解为Gmail中的标签，对消息进行再归类，方便Consumer指定过滤条件在MQ服务器过滤
//                "TagA",
//                // Message Body 可以是任何二进制形式的数据， MQ不做任何干预，
//                // 需要Producer与Consumer协商好一致的序列化和反序列化方式
//                "Hello MQ".getBytes());
//            // 设置代表消息的业务关键属性，请尽可能全局唯一。
//            // 以方便您在无法正常收到消息情况下，可通过阿里云服务器管理控制台查询消息并补发。
//            // 注意：不设置也不会影响消息正常收发
//            msg.setKey("ORDERID_" + i);
//            // 同步发送消息，只要不抛异常就是成功
//            SendResult sendResult = producer.send(msg);
//            System.out.println(sendResult);
//        }
//        for(int i = 0; i< 10000;i++){
//        	 
//        }
        MemberCountDTO dto1 = new MemberCountDTO();
        dto1.setMemberId(7);
        dto1.setDriverIcome(11.11);
        /*dto1.setMemberId(20001);
        dto1.setCrud(0);
        dto1.setMobile("13700000001");
        dto1.setRealName("新增");
        dto1.setRegetype(2);
        dto1.setStatus(0);
        dto1.setUpdateTime(new Date());*/
        Message msg1 = new Message("dev_nst_order_info","TagA",SerializeUtil.serialize(dto1));
        SendResult sendResult1 = producer.send(msg1);
        System.out.println(sendResult1);
     /*   MemberSendDto dto2 = new MemberSendDto();
        dto2.setMemberId(20002);
        dto2.setCrud(1);
        dto2.setMobile("13700000002");
        dto2.setRealName("修改");
        dto2.setRegetype(2);
        dto2.setStatus(0);
        dto2.setUpdateTime(new Date());
        
      
       // msg1.setKey(dto1.getMemberId()+"");
        Message msg2 = new Message("nst_member_info","TagA",SerializeUtil.serialize(dto2));
        msg2.setKey(dto2.getMemberId()+"");
        
        
        SendResult sendResult2 = producer.send(msg2);
     
        System.out.println(sendResult2);*/
        // 在应用退出前，销毁Producer对象
        // 注意：如果不销毁也没有问题
        producer.shutdown();
    }
}