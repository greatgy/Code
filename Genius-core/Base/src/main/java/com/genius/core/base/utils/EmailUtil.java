package com.genius.core.base.utils;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.MimeUtility;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.genius.core.base.conf.BaseSysConf;
import com.genius.core.base.constant.SysConst;
import com.genius.core.base.utils.task.SendEmailThread;

/**
 * 
 * @author architect.bian
 * @createtime 2014-8-25 下午2:37:15
 */
public class EmailUtil extends BaseUtil {

	private static final String REGEX_URL = "(?i)^(ftp|http|file|jar)://.*";
	private static final String DEFAULT_ATTACHMENT = "attachment";
	private static Logger log = LoggerFactory.getLogger(EmailUtil.class);
	
	/**
	 * 发送普通系统邮件
	 * @param toemail
	 * @param title
	 * @param content
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-7-16 下午12:48:05
	 */
	public static boolean send(String toemail, String title, String content) {
		return send(BaseSysConf.WebSiteName, toemail, title, content);
	}

	/**
	 * 发送带有附件的邮件
	 * @param fromname
	 * @param toemail
	 * @param title
	 * @param content
	 * @param urls
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-7-16 下午12:50:32
	 */
	public static boolean send(String fromname, String toemail, String title, String content, String... urls) {
		return send(BaseSysConf.EmailHost, BaseSysConf.EmailHostName, BaseSysConf.EmailHostPwd, fromname, toemail, title, content, urls);
	}
	
	/**
	 * 指定附件名称及url发送系统邮件
	 * @param fromname
	 * @param toemail
	 * @param title
	 * @param content
	 * @param name
	 * @param url
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-7-16 下午1:15:14
	 */
	public static boolean send(String toemail, String title, String content, String name, String url) {
		EmailAttachment attachment = new EmailAttachment();
		try {
			attachment.setName(MimeUtility.encodeWord(name, SysConst.UTF8, null));
		} catch (UnsupportedEncodingException e) {
			logException(log, e);
		}
		try {
			attachment.setURL(new URL(url));
		} catch (MalformedURLException e) {
			logException(log, e);
		}
		return send(BaseSysConf.EmailHost, BaseSysConf.EmailHostName, BaseSysConf.EmailHostPwd, BaseSysConf.WebSiteName, toemail, title, content, attachment);
	}
	
	/**
	 * 通过直接传递url使用特定的邮件服务器发送带有附件的邮件
	 * @param host
	 * @param fromemail
	 * @param pwd
	 * @param fromname
	 * @param toemail
	 * @param title
	 * @param content
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-7-16 下午12:48:25
	 */
	public static boolean send(String host, String fromemail, String pwd, String fromname, String toemail, String title, String content, String... urls) {
		List<EmailAttachment> attachments = new ArrayList<>();
		if(urls != null && urls.length > 0) {
			for (String url : urls) {
				if (url != null) {
					try {
						EmailAttachment item = new EmailAttachment();
						String strname = url.substring(url.lastIndexOf(BaseSysConf.Separator_Directory) + 1);
						if (strname.length() == 0) {
							strname = DEFAULT_ATTACHMENT;
						}
						item.setName(MimeUtility.encodeWord(strname, SysConst.UTF8, null));
						if (url.matches(REGEX_URL)) {
							item.setURL(new URL(url));
						} else {
							item.setPath(url);
						}
						attachments.add(item);
					} catch (MalformedURLException e) {
						logException(log, e);
					} catch (UnsupportedEncodingException e) {
						logException(log, e);
					}
				}
			}
		}
		return send(host, fromemail, pwd, fromname, toemail, title, content, attachments.toArray(new EmailAttachment[attachments.size()]));
	}

	/**
	 * 接收EmailAttachment使用特定的邮件服务器发送带有附件的邮件
	 * @param host
	 * @param fromemail
	 * @param pwd
	 * @param fromname
	 * @param toemail
	 * @param title
	 * @param content
	 * @param attachments
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-7-16 下午12:52:05
	 */
	public static boolean send(String host, String fromemail, String pwd, String fromname, String toemail, String title, String content, EmailAttachment... attachments) {
		try {
			HtmlEmail email = new HtmlEmail();
			//smtp host
			email.setHostName(host);
			//登陆邮件服务器的用户名和密码
			email.setAuthentication(fromemail, pwd);
			//接收人
			email.addTo(toemail);
			//设置主题的字符集为UTF-8
	        email.setCharset("UTF-8");
			//发送人
	        email.setFrom(fromemail, fromname);
			//标题
			email.setSubject(title);
			//邮件内容
			email.setHtmlMsg(content);
			//添加附件
			if (attachments != null && attachments.length > 0) {
				for (EmailAttachment item : attachments) {
					email.attach(item);
				}
			}
			email.send();
			log.debug(String.format("success sending Email TO:%s Title:%s", toemail, title));
			return true;
		} catch (EmailException e) {
			log.error(String.format("exception on sending Email TO:%s Title:%s", toemail, title));
			logException(log, e);
			return false;
		}
	}
	
	/**
	 * 使用多线程批量发送邮件：
	 * 	1，若使用此方法，并不需要配置ThreadPoolTaskExecutor，需要applicationContent.xml中增加配置
	 * 		<import resource="classpath*:** /email.xml" />
	 *  2，若使用此方法，需要重新配置ThreadPoolTaskExecutor，则自行添加email.xml
	 *  
	 * @param toEmailList
	 * @param title
	 * @param content
	 * @author chenminchang
	 * @create 2017年3月22日上午11:33:03
	 */
	public static void sendBatchUseThread(String title, String content, String... toEmails) {
		new SendEmailThread(title, content, toEmails).runTask();
	}
}
