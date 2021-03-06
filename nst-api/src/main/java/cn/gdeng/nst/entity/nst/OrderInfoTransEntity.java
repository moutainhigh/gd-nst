package cn.gdeng.nst.entity.nst;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 运单-物流信息 实体
 * @author wjguo
 *
 * datetime:2016年12月5日 下午12:14:45
 */
@Entity(name = "order_info_trans")
public class OrderInfoTransEntity  implements java.io.Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -4631878591307283262L;

	/**
    *主键ID
    */
    private Integer id;

    /**
    *运单id
    */
    private Integer orderInfoId;

    /**
    *货运订单号
    */
    private String orderNo;

    /**
    *货源id
    */
    private Integer sourceId;

    /**
    *物流状态: 物流状态: 1待验货 2已发货 3车主已送达 4验货不通过 5 拒绝收货
    */
    private Byte transStatus;

    /**
    *操作时间
    */
    private Date operateTime;

    /**
    *是否删除(0:未删除;1:已删除)
    */
    private Byte isDeleted;

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

    @Id
    @Column(name = "id")
    public Integer getId(){

        return this.id;
    }
    public void setId(Integer id){

        this.id = id;
    }
    @Column(name = "orderInfoId")
    public Integer getOrderInfoId(){

        return this.orderInfoId;
    }
    public void setOrderInfoId(Integer orderInfoId){

        this.orderInfoId = orderInfoId;
    }
    @Column(name = "orderNo")
    public String getOrderNo(){

        return this.orderNo;
    }
    public void setOrderNo(String orderNo){

        this.orderNo = orderNo;
    }
    @Column(name = "sourceId")
    public Integer getSourceId(){

        return this.sourceId;
    }
    public void setSourceId(Integer sourceId){

        this.sourceId = sourceId;
    }
    @Column(name = "transStatus")
    public Byte getTransStatus(){

        return this.transStatus;
    }
    public void setTransStatus(Byte transStatus){

        this.transStatus = transStatus;
    }
    @Column(name = "operateTime")
    public Date getOperateTime(){

        return this.operateTime;
    }
    public void setOperateTime(Date operateTime){

        this.operateTime = operateTime;
    }
    @Column(name = "isDeleted")
    public Byte getIsDeleted(){

        return this.isDeleted;
    }
    public void setIsDeleted(Byte isDeleted){

        this.isDeleted = isDeleted;
    }
    @Column(name = "createUserId")
    public String getCreateUserId(){

        return this.createUserId;
    }
    public void setCreateUserId(String createUserId){

        this.createUserId = createUserId;
    }
    @Column(name = "createTime")
    public Date getCreateTime(){

        return this.createTime;
    }
    public void setCreateTime(Date createTime){

        this.createTime = createTime;
    }
    @Column(name = "updateUserId")
    public String getUpdateUserId(){

        return this.updateUserId;
    }
    public void setUpdateUserId(String updateUserId){

        this.updateUserId = updateUserId;
    }
    @Column(name = "updateTime")
    public Date getUpdateTime(){

        return this.updateTime;
    }
    public void setUpdateTime(Date updateTime){

        this.updateTime = updateTime;
    }
}
