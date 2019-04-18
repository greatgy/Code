package com.genius.server.base.helper;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.core.base.constant.BaseViewKeyDict;
import com.genius.core.base.utils.StrUtil;
import com.genius.core.session.SessionFactory;
import com.genius.core.session.constant.SessDict;
import com.genius.core.session.rule.SessionRule;
import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;

/**
 * 所有PX的基类，注入了ApplicationContext对象
 * 
 * @author architect.bian
 * @createtime 2014-8-25 下午7:50:58
 */
@Service
public class BaseHP {
	
	protected static ApplicationContext spring;
	
	@Autowired(required=true)
	public void setContent(ApplicationContext content) {
		spring = content;
	}
	
	/**
	 * @param e
	 */
	public static void logException(Logger log, Exception e) {
		e.printStackTrace();
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		log.error(sw.toString());
	}

	/**
	 * 
	 * @param allstatus 是否包含所有的status
	 * @return
	 */
	protected static Map<String, Object> getParamMap(boolean... allstatus) {
		Map<String, Object> map = new HashMap<>();
		map.put(BaseMapperDict.ascDesc, BaseMapperDict.desc);
		map.put(BaseMapperDict.startIndex, 0);
		if (allstatus.length == 0 || !allstatus[0]) {
			map.put(BaseMapperDict.status, EStatus.enable);
			//todo:replace with nostatus=disable
		}
		return map;
	}
	
	/**
	 * 构建查询参数，keys对应于参数名及数据库字段名,map不包含status字段
	 * keys是需要模糊查询的字段，是一个可变参数类型，也就是说可以根据多个字段进行模糊查询
	 * 而且这些字段在相应的实体类的映射文件中都做好了配置
	 * @param pager
	 * @param model
	 * @param keys 返回like的字段
	 * @return
	 */
	protected static Map<String, Object> getParamMap(Pager pager, Map<?, ?> model, String... keys) {
		Map<String, Object> map = getParamMap(pager, true);
		for (String k : keys) {
			if (StrUtil.isNotEmpty(model.get(k))) {
				map.put(k + BaseMapperDict.suffix_like_key, model.get(k).toString().trim());
			}
		}
		return map;
	}
	
	//打印不分页
	protected static Map<String, Object> getPrintMap(Map<?, ?> model, String... keys) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.remove(BaseMapperDict.status);
		for (String k : keys) {
			if (StrUtil.isNotEmpty(model.get(k))) {
				map.put(k + BaseMapperDict.suffix_like_key, model.get(k).toString().trim());
			}
		}
		return map;
	}
	
	/**
	 * @param pager
	 * @return
	 */
	protected static Map<String, Object> getParamMap(Pager pager, boolean... allstatus) {
		Map<String, Object> map = getParamMap(allstatus);
		map.put(BaseViewKeyDict.startIndex, pager.getStartIndex());
		map.put(BaseMapperDict.pageSize, pager.getPageSize());
		return map;
	}

	/**
	 * 获取到session
	 * @param request
	 * @return
	 * @author Architect.bian
	 * 2014-6-27 下午4:48:14
	 */
	public static HttpSession getSession(HttpServletRequest request) {
		return SessionFactory.getHttpSession(request);
	}

	/**
	 * 获取客户端显示的验证码
	 * @param request
	 * @return
	 * @author Architect.bian
	 * 2014-6-27 下午4:48:28
	 */
	public static String getCaptcha(HttpServletRequest request) {
		String sessCaptchaKey = new SessionRule(request, SessDict.CAPTCHA).toString();
		return (String) getSession(request).getAttribute(sessCaptchaKey);
	}

}
