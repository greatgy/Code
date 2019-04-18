package com.supergenius.web.finance.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.genius.core.base.conf.BaseSysConf;
import com.genius.core.base.conf.BaseWebConf;
import com.genius.core.base.utils.FileUtil;
import com.genius.core.base.utils.JsonUtil;
import com.genius.server.base.controller.BaseController;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.xo.common.enums.ESite;
/**
 * CKEditor编辑器
 * @author liushaomin
 */
@Controller
public class CKEditorController extends BaseController{

	/**
	 * cke编辑器上传图片
	 * @param model
	 * @param dir
	 * @param funNum
	 * @param file
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/ajax/upload/webdata/{dir}", method = RequestMethod.POST)
	public ResponseEntity<String> upload(Map<String, Object> model, @PathVariable String dir, @RequestParam("CKEditorFuncNum") String funNum, @RequestParam(value="upload") MultipartFile file, HttpServletRequest request) {
		if (file.getSize() > 0) {
	        String path = FileUtil.uploadImg(file, BaseSysConf.ImgWebDataDir_Slash + dir, BaseSysConf.ImgUploadBasePath);
	        model.put(ViewKeyDict.msg, "File '" + file.getOriginalFilename() + "' uploaded successfully");
	        String basePath = StringUtils.isEmpty(BaseWebConf.WebImgRootPath) ? request.getContextPath() : BaseWebConf.WebImgRootPath;
	        String imgpath = basePath + path;
	        String resp = "<script type=\"text/javascript\">window.parent.CKEDITOR.tools.callFunction(" + funNum + ",'" + imgpath + "','')</script>";
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.TEXT_HTML);
	        return new ResponseEntity<String>(resp,headers, HttpStatus.OK);
	    } else {
	        HttpHeaders headers = new HttpHeaders();
	        String resp = "";
	        return new ResponseEntity<String>(resp,headers, HttpStatus.BAD_REQUEST);
	    }
	}
	
	/**
	 * cke编辑器上传视频
	 * @param model
	 * @param funNum
	 * @param file
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/ajax/ckeditor/flash", method = RequestMethod.POST)
	public ResponseEntity<String> uploadFlash(Map<String, Object> model, @RequestParam("CKEditorFuncNum") String funNum, @RequestParam(value="upload") MultipartFile file, HttpServletRequest request) {
		if (file.getSize() > 0) {
	        String path = FileUtil.uploadFile(file, BaseSysConf.VideoWebDataDir_Slash + ESite.finance.getName());
	        String basePath = StringUtils.isEmpty(BaseWebConf.WebImgRootPath) ? request.getContextPath() : BaseWebConf.WebImgRootPath;
	        String videopath = basePath + BaseSysConf.VideoWebDataDir_Slash + BaseSysConf.VideoPlayerName + basePath + path;
	        String resp = "<script type=\"text/javascript\">window.parent.CKEDITOR.tools.callFunction(" + funNum + ",'" + videopath + "','')</script>";
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.TEXT_HTML);
	        return new ResponseEntity<String>(resp,headers, HttpStatus.OK);
	    } else {
	        HttpHeaders headers = new HttpHeaders();
	        return new ResponseEntity<String>("" , headers, HttpStatus.BAD_REQUEST);
	    }
	}

	/**
	 * 拖拽图片上传
	 * @param model
	 * @param dir
	 * @param file
	 * @param request
	 * @return
	 * @author chenminchang
	 * @create 2016年12月20日下午4:20:12
	 */
	@RequestMapping(value = "/ajax/upload/webdata/{channel}/{iswater}/dropimg", method = RequestMethod.POST)
	@ResponseBody
	public String dropped_im(Map<String, Object> model, @PathVariable  String channel, @PathVariable boolean iswater, @RequestParam("upload") MultipartFile file, HttpServletRequest request) {
		return dropped_img(model, channel, iswater, file, request);
	}


	/**拖拽图片上传
	 * @param model
	 * @param dir
	 * @param file
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/upload/webdata/{channel}/dropimg", method = RequestMethod.POST)
	@ResponseBody
	private String dropped_img(Map<String, Object> model, @PathVariable String channel, boolean iswater, @RequestParam("upload") MultipartFile file, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		 if (file != null && file.getSize() > 0) {
			 String path = FileUtil.uploadImg(file, BaseSysConf.ImgWebDataDir_Slash + channel, BaseSysConf.ImgUploadBasePath);
			 if(iswater) {
				 FileUtil.waterMark(path);
			 }
			 String fileName = path.substring(path.lastIndexOf("/") + 1);
			 String basePath = StringUtils.isEmpty(BaseWebConf.WebImgRootPath) ? request.getContextPath() : BaseWebConf.WebImgRootPath;
		     String imgpath = basePath + path;
		     map.put("uploaded", 1);
		     map.put("fileName", fileName);
		     map.put("url", imgpath);
		 } else {
			 map.put("uploaded", 0);
			 map.put("error", "upload img failed");
		 }
		return JsonUtil.toJson(map);
	}
}
