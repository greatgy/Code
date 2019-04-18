package com.genius.server.baseadmin.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.genius.core.base.conf.BaseUriConf;
import com.genius.core.base.constant.BaseStrDict;
import com.genius.core.base.constant.BaseViewKeyDict;
import com.genius.core.base.utils.FileUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.server.base.controller.BaseController;


/**
 * 上传控制类
 * @author ShangJianguo
 * @createtime 2014-8-11 下午6:59:42
 */
@Controller
@RequestMapping(value = BaseUriConf.baseAdminPath)
public class FileAdminer extends BaseController{

	/**
	 * 加载图片上传控件
	 * @param model
	 * @param request
	 * @return
	 * @author ShangJianguo
	 * @createtime 2014-8-12 上午9:49:44
	 */
	@RequestMapping(value = "/api/file/img/upload", method = RequestMethod.GET)
	public String upload(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		return "doupload";
	}
	
	/**
	 * 上传图片，可根据传递的size参数压缩成相应尺寸的图片
	 * @param model
	 * @param request
	 * @param file 要上传的图片
	 * @param imgpath 图片存储的路径
	 * @return
	 * @author ShangJianguo
	 * @createtime 2014-8-15 下午3:05:25
	 * @modifier architect.bian
	 * @modifier YuYingJie
	 */
	@RequestMapping(value = "/api/file/img/upload", method = RequestMethod.POST)
	public String imgupload(Map<String, Object> model, HttpServletRequest request, @RequestParam MultipartFile file, String imgpath, String size) {
		cloneParamsToModel(model, request);
		int[][] ImgSizes = null;
		String path = "";
		if (StrUtil.isNotEmpty(size)) {
			String[] fragments = size.split(BaseStrDict.barRegSplitter);
			int len = fragments.length;
			ImgSizes = new int[len][2];
			for (int i = 0; i < len; i++) {
				String[] widhei = fragments[i].split(BaseStrDict.comma);
				ImgSizes[i] = new int[] {Integer.parseInt(widhei[0]), Integer.parseInt(widhei[1])};
			}
			String[] paths = FileUtil.resizeImage(file, imgpath, ImgSizes);
			path = paths[0] + BaseStrDict.comma + paths[1] + BaseStrDict.comma +paths[2]+ BaseStrDict.comma +paths[3];
		} else {
			path = FileUtil.uploadImg(file, imgpath);
		}
		model.put(BaseViewKeyDict.path, path);
		return "doupload";
	}
	
	/**
	 * 删除图片
	 * @param model
	 * @param path
	 * @return
	 * @author ShangJianguo
	 * @createtime 2014-8-12 下午4:10:58
	 */
	@RequestMapping(value = "/api/file/img/delete", method = RequestMethod.GET)
	@ResponseBody
	public boolean deleteImg(Map<String, Object> model, String path) {
		String[] paths = path.split(BaseStrDict.comma);
		return FileUtil.deleteImg(paths);
	}
}