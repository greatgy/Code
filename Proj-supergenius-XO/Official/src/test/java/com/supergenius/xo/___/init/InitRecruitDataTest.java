package com.supergenius.xo.___.init;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.model.base.enums.EStatus;
import com.supergenius.xo.official.dao.RecruitDao;
import com.supergenius.xo.official.entity.Recruit;
import com.supergenius.xo.official.enums.ERecruit;

/**
 * 招聘测试数据
 * @author liushaomin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class InitRecruitDataTest {
	
	@Autowired
	RecruitDao dao;
	
	@Ignore
	@Test
	public void insertInitData(){
		String testcontent = "testcontent";
		for (int i = 0; i < 10; i++) {
			Recruit entity = new Recruit();
			entity.setContent(i + testcontent);
			entity.setIstop(false);
			entity.setStatus(EStatus.enable);
			entity.setTitle("testtitle");
			entity.setType(ERecruit.consultation);
			entity.setAddress("北京");
			dao.insert(entity);
		}
	}

}
