package cn.gdeng.nst.entity.nst;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "source_shipper")
public class SourceShipperExtEntity  implements java.io.Serializable{
    /**
    *主键id
    */
    private Integer id;

    /**
    *关联会员id
    */
    private Integer memberId;

    /**
    *当前货主名称
    */
    private String shipperName;

    /**
    *当前货主电话
    */
    private String shipperMobile;

    /**
    *线路类型 1 干线 2 同城
    */
    private Byte sourceType;

    /**
    *货源类型 1 自有 2 分配 3 指派
    */
    private Byte sourceGoodsType;

    /**
    *出发地省会Id
    */
    private Integer s_provinceId;

    /**
    *出发地城市Id
    */
    private Integer s_cityId;

    /**
    *出发地区域Id
    */
    private Integer s_areaId;

    /**
    *出发地 省份+城市+区域（/  作为分隔符）
    */
    private String s_detail;

    /**
    *发货地详细地址(补充详情地址)
    */
    private String s_detailed_address;

    /**
    *出发地经度
    */
    private Double s_lng;

    /**
    *出发地纬度
    */
    private Double s_lat;

    /**
    *目的地省会Id
    */
    private Integer e_provinceId;

    /**
    *目的地城市Id
    */
    private Integer e_cityId;

    /**
    *目的地区域Id
    */
    private Integer e_areaId;

    /**
    *目的地 省份+城市+区域（/  作为分隔符）
    */
    private String e_detail;

    /**
    *收货地详细地址(补充详情地址)
    */
    private String e_detailed_address;

    /**
    *目的地经度
    */
    private Double e_lng;

    /**
    *目的地纬度
    */
    private Double e_lat;

    /**
    *装货时间 ,null:不限时间
    */
    private Date sendDate;

    /**
    *101 蔬菜水果,102 干调粮油,103 活鲜水产,104 食品饮料,105 冷冻商品,106 化肥种子,107 农资农具,108 日用品,109 建材设备,110 其他商品
    */
    private Byte goodsType;

    /**
    *总重量
    */
    private Double totalWeight;

    /**
    *总体积
    */
    private Integer totalSize;

    /**
    *货物名称
    */
    private String goodsName;

    /**
    *车型(-1:不限, 1:面包车, 2:金杯车, 3:小型平板, 4:中型平板, 5:小型厢货, 6:大型厢货, 7:敞车, 8:厢式货车, 9:高栏车, 10:平板车, 11:集装箱, 12:保温车, 13:冷藏车, 14:鲜活水车, 15:其他, 16依维柯
    */
    private Byte carType;

    /**
    *发货方式(1:不限;2:整车;3:零担)
    */
    private Byte sendGoodsType;

    /**
    *车长(-1表示不限制，-2表示其他)
    */
    private Double carLength;

    /**
    *意向运费(0表示面议)
    */
    private Double freight;

    /**
    *货主留言
    */
    private String remark;

    /**
    *里程
    */
    private Double mileage;

    /**
    *2 农速通 货主 3 农速通 物流公司 4 农商友 5 农商友 - 农批商 6 农商友-供应商
    */
    private Byte clients;

    /**
    *指派的公司/车主memberId
    */
    private Integer assignMemberId;

    /**
    *1 货主直发 2 分配规则_物流公司 3分配中 4物流公司直发 5分配规则_车主 6 指派_物流公司 7 指派_车主
    */
    private Byte nstRule;

    /**
    *0 非平台配送  1 平台配送
    */
    private Byte platform;

    /**
    *货源状态: 1 已发布  2 待确认 3 已接受 4 已过期 5 已关闭(平台配送)
    */
    private Byte sourceStatus;

    /**
    *被分配次数
    */
    private Byte assignCount;

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

    /**
    *版本控制
    */
    private Integer version;

    /**
    *真实发货时间(解决createTime被刷新的问题)
    */
    private Date realCreateTime;

