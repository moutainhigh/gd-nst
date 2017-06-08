package cn.gdeng.nst.util.server.bo;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import cn.gdeng.nst.util.server.jodis.JodisTemplate;
import cn.gdeng.nst.util.web.api.SerializeUtil;

/**
 * aop拦截：缓存处理
 * @author wwj
 * 
 */
@Component
@Aspect
public class RedisAopUtil {
	
	private JodisTemplate jodisTemplate;
	
	/**----------------查询：缓存处理--------------------**/
	
	/*********************************地区start*****************************/
	/**一级地区*/
	public static final String GET_AREA_TOP_ALL="execution(* cn.gdeng.nst.util.server.bo.CacheBo.listTopArea(..))";
	/**一级地区直辖市*/
	public static final String GET_AREA_TOP_DIRECTLYCITY="execution(* cn.gdeng.nst.util.server.bo.CacheBo.listTopDirectlyCity(..))";
	/**一级地区自治区*/
	public static final String GET_AREA_TOP_REGION="execution(* cn.gdeng.nst.util.server.bo.CacheBo.listTopRegion(..))";
	/**一级地区省份(不包括港澳)*/
	public static final String AREA_TOP_PROVINCE="execution(* cn.gdeng.nst.util.server.bo.CacheBo.listTopProvince(..))";
	/**地区*/
	public static final String GET_AREA_AREAID="execution(* cn.gdeng.nst.util.server.bo.CacheBo.getAreaById(..))";
	/**根据名称获取地区*/
	public static final String GET_AREA_AREANAME="execution(* cn.gdeng.nst.util.server.bo.CacheBo.getAreaByName(..))";
	/**下级地区*/
	public static final String GET_AREA_CHILD_AREAID="execution(* cn.gdeng.nst.util.server.bo.CacheBo.listChildArea(..))";
	/**下级地区树*/
	public static final String GET_AREA_TREE_AREAID="execution(* cn.gdeng.nst.util.server.bo.CacheBo.getAreaChildTree(..))";
	/**获取所有省份城市*/
	public static final String GET_AREA_ALL_PROVINCE_CITY="execution(* cn.gdeng.nst.util.server.bo.CacheBo.getAllProvinceCity(..))";
	/**一级地区树（省份：附加判断是否有下级）*/
	public static final String GET_PARENT_TREE="execution(* cn.gdeng.nst.util.server.bo.CacheBo.getParentTree(..))";
	/**删除地区***/
	public static final String DELETE_AREA_AREAID="execution(* cn.gdeng.nst.util.server.bo.CacheBo.deleteAreaCacheById(..))";
	

	/*********************************地区end*****************************/
	
	/*********************************会员start*****************************/
	/**会员id*/
	public static final String GET_MEMBER_ID="execution(* cn.gdeng.nst.util.server.bo.CacheBo.getMemberById(..))";
	/**会员手机号*/
	public static final String GET_MEMBER_MOBILE="execution(* cn.gdeng.nst.util.server.bo.CacheBo.getMemberByMobile(..))";
	/**会员账号*/
	public static final String GET_MEMBER_ACCOUNT="execution(* cn.gdeng.nst.util.server.bo.CacheBo.getMemberByAccount(..))";
	/***删除会员*/
	public static final String DELETE_MEMBER_ID="execution(* cn.gdeng.nst.util.server.bo.CacheBo.deleteMemberCacheById(..))";
	
	/*********************************会员start*****************************/
	
	/**---------------查询缓存start----------------*/
	
	/*********************************地区start*****************************/
	//地区
	@Around(GET_AREA_TOP_ALL)
	public Object areaTopAllAround(ProceedingJoinPoint joinPoint) {
		
		Object obj=null;
		Object[] args = joinPoint.getArgs();
		if(args.length>0){
			String key = getRedisKey(RedisConstant.RedisKey.AREA_TOP_ALL.value,"");
			obj=readWriteRedis(obj,key,joinPoint);
		}
		return obj;
	}
	
	//取一级地区直辖市
	@Around(GET_AREA_TOP_DIRECTLYCITY)
	public Object areaTopDirectlyCityAround(ProceedingJoinPoint joinPoint) {
		
		Object obj=null;
		Object[] args = joinPoint.getArgs();
		if(args.length>0){
			String key = getRedisKey(RedisConstant.RedisKey.AREA_TOP_DIRECTLYCITY.value,"");
			obj=readWriteRedis(obj,key,joinPoint);
		}
		return obj;
	}
	
