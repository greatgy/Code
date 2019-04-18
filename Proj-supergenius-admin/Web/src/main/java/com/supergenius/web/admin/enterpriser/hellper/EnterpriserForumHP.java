package com.supergenius.web.admin.enterpriser.hellper;

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
import com.supergenius.xo.enterpriser.entity.Forum;
import com.supergenius.xo.enterpriser.service.ForumSO;
import com.supergenius.xo.user.service.UserSO;

/**
 * 论坛管理HP
 * 
 * @author YangGuang
 * @date 2018年1月30日15:01:20
 */
public class EnterpriserForumHP extends BaseHP {

	private static ForumSO so;
	private static UserSO userSO;
	private static AdminSO adminSO;
	
	private static AdminSO getAdminSO() {
		if (adminSO == null) {
			adminSO = spring.getBean(AdminSO.class);
		}
		return adminSO;
	}

	private static ForumSO getSO() {
		if (so == null) {
			so = (ForumSO) spring.getBean(ForumSO.class);
		}
		return so;
	}

	public static UserSO getUserSO() {
		if (userSO == null) {
			userSO = (UserSO) spring.getBean(UserSO.class);
		}
		return userSO;
	}

	/**
	 * 组织查询语句
	 * 
	 * @param model
	 * @return
	 * @author 杨光
	 */
	public static Map<String, Object> query(Map<String, Object> model) {
		Map<String, Object> result = new HashMap<String, Object>();
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model);
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.title))) {
			map.put(MapperDict.keywords + MapperDict.suffix_like_key, model.get(BaseMapperDict.title).toString().trim());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.status))) {
			map.put(MapperDict.status, model.get(ViewKeyDict.status).toString().trim());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.cid))) {
			map.put(MapperDict.cid, model.get(ViewKeyDict.cid).toString().trim());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.isrecommend))) {
			map.put(MapperDict.isrecommend, model.get(ViewKeyDict.isrecommend));
		} else {
			map.put(MapperDict.isrecommend, null);
		}
		List<EStatus> liststatus = new ArrayList<>();
		liststatus.add(EStatus.enable);
		liststatus.add(EStatus.disable);
		map.put(MapperDict.statuslist, liststatus);
		map.put(MapperDict.orderBy, MapperDict.sql_order_by + MapperDict.status + MapperDict.desc + MapperDict.comma + MapperDict.createtime + MapperDict.desc);
		List<Forum> list = getSO().getList(map);
		for (Forum forum : list) {
			if (StrUtil.isNotEmpty(forum.getAdminuid())) {
				Admin admin = getAdminSO().get(forum.getAdminuid());
				if (admin != null) {
					forum.setAdminname(admin.getName());
				}
			}
		}
		result.put(ViewKeyDict.total, getSO().getCount(map));
		result.put(ViewKeyDict.rows, list);
		return result;
	}

	/**
	 * @author XueZhenYong
	 * @Datetime 2018年2月6日下午3:27:36
	 */
	public static int getTopForumCount(int cid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.istop, 1); // 表示置顶
		map.put(MapperDict.cid, cid);
		return getSO().getCount(map);

	}

}
