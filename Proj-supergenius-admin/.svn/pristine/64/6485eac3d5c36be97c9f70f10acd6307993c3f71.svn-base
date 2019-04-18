package com.supergenius.web.admin.tpi.helper;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import com.genius.core.base.utils.DateUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.server.tpi.helper.BaseTpiContentHP;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.tpi.enums.EContent;
import com.supergenius.xo.tpi.service.TpiContentSO;

/**
 * 网站内容HP
 * @author liushaomin
 */
public class TpiContentHP extends BaseTpiContentHP{
	
	private static TpiContentSO so;
	
	private static TpiContentSO getSO() {
		if (so == null) {
			so = spring.getBean(TpiContentSO.class);
		}
		return so;
	}

	/**
	 * 组织数据
	 * @param model
	 * @return
	 * @author liushaomin
	 */
	public static Map<String, Object> query(Map<?, ?> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model, MapperDict.title);
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.type))) {
			map.put(MapperDict.type, model.get(ViewKeyDict.type).toString().trim());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.createtimestart))) {
			String start = model.get(ViewKeyDict.createtimestart).toString() + MapperDict.starttimeformat;
			DateTime startTime = DateTime.parse(start);
			map.put(MapperDict.createtime + MapperDict.suffix_greater_key, startTime);
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.createtimeend))) {
			String end = model.get(ViewKeyDict.createtimeend).toString() + MapperDict.endtimeformat;
			DateTime endTime = DateTime.parse(end, DateTimeFormat.forPattern(DateUtil.FORMAT_DATETIME_CHINA));
			map.put(MapperDict.createtime + MapperDict.suffix_lessOrEqual_key, endTime);
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.status))) {
			map.put(MapperDict.status, model.get(ViewKeyDict.status).toString().trim());
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(ViewKeyDict.total, getSO().getCount(map));
		result.put(ViewKeyDict.rows, getSO().getList(map));
		return result;
	}
	
	/**
	 * 得到所有类型
	 * @return
	 * @author liushaomin
	 */
	public static Map<String, String> getEContent() {
		Map<String, String> map = new LinkedHashMap<>();
		for (EContent e : EContent.values()) {
			map.put(e.name(), EContent.getName(e, Locale.CHINA));
		}
		return map;
	}

}
