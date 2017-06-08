package cn.gdeng.nst.api.dto.task;

import java.io.Serializable;
import java.util.Map;

/**
 * 消息推送Dto
 * @author Administrator
 *
 */
public class PushMsgDto implements Serializable{

	private static final long serialVersionUID = -3366806331377340807L;
	/** 消息类型，定义请看PushConstants.java MSG_TYPE**/
	private Integer msgType;
	/** 业务主键Id**/
	private Integer bizId;
	/** 业务参数**/
	private Map<String,Object> bizMap;
	/** 会员Id**/
	private Integer memberId;
	/** 消息内容  1800个字符**/
	private String content;
	
	public Integer getMsgType() {
		return msgType;
	}
	
	public void setMsgType(Integer msgType) {
		this.msgType = msgType;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public Integer getBizId() {
		return bizId;
	}

	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}

	public Map<String, Object> getBizMap() {
		return bizMap;
	}

	public void setBizMap(Map<String, Object> bizMap) {
		this.bizMap = bizMap;
	}
	
	
}
