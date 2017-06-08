package cn.gdeng.nst.server.member.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cn.gdeng.nst.api.dto.member.MemberCarApiDTO;
import cn.gdeng.nst.api.server.member.MemberCarApiService;
import cn.gdeng.nst.api.vo.member.MemberCarDetailVo;
import cn.gdeng.nst.api.vo.member.MemberCarPageVo;
import cn.gdeng.nst.dao.BaseDao;
import cn.gdeng.nst.entity.nst.MemberCarEntity;
import cn.gdeng.nst.enums.IsSelectEnum;
import cn.gdeng.nst.enums.MsgCons;
import cn.gdeng.nst.util.web.api.ApiPage;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.BizException;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.gudeng.framework.dba.transaction.annotation.Transactional;
/**
 * 用户车辆管理
 * @author kwang
 *
 */
@Service
public class MemberCarApiServiceImpl implements MemberCarApiService{
    
    @Resource
    private BaseDao<?> baseDao;
    /**
     * 用户车辆列表
     * @param page对象
     */
	@Override
	public ApiResult<ApiPage> queryPage(ApiPage page) throws BizException {
		ApiResult<ApiPage> apiResult=new ApiResult<>();
		List<MemberCarPageVo> pageVoList=new ArrayList<MemberCarPageVo>();
		int total=baseDao.queryForObject("MemberCarApi.getTotal",page.getParaMap(), Integer.class);
		if(total==0){
			page.setRecordList(pageVoList).setRecordCount(total);
			apiResult.setResult(page);
			return apiResult;
		}
		pageVoList=baseDao.queryForList("MemberCarApi.queryPage", page.getParaMap(),MemberCarPageVo.class);
		page.setRecordList(pageVoList).setRecordCount(total);
		apiResult.setResult(page);
		return apiResult;
	}
	/**
     * 删除车辆（逻辑删除）
     * @param  id
     * @return 影响条数
     */
	@Override
    @Transactional
	public ApiResult<Integer> deleteMemberCarById(MemberCarApiDTO memberCarApiDTO) throws BizException {
		if(null==memberCarApiDTO.getId()){
			throw new BizException(MsgCons.C_20001, MsgCons.M_20001);
		}
		//用户车辆数
		int Cartotal=baseDao.queryForObject("MemberCarApi.getTotal",memberCarApiDTO, Integer.class);
		//常跑线路数
		int Linetotal=baseDao.queryForObject("MemberLineApi.getTotal",memberCarApiDTO, Integer.class);
		if(Linetotal>0&&Cartotal==1){
			throw new BizException(MsgCons.C_22025, MsgCons.M_22025);
		}
		//关联车辆订单数
		int CarOrdertotal=baseDao.queryForObject("MemberCarApi.queryMemberCarOrderCount",memberCarApiDTO, Integer.class);
		if(CarOrdertotal>0){
			throw new BizException(MsgCons.C_22029, MsgCons.M_22029);
		}
		//车辆是承运车辆不能删除
		MemberCarDetailVo vo=baseDao.queryForObject("MemberCarApi.findMemberCarById",memberCarApiDTO, MemberCarDetailVo.class);
		if(vo!=null&& (IsSelectEnum.SELECT.getCode()==(byte)vo.getIsCarriage())){
			throw new BizException(MsgCons.C_21027, MsgCons.M_21027);
		}
		//用户车辆删除
		int re=baseDao.execute("MemberCarApi.deleteMemberCarById",memberCarApiDTO);
	    return new ApiResult<Integer>(re,MsgCons.C_10000,MsgCons.M_10000);
	}
   
