package com.genius.core.base.utils.task;

import com.genius.core.base.utils.EmailUtil;

/** 
* 发送邮件任务类
* @author chenminchang
* @date 2017年3月20日 下午4:40:42 
*/
public class SendEmailTask implements Runnable {
	
	private String toemail;
	private String title;
	private String content;
	
	public SendEmailTask(String toemail, String title, String content){
		this.toemail = toemail;
		this.title = title;
		this.content = content;
	}
	
	@Override
	public void run() {
		EmailUtil.send(toemail, title, content);
	}

}
