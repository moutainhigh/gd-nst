package cn.gdeng.nst.server.source.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Service;

import cn.gdeng.nst.api.dto.source.FindCarLineDto;
import cn.gdeng.nst.api.dto.source.GoodsSourceBasicDto;
import cn.gdeng.nst.api.server.source.SearchGoodsSourceService;
import cn.gdeng.nst.dao.BaseDao;
import cn.gdeng.nst.entity.nst.MemberInfoEntity;
import cn.gdeng.nst.enums.MemberCerStatusEnum;
import cn.gdeng.nst.enums.MsgCons;
import cn.gdeng.nst.enums.selfdefine.VisitSourceEnum;
import cn.gdeng.nst.util.web.api.ApiPage;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.BizException;
import cn.gdeng.nst.util.web.api.ParamProcessUtil;

/**
 * @author DJB
 * @version 创建时间：2017年2月15日 下午3:54:13 搜索货源服务类实现
 */

@Service
public class SearchGoodsSourceServiceImpl implements SearchGoodsSourceService {
	@Resource
	private BaseDao<?> baseDao;

	/**
	 * 
	 * @Description: 推荐货源
	 * @param page
	 * @return
	 * @throws BizException
	 * 
	 * 参数说明  visitSource：2 车主  3物流公司
	 *
	 */
	@Override
	public ApiResult<ApiPage> queryRecommendGoods(ApiPage page) throws BizException {
		Map<String, Object> param = page.getParaMap();
		ApiResult<ApiPage> result = new ApiResult<ApiPage>();
		// 查询业务类型 param :memberId
		MemberInfoEntity memberInfoEntity = baseDao.queryForObject("FindCarLine.queryMemberById", param,
				MemberInfoEntity.class);
		if (memberInfoEntity == null) {
			throw new BizException(MsgCons.C_21018, MsgCons.M_21018);
		}
		
		if (param.get("visitSource").equals(VisitSourceEnum.SOURCE_DRIVER.getCode())) {
			// 车主
			param = findRecommendGoodsByLine(param);
		} else if (param.get("visitSource").equals(VisitSourceEnum.SOURCE_COMPANY.getCode()) 
				&& !memberInfoEntity.getCerPersonalStatus().equals(MemberCerStatusEnum.PASS_CER.getCode().intValue())) {
			throw new BizException(MsgCons.C_21017, MsgCons.M_21017);
		}
		
		//查询最近五天的
		param.put("startTime", ParamProcessUtil.getNewDateAdd(-6));
		param.put("sourceType", memberInfoEntity.getServiceType());
		int total = baseDao.queryForObject("SearchGoodsSource.countRecommendGoodsSource", param, Integer.class);
		if (total > 0) {
			List<GoodsSourceBasicDto> goodsSourceBasicDtos = baseDao.queryForList("SearchGoodsSource.queryRecommendGoodsSource", param, GoodsSourceBasicDto.class);
			goodsSourceBasicDtos = listDateToChinese(goodsSourceBasicDtos);
			page.setRecordList(goodsSourceBasicDtos);
		}
		page.setRecordCount(total);
		return result.setResult(page);

	}

	/**
	 * 
	 * @Description: 分页查找货源
	 * @param page
	 * @return
	 * @throws BizException
	 *
	 */
	@Override
	public ApiResult<ApiPage> queryGoodsPage(ApiPage page) throws BizException {
		Map<String, Object> param = page.getParaMap();
		//查询最近五天的
		param.put("startTime", ParamProcessUtil.getNewDateAdd(-6));
		// param.put("startTime","2016-08-11 11:12:13");
		ApiResult<ApiPage> result = new ApiResult<ApiPage>();
		// 车长和车型 不限时需要去掉参数
		if (param.get("carType") != null && param.get("carType").equals("-1")) {
			param.remove("carType");
		}
		param = removeSuffix(param, "carLength", "米");
		if (param.get("carLength") != null && param.get("carLength").equals("-1")) {
			param.remove("carLength");
		}

		page.setParaMap(param);
		if (param.get("visitSource").equals(VisitSourceEnum.SOURCE_DRIVER.getCode())) {
			// 车主查询 总数是假的 只查一次
			page = queryGoodsPageByDriver(page);
		} else if (param.get("visitSource").equals(VisitSourceEnum.SOURCE_COMPANY.getCode())) {
			MemberInfoEntity memberInfoEntity = baseDao.queryForObject("FindCarLine.queryMemberById", param,MemberInfoEntity.class);
			if (memberInfoEntity == null || 
					!memberInfoEntity.getCerPersonalStatus().equals(MemberCerStatusEnum.PASS_CER.getCode().intValue())) {
				//认证不通过
				throw new BizException(MsgCons.C_21017, MsgCons.M_21017);
			}
			// 物流公司查询 查询两次
			page = queryGoodsPageByCompany(page);
		} else {
			// 不符合要求，退出查询
			throw new BizException(MsgCons.C_20001, MsgCons.M_20001);
		}
		return result.setResult(page);
	}

