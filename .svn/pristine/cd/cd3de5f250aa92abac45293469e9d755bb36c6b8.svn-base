package cn.gdeng.nst.admin.server.admin.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import cn.gdeng.nst.admin.dto.admin.AdminOrderDTO;
import cn.gdeng.nst.admin.dto.admin.AdminOrderInfoDTO;
import cn.gdeng.nst.admin.dto.admin.AdminOrderPayDetailDTO;
import cn.gdeng.nst.admin.dto.admin.AdminPageDTO;
import cn.gdeng.nst.admin.service.admin.OrderInfoService;
import cn.gdeng.nst.dao.BaseDao;
import cn.gdeng.nst.entity.nst.SourceExamineEntity;
import cn.gdeng.nst.enums.NstRuleEnum;
import cn.gdeng.nst.enums.OrderTypeEnum;
import cn.gdeng.nst.enums.PayDetailOrderTypeEnum;
import cn.gdeng.nst.util.web.api.ApiResult;

import com.alibaba.dubbo.config.annotation.Service;

@Service
public class OrderInfoServiceImpl implements OrderInfoService{
	
	@Autowired
	private BaseDao<?> baseDao;
	
	@Override
	public ApiResult<AdminPageDTO> queryPage(Map<String, Object> paramMap) {
		int total = baseDao.queryForObject("OrderInfo.countTotal", paramMap, Integer.class);
		List<AdminOrderInfoDTO> list = null;
		if(total > 0){
			list = baseDao.queryForList("OrderInfo.queryListByPage", paramMap, AdminOrderInfoDTO.class);
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
		
		// 物流公司直发不查询货运订单
		byte nstRule = baseDao.queryForObject("SourceShipper.getNstRule", paramMap, Byte.class);
		if(nstRule == NstRuleEnum.SELFLOGST.getCode().byteValue()){
			return null;
		}
		
		List<AdminOrderDTO> orderList = baseDao.queryForList("OrderInfo.getAdminOrderDTOBySourceId", paramMap, AdminOrderDTO.class);
		if(orderList != null){
			for(AdminOrderDTO adminOrderDTO : orderList){
				adminOrderDTO.setOrderType(OrderTypeEnum.ORDER_INFO.getCode());
				
				//6+1货源，不显示付款方，收款方信息
				int platform = adminOrderDTO.getPlatform() == null ? 0 : adminOrderDTO.getPlatform();
				if (platform == 1) {
					adminOrderDTO.setBuyerMobile(null);
					adminOrderDTO.setBuyerName(null);
					adminOrderDTO.setSellerMobile(null);
					adminOrderDTO.setSellerName(null);
				}
			}
		}
		return new ApiResult<List<AdminOrderDTO>>().setResult(orderList);
	}

	@Override
	public ApiResult<AdminOrderInfoDTO> getById(Integer id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		AdminOrderInfoDTO orderInfoDTO = baseDao.queryForObject("OrderInfo.getById", paramMap, AdminOrderInfoDTO.class);
		
		if(orderInfoDTO != null){
			//支付信息
			paramMap.clear();
			paramMap.put("orderNo", orderInfoDTO.getOrderNo());
			paramMap.put("orderType", PayDetailOrderTypeEnum.ORDER_INFO.getCode());
			AdminOrderPayDetailDTO payDeital = baseDao.queryForObject("OrderPayDetail.queryOneByMap", paramMap, AdminOrderPayDetailDTO.class);
			orderInfoDTO.setOrderPayDetail(payDeital);
			
			//验货信息
			paramMap.clear();
			paramMap.put("orderId", orderInfoDTO.getId());
			SourceExamineEntity examineEntity = baseDao.queryForObject("SourceExamine.queryByOrderId", paramMap, SourceExamineEntity.class);
			orderInfoDTO.setSourceExamineEntity(examineEntity);
		}
		return new ApiResult<AdminOrderInfoDTO>().setResult(orderInfoDTO);
	}

	@Override
	public ApiResult<List<AdminOrderInfoDTO>> queryListByPage(Map<String, Object> paramMap) {
		List<AdminOrderInfoDTO> list = baseDao.queryForList("OrderInfo.queryListByPage", paramMap, AdminOrderInfoDTO.class);
		return new ApiResult<List<AdminOrderInfoDTO>>().setResult(list);
	}

	@Override
	public ApiResult<Integer> countTotal(Map<String, Object> paramMap) {
		int total = baseDao.queryForObject("OrderInfo.countTotal", paramMap, Integer.class);
		return new ApiResult<Integer>().setResult(total);
	}

}
