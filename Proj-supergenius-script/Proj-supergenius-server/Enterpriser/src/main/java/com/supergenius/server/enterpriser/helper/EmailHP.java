package com.supergenius.server.enterpriser.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.genius.core.base.utils.EmailUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.xo.user.entity.Content;
import com.supergenius.xo.user.enums.EContent;
import com.supergenius.xo.user.service.ContentSO;

/** 
 * 邮件模板类
 * @author liubin
 * @date 2016-10-26 下午1:23:57 
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
	 * 发送赠品邮件
	 * @param name 姓名
	 * @param sn 订单号
	 * @param toEmial 发送地址
	 * @return
	 * @author liubin
	 * @createtime 2016-10-26下午1:37:39
	 * @return boolean
	 */
	public static boolean sendGift(String lecturename, String name, String sn, String toEmial) {
		try {
			String NAME = "{NAME}";
			String SN = "{SN}";
			String LECTURENAME = "{LECTURENAME}";
			Content content = getContentSO().getOneByType(EContent.email_sendgift);
			String title = content.getTitle();
			title = title.replace(LECTURENAME, lecturename);
			String msg = content.getContent();
			msg = msg.replace(NAME, name);
			msg = msg.replace(SN, sn);
			msg = msg.replace(LECTURENAME, lecturename);
			EmailUtil.send(toEmial, title, msg);
			return true;
		} catch(Exception e) {
			logException(log, e);
			return false;
		}
	}

	/**
	 * 报名成功，赠送会员的邮件
	 * @param name 姓名
	 * @param time 讲座开始时间
	 * @param account 账号
	 * @param password 密码
	 * @param toEmial 发送地址
	 * @return
	 * @author liubin
	 * @createtime 2016-10-26下午2:38:30
	 * @return boolean
	 */
	public static boolean sendParticipateSuccess(String lecturename, String name, String account, String password, String toEmial) {
		try {
			String NAME = "{NAME}";
			String ACCOUNT = "{ACCOUNT}";
			String PASSWORD = "{PASSWORD}";
			String LECTURENAME = "{LECTURENAME}";
			Content content = getContentSO().getOneByType(EContent.email_participatesuccess);
			String title = content.getTitle();
			title = title.replace(LECTURENAME, lecturename);
			String msg = content.getContent();
			msg = msg.replace(NAME, name);
			msg = msg.replace(ACCOUNT, account);
			msg = msg.replace(PASSWORD, password);
			msg = msg.replace(LECTURENAME, lecturename);
			EmailUtil.send(toEmial, title, msg);
			return true;
		} catch(Exception e) {
			logException(log, e);
			return false;
		}
	}
	
	/**
	 * 讲座确定,时间修改和地点修改模板
	 * @param name 姓名
	 * @param time 讲座时间
	 * @param lecturename 讲座名称
	 * @param semester 学期
	 * @param address 地点
	 * @param toEmial 发送地址
	 * @param reason 修改的原因 (时间修改和地点修改要传入)
	 * @return
	 * @author liubin
	 * @createtime 2016-10-26下午2:48:44
	 * @return boolean
	 */
	public static boolean sendLectureAbout(String lecturename, String name, String time, String reason, String semester, String address, String toEmial, EContent type) {
		try {
			String NAME = "{NAME}";
		    String SEMESTER = "{SEMESTER}";
			String TIME = "{TIME}";
			String REASON = "{REASON}";
			String ADDRESS = "{ADDRESS}";
			String LECTURENAME = "{LECTURENAME}";
			Content content = getContentSO().getOneByType(type);
			String title = content.getTitle().replace(SEMESTER, semester);
			title = title.replace(LECTURENAME, lecturename);
			String msg = content.getContent();
			msg = msg.replace(NAME, name);
			msg = msg.replace(SEMESTER, semester);
			msg = msg.replace(TIME, time);
			msg = msg.replace(LECTURENAME, lecturename);
			if (StrUtil.isNotEmpty(reason)) {
				msg = msg.replace(REASON, reason);
			}
			msg = msg.replace(ADDRESS, address);
			EmailUtil.send(toEmial, title, msg);
			return true;
		} catch(Exception e) {
			logException(log, e);
			return false;
		}
	}
	
	/**
	 * 报名成功，不赠送会员的邮件
	 * @param lecturename
	 * @param name
	 * @param toEmial
	 * @return
	 * @author liubin
	 * @createtime 2016年12月14日下午12:33:10
	 * @return boolean
	 */
	public static boolean sendParticipateSuccessNoUser(String lecturename, String name, String toEmial) {
		try {
			String NAME = "{NAME}";
			String LECTURENAME = "{LECTURENAME}";
			Content content = getContentSO().getOneByType(EContent.email_participatesuccessnouser);
			String title = content.getTitle();
			title = title.replace(LECTURENAME, lecturename);
			String msg = content.getContent();
			msg = msg.replace(NAME, name);
			msg = msg.replace(LECTURENAME, lecturename);
			EmailUtil.send(toEmial, title, msg);
			return true;
		} catch(Exception e) {
			logException(log, e);
			return false;
		}
	}
}
