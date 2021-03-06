package cn.gdeng.nst.web.controller.source;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.gdeng.nst.api.server.member.RuleOnoffApiService;
import cn.gdeng.nst.api.server.source.GoodsAssignmentService;
import cn.gdeng.nst.enums.MsgCons;
import cn.gdeng.nst.util.web.api.ApiPage;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.AppVersionUtil;
import cn.gdeng.nst.util.web.api.BizException;
import cn.gdeng.nst.web.controller.base.BaseController;
import cn.gdeng.nst.web.util.DataCheckUtils;


/**货源分配查询控制类
 * @author wjguo
 * datetime 2016年8月4日 上午10:31:20  
 *
 */
@Controller
@RequestMapping("v1/goodsAsiQuery")
public class GoodsAssignmentQueryController extends BaseController {
	/**
	 * 货源分配服务类
	 */
	@Reference
	private GoodsAssignmentService goodsAssignmentService;
	/**
	 * 开启关闭货源分配服务类
	 */
	@Reference
	private RuleOnoffApiService ruleOnoffApiService;

	
	/**查询分配给我待接受的货源(老接口兼容(20161130以前版本))
	 * 以后使用app Version做兼容判断
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("queryMyUnconfirmed")
	@ResponseBody
	public ApiResult<?> queryMyUnconfirmed(HttpServletRequest request) throws Exception {
		//======查询待接受的货源前，判断当前用户是否有分配权力和开启接受=====
		Map<String, String> reqParam = getDecodeMapStr(request);
		DataCheckUtils.assertIsNonNull("物流公司的会员id不能为空", reqParam.get("assignMemberId"));
		
		Map<String, Object> queryParam = new HashMap<String, Object>(1);
		queryParam.put("memberId", reqParam.get("assignMemberId"));
		ApiResult<?> apiResult = ruleOnoffApiService.IsCanAcceptedGoods(queryParam);
		//不能获取分配的货源，直接返回错误码提示客户端。
		if (MsgCons.C_10000.intValue() != apiResult.getCode().intValue()) {
			return apiResult;
		}
		//接口版本兼容 以后使用app Version做兼容判断
		if (1 == ((Integer)apiResult.getResult()).intValue()) {
			return new ApiResult<>().withError(MsgCons.C_22101, MsgCons.M_22101);
		}
		//======查询待接受货源======
		List<Byte> statusList = new ArrayList<Byte>(1);
		//状态为1的代表待接受。
		statusList.add((byte) 1);
		
		ApiResult<ApiPage> result = queryMyAssginmentGoods(statusList, true, request,false);
		Map<String,Object> map = new HashMap<String,Object>();
		//是否开启收货模式(1:关闭;2:开启)
		map.put("onOff", apiResult.getResult());
		result.getResult().setParaMap(map);
		return result;
	}
	
	/**查询分配给我待接受的货源（新接口20161130）
	 * 以后使用app Version做兼容判断
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("queryMyUnconfirmedV2")
	@ResponseBody
	public ApiResult<?> queryMyUnconfirmedV2(HttpServletRequest request) throws Exception {
		//======查询待接受的货源前，判断当前用户是否有分配权力和开启接受=====
		Map<String, String> reqParam = getDecodeMapStr(request);
		DataCheckUtils.assertIsNonNull("物流公司的会员id不能为空", reqParam.get("assignMemberId"));
		
		Map<String, Object> queryParam = new HashMap<String, Object>(1);
		queryParam.put("memberId", reqParam.get("assignMemberId"));
		ApiResult<?> apiResult = ruleOnoffApiService.IsCanAcceptedGoods(queryParam);
		//不能获取分配的货源，直接返回错误码提示客户端。
		if (MsgCons.C_10000.intValue() != apiResult.getCode().intValue()) {
			return apiResult;
		}
		
		//======查询待接受货源======
		List<Byte> statusList = new ArrayList<Byte>(1);
		//状态为1的代表待接受。
		statusList.add((byte) 1);
		
		ApiResult<ApiPage> result = queryMyAssginmentGoods(statusList, true, request,false);
		Map<String,Object> map = new HashMap<String,Object>();
		//是否开启收货模式(1:关闭;2:开启)
		map.put("onOff", apiResult.getResult());
		result.getResult().setParaMap(map);
		return result;
	}
	
	
	/**查询分配给我已接受的货源，即对于物流公司来说是 已发布。
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("queryMyAccepted")
	@ResponseBody
	public ApiResult<?> queryMyAccepted(HttpServletRequest request) throws Exception {
		List<Byte> statusList = new ArrayList<Byte>(1);
		//状态为2的代表已接受。
		statusList.add((byte) 2);
		return queryMyAssginmentGoods(statusList, true, request,false);
	}
	
	
	/**查询分配给我已拒绝的货源。
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("queryMyRejected")
	@ResponseBody
	public ApiResult<?> queryMyRejected(HttpServletRequest request) throws Exception {
		List<Byte> statusList = new ArrayList<Byte>(1);
		//状态为3的代表已拒绝。
		statusList.add((byte) 3);
		return queryMyAssginmentGoods(statusList, true, request,false);
	}
	
	/**查询分配给我已失效的货源。
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("queryMyInvalid")
	@ResponseBody
	public ApiResult<?> queryMyInvalid(HttpServletRequest request) throws Exception {
		List<Byte> statusList = new ArrayList<Byte>(1);
		//状态为4的代表已过期失效。
		statusList.add((byte) 4);
		return queryMyAssginmentGoods(statusList, true, request,false);
	}
	
	/**查询分配给我的全部货源。
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("queryMyAll")
	@ResponseBody
	public ApiResult<?> queryMyAll(HttpServletRequest request) throws Exception {
		return queryMyAssginmentGoods(null, false, request,false);
	}
	
	/** 查询分配给我的货源
	 * @param statusList 分配状态集合
	 * @param excludeOvderdue 是否排除过期货源
	 * @param excludeCance 是否显示已取消的货源     true 显示  false  不显示
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private ApiResult<ApiPage> queryMyAssginmentGoods(List<Byte> statusList, Boolean excludeOvderdue, HttpServletRequest request,Boolean excludeCance) throws Exception {
		//解密参数并转成ApiPage对象
		ApiPage page = getPageInfoEncript(request);
		//数据校验
		checkDataBeforeQueryMyAssginmentGoods(page.getParaMap());
		//指定货源分配状态
		page.getParaMap().put("statusList", statusList);
		//是否排除过期货源
		page.getParaMap().put("excludeOvderdue", excludeOvderdue);
		//是否显示已取消的货源   
		page.getParaMap().put("excludeCance", excludeCance);
		//是否平台配送货源  ps:兼容IOS老版本
		boolean result=AppVersionUtil.isPlatform(this.getAppInfo(request));
		page.getParaMap().put("isPlatform", result);
		return goodsAssignmentService.queryMyAssginmentGoodsByPage(page);
	}
	
	
	/**查询分配给我的货源之前的数据检测。
	 * @param paraMap
	 * @param api
	 * @throws BizException  如果校验不通过，抛出此异常。
	 */
	private void checkDataBeforeQueryMyAssginmentGoods(Map<String, Object> paraMap) throws BizException {
		DataCheckUtils.assertIsNonNull("物流公司的会员id不能为空", paraMap.get("assignMemberId"));
	}
	
