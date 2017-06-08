package cn.gdeng.nst.web.util.test;

import java.io.ByteArrayInputStream;

import com.aliyun.oss.OSSClient;

/**
 * 阿里云上传测试
 * @author Administrator
 *
 */
public class UploadTest {

	public static void main(String[] args) {
	    // endpoint以杭州为例，其它region请按实际情况填写
	    String endpoint = "oss-cn-shenzhen.aliyuncs.com";
	    //oss-cn-shenzhen-internal.aliyuncs.com
	    String accessKeyId = "cfwNrd7F7oqRVpt1";
	    String accessKeySecret = "Rb5itBHiaPK5BWtl1RL0qrm7CvRDVq";
	    // 创建OSSClient实例
	    OSSClient ossClient = new OSSClient(endpoint, accessKeyId,accessKeySecret);
	    // 上传
	    byte[] content = "Hello OSS".getBytes();
	    ossClient.putObject("gudeng-test", "nst.dev.img001.txt", new ByteArrayInputStream(content));
	    //查看上传结果  http://gudeng-test.oss-cn-shenzhen.aliyuncs.com/nst.dev.img001.txt
	    // 关闭client
	    ossClient.shutdown();
	}

}
