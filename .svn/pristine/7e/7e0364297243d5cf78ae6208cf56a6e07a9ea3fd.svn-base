package cn.gdeng.nst.api.dto.order;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import cn.gdeng.nst.enums.OrderInfoTransStatusEnum;

/**
 * 运单-物流信息 DTO
 * @author kwang
 *
 * datetime:2016年12月5日 下午12:14:45
 */
public class OrderInfoTransDTO  implements java.io.Serializable{
	private static final long serialVersionUID = -4631878591307283262L;

    /**
    *物流状态: 0 待支付预付款(待确认) 1待验货(已确认) 2已发货(已确认) 3车主已送达(已确认) 4已支付尾款(已完成) 5已关闭(见closeReason)
    */
    private Byte transStatus;
    /**
    *创建时间
    */
    private Date createTime;
    /**
     * 物流状态名称
     */
    private String transStatusVar;

	public Byte getTransStatus() {
		return transStatus;
	}
	public void setTransStatus(Byte transStatus) {
		this.transStatus = transStatus;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getTransStatusVar() {
		return OrderInfoTransStatusEnum.getNameByCode(getTransStatus());
	}
	public void setTransStatusVar(String transStatusVar) {
		this.transStatusVar = transStatusVar;
	}



}