	/**
	 * 
	 * @Description: 车主条件查询货源
	 * @param page
	 * @return
	 *
	 */
	private ApiPage queryGoodsPageByDriver(ApiPage page) {
		Map<String, Object> param = page.getParaMap();
		Integer endRow = (Integer) param.get("endRow");
		Integer startRow = (Integer) param.get("startRow");
		param.put("endRow", endRow + 1);
		List<GoodsSourceBasicDto> goodsSourceBasicDtos = baseDao.queryForList("FindCarLine.queryGoodsPage", param,
				GoodsSourceBasicDto.class);
		if (goodsSourceBasicDtos != null && goodsSourceBasicDtos.size() > 0) {
			goodsSourceBasicDtos = listDateToChinese(goodsSourceBasicDtos);
			int size = goodsSourceBasicDtos.size();
			if (size > endRow) {
				page.setRecordCount(startRow + endRow + 1);
				// page.setHasNextPage(true);
				goodsSourceBasicDtos.remove(size - 1);
				page.setRecordList(goodsSourceBasicDtos);
			} else {
				page.setRecordCount(goodsSourceBasicDtos.size() + startRow);
				page.setRecordList(goodsSourceBasicDtos);
			}

		} else {
			page.setRecordCount(startRow);
		}
		return page;
	}

	/**
	 * 
	 * @Description: 非车主条件查询货源
	 * @param page
	 * @return
	 *
	 */
	private ApiPage queryGoodsPageByCompany(ApiPage page) {
		Map<String, Object> param = page.getParaMap();
		int total = baseDao.queryForObject("FindCarLine.countGoodsPage", param, Integer.class);
		if (total > 0) {
			List<GoodsSourceBasicDto> goodsSourceBasicDtos = baseDao.queryForList("FindCarLine.queryGoodsPage", param,
					GoodsSourceBasicDto.class);
			goodsSourceBasicDtos = listDateToChinese(goodsSourceBasicDtos);
			page.setRecordList(goodsSourceBasicDtos);
		}
		page.setRecordCount(total);
		return page;

	}

	/**
	 * 
	 * @Description: 根据常跑路线推荐 （本类私有方法）
	 * @param page
	 *            memberId(必须) sourceType(非必须) :startRow,:endRow
	 * @return
	 *
	 */
	private Map<String, Object> findRecommendGoodsByLine(Map<String, Object> param) {

		// 查询司机常跑路线 param :memberId
		List<FindCarLineDto> findCarLineDtos = baseDao.queryForList("FindCarLine.queryCarLine", param,
				FindCarLineDto.class);
		List<List<String>> listMap = new ArrayList<List<String>>();
		if (findCarLineDtos != null && !findCarLineDtos.isEmpty()) {
			for (FindCarLineDto findCarLineDto : findCarLineDtos) {
				List<String> list = new ArrayList<String>(), list2 = new ArrayList<String>();
				if (findCarLineDto.getS_areaId() != null) {
					list.add("s_areaId=" + findCarLineDto.getS_areaId());
					list2.add("e_areaId=" + findCarLineDto.getS_areaId());
				}
				if (findCarLineDto.getS_cityId() != null) {
					list.add("s_cityId=" + findCarLineDto.getS_cityId());
					list2.add("e_cityId=" + findCarLineDto.getS_cityId());
				}
				if (findCarLineDto.getS_provinceId() != null) {
					list.add("s_provinceId=" + findCarLineDto.getS_provinceId());
					list2.add("e_provinceId=" + findCarLineDto.getS_provinceId());
				}
				if (findCarLineDto.getE_areaId() != null) {
					list.add("e_areaId=" + findCarLineDto.getE_areaId());
					list2.add("s_areaId=" + findCarLineDto.getE_areaId());
				}
				if (findCarLineDto.getE_cityId() != null) {
					list.add("e_cityId=" + findCarLineDto.getE_cityId());
					list2.add("s_cityId=" + findCarLineDto.getE_cityId());
				}
				if (findCarLineDto.getE_provinceId() != null) {
					list.add("e_provinceId=" + findCarLineDto.getE_provinceId());
					list2.add("s_provinceId=" + findCarLineDto.getE_provinceId());
				}
				listMap.add(list);
				listMap.add(list2);
			}
		}
		param.put("cityList", listMap);
		return param;

	}

	/**
	 * 
	 * @Description: 时间处理
	 * @param list
	 * @return
	 *
	 */
	private List<GoodsSourceBasicDto> listDateToChinese(List<GoodsSourceBasicDto> list) {

		List<GoodsSourceBasicDto> goodsSourceBasicDtos = new ArrayList<>();
		Date nowTime = new Date();
		for (GoodsSourceBasicDto goodsSourceBasicDto : list) {
			String timeStr = "";
			Date createTime = goodsSourceBasicDto.getCreateTime();
			if (createTime != null) {
				long temp = nowTime.getTime() - createTime.getTime();
				long hours = temp / 1000 / 3600;

				if (hours < 1) {
					long temp2 = temp % (1000 * 3600);
					long mins = temp2 / 1000 / 60;
					timeStr = mins + "分钟前";
				} else if (1 <= hours && hours < 24) {
					timeStr = hours + "小时前";
				} else {
					long day = temp / 1000 / 3600 / 24;
					timeStr = day + "天前";
				}
			}
			goodsSourceBasicDto.setTimeStr(timeStr);
			goodsSourceBasicDtos.add(goodsSourceBasicDto);
		}
		return goodsSourceBasicDtos;

	}

	/**
	 * 
	 * @Description: 去除参数里面的指定后缀
	 * @param param
	 *            参数
	 * @param name
	 *            需要去除的参数名称
	 * @param suffix
	 *            后缀
	 * @return
	 *
	 */
	private Map<String, Object> removeSuffix(Map<String, Object> param, String name, String suffix) {
		if (param != null && name != null && suffix != null) {
			if (param.get(name) != null) {
				String temp = param.get(name).toString().trim();
				temp = temp.replaceAll(suffix, "").trim();
				param.put(name, temp);
			}
		}
		return param;
	}

}
