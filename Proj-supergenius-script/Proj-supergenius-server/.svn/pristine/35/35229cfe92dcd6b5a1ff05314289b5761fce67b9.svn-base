package com.supergenius.server.tpi.helper;


import java.util.Map;

import com.genius.server.base.helper.BaseHP;
import com.supergenius.xo.tpi.entity.EmailTemplate;
import com.supergenius.xo.tpi.enums.ESysEmailType;
import com.supergenius.xo.tpi.service.EmailTemplateSO;

/**
 * 邮件模板HP
 * @author liushaomin
 */
public class BaseEmailTemplateHP extends BaseHP{
	
	private static EmailTemplateSO so;
	
	private static EmailTemplateSO getSO() {
		if (so == null) {
			so = (EmailTemplateSO) spring.getBean(EmailTemplateSO.class);
		}
		return so;
	}
	
	/**
	 * 根据类型获取到邮件的模板
	 * 再从Map中遍历需要替换的邮件内容
	 * 返回加工后的邮件
	 * @param type
	 * @param map
	 * @return processedemail  返回加工后的邮件
	 * @author LiuXiaoke
	 */
	public static EmailTemplate getProcessedEmail(ESysEmailType type, Map<String, String> map) {
		EmailTemplate processedemail = getSO().getOneByType(type);
		for (String key : map.keySet()) {
			if (processedemail!=null && processedemail.getContent()!=null)
				processEmail(map, processedemail, key);
		}
		return processedemail;
	}
	
	/**
	 * 对邮件进行加工
	 * 将map中的key作为正则表达式进行匹配
	 * 并用map中的value替换匹配目标
	 * @param map
	 * @param processedemail
	 * @param key
	 * @author LiuXiaoke
	 */
	private static void processEmail(Map<String, String> map, EmailTemplate processedemail, String key) {
		processedemail.setContent(processedemail.getContent().replaceAll("\\{" + key + "\\}", map.get(key)));
	}
	
}
