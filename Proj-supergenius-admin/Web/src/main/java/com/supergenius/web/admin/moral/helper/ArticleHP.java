package com.supergenius.web.admin.moral.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import com.genius.core.base.utils.DateUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.moral.service.ArticleSO;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.service.UserSO;

/**
 * 发帖hp
 * @author liushaomin
 */
public class ArticleHP extends BaseHP{

	private static ArticleSO so;
	
	private static ArticleSO getSO() {
		if (so == null) {
			so = (ArticleSO) spring.getBean(ArticleSO.class);
		}
		return so;
	}
	
	private static UserSO userSO;
	
	private static UserSO getUserSO() {
		if (userSO == null) {
			userSO = (UserSO) spring.getBean(UserSO.class);
		}
		return userSO;
	}

	/**
	 * @param model
	 * @return
	 * @author liushaomin
	 */
	public static Map<String, Object> query(Map<String, Object> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model, MapperDict.title);
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.username))) {
			Map<String, Object> usermap = getParamMap();
			usermap.put(MapperDict.username + MapperDict.suffix_like_key, model.get(ViewKeyDict.username).toString().trim());
			List<User> users = getUserSO().getList(usermap);
			String[] useruids = new String[users.size()];
			for (int i = 0; i < users.size(); i++) {
				useruids[i] = users.get(i).getUid(); 
			}
			map.put(MapperDict.useruid + MapperDict.suffix_in_key, useruids);
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.status))) {
			map.put(MapperDict.status, model.get(ViewKeyDict.status).toString().trim());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.createtimestart))) {
			String start = model.get(ViewKeyDict.createtimestart).toString();
			DateTime startTime = DateTime.parse(start);
			map.put(MapperDict.createtime + MapperDict.suffix_greater_key, startTime);
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.createtimeend))) {
			String end = model.get(ViewKeyDict.createtimeend).toString() + MapperDict.endtimeformat;
			DateTime endTime = DateTime.parse(end, DateTimeFormat.forPattern(DateUtil.FORMAT_DATETIME_CHINA));
			map.put(MapperDict.createtime + MapperDict.suffix_lessOrEqual_key, endTime);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(ViewKeyDict.total, getSO().getCount(map));
		result.put(ViewKeyDict.rows, getSO().getList(map));
		return result;
	}
}