	//取一级地区省份(不包括港澳)
	@Around(AREA_TOP_PROVINCE)
	public Object areaTopProvinceAround(ProceedingJoinPoint joinPoint) {
		
		Object obj=null;
		Object[] args = joinPoint.getArgs();
		if(args.length>0){
			String key = getRedisKey(RedisConstant.RedisKey.AREA_TOP_PROVINCE.value,"");
			obj=readWriteRedis(obj,key,joinPoint);
		}
		return obj;
	}
	
	//取一级地区自治区
	@Around(GET_AREA_TOP_REGION)
	public Object areaTopRegionAround(ProceedingJoinPoint joinPoint) {
		
		Object obj=null;
		Object[] args = joinPoint.getArgs();
		if(args.length>0){
			String key = getRedisKey(RedisConstant.RedisKey.AREA_TOP_REGION.value,"");
			obj=readWriteRedis(obj,key,joinPoint);
		}
		return obj;
	}
	
	//所有省份城市
	@Around(GET_AREA_ALL_PROVINCE_CITY)
	public Object AREA_ALL_PROVINCE_CITYAround(ProceedingJoinPoint joinPoint) {
		Object obj=null;
		Object[] args = joinPoint.getArgs();
		if(args.length>0){
			String key = getRedisKey(RedisConstant.RedisKey.AREA_ALL_PROVINCE_CITY.value,"");
			obj=readWriteRedis(obj,key,joinPoint);
		}
		return obj;
	}
	
	//地区
	@Around(GET_AREA_AREAID)
	public Object areaAround(ProceedingJoinPoint joinPoint) {
		
		Object obj=null;
		Object[] args = joinPoint.getArgs();
		if(args.length>0){
			String key = getRedisKey(RedisConstant.RedisKey.AREA_AREAID.value,args[0].toString());
			obj=readWriteRedis(obj,key,joinPoint);
		}
		return obj;
	}
	
	//根据名称获取地区
	@Around(GET_AREA_AREANAME)
	public Object getAreaByNameAround(ProceedingJoinPoint joinPoint) {
		
		Object obj=null;
		Object[] args = joinPoint.getArgs();
		if(args.length>0){
			String reidsKeySuffix = args[0].toString();
			//通过名称获取地区的service方法被重载了，其中一个是两个参数的。
			if (args.length > 1) {
				reidsKeySuffix += args[1].toString();
			}
			String key = getRedisKey(RedisConstant.RedisKey.AREA_AREAID.value, reidsKeySuffix);
			obj=readWriteRedis(obj,key,joinPoint);
		}
		return obj;
	}
	
	//下级地区
	@Around(GET_AREA_TREE_AREAID)
	public Object areaTreeAround(ProceedingJoinPoint joinPoint) {
		
		Object obj=null;
		Object[] args = joinPoint.getArgs();
		if(args.length>0){
			String key = getRedisKey(RedisConstant.RedisKey.AREA_TREE_AREAID.value,args[0].toString());
			obj=readWriteRedis(obj,key,joinPoint);
		}
		return obj;
	}
	
	//下级地区树
	@Around(GET_AREA_CHILD_AREAID)
	public Object areaChildAround(ProceedingJoinPoint joinPoint) {
		
		Object obj=null;
		Object[] args = joinPoint.getArgs();
		if(args.length>0){
			String key = getRedisKey(RedisConstant.RedisKey.AREA_CHILD_AREAID.value,args[0].toString());
			obj=readWriteRedis(obj,key,joinPoint);
		}
		return obj;
	}
	
	//一级地区（附加isParent字段）
	@Around(GET_PARENT_TREE)
	public Object getParentTreeAround(ProceedingJoinPoint joinPoint){
		Object obj=null;
		Object[] args = joinPoint.getArgs();
		if(args.length>0){
			String key = getRedisKey(RedisConstant.RedisKey.AREA_PARENT_TREEID.value,"");
			obj=readWriteRedis(obj,key,joinPoint);
		}
		return obj;
	}
	
