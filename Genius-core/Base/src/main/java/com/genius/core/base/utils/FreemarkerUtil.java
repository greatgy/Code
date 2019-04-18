package com.genius.core.base.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.genius.core.base.conf.BaseSysConf;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

/**
 * @author architect.bian
 *
 */
public class FreemarkerUtil extends BaseUtil {

	private static Logger log = LoggerFactory.getLogger(FreemarkerUtil.class);
	
	private static Configuration defaultConfig = null;
	private static StringTemplateLoader strTmplLoader = new StringTemplateLoader();
	private static Configuration ConfigStrTmpl = null;
	
	/**
	 * 返回使用某个文件的模板的转换结果
	 * @param filename
	 * @param map
	 * @return
	 * @throws IOException
	 * @author Architect.bian
	 * 2014-6-24 下午6:50:10
	 */
	public static String process(String filename, Map<String, Object> map) throws IOException {
		Template template = buildTemplate(getDefaultCfg(), filename);
		buildTemplateModel(map);
		return getProcessResult(map, template);
	}
	
	/**
	 * 将freemarker模板输出到某个文件中
	 * @param filename
	 * @param map
	 * @param outputPath
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-7-3 下午4:36:04
	 */
	public static boolean process(String filename, Map<String, Object> map, String outputPath) {
		File file = new File(outputPath);
		return process(filename, map, file);
	}

	/**
	 * 将freemarker模板输出到某个文件中
	 * @param filename
	 * @param map
	 * @param file
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-7-3 下午4:36:19
	 */
	public static boolean process(String filename, Map<String, Object> map, File file) {
		try {
			Template template = buildTemplate(getDefaultCfg(), filename);
			buildTemplateModel(map);
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
			getProcessResult(map, template, out);
			return true;
		} catch (Exception e) {
			logException(log, e);
		}
		return false;
	}
	
	/**
	 * 返回使用某个目录下某个文件的模板的转换结果
	 * @param templateDirectory
	 * @param filename
	 * @param map
	 * @return
	 * @throws IOException
	 * @author Architect.bian
	 * 2014-6-24 下午6:51:27
	 */
	public static String process(String templateDirectory, String filename, Map<String, Object> map) throws IOException {
		Configuration cfg = buildConfiguration(templateDirectory);
		Template template = buildTemplate(cfg, filename);
		buildTemplateModel(map);
		return getProcessResult(map, template);
	}
	
	/**
	 * 返回使用某个目录下某个文件的模板的转换结果 直接输出到某个文件
	 * @param templateDirectory
	 * @param filename
	 * @param map
	 * @param file
	 * @return
	 * @throws IOException
	 * @author liushaomin
	 * 2015-3-30 15:51:27
	 */
	public static boolean process(String templateDirectory, String filename, Map<String, Object> map, File file) throws IOException {
		try {
			Configuration cfg = buildConfiguration(templateDirectory);
			Template template = buildTemplate(cfg, filename);
			buildTemplateModel(map);
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
			getProcessResult(map, template, out);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 返回使用字符串类型的模板的转换结果
	 * @param templateSource
	 * @param map
	 * @return
	 * @throws IOException
	 * @author Architect.bian
	 * 2014-6-24 下午6:49:30
	 */
	public static String processStr(String templateSource, Map<String, Object> map) {
		if (StrUtil.isEmpty(templateSource)) {
			return null;
		}
		String strTmplName = String.valueOf(templateSource.hashCode());
		Template template;
		try {
			template = buildTemplate(getStrTmplCfg(strTmplName, templateSource), strTmplName);
			buildTemplateModel(map);
			return getProcessResult(map, template);
		} catch (IOException e) {
			logException(log, e);
		}
		return null;
	}

	private static Configuration getDefaultCfg() {
		if (defaultConfig == null) {
			defaultConfig = buildConfiguration(BaseSysConf.EmailTemplateDirectoryPath);
		}
		return defaultConfig;
	}

	@SuppressWarnings("deprecation")
	private static Configuration getStrTmplCfg(String strTmplName, String templateSource) {
		if (strTmplLoader.findTemplateSource(strTmplName) == null || ConfigStrTmpl == null) {
			strTmplLoader.putTemplate(strTmplName, templateSource);
			ConfigStrTmpl = new Configuration();
			ConfigStrTmpl.setTemplateLoader(strTmplLoader);
			ConfigStrTmpl.setObjectWrapper(new DefaultObjectWrapper());
			ConfigStrTmpl.setDefaultEncoding("UTF-8");
			ConfigStrTmpl.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
			ConfigStrTmpl.setIncompatibleImprovements(new Version(2, 3, 20));
		}
		return ConfigStrTmpl;
	}

	/**
	 * @param model
	 * @param template
	 * @return
	 * @throws IOException
	 */
	private static String getProcessResult(Map<String, Object> model, Template template) throws IOException {
		if (template == null) {
			return null;
		}
		StringWriter writer = new StringWriter();
		try {
			template.process(model, writer);
			return writer.toString();
		} catch (TemplateException | IOException e) {
			logException(log, e);
			return null;
		} finally {
			writer.close();
		}
	}
	
	/**
	 * @param model
	 * @param template
	 * @return
	 * @throws Exception 
	 */
	private static String getProcessResult(Map<String, Object> model, Template template, Writer writer) throws Exception {
		if (template == null) {
			return null;
		}
		try {
			template.process(model, writer);
			return writer.toString();
		} catch (TemplateException | IOException e) {
			logException(log, e);
			throw e;
		} finally {
			writer.close();
		}
	}

	/**
	 * @param emailTemplateDirectoryPath
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private static Configuration buildConfiguration(String directoryPath) {
		Configuration cfg = new Configuration();
		if (StrUtil.isNotEmpty(directoryPath)) {
			try {
				cfg.setDirectoryForTemplateLoading(new File(directoryPath));
			} catch (IOException e) {
				logException(log, e);
			}
	        cfg.setObjectWrapper(new DefaultObjectWrapper());
	        cfg.setDefaultEncoding("UTF-8");
	        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
	        cfg.setIncompatibleImprovements(new Version(2, 3, 20));
		}
		return cfg;
	}
	
	/**
	 * @param cfg 
	 * @param filename
	 * @return
	 * @throws IOException 
	 */
	private static Template buildTemplate(Configuration cfg, String filename) throws IOException {
		if (cfg != null) {
			return cfg.getTemplate(filename);
		} else {
			return null;
		}
	}

	/**
	 * 初始化基础变量，暂时有需要初始化的值
	 * @param model
	 */
	private static void buildTemplateModel(Map<String, Object> model) {
		//base variable
	}
}