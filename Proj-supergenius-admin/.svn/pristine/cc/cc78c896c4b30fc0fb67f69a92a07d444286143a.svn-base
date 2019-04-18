package com.supergenius.web.admin.official.controller;


import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.constant.BaseStrDict;
import com.genius.core.base.utils.StrUtil;
import com.genius.server.base.controller.BaseController;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.official.helper.BannerHP;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.official.entity.Banner;
import com.supergenius.xo.official.enums.EBanner;
import com.supergenius.xo.official.service.BannerSO;

/**
* 图片轮播管理
* @author yuyingjie
*/
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class BannerAdminer extends BaseController{
	
	@Autowired
	BannerSO so;

	/**
	 * 跳转到列表页面 
	 * @param model
	 * @param request
	 * @return
	 * @author YuYingYie
	 */
	@RequestMapping(value = "/official/banner", method = RequestMethod.GET)
	public String banner(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.banner.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.banner, Locale.CHINA));
		model.put(ViewKeyDict.site, EChannel.official.name());
		Map<String, String> typeMap = new HashMap<String, String>();
		for (EBanner item : EBanner.values()) {
			typeMap.put(item.toString(), item.getName());
		}
		model.put(ViewKeyDict.map, typeMap);
		return "dobanner";
	}

	/**
	 * 加载列表
	 * @param model
	 * @param request
	 * @return
	 * @author YuYingYie
	 */
	@RequestMapping(value = { "/official/ajax/banner/list" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> banner_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = BannerHP.query(model);
		return json(searchMap, Json.webStrategy);
	}

	/**
	 * 轮播图片添加 
	 * @param banner
	 * @param file
	 * @return
	 * @author YuYingJie
	 */
	@RequestMapping(value = "/official/ajax/banner/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> banner_add(Banner banner, String file) {
		if (StringUtils.isNotEmpty(file)) {
			String[] imgs = file.split(BaseStrDict.comma);
			banner.setImgs(imgs);
		}
		if (StrUtil.isEmpty(banner.getBackground())) {
			banner.setBackground("#ffffff");
		}
		banner.setSortorder((int)(System.currentTimeMillis()/1000));
		banner.setUpdatetime(DateTime.now());
		banner.setCreatetime(DateTime.now());
		if (so.add(banner)) {
			return result(MsgKeyDict.addSuccess);
		} else {
			return result(MsgKeyDict.addFailed);
		}
		
	}
	
	/**
	 * 轮播图片修改
	 * @param banner
	 * @param file
	 * @return
	 * @author YuYingYie
	 */
	@RequestMapping(value = "/official/ajax/banner/edit",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> banner_edit(Banner newbanner, String file) {
		if (StringUtils.isNotEmpty(file)) {
			String[] imgs = file.split(BaseStrDict.comma);
			newbanner.setImgs(imgs);
		}
		Banner banner = so.get(newbanner.getUid());
		newbanner.setSortorder(banner.getSortorder());
		banner.set(newbanner);
		if (so.update(banner)) {
			return result(MsgKeyDict.editSuccess);
		}else {
			return result(MsgKeyDict.editFailed);
		}
	}
	
	/**
	 * 轮播图片删除
	 * @param ids
	 * @return
	 * @author YuYingYie
	 */
	@RequestMapping(value = "/official/ajax/banner/delete", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> banner_delete(String[] ids) {
		if (so.delete(ids)) {
			return success();
		} else {
			return result(MsgKeyDict.deleteFailed);
		}
	}
	
	/**
	 * 轮播图片上移
	 * @param ids
	 * @return
	 * @author YuYingYie
	 */
	@RequestMapping(value = "/official/ajax/banner/up", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> banner_up(String ids) {
		if (BannerHP.banner_up(ids)) {
			return success();
		}
		return result(MsgKeyDict.doFailed);
	}
	
	/**
	 * 轮播图片下移
	 * @param ids
	 * @return
	 * @author YuYingYie
	 */
	@RequestMapping(value = "/official/ajax/banner/down", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> banner_down(String ids) {
		if (BannerHP.banner_down(ids)) {
			return success();
		}
		return result(MsgKeyDict.doFailed);
	}
}
