package cn.gdeng.nst.api.dto.source;

import java.util.Date;

import cn.gdeng.nst.enums.CarTypeEnum;
import cn.gdeng.nst.enums.GoodsTypeEnum;
import cn.gdeng.nst.enums.SendGoodsTypeEnum;
import cn.gdeng.nst.util.web.api.NumUtils;

/**简化的货源详细DTO，省略接单人和物流公司的信息。
 * @author wjguo
 * datetime 2016年8月8日 下午2:06:18  
 *
 */
public class SourceShipperSimpleDetailDTO implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7543053087522348689L;

	/**
	 * 数据库id
	 */
	private Integer id;

    /**
     * 关联的会员id
     */
    private Integer memberId;
    
    /**出发地 省份+城市+区域（/  作为分隔符）
     * 
     */
    private String s_detail;
    
    /**
     * 发货地详细地址(补充详情地址)
     */
    private String s_detailed_address;
    
    /**
     * 目的地 省份+城市+区域（/  作为分隔符）
     */
    private String e_detail;
    
    /**
     * 收货地详细地址(补充详情地址)
     */
    private String e_detailed_address;
    
    /**
     * 分配物流公司memberId'
     */
    private Integer assignMemberId;
    
    /**
     * 装货时间
     */
    private Date sendDate;
    
    /**
     * 出发地的全地址，包括省市区和详细地
     */
    private String srcFullAddr;
    
    /**
     * 目的地的全地址，包括省市区和详细地
     */
    private String desFullAddr;
    /**货源状态名称，如：已分配、已发布、待确认、已接受、已过期
     * 
     */
    private String goodsStatusName;
    /**货源状态码。1表示分配中，2表示待确认，3已发布，4已接受，5已过期
     * 
     */
    private String goodsStatusCode;
    
    /**货源接单信息名称，如：暂无司机接单，司机已接单等
     * 
     */
    private String goodsAcceptOrderName;
    
    /**
     * 货主留言
     */
    private String remark;
    
    /**里程
     * 
     */
    private Double mileage;
    /**
     * 货物类型
     */
    private Byte goodsType;
    /**重量
     * 
     */
    private Double totalWeight;

    /**体积
     * 
     */
    private Integer totalSize;
    /**
     * 货物名称
     */
    private String goodsName;
    /**货源所需车型
     * 
     */
    private Byte carType;

    /**发货方式
     * 
     */
    private Byte sendGoodsType;
    /**货源所需车长
     * 
     */
    private Double carLength;

    /**意向运费
     * 
     */
    private Double freight;
    /**数据版本号
     * 
     */
    private Integer version;
    /**预订单id
     * 
     */
    private Integer orderBeforeId;
    /**当前货主名称
     * 
     */
    private String shipperName;
    /**服务器当前时间
     * 
     */
    private Date currentDate;
    
    /**
     * 货源线路类型。1 干线 2 同城
     */
    private Integer sourceType;
    /**货源创建时间,即发布时间。
     * 
     */
    private Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getS_detail() {
		return s_detail;
	}

	public void setS_detail(String s_detail) {
		this.s_detail = s_detail;
	}

	public String getS_detailed_address() {
		return s_detailed_address;
	}

	public void setS_detailed_address(String s_detailed_address) {
		this.s_detailed_address = s_detailed_address;
	}

	public String getE_detail() {
		return e_detail;
	}

	public void setE_detail(String e_detail) {
		this.e_detail = e_detail;
	}

	public String getE_detailed_address() {
		return e_detailed_address;
	}

	public void setE_detailed_address(String e_detailed_address) {
		this.e_detailed_address = e_detailed_address;
	}

	public Integer getAssignMemberId() {
		return assignMemberId;
	}

	public void setAssignMemberId(Integer assignMemberId) {
		this.assignMemberId = assignMemberId;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public String getSrcFullAddr() {
		return srcFullAddr;
	}

	public void setSrcFullAddr(String srcFullAddr) {
		this.srcFullAddr = srcFullAddr;
	}

	public String getDesFullAddr() {
		return desFullAddr;
	}

	public void setDesFullAddr(String desFullAddr) {
		this.desFullAddr = desFullAddr;
	}

	public String getGoodsStatusName() {
		return goodsStatusName;
	}

	public void setGoodsStatusName(String goodsStatusName) {
		this.goodsStatusName = goodsStatusName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Double getMileage() {
		return mileage;
	}

	public void setMileage(Double mileage) {
		this.mileage = mileage;
	}

	public Byte getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(Byte goodsType) {
		this.goodsType = goodsType;
	}

	public Double getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(Double totalWeight) {
		this.totalWeight = totalWeight;
	}

	public Integer getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(Integer totalSize) {
		this.totalSize = totalSize;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Byte getCarType() {
		return carType;
	}

	public void setCarType(Byte carType) {
		this.carType = carType;
	}

	public Byte getSendGoodsType() {
		return sendGoodsType;
	}

	public void setSendGoodsType(Byte sendGoodsType) {
		this.sendGoodsType = sendGoodsType;
	}

	public Double getCarLength() {
		return carLength;
	}

	public void setCarLength(Double carLength) {
		this.carLength = carLength;
	}

	public Double getFreight() {
		return freight;
	}

	public void setFreight(Double freight) {
		this.freight = freight;
	}
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	public Integer getOrderBeforeId() {
		return orderBeforeId;
	}

	public void setOrderBeforeId(Integer orderBeforeId) {
		this.orderBeforeId = orderBeforeId;
	}
	public String getGoodsAcceptOrderName() {
		return goodsAcceptOrderName;
	}

	public void setGoodsAcceptOrderName(String goodsAcceptOrderName) {
		this.goodsAcceptOrderName = goodsAcceptOrderName;
	}
	public String getGoodsStatusCode() {
		return goodsStatusCode;
	}

	public void setGoodsStatusCode(String goodsStatusCode) {
		this.goodsStatusCode = goodsStatusCode;
	}
	public String getShipperName() {
		return shipperName;
	}

	public void setShipperName(String shipperName) {
		this.shipperName = shipperName;
	}
	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}
	public Integer getSourceType() {
		return sourceType;
	}

	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**获取车型名称
	 * @return
	 */
	public String getCarTypeName() {
		return CarTypeEnum.getNameByCode(carType);
	}
	
	/**获取车长名称
	 * @return
	 */
	public String getCarLengthName() {
		if (carLength == null) {
			return null;
		}
		if (carLength == -2) {
			return "其他";
		}
		return carLength == -1 ? "不限" : NumUtils.format(carLength) + "米";
	}
	
	/**获取意向运费名称
	 * @return
	 */
	public String getFreightName() {
		if (freight == null) {
			return null;
		}
		return freight <= 0 ? "面议" : NumUtils.formatAndDotIdent(freight) + "元";
	}
	
	/**获取发货方式名称
	 * @return
	 */
	public String getSendGoodsTypeName() {
		return SendGoodsTypeEnum.getNameByCode(sendGoodsType);
	}
	
	
	/**获取货源类型名称
	 * @return
	 */
	public String getGoodsTypeName() {
		return GoodsTypeEnum.getNameByCode(goodsType);
	}

}
