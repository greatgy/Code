package com.supergenius.web.front.user.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.genius.core.base.utils.CookieUtil;
import com.genius.core.base.utils.DateUtil;
import com.genius.core.base.utils.NetUtil;
import com.genius.core.base.utils.RegexUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.enums.EStatus;
import com.genius.server.base.controller.BaseController;
import com.supergenius.global.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.server.user.helper.BaseUserHP;
import com.supergenius.web.front.user.helper.UserHP;
import com.supergenius.xo.base.enums.EUser;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.enums.EUserAisle;
import com.supergenius.xo.user.enums.EUserChannel;
import com.supergenius.xo.user.service.UserInfoSO;
import com.supergenius.xo.user.service.UserSO;

import weibo4j.Account;
import weibo4j.Users;
import weibo4j.examples.oauth2.Log;
import weibo4j.http.AccessToken;
import weibo4j.model.WeiboException;

/**
 * 微博登录Controller
 * 
 * @author ChenQi
 * @date 2018年3月13日14:56:46
 */
@Controller
public class WeiBoLoginController extends BaseController {

	@Autowired
	private UserSO userSO;
	@Autowired
	private UserInfoSO userInfoSO;

	/**
	 * 微博登录
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = { "/login/weibo" }, method = RequestMethod.GET)
	public String login_weibo(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setContentType("text/html;charset=utf-8");
		weibo4j.Oauth oauth = new weibo4j.Oauth();
		try {
			String referrer = request.getHeader("Referer");
			if (StrUtil.isNotEmpty(referrer)) {
				CookieUtil.addCookie(response, ViewKeyDict.referer, referrer);
			}
			response.sendRedirect(oauth.authorize("code", "true"));
		} catch (WeiboException e) {
			if (401 == e.getStatusCode()) {
				Log.logInfo("Unable to get the access token.");
			} else {
				e.printStackTrace();
			}
		}

		return null;
	}

	/**
	 * 微博登录回调
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = { "/afterlogin/weibo" }, method = RequestMethod.GET)
	public String AfterLogin(Map<String, Object> model, String code, String state, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		weibo4j.Oauth oauth = new weibo4j.Oauth();
		String referer = CookieUtil.get(request, ViewKeyDict.referer);
		CookieUtil.removeCookie(response, ViewKeyDict.referer);
		try {
			AccessToken accessToken = oauth.getAccessTokenByCode(code);
			if (accessToken != null) {
				String access_token = accessToken.getAccessToken();
				Account am = new Account();
				am.client.setToken(access_token);
				JSONObject uidJson = am.getUid();
				String uid = String.valueOf(uidJson.get("uid"));
				Users um = new Users();
				um.client.setToken(access_token);
				weibo4j.model.User weibouser = um.showUserById(uid);
				if (weibouser != null) {
					String openid = weibouser.getIdstr();
					String nickname = weibouser.getScreenName();
					String otheravatar = weibouser.getAvatarLarge();
					User user = BaseUserHP.getCurrUser(request);
					Map<String, Object> map = new HashMap<String, Object>();
					if (user != null) { // 已经登录时进行绑定sina
						if (StrUtil.isNotEmpty(referer)) {
							Boolean result = RegexUtil.isMatch(String.format("^%s$", "http://user.supergenius.cn/my/user/safeqa"), referer);
							if (!result) {
								return redirectPrefix + referer;
							}
						}
						if (UserHP.getUserBySina(openid) != null) {
							model.put(MsgKeyDict.err_sina_alreadybind, true);
						} else {
							user.setSina(openid);
							if (userSO.update(user)) {
								model.put(MsgKeyDict.success_sina_bind, true);
								BaseUserHP.freshSessUser(request, user);
							}
						}
						model.put(MsgKeyDict.sina_nickname, nickname);
						return "safeqa";// 绑定页面
					} else {
						user = UserHP.getUserBySina(openid);
						if (user != null) {
							if (user.getIsDeleted()) {// 删除状态的用户
								map.put(MsgKeyDict.err_user_delete, true);
							} else if (user.getIsDisable() || user.getFreeze() > 0) {// 冻结状态的用户
								map.put(MsgKeyDict.err_user_disable, true);
							} else {// 正常登陆
								BaseUserHP.online(user, request, response);
								user.setLastlogintime(DateUtil.NowTime());
								user.setLastloginip(NetUtil.getIPAddr(request));
								if (userSO.updateFields(user)) {
									Cookie cookieRedirect = CookieUtil.getCookie(request, ViewKeyDict.redirect);
									if (cookieRedirect != null && StrUtil.isNotEmpty(cookieRedirect.getValue())) { // 登录后跳转回之前页面
										CookieUtil.removeCookie(response, ViewKeyDict.redirect);
										return redirectPrefix + cookieRedirect.getValue();
									}
									if (StrUtil.isNotEmpty(referer)) {
										return redirectPrefix + referer;
									} else {
										return redirectPrefix + "/my/home";
									}
								}
							}
						} else { // 使用微信注册用户
							user = new User();
							user.setType(Integer.valueOf(EUser.consumer.toString()));// 默认普通注册用户
							user.setStatus(EStatus.start);
							user.setChannelfrom(EUserChannel.userfee);
							if (UserHP.register(user, false)) {
								user.getUserInfo().setUid(user.getUid());
								user.getUserInfo().setCreatetime(user.getCreatetime());
								user.getUserInfo().setUpdatetime(user.getUpdatetime());
								user.getUserInfo().setOthernicks(nickname);
								user.getUserInfo().setOtheravatar(otheravatar);
								user.getUserInfo().setSina(openid);
								user.getUserInfo().setAisle(Integer.parseInt(EUserAisle.sina.toString()));
								if (userInfoSO.add(user.getUserInfo())) {
									BaseUserHP.online(user, request, response);
									model.put(ViewKeyDict.user, user);
									if (StrUtil.isNotEmpty(referer)) {
										return redirectPrefix + referer;
									} else {
										return "registersuccess";
									}
								}
							}
						}
						model.put(ViewKeyDict.err, map);
						return "login";
					}
				}
			} else {
				model.put(ViewKeyDict.errmsg,"accessToken = null");
			}
		} catch (WeiboException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return "login";
	}

}
