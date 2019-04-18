package com.supergenius.server.user.helper;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.genius.core.base.conf.BaseSysConf;
import com.genius.core.base.constant.BaseViewKeyDict;
import com.genius.core.base.utils.CookieUtil;
import com.genius.core.base.utils.DateUtil;
import com.genius.core.base.utils.NetUtil;
import com.genius.core.cache.rule.Rule;
import com.genius.core.cache.rule.UserRule;
import com.genius.core.cache.utils.RedisUtil;
import com.genius.core.session.constant.SessDict;
import com.genius.core.session.rule.SessionRule;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.enums.EGender;
import com.supergenius.xo.user.service.UserSO;

/**
 * 用户HP
 * 
 * @author ShangJianguo
 * @modifier architect.bian
 */
public class BaseUserHP extends BaseHP {

	private static Logger log = LoggerFactory.getLogger(BaseUserHP.class);
	private static UserSO so;

	private static final String strSessKeyID = ".id";
	private static final int cookieValueLength = 6;// Session在客户端cookie value值的长度

	private static UserSO getSO() {
		if (so == null) {
			so = (UserSO) spring.getBean(UserSO.class);
		}
		return so;
	}

	public static User get(int oid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("oid", oid);
		return getSO().getOne(map);
	}

	public static User get(String uid) {
		return getSO().get(uid);
	}

	/**
	 * 通过uid从redis缓存中获得具有uid oid name avatar属性的User对象 books skills 等属性调用时再获取
	 * 
	 * @param uid
	 * @return
	 */
	public static User getFromRedis(String uid) {
		try {
			if (StringUtils.isEmpty(uid)) {
				return null;
			}
			Rule rule = new UserRule(uid);// TODO:因hash不能设置expire，所以要定时器清除数据
			User user = null;
			if (RedisUtil.getHLen(rule) == 14) {// Warning: 每次增删字段都需要修改此处的数字
				user = new User();
				user.setOid(RedisUtil.getHInt(rule, MapperDict.oid));
				user.setShowname(RedisUtil.getHStr(rule, MapperDict.showname));
				user.setUsersn(RedisUtil.getHStr(rule, MapperDict.usersn));
				user.setName(RedisUtil.getHStr(rule, MapperDict.name));
				user.setEmail(RedisUtil.getHStr(rule, MapperDict.email));
				user.setType(Integer.valueOf(RedisUtil.getHInt(rule, MapperDict.type)));
				user.setGender(EGender.get(RedisUtil.getHStr(rule, MapperDict.gender)));
				user.setAvatar(RedisUtil.getHStr(rule, MapperDict.avatar));
				user.setIdentityid(RedisUtil.getHStr(rule, MapperDict.identityid));
				user.setNickname(RedisUtil.getHStr(rule, MapperDict.nickname));
				user.setCompany(RedisUtil.getHStr(rule, MapperDict.company));
				user.setDepartment(RedisUtil.getHStr(rule, MapperDict.department));
				user.setJob(RedisUtil.getHStr(rule, MapperDict.job));
				user.setSummary(RedisUtil.getHStr(rule, MapperDict.summary));
				/*user.setPhone(RedisUtil.getHStr(rule, MapperDict.phone));
				user.setMobile(RedisUtil.getHStr(rule, MapperDict.mobile));
				user.setQq(RedisUtil.getHStr(rule, MapperDict.qq));
				user.setAccount(Double.valueOf(RedisUtil.getHStr(rule, MapperDict.account)));
				user.setTotalpay(Double.valueOf(RedisUtil.getHStr(rule, MapperDict.totalpay)));
				user.setTotalincome(Double.valueOf(RedisUtil.getHStr(rule, MapperDict.totalincome)));*/
			} else {
				user = BaseUserHP.get(uid);
				if (user != null) {
					Map<String, Object> userMap = new HashMap<String, Object>();
					userMap.put(MapperDict.oid, user.getOid());
					userMap.put(MapperDict.showname, user.getShowname());
					userMap.put(MapperDict.usersn, user.getUsersn());
					userMap.put(MapperDict.name, user.getName());
					userMap.put(MapperDict.email, user.getEmail());
					userMap.put(MapperDict.type, user.getType());
					userMap.put(MapperDict.gender, user.getGender());
					userMap.put(MapperDict.avatar, user.getAvatar());
					userMap.put(MapperDict.identityid, user.getIdentityid());
					userMap.put(MapperDict.nickname, user.getNickname());
					userMap.put(MapperDict.company, user.getCompany());
					userMap.put(MapperDict.department, user.getDepartment());
					userMap.put(MapperDict.job, user.getJob());
					userMap.put(MapperDict.summary, user.getSummary());
					/*userMap.put(MapperDict.phone, user.getPhone());
					userMap.put(MapperDict.mobile, user.getMobile());
					userMap.put(MapperDict.qq, user.getQq());
					userMap.put(MapperDict.account, user.getAccount());
					userMap.put(MapperDict.totalpay, user.getTotalpay());
					userMap.put(MapperDict.totalincome, user.getTotalincome());*/
					List<Object> keyOrValueList = new LinkedList<Object>();
					for (Map.Entry<String, Object> entry : userMap.entrySet()) {
						keyOrValueList.add(entry.getKey());
						keyOrValueList.add(entry.getValue());
					}
					RedisUtil.setMHash(rule, keyOrValueList.toArray());
				}
			}
			return user;
		} catch (Exception e) {
			logException(log, e);
		}
		return null;
	}

