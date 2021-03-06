package cn.gdeng.nst.web.controller.source;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.gdeng.nst.api.dto.member.AppInfoDto;
import cn.gdeng.nst.api.server.source.SearchGoodsSourceService;
import cn.gdeng.nst.enums.selfdefine.RecommendEnum;
import cn.gdeng.nst.pub.service.AreaService;
import cn.gdeng.nst.util.web.api.ApiPage;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.web.controller.base.BaseController;
import cn.gdeng.nst.web.util.DataCheckUtils;

/**
 * @author DJB
 * @version 创建时间：2017年2月15日 上午11:37:54 第二版 搜索货源 控制类
 */
@Controller
@RequestMapping ("v2/searchGoodsSource")
public class SearchGoodsSourceController extends BaseController {

	/**
	 * 货源控制类
	 */
	@Reference
	private SearchGoodsSourceService searchGoodsSourceService;

	/**
	 * 区域服务类
	 */
	@Reference
	private AreaService areaService;

	/**
	 * 
	 * @Description: 第一次进入，查询推荐货源 使用GPS查询，需要传出发地的省市区
	 * @param request
	 * @return
	 * @throws Exception
	 *             参数说明 visitSource：2 车主 3物流公司
	 *
	 */
	@RequestMapping ("queryGoodsSource")
	@ResponseBody
	public ApiResult<ApiPage> queryGoodsSource(HttpServletRequest request) throws Exception {
		ApiPage page = getPageInfoEncript(request);
		Map<String, Object> param = page.getParaMap();

		AppInfoDto appInfoDto = getAppInfo(request);
		if (null != appInfoDto && null != appInfoDto.getMemberId()) {
			param.put("memberId", appInfoDto.getMemberId());
		}

		if (null != param.get("recommend")
				&& param.get("recommend").toString().equals(RecommendEnum.SYS_RECOMMEND.getCode())
				&& DataCheckUtils.mapIsKey(param, "memberId")
				) {
			// 推荐
			return searchGoodsSourceService.queryRecommendGoods(page);
		}
		if (param.get("recommend") != null
				&& param.get("recommend").toString().equals(RecommendEnum.ALLOCATE_CARGO.getCode())) {
			// 空车配货定位查询
			Integer s_provinceId = DataCheckUtils.mapValueToInteger(param, "s_provinceId"),
					s_cityId = DataCheckUtils.mapValueToInteger(param, "s_cityId"),
					s_areaId = DataCheckUtils.mapValueToInteger(param, "s_areaId");
			String sCityName = DataCheckUtils.mapValueToString(param, "sCityName");
			if (s_areaId == null && s_cityId == null && s_provinceId == null && sCityName != null) {
				param = areaService.mapSrcAddrToId(param, "s_provinceId", "sProvinceName", "s_cityId", "sCityName",
						"s_areaId", "sAreaName");
				page.setParaMap(param);
			}
		}
		return searchGoodsSourceService.queryGoodsPage(page);

	}

}
