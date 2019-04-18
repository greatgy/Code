package com.supergenius.web.admin.moral.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import com.genius.core.base.utils.DateUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.moral.entity.Doc;
import com.supergenius.xo.moral.service.DocSO;

/**
 * 讲义HP
 * 
 * @author LiJiacheng
 */
public class DocHP extends BaseHP {

	private static DocSO so;

	private static DocSO getSO() {
		if (so == null) {
			so = (DocSO) spring.getBean(DocSO.class);
		}
		return so;
	}

	/**
	 * 组织查询
	 * 
	 * @param model
	 * @return
	 * @author LiJiacheng
	 */
	public static Map<String, Object> query(Map<?, ?> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model);
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.name))) {
			map.put(MapperDict.name + MapperDict.suffix_like_key, model.get(ViewKeyDict.name).toString().trim());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.chapter))) {
			map.put(ViewKeyDict.chapter, model.get(ViewKeyDict.chapter).toString().trim());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.status))) {
			map.put(MapperDict.status, model.get(ViewKeyDict.status).toString().trim());
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
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(ViewKeyDict.total, getSO().getCount(map));
		result.put(ViewKeyDict.rows, getSO().getList(map));
		return result;
	}

	/**
	 * 查找最新的编号 循环取出所有的sn值,判断最大值,并返回
	 * 
	 * @param docList
	 * @return
	 * @author LiJiacheng
	 */
	public static String getDocSn() {
		List<Doc> docList = so.getList();
		int j = 0;
		for (Doc doc : docList) {
			String string = doc.getSn();
			int i = Integer.parseInt(string);
			if (i > j) {
				j = i;
			}
		}
		return String.valueOf(j + 1);
	}
	
	/**
	 * 查找上移值 循环取出所有的updown值,判断最大值,并返回
	 * 
	 * @param docList
	 * @return
	 * @author LiJiacheng
	 */
	public static int getUpDown(List<Doc> docList) {
		int j = 0;
		for (Doc doc : docList) {
			int i = doc.getSortorder();
			if (i > j) {
				j = i;
			}
		}
		return j;
	}

}
