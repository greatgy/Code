package com.supergenius.web.admin.entrepreneur.hellper;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.genius.model.baseadmin.entity.Admin;
import com.genius.server.base.helper.BaseHP;
import com.genius.xo.baseadmin.service.AdminSO;
import com.supergenius.global.conf.SysConf;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.entrepreneur.entity.Catalogue;
import com.supergenius.xo.entrepreneur.service.CatalogueSO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 模块管理HP
 * 
 * @author tf
 * @date 2018年7月5日
 */
public class EntrepreneurCatalogueHP extends BaseHP {

	private static AdminSO adminSO;
	private static CatalogueSO catalogueSO;
	private static final int[] catalogues = { 256, 2, 4, 8, 16, 32 };

	private static CatalogueSO getCatalogueSO() {
		if (catalogueSO == null) {
			catalogueSO = (CatalogueSO) spring.getBean(CatalogueSO.class);
		}
		return catalogueSO;
	}

	private static AdminSO getAdminSO() {
		if (adminSO == null) {
			adminSO = (AdminSO) spring.getBean(AdminSO.class);
		}
		return adminSO;
	}

	/**
	 * 组织查询语句
	 * 
	 * @param model
	 * @return
	 * @author ChenQi
	 */
	public static Map<String, Object> query(Map<String, Object> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model);
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.name))) {
			map.put(MapperDict.name + MapperDict.suffix_like_key, model.get(BaseMapperDict.name).toString().trim());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.status))) {
			map.put(MapperDict.status, model.get(ViewKeyDict.status).toString().trim());
		}
		map.put(MapperDict.orderBy, MapperDict.sql_order_by + MapperDict.status + MapperDict.desc);
		Map<String, Object> result = new HashMap<String, Object>();
		List<Catalogue> list = getCatalogueSO().getList(map);
		for (int i = list.size() - 1; i >= 0; i--) {
			Catalogue catalogue = list.get(i);
			if (StrUtil.isNotEmpty(catalogue.getAdminuid())) {
				Admin admin = getAdminSO().get(catalogue.getAdminuid());
				if (admin != null) {
					catalogue.setAdminname(admin.getName());
				}
			}
			if (catalogue.getCid() == SysConf.IndexCid) {
				list.remove(catalogue);
			}
		}
		result.put(ViewKeyDict.total, getCatalogueSO().getCount(map) - 1);
		result.put(ViewKeyDict.rows, list);
		return result;
	}

	/**
	 * 查询所有模块
	 * 
	 * @return list
	 * @author LouPengYu
	 * @date 2018年2月23日下午2:23:36
	 */
	public static List<Catalogue> getlist() {
		List<Catalogue> list = new ArrayList<Catalogue>();
		for (int i = 0; i < catalogues.length; i++) {
			Catalogue catalogue = getCatalogueSO().get(catalogues[i]);
			list.add(catalogue);
		}
		return list;
	}
}
