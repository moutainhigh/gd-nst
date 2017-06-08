package cn.gdeng.nst.server.source.mq;

import cn.gdeng.nst.util.web.api.BizException;

/**货源生产者MQ服务类
 * @author wjguo
 * datetime 2016年8月16日 下午8:53:40  
 *
 */
public interface GoodsProvidereMQServie {
	/**生产货源分配的mq
	 * @param sourceShipperID
	 * @throws BizException
	 */
	public void goodsAssignmentMQ(Integer sourceShipperID) throws BizException;
	
	/**
	 * 发送MQ 
	 * 物流公司接受分配的货源，推送消息给货主
	 * @param sourceId 货源id
	 * @param memberId 货主会员id
	 */
	public void msgPushToShipperForGoodsAssignmentAccepted(Integer sourceId, Integer memberId) throws BizException;

	/**
	 * 发送MQ 
	 * 物流公司接受平台配送的货源，推送消息给车主
	 * @param sourceId
	 * @param memberId
	 * @throws BizException
	 */
	public void msgPushToDriverForPlatformAssignmentAccepted(Integer orderBeforeId, Integer memberId)throws BizException;
}
