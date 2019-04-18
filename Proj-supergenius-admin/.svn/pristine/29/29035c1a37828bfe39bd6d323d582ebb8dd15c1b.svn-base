package com.supergenius.web.admin.manager.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.genius.core.base.utils.EmailUtil;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.xo.user.entity.Content;
import com.supergenius.xo.user.enums.EContent;
import com.supergenius.xo.user.service.ContentSO;

/**
 * manager邮件相关hp
 * 
 * @author chenminchang
 * @date 2016-8-30 下午5:18:50
 */
public class EmailHP extends BaseHP {

	private static Logger log = LoggerFactory.getLogger(EmailHP.class);
	private static ContentSO contentSO;

	public static ContentSO getContentSO() {
		if (contentSO == null) {
			contentSO = spring.getBean(ContentSO.class);
		}
		return contentSO;
	}
	
	/**
	 * 证书发放成功，给获得证书的人发送邮件
	 * @param name
	 * @param major
	 * @param type
	 * @param toEmail
	 * @return
	 * @author liubin
	 * @createtime 2016-11-9下午2:28:34
	 * @return boolean
	 */
	public static boolean sendAwardCertificateSuccess(String name, String major, String type, String toEmail) {
		try {
			String NAME = "{NAME}";
			String MAJOR = "{MAJOR}";
			String TYPE = "{TYPE}";
			Content content = getContentSO().getOneByType(EContent.email_awardcertificatesuccess);
			String title = content.getTitle();
			String msg = content.getContent();
			msg = msg.replace(NAME, name);
			msg = msg.replace(MAJOR, major);
			msg= msg.replace(TYPE, type);
			EmailUtil.send(toEmail, title, msg);
			return true;
		} catch(Exception e) {
			logException(log, e);
			return false;
		}
	}
	
	/**
	 * 审核材料成功相关，给申请人发送邮件
	 * @param name
	 * @param time
	 * @param url
	 * @param toEmail
	 * @return
	 * @author liubin
	 * @createtime 2016-11-12下午2:52:42
	 * @return boolean
	 */
	public static boolean sendCheckReplyFileAboutSuccess(String name, String time, String url, String toEmail, EContent type) {
		try {
			String NAME = "{NAME}";
			String TIME = "{TIME}";
			String URL = "{URL}";
			Content content = getContentSO().getOneByType(type);
			String title = content.getTitle();
			String msg = content.getContent();
			msg = msg.replace(NAME, name);
			msg = msg.replace(TIME, time);
			msg = msg.replace(URL, url);
			EmailUtil.send(toEmail, title, msg);
			return true;
		} catch(Exception e) {
			logException(log, e);
			return false;
		}
	}
	
	/**
	 * 指定为答辩专家，给专家发送邮件
	 * @param name
	 * @param reftitle
	 * @param time
	 * @param address
	 * @param url
	 * @param toEmail
	 * @return
	 * @author liubin
	 * @createtime 2016-11-12下午2:58:45
	 * @return boolean
	 */
	public static boolean sendSpecifyReplyExpertSuccess(String name, String reftitle, String time, String address, String toEmail) {
		try {
			String NAME = "{NAME}";
			String TITLE = "{TITLE}";
			String ADDRESS = "{ADDRESS}";
			String TIME = "{TIME}";
			Content content = getContentSO().getOneByType(EContent.email_specifyreplyexpert);
			String title = content.getTitle();
			String msg = content.getContent();
			msg = msg.replace(NAME, name);
			msg = msg.replace(TITLE, reftitle);
			msg = msg.replace(ADDRESS, address);
			msg = msg.replace(TIME, time);
			EmailUtil.send(toEmail, title, msg);
			return true;
		} catch(Exception e) {
			logException(log, e);
			return false;
		}
	}
	
	/**
	 * 更换专家，给被更换的专家发送邮件
	 * @param name
	 * @param reftitle
	 * @param url
	 * @param toEmail
	 * @return
	 * @author liubin
	 * @createtime 2016-11-12下午3:01:09
	 * @return boolean
	 */
	public static boolean sendChangeSpecifyReplyExpert(String name, String reftitle, String toEmail) {
		try {
			String NAME = "{NAME}";
			String TITLE = "{TITLE}";
			Content content = getContentSO().getOneByType(EContent.email_changespecifyreplyexpert);
			String title = content.getTitle();
			String msg = content.getContent();
			msg = msg.replace(NAME, name);
			msg = msg.replace(TITLE, reftitle);
			EmailUtil.send(toEmail, title, msg);
			return true;
		} catch(Exception e) {
			logException(log, e);
			return false;
		}
	}
	