	/**
	 * 添加车辆
	 * @param 用户车辆实体
	 */
	@Override
	@Transactional
	public ApiResult<Long> saveMemberCar(MemberCarEntity memberCarEntity)
			throws BizException {
		if(null==memberCarEntity.getMemberId()){
			throw new BizException(MsgCons.C_20001, MsgCons.M_20001);
		}
		if(null==memberCarEntity.getCarLength()){
			throw new BizException(MsgCons.C_24015, MsgCons.M_24015);
		}
		if(null==memberCarEntity.getCarType()){
			throw new BizException(MsgCons.C_24016, MsgCons.M_24016);
		}
		if(null==memberCarEntity.getLoad()){
			throw new BizException(MsgCons.C_24019, MsgCons.M_24019);
		}
		if(null==memberCarEntity.getCarHeadUrl()&&null==memberCarEntity.getCarRearUrl()&&null==memberCarEntity.getVehicleUrl()){
			throw new BizException(MsgCons.C_26004, MsgCons.M_26004);
		}
		//重复车牌号数
		int total= baseDao.queryForObject("MemberCarApi.queryMemberCarNumber", memberCarEntity, Integer.class);     
       if(total>0){
    	   throw new BizException(MsgCons.C_22026, MsgCons.M_22026);
       }
        memberCarEntity.setIsCarriage( IsSelectEnum.NOT_SELECT.getCode());
		long re=baseDao.persist(memberCarEntity, Long.class);
		ApiResult<Long> apiResult=	new ApiResult<Long>();
		apiResult.setResult(re);
		return apiResult;
	}
	/**
	 * 车辆详情
	 * @param 用户车辆DTO
	 */
	@Override
	public ApiResult<MemberCarDetailVo> queryMemberCarDetailById(
			MemberCarApiDTO memberCarApiDTO) throws BizException {
		if(null==memberCarApiDTO.getMemberId()){
			throw new BizException(MsgCons.C_20001, MsgCons.M_20001);
		}
		MemberCarDetailVo vo = baseDao.queryForObject("MemberCarApi.findMemberCarById", memberCarApiDTO, MemberCarDetailVo.class);     
	        ApiResult<MemberCarDetailVo> apiResult = new ApiResult<MemberCarDetailVo>(vo,MsgCons.C_10000,MsgCons.M_10000);
	        return apiResult;
	}
	/**
	 * 重复车牌号数
	 * @param 用户车辆DTO
	 */
	@Override
	public ApiResult<Integer> queryMemberCarNumberNumber(
			MemberCarApiDTO memberCarApiDTO) throws BizException {
		if(null==memberCarApiDTO.getCarNumber()){
			throw new BizException(MsgCons.C_20001, MsgCons.M_20001);
		}
		int total= baseDao.queryForObject("MemberCarApi.queryMemberCarNumberNumber", memberCarApiDTO, Integer.class);     
	        ApiResult<Integer> apiResult = new ApiResult<Integer>(total,MsgCons.C_10000,MsgCons.M_10000);
	        return apiResult;
	}
	/**
	 * 修改车辆
	 * @param 用户车辆DTO 
	 */
	@Override
	public ApiResult<Integer> updateMemberCar(MemberCarApiDTO memberCarApiDTO)
			throws BizException {
		if(null==memberCarApiDTO.getId()){
			throw new BizException(MsgCons.C_20001, MsgCons.M_20001);
		}
		if(null==memberCarApiDTO.getMemberId()){
			throw new BizException(MsgCons.C_20001, MsgCons.M_20001);
		}
		int re=baseDao.execute("MemberCarApi.updateMemberCarById",memberCarApiDTO);
		return new ApiResult<Integer>(re,MsgCons.C_10000,MsgCons.M_10000);
	}
	/**
	 * 车辆总数（暂没使用）
	 * @param map
	 */
	@Override
	public ApiResult<Integer> getTotal(Map<String, Object> map) {
		int total=baseDao.queryForObject("MemberCarApi.getTotal",map, Integer.class);		
		return new ApiResult<Integer>(total,MsgCons.C_10000,MsgCons.M_10000);
	}
	@Override
	public ApiResult<List<MemberCarPageVo>> queryMemberCarrierCarList(Map<String, Object> map) throws BizException {
		ApiResult<List<MemberCarPageVo>> apiResult=new ApiResult<>();
		//是否承运车辆   
		//map.put("isCarriage", IsSelectEnum.SELECT.getCode());
		List<MemberCarPageVo> carList=baseDao.queryForList("MemberCarApi.queryCarrierCar", map,MemberCarPageVo.class);
		if(CollectionUtils.isNotEmpty(carList)){
			apiResult.setResult(carList);
		}
		return apiResult;
	}
	@Override
	public ApiResult<Integer> updateMemberCarrierCar(Map<String, Object> map) throws BizException {
		//step1 设置车辆全部未承运
		Map<String, Object> param=new HashMap<>();
		param.put("isCarriage", IsSelectEnum.NOT_SELECT.getCode());
		param.put("memberId", map.get("memberId"));
		baseDao.execute("MemberCarApi.updateMemberCarById",param);
		//setp2  新增或修改承运车辆
		map.put("isCarriage", IsSelectEnum.SELECT.getCode());
		int result= baseDao.execute("MemberCarApi.updateMemberCarById",map);
		return new ApiResult<Integer>(result,MsgCons.C_10000,MsgCons.M_10000);
	}

}
