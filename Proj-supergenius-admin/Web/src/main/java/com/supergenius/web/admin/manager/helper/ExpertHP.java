package com.supergenius.web.admin.manager.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.global.conf.SysConf;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.manager.entity.Expert;
import com.supergenius.xo.manager.service.ExpertSO;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.service.UserSO;

/**
 * 专家管理HP
 * @author XieMing
 * @date 2016-10-23 下午1:30:34
 */
public class ExpertHP extends BaseHP {
	
	private static ExpertSO so;
	private static UserSO userSO;
	
	private static ExpertSO getSO() {
		if (so == null) {
			so = spring.getBean(ExpertSO.class);
		}
		return so;
	}
	
	private static UserSO getUserSO() {
		if (userSO == null) {
			userSO = spring.getBean(UserSO.class);
		}
		return userSO;
	}
	
	/**
	 * 加载数据
	 * @param model
	 * @return
	 * @author XieMing
	 * 2016-10-21 上午11:51:43
	 */
	public static Map<String, Object> query(Map<String, Object> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model);
		Map<String, Object> result = new HashMap<String, Object>();
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.keyword))) {
			map.put(MapperDict.showname + MapperDict.suffix_like_key, model.get(ViewKeyDict.keyword).toString());
			map.put(MapperDict.sn + MapperDict.suffix_like_key, model.get(ViewKeyDict.keyword).toString());
			map.put(MapperDict.usersn + MapperDict.suffix_like_key, model.get(ViewKeyDict.keyword).toString());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.type))) {
			map.put(MapperDict.type, model.get(ViewKeyDict.type).toString());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.major))) {
			map.put(MapperDict.major, model.get(ViewKeyDict.major).toString());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.level))) {
			map.put(MapperDict.level, model.get(ViewKeyDict.level).toString());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.isfulltime))) {
			if(model.get(ViewKeyDict.isfulltime).equals("0")) {
				map.put(MapperDict.chaircount + MapperDict.suffix_lessOrEqual_key, SysConf.FullExpertCount);
			} else {
				map.put(MapperDict.chaircount + MapperDict.suffix_greaterOrEqual_key, SysConf.FullExpertCount);
			}
		}
		result.put(ViewKeyDict.total, getSO().getCount(map));
		List<Expert> list = getSO().getList(map);
		for (Expert expert : list) {
			User user = getUserSO().get(expert.getUseruid());
			expert.setUser(user);
		}
		result.put(ViewKeyDict.rows, list);
		return result;
	}
	
}
