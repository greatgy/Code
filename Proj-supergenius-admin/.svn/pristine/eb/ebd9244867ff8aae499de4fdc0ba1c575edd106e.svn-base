package com.supergenius.web.admin.user.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.genius.core.base.utils.DateUtil;
import com.genius.core.base.utils.EmailUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.core.cache.rule.AutoIncrRule;
import com.genius.core.cache.rule.Rule;
import com.genius.core.cache.utils.RedisUtil;
import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.xo.base.enums.EUser;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.ESerialID;
import com.supergenius.xo.user.entity.Content;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.enums.EContent;
import com.supergenius.xo.user.service.ContentSO;
import com.supergenius.xo.user.service.UserInfoSO;
import com.supergenius.xo.user.service.UserSO;

/**
 * 用户服务类
 * 
 * @author liubin
 * 
 */
public class ConsumerHP extends BaseHP {

	private static Logger log = LoggerFactory.getLogger(AutoIncrRule.class);
	private static String[] number = { "1", "2", "3", "6", "8", "9" };
	private static UserSO so;
	private static UserInfoSO userInfoSO;
	private static ContentSO contentSO;

	public static UserSO getSO() {
		if (so == null) {
			so = spring.getBean(UserSO.class);
		}
		return so;
	}

	public static UserInfoSO getuserInfoSO() {
		if (userInfoSO == null) {
			userInfoSO = spring.getBean(UserInfoSO.class);
		}
		return userInfoSO;
	}

	public static ContentSO getContentSO() {
		if (contentSO == null) {
			contentSO = spring.getBean(ContentSO.class);
		}
		return contentSO;
	}

	/**
	 * 特邀嘉宾的会员号
	 * 
	 * @return
	 */
	public synchronized static String getVONUsersn() {
		String prefix = DateUtil.NowTime().toString(DateUtil.FORMAT_DATE_SHORT);
		long id = getID(ESerialID.usersn);
		if (id >= 9999) {
			resetID(ESerialID.usersn);
		}
		int index = (int) (Math.random() * number.length);
		String rand = number[index];
		String usersn = "VON" + prefix + StrUtil.format("0000", id) + rand;
		log.info("getVONUsersn:" + usersn);
		return usersn;
	}

	private static long getID(ESerialID type) {
		Rule rule = new AutoIncrRule(type.name());
		log.info("getID rule:" + rule.toString());
		return RedisUtil.incr(rule);
	}

	/**
	 * 重置为0，下次自增时返回1
	 * 
	 * @param type
	 * @return
	 */
	private static boolean resetID(ESerialID type) {
		Rule rule = new AutoIncrRule(type.name());
		return RedisUtil.setIncr(rule, 0);
	}

	/**
	 * 发送账户冻结邮件
	 * 
	 * @param user
	 * @return
	 * @author XieMing 2016-5-20 上午10:36:51
	 */
	public static boolean sendEmail(User user, EContent emailtype) {
		try {
			if (StringUtils.isEmpty(user.getName())) {
				user.setName("");
			}
			String URL = "{URL}";
			String NAME = "{NAME}";
			Content content = getContentSO().getOneByType(emailtype);
			String title = NAME;
			String msg = URL;
			if (content != null) {
				title = content.getTitle().replace(NAME, user.getName());
				msg = content.getContent();
			}
			msg = msg.replace(NAME, user.getName());
			EmailUtil.send(user.getEmail(), title, msg);
			return true;
		} catch (Exception e) {
			logException(log, e);
			return false;
		}
	}

