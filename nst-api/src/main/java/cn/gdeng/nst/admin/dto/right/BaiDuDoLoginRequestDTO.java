package cn.gdeng.nst.admin.dto.right;

import java.io.Serializable;

/**
 * @author yangjj
 */
public class BaiDuDoLoginRequestDTO implements Serializable {

	private static final long serialVersionUID = 7519464571044022404L;
	// 用户输入密码
	private String password;
	// 验证码
	private String imageCode;
	// 验证码会话id
	private String imageSsid;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImageCode() {
		return imageCode;
	}

	public void setImageCode(String imageCode) {
		this.imageCode = imageCode;
	}

	public String getImageSsid() {
		return imageSsid;
	}

	public void setImageSsid(String imageSsid) {
		this.imageSsid = imageSsid;
	}
}
