package com.supergenius.web.front.user.helper;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.genius.server.base.helper.BaseHP;
import com.supergenius.global.conf.SysConf;

/**
 * 会员中心首页服务类
 * 
 * @author ChenQi
 * @date 2018年3月28日12:03:58
 * 
 */
public class IndexHP extends BaseHP {

	/**
	 * 得到内容
	 * @param directory
	 * @return
	 * @author ChenQi
	 */
	public static String getContent(String path) {
		try {
			File file = new File(SysConf.FileSiteBasePath + path);
			if (file.exists()) {
				List<String> lines = FileUtils.readLines(file, "UTF-8");
				StringBuffer content = new StringBuffer();
				if (lines != null) {
					for (String item : lines) {
						content.append(item);
					}
				}
				return content.toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
