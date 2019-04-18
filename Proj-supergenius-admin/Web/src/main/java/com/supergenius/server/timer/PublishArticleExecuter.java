package com.supergenius.server.timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.genius.server.timer.Executer;
import com.supergenius.web.admin.career.helper.CareerArticleHP;
import com.supergenius.web.admin.enterpriser.hellper.EnterpriserArticleHP;
import com.supergenius.web.admin.finance.helper.FinanceArticleHP;
import com.supergenius.web.admin.gupage.helper.GupageArticleHP;
import com.supergenius.web.admin.startup.helper.StartupArticleHP;

/**
 * 定时群发邮件的Execute（7天、14天注册没有交费）
 * 
 * @author XieMing
 * @date 2016-5-20 下午5:08:10
 */
public class PublishArticleExecuter implements Executer {

	private static Logger log = LoggerFactory.getLogger(PublishArticleExecuter.class);

	@Override
	public void execute() {
		log.debug("begin to execute UpdateFinanceArticle");
		boolean flag = false;
		if (FinanceArticleHP.publish() && StartupArticleHP.publish() && GupageArticleHP.publish() && CareerArticleHP.publish() && EnterpriserArticleHP.publish()) {
			flag = true;
		}
		log.debug("finish execute UpdateFinanceArticle" + flag);
	}
}
