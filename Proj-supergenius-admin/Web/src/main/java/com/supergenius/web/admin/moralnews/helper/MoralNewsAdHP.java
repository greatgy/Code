package com.supergenius.web.admin.moralnews.helper;

import java.util.ArrayList;
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
import com.supergenius.xo.moralnews.entity.Catalogue;
import com.supergenius.xo.moralnews.entity.Content;
import com.supergenius.xo.moralnews.enums.EContent;
import com.supergenius.xo.moralnews.service.CatalogueSO;
import com.supergenius.xo.moralnews.service.ContentSO;

/**
 * 广告位管理HP
 * 
 * @author tf
 * @date 2018年9月18日
 */
public class MoralNewsAdHP extends BaseHP{
	private static ContentSO so;
	private static AdminSO adminSO;
	private static CatalogueSO catalogueSO;
	private static final int[] Adcatalogue = { 1, 256, 2, 4, 8, 16, 32,64 };

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
	 * @return map
	 * @author tf
	 * @date 2018年9月19日
	 */
	public static Map<String, Object> query(Map<?, ?> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model);
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.name))) {
			map.put(MapperDict.keywords + MapperDict.suffix_like_key, model.get(MapperDict.name).toString().trim());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.cid))) {
			map.put(MapperDict.cid, model.get(MapperDict.cid).toString().trim());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.status))) {
			map.put(MapperDict.status, model.get(ViewKeyDict.status));
		}
		List<EStatus> liststatus = new ArrayList<>();
		liststatus.add(EStatus.enable);
		liststatus.add(EStatus.disable);
		map.put(MapperDict.statuslist, liststatus);
		map.put(MapperDict.type, EContent.ad);
		map.put(MapperDict.orderBy,
				MapperDict.sql_order_by + MapperDict.status + MapperDict.desc + MapperDict.comma + MapperDict.type + MapperDict.desc + MapperDict.comma + MapperDict.createtime + MapperDict.desc);
		List<Content> list = getSO().getList(map);
		for (Content content : list) {
			if (StrUtil.isNotEmpty(content.getAdminuid())) {
				Admin admin = getAdminSO().get(content.getAdminuid());
				if (admin != null) {
					content.setAdminname(admin.getName());
				}
			}
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(ViewKeyDict.total, getSO().getCount(map));
		result.put(ViewKeyDict.rows, list);
		return result;
	}
	
	/**
	 * 查询广告位的模块
	 * 
	 * @return list
	 * @author tf
	 * @date 2018年9月19日
	 */
	public static List<Catalogue> getCatalogue() {
		List<Catalogue> result = new ArrayList<Catalogue>();
		for (int i = 0; i < Adcatalogue.length; i++) {
			result.add(getCatalogueSO().getOneByCid(Adcatalogue[i]));
		}
		return result;
	}
}
