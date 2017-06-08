package cn.gdeng.nst.api.server.order;

import cn.gdeng.nst.api.dto.member.MemberSendDto;
import cn.gdeng.nst.api.dto.order.OrderDto;
import cn.gdeng.nst.entity.nst.SysRegisterUser;
import cn.gdeng.nst.util.web.api.ApiPage;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.BizException;

public interface DemoService {

	/**
	 * 缓存测试
	 */
	public ApiResult<SysRegisterUser> getRedis(OrderDto param) throws Exception;
	/**
	 * 分页
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public ApiResult<ApiPage> queryUserListByPage(ApiPage page) throws Exception;
	/**
	 * 队列
	 */
	public ApiResult<Object> mqProvider(MemberSendDto memberSendDto) throws BizException;
	
}