package cn.gdeng.nst.entity.nst;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * AppVersion Entity
 * 
 */

@Entity(name = "appversion")
public class AppVersion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6382723639734560108L;

	// Fields
	private String id;
	private String type;
	private String num;
	private String apkAddress;
	private String status;
	private Date createtime;
	private String createUserId;
	private Date updateTime;
	private String updateUserId;
	/**APP文件md5值**/
	private String fileMd5;
	/**是否强制更新，默认不需要*/
	private String needEnforce;
	/**升级功能说明*/
	private String remark;
	
	private String platform;
	
	private String productleader;
	
	private String publishTime;
	
	private String svnAddress;
	
	
	/** Default constructor */
	public AppVersion() {
	}

	/** Property accessors */
	@Id
	@Column(name = "id", unique = true, nullable = false)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "type", nullable = false)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "num", unique = true, nullable = false)
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	@Column(name = "apkAddress", length = 128)
	public String getApkAddress() {
		return apkAddress;
	}

	public void setApkAddress(String apkAddress) {
		this.apkAddress = apkAddress;
	}

	@Column(name = "createtime")
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Column(name = "status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "createUserId")
	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	@Column(name = "updateTime")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "updateUserId")
	public String getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	@Column(name="fileMd5")
	public String getFileMd5() {
		return fileMd5;
	}

	public void setFileMd5(String fileMd5) {
		this.fileMd5 = fileMd5;
	}
	@Column(name="needEnforce")
	public String getNeedEnforce() {
		return needEnforce;
	}
	
	public void setNeedEnforce(String needEnforce) {
		this.needEnforce = needEnforce;
	}
	@Column(name="remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name="platform")
	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	@Column(name="productleader")
	public String getProductleader() {
		return productleader;
	}

	public void setProductleader(String productleader) {
		this.productleader = productleader;
	}

	@Column(name="publishTime")
	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	@Column(name="svnAddress")
	public String getSvnAddress() {
		return svnAddress;
	}

	public void setSvnAddress(String svnAddress) {
		this.svnAddress = svnAddress;
	}
	
	
}
