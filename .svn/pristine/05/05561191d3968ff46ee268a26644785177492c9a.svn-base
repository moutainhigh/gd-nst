package cn.gdeng.nst.admin.dto.right;

import java.io.Serializable;

/**
 * 百度登录基类
 * @author yangjj
 */
public class BaiDuLoginBaseDTO implements Serializable{

	private static final long serialVersionUID = 7212799974352307947L;
    //登录用户名	
	private String username;
	//Drapi权限码
	private String token;
	//登录阶段方法名
	private String functionName;
	//唯一标识码，用于标识设备,MAC地址
	private String uuid;
	
	public BaiDuLoginBaseDTO(){};
	
	public BaiDuLoginBaseDTO(String username, String token,
			String functionName, String uuid) {
		super();
		this.username = username;
		this.token = token;
		this.functionName = functionName;
		this.uuid = uuid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}
