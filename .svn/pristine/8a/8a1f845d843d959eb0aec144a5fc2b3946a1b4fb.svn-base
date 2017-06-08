package cn.gdeng.nst.util.server;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.aliyun.oss.OSSClient;


/**
 * 文件上传工具类
 * 
 * @author 李冬
 * @time 2015年10月15日 下午4:22:01
 */
public class FileUploadUtil {
	/**
	 * 生成UUID。
	 * 
	 * @return
	 */
	public static String generateUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 根据日期创建图片上传路径
	 * 
	 * @param webRoot
	 * @return
	 * @author 李冬
	 * @time 2015年10月15日 下午4:27:22
	 */
	public static String createDatePath(String fileRoot) {
		if (!fileRoot.endsWith("\\") && !fileRoot.endsWith("/")) {
			fileRoot += "/";
		}
		// 根据日期创建图片上传路径
		Calendar calendar = Calendar.getInstance();
		String yf = String.valueOf(calendar.get(Calendar.YEAR)) + "/";
		File f1 = new File(fileRoot + yf);
		if (!f1.exists()) {
			f1.mkdirs();
		}
		String mf = String.valueOf(calendar.get(Calendar.MONTH) + 1) + "/";
		File f2 = new File(fileRoot + yf + mf);
		if (!f2.exists()) {
			f2.mkdirs();
		}
		String df = String.valueOf(calendar.get(Calendar.DATE)) + "/";
		File f3 = new File(fileRoot + yf + mf + df);
		if (!f3.exists()) {
			f3.mkdirs();
		}
		return yf + mf + df;
	}

