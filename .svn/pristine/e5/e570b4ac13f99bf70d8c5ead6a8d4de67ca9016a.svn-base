package cn.gdeng.nst.util.web.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 图表常量类
 * @author bob
 *
 */
public final  class ReportsUtil {
	
	/**
	 * 数据的上层
	 */
	public static String DATA="data";
	
	/**
	 * X轴数据
	 */
	public static String PARMS="parms";
	
	/**
	 * Y轴数据1
	 */
	public static String DATA1="data1";
	
	/**
	 * Y轴数据2
	 */
	public static String DATA2="data2";
	
	/**
	 * Y轴数据3
	 */
	public static String DATA3="data3";
	
	/**
	 * Y轴数据4
	 */
	public static String DATA4="data4";
	/**
	 * 颜色
	 */
	public static String COLOR="color";
	
	/**
	 * 类型
	 */
	public static String TYPE="type";
	
	/**
	 * Y轴类型
	 */
	public static String TYPE_Y="typeY";
	
	
	/**
	 * 颜色-红色
	 */
	public static String COLOR_RED="red";
	/**
	 * 颜色-绿色
	 */
	public static String COLOR_GREEN="green";
	
	/**
	 * 颜色枚举
	 * @author bob
	 *
	 */
	public static enum Color{
		BLUE("#4F81BD"),//蓝色
		ORANGE("#F79646"),//橙色
		PURPLE("#8064A2"),//紫色
		GARNET("#C0504D"),//暗红
		GREEN("#9BBB59")//绿色
		;
		private String _value;
		public String value()
	    {
	      return _value;
	    }
		private Color(String value){
			this._value=value;
		}
	}
	
	/**
	 * Y轴类型枚举
	 * @author bob
	 *
	 */
	public static enum TypeY{
		TYPEY_MONEY("1"),//金额类型
		TYPEY_NUMBER("2")//数字类型
		;
		private String _value;
		public String value()
	    {
	      return _value;
	    }
		private TypeY(String value){
			this._value=value;
		}
	}
	/**
	 * 图表类型枚举
	 * @author bob
	 *
	 */
	public static enum Type{
		TYPE_1("1"),//折线图
		TYPE_2("2")//柱状图
		;
		private String _value;
		public String value()
	    {
	      return _value;
	    }
		private Type(String value){
			this._value=value;
		}
	}
	
	/**
	 * 查询控件
	 */
	public static String WHERE="where";
	/**
	 * 筛选控件枚举
	 * @author bob
	 *
	 */
	public static enum Where{
		WHERE_TIME("1"),//时间周期控件
		WHERE_TIME_MARKET("2"),//时间周期控件+市场选择
		WHERE_TIME_USERSELECT("3"),//时间周期控件+用户选择
		WHERE_TIME_MARKET_USER("4"),//时间周期控件+市场选择+用户选择
		WHERE_TIME_MARKET_USER_RE("5")//时间周期控件+市场选择+用户选择+注册来源
		;
		private String _value;
		public String value()
	    {
	      return _value;
	    }
		private Where(String value){
			this._value=value;
		}
	}
	
	/** 
	*字符串的日期格式的计算 
	 * @throws java.text.ParseException 
	*/  
	    public static int daysBetween(String smdate,String bdate) throws java.text.ParseException{  
	        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
	        Calendar cal = Calendar.getInstance();    
	        cal.setTime(sdf.parse(smdate));    
	        long time1 = cal.getTimeInMillis();                 
	        cal.setTime(sdf.parse(bdate));    
	        long time2 = cal.getTimeInMillis();         
	        long between_days=(time2-time1)/(1000*3600*24);  
	            
	       return Integer.parseInt(String.valueOf(between_days));     
	    }  
	    /**
	    * 月份计算
	    * @param smdate 新日期 bdate老日期
	    * @return Integer
	    */
	    public static Integer monthBetween(String smdate,String bdate) {
	      
	    	  SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM");
	          Calendar c = Calendar.getInstance();
	          Integer result=null;
	          try {
				c.setTime(sdf.parse(smdate));
			
	          int year1 = c.get(Calendar.YEAR);
	          int month1 = c.get(Calendar.MONTH);
	           
	          c.setTime(sdf.parse(bdate));
	          int year2 = c.get(Calendar.YEAR);
	          int month2 = c.get(Calendar.MONTH);
	           
	         
	          if(year1 == year2) {
	              result = month1 - month2;
	          } else {
	              result = 12*(year1 - year2) + month1 - month2;
	          }
	        
	          } catch (ParseException e) {
					e.printStackTrace();
				}
	          return result;
	    }
	 
}
