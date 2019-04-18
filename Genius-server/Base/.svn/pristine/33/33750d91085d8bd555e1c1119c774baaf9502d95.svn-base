package com.genius.server.base.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import com.genius.core.base.conf.BaseWebConf;

/**
 * Freemarker view的处理类,可添加变量
 * 
 * @author architect.bian
 * @createtime 2014-8-25 下午7:44:07
 * @modifier liushaomin
 */
public class FtlView extends FreeMarkerView {

	@Override
	protected void doRender(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String basePath = request.getContextPath();
		model.putAll(BaseWebConf.getBasePath(basePath));
		super.doRender(model, request, response);
	}

}
