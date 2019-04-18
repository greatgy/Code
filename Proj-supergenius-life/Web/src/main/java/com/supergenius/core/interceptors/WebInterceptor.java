package com.supergenius.core.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.genius.core.base.conf.BaseSysConf;
import com.genius.core.base.conf.BaseUriConf;
import com.genius.core.base.constant.BaseStrDict;
import com.genius.core.base.utils.WebUtil;
import com.genius.server.base.helper.SEOHP;
import com.genius.server.base.interceptor.BaseWebInterceptor;
import com.supergenius.global.conf.WebConf;
import com.supergenius.global.helper.SysHP;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.server.user.helper.BaseUserHP;
import com.supergenius.xo.user.entity.User;

/**
 * @author Architect.bian
 * 
 */
public class WebInterceptor extends BaseWebInterceptor {
	

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (RedirectHandle(request, response))
			return super.preHandle(request, response, handler);
		return false;
	}
	
	@Override
	protected void initFrontPageVars(HttpServletRequest request, ModelAndView modelAndView) {
		if (modelAndView != null && modelAndView.getViewName() != null && !isRedirectView(modelAndView.getViewName())) {
			SysHP.initWebPageVars(modelAndView.getModel());
			if (BaseSysConf.getSEOPath("life") != null) {
				modelAndView.getModel().put(ViewKeyDict.site, "life");
				SEOHP.initialize(request.getServletPath(), modelAndView.getModel());
			}
		}
		super.initFrontPageVars(request, modelAndView);
	}
	
	/**
	 * @param request
	 * @return
	 */
	protected boolean isLogin(HttpServletRequest request) {
		if (WebUtil.isMobileRequest(request)) {
			request.setAttribute(ViewKeyDict.phone , true);
		}
		User user = BaseUserHP.getCurrUser(request);
		if (user != null) {
			request.setAttribute(ViewKeyDict.me, user);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 自动登录是否成功
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author YuYingJie
	 */
	@Override
	protected boolean isAutoLogin(HttpServletRequest request, HttpServletResponse response) {
		if (BaseUserHP.autoLogin(request, response)) {
			return isLogin(request);
		} else {
			return false;
		}
	}
	
	/**
	 * 判断pc端和手机端请求，跳转相对应的页面
	 * @param request
	 * @param response
	 * @throws Exception
	 * @author YangGuang
	 * @create 2018年6月12日09:53:37
	 */
	protected boolean RedirectHandle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String urlPath = request.getServletPath();
		if (WebUtil.isMobileRequest(request)) {
			if (!urlPath.startsWith(BaseUriConf.baseMobilePath + BaseStrDict.slash)) {
				response.sendRedirect(request.getContextPath() + BaseUriConf.baseMobilePath + urlPath);
				return false;
			}
				
		} else if (WebUtil.idIpad(request)) {
			if (!urlPath.startsWith(BaseUriConf.baseMobilePath + BaseStrDict.slash)) {
				response.sendRedirect(request.getContextPath() + BaseUriConf.baseMobilePath + urlPath);
				return false;
			}
		}else {
			if (urlPath.startsWith(BaseUriConf.baseMobilePath + BaseStrDict.slash)) {
				response.sendRedirect(request.getContextPath() + urlPath.replaceFirst(BaseUriConf.baseMobilePath, ""));
				return false;
			} 
		}
		return true;
	}
	
	/**
	 * 默认登录跳转
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author tf
	 */
	@Override
	protected void loginHandle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.sendRedirect(WebConf.UserBaseRootPath + "/login");
	}
	
	/**
	 * 对仅会员可访问的url处理
	 * @param request
	 * @param response
	 * @throws Exception
	 * @author tf
	 * @create 2018年7月25日16:22:52
	 */
	@Override
	protected void handleMemberUrl(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//判断当前用户是否是会员
		User user = BaseUserHP.getCurrUser(request);
		if (!user.getIsUser()) {
			response.sendRedirect(WebConf.LifeBaseRootPath+"/notmember");
		}
	}
	
}