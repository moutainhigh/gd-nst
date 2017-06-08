package cn.gdeng.nst.web.controller.source;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.gdeng.nst.api.server.source.CarOwnerSourceService;
import cn.gdeng.nst.api.server.source.GoodsAssignmentService;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.BizException;
import cn.gdeng.nst.web.controller.base.BaseController;
import cn.gdeng.nst.web.util.DataCheckUtils;


/**货源分配更新控制类（包括增删改操作）
 * @author wjguo
 * datetime 2016年8月4日 上午10:31:20  
 *
 */
@Controller
@RequestMapping("v1/goodsAsiUpdate")
public class GoodsAssignmentUpdateController extends BaseController {
	/**
	 * 货源分配服务类
	 */
	@Reference
	private GoodsAssignmentService goodsAssignmentService;
	
	@Reference
	private CarOwnerSourceService carOwnerSourceService;

	
	/**接受分配的货源
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("acceptAssigned")
	@ResponseBody
	public ApiResult<?> acceptAssigned(HttpServletRequest request) throws Exception {
		Map<String, String> reqParamMap = getDecodeMapStr(request);
		//转换为Object的泛型
		Map<String, Object> serParamMap = new HashMap<String, Object>(reqParamMap);
		checkDataBeforeAcceptOrRejectAssigned(serParamMap);
		
		//验证货源是否超时 是否关闭
		serParamMap.put("sourceId", serParamMap.get("sourceShipperId"));
		serParamMap.put("assignMemberId", serParamMap.get("updateUserId"));
		carOwnerSourceService.checkSourceAssignHis(serParamMap);
		
		ApiResult<Map<String, Object>> apiResult = goodsAssignmentService.acceptAssigned(serParamMap);
		
		logger.info("物流公司【"+ serParamMap.get("updateUserId") + "】接受分配的货源【" + serParamMap.get("sourceShipperId") + "】！");
		return apiResult;
	}
	
	/**接受平台配送的货源（农批商  农商友那边下的订单）
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("acceptPlatformDistribution")
	@ResponseBody
	public ApiResult<?> acceptPlatformDistribution(HttpServletRequest request) throws Exception {
		Map<String, String> reqParamMap = getDecodeMapStr(request);
		//step1 转换为Object的泛型
		Map<String, Object> serParamMap = new HashMap<String, Object>(reqParamMap);
		checkDataBeforeAcceptOrRejectAssigned(serParamMap);
		DataCheckUtils.assertIsNonNull("指派司机的ID不能为空", serParamMap.get("driverMemberId"));
		
		//step2 验证货源是否超时 是否关闭
		serParamMap.put("sourceId", serParamMap.get("sourceShipperId"));
		serParamMap.put("assignMemberId", serParamMap.get("updateUserId"));
		carOwnerSourceService.checkSourceAssignHis(serParamMap);
		
		ApiResult<Map<String, Object>> apiResult = goodsAssignmentService.acceptPlatformDistribution(serParamMap);
		logger.info("物流公司ID:{},接受平台配送的货源ID:{}",new Object[]{serParamMap.get("updateUserId"),serParamMap.get("sourceShipperId")});
		return apiResult;
	}
	
	/**接受或拒接分配的货源之前的数据检测。
	 * @param paraMap
	 * @param api
	 * @throws BizException  如果校验不通过，抛出此异常。
	 */
	private void checkDataBeforeAcceptOrRejectAssigned(Map<String, Object> paraMap) throws BizException {
		DataCheckUtils.assertIsNonNull("货源分配的id不能为空", paraMap.get("sourceAssignHisId"));
		DataCheckUtils.assertIsNonNull("修改人员id不能为空", paraMap.get("updateUserId"));
		DataCheckUtils.assertIsNonNull("货源id不能为空", paraMap.get("sourceShipperId"));
		DataCheckUtils.assertIsNonNull("货源数据版本号不能为空", paraMap.get("version"));
	}
	
	
	/**拒绝分配的货源
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("rejectAssigned")
	@ResponseBody
	public ApiResult<?> rejectAssigned(HttpServletRequest request) throws Exception {
		Map<String, String> reqParamMap = getDecodeMapStr(request);
		//转换为Object的泛型
		Map<String, Object> serParamMap = new HashMap<String, Object>(reqParamMap);
		checkDataBeforeAcceptOrRejectAssigned(serParamMap);
		serParamMap.put("sourceId", serParamMap.get("sourceShipperId"));
		serParamMap.put("assignMemberId", serParamMap.get("updateUserId"));
		carOwnerSourceService.checkSourceAssignHis(serParamMap);
		ApiResult<Map<String, Object>> apiResult = goodsAssignmentService.rejectAssigned(serParamMap);
		
		logger.info("物流公司【"+ serParamMap.get("updateUserId") + "】拒绝分配的货源【" + serParamMap.get("sourceShipperId") + "】！");
		return apiResult;
	}
	/**
	 * 分配货源查看
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("assignGoodsView")
	@ResponseBody
	public Object assignGoodsView(HttpServletRequest request) throws Exception {
		Map<String, Object> paramMap=getDecodeMap(request);
		// 检查参数
		checkParam(paramMap);
		return goodsAssignmentService.assignGoodsView(paramMap);
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
