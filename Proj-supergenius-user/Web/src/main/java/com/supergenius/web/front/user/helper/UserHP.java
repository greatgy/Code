package com.supergenius.web.front.user.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.genius.core.base.utils.DateUtil;
import com.genius.core.base.utils.EmailUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.core.cache.rule.AutoIncrRule;
import com.genius.core.cache.rule.Rule;
import com.genius.core.cache.utils.RedisUtil;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.global.conf.SysConf;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.ESerialID;
import com.supergenius.xo.user.entity.Content;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.entity.News;
import com.supergenius.xo.user.enums.EContent;
import com.supergenius.xo.user.service.ContentSO;
import com.supergenius.xo.user.service.UserInfoSO;
import com.supergenius.xo.user.service.UserSO;
import com.supergenius.xo.user.service.NewsSO;

/**
 * 用户服务类
 * 
 * @author liubin
 * 
 */
public class UserHP extends BaseHP {

	private static Logger log = LoggerFactory.getLogger(UserHP.class);
	private static String[] number = { "1", "2", "3", "6", "8", "9" };
	private static UserSO so;
	private static UserInfoSO userInfoSO;
	private static ContentSO contentSO;
	private static NewsSO newsSO;

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
	public static NewsSO getNewsSO() {
		if (newsSO == null) {
			newsSO = (NewsSO) spring.getBean(NewsSO.class);
		}
		return newsSO;
	}
	
	public static ContentSO getContentSO() {
		if (contentSO == null) {
			contentSO = spring.getBean(ContentSO.class);
		}
		return contentSO;
	}

	/**
	 * 根据uid获取User信息
	 * 
	 * @param uid
	 * @return
	 */
	public static User getUser(String uid) {
		User user = getSO().get(uid);
		return user;
	}
	
	/**
	 * 根据identityid获取User信息
	 * 
	 * @param identityid
	 * @return
	 */
	public static User getUserByIdentityid(String identityid) {
		Map<String, Object> map = getParamMap(true);
		map.put(MapperDict.identityid, identityid);
		User user = getSO().getOne(map);
		return user;
	}
	
	/**
	 * 根据openid获取User信息
	 * 
	 * @param qq
	 * @return
	 */
	public static User getUserByQQ(String qq) {
		Map<String, Object> map = getParamMap(true);
		map.put(MapperDict.qq, qq);
		User user = getSO().getOne(map);
		return user;
	}
	
	/**
	 * 根据openid获取User信息
	 * 
	 * @param wx
	 * @return
	 */
	public static User getUserByWX(String wx) {
		Map<String, Object> map = getParamMap(true);
		map.put(MapperDict.wx, wx);
		User user = getSO().getOne(map);
		return user;
	}
	
	/**
	 * 根据openid获取User信息
	 * 
	 * @param sina
	 * @return
	 */
	public static User getUserBySina(String sina) {
		Map<String, Object> map = getParamMap(true);
		map.put(MapperDict.sina, sina);
		User user = getSO().getOne(map);
		return user;
	}

	/**
	 * 插入数据库 发送邮件
	 * 
	 * @param user
	 * @param
	 */
	public static boolean register(User user, boolean bool) {
		user.setUsersn(getHRHUsersn());
		if (getSO().add(user)) {
			if (bool) {
				return sendValidEmail(user);
			}
			return true;
		}
		return false;
	}

	/**
	 * 验证邮箱
	 * 
	 * @param user
	 * @return
	 */
	public static boolean sendValidEmail(User user) {
		try {
			if (StringUtils.isEmpty(user.getName())) {
				user.setName("");
			}
			String URL = "{URL}";
			String NAME = "{NAME}";
			Content content = getContentSO().getOneByType(EContent.email_validate);
			String title = NAME;
			String msg = URL;
			 if (content != null) {
			 title = content.getTitle().replace(NAME, user.getName());
			 msg = content.getContent();
			 }
			msg = msg.replace(URL, String.format(SysConf.EmailValidateUrlFormat, user.getUid()));
			msg = msg.replace(NAME, user.getName());
			EmailUtil.send(user.getEmail(), title, msg);
			return true;
		} catch (Exception e) {
			logException(log, e);
			return false;
		}
	}

