package cn.gdeng.nst.api.vo.order;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 订单操作日志
 * @author huangjianhua  2016年8月16日  下午4:19:11
 * @version 1.0
 */
public class OrderOperateLogVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7418015277663594933L;
	    /**
	    *物流描述
	    */
	    private String description;

	    /**
	    *创建时间
	    */
	    private Date createTime;

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
	    
}