	/**
	 * 设置余额邮件
	 * 
	 * @param user
	 * @param caozuo
	 * @return
	 * @author XieMing 2016-5-20 上午11:35:15
	 */
	public static boolean sendAccountEmail(User user, boolean isplus, String number) {
		try {
			if (StringUtils.isEmpty(user.getName())) {
				user.setName("");
			}
			String URL = "{URL}";
			String NAME = "{NAME}";
			String NOW = "{NOW}";
			String CHANGE = "{CHANGE}";
			Content content = getContentSO().getOneByType(EContent.email_tepiaccount);
			String title = NAME;
			String msg = URL;
			if (content != null) {
				title = content.getTitle().replace(NAME, user.getName());
				msg = content.getContent();
			}
			msg = msg.replace(NAME, user.getName());
			msg = msg.replace(NOW, user.getAccount() + "");
			if (!isplus) {
				msg = msg.replace(CHANGE, "-" + number);
			} else {
				msg = msg.replace(CHANGE, number);
			}
			msg = msg.replace(CHANGE, user.getName());
			EmailUtil.send(user.getEmail(), title, msg);
			return true;
		} catch (Exception e) {
			logException(log, e);
			return false;
		}
	}

	/**
	 * 邮件提醒注册未缴费的用户缴费7天和14天
	 * 
	 * @return
	 * @author XieMing 2016-5-20 下午4:54:00
	 */
	public static boolean remindPayEmail() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.status, EStatus.wait);
		List<User> list = getSO().getList(map);
		for (User user : list) {
			DateTime start = user.getCreatetime();
			if (start.plusDays(7).isBeforeNow() && start.plusDays(8).isAfterNow()) {
				sendEmail(user, EContent.email_remindpay);
			}
			if (start.plusDays(14).isBeforeNow() && start.plusDays(15).isAfterNow()) {
				sendEmail(user, EContent.email_remindpay1);
			}
		}
		return true;
	}

	/**
	 * 发送信息邮件
	 * 
	 * @param user
	 * @return
	 */
	public static boolean sentInfoEmail(User user, String title, String content) {
		try {
			if (StringUtils.isEmpty(user.getName())) {
				user.setName("");
			}
			EmailUtil.send(user.getEmail(), title, content);
			return true;
		} catch (Exception e) {
			logException(log, e);
			return false;
		}
	}

	/**
	 * 获取查询结果
	 * 
	 * @param model
	 * @return
	 * @author XieMing 2016-5-15 下午4:26:47
	 */
	public static Map<String, Object> query(Map<String, Object> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model);
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.name))) {
			map.put(MapperDict.username + MapperDict.suffix_like_key, model.get(ViewKeyDict.name).toString());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.email))) {
			map.put(MapperDict.email + MapperDict.suffix_like_key, model.get(ViewKeyDict.email).toString());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.usersn))) {
			map.put(MapperDict.usersn + MapperDict.suffix_like_key, model.get(ViewKeyDict.usersn).toString());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.aisle))) {
			map.put(MapperDict.aisle, model.get(ViewKeyDict.aisle).toString());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.status))) {
			if (model.get(ViewKeyDict.status).toString().equals("all")) {
				
			} else {
				map.put(MapperDict.status, model.get(ViewKeyDict.status).toString());
			}
		}
		map.put(MapperDict.type, EUser.consumer.toString());
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.sisStudent)) && !new Boolean(model.get(ViewKeyDict.sisStudent).toString())) {
			map.put(MapperDict.type + MapperDict.suffix_no_key, EUser.studentMoral.toString());
		} else if (StrUtil.isNotEmpty(model.get(ViewKeyDict.sisStudent)) && new Boolean(model.get(ViewKeyDict.sisStudent).toString())) {
			map.put(MapperDict.type, EUser.studentMoral.toString());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.accountstart))) {
			map.put(MapperDict.account + MapperDict.suffix_greater_key, Double.parseDouble(model.get(ViewKeyDict.accountstart).toString()));
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.accountend))) {
			map.put(MapperDict.account + MapperDict.suffix_less_key, Double.parseDouble(model.get(ViewKeyDict.accountend).toString()));
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(ViewKeyDict.total, getSO().getCount(map));
		List<User> list = getSO().getList(map);
		result.put(ViewKeyDict.rows, list);
		return result;
	}

}
