package cn.gdeng.nst.api.server.member;

import java.util.Map;

import cn.gdeng.nst.api.dto.member.RuleOnoffDTO;
import cn.gdeng.nst.api.vo.member.RuleOnoffDetailVo;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.BizException;

public interface RuleOnoffApiService {
	
	/**
     * 修改订阅状态
     * @param  id
     * @return 影响条数
     */
	public ApiResult<Integer>  updateRuleOnoffById(RuleOnoffDTO ruleOnoffDTO) throws BizException;
	
	/** 判断当前会员memberId是否可以接受分配的货源。
	 *  
	 * @param mep的entity:  membereId 会员id
	 * @return 结果集对象，如果可以分配货源，则状态码code返回1000，反之返回对应的状态码。
	 * @throws BizException
	 */
	public ApiResult<?>  IsCanAcceptedGoods(Map<String, Object> param) throws BizException;

	 /**
     * 修改订阅地址
     * @param  id
     * @return 影响条数
     */
	ApiResult<Integer> updateRuleOnoffAddressById(RuleOnoffDTO ruleOnoffDTO)
			throws BizException;

	 /**
     * 根据ID 查订阅开关详情
     * @param RuleOnoffDTO
     */
	ApiResult<RuleOnoffDetailVo> queryRuleOnoffById(
			RuleOnoffDTO ruleOnoffDTO) throws BizException;
}
