package cn.gdeng.nst.util.server;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.SqlDateConverter;
import org.apache.commons.beanutils.converters.SqlTimestampConverter;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class SqlUtil {

    public static String DATE_FORMAT = "yyyy-MM-dd";

    public static String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static final SqlUtil sqlUtil = new SqlUtil();

    static {
        DateConverter converter = new DateConverter();
        converter.setPattern(DATETIME_FORMAT);
        ConvertUtils.register(converter, java.util.Date.class);
    }
    static {
        SqlDateConverter converter = new SqlDateConverter();
        converter.setPattern(DATE_FORMAT);
        ConvertUtils.register(converter, java.sql.Date.class);
    }
    static {
        SqlTimestampConverter converter = new SqlTimestampConverter();
        converter.setPattern(DATETIME_FORMAT);
        ConvertUtils.register(converter, java.sql.Timestamp.class);
    }

    private SqlUtil() {

    }

    public static SqlUtil getInstance() {
        return sqlUtil;
    }

    public static Map<String, Object> wrapMap(Map<String, Object> paramMap) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        Set<Map.Entry<String, Object>> entrySet = paramMap.entrySet();
        for (Map.Entry<String, Object> entry : entrySet) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof java.util.Date) {
                value = new SimpleDateFormat(DATETIME_FORMAT).format(value);
            }
            else if (value instanceof java.sql.Date) {
                value = new SimpleDateFormat(DATE_FORMAT).format(value);
            }
            else if (value instanceof java.sql.Timestamp) {
                value = new SimpleDateFormat(DATETIME_FORMAT).format(value);
            }
            resultMap.put(key, value);
        }

        return resultMap;
    }

    public static Map<String, Object> wrapMap(Object obj) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        Method[] methods = obj.getClass().getMethods();
        String methodName = null;
        for (Method method : methods) {
            methodName = method.getName();
            if (!methodName.startsWith("get") && !methodName.startsWith("is")) {
                continue;
            }
            PropertyDescriptor descriptor = BeanUtils.findPropertyForMethod(method);
            if (descriptor == null) {
                continue;
            }
            String key = descriptor.getName();
            if (key.equals("class")) {
                continue;
            }
            Object value = null;
            try {
                value = method.invoke(obj, new Object[] {});
                if (value instanceof java.util.Date) {
                    value = new SimpleDateFormat(DATETIME_FORMAT).format(value);
                }
                else if (value instanceof java.sql.Date) {
                    value = new SimpleDateFormat(DATE_FORMAT).format(value);
                }
                else if (value instanceof java.sql.Timestamp) {
                    value = new SimpleDateFormat(DATETIME_FORMAT).format(value);
                }
            }
            catch (Exception e) {
                // TODO
            }
            resultMap.put(key, value);
        }

        return resultMap;
    }

}
