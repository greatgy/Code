package com.supergenius.web.admin.career.helper;

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
import com.supergenius.xo.career.entity.Ruler;
import com.supergenius.xo.career.service.RulerSO;
import com.supergenius.xo.common.constants.MapperDict;

/**
 * 问卷调查规则管理HP
 * 
 * @author ChenQi
 */
public class RulerHP extends BaseHP {

	private static RulerSO so;
	private static AdminSO adminSO;

	private static RulerSO getSO() {
		if (so == null) {
			so = (RulerSO) spring.getBean(RulerSO.class);
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
	 * 查询测试数据时组织数据
	 * 
	 * @return
	 * @author ChenQi
	 */
	public static Map<String, Object> query(Map<?, ?> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model);
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.name))) {
			map.put(BaseMapperDict.name + MapperDict.suffix_like_key, model.get(BaseMapperDict.name).toString().trim());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.createtimestart))) {
			String startTime = model.get(ViewKeyDict.createtimestart).toString().trim() + MapperDict.starttimeformat;
			map.put(MapperDict.createtimestart, startTime);
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.createtimeend))) {
			String endTime = model.get(ViewKeyDict.createtimeend).toString().trim() + MapperDict.endtimeformat;
			map.put(MapperDict.createtimeend, endTime);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		List<Ruler> list = getSO().getList(map);
		for (Ruler ruler : list) {
			if (StrUtil.isNotEmpty(ruler.getAdminuid())) {
				Admin admin = getAdminSO().get(ruler.getAdminuid());
				if (admin != null) {
					ruler.setAdminname(admin.getName());
				}
			}
		}
		result.put(ViewKeyDict.total, getSO().getCount(map));
		result.put(ViewKeyDict.rows, list);
		return result;
	}

}
