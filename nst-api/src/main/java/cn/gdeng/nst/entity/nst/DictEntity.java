package cn.gdeng.nst.entity.nst;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "t_dic")
public class DictEntity implements java.io.Serializable{

	private static final long serialVersionUID = -1339870494698657790L;

	private String id;
	private String name;
	
    @Id
	@Column(name = "id", unique = true, nullable = false)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "name",nullable = false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
