package com.genius.server.portal.helper;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.genius.core.base.utils.WebUtil;
import com.genius.core.cache.conf.BaseCacheConf;
import com.genius.core.cache.rule.OneRule;
import com.genius.core.cache.rule.Rule;
import com.genius.core.cache.utils.MemcacheUtil;
import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class BlackwordsHP {

	private static Logger log = LoggerFactory.getLogger(BlackwordsHP.class);
	
	private static List<String> cacheList = null;
	
	/**
	 * 返回blackwords的列表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<String> getBlackWords() {
		Rule rule = new OneRule("blackwords");
		try {
			if (BaseCacheConf.cacheProps != null) {
				List<String> list = (List<String>) MemcacheUtil.get(rule);
				if (list == null) {
					list = Files.readLines(new File(BlackwordsHP.class.getClassLoader().getResource("webconfig/blackwords").getPath()), Charsets.UTF_8);
					MemcacheUtil.set(rule, list);
				}
				return list;
			} else {
				if (cacheList == null) {
					cacheList = Files.readLines(new File(BlackwordsHP.class.getClassLoader().getResource("webconfig/blackwords").getPath()), Charsets.UTF_8);
				}
				return cacheList;
			}
		} catch(IOException e) {
			e.printStackTrace();
			logException(log, e);
		} catch (Exception e) {
			e.printStackTrace();
			logException(log, e);
		}
		return null;
	}
	
	public static boolean clearBlackWords() {
		Rule rule = new OneRule("blackwords");
		return MemcacheUtil.remove(rule);
	}
	

	/**
	 * 是否包含敏感词的列表
	 * @param source
	 * @param words
	 * @return
	 */
	public static boolean containBlackWords(String source) {
		if (source == null) {
			return false;
		}
		String filteredstr = WebUtil.clearHtml(source.toLowerCase()).toString();
		List<String> words = BlackwordsHP.getBlackWords();
		for (String item : words) {
			if (filteredstr.contains(item)) {
				return true;
			}
		}
		return false;
	}


	public static String getBlackwordsContent(){
		String path = BlackwordsHP.class.getClassLoader().getResource("webconfig/blackwords").getPath();
		File file = new File(path);	
		try {
			return Files.toString(file, Charsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
			logException(log, e);
		}
		return null;
	}
	
	/**
	 * 保存敏感词文件
	 * @param content
	 */
	
	public static boolean saveblackwords(String content) {
		String path = BlackwordsHP.class.getClassLoader().getResource("webconfig/blackwords").getPath();
		File file = new File(path);	
		try {
			Files.write(content, file, Charsets.UTF_8);
			clearBlackWords();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			logException(log, e);
		}
		return false;
	}
	
	/**
	 * 输出log4j的日志
	 * @param log
	 * @param e
	 */
	private static void logException(Logger log, Exception e) {
		e.printStackTrace();
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		log.error(sw.toString());
	}
}
