package com.genius.server.base.exception;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import com.genius.core.base.conf.MappingConf;
import com.genius.core.base.constant.BaseViewKeyDict;
import com.genius.core.base.constant.SysConst;
import com.genius.core.base.utils.JsonUtil;
import com.genius.server.base.helper.SysHP;

import freemarker.core.Environment;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

/**
 * 异常处理
 * 
 * @author architect.bian
 * @createtime 2014-7-24 下午5:52:41
 */
public class SimpleMappingExceptionResolver extends org.springframework.web.servlet.handler.SimpleMappingExceptionResolver implements TemplateExceptionHandler {

	Logger log = LoggerFactory.getLogger(SimpleMappingExceptionResolver.class);

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		// Call super method to get the ModelAndView
		ModelAndView modelAndView = super.doResolveException(request, response, handler, ex);
		// Make the full URL available to the view - note ModelAndView uses
		// addObject()
		// but Model uses addAttribute(). They work the same.
		// mav.addObject("url", request.getRequestURL());
		String uriInfo = "The request path of the exception is:"+ request.getRequestURI();
		StringWriter sw = new StringWriter();
		ex.printStackTrace(new PrintWriter(sw));
		String exception = sw.toString() + uriInfo;
		log.error(exception);
		if (MappingConf.isJsonUrl(request.getRequestURI())) {
			try {
				Map<String, Object> result = jsonRequestExceptionHandler(ex);
				result.put(BaseViewKeyDict.exception, exception);
				// response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				OutputStream stream = response.getOutputStream();
				stream.write(JsonUtil.toJson(result).getBytes(SysConst.UTF8));
				stream.flush();
				stream.close();
				// PrintWriter writer = response.getWriter();
				// writer.write(JsonUtil.toJson(result));
				// writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		} else {
			modelAndView.getModel().put(BaseViewKeyDict.exception, exception);
			System.err.println(exception);
			pageRequestExceptionHandler(request, modelAndView, ex);
		}
		return modelAndView;
	}

	// @Override
	// protected String determineViewName(Exception ex, HttpServletRequest
	// request) {
	// return super.determineViewName(ex, request);
	// }

	/**
	 * 若请求是json请求，则封装需要返回的参数
	 * 
	 * @param ex
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-7-24 下午9:32:29
	 */
	protected Map<String, Object> jsonRequestExceptionHandler(Exception ex) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(BaseViewKeyDict.err, true);
		result.put(BaseViewKeyDict.errmsg, ex.toString());
		return result;
	}

	/**
	 * 若请求是普通页面的请求，则初始化view层需要的数据
	 * 
	 * @param request
	 * @param modelAndView
	 * @author Architect.bian
	 * @param ex
	 * @createtime 2014-7-24 下午8:16:38
	 */
	protected void pageRequestExceptionHandler(HttpServletRequest request, ModelAndView modelAndView, Exception ex) {
		String requestURI = request.getRequestURI();
		if (MappingConf.isAdminUrl(requestURI)) {
			SysHP.initAdminPageVars(modelAndView.getModel());
		} else if (MappingConf.isLoginNeedUrl(requestURI)) {
			SysHP.initMyCenterPageVars(modelAndView.getModel());
		} else if (MappingConf.isNeedInitPageVarUrl(requestURI)) {
			SysHP.initWebPageVars(modelAndView.getModel());
		}
	}

	@Override
	public void handleTemplateException(TemplateException te, Environment env, Writer out) throws TemplateException {
		String[] temp = te.getMessageWithoutStackTop().split("\n");
		String exception = null;
		if (temp.length > 1)
			exception = temp[0] + temp[1];
		log.error(exception);
		System.err.println(exception);
	}
}
