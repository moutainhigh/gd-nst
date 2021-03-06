package cn.gdeng.nst.entity.nst;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "member_car")
public class MemberCarEntity  implements java.io.Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 8557956175236452348L;

	private Integer id;
    private Integer memberId;
    private String carNumber;
    private Byte carType;
    private Double load;
    private Double carLength;
    private String vehicleUrl;
    private String carHeadUrl;
    private String carRearUrl;
    private Byte isDeleted;
    private String createUserId;
    private Date createTime;
    private String updateUserId;
    private Date updateTime;

    private Byte isCarriage;
    @Id    @Column(name = "id")
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
    @Column(name = "carNumber")
    public String getCarNumber(){
        return this.carNumber;
    }
    public void setCarNumber(String carNumber){
        this.carNumber = carNumber;
    }
    @Column(name = "carType")
    public Byte getCarType(){
        return this.carType;
    }
    public void setCarType(Byte carType){
        this.carType = carType;
    }
    @Column(name = "`load`")
    public Double getLoad(){
        return this.load;
    }
    public void setLoad(Double load){
        this.load = load;
    }
    @Column(name = "carLength")
    public Double getCarLength(){
        return this.carLength;
    }
    public void setCarLength(Double carLength){
        this.carLength = carLength;
    }
    @Column(name = "vehicleUrl")
    public String getVehicleUrl(){
        return this.vehicleUrl;
    }
    public void setVehicleUrl(String vehicleUrl){
        this.vehicleUrl = vehicleUrl;
    }
    @Column(name = "carHeadUrl")
    public String getCarHeadUrl(){
        return this.carHeadUrl;
    }
    public void setCarHeadUrl(String carHeadUrl){
        this.carHeadUrl = carHeadUrl;
    }
    @Column(name = "carRearUrl")
    public String getCarRearUrl(){
        return this.carRearUrl;
    }
    public void setCarRearUrl(String carRearUrl){
        this.carRearUrl = carRearUrl;
    }
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
    public Date getUpdateTime(){
        return this.updateTime;
    }
    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
    }
    @Column(name = "isCarriage")
	public Byte getIsCarriage() {
		return isCarriage;
	}
	public void setIsCarriage(Byte isCarriage) {
		this.isCarriage = isCarriage;
	}
    
    
}
