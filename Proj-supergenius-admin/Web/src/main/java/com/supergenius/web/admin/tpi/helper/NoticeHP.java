package com.supergenius.web.admin.tpi.helper;


import java.util.Comparator;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import com.genius.core.base.utils.DateUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.server.tpi.helper.BaseNoticeHP;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.tpi.enums.ENoticeType;
import com.supergenius.xo.tpi.service.NoticeSO;

/**
 * 招聘信息HP
 * @author liushaomin
 */
public class NoticeHP extends BaseNoticeHP{

	private static NoticeSO so;

	private static NoticeSO getSO() {
		if (so == null) {
			so = (NoticeSO) spring.getBean(NoticeSO.class);
		}
		return so;
	}
	
	public static Map<String, Object> query(Map<?, ?> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model, MapperDict.content, MapperDict.fromname);
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
	 * 得到Noticetype所有类型
	 * @return
	 */
	public static Map<String, String> getENoticeTypeMap() {
		Map<String, String> map = new TreeMap<>(new Comparator<String>() {
			public int compare(String k1, String k2) {
				return k1.compareTo(k2);
			}
		});
		for (ENoticeType e : ENoticeType.values()) {
			map.put(e.toString(), ENoticeType.getName(e, Locale.CHINA));
		}
		return map;
	}
}
