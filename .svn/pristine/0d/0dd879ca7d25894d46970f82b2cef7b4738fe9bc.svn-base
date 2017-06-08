package cn.gdeng.nst.api.server.member;

import java.util.Map;

import cn.gdeng.nst.util.web.api.ApiPage;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.BizException;

/**
 * 物流公司->个人中心->司机管理apiService
 * @author yjj
 *
 */
public interface MemberLogisticDriverApiService {

	/**
	 * 司机管理 -> 获取车主列表
	 * @param apiPage
	 * @return
	 */
	public ApiResult<ApiPage> queryPage(ApiPage apiPage) throws BizException;
	
	/**
	 * 司机管理 -> 司机列表（显示有车辆及无车辆的司机）
	 * @param apiPage
	 * @return
	 */
	public ApiResult<ApiPage> queryPageByCar(ApiPage apiPage) throws BizException;
	
	/**
	 * 司机管理 -> 添加车主
	 * @param map
	 * @return
	 */
	public ApiResult<Integer> save(Map<String,Object> map) throws BizException;
	
	/**
	 * 司机管理 -> 删除车主
	 * @param map
	 * @return
	 */
	public ApiResult<Integer> update(Map<String,Object> map) throws BizException;
	
	/**
	 * 司机管理 -> 校验车主是否有未完成的订单
	 * @param map
	 * @return
	 */
	public ApiResult<Integer> checkDriverOrder(Map<String,Object> map) throws BizException;
	
	/**
	 * 司机管理 -> 校验车主是否存在
	 * @param map
	 * @return
	 */
	public ApiResult<Integer> checkDriverExist(Map<String,Object> map) throws BizException;
}
