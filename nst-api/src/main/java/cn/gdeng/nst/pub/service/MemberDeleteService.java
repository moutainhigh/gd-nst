package cn.gdeng.nst.pub.service;

import cn.gdeng.nst.pub.dto.MemberDeleteDTO;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.BizException;

/**
 * 会员删除汇总服务
 * 
 * @author xiaojun
 */
public interface MemberDeleteService {
	/**
	 * 删除
	 * 
	 * @throws BizException
	 */
	ApiResult<Integer> delete(MemberDeleteDTO dto) throws BizException;
}
