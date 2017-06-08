package cn.gdeng.nst.api.vo.member;

import java.io.Serializable;
/**
 * 常用线路PageVo
 * @author wangkai   2016-08-10
 * @version 1.0
 */

public class MemberLinePageVo implements Serializable{

	private static final long serialVersionUID = -1L;
	//ID
	private Integer id;
	//出发地
	private String startArea;
	//目的地
	private String endArea;
	//用户ID
	private Integer memberId;
	
	public String getStartArea() {
		String sDetail = startArea;
		if(sDetail != null){
			String[] detailArray = sDetail.split("/");
			StringBuilder sb = new StringBuilder();
			for(String val : detailArray){
				sb.append(val).append("");
			}
			return sb.toString();
		}
		return null;
	}
	public void setStartArea(String startArea) {
		this.startArea = startArea;
	}
	public String getEndArea() {
		String sDetail = endArea;
		if(sDetail != null){
			String[] detailArray = sDetail.split("/");
			StringBuilder sb = new StringBuilder();
			for(String val : detailArray){
				sb.append(val).append("");
			}
			return sb.toString();
		}
		return null;
	}
	public void setEndArea(String endArea) {
		this.endArea = endArea;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
