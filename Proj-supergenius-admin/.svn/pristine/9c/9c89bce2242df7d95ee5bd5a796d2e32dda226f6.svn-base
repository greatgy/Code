package com.supergenius.web.admin.admin.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.supergenius.global.conf.SysConf;
import com.supergenius.global.conf.UriConf;


/**
 * 截取图片的控制器
 * @author XieMing
 * @date 2017年3月15日 下午2:07:00
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class CutImgAdminer extends com.genius.server.baseadmin.controller.CutImgAdminer {
	
	/**
	 * 加载图片上传控件
	 * @param model
	 * @param request
	 * @return
	 * @author ShangJianguo
	 * @createtime 2014-8-12 上午9:49:44
	 */
	@RequestMapping(value = "/ajax/file/cutimg/upload", method = RequestMethod.GET)
	public String upload(Map<String, Object> model, HttpServletRequest request) {
		return super.upload(model, request);
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
	@RequestMapping(value = "/ajax/file/cutimg/upload", method = RequestMethod.POST)
	public String imgupload2(Map<String, Object> model, HttpServletRequest request, @RequestParam MultipartFile file, String imgpath, String size) {
		return super.imgupload(model, request, file, imgpath, size);
	}
	
	/**
	 * 截取图片
	 * @param x
	 * @param y
	 * @param x2
	 * @param y2
	 * @param images
	 * @param cutsize
	 * @return
	 * @author XieMing
	 * 2017年3月14日 下午9:36:39
	 */
	@RequestMapping(value = "/ajax/file/cutimg/cut", method = RequestMethod.GET)
	@ResponseBody
	public String cut( String x, String y, String x2, String y2, String images) {
		return super.cut(x, y, x2, y2, images, SysConf.AvatarSizes);
	}
	
}
