package cn.gdeng.nst.server.source.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Service;
import com.gudeng.framework.dba.transaction.annotation.Transactional;

import cn.gdeng.nst.api.dto.source.CarBasicDto;
import cn.gdeng.nst.api.dto.source.CarDriverBasicDto;
import cn.gdeng.nst.api.dto.source.CarDriverDetailDto;
import cn.gdeng.nst.api.dto.source.FindCarLineDto;
import cn.gdeng.nst.api.dto.source.GoodsSourceBasicDto;
import cn.gdeng.nst.api.server.source.CarService;
import cn.gdeng.nst.api.vo.order.OrderDetailBaseVo;
import cn.gdeng.nst.dao.BaseDao;
import cn.gdeng.nst.entity.nst.FindCarLineEntity;
import cn.gdeng.nst.entity.nst.MemberInfoEntity;
import cn.gdeng.nst.enums.MsgCons;
import cn.gdeng.nst.util.web.api.ApiPage;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.BizException;
import cn.gdeng.nst.util.web.api.Constant;
import cn.gdeng.nst.util.web.api.ParamProcessUtil;

/**
 * @author DJB
 * @version 创建时间：2016年8月4日 下午3:50:43 车源服务接口实现
 */

@Service
public class CarServiceImpl implements CarService {

	// private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	private BaseDao<?> baseDao;

	/**
	 * 
	 * @Description: 添加物流公司车辆查询线路
	 * @param findCarLineEntity
	 * @return
	 * @throws BizException
	 *
	 */
	@Transactional
	@Override
	public ApiResult<FindCarLineDto> saveFindCarLine(FindCarLineEntity findCarLineEntity) throws BizException {
		ApiResult<FindCarLineDto> result = new ApiResult<FindCarLineDto>();
		Integer memberId = findCarLineEntity.getMemberId();
		Map<String, Object> param=new HashMap<>();
		param.put("memberId", memberId);
		List<FindCarLineDto> findCarLineDtos = baseDao.queryForList("FindCarLine.queryByMemberId", param, FindCarLineDto.class);
		if (findCarLineDtos != null) {
			if (findCarLineDtos.size() >= 5) {
				// 已经超过五条了，不可以再添加
				return result.withError(MsgCons.C_22021, MsgCons.M_22021);
			} else {
				// 判断是否重复添加过
				compareArea(findCarLineEntity, findCarLineDtos);
			}
		}
		// 将此会员的所有线路更新为不选中
		baseDao.execute("FindCarLine.updateIsSelect", param);
		// 添加线路
		Long  id=baseDao.persist(findCarLineEntity,Long.class);
		findCarLineEntity.setId(id.intValue());
		FindCarLineDto findCarLineDto=new FindCarLineDto(findCarLineEntity);
		return result.setResult(findCarLineDto);

	}

	/**
	 * 
	 * @Description: 分页查询司机线路
	 * @param page
	 * @return
	 * @throws BizException
	 *
	 */
	@Override
	public ApiResult<ApiPage> queryFindCarLinePage(ApiPage page) throws BizException {
		ApiResult<ApiPage> result = new ApiResult<ApiPage>();
		Map<String, Object> param=page.getParaMap();
		// 从0开始查询 需要更新选中
		if (page.getOffset() == 0) {
			updateFindCarLine(param.get("id"),param.get("memberId"));
		}
		//查询线路
		FindCarLineEntity findCarLineEntity = baseDao.queryForObject("FindCarLine.queryFindCarLineById",param,FindCarLineEntity.class);
		if (findCarLineEntity==null) {
			throw new BizException(MsgCons.C_20001, MsgCons.M_20001);
		}
		Map<String, Object> map=page.getParaMap();
		map.put("s_areaId",findCarLineEntity.getS_areaId());
		map.put("s_cityId",findCarLineEntity.getS_cityId());
		map.put("s_provinceId",findCarLineEntity.getS_provinceId());
		map.put("e_areaId",findCarLineEntity.getE_areaId());
		map.put("e_cityId",findCarLineEntity.getE_cityId());
		map.put("e_provinceId",findCarLineEntity.getE_provinceId());
		int total = baseDao.queryForObject("FindCarLine.countQueryCarByLine", map, Integer.class);
		if (total > 0) {
			List<CarDriverBasicDto> list = baseDao.queryForList("FindCarLine.queryCarByLine", map, CarDriverBasicDto.class);
			page.setRecordList(list);
		}
		page.setRecordCount(total);
		return result.setResult(page);

	}

