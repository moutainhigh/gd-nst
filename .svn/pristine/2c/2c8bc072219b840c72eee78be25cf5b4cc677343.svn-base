package cn.gdeng.nst.web.controller.pub;

import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gdeng.nst.enums.MsgCons;
import cn.gdeng.nst.util.server.FileUploadUtil;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.Base64;
import cn.gdeng.nst.util.web.api.BizException;
import cn.gdeng.nst.util.web.api.GdProperties;

/**
 * 上传文件公共控制器
 * @author yangjj
 *
 */
@Controller
@RequestMapping("v1/upload")
public class FileUploadController{
	
	@Autowired
	private GdProperties gdProperties;

	@ResponseBody
	@RequestMapping(value="img",produces="application/json;charset=utf-8")
	public Object uploadImg(String file) throws Exception{
	    if(StringUtils.isNotEmpty(file)){
	        byte[] data = Base64.decode(file);
	        
	        // 上传文件大小校验（最大限制20M）
	        if ( data != null && data.length > 20 * 1024 * 1024) {
	        	return new ApiResult<String>().withError(MsgCons.C_2001, MsgCons.M_20001);
	        }
	        
	        String fileName = "temp.jpg";
	        Properties properties = gdProperties.getProperties();
	        String endpoint = properties.getProperty("gd.upload.endpoint");
	        String accessKeyId = properties.getProperty("gd.upload.accessKeyId");
	        String accessKeySecret = properties.getProperty("gd.upload.accessKeySecret");
	        String bucketName = properties.getProperty("gd.upload.bucketName");
	        String path = properties.getProperty("gd.uplaod.path")+".api/";
	        String host = properties.getProperty("gd.uplaod.host");
	        String masterPicPath = FileUploadUtil.uploadFile(endpoint, accessKeyId, accessKeySecret, bucketName, path, fileName, data);
	        ApiResult<String> result = new ApiResult<String>(host+masterPicPath, MsgCons.C_10000, MsgCons.M_10000);
            return result;
	    }else{
            throw new BizException(MsgCons.C_2000, MsgCons.M_2000);
        }	  
	}
}
