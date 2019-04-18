package com.supergenius.xo.___.init;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.supergenius.xo.official.dao.ContentDao;
import com.supergenius.xo.official.entity.Content;
import com.supergenius.xo.official.enums.EType;

/**
 * 插入网站内容数据
 * @author LiuXiaoke
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class InitContentDataTest {
	
	@Autowired
	ContentDao dao;
	
	/**
	 * 插入关于我们 介绍数据
	 * @author LiuXiaoke
	 */
	@Ignore
	@Test
	public void initContentDataTest() {
		for (EType e : EType.values()) {
			Content content = new Content();
			content.setTitle(e.name());
			content.setContent(e.name());
			content.setType(e);
			dao.insert(content);
		}
	}
	
}
