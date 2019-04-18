package com.supergenius.xo.___.init;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.model.base.enums.EStatus;
import com.supergenius.xo.mock.testconstants.TestConst;
import com.supergenius.xo.official.dao.DiscussDao;
import com.supergenius.xo.official.entity.Discuss;
import com.supergenius.xo.official.enums.EDiscuss;

/**
 * 评论互动测试数据
 * @author liushaomin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class InitDiscussDataTest {
	
	@Autowired
	DiscussDao dao;
	
	@Ignore
	@Test
	public void insertInitData(){
		String testcontent = "contenttest";
		for (int i = 0; i < 2; i++) {
			Discuss entity = new Discuss();
			entity.setFromname("jasonLiu");
			entity.setContent(i + testcontent);
			entity.setFromuid(TestConst.uid);
			entity.setIstop(false);
			entity.setStatus(EStatus.enable);
			entity.setTitle("titletest");
			entity.setType(EDiscuss.user);
			initReply(entity);
			dao.insert(entity);
		}
	}
	
	private void initReply(Discuss entity) {
		List<Discuss> reply = new ArrayList<>();
		for (int i = 0; i < 1; i++) {
			Discuss item = new Discuss();
			item.setContent("回复内容");
			item.setIstop(false);
			item.setStatus(EStatus.enable);
			item.setType(EDiscuss.admin);
			reply.add(item);
		}
		entity.setReply(reply);
	}

}
