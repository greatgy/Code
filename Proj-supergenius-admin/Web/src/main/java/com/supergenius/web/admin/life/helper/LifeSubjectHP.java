package com.supergenius.web.admin.life.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.genius.model.baseadmin.entity.Admin;
import com.genius.server.base.helper.BaseHP;
import com.genius.xo.baseadmin.service.AdminSO;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.life.entity.Subject;
import com.supergenius.xo.life.service.SubjectSO;

/**
 * 广告位管理HP
 * 
 * @author YangGuang
 * @date 2018年5月9日18:22:39
 */
public class LifeSubjectHP extends BaseHP {

	private static SubjectSO so;
	private static AdminSO adminSO;
	
	private static SubjectSO getSO() {
		if (so == null) {
			so = (SubjectSO) spring.getBean(SubjectSO.class);
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
	 * 查询组织数据
	 * 
	 * @return map
	 * @author YangGuang
	 * @date 2018年5月9日18:22:51
	 */
	public static Map<String, Object> query(Map<?, ?> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model);
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.name))) {
			map.put(BaseMapperDict.name + MapperDict.suffix_like_key, model.get(BaseMapperDict.name).toString().trim());
		}
		if (StrUtil.isNotEmpty(model.get(MapperDict.grade))) {
			map.put(MapperDict.grade, model.get(MapperDict.grade).toString().trim());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.status))) {
			map.put(MapperDict.status, model.get(ViewKeyDict.status));
		}
		Map<String, Object> result = new HashMap<String, Object>();
		map.put(MapperDict.orderBy, MapperDict.sql_order_by + MapperDict.status + MapperDict.desc + MapperDict.comma + MapperDict.createtime + MapperDict.desc);
		List<Subject> list = getSO().getList(map);
		for (Subject subject : list) {
			if (StrUtil.isNotEmpty(subject.getAdminuid())) {
				Admin admin = getAdminSO().get(subject.getAdminuid());
				if (admin != null) {
					subject.setAdminname(admin.getName());
				}
			}
		}
		result.put(ViewKeyDict.total, getSO().getCount(map));
		result.put(ViewKeyDict.rows, list);
		return result;
	}
}
