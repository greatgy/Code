package com.supergenius.web.admin.moralnews.helper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.genius.model.baseadmin.entity.Admin;
import com.genius.server.base.helper.BaseHP;
import com.genius.xo.baseadmin.service.AdminSO;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.moralnews.entity.Catalogue;
import com.supergenius.xo.moralnews.service.CatalogueSO;

/**
 * 模块管理HP
 * 
 * @author tf
 * @date 2018年9月20日
 */
public class MoralNewsCatalogueHP extends BaseHP {

	private static AdminSO adminSO;
	private static CatalogueSO catalogueSO;
	public static final int [] articleCatalogue = {  2, 4, 8, 16, 32 };
	
	private static Logger log = LoggerFactory.getLogger(MoralNewsCatalogueHP.class);

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
	 * @author 杨光
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
		map.put(MapperDict.orderBy, MapperDict.sql_order_by + MapperDict.status + MapperDict.desc + MapperDict.comma + MapperDict.createtime + MapperDict.desc);
		List<Catalogue> templist = getCatalogueSO().getList(map);
		List<Catalogue> list = new ArrayList<>();
		for (Catalogue catalogue : templist) {
			if (StrUtil.isNotEmpty(catalogue.getAdminuid())) {
				Admin admin = getAdminSO().get(catalogue.getAdminuid());
				if (admin != null) {
					catalogue.setAdminname(admin.getName());
				}
			}
			if (catalogue.getCid() != 1) {
				list.add(catalogue);
			}
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(ViewKeyDict.total, getCatalogueSO().getCount(map)-1);
		result.put(ViewKeyDict.rows, list);
		return result;
	}

	/**
	 * 创建带目录的文件夹
	 * 
	 * @author JiaShitao
	 * @date 2018年7月5日12:26:09
	 */
	public static void NewMultiFile(File file) {
		File fileParent = file.getParentFile(); 
		if(!fileParent.exists()){ 
			fileParent.mkdirs(); 
		}
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	/**
	 * 查询所有模块
	 * @return list
	 * @author LouPengYu
	 * @date 2018年2月23日下午2:23:36
	 */
	public static List<Catalogue> getlist() {
		List<Catalogue> list = new ArrayList<Catalogue>();
		for(int i = 0; i< articleCatalogue.length; i++){
			list.add(getCatalogueSO().getOneByCid(articleCatalogue[i]));
		}
		return list;
	}
}