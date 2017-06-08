package cn.gdeng.nst.enums;

/**
 * 农速通状态返回表和信息
 * @author xiaojun
 */
public enum ApiStatEnum {
	// 成功返回 
	SUCCESS(200,true, "处理成功"),
	//返回失败
	FAIL(500,false,"请求失败");
	
	private int code;
	private boolean isSuccess;
	private String msg;
	private ApiStatEnum(int code,Boolean isSuccess,String msg){
		this.code=code;
		this.isSuccess=isSuccess;
		this.msg=msg;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
