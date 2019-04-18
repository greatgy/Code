package com.supergenius.web.admin.tpi.helper;

import java.util.HashMap;
import java.util.Map;

import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.server.tpi.helper.BaseEmailTemplateHP;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.tpi.enums.ESysEmailType;
import com.supergenius.xo.tpi.service.EmailTemplateSO;

/**
 * 邮件模板hp
 * 
 * @author LiuXiaoke
 */
public class EmailTemplateHP extends BaseEmailTemplateHP {
	private static EmailTemplateSO so;

	private static EmailTemplateSO getSO() {
		if (so == null) {
			so = (EmailTemplateSO) spring.getBean(EmailTemplateSO.class);
		}
		return so;
	}
	
	/**
	 * 
	 * @param model
	 * @return
	 * @author LiuXiaoke
	 */
	public static Map<String, Object> query(Map<?, ?> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model);
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.title))) {
			map.put(MapperDict.title + MapperDict.suffix_like_key, model.get(ViewKeyDict.title));
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.type))) {
			map.put(MapperDict.type, ESysEmailType.get(model.get(ViewKeyDict.type).toString()));
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(ViewKeyDict.total, getSO().getCount(map));
		result.put(ViewKeyDict.rows, getSO().getList(map));
		return result;
	}
	
}
