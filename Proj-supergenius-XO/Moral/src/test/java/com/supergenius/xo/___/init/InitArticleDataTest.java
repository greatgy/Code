package com.supergenius.xo.___.init;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.supergenius.moral.moral.dao.ArticleDao;
import com.supergenius.xo.moral.entity.Article;

/**
 * 发帖测试数据
 * 
 * @author liushaomin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class InitArticleDataTest {

	@Autowired
	ArticleDao dao;

	@Ignore
	@Test
	public void InsertInitData() {
		for (int i = 0; i < 5; i++) {
			Article article = new Article();
			article.setUseruid("useruid" + i);
			article.setUsername("username" + i);
			article.setTitle("title-title-title-title-----" + i);
			article.setContent("content-content-content-content-content-content-----" + i);
			article.setKeywords("keywords-keywords-keywords-keywords-keywords-----" + i);
			article.setCountclick(10 + i);
			article.setCountcomment(10 + i);
			article.setCountpraise(10 + i);
			dao.insert(article);
		}
	}
}