	/**
	 * 
	 * @Description: 查询物流公司添加的线路
	 * @param memberId
	 * @return
	 * @throws BizException
	 *
	 */
	@Override
	public ApiResult<List<FindCarLineDto>> queryFindCarLineDtos(Integer memberId) throws BizException {
		ApiResult<List<FindCarLineDto>> result = new ApiResult<List<FindCarLineDto>>();
		Map<String, Object> param=new HashMap<>();
		param.put("memberId", memberId);
		List<FindCarLineDto> findCarLineDtos = baseDao.queryForList("FindCarLine.queryByProperty", param, FindCarLineDto.class);
		return result.setResult(findCarLineDtos);
	}

	/**
	 * 
	 * @Description: 更新此线路为默认选择
	 * @param paraMap
	 *            id,memberId
	 *
	 */
	@Transactional
	@Override
	public void updateFindCarLine(Object id, Object memberId) throws BizException {
		if (id != null && memberId != null) {
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("id", id);
			paraMap.put("memberId", memberId);
			// 判断是否是选中
			List<FindCarLineDto> findCarLineDtos = baseDao.queryForList("FindCarLine.queryByProperty", paraMap, FindCarLineDto.class);
			if (findCarLineDtos == null || findCarLineDtos.isEmpty() || findCarLineDtos.get(0).getIsSelect().compareTo(Constant.TABLE_SELECT) != 0) {
				// 全部改为不选中
				Map<String, Object> para= new HashMap<String, Object>();
				para.put("memberId", memberId);
				baseDao.execute("FindCarLine.updateIsSelect",para);
				paraMap.put("isSelect", Constant.TABLE_SELECT);
				// 更新此条为选中
				baseDao.execute("FindCarLine.updateIsSelect", paraMap);
			}
		}
	}

	/**
	 * 
	 * @Description:删除物流查询线路
	 * @param param
	 *            id(需要删除数据的ID) 、 memberId(物流公司ID)
	 * @return
	 * @throws BizException
	 *
	 */
	@Transactional
	@Override
	public ApiResult<List<FindCarLineDto>> deleteFindCarLineDtos(Map<String, Object> param) throws BizException {
		baseDao.execute("FindCarLine.delectLine", param);
		ApiResult<List<FindCarLineDto>> result = new ApiResult<List<FindCarLineDto>>();
		param.remove("id");
		List<FindCarLineDto> findCarLineDtos = baseDao.queryForList("FindCarLine.queryByProperty", param, FindCarLineDto.class);
		return result.setResult(findCarLineDtos);

	}

	/**
	 * 
	 * @Description:查询司机详细信息
	 * @param param
	 * @return
	 * @throws BizException
	 *
	 */
	@Override
	public ApiResult<CarDriverDetailDto> queryCarDetail(Integer memberId) throws BizException {
		Map<String, Object> param=new HashMap<>();
		param.put("memberId", memberId);
		// 查询司机线路
		List<FindCarLineDto> findCarLineDtos = baseDao.queryForList("FindCarLine.queryCarLine", param, FindCarLineDto.class);
		// 查询司机车辆
		List<CarBasicDto> carBasicDtos = baseDao.queryForList("FindCarLine.queryCar", param, CarBasicDto.class);
		// 查询司机信息
		CarDriverBasicDto carDriverBasicDto = baseDao.queryForObject("FindCarLine.queryCarDriver", param, CarDriverBasicDto.class);
		CarDriverDetailDto carDriverDetailDto = new CarDriverDetailDto(carDriverBasicDto, carBasicDtos, findCarLineDtos);
		return new ApiResult<CarDriverDetailDto>().setResult(carDriverDetailDto);
	}

	/*************************** 本类私有方法 ************************************/

	/**
	 * 
	 * @Description: 比较出发地、目的地 是否有重复的
	 * @param findCarLineEntity
	 * @param findCarLineDtos
	 * @throws BizException
	 *
	 */
	private void compareArea(FindCarLineEntity findCarLineEntity, List<FindCarLineDto> findCarLineDtos) throws BizException {
		List<Integer> newAddress = new ArrayList<Integer>();
		newAddress.add(findCarLineEntity.getS_areaId());
		newAddress.add(findCarLineEntity.getS_cityId());
		newAddress.add(findCarLineEntity.getS_provinceId());
		newAddress.add(findCarLineEntity.getE_areaId());
		newAddress.add(findCarLineEntity.getE_cityId());
		newAddress.add(findCarLineEntity.getE_provinceId());
		// 判断是否有重复数据
		for (FindCarLineDto findCarLineDto : findCarLineDtos) {
			List<Integer> oldAddress = new ArrayList<Integer>();
			oldAddress.add(findCarLineDto.getS_areaId());
			oldAddress.add(findCarLineDto.getS_cityId());
			oldAddress.add(findCarLineDto.getS_provinceId());
			oldAddress.add(findCarLineDto.getE_areaId());
			oldAddress.add(findCarLineDto.getE_cityId());
			oldAddress.add(findCarLineDto.getE_provinceId());
			if (ParamProcessUtil.compareSort(newAddress, oldAddress)) {
				// 已经添加过了
				throw new BizException(MsgCons.C_22022, MsgCons.M_22022);
			}
		}
	}

