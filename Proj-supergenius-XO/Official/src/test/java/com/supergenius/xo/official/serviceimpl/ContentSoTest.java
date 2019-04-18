package com.supergenius.xo.official.serviceimpl;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.supergenius.xo.official.entity.Content;
import com.supergenius.xo.official.service.ContentSO;


/**
 * 测试类
 * 
 * @author LiuXiaoke
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class ContentSoTest {
	
	@Autowired
	private ContentSO so;
	
	@Ignore
	@Test
	public void addTest() {
		Content content = new Content();
		content.setTitle("test");
		so.add(content);
	}
	
}