	/**
	 * 设置某个用户在线
	 * 
	 * @param user
	 * @param request
	 * @param response
	 * @author Architect.bian
	 * @createtime 2014-11-3 下午7:46:43
	 */
	public static void online(User user, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(SessDict.SESSION_ID, createCookieValue(user));
		addSession(request, user);
		// UserPX.incrLoginCount(user);
		CookieUtil.addCookieSess(response, SessDict.SESSION_ID, request.getAttribute(SessDict.SESSION_ID).toString());
		if (user.getIsUser()) {
			CookieUtil.addCookie(response, ViewKeyDict.memberCookie, ViewKeyDict.member);
		}
		// CookieUtil.addCookieSess(response,
		// SessConst.currid,String.valueOf(user.getOid()));
		// getSO().updateLastLogin(map);
	}

	/**
	 * 设置离线，登录退出
	 * 
	 * @param request
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-11-3 下午8:09:38
	 */
	public static boolean offline(HttpServletRequest request) {
		getSession(request).removeAttribute(getCurrSessKey(request));// 删除/session/{cookie.sid}:user的值
																		// TODO：要做判断request的cookie是否与缓存中的值是否相等
		getSession(request).removeAttribute(getCurrSessKey(request) + strSessKeyID);// 删除/session/{cookie.sid}:user.id的值
		return true;
	}

	private static void addSession(HttpServletRequest request, User user) {
		getSession(request).setAttribute(getCurrSessKey(request), user.getUid());// 设置/session/{cookie.sid}:user的值为user.uid
		getSession(request).setAttribute(getCurrSessKey(request) + strSessKeyID, user.getOid());// 设置/session/{cookie.sid}:user.id的值为user.oid
		getSession(request).setAttribute(getCurrSessKey(user.getUid(), SessDict.USER), user);// (此条只是缓存当前用户，与登录无关)设置/session/{user.uid}:user的值为user
	}

	/**
	 * 获取当前登录用户
	 * 
	 * @param request
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-11-3 下午8:12:50
	 */
	public static User getCurrUser(HttpServletRequest request) {
		String useruid = (String) getSession(request).getAttribute(getCurrSessKey(request));// 获取当前登录用户的uid
		if (StringUtils.isNotEmpty(useruid)) {
			//User user = (User) getSession(request).getAttribute(getCurrSessKey(useruid, SessDict.USER));// 获取缓存中user对象
			User user = null;
			if (user == null) {
				user = BaseUserHP.get(useruid);
				getSession(request).setAttribute(getCurrSessKey(useruid, SessDict.USER), user);// 设置/session/{user.uid}:user的值为user
			}
			return user;
		}
		return null;
	}

