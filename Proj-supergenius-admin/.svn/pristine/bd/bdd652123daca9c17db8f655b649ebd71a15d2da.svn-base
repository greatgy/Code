package com.supergenius.server.timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.genius.server.timer.Executer;
import com.supergenius.web.admin.user.helper.UserHP;

/**
 * 定时群发邮件的Execute（7天、14天注册没有交费）
 * 
 * @author XieMing
 * @date 2016-5-20 下午5:08:10
 */
public class UserRemindPayExecuter implements Executer {

	private static Logger log = LoggerFactory.getLogger(UserRemindPayExecuter.class);

	@Override
	public void execute() {
		log.debug("begin to execute judgeIfSendEmailToUserNotPayJustRegist");
		boolean flag = UserHP.remindPayEmail();
		log.debug("finish execute judgeIfSendEmailToUserNotPayJustRegist, flag:" + flag);
	}
}
