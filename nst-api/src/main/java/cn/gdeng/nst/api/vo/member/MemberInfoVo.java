package cn.gdeng.nst.api.vo.member;

import java.io.Serializable;


public class MemberInfoVo implements Serializable {

    private static final long serialVersionUID = 756928731291292291L;
    /**
     * 会员ID
     */
    private Integer memberId;
    /**
     * 联系人
     */
    private String userName;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 头像
     */
    private String iconUrl;
    /**
     * 账号
     */
    private String account;
    /**
     * 电话
     */
    private String mobile;
    /**
     * 业务类型  1 干线业务 2 同城业务
     */
    private Integer serviceType;
    /**
     * 注册来源 0管理后台，1谷登农批网，3农商友，4农商友-农批商，5农批友，6供应商，7POS刷卡 ，8微信授权，9农速通-货主，10农速通-司机，11农速通-物流公司
     */
    private Integer regetype;
    /**
     * 认证状态:0:未认证 1:认证中 2:认证通过 3:认证未通过
     */
    private Integer cerStatus;
    
    public Integer getMemberId() {
        return memberId;
    }
    
    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getRealName() {
        return realName;
    }
    
    public void setRealName(String realName) {
        this.realName = realName;
    }
    
    public String getIconUrl() {
        return iconUrl;
    }
    
    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
    
    public String getAccount() {
        return account;
    }
    
    public void setAccount(String account) {
        this.account = account;
    }
    
    public String getMobile() {
        return mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
    public Integer getServiceType() {
        return serviceType;
    }
    
    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }
    
    public Integer getRegetype() {
        return regetype;
    }
    
    public void setRegetype(Integer regetype) {
        this.regetype = regetype;
    }
    
    public Integer getCerStatus() {
        return cerStatus;
    }
    
    public void setCerStatus(Integer cerStatus) {
        this.cerStatus = cerStatus;
    }
    
}
