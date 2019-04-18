package com.supergenius.xo.___.init;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.supergenius.xo.official.dao.BannerDao;
import com.supergenius.xo.official.entity.Banner;

/**
 * 插入banner数据
 * @author LiuXiaoke
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class InitBannerDataTest {
	
	@Autowired
	BannerDao dao;
	
	@Ignore
	@Test
	public void initContentDataTest() {
		Banner banner = new Banner();
		banner.setTitle("标题标题标题标题标题标题标题");
		dao.insert(banner);
	}
	
}