	/**
	 * 满足答辩要求，发送邮件通知
	 * @param name
	 * @param major
	 * @param level
	 * @param url
	 * @param toEmail
	 * @return
	 * @author liubin
	 * @createtime 2016-11-13下午2:01:44
	 * @return boolean
	 */
	public static boolean sendCanAppreply(String name, String major, String level, String url, String toEmail) {
		try {
			String NAME = "{NAME}";
			String MAJOR = "{MAJOR}";
			String LEVEL = "{LEVEL}";
			String URL = "{URL}";
			Content content = getContentSO().getOneByType(EContent.email_canappreply);
			String title = content.getTitle().replace(LEVEL, level);
			String msg = content.getContent();
			msg = msg.replace(NAME, name);
			msg = msg.replace(MAJOR, level);
			msg = msg.replace(LEVEL, major);
			msg = msg.replace(URL, url);
			EmailUtil.send(toEmail, title, msg);
			return true;
		} catch(Exception e) {
			logException(log, e);
			return false;
		}
	}
	
	/**
	 * 发送会议室邀请
	 * @param username
	 * @param datatime
	 * @param refname
	 * @param refurl
	 * @return
	 * @author chenminchang
	 * @create 2016-11-14下午7:14:23
	 */
	public static boolean sendInviteUser(String name, String datatime, String refname, String refurl, EContent emailtype, String toEmail) {
		try {
			String NAME = "{NAME}";
			String DATATIME = "{DATATIME}";
			String REFNAME = "{REFNAME}";
			String REFURL = "{REFURL}";
			Content content = getContentSO().getOneByType(emailtype);
			String msg = content.getContent();
			msg = msg.replace(NAME, name);
			msg = msg.replace(DATATIME, datatime);
			msg = msg.replace(REFNAME, refname);
			msg = msg.replace(REFURL, refurl);
			EmailUtil.send(toEmail, content.getTitle(), msg);
			return true;
		} catch(Exception e) {
			logException(log, e);
			return false;
		}
	}
	
	/**
	 * 挑战确定，发送qq群会议室账号（裁判和挑战人员）
	 * @param name
	 * @param pkname
	 * @param toEmail
	 * @param qqGroup
	 * @return
	 * @author liubin
	 * @createtime 2016-11-16下午5:38:19
	 * @return boolean
	 */
	public static boolean sendCreatPKConferenceSuccess(String name, String pkname, String toEmail, String qqGroup, String href) {
		try {
			String NAME = "{NAME}";
			String PKNAME = "{PKNAME}";
			String QQGROUP = "{QQGROUP}";
			String HREF = "{HREF}";
			Content content = getContentSO().getOneByType(EContent.email_pkqqgroupoktouser);
			String msg = content.getContent();
			msg = msg.replace(NAME, name);
			msg = msg.replace(PKNAME, pkname);
			msg = msg.replace(QQGROUP, qqGroup);
			msg = msg.replace(HREF, href);
			EmailUtil.send(toEmail, content.getTitle(), msg);
			return true;
		} catch(Exception e) {
			logException(log, e);
			return false;
		}
	}
	
	/**
	 * 开题论证会确定，发送qq群会议室账号（答辩者和专家）
	 * @param name
	 * @param replyName
	 * @param toEmail
	 * @param qqGroup
	 * @return
	 * @author liubin
	 * @createtime 2016-11-16下午6:22:09
	 * @return boolean
	 */
	public static boolean sendCreatReplyConferenceSuccess(String name, String replyName, String toEmail, String qqGroup, String href) {
		try {
			String NAME = "{NAME}";
			String REPLYNAME = "{REPLYNAME}";
			String QQGROUP = "{QQGROUP}";
			String HREF = "{HREF}";
			Content content = getContentSO().getOneByType(EContent.email_openreplyqqgroupok);
			String msg = content.getContent();
			msg = msg.replace(NAME, name);
			msg = msg.replace(REPLYNAME, replyName);
			msg = msg.replace(QQGROUP, qqGroup);
			msg = msg.replace(HREF, href);
			EmailUtil.send(toEmail, content.getTitle(), msg);
			return true;
		} catch(Exception e) {
			logException(log, e);
			return false;
		}
	}
	
	/**
	 * 设定挑战结果以及发放奖励相关邮件
	 * @param name
	 * @param pkname
	 * @param toEmail
	 * @param href
	 * @param type
	 * @return
	 * @author liubin
	 * @createtime 2016年12月6日下午12:14:33
	 * @return boolean
	 */
	public static boolean sendPKGetResult(String name, String pkname, String toEmail, String href, EContent type) {
		try {
			String NAME = "{NAME}";
			String PKNAME = "{PKNAME}";
			String HREF = "{HREF}";
			Content content = getContentSO().getOneByType(type);
			String msg = content.getContent();
			msg = msg.replace(NAME, name);
			msg = msg.replace(PKNAME, pkname);
			msg = msg.replace(HREF, href);
			EmailUtil.send(toEmail, content.getTitle(), msg);
			return true;
		} catch(Exception e) {
			logException(log, e);
			return false;
		}
	}
}