package com.supergenius.web.admin.gupage.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.core.base.utils.DateUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.core.search.engine.SearchEngine;
import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.Admin;
import com.genius.server.base.helper.BaseHP;
import com.genius.xo.baseadmin.service.AdminSO;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.gupage.entity.Debate;
import com.supergenius.xo.gupage.service.DebateSO;

/**
 * 个人文章管理HP
 * 
 * @author 
 * @date 
 */
public class GupageDebateHP extends BaseHP {

	private static DebateSO so;
	private static SearchEngine engine;
	private static AdminSO adminSO;

	private static DebateSO getSO() {
		if (so == null) {
			so = (DebateSO) spring.getBean(DebateSO.class);
		}
		return so;
	}

	public static SearchEngine getEngine() {
		if (engine == null) {
			engine = (SearchEngine) spring.getBean("gupageEngine");
		}
		return engine;
	}
	
	private static AdminSO getAdminSO() {
		if (adminSO == null) {
			adminSO = spring.getBean(AdminSO.class);
		}
		return adminSO;
	}

	/**
	 * 组织查询语句
	 * 
	 * @param model
	 * @return
	 * @author 
	 */
	public static Map<String, Object> query(Map<String, Object> model) {
		Map<String, Object> result = new HashMap<String, Object>();
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model);
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.name))) {
			map.put(MapperDict.keywords + MapperDict.suffix_like_key, model.get(BaseMapperDict.name).toString().trim());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.status))) {
			if (Integer.valueOf(model.get(ViewKeyDict.status).toString()) == 10) {
				map.put(MapperDict.createtime_gt, new DateTime(DateTimeZone.forOffsetHours(8)));
			}else {
				map.put(MapperDict.status, model.get(ViewKeyDict.status).toString().trim());
			}
		}
		List<EStatus> liststatus = new ArrayList<>();
		liststatus.add(EStatus.enable);
		liststatus.add(EStatus.disable);
		map.put(MapperDict.status + MapperDict.suffix_in_key, liststatus);
		map.put(MapperDict.orderBy, MapperDict.sql_order_by + MapperDict.status + MapperDict.desc + MapperDict.comma + MapperDict.createtime + MapperDict.desc);
		map.put(MapperDict.createtime_le, true);
		List<Debate> list = getSO().getList(map);
		for (Debate articles : list) {
			if (StrUtil.isNotEmpty(articles.getAdminuid())) {
				Admin admin = getAdminSO().get(articles.getAdminuid());
				if (admin != null) {
					articles.setAdminname(admin.getName());
			    }
			}
		}
		result.put(ViewKeyDict.total, getSO().getCount(map));
		result.put(ViewKeyDict.rows, list);
		result.put(ViewKeyDict.time, new DateTime(DateTimeZone.forOffsetHours(8)).toString(DateUtil.FORMAT_DATETIME_CHINA));
		return result;
	}
}
