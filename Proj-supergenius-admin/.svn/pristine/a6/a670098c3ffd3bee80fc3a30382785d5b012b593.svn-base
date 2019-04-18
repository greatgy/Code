package com.supergenius.web.admin.career.helper;

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
import com.supergenius.xo.career.entity.Tease;
import com.supergenius.xo.career.service.TeaseSO;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.user.entity.User;

/**
 * 吐槽专区或鬼话管理HP
 * 
 * @author ChenQi
 * @date 2017年11月14日18:19:25
 */
public class TeaseHP extends BaseHP {

	private static TeaseSO so;
	private static AdminSO adminSO;

	private static TeaseSO getSO() {
		if (so == null) {
			so = (TeaseSO) spring.getBean(TeaseSO.class);
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
	 * 查询评论时组织数据
	 * 
	 * @return
	 * @author ChenQi
	 */
	public static Map<String, Object> query(Map<?, ?> model, EChannel eChannel) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model);
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.search))) {
			map.put(BaseMapperDict.content + MapperDict.suffix_like_key,
					model.get(ViewKeyDict.search).toString().trim());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.status))) {
			map.put(MapperDict.status, EStatus.get(model.get(ViewKeyDict.status).toString()));
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.touid))) {
			if (Integer.valueOf(model.get(ViewKeyDict.touid).toString()) == 1) {
				map.put(MapperDict.touidnull, model.get(ViewKeyDict.status).toString());
			} else {
				map.put(MapperDict.judge, model.get(ViewKeyDict.status).toString());
			}
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
		map.put(MapperDict.channel, eChannel);
		List<Tease> list = getSO().search(map);
		for (Tease tease : list) {
			if (StrUtil.isNotEmpty(tease.getAdminuid())) {
				Admin admin = getAdminSO().get(tease.getAdminuid());
				if (admin != null) {
					tease.setAdminname(admin.getName());
				}
			}
			if (StrUtil.isNotEmpty(tease.getFromuseruid())) {
				User user = BaseUserHP.get(tease.getFromuseruid());
				if (user != null) {
					tease.setFromUserType(1);
				} else {
					tease.setFromUserType(0);
				}
			}
		}
		result.put(ViewKeyDict.total, getSO().searchCount(map));
		result.put(ViewKeyDict.rows, list);
		return result;
	}

}
