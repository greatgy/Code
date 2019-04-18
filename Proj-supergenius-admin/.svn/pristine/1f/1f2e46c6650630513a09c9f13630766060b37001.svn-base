package com.supergenius.web.admin.tpi.helper;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.server.tpi.helper.BaseTeamHP;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.tpi.enums.EType;
import com.supergenius.xo.tpi.service.TypeSO;

/**
 * 团队hp（管理后台）
 * 
 * @author liushaomin
 */
public class TypeHP extends BaseTeamHP {

	private static TypeSO so;

	private static TypeSO getSO() {
		if (so == null) {
			so = (TypeSO) spring.getBean(TypeSO.class);
		}
		return so;
	}

	/**
	 * 查询团队类型时组织数据
	 * @return
	 * @author liushaomin
	 */
	public static Map<String, Object> query(Map<?, ?> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model, MapperDict.title);
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.type))) {
			map.put(MapperDict.type,  model.get(ViewKeyDict.type).toString());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.name))) {
			map.put(MapperDict.name + MapperDict.suffix_like_key, model.get(ViewKeyDict.name).toString().trim());
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(ViewKeyDict.total, getSO().getCount(map));
		result.put(ViewKeyDict.rows, getSO().getList(map));
		return result;
	}

	/**
	 * 获取所有类型
	 * @return
	 */
	public static Object getETypeMap() {
		Map<String, String> map = new TreeMap<>(new Comparator<String>() {
			public int compare(String k1, String k2) {
				return k1.compareTo(k2);
			}
		});
		for (EType e : EType.values()) {
			map.put(e.toString(), EType.getName(e, Locale.CHINA));
		}
		return map;
	}
}
