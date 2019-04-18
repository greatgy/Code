package com.supergenius.server.timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.genius.server.timer.Executer;
import com.supergenius.web.admin.manager.helper.ReplyHP;

/**
 * 根据答辩时间自动答辩的状态
 * 
 * @author liubin
 * @createtime 2016-11-14下午4:50:39
 */
public class UpdateReplyStageExecuter implements Executer {

	private static Logger log = LoggerFactory.getLogger(UpdateReplyStageExecuter.class);

	@Override
	public void execute() {
		log.debug("begin to execute updateAppExpertStage");
		boolean flag = ReplyHP.updateReplyStage();
		log.debug("finish execute updateAppExpertStage, flag:" + flag);
	}
}
