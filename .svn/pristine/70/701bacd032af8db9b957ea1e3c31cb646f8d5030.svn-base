package cn.gdeng.nst.admin.controller.admin;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.gudeng.framework.core2.GdLogger;
import com.gudeng.framework.core2.GdLoggerFactory;

import cn.gdeng.nst.admin.controller.right.AdminBaseController;
import cn.gdeng.nst.admin.service.admin.AppVersionService;
import cn.gdeng.nst.entity.nst.AppVersion;
import cn.gdeng.nst.util.admin.web.Constant.ActStatus;

@Controller
@RequestMapping("app")
public class AppStatisticsController extends AdminBaseController {

	private static final GdLogger logger = GdLoggerFactory.getLogger(AppStatisticsController.class);
	private static final String APP_RECORD_TYPE_FOR_COMPANY = "1";
	private static final String APP_RECORD_TYPE_FOR_MEMBER = "2";
	
	@Reference
	public AppVersionService appVersionService;
   
	@RequestMapping("list")
	public String listInit(){
		return "appversion/versionlist";
	}
	
	/**
	 * 查看版本列表
	 * @param appVersion
	 * @param pagerBean
	 * @return
	 */
	@RequestMapping("version")
	@ResponseBody
	public Object list(AppVersion  appVersion,HttpServletRequest request ){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("type", appVersion.getType());
			map.put("num", appVersion.getNum());
			map.put("platform", appVersion.getPlatform());
			map.put("startTime", request.getParameter("publishTime_st"));
			map.put("endTime", request.getParameter("publishTime_en"));
			// 获取条件记录总数
			map.put("total", appVersionService.getAppVersionCount(map));
			// 设置分页参数
			setCommParameters(request, map);
			// 数据集合
			List<AppVersion> list = appVersionService.getAppVersionList(map);
			// rows键 存放每页记录 list
			map.put("rows", list);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.toJSONString(map,SerializerFeature.WriteDateUseDateFormat);

	}

	/**
	 * 删除版本
	 * @param idList
	 * @param pagerBean
	 * @return
	 */
	@RequestMapping("delete")
	@ResponseBody	
	public Object delete(HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			 String id=request.getParameter("id");
			 appVersionService.deleteAppVersion(id);
			 resultMap.put("code", "0");
			
		} catch (Exception e) {
			logger.trace(e.getMessage());
			resultMap.put("code", "-1");
		}
		return resultMap;
	}
	
	/**
	 * 新增初始化
	 * @return
	 */
	@RequestMapping("addinit")
	public String addinit(){
		
		return "appversion/versionadd";
	}
	/**
	 * 修改初始化
	 * @author fuzhuan 2015年1月27日 上午10:50:54
	 * <p>Title: editinit </p>
	 * <p>Description: </p>
	 * @return
	 */
	@RequestMapping("editinit")
	public String editinit(HttpServletRequest request){
		
		String id=request.getParameter("id");
		try {
			AppVersion version = appVersionService.getAppVersionById(id);
			this.putModel("version", version);
		} catch (Exception e) {
			this.putModel(ActStatus.STATUS, "系统异常");
			e.printStackTrace();
			return "app/versionedit";
		}
		return "appversion/versionedit";
	}
	/**
	 * 
	 * @author fuzhuan 2015年1月27日 下午2:53:20
	 * <p>Title: view </p>
	 * <p>Description: </p>
	 * @param request
	 * @return
	 */
	@RequestMapping("view")
	public String view(HttpServletRequest request){
		
		String id=request.getParameter("id");
		try {
			AppVersion version = appVersionService.getAppVersionById(id);
			this.putModel("version", version);
		} catch (Exception e) {
			this.putModel(ActStatus.STATUS, "系统异常");
			e.printStackTrace();
			return "app/versionview";
		}
		return "appversion/versionview";
	}
	/**
	 * 新增版本
	 * @param appVersion
	 * @return
	 */
	@RequestMapping("add")
	@ResponseBody
	public Object add(HttpServletRequest request,AppVersion  appVersion){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			appVersion.setCreateUserId(this.getUser(request).getUserID());
			AppVersion lastapp = appVersionService.getLastAppVersion(appVersion.getType(), appVersion.getPlatform());
			if(lastapp!=null){
				if(compareVersion(lastapp.getNum(),appVersion.getNum())>=0){
					resultMap.put("code", "-1");
					return resultMap;
				}
			}
			appVersionService.persistAppVersion(appVersion);
			resultMap.put("code", "success");
		}catch(Exception e){
			logger.trace(e.getMessage());
			e.printStackTrace();
			resultMap.put("code", "fail");
		}
		return resultMap;
	}
	/**
	 * 
	 * @author fuzhuan 2015年1月27日 上午11:34:18
	 * <p>Title: edit </p>
	 * <p>Description: </p>
	 * @param request
	 * @param appVersion
	 * @return
	 */
	@RequestMapping("edit")
	@ResponseBody
	public Object edit(HttpServletRequest request,AppVersion  appVersion){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			appVersion.setUpdateUserId(this.getUser(request).getUserID());
			appVersionService.updateAppVersion(appVersion);
			resultMap.put("code", "success");
		}catch(Exception e){
			logger.trace(e.getMessage());
			resultMap.put("code", "fail");
		}
		return resultMap;
	}
	/**
	 * 读取远程APP
	 * @author songhui
	 * @date 创建时间：2014年9月11日 下午4:40:59
	 * @param strUrl
	 * @param fileName
	 * @throws IOException
	 *
	 */
	public boolean getRemoteFile(String strUrl,String fileName){
		try{
			URL url = new URL(strUrl); 
			HttpURLConnection conn = (HttpURLConnection) url.openConnection(); 
			DataInputStream input = new DataInputStream(conn.getInputStream()); 
			DataOutputStream output = new DataOutputStream(new FileOutputStream(fileName)); 
			byte[] buffer = new byte[1024 * 8]; 
			int count = 0; 
			while ((count = input.read(buffer)) > 0) { 
				output.write(buffer, 0, count); 
			} 
			output.close(); 
			input.close();
		}catch(Exception e){
			e.printStackTrace();
			logger.info("APP文件读取失败！");
			return false;
		}
		return true;
	}
	
	/**
	 * 比较版本号的大小,前者大则返回一个正数,后者大返回一个负数,相等则返回0
	 * @param version1
	 * @param version2
	 * @return
	 */
	public int compareVersion(String version1, String version2) throws Exception {
		if (version1 == null || version2 == null) {
			throw new Exception("compareVersion error:illegal params.");
		}
		String[] versionArray1 = version1.split("\\.");//注意此处为正则匹配，不能用"."；
		String[] versionArray2 = version2.split("\\.");
		int idx = 0;
		int minLength = Math.min(versionArray1.length, versionArray2.length);//取最小长度值
		int diff = 0;
		while (idx < minLength
				&& (diff = versionArray1[idx].length() - versionArray2[idx].length()) == 0//先比较长度
				&& (diff = versionArray1[idx].compareTo(versionArray2[idx])) == 0) {//再比较字符
			++idx;
		}
		//如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大；
		diff = (diff != 0) ? diff : versionArray1.length - versionArray2.length;
		return diff;
	}	
}
