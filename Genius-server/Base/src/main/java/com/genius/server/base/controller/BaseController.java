package com.genius.server.base.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import com.genius.core.base.conf.BaseWebConf;
import com.genius.core.base.constant.BaseViewKeyDict;
import com.genius.core.base.constant.SysConst;
import com.genius.core.base.utils.BaseLog;
import com.genius.core.base.utils.JsonUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.server.base.helper.BaseHP;

/**
 * @author Architect.bian
 * 
 */
public abstract class BaseController extends BaseLog {// extends ParameterizableViewController

	protected static final String redirectPrefix = "redirect:";

	/**
	 * 设置header:Content-Type:text/plain;charset=UTF-8
	 * 
	 * @param model
	 * @param response
	 */
	protected void manifestHandler(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) {
		// Cookie statuscookie = CookieUtil.getCookie(request, "");
		// if (statuscookie == null || !statuscookie.getValue().equals("200")) {
		// model.put(ViewKeyDict.createMillis, new DateTime().getMillis());
		// }
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
	}

	/**
	 * 设置header:Content-Type:application/xml; charset=UTF-8
	 * 
	 * @param model
	 * @param response
	 */
	protected void xmlHandler(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("application/xml");
		response.setCharacterEncoding(SysConst.UTF8);
	}

	/**
	 * 
	 * @param result
	 * @param model
	 */
	// protected void validateErrors(BindingResult result, Map<String, String>
	// errs) {
	// List<FieldError> fes = result.getFieldErrors();
	// for (FieldError fe : fes) {
	// String k = fe.getField();
	// String v = errs.get(k);
	// String[] strs = StringUtils.splitByWholeSeparator(v,
	// ValidConst.VALID_MSG_SPLITTER);
	// strs = ArrayUtils.add(strs, fe.getDefaultMessage());
	// v = StringUtils.join(strs, ValidConst.VALID_MSG_SPLITTER);
	// errs.put(k, v);
	// }
	// }

	/**
	 * 页面保留初始数据
	 * 
	 * @param model
	 * @param params
	 */
	protected void cloneParamsToModel(Map<String, Object> model, Map<?, ?> params) {
		for (Object key : params.keySet()) {
			if (params.get(key).getClass().isArray()) {
				String[] arr = (String[]) params.get(key);
				if (arr.length == 1) {
					model.put(key.toString(), arr[0]);
				} else {
					model.put(key.toString(), arr);
				}
			}
		}
	}

	/**
	 * 页面保留初始数据
	 * 
	 * @param model
	 * @param params
	 */
	protected void cloneParamsToModel(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request.getParameterMap());
	}

	protected String response404(HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		return "404";
	}

	/**
	 * 获得项目路径，如：/zizhuan
	 * 
	 * @param request
	 * @return
	 */
	protected String getBasePath(HttpServletRequest request) {
		String basePath = StringUtils.isEmpty(BaseWebConf.baseImgRootPath) ? request.getContextPath() : BaseWebConf.baseImgRootPath;
		return basePath;
	}

	/**
	 * 获取session
	 * 
	 * @param request
	 * @return
	 * @author Architect.bian 2014-6-27 下午5:06:23
	 */
	protected HttpSession getSession(HttpServletRequest request) {
		return BaseHP.getSession(request);
	}

	/**
	 * 判断验证码是否正确
	 * @param request
	 * @param code
	 * @return
	 * @author liushaomin
	 */
	protected boolean isCaptchaEquals(HttpServletRequest request, String code) {
		if (StrUtil.isEmpty(code)) {
			return false;
		}
		return BaseHP.getCaptcha(request).equals(code.trim().toLowerCase());
	}
	
	/**
	 * 获取验证码
	 * @param request
	 * @return
	 * @author Architect.bian 2014-6-27 下午5:06:35
	 */
	protected String getCaptcha(HttpServletRequest request) {
		return BaseHP.getCaptcha(request);
	}

	/**
	 * 将需要输出为json对象的strategy放入headers中
	 * 
	 * @param response
	 * @param string
	 * @author Architect.bian
	 * @createtime 2014-6-30 下午7:19:57
	 */
	protected <T> ResponseEntity<T> json(T t, String strategy) {
		MultiValueMap<String, String> headers = new HttpHeaders();
		headers.add(BaseViewKeyDict._jsonStrategy, strategy);
		return new ResponseEntity<T>(t, headers, HttpStatus.OK);
	}

	/**
	 * 直接返回含有msgkey的map，如操作成功或失败时直接返回信息
	 * 主要用在管理后台的json请求
	 * @param msgkey
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-7-25 下午1:34:43
	 */
	protected Map<String, Object> result(String msgkey) {
		Map<String, Object> map = new HashMap<>();
		map.put(msgkey, true);
		return map;
	}

	/**
	 * 直接返回成功
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-7-25 下午1:38:09
	 */
	protected Map<String, Object> success() {
		return result(BaseViewKeyDict.success);
	}
	
	/**
	 * 返回封装的ajax跨域请求返回值
	 * @param map
	 * @param request
	 * @return
	 * @author YuYingJie
	 */
	protected String jsonp(Map<String, Object> map, HttpServletRequest request) {
		return jsonp(JsonUtil.toJson(map), request);
	}
	
	/**
	 * 返回封装的ajax跨域请求返回值
	 * @param data
	 * @param request
	 * @return
	 * @author YuYingJie
	 */
	protected String jsonp(String data, HttpServletRequest request) {
		String callback = request.getParameter("callback");
		return callback + "(" + data + ")";
	}
	
}
