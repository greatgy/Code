package com.supergenius.web.admin.gupage.helper;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.genius.model.baseadmin.entity.Admin;
import com.genius.server.base.helper.BaseHP;
import com.genius.xo.baseadmin.service.AdminSO;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.xo.gupage.entity.Catalogue;
import com.supergenius.xo.gupage.service.CatalogueSO;
import com.supergenius.xo.common.constants.MapperDict;

/**
 * 模块管理HP
 * 
 * @author ChenQi
 * @date 2017年11月15日15:23:45
 */
public class GupageCatalogueHP extends BaseHP {

	private static AdminSO adminSO;
	private static CatalogueSO catalogueSO;

	private static Logger log = LoggerFactory.getLogger(GupageCatalogueHP.class);

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
		map.put(MapperDict.orderBy, MapperDict.sql_order_by + MapperDict.status + MapperDict.desc + MapperDict.comma
				+ MapperDict.createtime + MapperDict.desc);
		Map<String, Object> result = new HashMap<String, Object>();
		List<Catalogue> templist = getCatalogueSO().getList(map);
		List<Catalogue> list = new ArrayList<>();
		for (Catalogue catalogue : templist) {
			if (StrUtil.isNotEmpty(catalogue.getAdminuid())) {
				Admin admin = getAdminSO().get(catalogue.getAdminuid());
				if (admin != null) {
					catalogue.setAdminname(admin.getName());
				}
			}
			if (catalogue.getCid() != 0) {
				list.add(catalogue);
			}
		}
		result.put(ViewKeyDict.total, getCatalogueSO().getCount(map));
		result.put(ViewKeyDict.rows, list);
		return result;
	}

	/**
	 * 递归删除文件夹
	 * 
	 * @author ChenQi
	 * @date 2017年9月13日12:26:09
	 */
	public static void deleteFile(File file) {
		if (file.exists()) {
			if (file.isFile()) {
				file.delete();
			} else if (file.isDirectory()) {
				File[] files = file.listFiles();
				for (int i = 0; i < files.length; i++) {
					deleteFile(files[i]);// 把目录下的每个文件用这个方法进行迭代
				}
				file.delete();// 删除文件夹
			}
		} else {
			log.debug("该文件不存在");
		}
	}
}
