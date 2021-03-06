package cn.gdeng.nst.api.server.source;

import java.util.Map;

import cn.gdeng.nst.api.dto.source.SourceAssignHisDetailDTO;
import cn.gdeng.nst.util.web.api.ApiPage;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.BizException;

/**货源分配服务类
 * @author wjguo
 * datetime 2016年8月11日 上午10:15:11  
 *
 */
public interface GoodsAssignmentService {
	/**查询分配给我的货源。查询结果按分配的创建时间倒序。
	 * @param page 分页对象。
	 * @return 返回结果对象，数据为分页对象。
	 */
	ApiResult<ApiPage> queryMyAssginmentGoodsByPage(ApiPage page) throws BizException;
	
	/**接受分配的货源
	 * @param paramMap
	 * @return 返回结果对象，数据为影响的行数。
	 * @throws BizException
	 */
	ApiResult<Map<String, Object>> acceptAssigned(Map<String, Object> paramMap) throws BizException;
	
	
	/**接受平台配送的货源
	 * @param paramMap
	 * @return 返回结果对象，数据为影响的行数。
	 * @throws BizException
	 */
	ApiResult<Map<String, Object>> acceptPlatformDistribution(Map<String, Object> paramMap) throws BizException;
	
	
	/**拒绝分配的货源
	 * @param paramMap
	 * @return 返回结果对象，数据为影响的行数。
	 * @throws BizException
	 */
	ApiResult<Map<String, Object>> rejectAssigned(Map<String, Object> paramMap) throws BizException;
	
	/** 查询分配的货源详情
	 * @param paramMap
	 * @return  返回结果对象，数据为货源详情。
	 * @throws BizException
	 */
	ApiResult<SourceAssignHisDetailDTO> queryAssginmentGoodsDetail(Map<String, Object> paramMap) throws BizException;
	/**
	 * 查询分配的货有没有被查看
	 * @param paramMap
	 * @return 返回结果 为是否查看的标示
	 * @throws BizException
	 */
	ApiResult<Integer> queryAssiGoodsIsView(Map<String, Object> paramMap) throws BizException;
	/**
	 * 分配货源查看
	 * @param paramMap
	 * @return
	 * @throws BizException
	 */
	ApiResult<Integer> assignGoodsView(Map<String, Object> paramMap) throws BizException;
}
