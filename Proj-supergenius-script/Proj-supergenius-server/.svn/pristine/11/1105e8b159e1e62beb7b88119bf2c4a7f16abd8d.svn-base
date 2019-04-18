package com.supergenius.server.common.helper;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.genius.core.base.utils.WebUtil;
import com.genius.core.cache.rule.OneRule;
import com.genius.core.cache.rule.Rule;
import com.genius.core.cache.utils.MemcacheUtil;
import com.google.common.base.Charsets;
import com.google.common.io.Files;

/**
 * 
 * @author liubin
 * @date 2016-4-19 下午4:41:15
 */
public class CommonHP {

	private static Logger log = LoggerFactory.getLogger(CommonHP.class);

	/**
	 * 是否包含敏感词
	 * 
	 * @param source
	 * @return
	 */
	public static boolean containBlackWords(String source) {
		return WebUtil.containWords(source, CommonHP.getBlackWords());
	}

	/**
	 * 返回blackwords的列表
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<String> getBlackWords() {
		Rule rule = new OneRule("blackwords");
		List<String> list = null;
		try {
			list = (List<String>) MemcacheUtil.get(rule);
			if (list == null) {
				list = Files.readLines(new File(CommonHP.class.getClassLoader().getResource("webconfig/blackwords").getPath()), Charsets.UTF_8);
				MemcacheUtil.set(rule, list);
			}
		} catch (IOException e) {
			e.printStackTrace();
			logException(log, e);
		} catch (Exception e) {
			e.printStackTrace();
			logException(log, e);
		}
		return list;
	}

	public static boolean clearBlackWords() {
		Rule rule = new OneRule("blackwords");
		return MemcacheUtil.remove(rule);
	}

	/**
	 * 输出log4j的日志
	 * 
	 * @param log
	 * @param e
	 */
	public static void logException(Logger log, Exception e) {
		e.printStackTrace();
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		log.error(sw.toString());
	}

}
