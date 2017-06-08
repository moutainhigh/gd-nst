package cn.gdeng.nst.web.controller.pub;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.gdeng.nst.pub.dto.MemberDeleteDTO;
import cn.gdeng.nst.pub.service.MemberDeleteService;
import cn.gdeng.nst.util.web.api.BizException;
import cn.gdeng.nst.web.controller.base.BaseController;
import cn.gdeng.nst.web.util.DataCheckUtils;

/**
 * 会员删除controller
 * 
 * @author xiaojun
 */
@Controller
@RequestMapping("memberPublic")
public class MemberDeleteController extends BaseController {

	@Reference
	private MemberDeleteService memberDeleteService;

	/**
	 * 删除
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("delete")
	public Object delete(HttpServletRequest request) throws Exception {
		MemberDeleteDTO dto = getDecodeDto(request, MemberDeleteDTO.class);
		checkParam(dto);
		return memberDeleteService.delete(dto);
	}

	/**
	 * 检查字段
	 * 
	 * @param dto
	 * @throws BizException
	 */
	private void checkParam(MemberDeleteDTO dto) throws BizException {
		DataCheckUtils.assertIsNonNull("会员id不能为空", dto.getMemberId());
		DataCheckUtils.assertIsNonNull("业务主表id不能为空", dto.getBusinessId());
		DataCheckUtils.assertIsNonNull("当前需删除app类型不能为空", dto.getAppType());
		DataCheckUtils.assertIsNonNull("业务模块类型不能为空", dto.getDeleteType());
	}
}
