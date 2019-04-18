package com.supergenius.server.timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.genius.server.timer.Executer;
import com.supergenius.web.admin.manager.helper.AppExpertHP;

/**
 * 根据面试时间自动更新专家申请的状态
 * @author XieMing
 * @date 2016-11-14 下午4:31:30
 */
public class UpdateAppExpertExecuter implements Executer {

	private static Logger log = LoggerFactory.getLogger(UpdateAppExpertExecuter.class);

	@Override
	public void execute() {
		log.debug("begin to execute updateAppExpertStage");
		boolean flag = AppExpertHP.updateAppExpertStage();
		log.debug("finish execute updateAppExpertStage, flag:" + flag);
	}
}