	//删除地区
	@Around(DELETE_AREA_AREAID)
	public void areaDeleteAround(ProceedingJoinPoint joinPoint) {
		
		Object[] args = joinPoint.getArgs();
		if(args.length>0){
			String key = getRedisKey(RedisConstant.RedisKey.AREA_TOP_ALL.value,"");
			String key2 = getRedisKey(RedisConstant.RedisKey.AREA_AREAID.value,args[0].toString());
			String key3 = getRedisKey(RedisConstant.RedisKey.AREA_TREE_AREAID.value,args[0].toString());
			String key4 = getRedisKey(RedisConstant.RedisKey.AREA_CHILD_AREAID.value,args[0].toString());
			String key5 = getRedisKey(RedisConstant.RedisKey.AREA_PARENT_TREEID.value,"");
			//清掉缓存
			delRedis(key);
			delRedis(key2);
			delRedis(key3);
			delRedis(key4);
			delRedis(key5);
		}
	}
	
	/*********************************地区end*****************************/
	
	/*********************************会员Start*****************************/
	@Around(GET_MEMBER_ID)
	public Object memberIdAround(ProceedingJoinPoint joinPoint) {
		
		Object obj=null;
		Object[] args = joinPoint.getArgs();
		if(args.length>0){
			String key = getRedisKey(RedisConstant.RedisKey.MEMBER_ID.value,args[0].toString());
			obj=readWriteRedis(obj,key,joinPoint);
		}
		return obj;
	}
	
	@Around(GET_MEMBER_MOBILE)
	public Object memberMobileAround(ProceedingJoinPoint joinPoint) {
		
		Object obj=null;
		Object[] args = joinPoint.getArgs();
		if(args.length>0){
			String key = getRedisKey(RedisConstant.RedisKey.MEMBER_MOBILE.value,args[0].toString());
			obj=readWriteRedis(obj,key,joinPoint);
		}
		return obj;
	}
	
	@Around(GET_MEMBER_ACCOUNT)
	public Object memberAccountAround(ProceedingJoinPoint joinPoint) {
		
		Object obj=null;
		Object[] args = joinPoint.getArgs();
		if(args.length>0){
			String key = getRedisKey(RedisConstant.RedisKey.MEMBER_ACCOUNT.value,args[0].toString());
			obj=readWriteRedis(obj,key,joinPoint);
		}
		return obj;
	}
	
	//删除地区
	@Around(DELETE_MEMBER_ID)
	public void memberDeleteAround(ProceedingJoinPoint joinPoint) {
		
		Object[] args = joinPoint.getArgs();
		if(args.length>0){
			String key = getRedisKey(RedisConstant.RedisKey.MEMBER_ID.value,args[0].toString());
			String key2 = getRedisKey(RedisConstant.RedisKey.MEMBER_MOBILE.value,args[0].toString());
			String key3 = getRedisKey(RedisConstant.RedisKey.MEMBER_ACCOUNT.value,args[0].toString());
			//清掉缓存
			delRedis(key);
			delRedis(key2);
			delRedis(key3);
		}
	}
	/*********************************会员end*****************************/
	
	
	
	/**---------------end----------------*/
	
	//读写redis缓存
	private Object readWriteRedis(Object obj,String key,ProceedingJoinPoint joinPoint){
		
		try{
			obj=jodisTemplate.getObject(key.getBytes());
		}catch(Exception e){
			//处理redis服务器异常
			e.printStackTrace();
			obj= null;
		}
		if(obj != null){
			return obj;
		}else{
			try{
				obj = joinPoint.proceed(joinPoint.getArgs());
				if(null != obj){
					try{
						jodisTemplate.set(key.getBytes(), SerializeUtil.serialize(obj));
						//System.out.println("写入key"+key);
					}catch(Exception e){
						//处理redis服务器异常
						e.printStackTrace();
					}
				}
			} catch (Throwable e) {
				//处理hessian异常
				e.printStackTrace();
				obj = null;
			}
		}
		return obj;
	}
	
	//清除缓存
	private void delRedis(String key){
		try{
			jodisTemplate.del(key);
			//System.out.println("除件"+key);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//获取key
	private String getRedisKey(String preFix, String id){

		return preFix+id;
	}

	public JodisTemplate getJodisTemplate() {
		return jodisTemplate;
	}

	public void setJodisTemplate(JodisTemplate jodisTemplate) {
		this.jodisTemplate = jodisTemplate;
	}
	
}
