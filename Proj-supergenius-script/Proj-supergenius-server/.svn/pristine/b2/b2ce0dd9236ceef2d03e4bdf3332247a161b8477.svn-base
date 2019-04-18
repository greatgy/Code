package com.supergenius.server.manager.helper;

import java.util.ArrayList;
import java.util.List;

import com.genius.server.base.helper.BaseHP;
import com.supergenius.xo.manager.entity.AppReplyExpert;
import com.supergenius.xo.manager.entity.Expert;
import com.supergenius.xo.manager.service.AppReplyExpertSO;
import com.supergenius.xo.manager.service.AppReplySO;
import com.supergenius.xo.manager.service.ExpertSO;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.service.UserSO;

/**
 * 与答辩相关的交易明细及订单变动HP
 * 
 * @author liubin
 * @createtime 2016-11-14下午7:33:28
 */
public class BaseReplyHP extends BaseHP {

	private static AppReplySO so;
	private static AppReplyExpertSO appReplyExpertSO;
	private static ExpertSO expertSO;
	private static UserSO userSO;

	private static AppReplySO getSO() {
		if (so == null) {
			so = spring.getBean(AppReplySO.class);
		}
		return so;
	}
	
	private static AppReplyExpertSO getAppReplyExpertSO() {
		if (appReplyExpertSO == null) {
			appReplyExpertSO = spring.getBean(AppReplyExpertSO.class);
		}
		return appReplyExpertSO;
	}
	
	private static ExpertSO getExpertSO() {
		if (expertSO == null) {
			expertSO = spring.getBean(ExpertSO.class);
		}
		return expertSO;
	}
	
	private static UserSO getUserSO() {
		if (userSO == null) {
			userSO = spring.getBean(UserSO.class);
		}
		return userSO;
	}

	/**
	 * 得到答辩so
	 * @return
	 * @author liubin
	 * @createtime 2016-11-14下午7:42:06
	 * @return AppReplySO
	 */
	public static AppReplySO getAppReplySO() {
		return getSO();
	}
	
	/**
	 * 得到答辩的专家
	 * @param appreplyuid
	 * @return
	 * @author liubin
	 * @createtime 2016-11-14下午8:14:43
	 * @return List<Expert>
	 */
	public static List<User> getReplyExperts(String appreplyuid) {
		List<AppReplyExpert> appReplyExperts = getAppReplyExpertSO().getList(appreplyuid, false);
		List<User> list = new ArrayList<User>();
		for (AppReplyExpert appReplyExpert : appReplyExperts) {
			Expert expert = getExpertSO().get(appReplyExpert.getExpertuid());
			if (expert != null) {
				User user = getUserSO().get(expert.getUseruid());
				if (user != null) {
					list.add(user);
				}
			}
		}
		return list;
	}
}
