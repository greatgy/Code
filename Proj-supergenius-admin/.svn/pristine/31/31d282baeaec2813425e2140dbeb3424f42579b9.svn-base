package com.supergenius.web.admin.admin.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.supergenius.global.conf.UriConf;

@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class CKEditorAdminer extends com.genius.server.baseadmin.controller.CKEditorAdminer {

	@Override
	@RequestMapping(value = "/ajax/upload/webdata/{channel}/{iswater}", method = RequestMethod.POST)
	public ResponseEntity<String> upload(Map<String, Object> model, @PathVariable  String channel, @PathVariable boolean iswater, @RequestParam("CKEditorFuncNum") String funNum, @RequestParam("upload") MultipartFile file,
			HttpServletRequest request) {
		return super.upload(model, channel, iswater, funNum, file, request);
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
	@Override
	@RequestMapping(value = "/ajax/upload/webdata/{channel}/{iswater}/dropimg", method = RequestMethod.POST)
	@ResponseBody
	public String dropped_img(Map<String, Object> model, @PathVariable  String channel, @PathVariable boolean iswater, @RequestParam("upload") MultipartFile file, HttpServletRequest request) {
		return super.dropped_img(model, channel, iswater, file, request);
	}
	
	/**
	 * 上传视频
	 */
	@Override
	@RequestMapping(value = "/ajax/upload/webdata/{channel}/video", method = RequestMethod.POST)
	public ResponseEntity<String> upload_video(Map<String, Object> model, @PathVariable  String channel, @RequestParam("CKEditorFuncNum") String funNum, @RequestParam("upload") MultipartFile file,
			HttpServletRequest request) {
		return super.upload_video(model, channel, funNum, file, request);
	}
	
}
