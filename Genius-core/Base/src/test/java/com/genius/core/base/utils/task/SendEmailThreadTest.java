package com.genius.core.base.utils.task;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.core.base.utils.EmailUtil;

/** 
* 
* @author chenminchang
* @date 2017年3月20日 下午6:39:42 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class SendEmailThreadTest {

	/**
	 * 测试线程批量发送邮件
	 * 
	 * @author chenminchang
	 * @create 2017年3月22日上午11:59:48
	 */
	@Test
	public void testSendEmailThread() {
		List<String> toEmailList = new ArrayList<>();
//		for (int i = 0; i < 50; i++) {
//			toEmailList.add("1113449881@qq.com");
//		}
		EmailUtil.sendBatchUseThread("注册用户", "您已成为注册用户", toEmailList.toArray(new String[toEmailList.size()]));
	}
}