	/**
	 * 
	 * @Description: 根据实体查询线路
	 * @param findCarLineEntity
	 *            对象实体
	 * @param startRow
	 *            开始
	 * @param endRow
	 *            结束位置
	 * @return
	 * @throws BizException
	 *
	 */
/*	private ApiPage findCarDriverBasicDtos(FindCarLineEntity findCarLineEntity, Integer startRow, Integer endRow) throws BizException {
		ApiPage page = new ApiPage();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("s_areaId", findCarLineEntity.getS_areaId());
		map.put("s_cityId", findCarLineEntity.getS_cityId());
		map.put("s_provinceId", findCarLineEntity.getS_provinceId());
		map.put("e_areaId", findCarLineEntity.getE_areaId());
		map.put("e_cityId", findCarLineEntity.getE_cityId());
		map.put("e_provinceId", findCarLineEntity.getE_provinceId());
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		int total = baseDao.queryForObject("FindCarLine.countQueryCarByLine", map, Integer.class);
		if (total > 0) {
			List<CarDriverBasicDto> list = baseDao.queryForList("FindCarLine.queryCarByLine", map, CarDriverBasicDto.class);
			page.setRecordList(list);
		}
		page.setRecordCount(total);
		page.setOffset(startRow);
		page.setPageSize(endRow);
		page.setParaMap(map);
		return page;

	}*/
	
	
	/**
	 * 
	 * @Description: 车主查找货源  --分页查找货源
	 * @param page
	 * @return
	 * @throws BizException
	 *
	 */
	@Override
	public ApiResult<ApiPage> queryGoodsPage(ApiPage page) throws BizException {
		 Map<String, Object> param= page.getParaMap();
		param.put("startTime", ParamProcessUtil.getNewDateAdd(-6));
		//param.put("startTime","2016-08-11 11:12:13");
		ApiResult<ApiPage> result = new ApiResult<ApiPage>();
		//车长和车型 不限时需要去掉参数
		if (param.get("carType")!=null&&param.get("carType").equals("-1")) {
			param.remove("carType");
		}
		
		param=removeSuffix(param,"carLength","米");
		if (param.get("carLength")!=null&&param.get("carLength").equals("-1")) {
			param.remove("carLength");
		}
		/*int total = baseDao.queryForObject("FindCarLine.countGoodsPage", param, Integer.class);
		if (total > 0) {
			 List<GoodsSourceBasicDto> goodsSourceBasicDtos=baseDao.queryForList("FindCarLine.queryGoodsPage", param, GoodsSourceBasicDto.class);
			 goodsSourceBasicDtos=listDateToChinese(goodsSourceBasicDtos);
			 page.setRecordList(goodsSourceBasicDtos);
		}
		page.setRecordCount(total);*/
		
		Integer endRow=(Integer) param.get("endRow");
		Integer startRow=(Integer) param.get("startRow");
		
		param.put("endRow", endRow+1);
		List<GoodsSourceBasicDto> goodsSourceBasicDtos= baseDao.queryForList("FindCarLine.queryGoodsPage", param, GoodsSourceBasicDto.class);
		if (goodsSourceBasicDtos!=null&&goodsSourceBasicDtos.size()>0) {
			goodsSourceBasicDtos=listDateToChinese(goodsSourceBasicDtos);
			int size=goodsSourceBasicDtos.size();
			if (size>endRow) {
				page.setRecordCount(startRow+endRow+1);
				//page.setHasNextPage(true);
				goodsSourceBasicDtos.remove(size-1);
				page.setRecordList(goodsSourceBasicDtos);
			}else {
				page.setRecordCount(goodsSourceBasicDtos.size()+startRow);
				page.setRecordList(goodsSourceBasicDtos);
			}
			
		}else {
			page.setRecordCount(startRow);
		}
		
		return result.setResult(page);
	}
	
