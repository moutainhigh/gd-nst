package cn.gdeng.nst.api.vo.pub;

import java.io.Serializable;

/**
 * 版本控制VO
 * @author xiaojun
 *
 */
public class AppVersionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 782383988036300685L;
	/**
	 * 版本号
	 */
	private String num;
	/**
	 * apk下载地址
	 */
	private String apkAddress;
	/**
	 * 版本描述
	 */
	private String remark;
	/**
	 * 是否强制执行(0.不强制执行;1.强制执行)
	 */
	private String needEnforce;
	/**
	 * 发布时间
	 */
	private String publishTime;

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getApkAddress() {
		return apkAddress;
	}

	public void setApkAddress(String apkAddress) {
		this.apkAddress = apkAddress;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getNeedEnforce() {
		return needEnforce;
	}

	public void setNeedEnforce(String needEnforce) {
		this.needEnforce = needEnforce;
	}

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
	
}
