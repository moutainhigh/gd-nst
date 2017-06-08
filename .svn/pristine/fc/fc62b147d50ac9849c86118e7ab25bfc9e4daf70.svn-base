package cn.gdeng.nst.api.server.member;

import cn.gdeng.nst.api.dto.member.MemberLineApiDTO;
import cn.gdeng.nst.entity.nst.MemberLineEntity;
import cn.gdeng.nst.util.web.api.ApiPage;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.BizException;

public interface MemberLineApiService {

	/**
	 * 查询常跑线路列表
	 * @param ApiPage
	 */
	public ApiResult<ApiPage>  queryPage(ApiPage page) throws BizException;
    /**
     * 删除线路（逻辑删除）
     * @param  id
     * @return 影响条数
     */
	public ApiResult<Integer>  deleteMemberLineById(MemberLineApiDTO memberLineApiDTO) throws BizException;
	/**
	 * 添加常跑线路
	 * @param MemberLineEntity
	 */
	public ApiResult<Long>  saveMemberLine(MemberLineEntity memberLineEntity) throws BizException;
}
