package cn.gdeng.nst.util.server;

import java.lang.reflect.Method;

/**反射工具类
 * @author wjguo
 * datetime 2016年8月11日 下午8:48:49  
 *
 */
public final class ReflectionUtils {
	/** 通过反射调用指定方法。
	 * @param obj 被调用的对象
	 * @param methodName 方法名称
	 * @return 调用结果。
	 * @throws Exception
	 */
	public static Object invokeMethod(Object obj, String methodName, Object... param) throws Exception{
		Class<?>[] clazz = transClass(param);
		Method sDetailMethod = obj.getClass().getMethod(methodName, clazz);
		return sDetailMethod.invoke(obj, param);
	}
	
	/** Object对象转换为Class对象
	 * @param objs
	 * @return
	 */
	private static Class<?>[] transClass(Object... objs) {
		int len = objs.length;
		Class<?>[] clazz = new Class[len];
		for (int i = 0; i < len; i++) {
			clazz[i] = objs[i].getClass();
		}
		return clazz;
	}

	
}
