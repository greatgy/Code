package com.supergenius.web.admin.manager.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.manager.enums.EContent;
import com.supergenius.xo.manager.service.ContentSO;
import com.supergenius.xo.manager.service.MajorSO;

/** 
 * manager的content后台HP
 * @author chenminchang
 * @date 2016-11-2 下午4:47:50 
 */
public class ContentHP extends BaseHP {

	private static ContentSO so;
	private static MajorSO majorso;
	
	private static ContentSO getSO() {
		if (so == null) {
			so = spring.getBean(ContentSO.class);
		}
		return so;
	}
	
	private static MajorSO getMajorSO() {
		if (majorso == null) {
			majorso = spring.getBean(MajorSO.class);
		}
		return majorso;
	}
	
	/**
	 * content查询
	 * @param model
	 * @return
	 * @author chenminchang
	 * @create 2016-11-2下午4:49:36
	 */
	public static Map<String, Object> query(Map<String, Object> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, true);
		int total = 0;
		int contentTotal = 0;
		int majorTotal = 0;
		List<Object> list = new ArrayList<>();
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.type))) {
			String type = model.get(ViewKeyDict.type).toString();
			if (EStatus.deleted.toString().equals(type)) {//专业简介:major 表
				majorTotal = getMajorSO().getCount(map);
				list.addAll(getMajorSO().getList(map));
			} else {//首页和细则：content表
				map.put(MapperDict.type, EContent.get(type));
				contentTotal = getSO().getCount(map);
				list.addAll(getSO().getList(map));
			}
		} else {
			contentTotal = getSO().getCount(map);
			majorTotal = getMajorSO().getCount(map);
			list.addAll(getSO().getList(map));
			list.addAll(getMajorSO().getList(map));
		}
		Map<String, Object> result = new HashMap<String, Object>();
		total = contentTotal + majorTotal;
		result.put(ViewKeyDict.total, total);
		result.put(ViewKeyDict.rows, list);
		return result;
	}
	
}