    @Id
    @Column(name = "id")
    public Integer getId(){

        return this.id;
    }
    public void setId(Integer id){

        this.id = id;
    }
    @Column(name = "memberId")
    public Integer getMemberId(){

        return this.memberId;
    }
    public void setMemberId(Integer memberId){

        this.memberId = memberId;
    }
    @Column(name = "shipperName")
    public String getShipperName(){

        return this.shipperName;
    }
    public void setShipperName(String shipperName){

        this.shipperName = shipperName;
    }
    @Column(name = "shipperMobile")
    public String getShipperMobile(){

        return this.shipperMobile;
    }
    public void setShipperMobile(String shipperMobile){

        this.shipperMobile = shipperMobile;
    }
    @Column(name = "sourceType")
    public Byte getSourceType(){

        return this.sourceType;
    }
    public void setSourceType(Byte sourceType){

        this.sourceType = sourceType;
    }
    @Column(name = "sourceGoodsType")
    public Byte getSourceGoodsType(){

        return this.sourceGoodsType;
    }
    public void setSourceGoodsType(Byte sourceGoodsType){

        this.sourceGoodsType = sourceGoodsType;
    }
    @Column(name = "s_provinceId")
    public Integer getS_provinceId(){

        return this.s_provinceId;
    }
    public void setS_provinceId(Integer s_provinceId){

        this.s_provinceId = s_provinceId;
    }
    @Column(name = "s_cityId")
    public Integer getS_cityId(){

        return this.s_cityId;
    }
    public void setS_cityId(Integer s_cityId){

        this.s_cityId = s_cityId;
    }
    @Column(name = "s_areaId")
    public Integer getS_areaId(){

        return this.s_areaId;
    }
    public void setS_areaId(Integer s_areaId){

        this.s_areaId = s_areaId;
    }
    @Column(name = "s_detail")
    public String getS_detail(){

        return this.s_detail;
    }
    public void setS_detail(String s_detail){

        this.s_detail = s_detail;
    }
    @Column(name = "s_detailed_address")
    public String getS_detailed_address(){

        return this.s_detailed_address;
    }
    public void setS_detailed_address(String s_detailed_address){

        this.s_detailed_address = s_detailed_address;
    }
    @Column(name = "s_lng")
    public Double getS_lng(){

        return this.s_lng;
    }
    public void setS_lng(Double s_lng){

        this.s_lng = s_lng;
    }
    @Column(name = "s_lat")
    public Double getS_lat(){

        return this.s_lat;
    }
    public void setS_lat(Double s_lat){

        this.s_lat = s_lat;
    }
    @Column(name = "e_provinceId")
    public Integer getE_provinceId(){

        return this.e_provinceId;
    }
    public void setE_provinceId(Integer e_provinceId){

        this.e_provinceId = e_provinceId;
    }
    @Column(name = "e_cityId")
    public Integer getE_cityId(){

        return this.e_cityId;
    }
    public void setE_cityId(Integer e_cityId){

        this.e_cityId = e_cityId;
    }
    @Column(name = "e_areaId")
    public Integer getE_areaId(){

        return this.e_areaId;
    }
    public void setE_areaId(Integer e_areaId){

        this.e_areaId = e_areaId;
    }
    @Column(name = "e_detail")
    public String getE_detail(){

        return this.e_detail;
    }
    public void setE_detail(String e_detail){

        this.e_detail = e_detail;
    }
    @Column(name = "e_detailed_address")
    public String getE_detailed_address(){

        return this.e_detailed_address;
    }
    public void setE_detailed_address(String e_detailed_address){

        this.e_detailed_address = e_detailed_address;
    }
    @Column(name = "e_lng")
    public Double getE_lng(){

        return this.e_lng;
    }
    public void setE_lng(Double e_lng){

        this.e_lng = e_lng;
    }
    @Column(name = "e_lat")
    public Double getE_lat(){

        return this.e_lat;
    }
    public void setE_lat(Double e_lat){

        this.e_lat = e_lat;
    }
    @Column(name = "sendDate")
    public Date getSendDate(){

        return this.sendDate;
    }
    public void setSendDate(Date sendDate){

        this.sendDate = sendDate;
    }
    @Column(name = "goodsType")
    public Byte getGoodsType(){

        return this.goodsType;
    }
    public void setGoodsType(Byte goodsType){

        this.goodsType = goodsType;
    }
    @Column(name = "totalWeight")
    public Double getTotalWeight(){

        return this.totalWeight;
    }
    public void setTotalWeight(Double totalWeight){

        this.totalWeight = totalWeight;
    }
    @Column(name = "totalSize")
    public Integer getTotalSize(){

        return this.totalSize;
    }
    public void setTotalSize(Integer totalSize){

        this.totalSize = totalSize;
    }
    @Column(name = "goodsName")
    public String getGoodsName(){

        return this.goodsName;
    }
    public void setGoodsName(String goodsName){

        this.goodsName = goodsName;
    }
    @Column(name = "carType")
    public Byte getCarType(){

        return this.carType;
    }
    public void setCarType(Byte carType){

        this.carType = carType;
    }
    @Column(name = "sendGoodsType")
    public Byte getSendGoodsType(){

        return this.sendGoodsType;
    }
    public void setSendGoodsType(Byte sendGoodsType){

        this.sendGoodsType = sendGoodsType;
    }
    @Column(name = "carLength")
    public Double getCarLength(){

        return this.carLength;
    }
    public void setCarLength(Double carLength){

        this.carLength = carLength;
    }
    @Column(name = "freight")
    public Double getFreight(){

        return this.freight;
    }
    public void setFreight(Double freight){

        this.freight = freight;
    }
    @Column(name = "remark")
    public String getRemark(){

        return this.remark;
    }
    public void setRemark(String remark){

        this.remark = remark;
    }
    @Column(name = "mileage")
    public Double getMileage(){

        return this.mileage;
    }
    public void setMileage(Double mileage){

        this.mileage = mileage;
    }
    @Column(name = "clients")
    public Byte getClients(){

        return this.clients;
    }
    public void setClients(Byte clients){

        this.clients = clients;
    }
    @Column(name = "assignMemberId")
    public Integer getAssignMemberId(){

        return this.assignMemberId;
    }
    public void setAssignMemberId(Integer assignMemberId){

        this.assignMemberId = assignMemberId;
    }
    @Column(name = "nstRule")
    public Byte getNstRule(){

        return this.nstRule;
    }
    public void setNstRule(Byte nstRule){

        this.nstRule = nstRule;
    }
    @Column(name = "platform")
    public Byte getPlatform(){

        return this.platform;
    }
    public void setPlatform(Byte platform){

        this.platform = platform;
    }
    @Column(name = "sourceStatus")
    public Byte getSourceStatus(){

        return this.sourceStatus;
    }
    public void setSourceStatus(Byte sourceStatus){

        this.sourceStatus = sourceStatus;
    }
    @Column(name = "assignCount")
    public Byte getAssignCount(){

        return this.assignCount;
    }
    public void setAssignCount(Byte assignCount){

        this.assignCount = assignCount;
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
    @Column(name = "version")
    public Integer getVersion(){

        return this.version;
    }
    public void setVersion(Integer version){

        this.version = version;
    }
    @Column(name = "realCreateTime")
    public Date getRealCreateTime(){

        return this.realCreateTime;
    }
    public void setRealCreateTime(Date realCreateTime){

        this.realCreateTime = realCreateTime;
    }
}

