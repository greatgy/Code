package com.supergenius.web.admin.life.helper;

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
import com.supergenius.server.user.helper.BaseUserHP;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.life.entity.Catalogue;
import com.supergenius.xo.life.entity.Essay;
import com.supergenius.xo.life.service.CatalogueSO;
import com.supergenius.xo.life.service.EssaySO;
import com.supergenius.xo.user.entity.User;

/**
 * 吐槽专区或鬼话管理HP
 * 
 * @author ChenQi
 * @date 2017年11月14日18:19:25
 */
public class LifeEssayHP extends BaseHP {

	private static EssaySO so;
	private static CatalogueSO catalogueSO;
	private static AdminSO adminSO;

	private static EssaySO getSO() {
		if (so == null) {
			so = (EssaySO) spring.getBean(EssaySO.class);
		}
		return so;
	}

	private static AdminSO getAdminSO() {
		if (adminSO == null) {
			adminSO = spring.getBean(AdminSO.class);
		}
		return adminSO;
	}

	private static CatalogueSO getCatalogueSO() {
		if (catalogueSO == null) {
			catalogueSO = spring.getBean(CatalogueSO.class);
		}
		return catalogueSO;
	}
	/**
	 * 查询评论时组织数据
	 * 
	 * @return
	 * @author ChenQi
	 */
	public static Map<String, Object> query(Map<?, ?> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model);
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.search))) {
			map.put(BaseMapperDict.content + MapperDict.suffix_like_key,
					model.get(ViewKeyDict.search).toString().trim());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.status))) {
			map.put(MapperDict.status, EStatus.get(model.get(ViewKeyDict.status).toString()));
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
		map.put(MapperDict.orderBy, MapperDict.sql_order_by + MapperDict.status + MapperDict.desc + MapperDict.comma
				+ MapperDict.type + MapperDict.asc + MapperDict.comma + MapperDict.createtime + MapperDict.desc);
		List<EStatus> liststatus = new ArrayList<>();
		liststatus.add(EStatus.enable);
		liststatus.add(EStatus.disable);
		map.put(MapperDict.statuslist, liststatus);
		List<Essay> list = getSO().search(map);
		for (Essay essay : list) {
			if (StrUtil.isNotEmpty(essay.getAdminuid())) {
				Admin admin = getAdminSO().get(essay.getAdminuid());
				if (admin != null) {
					essay.setAdminname(admin.getName());
				}
			}
			if (StrUtil.isNotEmpty(essay.getFromuseruid())) {
				User user = BaseUserHP.get(essay.getFromuseruid());
				if (user != null) {
					essay.setFromUserType(1);
				} else {
					essay.setFromUserType(0);
				}
			}
			if (StrUtil.isNotEmpty(essay.getCid())) {
				Catalogue catalogue = getCatalogueSO().getOneByCid(essay.getCid());
				if (catalogue != null) {
					essay.setCatalogueName(catalogue.getName());;
				}
			}
		}
		result.put(ViewKeyDict.total, getSO().searchCount(map));
		result.put(ViewKeyDict.rows, list);
		return result;
	}

	/**
	 * 获取参与人数的数量
	 * 
	 * @return String
	 * @author ChenQi
	 */
	public static String getUserCount() {
		int commentsCount = getSO().getCountByfromuseruid();
		return String.valueOf(commentsCount);
	}
}