	/**查询分配的货源详情
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("queryAssiGoodsDetail")
	@ResponseBody
	public ApiResult<?> queryAssiGoodsDetail(HttpServletRequest request) throws Exception {
		Map<String, Object> paramMap = getDecodeMap(request);
		checkDataBeforeQueryAssiGoodsDetail(paramMap);
		return goodsAssignmentService.queryAssginmentGoodsDetail(paramMap);
	}
	
	/**查询分配的货源详情之前的数据检测。
	 * @param paraMap
	 * @param api
	 * @throws BizException  如果校验不通过，抛出此异常。
	 */
	private void checkDataBeforeQueryAssiGoodsDetail(Map<String, Object> paraMap) throws BizException {
		DataCheckUtils.assertIsNonNull("货源分配id不能为空", paraMap.get("id"));
	}
	
	/**查询分配给我已关闭的货源。（包括：已失效，已过期，已取消，已拒绝的）
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("queryAssiGoodsClose")
	@ResponseBody
	public ApiResult<?> queryAssiGoodsClose(HttpServletRequest request) throws Exception {
		List<Byte> statusList = new ArrayList<Byte>(1);
		//状态为3的代表已拒绝。
		statusList.add((byte) 3);
		//状态为4的代表已过期。
		statusList.add((byte) 4);
		return queryMyAssginmentGoods(statusList, false, request,true);
	}
	/**
	 * 查询分配货是否被查看
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("queryAssiGoodsIsView")
	@ResponseBody
	public Object queryAssiGoodsIsView(HttpServletRequest request) throws Exception {
		Map<String, Object> paramMap=getDecodeMap(request);
		// 检查参数
		checkParam(paramMap);
		return goodsAssignmentService.queryAssiGoodsIsView(paramMap);
	}
	/**
	 * 检查参数
	 * @param paramMap
	 * @throws BizException
	 */
	private void checkParam(Map<String, Object> paramMap) throws BizException {
		DataCheckUtils.assertIsNonNull("会员id不能为空", paramMap.get("memberId"));
	}
	
}
