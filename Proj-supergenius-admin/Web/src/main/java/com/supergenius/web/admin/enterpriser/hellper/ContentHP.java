package com.supergenius.web.admin.enterpriser.hellper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.enterpriser.entity.Content;
import com.supergenius.xo.enterpriser.enums.EContent;
import com.supergenius.xo.enterpriser.service.ContentSO;

/** 
 * 内容管理HP
 * @author liubin
 * @date 2016-10-28 下午4:52:29 
 */
public class ContentHP extends BaseHP {

	private static ContentSO so;
	
	private static ContentSO getSO() {
		if (so == null) {
			so = spring.getBean(ContentSO.class);
		}
		return so;
	}
	
	/**
	 * 查询数据
	 * @param model
	 * @return
	 * @author liubin
	 * @createtime 2016-10-28下午4:57:04
	 * @return Map<String,Object>
	 */
	public static Map<String, Object> query(Map<String, Object> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, true);
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.name))) {
			map.put(MapperDict.name + MapperDict.suffix_like_key, (model.get(ViewKeyDict.name).toString()));
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.type))) {
			map.put(MapperDict.type, model.get(ViewKeyDict.type).toString());
		}
		List<EContent> typelist = new ArrayList<>();
		typelist.add(EContent.lecture);
		typelist.add(EContent.train);
		typelist.add(EContent.cooperation);
		typelist.add(EContent.concept);
		typelist.add(EContent.member);
		map.put(MapperDict.typelist, typelist);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(ViewKeyDict.total, getSO().getCount(map));
		List<Content> list = getSO().getList(map);
		result.put(ViewKeyDict.rows, list);
		return result;
	}

}