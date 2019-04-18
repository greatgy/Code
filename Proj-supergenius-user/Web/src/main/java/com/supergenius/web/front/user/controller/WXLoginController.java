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
import com.genius.core.base.utils.HttpUtil;
import com.genius.core.base.utils.JsonUtil;
import com.genius.core.base.utils.NetUtil;
import com.genius.core.base.utils.RegexUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.enums.EStatus;
import com.genius.server.base.controller.BaseController;
import com.supergenius.global.conf.SysConf;
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
 * 微信登录Controller
 * 
 * @author ChenQi
 * @date 2018年4月3日20:13:34
 */
@Controller
public class WXLoginController extends BaseController {

	@Autowired
	private UserSO userSO;
	@Autowired
	private UserInfoSO userInfoSO;

	/**
	 * 进入微信二维码页面
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = { "/login/wx" }, method = RequestMethod.GET)
	public String QRcode(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) {
		String url = SysConf.QRcodeurl + "?appid=" + SysConf.wxappid + "&redirect_uri=" + SysConf.wxredirect_uri + "&response_type=code&scope=" + SysConf.wxscope + "&style=black";
		response.setContentType("text/html;charset=utf-8");
		try {
			String referrer = request.getHeader("Referer");
			if (StrUtil.isNotEmpty(referrer)) {
				CookieUtil.addCookie(response, ViewKeyDict.referer, referrer);
			}
			response.sendRedirect(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 微信登录回调
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/afterlogin/wx" }, method = RequestMethod.GET)
	public String AfterWXLogin(Map<String, Object> model, String code, String state, HttpServletRequest request,
			HttpServletResponse response) {
		String referer = CookieUtil.get(request, ViewKeyDict.referer);
		CookieUtil.removeCookie(response, ViewKeyDict.referer);
		Map<String, String> params = new HashMap<String, String>();// 获取access_token
		params.put("appid", "wx7ad782443f7fb272");
		params.put("secret", "a75f876ce9c201219ac16b9c1fb12d3b");
		params.put("code", code);
		params.put("grant_type", "authorization_code");
		String result = HttpUtil.get("https://api.weixin.qq.com/sns/oauth2/access_token", params);
		Map<String, String> resultMap = JsonUtil.fromJson(result, Map.class);
		String errcode = String.valueOf(resultMap.get("errcode"));
		if (StrUtil.isEmpty(errcode) || errcode.equals("null")) {
			String access_token = resultMap.get("access_token");
			String refresh_token = resultMap.get("refresh_token");
			String openid = resultMap.get("openid");
			Map<String, String> refreshparams = new HashMap<String, String>();// 刷新或续期access_token使用
			refreshparams.put("appid", "wx7ad782443f7fb272");
			refreshparams.put("grant_type", "refresh_token");
			refreshparams.put("refresh_token", refresh_token);
			String refreshresult = HttpUtil.get("https://api.weixin.qq.com/sns/oauth2/refresh_token", refreshparams);
			Map<String, String> refreshresultMap = JsonUtil.fromJson(refreshresult, Map.class);
			errcode = String.valueOf(refreshresultMap.get("errcode"));
			if (StrUtil.isEmpty(errcode) || errcode.equals("null")) {
				Map<String, String> userinfoparams = new HashMap<String, String>();// 获取用户个人信息
				userinfoparams.put("access_token", access_token);
				userinfoparams.put("openid", openid);
				String userinforesult = HttpUtil.get("https://api.weixin.qq.com/sns/userinfo", userinfoparams);
				Map<String, String> userinforesultMap = JsonUtil.fromJson(userinforesult, Map.class);
				errcode = String.valueOf(userinforesultMap.get("errcode"));
				if (StrUtil.isEmpty(errcode) || errcode.equals("null")) {
					String nickname = userinforesultMap.get("nickname");// 获取用户昵称
					String headimgurl = userinforesultMap.get("headimgurl");// 获取用户头像
					// String unionid =
					// userinforesultMap.get("unionid");用户统一标识。针对一个微信开放平台帐号下的应用，同一用户的unionid是唯一的。以便以后在不同应用中进行用户信息互通。
					User user = BaseUserHP.getCurrUser(request);
					Map<String, Object> map = new HashMap<String, Object>();
					if (user != null) { // 已经登录时进行绑定wx
						if (StrUtil.isNotEmpty(referer)) {
							if (!RegexUtil.isMatch(String.format("^%s$", "http://user.supergenius.cn/my/user/safeqa"), referer)) {
								return redirectPrefix + referer;
							}
						}
						if (UserHP.getUserByWX(openid) != null) {
							model.put(MsgKeyDict.err_wx_alreadybind, true);
						} else {
							user.setWx(openid);
							if (userSO.update(user)) {
								model.put(MsgKeyDict.success_wx_bind, true);
								BaseUserHP.freshSessUser(request, user);
							}
						}
						model.put(MsgKeyDict.wx_nickname, nickname);
						return "safeqa";// 绑定页面
					} else {
						user = UserHP.getUserByWX(openid);
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
								user.getUserInfo().setOtheravatar(headimgurl);
								user.getUserInfo().setWx(openid);
								user.getUserInfo().setAisle(Integer.parseInt(EUserAisle.wx.toString()));
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
					}
				} else {
					model.put(ViewKeyDict.errmsg, userinforesultMap.get("errmsg"));
				}
			} else {
				model.put(ViewKeyDict.errmsg, refreshresultMap.get("errmsg"));
			}
		} else {
			model.put(ViewKeyDict.errmsg, resultMap.get("errmsg"));
		}
		return "login";
	}
}
