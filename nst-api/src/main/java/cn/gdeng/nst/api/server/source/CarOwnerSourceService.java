package cn.gdeng.nst.api.server.source;

import java.util.Map;

import cn.gdeng.nst.util.web.api.ApiPage;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.BizException;

/**
 * 
 * @author yjj
 * 车主分给我的货中的货源service
 *
 */
public interface CarOwnerSourceService{
    
	/**
	 * 车主-分给我的货-货源列表
	 */
	public ApiResult<ApiPage> queryPage(ApiPage page) throws BizException;
	/**
	 * 货源详情
	 */
	public ApiResult<?> detail(Map<String, Object> reqParam) throws BizException;
	/**
	 * 接受货源
	 */
	public ApiResult<?> accept(Map<String, Object> reqParam) throws BizException;
	/**
	 * 查询货源历史分配记录
	 */
	public ApiResult<?> checkSourceAssignHis(Map<String, Object> reqParam) throws BizException;
}
