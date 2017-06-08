package cn.gdeng.nst.admin.dto.right;

import java.io.Serializable;

/**
 * @author yangjj
 */
public class BaiDuDoLogoutDTO extends BaiDuLoginBaseDTO implements Serializable {

    private static final long serialVersionUID = 783296585851940274L;

    private BaiDuDoLogoutRequestDTO request;

    public BaiDuDoLogoutDTO() {}

    public BaiDuDoLogoutDTO(String username, String token, String functionName, String uuid) {
        super(username, token, functionName, uuid);
    }

    public BaiDuDoLogoutRequestDTO getRequest() {
        return request;
    }

    public void setRequest(BaiDuDoLogoutRequestDTO request) {
        this.request = request;
    }

    private void initRequest() {
        if (request == null) {
            this.request = new BaiDuDoLogoutRequestDTO();
        }
    }

    public void setUcid(long ucid) {
        initRequest();
        this.request.setUcid(ucid);
    }

    public void setSt(String st) {
        initRequest();
        this.request.setSt(st);
    }
}
