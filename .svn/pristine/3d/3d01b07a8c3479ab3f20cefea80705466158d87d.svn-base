package cn.gdeng.nst.entity.nst;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 6+1货源对应商家信息表
 * @author wjguo
 *
 * datetime:2016年12月3日 下午4:42:42
 */
@Entity(name = "source_merchant")
public class SourceMerchantEntity  implements java.io.Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -2628268806127765060L;

	/**
    *主键ID
    */
    private Integer id;

    /**
    *货源id
    */
    private Integer sourceId;

    /**
    *商家会员id
    */
    private Integer memberId;

    /**
    *商家名称
    */
    private String name;

    /**
    *商家电话
    */
    private String mobile;

    /**
    *商铺名称
    */
    private String title;

    /**
    *商铺地址
    */
    private String address;

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
    @Column(name = "sourceId")
    public Integer getSourceId(){

        return this.sourceId;
    }
    public void setSourceId(Integer sourceId){

        this.sourceId = sourceId;
    }
    @Column(name = "memberId")
    public Integer getMemberId(){

        return this.memberId;
    }
    public void setMemberId(Integer memberId){

        this.memberId = memberId;
    }
    @Column(name = "name")
    public String getName(){

        return this.name;
    }
    public void setName(String name){

        this.name = name;
    }
    @Column(name = "mobile")
    public String getMobile(){

        return this.mobile;
    }
    public void setMobile(String mobile){

        this.mobile = mobile;
    }
    @Column(name = "title")
    public String getTitle(){

        return this.title;
    }
    public void setTitle(String title){

        this.title = title;
    }
    @Column(name = "address")
    public String getAddress(){

        return this.address;
    }
    public void setAddress(String address){

        this.address = address;
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

