package cn.gdeng.nst.entity.nst;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "callstatistics")
public class CallstatisticsEntity implements java.io.Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -328702566528509356L;

	/**
    *id
    */
    private Integer id;

    /**
    *1:农商友, 2:农速通，3:农批商，4:供应商
    */
    private Byte callRole;


    private Byte source;

    /**
    *被叫号码
    */
    private String s_Mobile;

    /**
    *被叫人姓名
    */
    private String s_Name;

    /**
    *商铺名称
    */
    private String shopName;

    /**
    *主叫号码
    */
    private String e_Mobile;

    /**
    *主叫号姓名
    */
    private String e_Name;

    /**
    *拨打时间
    */
    private Date createTime;

    /**
    *1:农批商打给供应商2:供应商打给农批商3:农商友打给农批商,4:农批商打给农商友
    */
    private Byte fromCode;

    /**
    *被叫人memberid
    */
    private Integer b_memberid;

    /**
    *主叫人memberid
    */
    private Integer memberid;

    @Id
    @Column(name = "id")
    public Integer getId(){

        return this.id;
    }
    public void setId(Integer id){

        this.id = id;
    }
    @Column(name = "callRole")
    public Byte getCallRole(){

        return this.callRole;
    }
    public void setCallRole(Byte callRole){

        this.callRole = callRole;
    }
    @Column(name = "source")
    public Byte getSource(){

        return this.source;
    }
    public void setSource(Byte source){

        this.source = source;
    }
    @Column(name = "s_Mobile")
    public String getS_Mobile(){

        return this.s_Mobile;
    }
    public void setS_Mobile(String s_Mobile){

        this.s_Mobile = s_Mobile;
    }
    @Column(name = "s_Name")
    public String getS_Name(){

        return this.s_Name;
    }
    public void setS_Name(String s_Name){

        this.s_Name = s_Name;
    }
    @Column(name = "shopName")
    public String getShopName(){

        return this.shopName;
    }
    public void setShopName(String shopName){

        this.shopName = shopName;
    }
    @Column(name = "e_Mobile")
    public String getE_Mobile(){

        return this.e_Mobile;
    }
    public void setE_Mobile(String e_Mobile){

        this.e_Mobile = e_Mobile;
    }
    @Column(name = "e_Name")
    public String getE_Name(){

        return this.e_Name;
    }
    public void setE_Name(String e_Name){

        this.e_Name = e_Name;
    }
    @Column(name = "createTime")
    public Date getCreateTime(){

        return this.createTime;
    }
    public void setCreateTime(Date createTime){

        this.createTime = createTime;
    }
    @Column(name = "fromCode")
    public Byte getFromCode(){

        return this.fromCode;
    }
    public void setFromCode(Byte fromCode){

        this.fromCode = fromCode;
    }
    @Column(name = "b_memberid")
    public Integer getB_memberid(){

        return this.b_memberid;
    }
    public void setB_memberid(Integer b_memberid){

        this.b_memberid = b_memberid;
    }
    @Column(name = "memberid")
    public Integer getMemberid(){

        return this.memberid;
    }
    public void setMemberid(Integer memberid){

        this.memberid = memberid;
    }
}
