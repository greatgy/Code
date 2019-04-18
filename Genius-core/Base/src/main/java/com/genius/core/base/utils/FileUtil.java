package com.genius.core.base.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.genius.core.base.conf.BaseSysConf;
import com.genius.core.base.constant.BaseStrDict;
import com.google.common.base.Charsets;
import com.google.common.io.Files;

/**
 * @author GreatHost
 *
 */
public class FileUtil extends BaseUtil {

	private static Logger log = LoggerFactory.getLogger(FileUtil.class);

	private static final String format_error_deletefile = "Error deleting file:%s";
	
	private static final String image_type = "image";
	
	/**
	 * 直接上传文件
	 * @param file
	 * @return 返回相对路径
	 */
	public static String uploadFile(MultipartFile file, String dir) {
		String siteBasePath = BaseSysConf.FileSiteBasePath;
		String fullpath = transferFile(file, dir, siteBasePath);
		if (StringUtils.isNotEmpty(fullpath)) {
			return fullpath.replace(siteBasePath, "");
		} else {
			return "";
		}
	}

	/**
	 * 直接上传文件，并替换存在的文件
	 * @param file 一套上传的文件
	 * @param dir 路径，末尾的/可有可无
	 * @param filename 文件名
	 * @return
	 * @author: Architect.bian
	 * 2014-6-14 上午11:40:40
	 */
	public static String uploadFile(MultipartFile file, String dir, String filename) {
		if (StrUtil.isEmpty(dir)) {
			dir = BaseSysConf.Separator_Directory;
		} else if (!dir.endsWith(BaseSysConf.Separator_Directory)) {
			dir += BaseSysConf.Separator_Directory;
		}
		return uploadFileToPath(file, dir + filename);
	}
	

	/**
	 * 直接上传文件，并替换存在的文件
	 * @param file 要上传的文件
	 * @param filepath 文件路径如,/files/download/report.exsl
	 * @return
	 * @author: Architect.bian
	 * 2014-6-14 上午11:41:24
	 */
	public static String uploadFileToPath(MultipartFile file, String filepath) {
		String siteBasePath = BaseSysConf.FileSiteBasePath;
		String fullpath = transferFileToPath(file, filepath, siteBasePath);
		if (StringUtils.isNotEmpty(fullpath)) {
			return fullpath.replace(siteBasePath, "");
		} else {
			return "";
		}
	}
	
	/**
	 * 直接上传图片
	 * @param file
	 * @return 返回相对路径
	 */
	public static String uploadImg(MultipartFile file, String path) {
		return uploadImg(file, path, BaseSysConf.ImgSiteBasePath);
	}
	
	/**
	 * 直接上传图片
	 * @param file
	 * @return 返回相对路径
	 */
	public static String uploadImg(MultipartFile file, String path, String ImgBasePath) {
		String fullpath = transferImg(file, path);
		if (StringUtils.isNotEmpty(fullpath)) {
			return fullpath.replace(ImgBasePath, "");
		} else {
			return "";
		}
	}
	
	/**
	 * 上传图片到指定目录下
	 * @param file
	 * @param imgdir 
	 * @return 源文件保存路径
	 */
	private static String transferImg(MultipartFile file, String dir) {
		return transferFile(file, dir, BaseSysConf.ImgSiteBasePath);
	}
	
