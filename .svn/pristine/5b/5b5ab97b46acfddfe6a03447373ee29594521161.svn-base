package cn.gdeng.nst.api.server.source;

import java.util.List;
import java.util.Map;

import cn.gdeng.nst.api.dto.source.CarDriverDetailDto;
import cn.gdeng.nst.api.dto.source.FindCarLineDto;
import cn.gdeng.nst.api.vo.order.OrderDetailBaseVo;
import cn.gdeng.nst.entity.nst.FindCarLineEntity;
import cn.gdeng.nst.util.web.api.ApiPage;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.BizException;

/**
* @author DJB
* @version 创建时间：2016年8月4日 下午3:24:23
* 车源服务接口
*/

public interface CarService {
	
	
	//物流公司添加线路 并查询此线路车辆
	ApiResult<FindCarLineDto> saveFindCarLine(FindCarLineEntity findCarLineEntity) throws BizException;
	
	//分页查询司机线路
	ApiResult<ApiPage> queryFindCarLinePage(ApiPage page) throws BizException;
	
	//查询物流公司添加的线路
	ApiResult<List<FindCarLineDto>> queryFindCarLineDtos(Integer memberId) throws BizException;
	
	//删除查询线路
	ApiResult<List<FindCarLineDto>> deleteFindCarLineDtos(Map<String, Object> param) throws BizException;
	
	
	//查询司机详情
	ApiResult<CarDriverDetailDto> queryCarDetail(Integer memberId) throws BizException;
	
	//更新查询线路的选中情况
	void updateFindCarLine(Object id,Object memberId) throws BizException ;
	
	
  /**************  车主查找货源   start  ******************/
	
	/**
	 * 
	 * @Description: 车主查找货源-- 第一次进入系统推荐货源
	 * @param page
	 * @return
	 * @throws BizException
	 *
	 */
	ApiResult<ApiPage> queryRecommendGoods(ApiPage page) throws BizException;
	
	
	
	/**
	 * 
	 * @Description: 车主查询货源  --分页查找货源
	 * @param page
	 * @return
	 * @throws BizException
	 *
	 */
	ApiResult<ApiPage> queryGoodsPage(ApiPage page) throws BizException;
	
	/**
	 * 
	 * @Description: 物流公司找货源--分页查货源总数统计
	 * @param page
	 * @return
	 * @throws BizException
	 *
	 */
	ApiResult<Object> queryGoodsAllTotal(ApiPage page) throws BizException;	
	
	/**
	 * 
	 * @Description:车主查找货源-- 查询货源详情 
	 * @param param
	 * @return
	 * @throws BizException
	 *
	 */
	ApiResult<OrderDetailBaseVo> queryGoodsDetail(Map<String, Object> param) throws BizException;
	
	/**
	 * 
	 * @Description:  验证用户使用认证通过
	 * @param memberId
	 * @return
	 * @throws BizException
	 *
	 */
	void memberCer(Integer code,String msg,Integer memberId) throws BizException;
	
	
	

	

}
