/**
 *
 */
package cn.gdeng.nst.util.web.api;

public class ApiReturnObject {

	private int statusCode= -1;

	private String msg="";

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
