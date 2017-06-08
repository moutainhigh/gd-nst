package cn.gdeng.nst.admin.dto.right;

import java.io.Serializable;

/**
 * @author Administrator
 *
 */
public class BaseCataNameDto implements Serializable{

	private static final long serialVersionUID = 6597351139042193540L;

	/** 市场id */
	private Long marketId;
	
	/** '当前层次(1;2)', */
	private Integer curLevel;
	
	/** 状态(0.已删除;1未删除) */
	private String status;
	
	public BaseCataNameDto() {
	}

	public BaseCataNameDto(Long marketId, Integer curLevel, String status) {
		this.marketId = marketId;
		this.curLevel = curLevel;
		this.status = status;
	}

	public Long getMarketId() {
		return marketId;
	}

	public void setMarketId(Long marketId) {
		this.marketId = marketId;
	}

	public Integer getCurLevel() {
		return curLevel;
	}

	public void setCurLevel(Integer curLevel) {
		this.curLevel = curLevel;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "BaseCataNameRequest [marketId=" + marketId + ", curLevel=" + curLevel + ", status=" + status + "]";
	}
	
}
