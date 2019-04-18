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
import com.supergenius.xo.manager.entity.Judge;
import com.supergenius.xo.manager.service.JudgeSO;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.service.UserSO;

/**
 * 裁判HP
 * @author XieMing
 * @date 2016-10-21 上午11:49:45
 */
public class JudgeHP extends BaseHP {
	
	private static JudgeSO so;
	private static UserSO userSO;
	
	private static JudgeSO getSO() {
		if (so == null) {
			so = spring.getBean(JudgeSO.class);
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
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.status))) {
			map.put(MapperDict.status, model.get(ViewKeyDict.status).toString());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.isfulltime))) {
			if(model.get(ViewKeyDict.isfulltime).equals("0")) {
				map.put(MapperDict.judgecount + MapperDict.suffix_lessOrEqual_key, SysConf.FullJudgeCount);
			} else {
				map.put(MapperDict.judgecount + MapperDict.suffix_greaterOrEqual_key, SysConf.FullJudgeCount);
			}
		}
		result.put(ViewKeyDict.total, getSO().getCount(map));
		List<Judge> list = getSO().getList(map);
		for (Judge judge : list) {
			User user = getUserSO().get(judge.getUseruid());
			judge.setUser(user);
		}
		result.put(ViewKeyDict.rows, list);
		return result;
	}
	
}
