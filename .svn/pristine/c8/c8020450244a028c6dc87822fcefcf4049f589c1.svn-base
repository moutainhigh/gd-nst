package cn.gdeng.nst.api.server.member;


import cn.gdeng.nst.api.dto.member.MemberCountDTO;
import cn.gdeng.nst.api.vo.member.MemberCountCompanyVo;
import cn.gdeng.nst.api.vo.member.MemberCountDriverVo;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.BizException;

public interface MemberCountApiService {
	/**
     * 查物流公司统计信息
     * @param MemberCountDTO
     */
	public ApiResult<MemberCountCompanyVo> findByCompanyMemberId(MemberCountDTO dto)throws BizException;
	 /**
     * 查司机统计信息
     * @param MemberCountDTO
     */
	public ApiResult<MemberCountDriverVo> findByDriverMemberId(MemberCountDTO dto)throws BizException;
   
   
}
