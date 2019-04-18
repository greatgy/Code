package com.supergenius.web.admin.user.helper;

import java.util.HashMap;
import java.util.Map;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.server.tpi.helper.BaseEmailTemplateHP;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.user.enums.EContent;
import com.supergenius.xo.user.service.ContentSO;

/**
 * 邮件模板
 * 
 * @author XieMing
 * @date 2016-5-19 下午12:09:00
 */
public class EmailTemplateHP extends BaseEmailTemplateHP {
	private static ContentSO so;

	private static ContentSO getSO() {
		if (so == null) {
			so = spring.getBean(ContentSO.class);
		}
		return so;
	}

	/**
	 * 获取查询结果
	 * 
	 * @param model
	 * @return
	 * @author XieMing 2016-5-19 下午12:09:46
	 */
	public static Map<String, Object> query(Map<String, Object> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model);
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.title))) {
			map.put(MapperDict.title + MapperDict.suffix_like_key, model.get(ViewKeyDict.title));
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.type))) {
			map.put(MapperDict.type, EContent.get(Integer.parseInt(model.get(ViewKeyDict.type).toString())));
		}
		map.put(MapperDict.type + BaseMapperDict.suffix_nin_key, EContent.getNotEmail());
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(ViewKeyDict.total, getSO().getCount(map));
		result.put(ViewKeyDict.rows, getSO().getList(map));
		return result;
	}

}
