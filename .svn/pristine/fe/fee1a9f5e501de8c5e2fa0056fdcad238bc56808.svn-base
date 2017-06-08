package cn.gdeng.nst.api.server.member;

import java.util.Map;

import cn.gdeng.nst.api.dto.member.MemberCerApiDTO;
import cn.gdeng.nst.api.vo.member.MemberCerVo;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.BizException;


/**
 * 身份认证Service
 * @author yjj
 *
 */
public interface MemberCerApiService{
    
	/**
	 * 根据id和用户类型查询已认证的数量
	 * @param paramMap
	 * @return
	 * @throws BizException
	 */
    public ApiResult<Integer> countByMemberId(Map<String, Object> paramMap) throws BizException;
  
    /**
     * 根据memberId查询认证信息
     * @param paramMap
     * @return
     * @throws BizException
     */
    public ApiResult<MemberCerVo> findOneByMemberId(Map<String, Object> paramMap) throws BizException;
    
    /**
     * 保存已认证的信息到认证信息表
     * @param dto
     * @return
     * @throws BizException
     */
    public ApiResult<Integer> save(MemberCerApiDTO dto) throws BizException; 
}
