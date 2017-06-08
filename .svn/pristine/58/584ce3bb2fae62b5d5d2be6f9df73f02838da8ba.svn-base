package cn.gdeng.nst.admin.server.admin.mq.error;

import cn.gdeng.nst.dao.BaseDao;
import cn.gdeng.nst.entity.nst.MqError;
import cn.gdeng.nst.enums.MqConstants;
import cn.gdeng.nst.util.web.api.GSONUtils;


public class MqErrorAction {
    /**
     * 异常数据存入表 mq_error
     * @param <T>
     * @param dto
     * @param remark 异常信息备注
     */
    public static <T> void insertMqError(BaseDao<?> baseDao,T t,Integer topIc,String remark){
        MqError mqError = new MqError();
        mqError.setBizType(MqConstants.BIZ_TYPE_1);
        mqError.setTopic(topIc);
        mqError.setContent(GSONUtils.toJson(t,false));
        mqError.setRemark(remark);
        baseDao.execute("MqError.insert", mqError);
    }
}
