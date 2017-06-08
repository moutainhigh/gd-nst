package cn.gdeng.nst.util.web.api;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/** 数值工具类
 * @author wjguo
 * datetime 2016年8月12日 上午11:41:37  
 *
 */
public final class NumUtils {
	

	/** 格式化小数的输出。
	 * @param dob 数值。
	 * @param minFraction 最小保留的小数位数
	 * @param maxFraction 最大保留的小数位数
	 * @return 如果数值为空，则返回空字符串""
	 */
	public static String format(Double dob, int minFraction ,int maxFraction) {
		if (dob == null) {
			return "";
		}
		NumberFormat format=NumberFormat.getNumberInstance() ;
		format.setMinimumFractionDigits(minFraction);
		format.setMaximumFractionDigits(maxFraction) ;
		return format.format(dob);
	}
	
	/** 格式化小数的输出，最大保留两位小数。
	 * @param dob 小数
	 * @return 如果数值为空，则返回空字符串""
	 */
	public static String format(Double dob) {
		return format(dob, 0, 2);
	}
	
	/**格式化并加逗号标识符。<br/>
	 * ps：每隔三位加一个逗号标识符，最大保留两位小数。
	 * @param dob
	 * @return
	 */
	public static String formatAndDotIdent(Double dob) {
		if (dob == null) {
			return "";
		}
		
		DecimalFormat format = new DecimalFormat("#,###.##");
		return format.format(dob);
	}
	
}
