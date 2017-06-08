package cn.gdeng.nst.admin.dto.right;

import java.io.Serializable;

/**
 * @author yangjj
 */
public class BaiDuAuthHeaderDTO implements Serializable {

    private static final long serialVersionUID = -3493054094800022149L;
    // 用户名
    private String            username;
    // 成功登录后获取的会话id(st)
    private String            password;
    // api权限码
    private String            token;
    // 账户类型
    private Integer           account_type;
    // 被操作用户名(选填)
    private String            target;
    // oauth access token(选填)
    private String            accessToken;

    public BaiDuAuthHeaderDTO() {
    }

    public BaiDuAuthHeaderDTO(String username, String password, String token, Integer account_type, String target,
                              String accessToken) {
        super();
        this.username = username;
        this.password = password;
        this.token = token;
        this.account_type = account_type;
        this.target = target;
        this.accessToken = accessToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getAccount_type() {
        return account_type;
    }

    public void setAccount_type(Integer account_type) {
        this.account_type = account_type;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
