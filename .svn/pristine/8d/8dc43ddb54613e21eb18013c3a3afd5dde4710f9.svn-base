package cn.gdeng.nst.web.controller.source;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.gdeng.nst.api.server.source.GoodsService;
import cn.gdeng.nst.util.web.api.ApiPage;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.BizException;
import cn.gdeng.nst.web.controller.base.BaseController;
import cn.gdeng.nst.web.util.DataCheckUtils;


/**货源查询控制类
 * @author wjguo
 * datetime 2016年8月4日 上午10:31:20  
 *
 */
@Controller
@RequestMapping("v1/goodsQuery")
public class GoodsQueryController extends BaseController {
	/**
	 * 货源服务类
	 */
	@Reference
	private GoodsService goodsService;
	
	
	/**货主查询我已发布的货源
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("queryMyPublishedForShipper")
	@ResponseBody
	public ApiResult<?> queryMyPublishedForShipper(HttpServletRequest request) throws Exception {
		//解密参数并转成ApiPage对象
		ApiPage page = getPageInfoEncript(request);
		//数据校验
		checkDataBeforeQueryMyGoods(page.getParaMap());
		return goodsService.queryMyPublishedByPageForShipper(page);
	}

	/**货主查询我已过期的货源
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("queryMyOverdueForShipper")
	@ResponseBody
	public ApiResult<?> queryMyOverdueForShipper(HttpServletRequest request) throws Exception {
		//解密参数并转成ApiPage对象
		ApiPage page = getPageInfoEncript(request);
		//数据校验
		checkDataBeforeQueryMyGoods(page.getParaMap());
		return goodsService.queryMyOverdueByPageForShipper(page);
	}
	
	
	/**查询我的货源之前的数据检测。
	 * @param paraMap
	 * @param api
	 * @throws BizException  如果校验不通过，抛出此异常。
	 */
	private void checkDataBeforeQueryMyGoods(Map<String, Object> paraMap) throws BizException {
		DataCheckUtils.assertIsNonNull("关联会员id不能为空", paraMap.get("memberId"));
	}
	
	
	/**货主查询我待确认的货源
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("queryMyUnconfirmedForShipper")
	@ResponseBody
	public ApiResult<?> queryMyUnconfirmedForShipper(HttpServletRequest request) throws Exception {
		//解密参数并转成ApiPage对象
		ApiPage page = getPageInfoEncript(request);
		//当前时间，以服务器的时间为准
		page.getParaMap().put("currentDate", new Date());
		//数据校验
		checkDataBeforeQueryMyGoods(page.getParaMap());
		return goodsService.queryMyUnconfirmedByPageForShipper(page);
	}
	

	
	/**货主查询我全部的货源
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("queryMyAllForShipper")
	@ResponseBody
	public ApiResult<?> queryMyAllForShipper(HttpServletRequest request) throws Exception {
		//解密参数并转成ApiPage对象
		ApiPage page = getPageInfoEncript(request);
		//数据校验
		checkDataBeforeQueryMyGoods(page.getParaMap());
		return goodsService.queryMyAllByPageForShipper(page);
	}
	
	
	/**货主查询货物详情
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("queryGoodsDetailForShipper")
	@ResponseBody
	public ApiResult<?> queryGoodsDetailForShipper(HttpServletRequest request) throws Exception {
		Map<String, Object> paraMap = getDecodeMap(request);
		
		//数据校验
		checkDataBeforeQueryGoodsDetail(paraMap);
		return goodsService.queryGoodsDetailForShipper(paraMap);
	}
	
	
	/**货主查询货物详情，包含货源日志信息。
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("queryDetailAndLogForShipper")
	@ResponseBody
	public ApiResult<?> queryDetailAndLogForShipper(HttpServletRequest request) throws Exception {
		Map<String, Object> paraMap = getDecodeMap(request);
		
		//数据校验
		checkDataBeforeQueryGoodsDetail(paraMap);
		return goodsService.queryGoodsDetailAndLogForShipper(paraMap);
	}
	
	/**查询货源详情前的数据校验
	 * @param paraMap
	 * @param api
	 * @throws BizException  如果校验不通过，抛出此异常。
	 */
	private void checkDataBeforeQueryGoodsDetail(Map<String, Object> paraMap) throws BizException {
		DataCheckUtils.assertIsNonNull("货源id不能为空", paraMap.get("id"));
	}
	
	
	/**物流公司查询自己已发布的货源
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("queryMyPublishedForLogistics")
	@ResponseBody
	public ApiResult<?> queryMyPublishedForLogistics(HttpServletRequest request) throws Exception {
		List<Byte> sourceStatusList = new ArrayList<Byte>(1);
		//状态为1的货源，代表已发布。
		sourceStatusList.add((byte) 1);
		return queryMyGoodsByPageForLogistics(sourceStatusList, request);
	}
	
	/**物流公司查询自己已过期的货源
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("queryMyOverdueForLogistics")
	@ResponseBody
	public ApiResult<?> queryMyOverdueForLogistics(HttpServletRequest request) throws Exception {
		List<Byte> sourceStatusList = new ArrayList<Byte>(1);
		//状态为1的货源，代表已过期。
		sourceStatusList.add((byte) 4);
		return queryMyGoodsByPageForLogistics(sourceStatusList, request);
	}
	
	/**物流公司查询自己发布的全部货源。ps：只包括已发布的和已过期的。
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("queryMyAllForLogistics")
	@ResponseBody
	public ApiResult<?> queryMyAllForLogistics(HttpServletRequest request) throws Exception {
		List<Byte> sourceStatusList = new ArrayList<Byte>(2);
		//状态为1的货源，代表已发布。
		sourceStatusList.add((byte) 1);
		//状态为1的货源，代表已过期。
		sourceStatusList.add((byte) 4);
		return queryMyGoodsByPageForLogistics(sourceStatusList, request);
	}
	
	
	/** 物流公司查询自己发布的货源。
	 * @param sourceStatusList 查询的货源状态集合，可以同时查询多个状态下的货源。
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private ApiResult<?> queryMyGoodsByPageForLogistics(List<Byte> sourceStatusList, HttpServletRequest request) throws Exception {
		//解密参数并转成ApiPage对象
		ApiPage page = getPageInfoEncript(request);
		//指定货源状态
		page.getParaMap().put("sourceStatusList", sourceStatusList);
		//数据校验
		checkDataBeforeQueryMyGoods(page.getParaMap());
		return goodsService.queryMyGoodsByPageForLogistics(page);
	}
	
	/**物流公司查询自己发布的货源详情
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("queryMyGoodsDetailForLogistics")
	@ResponseBody
	public ApiResult<?> queryMyGoodsDetailForLogistics(HttpServletRequest request) throws Exception {
		Map<String, Object> paraMap = getDecodeMap(request);
		//数据校验
		checkDataBeforeQueryGoodsDetail(paraMap);
		//数据查询
		return goodsService.queryMyGoodsDetailForLogistics(paraMap);
	}
	
	/**通过id查询货源实体
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("queryGoodsEntityById")
	@ResponseBody
	public ApiResult<?> queryGoodsEntityById(HttpServletRequest request) throws Exception {
		//请求参数map。ps:转为泛型为String类型，是为了防止整数转换为小数的问题。
		Map<String, String> reqParamMap = getDecodeMapStr(request);
		//调用服务的参数map
		Map<String, Object> serParamMap = new HashMap<String, Object>(reqParamMap);
		//数据校验
		checkDataBeforeQueryGoodsDetail(serParamMap);
		//数据查询
		return goodsService.getEntityById(serParamMap);
	}
	
	

}
