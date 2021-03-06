package cn.gdeng.nst.web.controller.order;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gdeng.nst.api.dto.member.MemberSendDto;
import cn.gdeng.nst.api.dto.order.OrderDto;
import cn.gdeng.nst.api.server.member.MemberSyncService;
import cn.gdeng.nst.api.server.order.DemoService;
import cn.gdeng.nst.entity.nst.SysRegisterUser;
import cn.gdeng.nst.util.web.api.ApiPage;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.web.controller.base.BaseController;

import com.alibaba.dubbo.config.annotation.Reference;

@Controller
@RequestMapping("test")
public class DemonController extends BaseController {

	@Reference
	private DemoService demoService;
	@Reference
	private MemberSyncService memberSyncService;

	@RequestMapping("redis")
	@ResponseBody
	public Object redis(HttpServletRequest request) throws Exception {
		ApiResult<SysRegisterUser> result = new ApiResult<SysRegisterUser>();
		//解密参数并转成所需对象
		OrderDto orderDto = getDecodeDto(request,OrderDto.class);
		//解密参数并转成Map
		Map<String,Object> paraMap = getDecodeMap(request);
		logger.info(paraMap.toString());
		result = demoService.getRedis(orderDto);
		return result;
	}

	@RequestMapping("queryUserListByPage")
	@ResponseBody
	public Object queryUserListByPage(HttpServletRequest request) throws Exception {
		//解密参数并转成ApiPage对象
		ApiPage page = getPageInfoEncript(request);
		return demoService.queryUserListByPage(page);
	}
	
	@RequestMapping("mq")
	@ResponseBody
	public Object mqProvider() throws Exception {
		MemberSendDto memberSendDto = new MemberSendDto();
		memberSendDto.setMemberId(new Long(System.currentTimeMillis()).intValue());
		memberSendDto.setUpdateTime(new Date());
		memberSendDto.setMobile("137"+memberSendDto.getMemberId());
		return demoService.mqProvider(memberSendDto);
	}
	
	@RequestMapping("memberSync")
	@ResponseBody
	public Object memberSync() throws Exception {
		Map<String, Object> param2 = new HashMap<String, Object>();
		param2.put("id",224);
		param2.put("memberId",224);
		param2.put("realName","张三");
		//param2.put(SimpleParamDatabaseRouteConfig.DEFAULT_PARAMKEY, SimpleParamDatabaseRouteConfig.DB_GUDENG);
		return memberSyncService.syncByMemberId(param2);
	}
}
