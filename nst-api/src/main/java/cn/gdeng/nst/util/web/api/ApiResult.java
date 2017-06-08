package cn.gdeng.nst.util.web.api;

import java.io.Serializable;

import cn.gdeng.nst.enums.MsgCons;

public class ApiResult<T> implements Serializable {
	private static final long serialVersionUID = 229227289014288108L;
	private Boolean isSuccess;
	private Integer code;
	private String msg;
	private T result;

	/**构造对象。默认是处理成功。
	 * 
	 */
	public ApiResult() {
		this(null, MsgCons.C_10000,  MsgCons.M_10000);
	}
	
	/** 构造对象。默认是处理成功。
	 * @param result
	 */
	public ApiResult(T result) {
		this(result, MsgCons.C_10000,  MsgCons.M_10000);
	}

	/** 构造对象。根据code码自动判断处理结果是否成功。
	 * @param result
	 * @param code
	 * @param msg
	 */
	public ApiResult(T result, Integer code, String msg) {
		if (MsgCons.C_10000.equals(code)) {
			this.isSuccess = Boolean.valueOf(true);
		} else {
			this.isSuccess = Boolean.valueOf(false);
		}
		this.result = result;
		this.code = code;
		this.msg = msg;
	}
	
	public boolean isSuccess() {
		return this.isSuccess.booleanValue();
	}

	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public T getResult() {
		return (T) this.result;
	}

	public ApiResult<T> setResult(T result) {
		this.result = result;
		return this;
	}

	public Integer getCode() {
		return this.code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	public ApiResult<T> setCodeMsg(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
		return this;
	}

	public ApiResult<T> withResult(T result) {
		setIsSuccess(Boolean.valueOf(true));
		setResult(result);
		return this;
	}

	public ApiResult<T> withError(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
		setIsSuccess(Boolean.valueOf(false));
		return this;
	}
}