package cn.gdeng.nst.api.vo.member;

public class MemberCountCompanyVo implements java.io.Serializable{
    
    private static final long serialVersionUID = -7290098147420186948L;
	  /**
	    *会员id
	    */
	    private Integer memberId;

	    /**
	    *物流公司总接单数
	    */
	    private Integer confirmSourceCount=0;

	    /**
	    *货源分配总数
	    */
	    private Integer AutoSourceCount=0;

		public Integer getMemberId() {
			return memberId;
		}

		public void setMemberId(Integer memberId) {
			this.memberId = memberId;
		}

		public Integer getConfirmSourceCount() {
			return confirmSourceCount;
		}

		public void setConfirmSourceCount(Integer confirmSourceCount) {
			this.confirmSourceCount = confirmSourceCount;
		}

		public Integer getAutoSourceCount() {
			return AutoSourceCount;
		}

		public void setAutoSourceCount(Integer autoSourceCount) {
			AutoSourceCount = autoSourceCount;
		}
	    
	    
	
	
}
