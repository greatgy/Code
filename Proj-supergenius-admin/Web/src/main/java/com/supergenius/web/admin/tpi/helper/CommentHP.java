package com.supergenius.web.admin.tpi.helper;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import com.genius.core.base.utils.DateUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.server.tpi.helper.BaseTpiuserHP;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.tpi.service.CommentSO;

/**
 * 并购平台会员HP
 * 
 * @author ShangJianguo
 */
public class CommentHP extends BaseTpiuserHP {
	
	private static CommentSO so;

	private static CommentSO getSO() {
		if (so == null) {
			so = (CommentSO) spring.getBean(CommentSO.class);
		}
		return so;
	}
	
	/**
	 * 
	 * @param model
	 * @return
	 * @author ShangJianguo
	 */
	public static Map<String, Object> query(Map<?, ?> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model);
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.fromusername))) {
			map.put(MapperDict.fromusername + MapperDict.suffix_like_key, model.get(ViewKeyDict.fromusername));
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.content))) {
			map.put(MapperDict.content + MapperDict.suffix_like_key, model.get(ViewKeyDict.content));
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.createtimestart))) {
			String start = model.get(ViewKeyDict.createtimestart).toString();
			DateTime startTime = DateTime.parse(start);
			map.put(MapperDict.createtime + MapperDict.suffix_greaterOrEqual_key, startTime);
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.createtimeend))) {
			String end = model.get(ViewKeyDict.createtimeend).toString() + MapperDict.endtimeformat;
			DateTime endTime = DateTime.parse(end, DateTimeFormat.forPattern(DateUtil.FORMAT_DATETIME_CHINA));
			map.put(MapperDict.createtime + MapperDict.suffix_lessOrEqual_key, endTime);
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.channel))) {
			map.put(MapperDict.channel, model.get(ViewKeyDict.channel));
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.status))) {
			map.put(MapperDict.status, model.get(ViewKeyDict.status));
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.fromtitle))) {
			map.put(MapperDict.fromtitle, model.get(ViewKeyDict.fromtitle));
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(ViewKeyDict.total, getSO().getCount(map));
		result.put(ViewKeyDict.rows, getSO().getList(map));
		return result;
	}
	
}