	/**
	 * 刷新session中保存的用户的值
	 * 
	 * @param request
	 * @param user
	 * @author Architect.bian
	 * @createtime 2014-11-3 下午8:14:56
	 */
	public static void freshSessUser(HttpServletRequest request, User user) {
		if (user != null) {
			getSession(request).setAttribute(getCurrSessKey(user.getUid(), SessDict.USER), user);// 刷新session中user的值，即设置/session/{user.uid}:user的值为user
			getSession(request).setAttribute(getCurrSessKey(request), user.getUid());
			request.setAttribute(BaseViewKeyDict.me, user);
			freshRedisCache(user.getUid());
		} else {
			offline(request);
		}
	}

	/**
	 * 用户访问配置页面时刷新缓存
	 * 
	 * @param uid
	 */
	public static void freshRedisCache(String uid) {
		Rule rule = new UserRule(uid);// 用户保存在redis的uid oid name等及skill book信息
		RedisUtil.delete(rule);
	}

	/**
	 * 创建cookie客户端的值，一个随机数
	 * 
	 * @param u
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-11-3 下午7:54:02
	 */
	private static String createCookieValue(User u) {
		int random = (new Random(DateUtil.Now())).nextInt(u.getUid().length() - cookieValueLength - 1);
		String id = DateUtil.Now() + u.getUid().substring(random, random + cookieValueLength);
		return DigestUtils.md5Hex(id);
	}

	/**
	 * 通过request获取在session中cookie.sid值对应的session key
	 * 
	 * @param request
	 * @return 类似于:/s/f4785b06c14dc6a08c424ab8ef160d8d:user
	 */
	private static String getCurrSessKey(HttpServletRequest request) {
		if (StringUtils.isNotEmpty(request.getParameter(SessDict.SESSION_ID))) {
			return request.getParameter(SessDict.SESSION_ID);
		} else {
			return new SessionRule(request, SessDict.USER, BaseSysConf.Expire_UserSession).getKey();
		}
	}

	/**
	 * 
	 * @param sid
	 * @param key
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-11-3 下午8:02:56
	 */
	public static String getCurrSessKey(String sid, String key) {
		if (sid != null) {
			return new SessionRule(sid, key, BaseSysConf.Expire_UserSession).getKey();
		}
		return null;
	}

	/**
	 * 判断主流邮箱
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmailCommon(String email) {
		Set<String> keys = BaseSysConf.EmailMainMap.keySet();
		for (String k : keys) {
			if (email.indexOf(k) > 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 自动登录
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author YuYingJie
	 */
	public static boolean autoLogin(HttpServletRequest request, HttpServletResponse response) {
		String useroid = CookieUtil.get(request, ViewKeyDict.userid);
		String userpwd = CookieUtil.get(request, ViewKeyDict.userpwd);
		String endTime = CookieUtil.get(request, ViewKeyDict.endtime);
		String useruid = "";
		if (useroid == null || userpwd == null || endTime == null) {
			return false;
		}
		DateTime dateTime = DateUtil.NowTime();
		// 判断时间
		if (dateTime.isAfter(Long.valueOf(endTime))) {
			return false;
		}
		User u = BaseUserHP.get(Integer.valueOf(useroid));
		if (u == null) {
			return false;
		}
		useruid = u.getUid();
		String userAgent = request.getHeader("User-Agent");
		String ip = NetUtil.getIPAddr(request);
		String userPasswd = DigestUtils.md5Hex(userAgent + endTime + useruid + ip);
		if (userpwd.equals(userPasswd)) {
			online(u, request, response);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 更新user的type
	 * @param useruid
	 * @param type
	 * @return
	 */
	public static boolean updateUserType(String useruid, int type) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.uid, useruid);
		map.put(MapperDict.type, type);
		return getSO().updateFields(map);
	}
	
}