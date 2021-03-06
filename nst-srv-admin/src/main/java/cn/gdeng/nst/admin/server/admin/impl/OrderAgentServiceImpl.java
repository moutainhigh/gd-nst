package cn.gdeng.nst.admin.server.admin.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import cn.gdeng.nst.admin.dto.admin.AdminOrderAgentDTO;
import cn.gdeng.nst.admin.dto.admin.AdminOrderDTO;
import cn.gdeng.nst.admin.dto.admin.AdminOrderPayDetailDTO;
import cn.gdeng.nst.admin.dto.admin.AdminPageDTO;
import cn.gdeng.nst.admin.service.admin.OrderAgentService;
import cn.gdeng.nst.dao.BaseDao;
import cn.gdeng.nst.enums.OrderTypeEnum;
import cn.gdeng.nst.enums.PayDetailOrderTypeEnum;
import cn.gdeng.nst.util.web.api.ApiResult;

import com.alibaba.dubbo.config.annotation.Service;

@Service
public class OrderAgentServiceImpl implements OrderAgentService{
	
	@Autowired
	private BaseDao<?> baseDao;
	
	@Override
	public ApiResult<AdminPageDTO> queryPage(Map<String, Object> paramMap) {
		int total = baseDao.queryForObject("OrderAgent.countTotal", paramMap, Integer.class);
		List<AdminOrderAgentDTO> list = null;
		if(total > 0){
			list = baseDao.queryForList("OrderAgent.queryListByPage", paramMap, AdminOrderAgentDTO.class);
		}else{
			list = Collections.emptyList();
		}
		AdminPageDTO page = new AdminPageDTO(total, list);
		return new ApiResult<AdminPageDTO>().setResult(page);
		
	}

	@Override
	public ApiResult<List<AdminOrderDTO>> getAdminOrderDTOBySourceId(Integer sourceId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sourceId", sourceId);
		
		List<AdminOrderDTO> orderList = baseDao.queryForList("OrderAgent.getAdminOrderDTOBySourceId", paramMap, AdminOrderDTO.class);
		if(orderList != null){
			for(AdminOrderDTO adminOrderDTO : orderList){
				adminOrderDTO.setOrderType(OrderTypeEnum.ORDER_AGENT.getCode());
			}
		}
		return new ApiResult<List<AdminOrderDTO>>().setResult(orderList);
	}

	@Override
	public ApiResult<AdminOrderAgentDTO> getById(Integer id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		AdminOrderAgentDTO orderAgentDTO = baseDao.queryForObject("OrderAgent.getById", paramMap, AdminOrderAgentDTO.class);
		
		if(orderAgentDTO != null){
			//支付信息
			Map<String, Object> payDetailMap = new HashMap<String, Object>();
			payDetailMap.put("orderNo", orderAgentDTO.getOrderNo());
			payDetailMap.put("orderType", PayDetailOrderTypeEnum.ORDER_AGNET.getCode());
			AdminOrderPayDetailDTO payDeital = baseDao.queryForObject("OrderPayDetail.queryOneByMap", payDetailMap, AdminOrderPayDetailDTO.class);
			orderAgentDTO.setOrderPayDetail(payDeital);
		}
		return new ApiResult<AdminOrderAgentDTO>().setResult(orderAgentDTO);
	}

	@Override
	public ApiResult<Integer> countTotal(Map<String, Object> paramMap) {
		int total = baseDao.queryForObject("OrderAgent.countTotal", paramMap, Integer.class);
		return new ApiResult<Integer>().setResult(total);
	}

	@Override
	public ApiResult<List<AdminOrderAgentDTO>> queryListByPage(Map<String, Object> paramMap) {
		List<AdminOrderAgentDTO> list = baseDao.queryForList("OrderAgent.queryListByPage", paramMap, AdminOrderAgentDTO.class);
		return new ApiResult<List<AdminOrderAgentDTO>>().setResult(list);
	}

}
