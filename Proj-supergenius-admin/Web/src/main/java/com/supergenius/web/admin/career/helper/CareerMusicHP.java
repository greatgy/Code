package com.supergenius.web.admin.career.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.genius.model.baseadmin.entity.Admin;
import com.genius.server.base.helper.BaseHP;
import com.genius.xo.baseadmin.service.AdminSO;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.xo.career.entity.Music;
import com.supergenius.xo.career.service.MusicSO;

/**
 * 背景音乐管理HP
 * 
 * @author ChenQi
 * @date 2017年12月28日10:49:58
 */
public class CareerMusicHP extends BaseHP {

	private static MusicSO so;
	
	private static AdminSO adminSO;
	
	private static MusicSO getSO() {
		if (so == null) {
			so = (MusicSO)spring.getBean(MusicSO.class);
		}
		return so;
	}
	
	private static AdminSO getAdminSO() {
		if (adminSO == null) {
			adminSO = (AdminSO) spring.getBean(AdminSO.class);
		}
		return adminSO;
	}
	
	/**
	 * 组织查询语句
	 * @param model
	 * @return
	 * @author ChenQi
	 */
	public static Map<String, Object> query(Map<String, Object> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager);
		Map<String, Object> result = new HashMap<String, Object>();
		List<Music> list = getSO().getList(map);
		for (Music music : list) {
			if (StrUtil.isNotEmpty(music.getAdminuid())) {
				Admin admin = getAdminSO().get(music.getAdminuid());
				if (admin != null) {
					music.setAdminname(admin.getName());
				}
			}
		}
		result.put(ViewKeyDict.total, getSO().getCount(map));
		result.put(ViewKeyDict.rows, list);
		return result;
	}
}
