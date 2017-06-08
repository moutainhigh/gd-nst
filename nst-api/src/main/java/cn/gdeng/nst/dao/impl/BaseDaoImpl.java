package cn.gdeng.nst.dao.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import cn.gdeng.nst.dao.BaseDao;
import cn.gdeng.nst.entity.nst.MqError;
import cn.gdeng.nst.util.web.api.GSONUtils;

import com.gudeng.framework.dba.client.DbaClient;
import com.gudeng.framework.page.QueryParam;
import com.gudeng.framework.page.QueryResult;

/**
 * 功能描述： baseDao实现
 * @author 
 * @param <T> 操作泛型
 */

public class BaseDaoImpl<T> implements BaseDao<T> {

    public static final String    SQL_COUNT         = "count";

    public static final String    SQL_LIST         = "list";

    public static final String    SQL_LIST_BY_PAGE    = "listByPage";

    public static final String    SQL_INSERT         = "insert";

    public static final String    SQL_FIND_BY         = "findBy";

    public static final String    SQL_UPDATE         = "update";

    public static final String    SQL_DELETE         = "delete";

    @Autowired
    private DbaClient     dalClient;


    @Override
    public Number insertMqError(Integer memberId, T content,
    		Integer topic,  Integer bizType,
    		String httpUrl,
    		String remark) {
    	MqError mqError=new MqError();
    	mqError.setBizType(bizType);
    	mqError.setTopic(topic);
    	mqError.setContent(GSONUtils.toJson(content,false));
    	mqError.setRemark(remark);
    	mqError.setMemberId(memberId);
    	mqError.setHttpUrl(httpUrl);
    	return dalClient.persist(mqError);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Number persist(T t) {
        return dalClient.persist(t);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E> E persist(Object entity, Class<E> requiredType) {
        return dalClient.persist(entity, requiredType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int merge(T t) {
        return dalClient.merge(t);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int remove(T t) {
        return dalClient.remove(t);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E> E find(Class<E> entityClass, Object entity) {
        return dalClient.find(entityClass, entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T find(T t) {
        return (T) dalClient.find(t.getClass(), t);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E> E queryForObject(String sqlId, Map<String, ?> paramMap, Class<E> requiredType) {
        return dalClient.queryForObject(sqlId, paramMap, requiredType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E> E queryForObject(String sqlId, Object param, Class<E> requiredType) {
        return dalClient.queryForObject(sqlId, param, requiredType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Object> queryForMap(String sqlId, Map<String, ?> paramMap) {

        return dalClient.queryForMap(sqlId, paramMap);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Object> queryForMap(String sqlId, Object param) {

        return dalClient.queryForMap(sqlId, param);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E> List<E> queryForList(String sqlId, Map<String, ?> paramMap, Class<E> elementType) {
        return dalClient.queryForList(sqlId, paramMap, elementType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E> List<E> queryForList(String sqlId, Object param, Class<E> requiredType) {
        return dalClient.queryForList(sqlId, param, requiredType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Map<String, Object>> queryForList(String sqlId, Map<String, ?> paramMap) {
        return dalClient.queryForList(sqlId, paramMap);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Map<String, Object>> queryForList(String sqlId, Object param) {
        return dalClient.queryForList(sqlId, param);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int execute(String sqlId, Map<String, ?> paramMap) {
        return dalClient.execute(sqlId, paramMap);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int execute(String sqlId, Object param) {
        return dalClient.execute(sqlId, param);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int[] batchUpdate(String sqlId, Map<String, Object>[] batchValues) {
        return dalClient.batchUpdate(sqlId, batchValues);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int dynamicMerge(Object entity) {
        return dalClient.dynamicMerge(entity);
    }

    /**
     * <p>
     * 分页查询
     * </p>
     * <p>
     * 部分限制：查询条数的sql：查询结果需要以count为别名
     * </p>
     * @param countSqlId 查询总条数的SQL_ID
     * @param sqlId 查询内容的SQL_ID
     * @param param 参数集合
     * @param requiredType 返回类型
     * @return requiredType类型的查询结果
     */
    public final <E> QueryResult<E> pageQuery(String countSqlId, String pageSqlId, 
            QueryParam<Map<String, Object>> param, Class<E> requiredType) {

            QueryResult<E> result = null;

            if (param != null) {
                int totalCount = 0;
                Map<String, Object> map = queryForMap(countSqlId, param.getQueryParam());
                if (map != null) {
                    totalCount = Integer.valueOf(String.valueOf(map.get("totalRows"))).intValue();
                }

                // 把检索参数置回返回参数中
                result = new QueryResult<E>(totalCount, param.getPageSize(), param.getPageNumber());

                param.getQueryParam().put("totalRows", totalCount);//总行数
                // 如果总数大于0，继续查询
                if (totalCount != 0) {
                    // 设置检索开始行
                    param.getQueryParam().put("startIndex", result.getIndexNumber());
                    // 设置检索数据件数
                    param.getQueryParam().put("maxCount", result.getPageSize());

                    // 检索数据列表，放到返回结果对象中
                    List<E> retList = queryForList(pageSqlId, param.getQueryParam(), requiredType);

                    result.setDatas(retList);
                }
            }
            return result;
        }



}