	/**
	 * 验证重新绑定的邮箱
	 * 
	 * @param user
	 * @return
	 */
	public static boolean sendRebindingEmail(User user) {
		try {
			String URL = "{URL}";
			String NAME = "{NAME}";
			Content content = getContentSO().getOneByType(EContent.email_rebinding);
			String title = NAME;
			String msg = URL;
			title = content.getTitle().replace(NAME, user.getName());
			msg = content.getContent();
			msg = msg.replace(URL, String.format(SysConf.EmailRebindingUrlFormat, user.getUid(), user.getValidcode()));
			msg = msg.replace(NAME, user.getName());
			EmailUtil.send(user.getNewemail(), title, msg);
			return true;
		} catch (Exception e) {
			logException(log, e);
			return false;
		}
	}
	
	/**
	 * 找回支付密码邮件
	 * @return
	 * @author chenminchang
	 * @create 2016年12月2日上午10:33:56
	 */
	public static boolean sendFindPayPwdEmail(User user) {
		try {
			String URL = "{URL}";
			String NAME = "{NAME}";
			Content content = getContentSO().getOneByType(EContent.email_findpaypwd);
			String title = NAME;
			String msg = URL;
			title = content.getTitle().replace(NAME, user.getName());
			msg = content.getContent();
			msg = msg.replace(URL, String.format(SysConf.EmailFinaPayPwdUrlFormat, user.getUid(), user.getDataMap_resetpaypwdcode()));
			msg = msg.replace(NAME, user.getName());
			EmailUtil.send(user.getEmail(), title, msg);
			return true;
		} catch (Exception e) {
			logException(log, e);
			return false;
		}
	}
	
	/*
	 * 网页进入邮箱
	 */
	public static String getEmailLoginUrl(String email) {
		return SysConf.EmailMainMap.get(email.subSequence(email.indexOf("@"), email.length()));
	}
	
	/**
	 * 普通会员号生成规则 三个字符HRH-普通会员
	 * 
	 * @return
	 */
	public synchronized static String getHRHUsersn() {
		String prefix = DateUtil.NowTime().toString(DateUtil.FORMAT_DATE_SHORT);
		long id = getID(ESerialID.usersn);
		if (id >= 9999) {
			resetID(ESerialID.usersn);
		}
		int index = (int) (Math.random() * number.length);
		String rand = number[index];
		String usersn = "HRH" + prefix + StrUtil.format("0000", id) + rand;
		log.info("getHRHUsersn:" + usersn);
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
	 * 发送重置密码邮件
	 * 
	 * @param user
	 * @return
	 * @author XieMing
	 */
	public static boolean resetPwdEmail(User user) {
		try {
			if (StringUtils.isEmpty(user.getName())) {
				user.setName("");
			}
			String URL = "{URL}";
			String NAME = "{NAME}";
			Content content = getContentSO().getOneByType(EContent.email_pwdreset);
			String title = NAME;
			String msg = URL;
			 if (content != null) {
				 title = content.getTitle().replace(NAME, user.getName());
				 msg = content.getContent();
			 }
			msg = msg.replace(URL, String.format(SysConf.EmailPwdResetUrlFormat, user.getOid(), user.getResetpwd()));
			msg = msg.replace(NAME, user.getName());
			EmailUtil.send(user.getEmail(), title, msg);
			return true;
		} catch (Exception e) {
			logException(log, e);
			return false;
		}
	}

	/**
	 * 获取我的消息
	 *
	 * @param useruid
	 * @param pagenum
	 * @param pagesize
	 * @return
	 * @author YangGuang
	 * @date 2018年5月16日15:47:26
	 */
	public static List<News> getMsgs(String useruid, int pagenum, int pagesize) {
		Map<String, Object> map = getParamMap(Pager.getNewInstance(pagenum, pagesize));
		map.put(MapperDict.touid, useruid);
		List<News> list = getNewsSO().getList(map);
		return list;
	}

	/**
	 * @param uid
	 * @return
	 */
	public static News getOneNew(String uid) {
		Map<String ,Object> map = new HashMap<>();
		map.put(MapperDict.uid,uid);
		map.put(MapperDict.isread,true);
		getNewsSO().updateFields(map);
		return getNewsSO().get(uid);
	}
	public static boolean deleteOneNew(String uid) {
		Map<String ,Object> map = new HashMap<>();
		map.put(MapperDict.uid,uid);
		map.put(MapperDict.status,EStatus.deleted);
		if (getNewsSO().updateFields(map)){
			return true;
		}
		return false;
	}
}