	/**
	 * 
	 * @Description: 物流公司找货源--分页查货源总数统计
	 * @param page
	 * @return
	 * @throws BizException
	 *
	 */
	@Override
	public ApiResult<Object> queryGoodsAllTotal(ApiPage page) throws BizException {
		Map<String, Object> param= page.getParaMap();
		param.put("startTime", ParamProcessUtil.getNewDateAdd(-6));
		//param.put("startTime","2016-08-11 11:12:13");
		ApiResult<Object> result = new ApiResult<Object>();
		//车长和车型 不限时需要去掉参数
		if (param.get("carType")!=null&&param.get("carType").equals("-1")) {
			param.remove("carType");
		}
		
		param=removeSuffix(param,"carLength","米");
		if (param.get("carLength")!=null&&param.get("carLength").equals("-1")) {
			param.remove("carLength");
		}
		int total = baseDao.queryForObject("FindCarLine.countGoodsPage", param, Integer.class);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("total", total);
		return result.setResult(map);
	}

	/**
	 * 
	 * @Description: 车主查找货源--第一次进入，系统推荐货源
	 * @param page
	 * @return
	 * @throws BizException
	 *
	 */
	@Override
	public ApiResult<ApiPage> queryRecommendGoods(ApiPage page) throws BizException {
		 Map<String, Object> param= page.getParaMap();
		 ApiResult<ApiPage> result = new ApiResult<ApiPage>();
		//查询司机业务类型 param :memberId
		MemberInfoEntity memberInfoEntity=baseDao.queryForObject("FindCarLine.queryMemberById", param, MemberInfoEntity.class);
		if (memberInfoEntity==null) {
			throw new BizException(MsgCons.C_21018, MsgCons.M_21018);
		}
		if (memberInfoEntity.getServiceType()==null) throw new BizException(MsgCons.C_21008, MsgCons.M_21008);
		ApiPage pageTemp=findRecommendGoodsByLine(page);
		if (pageTemp!=null) {
			return result.setResult(pageTemp);
		}
		//没有常跑路线，就用GPS  处理出发城市
		//都没有，根据业务类型推荐
		/*Map<String, Object> paramTemp=new HashMap<String, Object>();*/
		param.put("startTime", ParamProcessUtil.getNewDateAdd(-6));
		if (!ParamProcessUtil.mapKeyIsEmpty(param, "sourceType")) {
			param.put("sourceType", memberInfoEntity.getServiceType());
		}
		int total = baseDao.queryForObject("FindCarLine.countGoodsPage", param, Integer.class);
		if (total > 0) {
			 List<GoodsSourceBasicDto> goodsSourceBasicDtos=baseDao.queryForList("FindCarLine.queryGoodsPage", param, GoodsSourceBasicDto.class);
			 goodsSourceBasicDtos=listDateToChinese(goodsSourceBasicDtos);
			 page.setRecordList(goodsSourceBasicDtos);
			 page.setRecordCount(total);
			return result.setResult(page);
		}
		
		return this.queryGoodsPage(page);
	}
	
