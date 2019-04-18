package com.supergenius.web.admin.official.helper;

import java.util.HashMap;
import java.util.Map;

import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.server.tpi.helper.BaseMessageHP;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.official.enums.EType;
import com.supergenius.xo.official.service.ContentSO;

/**
 * 网站内容HP
 * @author LiuXiaoke
 */
public class CharactersContentHP extends BaseMessageHP {
	
	private static ContentSO so;

	private static ContentSO getSO() {
		if (so == null) {
			so = (ContentSO) spring.getBean(ContentSO.class);
		}
		return so;
	}
	
	/**
	 * 获取json数据
	 * @param model
	 * @return
	 * @author LiuXiaoke
	 */
	public static Map<String, Object> query(Map<?, ?> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model);
		String[] etype = new String[] {EType.aboutus.toString(), EType.friendlink.toString(), EType.contact.toString(), EType.indexsupergenius.toString(), EType.joinus.toString(), EType.legal.toString(), EType.mobileaboutus.toString(), EType.mobilecontact.toString()};
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.content))) {
			map.put(MapperDict.content, model.get(ViewKeyDict.content));
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.title))) {
			map.put(MapperDict.title, model.get(ViewKeyDict.title));
		}
		map.put(MapperDict.type + MapperDict.suffix_in_key, etype);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(ViewKeyDict.total, getSO().getCount(map));
		result.put(ViewKeyDict.rows, getSO().getList(map));
		return result;
	}
	
}