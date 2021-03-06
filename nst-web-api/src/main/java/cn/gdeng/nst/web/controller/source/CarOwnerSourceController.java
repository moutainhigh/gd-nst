package cn.gdeng.nst.web.controller.source;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gdeng.nst.api.server.member.RuleOnoffApiService;
import cn.gdeng.nst.api.server.source.CarOwnerSourceService;
import cn.gdeng.nst.enums.MsgCons;
import cn.gdeng.nst.util.web.api.ApiPage;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.web.controller.base.BaseController;
import cn.gdeng.nst.web.util.DataCheckUtils;

import com.alibaba.dubbo.config.annotation.Reference;

/**
 * 分给车主的货，车主自己接单，无须货主确认
 */
@Controller
@RequestMapping("v1/carOwnerSource")
public class CarOwnerSourceController extends BaseController  {
	
	@Reference
	private RuleOnoffApiService ruleOnoffApiService;
	
	@Reference
	private CarOwnerSourceService carOwnerSourceService;
	
	
	/**
	 * 货源列表查询分配给我待接受的货源(老接口兼容(20161130以前版本))
	 * 以后使用app Version做兼容判断
	 */
	@ResponseBody
	@RequestMapping("/queryPage")
	public Object queryPage(HttpServletRequest request) throws Exception{
		//======查询待接受的货源前，判断当前用户是否有分配权力和开启接受=====
		ApiPage page = getPageInfoEncript(request);
		Map<String, Object> reqParam = page.getParaMap();
		String memberType = getAppInfo(request).getDeviceApp();
		DataCheckUtils.assertIsNonNull("会员id不能为空", reqParam.get("memberId"));
		DataCheckUtils.assertIsNonNull("标签状态不能为空", reqParam.get("status"));
		DataCheckUtils.assertIsNonNull("会员类型不能为空", memberType);
		String status = (String) reqParam.get("status");
		page.getParaMap().put("memberType", memberType);
		Map<String,Object> map = new HashMap<String,Object>();
		//1、待处理 2、已接受 3、已拒绝 4、已超时
		if("1".equals(status)){
			ApiResult<?> apiResult = ruleOnoffApiService.IsCanAcceptedGoods(reqParam);
			//不能获取分配的货源，直接返回错误码提示客户端。
			if (MsgCons.C_10000.intValue() != apiResult.getCode().intValue()) {
				return apiResult;
			}
			//接口版本兼容 以后使用app Version做兼容判断
			if (1 == ((Integer)apiResult.getResult()).intValue()) {
				return new ApiResult<>().withError(MsgCons.C_22101, MsgCons.M_22101);
			}
			//是否开启收货模式(1:关闭;2:开启)
			map.put("onOff", apiResult.getResult());
		}
		
		ApiResult<ApiPage> result = carOwnerSourceService.queryPage(page);
		result.getResult().setParaMap(map);
		return result;
	}
	
	/**
	 * 货源列表（新接口20161130）
	 * 以后使用app Version做兼容判断
	 */
	@ResponseBody
	@RequestMapping("/queryPageV2")
	public Object queryPageV2(HttpServletRequest request) throws Exception{
		//======查询待接受的货源前，判断当前用户是否有分配权力和开启接受=====
		ApiPage page = getPageInfoEncript(request);
		String memberType = getAppInfo(request).getDeviceApp();
		Map<String, Object> reqParam = page.getParaMap();
		String status = (String) reqParam.get("status");
		DataCheckUtils.assertIsNonNull("会员id不能为空", reqParam.get("memberId"));
		DataCheckUtils.assertIsNonNull("标签状态不能为空", reqParam.get("status"));
		DataCheckUtils.assertIsNonNull("会员类型不能为空", memberType);
		page.getParaMap().put("memberType", memberType);
		Map<String,Object> map = new HashMap<String,Object>();
		//1、待处理 2、已接受 3、已拒绝 4、已超时
		if("1".equals(status)){
			ApiResult<?> apiResult = ruleOnoffApiService.IsCanAcceptedGoods(reqParam);		
			if (MsgCons.C_10000.intValue() != apiResult.getCode().intValue()) {
				return apiResult;//不能获取分配的货源，直接返回错误码提示客户端。
			}			
			map.put("onOff", apiResult.getResult());//是否开启收货模式(1:关闭;2:开启)
		}	
		ApiResult<ApiPage> result = carOwnerSourceService.queryPage(page);
		result.getResult().setParaMap(map);
		return result;
	}
	
	/**
	 * 查询分配的货源详情
	 */
	@ResponseBody
	@RequestMapping("/detail")
	public Object detail(HttpServletRequest request) throws Exception {
		Map<String, Object> paramMap = getDecodeMap(request);	
		DataCheckUtils.assertIsNonNull("货源ID不能为空", paramMap.get("sourceId"));	
		DataCheckUtils.assertIsNonNull("货源状态不能为空", paramMap.get("status"));
		Object statusKey = paramMap.get("status");
		if(statusKey != null && "2".equals((String)statusKey)){
			DataCheckUtils.assertIsNonNull("预订单ID不能为空", paramMap.get("beforeId"));
		}
		paramMap.put("memberId", super.getAppInfo(request).getMemberId());
		return carOwnerSourceService.detail(paramMap);
	}
	
	/**
	 * 接受货源
	 */
	@ResponseBody
	@RequestMapping("/accept")
	public Object accept(HttpServletRequest request) throws Exception {
		Map<String, Object> paramMap = getDecodeMap(request);	
		DataCheckUtils.assertIsNonNull("货源分配ID不能为空", paramMap.get("sourceAssignHisId"));	
		DataCheckUtils.assertIsNonNull("车主会员ID不能为空", paramMap.get("updateUserId"));
		DataCheckUtils.assertIsNonNull("货源ID不能为空", paramMap.get("sourceShipperId"));
		DataCheckUtils.assertIsNonNull("货源数据版本不能为空", paramMap.get("version"));
		return carOwnerSourceService.accept(paramMap);
	}
	
}
