package com.supergenius.xo.___.init;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.model.base.enums.EStatus;
import com.supergenius.xo.official.dao.ArticleDao;
import com.supergenius.xo.official.entity.Article;
import com.supergenius.xo.official.enums.EArticleChannel;
import com.supergenius.xo.official.enums.EArticleType;

/**
 * 新闻测试数据
 * @author liushaomin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class InitArticleDataTest {
	
	@Autowired
	ArticleDao dao;
	
	/**
	 * 只插入新闻测试数据
	 * @author liushaomin
	 */
	@Ignore
	@Test
	public void insertInitNewsData(){
		String testcontent = "testcontent";
		for (int i = 0; i < 5; i++) {
			Article entity = new Article();
			entity.setContent(i + testcontent);
			entity.setIstop(false);
			entity.setStatus(EStatus.enable);
			entity.setTitle("testtitle");
			entity.setAuthor("testname");
			entity.setOrigin("supergenius");
			entity.setType(EArticleType.news);
			dao.insert(entity);
		}
	}
	
	/**
	 * 添加官网文章数据
	 * @author liushaomin
	 */
	@Ignore
	@Test
	public void insertInitArticleData(){
		String testcontent = "testcontent";
		for (int i = 0; i < 2; i++) {
			Article entity = new Article();
			entity.setContent(i + testcontent);
			entity.setIstop(false);
			entity.setStatus(EStatus.enable);
			entity.setTitle("testtitle");
			entity.setAuthor("testname");
			entity.setOrigin("supergenius");
			entity.setType(EArticleType.article);
			entity.setChannel(EArticleChannel.manager);
			dao.insert(entity);
		}
	}

}
