package cn.gdeng.nst.admin.dto.right;

import java.io.Serializable;

/**
 * 百度登录类
 * 
 * @author yangjj
 */
public class BaiDuDoLoginDTO extends BaiDuLoginBaseDTO implements Serializable {

    private static final long serialVersionUID = -259731705769154701L;

    public BaiDuDoLoginDTO() {}

    public BaiDuDoLoginDTO(String username, String token, String functionName, String uuid) {
        super(username, token, functionName, uuid);
    }

    private BaiDuDoLoginRequestDTO request;

    public BaiDuDoLoginRequestDTO getRequest() {
        return request;
    }

    public void setRequest(BaiDuDoLoginRequestDTO request) {
        this.request = request;
    }

    private void initRequest() {
        if (request == null) {
            this.request = new BaiDuDoLoginRequestDTO();
        }
    }

    public void setPassword(String password) {
        initRequest();
        this.request.setPassword(password);
    }

    public void setImageCode(String imageCode) {
        initRequest();
        this.request.setImageCode(imageCode);
    }

    public void setImageSsid(String imageSsid) {
        initRequest();
        this.request.setImageSsid(imageSsid);
    }

}
