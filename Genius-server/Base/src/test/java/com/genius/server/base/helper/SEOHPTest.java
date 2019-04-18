package com.genius.server.base.helper;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.model.base.entity.SEO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class SEOHPTest {

	@Test
	@Ignore
	public void testAdd() {
		String uri = "/index";
		String title = "首页";
		String keywords = "首页,关键词";
		String desc = "欢迎访问首页栏目";
		SEO seo = null;
		seo = new SEO("${title}-天才网", "${keywords}----天才关键词", "${description}-----欢迎访问天才网");
		SEOHP.add(seo);
		String parentuid = seo.getUid();
		seo = new SEO(uri, title, keywords, desc, parentuid);
		SEOHP.add(seo);
		seo = new SEO("/news", "新闻", "新闻关键词", "欢迎访问新闻栏目");
		SEOHP.add(seo);
	}

}
