package cn.gdeng.nst.util.server;
/**
* @author DJB
* @version 创建时间：2016年8月5日 下午3:11:48
* 服务层异常
*/

public class ServiceException extends Exception {
	/**
	 * 错误码
	 */
	private Integer code;
	/**
	 * 消息
	 */
	private String msg;
	public ServiceException() {
		super();
	}
	public ServiceException(Integer code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	public ServiceException(String msg, Throwable cause) {
		super(msg, cause);
		this.msg = msg;
	}
	public ServiceException(String msg) {
		super(msg);
		this.msg = msg;
	}
	public ServiceException(Throwable cause) {
		super(cause);
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

}
