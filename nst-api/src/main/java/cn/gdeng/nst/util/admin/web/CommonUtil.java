package cn.gdeng.nst.util.admin.web;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtil {

	public static String generatePictureName(String fileName, int width){
		int index = fileName.lastIndexOf(".");
		String fileExt = fileName.substring(index);
		String newName = fileName.substring(0, index) + width + "_" + width + fileExt;
		return newName;
	}
	public static String generateSimpleFileName(String orgName){
		int index = orgName.lastIndexOf(File.separator);
		String fileName = "";
		if (index != -1){
			fileName = orgName.substring(index+1);
		}else {
			fileName = orgName;
		}
		return fileName;
	}
	public static boolean isMobile(String str) {   
        Pattern p = null;  
        Matcher m = null;  
        boolean b = false;   
        p = Pattern.compile("^[1][3,4,5,8][0-9]{9}$"); // 验证手机号  
        m = p.matcher(str);  
        b = m.matches();   
        return b;  
    }
	public static String getStartOfDay(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return DateUtil.getDate(calendar.getTime(), DateUtil.DATE_FORMAT_DATETIME);
	}
	public static String getEndOfDay(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return DateUtil.getDate(calendar.getTime(), DateUtil.DATE_FORMAT_DATETIME);
	}
	
	
	/**
	 * @Description formatNumber 数字格式化，将数值大于10000的值转化为以万为单位，保留两位小数，最后以为小数四舍五入，123456.56=12.35万
	 * @param price
	 * @return
	 * @CreationDate 2015年11月3日 下午12:20:44
	 * @Author lidong(dli@cnagri-products.com)
	*/
	public static String formatNumber(Double number) {
		String numberString = null;
		if (number==null) {
			return "0";
		}
		DecimalFormat df = new DecimalFormat("0.00");
		if (number < 10000) {
			numberString = "" + df.format(number);
		} else {
			number = number / 10000.0;
			numberString = df.format(number)+"万";
		}

		return numberString;
	}
}