	/**
	 * 上传文件到指定目录下
	 * @param file
	 * @param imgdir 
	 * @return 源文件保存路径
	 */
	private static synchronized String transferFile(MultipartFile file, String dir, String siteBasePath) {
		String oldname = file.getOriginalFilename();
		String ext = getExtName(oldname);
		String pathname = createFilePathName(dir, ext, siteBasePath);
		File dest = new File(pathname);
		while (dest.exists()) {
			pathname = createFilePathName(dir, ext, siteBasePath);
			dest = new File(pathname);
		}
		try {
			if (!dest.getParentFile().exists()) {
				dest.mkdirs();
			}
			file.transferTo(dest);
			String fileType = file.getContentType();
			String[] type = fileType.split("\\/");
			if (type != null && type.length > 0 && image_type.equals(type[0])) {//判断是否是图片类型
				String imageType = ImgUtil.getImageFormat(pathname);
				if (StrUtil.isEmpty(ext)) {
					pathname = pathname + BaseStrDict.dot + imageType.toLowerCase();
				}
				dest.renameTo(new File(pathname));
				int angle = ImgUtil.getRotateAngle(new File(pathname));
				if (angle != 0) {
					pathname = ImgUtil.rotate(Double.valueOf(angle), dest.getAbsolutePath());
				}
			}
			return pathname;
		} catch (IllegalStateException | IOException e) {
			logException(log, e);
			return null;
		}
	}

	/**
	 * 返回扩展名
	 * @param oldname
	 * @return
	 * @author Architect.bian
	 * @createtime 2016-4-19 下午5:31:25
	 */
	public static String getExtName(String filename) {
		return filename.lastIndexOf(BaseStrDict.dot) != -1 ? filename.substring(filename.lastIndexOf(BaseStrDict.dot)).toLowerCase() : "";
	}
	
	/**
	 * 上传文件为指定文件，并替换原文件
	 * @param file
	 * @param filepath
	 * @param siteBasePath
	 * @return
	 * @author: Architect.bian
	 * 2014-6-14 上午11:24:52
	 */
	private static String transferFileToPath(MultipartFile file, String filepath, String siteBasePath) {
		String path = siteBasePath + filepath;
		File dest = new File(path);
		FileUtil.deleteQuietly(dest);
		try {
			if (!dest.getParentFile().exists()) {
				dest.mkdirs();
			}
			file.transferTo(dest);
			return path;
		} catch (IllegalStateException | IOException e) {
			logException(log, e);
			return null;
		}
	}

