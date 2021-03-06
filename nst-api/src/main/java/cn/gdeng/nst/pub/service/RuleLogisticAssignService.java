package cn.gdeng.nst.pub.service;

import java.util.Map;

import cn.gdeng.nst.api.vo.pub.AssignVO;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.BizException;


/**
 * 是否指派物流公司
 * 
 * @author wj
 */
public interface RuleLogisticAssignService {
	/**
	 * 验证是否分配
	 * @param memberIdShipper 货主ID
	 * @return
	 */
	public ApiResult<AssignVO> validateAssign(Map<String, Object> paraMap) throws BizException;
}
