package com.supergenius.web.admin.career.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.Admin;
import com.genius.server.base.helper.BaseHP;
import com.genius.xo.baseadmin.service.AdminSO;
import com.supergenius.server.career.helper.CareerQuestionHP;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.xo.career.entity.Question;
import com.supergenius.xo.career.service.QuestionSO;
import com.supergenius.xo.common.constants.MapperDict;

/**
 * 问题管理HP
 * 
 * @author yangguang
 * @date 2017年6月28日 上午10:32:47
 */
public class QuestionHP extends BaseHP {

	private static QuestionSO so;
	private static AdminSO adminSO;

	private static QuestionSO getSO() {
		if (so == null) {
			so = spring.getBean(QuestionSO.class);
		}
		return so;
	}

	private static AdminSO getAdminSO() {
		if (adminSO == null) {
			adminSO = spring.getBean(AdminSO.class);
		}
		return adminSO;
	}

	/**
	 * 查询列表数据
	 * 
	 * @author liubin
	 * @date 2017年6月28日 上午10:33:45
	 * @return Map<String,Object>
	 */
	public static Map<String, Object> query(Map<String, Object> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model);
		Map<String, Object> result = new HashMap<String, Object>();
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.order))) {
			map.put(MapperDict.order, model.get(ViewKeyDict.order).toString());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.name))) {
			map.put(MapperDict.name + MapperDict.suffix_like_key, model.get(ViewKeyDict.name).toString());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.type))) {
			map.put(MapperDict.type, model.get(ViewKeyDict.type).toString());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.status))) {
			if (model.get(ViewKeyDict.status).toString().equals("1")) {
				map.put(MapperDict.status, EStatus.enable);
			}
			if (model.get(ViewKeyDict.status).toString().equals("0")) {
				map.put(MapperDict.status, EStatus.disable);
			}
		} else {
			map.put(MapperDict.status, EStatus.enable);// 默认只查出来正常的数据
		}
		map.put(MapperDict.orderBy, MapperDict.sql_order_by + MapperDict.sql_order + MapperDict.asc);// 按照order正排序
		result.put(ViewKeyDict.total, getSO().getCount(map));
		List<Question> list = getSO().getList(map);
		CareerQuestionHP.querys(list);
		for (Question question : list) {
			if (StrUtil.isNotEmpty(question.getAdminuid())) {
				Admin admin = getAdminSO().get(question.getAdminuid());
				if (admin != null) {
					question.setAdminname(admin.getName());
				}
			}
		}
		result.put(ViewKeyDict.rows, list);
		return result;
	}

}
