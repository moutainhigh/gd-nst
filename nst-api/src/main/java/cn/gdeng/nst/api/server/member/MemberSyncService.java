package cn.gdeng.nst.api.server.member;

import java.util.Map;

import cn.gdeng.nst.util.web.api.ApiResult;

/**
 * 会员同步
 * 从gudeng member_baseInfo同步至gd-nst member_info
 * 通过MQ或者数据库同步
 * @author znf 20160806
 *
 */
public interface MemberSyncService {
	/**
	 * 无用接口
	 * 根据会员ID同步会员信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ApiResult<Object> syncByMemberId(Map<String, Object> param2) throws Exception;
}
