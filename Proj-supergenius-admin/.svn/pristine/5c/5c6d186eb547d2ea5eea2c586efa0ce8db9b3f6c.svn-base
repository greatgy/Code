package com.supergenius.web.admin.moralnews.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.core.base.utils.DateUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.moralnews.entity.Announcement;
import com.supergenius.xo.moralnews.service.AnnouncementSO;

/**
 * 社区公告
 * 
 * @author tf
 * @date 2018年9月19日
 */
public class AnnouncementHP extends BaseHP{
	
	private static AnnouncementSO announcementSO;

	private static AnnouncementSO getSO() {
		if (announcementSO == null) {
			announcementSO = (AnnouncementSO) spring.getBean(AnnouncementSO.class);
		}
		return announcementSO;
	}
	
	/**
	 * 组织查询数据
	 * 
	 * @param model
	 * @return
	 * @author LiJiacheng
	 */
	public static Map<String, Object> query(Map<?, ?> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model);
		map.put(MapperDict.orderBy, MapperDict.sn);
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.title))) {
			map.put(MapperDict.title + MapperDict.suffix_like_key, model.get(ViewKeyDict.title).toString().trim());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.status))) {
			map.put(MapperDict.status, model.get(ViewKeyDict.status).toString().trim());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.addtimestart))) {
			String start = model.get(ViewKeyDict.addtimestart).toString() + MapperDict.starttimeformat;
			DateTime startTime = DateTime.parse(start, DateTimeFormat.forPattern(DateUtil.FORMAT_DATETIME_CHINA));
			map.put(MapperDict.addtime + MapperDict.suffix_greater_key, startTime);
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.addtimeend))) {
			String end = model.get(ViewKeyDict.addtimeend).toString() + MapperDict.endtimeformat;
			DateTime endTime = DateTime.parse(end, DateTimeFormat.forPattern(DateUtil.FORMAT_DATETIME_CHINA));
			map.put(MapperDict.addtime + MapperDict.suffix_less_key, endTime);
		}
		List<EStatus> liststatus = new ArrayList<>();
		liststatus.add(EStatus.enable);
		liststatus.add(EStatus.disable);
		map.put(MapperDict.statuslist, liststatus);
		map.put(MapperDict.orderBy,
				MapperDict.sql_order_by + MapperDict.status + MapperDict.desc + MapperDict.comma + MapperDict.createtime + MapperDict.desc);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(ViewKeyDict.total, getSO().getCount(map));
		result.put(ViewKeyDict.rows, getSO().getList(map));
		return result;
	}
	
	/**
	 * 取得最大的编号加一
	 * 
	 * @author LiJiacheng
	 */
	public static int getAnnouncementSn() {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.pageSize, 1);
		map.put(MapperDict.orderBy, MapperDict.sn);
		List<Announcement> list = getSO().getList(map);
		if (0 == list.size()) {
			return 1;
		} else if (1 == list.size()) {
			return list.get(0).getSn() + 1;
		} else {
			return -1;
		}
	}
}
