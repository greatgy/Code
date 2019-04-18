package com.genius.server.base.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.genius.core.base.conf.BaseWebConf;
import com.opensymphony.module.sitemesh.freemarker.FreemarkerDecoratorServlet;

import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateModel;

/**
 * 重写sitemesh对freemarker的处理类
 * 
 * @author architect.bian
 * @createtime 2014-8-25 下午7:38:30
 */
public class SiteMeshFreemarkerServlet extends FreemarkerDecoratorServlet {

	private static final long serialVersionUID = 602800774258454089L;

	/**
	 * 可在此处添加内置变量，或者在@see com.genius.core.views.FtlView 中添加内置变量
	 */
	@Override
	protected boolean preTemplateProcess(HttpServletRequest request, HttpServletResponse response, Template template,
			TemplateModel templateModel) throws ServletException, IOException {
		if(super.preTemplateProcess(request, response, template, templateModel)) {
			SimpleHash hash = (SimpleHash) templateModel;
			if (StringUtils.isNotEmpty(BaseWebConf.baseRootPath)) {
				hash.put("base", BaseWebConf.baseRootPath);
			}
			return true;
		}
		return false;
	}
}
