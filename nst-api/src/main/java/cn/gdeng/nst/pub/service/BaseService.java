package cn.gdeng.nst.pub.service;

import java.util.Map;

import cn.gdeng.nst.admin.dto.admin.AdminPageDTO;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.BizException;

/**
 * 基础service
 * @author xiaojun
 *
 */
public interface BaseService {
    /**
     * 获取总记录
     * @param paramMap
     * @return
     */
    public ApiResult<Integer> countTotal(Map<String, Object> paramMap) throws BizException;
   /**
    * 获取分页结果
    * @param paramMap
    * @return
    */
    public ApiResult<AdminPageDTO> findByConditions(Map<String, Object> paramMap) throws BizException;
    /**
     * 获取所有结果
     * @param paramMap
     * @return
     */
    public ApiResult<?> findAll(Map<String, Object> paramMap) throws BizException;
}
