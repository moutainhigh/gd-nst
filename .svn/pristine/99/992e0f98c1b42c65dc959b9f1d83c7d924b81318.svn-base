package cn.gdeng.nst.api.server.source;

import cn.gdeng.nst.entity.nst.SourceLogEntity;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.BizException;

/**货源日志记录服务类
 * @author wjguo
 * datetime 2016年8月12日 下午3:04:19  
 *
 */
public interface GoodsLogService {
	/**保存实体
	 * @param entity
	 * @return 结果集数据为保存的id
	 * @throws BizException
	 */
	public ApiResult<Integer> save(SourceLogEntity entity) throws BizException;
	
	/**保存实体的简便方法
	 * @param sourceId  货源id
	 * @param desc 日志描述
	 * @param createUserId 创建人id
	 * @return  结果集数据为保存的id
	 * @throws BizException
	 */
	public ApiResult<Integer> simpleSave(Integer sourceId, String desc, String createUserId) throws BizException;
}
