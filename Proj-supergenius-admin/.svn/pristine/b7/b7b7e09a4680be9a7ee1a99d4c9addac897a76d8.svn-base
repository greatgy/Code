package com.supergenius.server.timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.genius.server.timer.Executer;
import com.supergenius.web.admin.manager.helper.AppJudgeHP;

/**
 * 根据面试时间自动更新裁判申请的状态
 * @author XieMing
 * @date 2016-11-14 下午2:57:39
 */
public class UpdateAppJudgeExecuter implements Executer {

	private static Logger log = LoggerFactory.getLogger(UpdateAppJudgeExecuter.class);

	@Override
	public void execute() {
		log.debug("begin to execute updateAppJudgeStage");
		boolean flag = AppJudgeHP.updateAppJudgeStage();
		log.debug("finish execute updateAppJudgeStage, flag:" + flag);
	}
}
