package cn.gdeng.nst.api.server.source;

import java.util.Map;

import cn.gdeng.nst.api.dto.source.SourceShipperFullDetailDTO;
import cn.gdeng.nst.api.dto.source.SourceShipperSimpleDetailDTO;
import cn.gdeng.nst.entity.nst.SourceMerchantEntity;
import cn.gdeng.nst.entity.nst.SourceShipperEntity;
import cn.gdeng.nst.util.web.api.ApiPage;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.BizException;

/**货源服务接口
 * @author wjguo
 * datetime 2016年8月3日 下午3:38:22  
 *
 */
public interface GoodsService {
	/**保存实体。
	 * @param entity 
	 * @return 返回结果对象，数据为保存的数据库id
	 */
	ApiResult<Map<String, Object>> save(SourceShipperEntity entity) throws BizException;
	/**保存实体。
	 * @param shipper 
	 * @param merchant 
	 * @return 返回结果对象，数据为保存的SourceShipperEntity数据库id。
	 */
	ApiResult<Map<String, Object>> save(SourceShipperEntity shipper, SourceMerchantEntity merchant) throws BizException;

	
	/** 货主分页查询我发布的货源。查询结果按创建时间倒序。
	 * NOTE:我发布的货源包括"已发布"和"分配中"的
	 * @param page 分页对象。
	 * @return 返回结果对象，数据为分页对象。
	 */
	ApiResult<ApiPage> queryMyPublishedByPageForShipper(ApiPage page) throws BizException;
	
	/** 货主分页查询我已过期的货源。查询结果按创建时间倒序。
	 * NOTE:我发布的货源包括"已发布"和"分配中"的
	 * @param page 分页对象。
	 * @return 返回结果对象，数据为分页对象。
	 */
	ApiResult<ApiPage> queryMyOverdueByPageForShipper(ApiPage page) throws BizException;
	
	
	/** 货主分页查询我待确认的货源。查询结果按创建时间倒序。
	 * @param page 分页对象。
	 * @return 返回结果对象，数据为分页对象。
	 */
	ApiResult<ApiPage> queryMyUnconfirmedByPageForShipper(ApiPage page) throws BizException;
	
	/** 货主分页查询我全部的货源。查询结果按创建时间倒序。
	 * NOTE:全部货源不包括“已接受”的
	 * @param page 分页对象。
	 * @return 返回结果对象，数据为分页对象。
	 */
	ApiResult<ApiPage> queryMyAllByPageForShipper(ApiPage page) throws BizException;
	
	/**通过id安全删除。所谓安全删除，就是货物没有分配给对应的物流公司，
	 * 即assignMemberId字段为null，方可进行软删除。
	 * @param param
	 * @return 返回结果对象，数据为影响的行数
	 */
	ApiResult<Map<String, Object>> deleteSafelyById(Map<String, Object> param) throws BizException;

	/**货主查看货源详情
	 * @param param
	 * @return
	 */
	ApiResult<SourceShipperFullDetailDTO> queryGoodsDetailForShipper(Map<String, Object> param) throws BizException;
	
	/**物流公司查看自己发布的货源详情
	 * @param param
 	 * @return
	 */
	ApiResult<SourceShipperSimpleDetailDTO> queryMyGoodsDetailForLogistics(Map<String, Object> param) throws BizException;
	
	/**物流公司分页查询自己发布的货源。查询结果按创建时间倒序。
	 * @param param
	 * @return
	 */
	ApiResult<ApiPage> queryMyGoodsByPageForLogistics(ApiPage page) throws BizException;
	
	/** 刷新货源创建时间。ps：只更新已发布和已过期的时间，并且货源状态均改为已发布。
	 * @param param
	 * @return  结果集对象，数据为影响的行数和刷新的时间
	 * @throws BizException
	 */
	ApiResult<Map<String, Object>> refreshGoodsCreateTimeAndTransToPub(Map<String, Object> param) throws BizException;
	
	/** 更改货源装货时间，并重新刷新创建时间。ps：只更新已发布和已过期的时间，并且货源状态均改为已发布。
	 * @param param
	 * @return  结果集对象，数据为影响的行数。
	 * @throws BizException
	 */
	ApiResult<Map<String, Object>> changeSendDateAndTransToPub(Map<String, Object> param) throws BizException;
	
	/** 根据id获取实体对象
	 * @param param
	 * @return  结果集对象，数据entity对象。
	 * @throws BizException
	 */
	ApiResult<SourceShipperEntity> getEntityById(Map<String, Object> param) throws BizException;


	/**货主查看货源详情（包含货源日志信息）
	 * @param paraMap
	 * @return
	 */
	ApiResult<SourceShipperFullDetailDTO> queryGoodsDetailAndLogForShipper(Map<String, Object> paraMap) throws BizException;
	
	/**扫描过期货源并进行更新。
	 * @return 返回结果对象，数据为影响的行数(即过期的货源数)
	 */
	ApiResult<Map<String, Object>> scanOverdueAndUpdate() throws BizException;
	/**
	 * 刷新货源
	 * @return 返回结果为删除后插入的主键id
	 * @throws BizException
	 */
	ApiResult<Integer> refreshGoods(Map<String, Object> paramMap) throws BizException;
	/**
	 * 根据id删除货源
	 * @param paramMap
	 * @return
	 * @throws BizException
	 */
	ApiResult<Integer> deleteGoodsById(Integer updateId) throws BizException;
	
}
