package cn.gdeng.nst.api.vo.order;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import cn.gdeng.nst.enums.SourceExamineStatusEnum;
/**
 * 验货Vo
 * @author kwang
 *
 */
public class SourceExamineVo  implements java.io.Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -1635518064797775290L;

	/**
    *主键ID
    */
    private Integer id;

    /**
    *运单Id
    */
    private Integer orderId;

    /**
    *验货说明
    */
    private String description;

    /**
    *验货图片1
    */
    private String imageUrl;

    /**
    *验货图片2
    */
    private String imageUrl2;

    /**
    *验货图片3
    */
    private String imageUrl3;

    /**
    *验货图片4
    */
    private String imageUrl4;

    /**
    *验货图片5
    */
    private String imageUrl5;

    /**
    *验货状态 1 通过 2 不通过
    */
    private Byte status;
    
    /**
     * 通过  不通过
     */
     private String statusVar;

    /**
    *创建人员ID
    */
    private String createUserId;

    /**
    *创建时间
    */
    private Date createTime;

    /**
    *修改人员ID
    */
    private String updateUserId;

    /**
    *修改时间
    */
    private Date updateTime;

    public Integer getId(){

        return this.id;
    }
    public void setId(Integer id){

        this.id = id;
    }
    public Integer getOrderId(){

        return this.orderId;
    }
    public void setOrderId(Integer orderId){

        this.orderId = orderId;
    }
    public String getDescription(){

        return this.description;
    }
    public void setDescription(String description){

        this.description = description;
    }
    public String getImageUrl(){

        return this.imageUrl;
    }
    public void setImageUrl(String imageUrl){

        this.imageUrl = imageUrl;
    }
    public String getImageUrl2(){

        return this.imageUrl2;
    }
    public void setImageUrl2(String imageUrl2){

        this.imageUrl2 = imageUrl2;
    }
    public String getImageUrl3(){

        return this.imageUrl3;
    }
    public void setImageUrl3(String imageUrl3){

        this.imageUrl3 = imageUrl3;
    }
    public String getImageUrl4(){

        return this.imageUrl4;
    }
    public void setImageUrl4(String imageUrl4){

        this.imageUrl4 = imageUrl4;
    }
    public String getImageUrl5(){

        return this.imageUrl5;
    }
    public void setImageUrl5(String imageUrl5){

        this.imageUrl5 = imageUrl5;
    }
    public Byte getStatus(){

        return this.status;
    }
    public void setStatus(Byte status){

        this.status = status;
    }
    public String getCreateUserId(){

        return this.createUserId;
    }
    public void setCreateUserId(String createUserId){

        this.createUserId = createUserId;
    }
    public Date getCreateTime(){

        return this.createTime;
    }
    public void setCreateTime(Date createTime){

        this.createTime = createTime;
    }
    public String getUpdateUserId(){

        return this.updateUserId;
    }
    public void setUpdateUserId(String updateUserId){

        this.updateUserId = updateUserId;
    }
    public Date getUpdateTime(){

        return this.updateTime;
    }
    public void setUpdateTime(Date updateTime){

        this.updateTime = updateTime;
    }
	public String getStatusVar() {
		return SourceExamineStatusEnum.getNameByCode(getStatus());
	}
	public void setStatusVar(String statusVar) {
		this.statusVar = statusVar;
	}
    
    
}

