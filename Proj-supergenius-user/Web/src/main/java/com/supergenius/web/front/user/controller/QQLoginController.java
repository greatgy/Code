package com.supergenius.web.front.user.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;
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

/**
 * qq登录Controller
 * 
 * @author YangGuang
 * @date 2018年3月13日14:56:46
 */
@Controller
public class QQLoginController extends BaseController {

	@Autowired
	private UserSO userSO;
	@Autowired
	private UserInfoSO userInfoSO;

	/**
	 * qq登录
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = { "/login/qq" }, method = RequestMethod.GET)
	public String login(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		try {
			String referrer = request.getHeader("Referer");
			if (StrUtil.isNotEmpty(referrer)) {
				CookieUtil.addCookie(response, ViewKeyDict.referer, referrer);
			}
			response.sendRedirect(new Oauth().getAuthorizeURL(request));
		} catch (QQConnectException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * qq登录回调
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = { "/afterlogin/qq" }, method = RequestMethod.GET)
	public String AfterLogin(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);
			String accessToken = null, openID = null;
			String referer = CookieUtil.get(request, ViewKeyDict.referer);
			CookieUtil.removeCookie(response, ViewKeyDict.referer);
			// long tokenExpireIn = 0L;
			if (StrUtil.isEmpty(accessTokenObj.getAccessToken())) {
				System.out.print("没有获取到响应参数");
			} else {
				accessToken = accessTokenObj.getAccessToken();
				// tokenExpireIn = accessTokenObj.getExpireIn();
				OpenID openIDObj = new OpenID(accessToken);
				openID = openIDObj.getUserOpenID();
				UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
				UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
				String name = userInfoBean.getNickname();
				String avatar = userInfoBean.getAvatar().getAvatarURL30();
				User user = BaseUserHP.getCurrUser(request);
				Map<String, Object> map = new HashMap<String, Object>();
				if (user != null) { // 已经登录时进行绑定qq
					if (StrUtil.isNotEmpty(referer)) {
						Boolean result = RegexUtil.isMatch(String.format("^%s$", "http://user.supergenius.cn/my/user/safeqa"), referer);
						if (!result) {
							return redirectPrefix + referer;
						}
					}
					if (UserHP.getUserByQQ(openID) != null) {
						model.put(MsgKeyDict.err_qq_alreadybind, true);
					} else {
						user.setQq(openID);
						if (userSO.update(user)) {
							model.put(MsgKeyDict.success_qq_bind, true);
							BaseUserHP.freshSessUser(request, user);
						}
					}
					model.put(MsgKeyDict.qq_nickname, name);
					return "safeqa";// 绑定页面
				} else {
					user = UserHP.getUserByQQ(openID);
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
					} else { // 使用qq注册用户
						user = new User();
						user.setType(Integer.valueOf(EUser.consumer.toString()));// 默认普通注册用户
						user.setStatus(EStatus.start);
						user.setChannelfrom(EUserChannel.userfee);
						if (UserHP.register(user, false)) {
							user.getUserInfo().setUid(user.getUid());
							user.getUserInfo().setCreatetime(user.getCreatetime());
							user.getUserInfo().setUpdatetime(user.getUpdatetime());
							user.getUserInfo().setOthernicks(name);
							user.getUserInfo().setOtheravatar(avatar);
							user.getUserInfo().setQq(openID);
							user.getUserInfo().setAisle(Integer.parseInt(EUserAisle.qq.toString()));
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}