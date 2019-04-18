package com.supergenius.web.admin.admin.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.supergenius.global.conf.UriConf;

/**
 * 上传控制类
 * @author ShangJianguo
 * @createtime 2014-8-11 下午6:59:42
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class FileAdminer extends com.genius.server.baseadmin.controller.FileAdminer{

	@Override
	@RequestMapping(value = "/ajax/file/img/upload", method = RequestMethod.GET)
	public String upload(Map<String, Object> model, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return super.upload(model, request);
	}

	@Override
	@RequestMapping(value = "/ajax/file/img/upload", method = RequestMethod.POST)
	public String imgupload(Map<String, Object> model, HttpServletRequest request, @RequestParam MultipartFile file, String imgpath, String size) {
		// TODO Auto-generated method stub
		return super.imgupload(model, request, file, imgpath, size);
	}

	@Override
	@RequestMapping(value = "/ajax/file/img/delete", method = RequestMethod.GET)
	@ResponseBody
	public boolean deleteImg(Map<String, Object> model, String path) {
		// TODO Auto-generated method stub
		return super.deleteImg(model, path);
	}

}
