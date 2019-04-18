package com.supergenius.web.admin.gupage.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.Admin;
import com.genius.server.base.helper.BaseHP;
import com.genius.xo.baseadmin.service.AdminSO;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.gupage.entity.Catalogue;
import com.supergenius.xo.gupage.entity.Content;
import com.supergenius.xo.gupage.service.CatalogueSO;
import com.supergenius.xo.gupage.service.ContentSO;

/**
 * 内容管理HP
 * 
 * @author Yangguang
 * @date 2018年1月4日14:26:26
 */
public class ContentHP extends BaseHP {

	private static ContentSO so;
	private static AdminSO adminSO;
	private static CatalogueSO catalogueSO;

	private static CatalogueSO getCatalogueSO() {
		if (catalogueSO == null) {
			catalogueSO = (CatalogueSO) spring.getBean(CatalogueSO.class);
		}
		return catalogueSO;
	}
	
	private static ContentSO getSO() {
		if (so == null) {
			so = (ContentSO) spring.getBean(ContentSO.class);
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
	 * 查询内容时组织数据
	 * 
	 * @return
	 * @author xuzhixiang
	 */
	public static Map<String, Object> query(Map<?, ?> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model);
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.name))) {
			map.put(BaseMapperDict.name + MapperDict.suffix_like_key, model.get(BaseMapperDict.name).toString().trim());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.type))) {
			map.put(MapperDict.type, model.get(ViewKeyDict.type));
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.status))) {
			if (model.get(ViewKeyDict.status).toString().equals("1")) {
				map.put(MapperDict.status, EStatus.enable);
			}
			if (model.get(ViewKeyDict.status).toString().equals("0")) {
				map.put(MapperDict.status, EStatus.disable);
			}
		}
		Map<String, Object> result = new HashMap<String, Object>();
		map.put(MapperDict.orderBy, MapperDict.sql_order_by + MapperDict.status + MapperDict.desc + MapperDict.comma + MapperDict.type + MapperDict.desc + MapperDict.comma + MapperDict.createtime + MapperDict.desc);
		List<Content> list = getSO().getList(map);
		for (Content content : list) {
			if (StrUtil.isNotEmpty(content.getAdminuid())) {
				Admin admin = getAdminSO().get(content.getAdminuid());
				if (admin != null) {
					content.setAdminname(admin.getName());
				}
			}
		}
		result.put(ViewKeyDict.total, getSO().getCount(map));
		result.put(ViewKeyDict.rows, list);
		return result;
	}
	
	/**
	 * 查询广告位的模块
	 * 
	 * @return
	 * @author YangGuang
	 */
	public static List<Catalogue> getCatalogue() {
		Map<String, Object> map = getParamMap();
		List<Catalogue> result = getCatalogueSO().getList(map);
		return result;
	}

	/**
	 * 获取广告位中应聘指南的个数
	 * 
	 * @return
	 * @author 杨光
	 * @date 2017年12月18日16:50:363
	 */
	public static int getGuideCount() {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.type, 2); // 表示置顶
		return getSO().getCount(map);
	}
}
