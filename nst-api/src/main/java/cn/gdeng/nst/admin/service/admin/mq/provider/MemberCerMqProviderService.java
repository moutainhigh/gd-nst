package cn.gdeng.nst.admin.service.admin.mq.provider;

import cn.gdeng.nst.api.dto.task.PushMsgDto;


public interface MemberCerMqProviderService {
      
    public void sendMemberCerAppMsg(String keyId,PushMsgDto dto);
}
