package cn.gdeng.nst.api.server.member;

import java.util.Map;

import cn.gdeng.nst.api.vo.member.MemberInfoVo;
import cn.gdeng.nst.entity.nst.MemberInfoEntity;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.BizException;

/**
 * 会员信息Service
 * @author yjj
 *
 */
public interface MemberInfoApiService {
   
	/**
	 * 查询会员信息
	 * @param map
	 * @return
	 * @throws BizException
	 */
    public ApiResult<MemberInfoVo> findMember(Map<String,Object> map) throws BizException;
     
	/**
	 * 更新或删除会员信息
	 * @param map
	 * @return
	 * @throws BizException
	 */
    public ApiResult<Integer> updateByMap(Map<String,Object> map) throws BizException;
     
	/**
	 * 查询会员信息By id
	 * @param map
	 * @return
	 * @throws BizException
	 */
    public ApiResult<MemberInfoEntity> getById(Integer id) throws BizException; 
    
	/**
	 * 查询车主的信息及成功接单数 
	 * @param map
	 * @return
	 * @throws BizException
	 */
    public ApiResult<Map<String,Object>> findCarMember(Map<String,Object> map) throws BizException;
    
	/**
	 * 校验用户是否在平台注册或有无车辆信息 
	 * @param map
	 * @return
	 * @throws BizException
	 */
    public ApiResult<Map<String,Object>> checkMemberOrCar(Map<String,Object> map) throws BizException;
}