	/**
	 * 删除文件
	 * @param path
	 */
	public static boolean delete(String... files) {
		Logger log = LoggerFactory.getLogger(FileUtil.class);
		for (String path : files) {
			if (StringUtils.isNotEmpty(path)) {
				File file = new File(path);
				if (file.exists()) {
					if (!file.delete()) {
						log.error(String.format(format_error_deletefile, path));
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * [0]:原图,若为空则计算原图地址并删除
	 * @param titleimg
	 */
	public static boolean deleteImg(String... files) {
		Logger log = LoggerFactory.getLogger(FileUtil.class);
		boolean valid = true;
		if (files == null) {
			return valid;
		}
		for (String path : files) {
			if (StringUtils.isNotEmpty(path)) {
				File file = new File(BaseSysConf.ImgSiteBasePath + path);
				if (file.exists() && !StrUtil.isMatchAny(path, BaseSysConf.NotDeleteImgsRegex)) {
					if (!file.delete()) {
						log.error(String.format(format_error_deletefile, path));
					}
				}
			}
		}
		//删除原图
		if (files.length > 0 && files[0] != null && files[0].indexOf("_") > 0) {
			String orginalpath = files[0].substring(0, files[0].indexOf("_"));
			if (files[0].indexOf(".") > 0) {
				orginalpath += files[0].substring(files[0].indexOf("."));
			}
			File fileoriginal = new File(BaseSysConf.ImgSiteBasePath + orginalpath);
			if (fileoriginal.exists() && !StrUtil.isMatchAny(orginalpath, BaseSysConf.NotDeleteImgsRegex)) {
				if (!fileoriginal.delete()) {
					log.error(String.format(format_error_deletefile, orginalpath));
				}
			}
		}
		return valid;
	}

	/**
	 * @param file
	 * @param i
	 * @param j
	 * @return string[0] 原图
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	public static String[] resizeImage(MultipartFile file, String path, int width, int height) {
		int[][] sizes = {{width, height}};
		return resizeImage(file, path, sizes);
	}

	/**
	 * 直接对上传文件进行transfer&resize
	 * @param file
	 * @param sizes sizes[i][0]=width,sizes[i][1]=height
	 * @return string[0] 原图
	 */
	public static String[] resizeImage(MultipartFile file, String path, int[][] sizes) {
		return resizeImage(file, path, sizes, BaseSysConf.ImgSiteBasePath);
	}
	
	/**
	 * 直接对上传文件进行transfer&resize
	 * @param file
	 * @param sizes sizes[i][0]=width,sizes[i][1]=height
	 * @return string[0] 原图
	 */
	public static String[] resizeImage(MultipartFile file, String path, int[][] sizes, String imgbasepath) {
		String[] strs = new String[sizes.length + 1];
		String fullpath = transferFile(file, path, imgbasepath);
		if (StringUtils.isNotEmpty(fullpath)) {
			strs[0] = fullpath.replace(imgbasepath, "");
			for (int i = 0; i < sizes.length; i++) {
				if (ImgUtil.getImageWidth(fullpath) > sizes[i][0] || ImgUtil.getImageHeight(fullpath) > sizes[i][1]) {
					String img = ImgUtil.resizeImage(sizes[i][0], sizes[i][1], fullpath);
					strs[i + 1] = StringUtils.isEmpty(img) ? null : img.replace(imgbasepath, "");
				} else {
					String img = ImgUtil.compress(fullpath);//采用原图，直接压缩
					strs[i + 1] = StringUtils.isEmpty(img) ? null : img.replace(imgbasepath, "");
				}
			}
			return strs;
		}
		return null;
	}
	
	/**
	 * 直接对上传文件进行transfer&resize,指定压缩质量
	 * @param file
	 * @param path
	 * @param quality 最小0,最大100
	 * @param sizes sizes[i][0]=width,sizes[i][1]=height
	 * @return string[0] 原图
	 */
	public static String[] resizeImage(MultipartFile file, String path, double quality, int[][] sizes) {
		return resizeImage(file, path, quality, sizes, BaseSysConf.ImgSiteBasePath);
	}
	
	/**
	 * 直接对上传文件进行transfer&resize,指定压缩质量
	 * @param file
	 * @param path
	 * @param quality 最小0,最大100
	 * @param sizes sizes[i][0]=width,sizes[i][1]=height
	 * @return string[0] 原图
	 */
	public static String[] resizeImage(MultipartFile file, String path, double quality, int[][] sizes, String imgbasepath) {
		String[] strs = new String[sizes.length + 1];
		String fullpath = transferImg(file, path);
		if (StringUtils.isNotEmpty(fullpath)) {
			strs[0] = fullpath.replace(imgbasepath, "");
			for (int i = 0; i < sizes.length; i++) {
				if (ImgUtil.getImageWidth(fullpath) > sizes[i][0] || ImgUtil.getImageHeight(fullpath) > sizes[i][1]) {
					String img = ImgUtil.resizeImage(sizes[i][0], sizes[i][1], quality, fullpath);
					strs[i + 1] = StringUtils.isEmpty(img) ? null : img.replace(imgbasepath, "");
				} else {
					String img = ImgUtil.compress(quality, fullpath);//采用原图，直接压缩
					strs[i + 1] = StringUtils.isEmpty(img) ? null : img.replace(imgbasepath, "");
				}
			}
			return strs;
		}
		return null;
	}
	
	/**
	 * @param f
	 * @param width
	 * @param height
	 * @return string[0] 压缩后图 原图不返回
	 */
	public static String[] resizeImage(String siteurl, int width, int height) {
		int[][] sizes = {{width, height}};
		return resizeImage(siteurl, sizes);
	}

	/**
	 * 对某个文件进行resize
	 * @param file
	 * @param sizes
	 * @return
	 */
	public static String[] resizeImage(String imgurl, int[][] sizes) {
		return resizeImage(imgurl, sizes, BaseSysConf.ImgSiteBasePath);
	}
	
	
	/**
	 * 对某个文件进行resize
	 * @param file
	 * @param sizes
	 * @return
	 */
	public static String[] resizeImage(String imgurl, int[][] sizes, String imgbasepath) {
		String path = imgurl.contains(imgbasepath) ? imgurl : imgbasepath + imgurl;
		String[] strs = new String[sizes.length];
		if (StringUtils.isNotEmpty(path)) {
			for (int i = 0; i < sizes.length; i++) {
				if (ImgUtil.getImageWidth(path) > sizes[i][0] || ImgUtil.getImageHeight(path) > sizes[i][1]) {
					String img = ImgUtil.resizeImage(sizes[i][0], sizes[i][1], path);
					strs[i] = StringUtils.isEmpty(img) ? null : img.replace(imgbasepath, "");
				} else {
					String img = ImgUtil.compress(path);
					strs[i] = StringUtils.isEmpty(img) ? null : img.replace(imgbasepath, "");
				}
			}
			return strs;
		}
		return null;
	}

	/**
	 * @param string
	 * @return
	 */
	public static String gray(String imgpath) {
		File file = new File(BaseSysConf.ImgSiteBasePath + imgpath);
		if (file.exists()) {
			String img = ImgUtil.gray(file.getPath());
			return StringUtils.isEmpty(img) ? null : img.replace(BaseSysConf.ImgSiteBasePath, "");
		}
		return null;
	}

	/**
	 * 获得文件名称
	 * @param dir 
	 * @return
	 */
	@SuppressWarnings("unused")
	private static String createFilePathName(String dir, String ext) {
		return createFilePathName(dir, ext, BaseSysConf.ImgSiteBasePath);
	}
	
	/**
	 * 获得文件地址
	 * @param dir 
	 * @return
	 */
	private static String createFilePathName(String dir, String ext, String siteBasePath) {
		String filename = DateUtil.getNowForID() + StrUtil.getRandomString(3);
		return String.format("%s%s/%s%s", siteBasePath, dir, filename, ext);
	}

	/**
	 * @param dirdel
	 * @return
	 */
	public static List<String> getFiles(File dir) {
		return getFiles(dir, null);
	}

	/**
	 * 列出某个目录下filter后的所有文件
	 * @param dir
	 * @param filter
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-5-14 下午6:34:01
	 */
	public static List<String> getFiles(File dir, FileFilter filter) {
		List<String> paths = new ArrayList<>();
		File[] files = filter != null ? dir.listFiles(filter) : dir.listFiles();
		if (files != null) {
			for (File file : files) {
				if (file.isDirectory()) {
					paths.addAll(getFiles(file, filter));
				} else {
					paths.add(file.getPath());
				}
			}
		}
		return paths;
	}

	/**
	 * 给当前图片加水印
	 * @param orginalimg 相对路径
	 * @return 加水印后的图片地址(应与orginalimg相同)
	 */
	public static String waterMark(String orginalimg) {
		return waterMark(orginalimg, ImgUtil.southeast);
	}

	/**
	 * @param orginalimg
	 * @param southeast
	 * @return
	 */
	public static String waterMark(String orginalimgfullpath, String gravity) {
		String img = BaseSysConf.ImgSiteBasePath + orginalimgfullpath;
		if (ImgUtil.getImageHeight(img) > BaseSysConf.ImgWaterMarkMinSize && ImgUtil.getImageWidth(img) > BaseSysConf.ImgWaterMarkMinSize) {
			return ImgUtil.waterMark(img, BaseSysConf.ImgWaterMark, gravity).replace(BaseSysConf.ImgSiteBasePath, "");
		} else {
			return orginalimgfullpath;
		}
	}

	/**
	 * Deletes a file, never throwing an exception. If file is a directory, delete it and all sub-directories. 
	 * @param path
	 */
	public static boolean deleteQuietly(String path) {
		File file = new File(path);
		if (file.exists()) {
			return FileUtils.deleteQuietly(file);
		}
		return true;
	}
	
	/**
	 * Deletes a file, never throwing an exception. If file is a directory, delete it and all sub-directories. 
	 * @param path
	 */
	public static boolean deleteQuietly(File file) {
		if (file.exists()) {
			return FileUtils.deleteQuietly(file);
		}
		return true;
	}

	/**
	 * @param avatar
	 * @param x
	 * @param y
	 * @param string
	 * @param string2
	 * @return
	 */
	public static String cropImage(String path, int x, int y, int x2, int y2) {
		String img = BaseSysConf.ImgSiteBasePath + path;
		File file = new File(img);
		if (file.exists()) {
			return ImgUtil.cutImage(x, y, x2, y2, img);
		}
		return null;
	}

	/**
	 * @param file
	 * @return
	 * @throws IOException 
	 */
	public static String toString(File file) throws IOException {
		return Files.toString(file, Charsets.UTF_8);
	}

	/**
	 * @param json
	 * @param file
	 * @throws IOException 
	 */
	public static void write(String content, File file) throws IOException {
		Files.write(content, file, Charsets.UTF_8);
	}

	/**
	 * 将某个文件转成base64编码
	 * @param file
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-7-17 下午1:29:14
	 */
	public static String toBase64(String file) {
		InputStream in = null;
		byte[] data = null;
		//读取图片字节数组  
        try {
        	in = new FileInputStream(file);
        	data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			logException(log, e);
		}
        return toBase64(data);
	}
	
	/**
	 * 将二进制数据进行base64编码
	 * @param data
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-7-17 下午1:37:44
	 */
	public static String toBase64(byte[] data) {
		return Base64.encodeBase64String(data);
	}
	
	/**
	 * 将某个base64编码的字符串解码成二进制
	 * @param str
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-7-17 下午1:37:30
	 */
	public static byte[] fromBase64(String str) {
		if (str == null) {
			return null;
		}
		//Base64解码
		return Base64.decodeBase64(str);
	}
	
	/**
	 * 将某个base64编码的字符串解码到某个文件中
	 * @param str
	 * @param filepath
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-7-17 下午1:36:29
	 */
	public static boolean fromBase64(String str, String filepath) {
		byte[] data = fromBase64(str);
		OutputStream out = null;
		try {
			out = new FileOutputStream(filepath);
			out.write(data);
			out.flush();
			out.close();
			return true;
		} catch (IOException e) {
			logException(log, e);
		}
		return false;
	}
	
	/**
	 * 下载
	 * @param fileName
	 * @param filePath
	 * @param request
	 * @param response
	 * @throws Exception
	 * @author liushaomin
	 * @createtime 2015-7-8 下午3:3:29
	 */
	public static void download(String fileName, String filePath, HttpServletRequest request ,HttpServletResponse response) throws Exception{
		download(fileName, BaseSysConf.FileSiteBasePath, filePath, request, response);
	}
	
	/**
	 * 下载
	 * @param fileName
	 * @param basepath
	 * @param filePath
	 * @param request
	 * @param response
	 * @throws Exception
	 * @author liushaomin
	 * @createtime 2015-7-8 下午3:3:29
	 */
	public static void download(String fileName, String basepath, String path, HttpServletRequest request ,HttpServletResponse response) throws Exception{
		String suffix = null;
		String filePath = basepath + path;
		response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        long fileLength = new File(filePath).length();
        try {
        	suffix = filePath.substring(filePath.lastIndexOf(BaseStrDict.dot));
		} catch (Exception e) {
			suffix = filePath.substring(filePath.lastIndexOf(BaseStrDict.slash));
		}
        fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment; filename=" + fileName + suffix);
        response.setHeader("Content-Length", String.valueOf(fileLength));
        bis = new BufferedInputStream(new FileInputStream(filePath));
        bos = new BufferedOutputStream(response.getOutputStream());
        byte[] buff = new byte[2048];
        int bytesRead;
        while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
            bos.write(buff, 0, bytesRead);
        }
        bis.close();
        bos.close();
	}
	
}
