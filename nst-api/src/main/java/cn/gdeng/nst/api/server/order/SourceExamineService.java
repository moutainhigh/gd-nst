package cn.gdeng.nst.api.server.order;

import cn.gdeng.nst.api.dto.order.SourceExamineDTO;
import cn.gdeng.nst.api.vo.order.SourceExamineVo;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.BizException;

/**
 * service 验货
 * 
 * @author wk  2016年12月5日  
 * @version 1.0
 */
public interface SourceExamineService {
	

	/**
	 * 查询验货详情
	 * @param SourceExamineDTO
	 * @return
	 * @throws Exception
	 */
	ApiResult<SourceExamineVo>  querysourceExamine(SourceExamineDTO dto)throws BizException;
	
	/**
	 * 添加验货(图片传来的是数组)
	 * @param SourceExamineDTO
	 * @return
	 * @throws Exception
	 */
	ApiResult<Long> saveSourceExamine(SourceExamineDTO dto)		throws BizException;
	

}
