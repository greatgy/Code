package com.genius.server.base.interceptor;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.genius.core.base.conf.BaseSysConf;
import com.genius.core.base.conf.MappingConf;
import com.genius.core.base.constant.BaseViewKeyDict;
import com.genius.core.base.constant.SysConst;
import com.genius.core.base.utils.CookieUtil;
import com.genius.core.base.utils.JsonUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.core.base.utils.WebUtil;
import com.genius.server.base.helper.SEOHP;
import com.genius.server.base.helper.SysHP;

/**
 * 所有页面的拦截器
 * 
 * @author architect.bian
 * @createtime 2014-8-26 下午2:18:40
 */
public abstract class BaseWebInterceptor extends HandlerInterceptorAdapter {

	public static final String RedirectPrefix = "redirect:";
	private static Logger log = LoggerFactory.getLogger(BaseWebInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if(!isLogin(request) && !isAutoLogin(request, response)) {//判断用户是否登录，或是否自动登录
			if (isLoginNeedUrl(request.getRequestURI())) {
				log.info(String.format("URL:%s need login, writing cookie and redirecting to login...", request.getRequestURL().toString()));
				if (isNeedInitPageVarUrl(request.getRequestURI())) {
					CookieUtil.addCookieSess(response, BaseViewKeyDict.redirect, request.getRequestURL().toString()); //记录登录后自动跳转的地址
				}
				if (isAjaxRequest(request)) {
	        		OutputStream stream = response.getOutputStream();
	        		stream.write(JsonUtil.toJson(ajaxNeedLoginHandler()).getBytes(SysConst.UTF8));
	        		stream.flush();
	        		stream.close();
				} else {
					loginHandle(request, response);
				}
				return false;
			}
		} else { //用户已登录，判断请求是否是仅会员可以访问
			if (urlForMember(request.getRequestURI())) {
				handleMemberUrl(request, response);
			}
		}
		return super.preHandle(request, response, handler);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if (isNeedInitPageVarUrl(request.getRequestURI())) {
			InitPageVar(request, modelAndView);
		}
//		response.addHeader("Access-Control-Allow-Origin", "*");
		super.postHandle(request, response, handler, modelAndView);
	}

	/**
	 * 默认登录跳转，可重写
	 * @param request
	 * @param response
	 * @throws Exception
	 * @author chenminchang
	 * @create 2017年2月23日下午5:49:34
	 */
	protected void loginHandle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.sendRedirect(request.getContextPath() + "/login");
	}
	
	/**
	 * 对仅会员可访问的url处理 可重写
	 * @param request
	 * @param response
	 * @throws Exception
	 * @author YangGuang
	 * @create 2018年7月25日16:22:52
	 */
	protected void handleMemberUrl(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
	}
	
	/**
	 * 退出处理
	 * @param request
	 * @param response
	 * @author: Architect.bian
	 * 2014-6-23 下午5:07:09
	 */
	protected void logoutHandler(HttpServletRequest request, HttpServletResponse response) {
		
	}

	/**
	 * @param requestURI
	 * @return
	 */
//	private boolean isLogoutUrl(String requestURI) {
//		String[] strs = MappingConf.mappingProps.get(MappingConf.USER_LOGOUT_URL).toString().split(",");
//		if (StrUtil.isMatchAny(requestURI, strs)) {
//			return true;
//		}
//		return false;
//	}

	/**
	 * 判断是否登录，需重写
	 * @param request
	 * @return
	 * @author Architect.bian
	 */
	protected boolean isLogin(HttpServletRequest request) {
		return true;
	}

	/**
	 * 判断是否自动登录，默认为false，没有自动登录功能
	 * @param request
	 * @param response
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-8-11 下午12:31:23
	 */
	protected boolean isAutoLogin(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * @param request
	 * @param modelAndView 
	 */
	private void InitPageVar(HttpServletRequest request, ModelAndView modelAndView) {
		if (!isAdminUrl(request.getRequestURI())) {
			initFrontPageVars(request, modelAndView);
		}
	}

	/**
	 * 初始化前台页面变量
	 * @param request
	 * @author: Architect.bian
	 * 2014-6-23 下午5:05:47
	 * @param modelAndView 
	 */
	protected void initFrontPageVars(HttpServletRequest request, ModelAndView modelAndView) {
//		if (WebUtil.isMobileRequest(request)) {
//			response.sendRedirect("http://m.genius.com/xxx");
//		}
		if (modelAndView != null) {
			SysHP.initWebPageVars(modelAndView.getModel());
			if (BaseSysConf.SerialSEOPath != null) {
				SEOHP.initialize(request.getServletPath(), modelAndView.getModel());
			}
		}
	}
	
	/**
	 * 是否是ajax请求
	 * @param request
	 * @return
	 * @author liushaomin
	 */
	protected boolean isAjaxRequest(HttpServletRequest request){
		return WebUtil.isAjaxRequest(request);
	}
	
	/**
	 * 若请求是ajax请求并需要登录，则封装需要返回的参数
	 * @return
	 * @author liushaomin
	 */
	protected Map<String, Object> ajaxNeedLoginHandler() {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(BaseViewKeyDict.islogin, false);
		return result;
	}

	/**
	 * 需要登录的uri
	 * @param requestURI
	 * @return
	 */
	private boolean isLoginNeedUrl(String requestURI) {
		return MappingConf.isLoginNeedUrl(requestURI);
	}
	
	/**
	 * 会员可访问的uri
	 * @param requestURI
	 * @return
	 */
	private boolean urlForMember(String requestURI) {
		return MappingConf.urlForMember(requestURI);
	}
	
	/**
	 * 需要初始化页面的uri
	 * @param uri
	 * @return
	 * @author: Architect.bian
	 * 2014-6-23 下午5:01:38
	 */
	protected boolean isNeedInitPageVarUrl(String uri) {
		return MappingConf.isNeedInitPageVarUrl(uri);
	}

	/**
	 * @param request
	 * @return
	 */
	private boolean isAdminUrl(String requestURI) {
		return MappingConf.isAdminUrl(requestURI);
	}
	
	protected boolean isRedirectView(String view) {
		return StrUtil.startsWith(view, RedirectPrefix);
	}

}
