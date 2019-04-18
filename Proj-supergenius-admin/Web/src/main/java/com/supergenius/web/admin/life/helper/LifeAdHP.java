package com.supergenius.web.admin.life.helper;

import java.util.ArrayList;
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
import com.supergenius.xo.life.entity.Catalogue;
import com.supergenius.xo.life.entity.Content;
import com.supergenius.xo.life.enums.EContent;
import com.supergenius.xo.life.service.CatalogueSO;
import com.supergenius.xo.life.service.ContentSO;

/**
 * 广告位管理HP
 * 
 * @author YangGuang
 * @date 2018年5月9日18:22:39
 */
public class LifeAdHP extends BaseHP {

	private static ContentSO so;
	private static AdminSO adminSO;
	private static CatalogueSO catalogueSO;
	private static final long[] Adcatalogue = {1, 4, 131072, 524288, 8388608, 16777216, 33554432, 134217728, 268435456, 536870912,
			Long.parseLong("4294967296"), Long.parseLong("8589934592"), Long.parseLong("34359738368"), Long.parseLong("68719476736") };

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
	 * @author YangGuang
	 * @date 2018年5月9日18:22:51
	 */
	public static Map<String, Object> query(Map<?, ?> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model);
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.name))) {
			map.put(BaseMapperDict.name + MapperDict.suffix_like_key, model.get(BaseMapperDict.name).toString().trim());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.cid))) {
			map.put(MapperDict.cid, model.get(MapperDict.cid).toString().trim());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.status))) {
			map.put(MapperDict.status, model.get(ViewKeyDict.status));
		}
		map.put(MapperDict.type,EContent.ad);
		Map<String, Object> result = new HashMap<String, Object>();
		map.put(MapperDict.orderBy, MapperDict.sql_order_by + MapperDict.status + MapperDict.desc + MapperDict.comma + MapperDict.createtime + MapperDict.desc);
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
	 * @return list
	 * @author YangGuang
	 * @date 2018年5月9日18:22:59
	 */
	public static List<Catalogue> getCatalogue() {
		List<Catalogue> result = new ArrayList<Catalogue>();
        for(int i = 0; i< Adcatalogue.length; i++){
            result.add(getCatalogueSO().getOneByCid(Adcatalogue[i]));
        }
		return result;
	}
}
