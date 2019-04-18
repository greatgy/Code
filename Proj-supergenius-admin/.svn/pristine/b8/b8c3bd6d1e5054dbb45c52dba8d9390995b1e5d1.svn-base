package com.supergenius.web.admin.tpi.helper;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import com.genius.core.base.utils.DateUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.server.tpi.helper.BaseTeamHP;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.tpi.service.TeamSO;

/**
 * 团队hp（管理后台）
 * 
 * @author liushaomin
 */
public class TeamHP extends BaseTeamHP {

	private static TeamSO so;

	private static TeamSO getSO() {
		if (so == null) {
			so = (TeamSO) spring.getBean(TeamSO.class);
		}
		return so;
	}

	/**
	 * 查询团队时组织数据
	 * 
	 * @return
	 * @author liushaomin
	 */
	public static Map<String, Object> query(Map<?, ?> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model, MapperDict.title);
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.name))) {
			map.put(MapperDict.name + MapperDict.suffix_like_key, model.get(ViewKeyDict.name).toString().trim());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.username))) {
			map.put(MapperDict.username + MapperDict.suffix_like_key, model.get(ViewKeyDict.username).toString().trim());
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
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.state))) {
			map.put(MapperDict.state, model.get(ViewKeyDict.state).toString().trim());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.status))) {
			map.put(MapperDict.status, model.get(ViewKeyDict.status).toString().trim());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.istop))) {
			map.put(MapperDict.istop, Boolean.parseBoolean(model.get(ViewKeyDict.istop).toString().trim()));
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(ViewKeyDict.total, getSO().getCount(map));
		result.put(ViewKeyDict.rows, getSO().getList(map));
		return result;
	}
}
