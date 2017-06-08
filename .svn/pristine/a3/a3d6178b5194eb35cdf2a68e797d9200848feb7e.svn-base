
package cn.gdeng.nst.web.controller.member;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gdeng.nst.api.server.member.MemberCarApiService;
import cn.gdeng.nst.api.server.member.MemberInfoApiService;
import cn.gdeng.nst.api.server.member.MemberLineApiService;
import cn.gdeng.nst.api.server.member.MemberLogisticDriverApiService;
import cn.gdeng.nst.api.vo.member.CarOwnersVo;
import cn.gdeng.nst.enums.MsgCons;
import cn.gdeng.nst.util.web.api.ApiPage;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.web.controller.base.BaseController;
import cn.gdeng.nst.web.util.DataCheckUtils;

import com.alibaba.dubbo.config.annotation.Reference;

/**
 * 物流公司->个人中心->司机管理
 * @author yangjj
 */
@Controller
@RequestMapping("v1/memberLogisticApi")
public class MemberLogisticApiController extends BaseController{
	
	@Reference
	MemberInfoApiService memberInfoApiService;
	@Reference
	MemberCarApiService memberCarApiService;
	@Reference
	MemberLineApiService memberLineApiService;
	@Reference
	MemberLogisticDriverApiService memberLogisticDriverApiService;

	/**
	 * 司机管理
	 * 获取车主列表
	 * 
	 * @param
	 * logisticMemberId 物流公司会员ID
	 * startRow 起始页数
	 * endRow 条数
	 */
	@ResponseBody
    @RequestMapping("/carOwners/list")
	private Object list(HttpServletRequest request) throws Exception {
		ApiPage apiPage = getPageInfoEncript(request);
		return memberLogisticDriverApiService.queryPage(apiPage);
	}
	
	/**
	 * 司机管理
	 * 添加车主
	 * 
	 * @param
	 * mobile 手机号
	 * logisticMemberId 物流公司会员ID
	 */
	@ResponseBody
    @RequestMapping("/carOwners/save")
	private Object save(HttpServletRequest request) throws Exception {
		Map<String,Object> paramMap = getDecodeMap(request);
		Map<String,Object> resultMap = memberInfoApiService.checkMemberOrCar(paramMap).getResult();
		DataCheckUtils.assertIsNonNull(MsgCons.C_21023, MsgCons.M_21023,resultMap==null ? null : resultMap.get("mobile"));//校验车主是否在平台注册
		DataCheckUtils.assertIsNonNull(MsgCons.C_21024, MsgCons.M_21024,resultMap==null ? null : resultMap.get("carNumber"));//校验车主是否添加车辆
		int total = memberLogisticDriverApiService.checkDriverExist(paramMap).getResult();
		DataCheckUtils.assertIsNonNull(MsgCons.C_21026, MsgCons.M_21026,total > 0 ? null : total);//校验车主是否已添加过
		
		paramMap.put("driverMemberId", resultMap.get("memberId"));
		return memberLogisticDriverApiService.save(paramMap);
	}
	
	/**
	 * 司机管理
	 * 删除车主
	 * 
	 * @param
	 * logisticMemberId 物流公司会员ID
	 * id member_logistic_driver表主键ID，从列表获取
	 */
	@ResponseBody
    @RequestMapping("/carOwners/del")
	private Object del(HttpServletRequest request) throws Exception {
		Map<String,Object> paramMap = getDecodeMap(request);
		Integer total = memberLogisticDriverApiService.checkDriverOrder(paramMap).getResult();
		DataCheckUtils.assertIsNonNull(MsgCons.C_20002, MsgCons.M_20002, paramMap.get("id") == null || StringUtils.isEmpty((String)paramMap.get("id")) ? null : paramMap.get("id"));
		DataCheckUtils.assertIsNonNull(MsgCons.C_21025, MsgCons.M_21025, total > 0 ? null : total);//校验车主是否有未完成的订单
		return memberLogisticDriverApiService.update(paramMap);
	}
	
	/**
	 * 司机管理
	 * 查看车主详情
	 * 
	 * memberId 车主会员ID 从列表查询接口回参driverMemberId获取
	 */
	@ResponseBody
    @RequestMapping("/carOwners/details")
	private Object details(HttpServletRequest request) throws Exception {
		 CarOwnersVo vo = new CarOwnersVo();
		 ApiPage apiPage = getPageInfoEncript(request);
		 Map<String,Object> carMemberMap = memberInfoApiService.findCarMember(apiPage.getParaMap()).getResult();
		 List<?> carList = memberCarApiService.queryPage(apiPage).getResult().getRecordList();
		 List<?> lineList = memberLineApiService.queryPage(apiPage).getResult().getRecordList();
		 if(carMemberMap != null){
			 vo.setUserName((String) carMemberMap.get("userName"));
			 vo.setIconUrl((String) carMemberMap.get("iconUrl"));
			 vo.setMobile((String)carMemberMap.get("mobile"));
			 vo.setCerPersonalStatus((Integer) carMemberMap.get("cerPersonalStatus"));
			 vo.setDriverOrderCount((Long) carMemberMap.get("driverOrderCount"));
		 }
		 vo.setCarList(carList);
		 vo.setLineList(lineList);
		 ApiResult<CarOwnersVo> apiResult = new ApiResult<CarOwnersVo>(vo,MsgCons.C_10000,MsgCons.M_10000);
		 return apiResult;
	}
	
	/**
	 * 分给我的货->指派司机
	 * 司机列表（显示有车辆及无车辆的司机）
	 * 
	 * @param
	 * logisticMemberId 物流公司会员ID
     * startRow 起始页数
	 * endRow 条数
	 */
	@ResponseBody
    @RequestMapping("/carOwners/reassignList")
	private Object reassignList(HttpServletRequest request) throws Exception {
		ApiPage apiPage = getPageInfoEncript(request);
		return memberLogisticDriverApiService.queryPageByCar(apiPage);
	}
	
	/**
	 * 分给我的货->指派司机
	 * 检查司机车辆是否删除
	 * 
	 * @param
	 * memberId 车主会员ID 从分给我的货->指派司机列表获取
	 */
	@ResponseBody
    @RequestMapping("/carOwners/checkCar")
	private Object checkCar(HttpServletRequest request) throws Exception {
		Map<String,Object> paramMap = getDecodeMap(request);
		return memberCarApiService.getTotal(paramMap);
	}

}