	/**
	 * 上传图片，同时根据上传的图片生成多个不同尺寸的图片，并返回上传后的图片的路径.目前提供的尺寸包括650*650,400*400,160*160, 120*120,60*60,见配置文件upload.properties
	 * 
	 * @param data
	 *            图片二进制流
	 * @param fileName
	 *            图片名称
	 * @param fileUploadUrl
	 *            配置定义的上传根O路径
	 * @param imgSize
	 *            配置定义的图片尺寸字符串
	 * @return 返回图片上传后的地址：http://wwww.domain.com/img/xxx.jpg,其他尺寸的图片地址为http://wwww .domain.com/img/xxx650_650.jpg,http://wwww .domain.com/img/xxx400_400.jpg等
	 * @author 李冬
	 * @time 2015年10月15日 下午4:18:27
	 */
	public static String uploadImgfile(byte[] data, String fileName, String fileUploadUrl, String imgSize) {
		String uploadUrl = null;
		OutputStream out = null;
		// 分割字符串，获取文件后缀（类型）
		String str[] = fileName.split("\\.");
		String[][] size = getImgSizes(imgSize);
		String retPath = null;
		try {
			// 构建文件路径及名称O
			fileName = generateUUID();
			retPath = createDatePath(fileUploadUrl);
			uploadUrl = fileUploadUrl+ "/" + retPath + fileName + "." + str[str.length - 1];
			System.out.println("########################"+uploadUrl);
			// 输出路径
			out = new BufferedOutputStream(new FileOutputStream(uploadUrl), data.length);
			// 写入文件
			out.write(data);
			out.flush();
			// 生成各尺寸图片缩略图
			for (int i = 0; i < size.length; i++) {
				createThumbnail(uploadUrl, fileUploadUrl + "/" + createDatePath(fileUploadUrl) + fileName + size[i][0] + "_" + size[i][1] + "." + str[str.length - 1],
						Integer.valueOf(size[i][0]), Integer.valueOf(size[i][1]));
			}
		} catch (Exception e) {

		} finally {
			
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("******************"+retPath + fileName + "." + str[str.length - 1]);
		return retPath + fileName + "." + str[str.length - 1];
	}

	/**
	 * 上传文件，并返回文件的路径
	 * 
	 * @param imgInputStream
	 * @return
	 * @author 李冬
	 * @time 2015年10月15日 下午4:18:27
	 */
	public static String uploadfile(InputStream inputStream, String fileName, String fileUploadUrl) {
		String uploadUrl = null;
		OutputStream out = null;
		String retPath = null;
		// 分割字符串，获取文件后缀（类型）
		String str[] = fileName.split("\\.");
		try {
			// 构建文件路径及名称
			fileName = generateUUID();
			retPath = createDatePath(fileUploadUrl);
			uploadUrl = fileUploadUrl + retPath + fileName + "." + str[str.length - 1];
			// 输出路径
			out = new BufferedOutputStream(new FileOutputStream(uploadUrl), inputStream.available());
			byte[] buffer = new byte[1024];
			// 写入文件
			while (inputStream.read(buffer) > 0) {
				out.write(buffer);
			}
			out.flush();
		} catch (Exception e) {

		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return retPath;
	}

	/**
	 * 按源图比例缩放，生成高质量缩略图
	 * 
	 * @param imageFile
	 *            源图文件
	 * @param thumbnailFile
	 *            要保存到的缩略图文件
	 * @param maxWidth
	 *            缩略图的最大宽度
	 * @param maxHeight
	 *            缩略图的最大高度
	 * @throws IOException
	 *             读取源图imageFile出错，或者用户指定的thumbnailFile无法被创建时
	 */
	private static void createThumbnail(String imageFileS, String thumbnailFileS, int maxWidth, int maxHeight) throws IOException {
		File imageFile = new File(imageFileS);
		File thumbnailFile = new File(thumbnailFileS);
		if (maxWidth <= 0) {
			throw new IllegalArgumentException("maxWidth must > 0");
		}
		if (maxHeight <= 0) {
			throw new IllegalArgumentException("maxHeight must > 0");
		}

		try {
			BufferedImage image = ImageIO.read(imageFile);

			/* 源图宽和高 */
			int imageWidth = image.getWidth();
			int imageHeight = image.getHeight();
			if (maxWidth >= imageWidth && maxHeight >= imageHeight) {
				InputStream in = null;
				OutputStream out = null;
				// System.out.println(src.getAbsolutePath()+":::::::::::::::::::::::"+dst.getAbsolutePath());
				in = new BufferedInputStream(new FileInputStream(imageFileS), 1024);
				out = new BufferedOutputStream(new FileOutputStream(thumbnailFileS), 1024);
				byte[] buffer = new byte[1024];
				while (in.read(buffer) > 0) {
					out.write(buffer);
				}
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
				return;
			}

			/* 按比例缩放图像 */
			double scaleZ = (double) imageWidth / imageHeight;
			if (scaleZ > 0) {
				imageWidth = maxWidth;
				imageHeight = (int) (maxWidth / scaleZ);
			} else {
				imageWidth = (int) (maxHeight * scaleZ);
				imageHeight = maxHeight;
			}

			/* 根据源图和缩略图宽高生成一张图片？ */
			ImageFilter filter = new java.awt.image.AreaAveragingScaleFilter(imageWidth, imageHeight);
			ImageProducer producer = new FilteredImageSource(image.getSource(), filter);
			Image newImage = Toolkit.getDefaultToolkit().createImage(producer);
			ImageIcon imageIcon = new ImageIcon(newImage);
			Image scaleImage = imageIcon.getImage();

			BufferedImage thumbnail = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
			Graphics2D g2d = thumbnail.createGraphics();
			g2d.drawImage(scaleImage, 0, 0, null);
			g2d.dispose();

			ImageIO.write(thumbnail, "jpeg", thumbnailFile);

		} catch (IOException e) {
			throw new IOException("Connot create thumbnail, Please check 'imageFile' or 'thumbnailFile'!");
		}
	}

	/**
	 * 根据配置文件获取生成图片的各尺寸
	 * 
	 * @param imgSize
	 *            配置文件中定义的size字符串
	 * @return
	 * @author 李冬
	 * @time 2015年10月15日 下午7:37:52
	 */
	public static String[][] getImgSizes(String imgSize) {
		// String imgSize = getInstance().getImgUploadSize();
		// 图片尺寸配置信息第一次分割
		String[] size0 = imgSize.split(";");
		String[][] temp = new String[size0.length][2];
		for (int i = 0; i < size0.length; i++) {
			// 图片尺寸配置信息第二次分割
			String[] size1 = size0[i].split(",");
			temp[i][0] = size1[0];// width
			temp[i][1] = size1[1];// height
		}
		return temp;
	}
	
	/**
	 * 获取图片完整路径
	 * @param fileHost 请求图片服务器地址 如：http://IP:端口/
	 * @param file 文件目录地址 如：2016/08/16/temp.jpg
	 * @return
	 */
	public static String getFullFilePath(String fileHost,String file){
	    return fileHost + file;
	}

	/**
	 * 阿里云服务器文件上传
	 * @param endpoint
	 * @param accessKeyId
	 * @param accessKeySecret
	 * @param bucketName
	 * @param path
	 * @param fileName
	 * @param data
	 * @return
	 */
	public static String uploadFile(String endpoint, String accessKeyId, String accessKeySecret, 
			String bucketName, String path, String fileName, byte[] data){
	    // 创建OSSClient实例
	    OSSClient ossClient = new OSSClient(endpoint, accessKeyId,accessKeySecret);
	    // 上传
	    String str[] = fileName.split("\\.");
	    String newFileName = generateUUID();
	    // 查看上传结果  http://gudeng-test.oss-cn-shenzhen.aliyuncs.com/nst.dev.img001.txt
	    
	    // String key = path + newFileName + "." + str[str.length-1];
	    // 时间：2016-08-27， 图片路径格式修改：年/月/日/图片名称
	    String key = createDatePath("/") + newFileName + "." + str[str.length-1];
	    ossClient.putObject(bucketName, key, new ByteArrayInputStream(data));
	    // 关闭client
	    ossClient.shutdown();
	    return key;
	}
}