	/**
	 * 
	 * @Description: 根据常跑路线推荐 （本类私有方法）
	 * @param page memberId(必须)  sourceType(非必须) :startRow,:endRow 
	 * @return
	 *
	 */
    private  ApiPage  findRecommendGoodsByLine(ApiPage page){
    	Map<String, Object> param= page.getParaMap();
    	//查询司机常跑路线 param :memberId
    	List<FindCarLineDto> findCarLineDtos = baseDao.queryForList("FindCarLine.queryCarLine", param, FindCarLineDto.class);
		if (findCarLineDtos!=null&& !findCarLineDtos.isEmpty()) {
			List<List<String>> listMap=new ArrayList<List<String>>();
			for (FindCarLineDto findCarLineDto : findCarLineDtos) {
				List<String> list=new ArrayList<String>(),list2=new ArrayList<String>();
				if (findCarLineDto.getS_areaId()!=null) {
					list.add("s_areaId="+findCarLineDto.getS_areaId());
					list2.add("e_areaId="+findCarLineDto.getS_areaId());
				}
				if (findCarLineDto.getS_cityId()!=null) {
					list.add("s_cityId="+findCarLineDto.getS_cityId());
					list2.add("e_cityId="+findCarLineDto.getS_cityId());
				}
				if (findCarLineDto.getS_provinceId()!=null) {
					list.add("s_provinceId="+findCarLineDto.getS_provinceId());
					list2.add("e_provinceId="+findCarLineDto.getS_provinceId());
				}
				if (findCarLineDto.getE_areaId()!=null) {
					list.add("e_areaId="+findCarLineDto.getE_areaId());
					list2.add("s_areaId="+findCarLineDto.getE_areaId());
				}
				if (findCarLineDto.getE_cityId()!=null) {
					list.add("e_cityId="+findCarLineDto.getE_cityId());
					list2.add("s_cityId="+findCarLineDto.getE_cityId());
				}
				if (findCarLineDto.getE_provinceId()!=null) {
					list.add("e_provinceId="+findCarLineDto.getE_provinceId());
					list2.add("s_provinceId="+findCarLineDto.getE_provinceId());
				}
				listMap.add(list);
				listMap.add(list2);
			}
			param.put("cityList", listMap);
			param.put("startTime", ParamProcessUtil.getNewDateAdd(-6));
			// 根据常跑路线查询司机推荐路线  param: 出发地  目的地   sourceType :startRow,:endRow 
			int total = baseDao.queryForObject("FindCarLine.countRecommendGoods", param, Integer.class);
			if (total > 0) {
				 List<GoodsSourceBasicDto> goodsSourceBasicDtos=baseDao.queryForList("FindCarLine.queryRecommendGoods", param, GoodsSourceBasicDto.class);
				 goodsSourceBasicDtos=listDateToChinese(goodsSourceBasicDtos);
				 page.setRecordList(goodsSourceBasicDtos);
				 page.setRecordCount(total);
				return page;
			}
			
		}
		return null;
    }

    /**
     * 
     * @Description: 车主查找货源--根据ID 查询货源详情
     * @param param sourceId 
     * @return
     * @throws BizException
     *
     */
	@Override
	public ApiResult<OrderDetailBaseVo> queryGoodsDetail(Map<String, Object> param) throws BizException {
		 ApiResult<OrderDetailBaseVo> result = new ApiResult<OrderDetailBaseVo>();
		OrderDetailBaseVo orderDetailBaseVo= baseDao.queryForObject("FindCarLine.queryGoodsDetailByIdAndStatus", param, OrderDetailBaseVo.class);
		if (orderDetailBaseVo==null) {
			throw new BizException(MsgCons.C_24030, MsgCons.M_24030);
		}
		return result.setResult(orderDetailBaseVo);
	}

	@Override
	public void memberCer(Integer code, String msg, Integer memberId) throws BizException {
		Map<String ,Object> param=new HashMap<>();
		param.put("memberId", memberId);
		// TODO Auto-generated method stub
		MemberInfoEntity memberInfoEntity=baseDao.queryForObject("FindCarLine.queryMemberById", param, MemberInfoEntity.class);
		if (memberInfoEntity==null||!(memberInfoEntity.getCerCompanyStatus().compareTo(2)==0||memberInfoEntity.getCerPersonalStatus().compareTo(2)==0)) {
			throw new BizException(code, msg);
		}
	}
	
	/**
	 * 
	 * @Description: 时间处理  
	 * @param list
	 * @return
	 *
	 */
	private  List<GoodsSourceBasicDto> listDateToChinese(List<GoodsSourceBasicDto> list){
		
		List<GoodsSourceBasicDto> goodsSourceBasicDtos=new ArrayList<>();
		Date nowTime=new Date();
		for (GoodsSourceBasicDto goodsSourceBasicDto : list) {
			String timeStr="";
			Date createTime=goodsSourceBasicDto.getCreateTime();
			if (createTime!=null) {
				long temp =nowTime.getTime()-createTime.getTime();
				long hours=temp / 1000 / 3600;
				
				if (hours<1) {
					 long temp2 = temp % (1000 * 3600);
				     long mins = temp2 / 1000 / 60;  
				     timeStr=mins+"分钟前";
				}else if (1<=hours&&hours<24) {
					timeStr=hours+"小时前";
				}else{
					long day=temp/1000/3600/24;
					timeStr=day+"天前";
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
	 * @param param 参数
	 * @param name 需要去除的参数名称
	 * @param suffix 后缀
	 * @return
	 *
	 */
	private  Map<String, Object>   removeSuffix(Map<String, Object> param,String name,String suffix ){
		if (param!=null&&name!=null&&suffix!=null) {
			if (param.get(name)!=null) {
				String temp=param.get(name).toString().trim();
				temp=temp.replaceAll(suffix, "").trim();
				param.put(name,temp);
			}
		}
		return param;
	}

}
