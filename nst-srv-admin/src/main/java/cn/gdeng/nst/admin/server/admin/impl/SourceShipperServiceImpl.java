package cn.gdeng.nst.admin.server.admin.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import cn.gdeng.nst.admin.dto.admin.AdminOrderBeforeDTO;
import cn.gdeng.nst.admin.dto.admin.AdminPageDTO;
import cn.gdeng.nst.admin.dto.admin.AdminSourceShipperDTO;
import cn.gdeng.nst.admin.service.admin.SourceShipperService;
import cn.gdeng.nst.dao.BaseDao;
import cn.gdeng.nst.enums.IsDeletedEnum;
import cn.gdeng.nst.enums.SourceTypeEnum;
import cn.gdeng.nst.util.web.api.ApiResult;

import com.alibaba.dubbo.config.annotation.Service;
import com.gudeng.framework.dba.transaction.annotation.Transactional;

@Service
public class SourceShipperServiceImpl implements SourceShipperService{

	@Autowired
	private BaseDao<?> baseDao;

	@Override
	public ApiResult<AdminPageDTO> queryPage(Map<String, Object> paramMap) {
		int total = baseDao.queryForObject("SourceShipper.countTotal", paramMap, Integer.class);
		List<AdminSourceShipperDTO> list = null;
		if(total > 0){
			list = baseDao.queryForList("SourceShipper.queryListByPage", paramMap, AdminSourceShipperDTO.class);
		}else{
			list = Collections.emptyList();
		}
		AdminPageDTO page = new AdminPageDTO(total, list);
		return new ApiResult<AdminPageDTO>().setResult(page);
	}

	@Override
	public ApiResult<AdminSourceShipperDTO> getById(Integer id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		AdminSourceShipperDTO sourceShipperDTO = baseDao.queryForObject("SourceShipper.getById", paramMap, AdminSourceShipperDTO.class);
		return new ApiResult<AdminSourceShipperDTO>().setResult(sourceShipperDTO);
	}

	@Override
	@Transactional
	public ApiResult<Integer> update(AdminSourceShipperDTO sourceShipperDTO) {
		Integer sCityId = sourceShipperDTO.getSCityId();
		Integer eCityId = sourceShipperDTO.getECityId();
		if(sCityId != null && sCityId.equals(eCityId)){
			sourceShipperDTO.setSourceType(SourceTypeEnum.CITY_WIDE.getCode());
		}else{
			sourceShipperDTO.setSourceType(SourceTypeEnum.TRUNK.getCode());
		}
		int result = baseDao.execute("SourceShipper.update", sourceShipperDTO);
		
		// 修改order_before表shipperName、shipperMobile
		AdminOrderBeforeDTO orderBeforeDTO = new AdminOrderBeforeDTO();
		orderBeforeDTO.setSourceId(sourceShipperDTO.getId());
		orderBeforeDTO.setShipperName(sourceShipperDTO.getShipperName());
		orderBeforeDTO.setShipperMobile(sourceShipperDTO.getShipperMobile());
		orderBeforeDTO.setUpdateUserId(sourceShipperDTO.getUpdateUserId());
		baseDao.execute("OrderBefore.updateShipper", orderBeforeDTO);
		
		return new ApiResult<Integer>().setResult(result);
	}

	@Override
	@Transactional
	public ApiResult<int[]> batchDelete(String[] ids, String loginUserId) {
		@SuppressWarnings("unchecked")
		Map<String, Object>[] batchValues = new HashMap[ids.length];
		for(int i = 0, len = batchValues.length; i < len; i++){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", Integer.parseInt(ids[i]));
			map.put("updateUserId", loginUserId);
			map.put("isDeleted", IsDeletedEnum.DELETED.getCode());
			batchValues[i] = map;
		}
		int[] result = baseDao.batchUpdate("SourceShipper.updateIsDeleted", batchValues);
		return new ApiResult<int[]>().setResult(result);
	}

	@Override
	public ApiResult<Integer> countTotal(Map<String, Object> paramMap) {
		int total = baseDao.queryForObject("SourceShipper.countTotal", paramMap, Integer.class);
		return new ApiResult<Integer>().setResult(total);
	}

	@Override
	public ApiResult<List<AdminSourceShipperDTO>> queryListByPage(Map<String, Object> paramMap) {
		List<AdminSourceShipperDTO> list = baseDao.queryForList("SourceShipper.queryListByPage", paramMap, AdminSourceShipperDTO.class);
		return new ApiResult<List<AdminSourceShipperDTO>>().setResult(list);
	}
}
