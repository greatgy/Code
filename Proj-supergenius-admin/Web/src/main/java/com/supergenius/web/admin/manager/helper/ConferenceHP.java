package com.supergenius.web.admin.manager.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;

import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.server.manager.helper.BaseConferenceHP;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.manager.entity.Conference;
import com.supergenius.xo.manager.enums.EConfer;

/** 
 * 会议室管理HP
 * @author chenminchang
 * @date 2016-11-10 下午12:33:08 
 */
public class ConferenceHP extends BaseConferenceHP {

	/**
	 * 获取查询结果
	 * @param model
	 * @return
	 */
	public static Map<String, Object> query(Map<String, Object> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, true);
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.type))) {
			map.put(MapperDict.type, EConfer.get(Integer.valueOf(model.get(ViewKeyDict.type).toString())));
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.status))) {
			map.put(MapperDict.status, EStatus.get(model.get(ViewKeyDict.status).toString()));
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.keywords))) {
			map.put(MapperDict.sn + MapperDict.suffix_like_key, model.get(ViewKeyDict.keywords).toString());
			map.put(MapperDict.name + MapperDict.suffix_like_key, model.get(ViewKeyDict.keywords).toString());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.createtimestart))) {
			String start = model.get(ViewKeyDict.createtimestart).toString();
			DateTime startTime = DateTime.parse(start);
			map.put(MapperDict.createtime + MapperDict.suffix_greater_key, startTime);
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.createtimeend))) {
			String end = model.get(ViewKeyDict.createtimeend).toString();
			DateTime endTime = DateTime.parse(end);
			map.put(MapperDict.createtime + MapperDict.suffix_less_key, endTime);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(ViewKeyDict.total, getSO().getCount(map));
		List<Conference> list = getSO().getList(map);
		result.put(ViewKeyDict.rows, list);
		return result;
	}

}
