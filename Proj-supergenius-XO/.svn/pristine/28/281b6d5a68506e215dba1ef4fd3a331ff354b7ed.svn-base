package com.supergenius.xo.___.init;

import org.joda.time.DateTime;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.model.base.enums.EStatus;
import com.supergenius.moral.moral.dao.CaseDao;
import com.supergenius.xo.moral.entity.Case;
import com.supergenius.xo.moral.enums.ECase;

/**
 * 添加案例的初始化数据
 * 
 * @author LiJiacheng
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class InitCaseDataTest {

	@Autowired
	CaseDao dao;

	@Ignore
	@Test
	public void InsertInitData() {
		for (int i = 1; i <= 5; i++) {
			Case case1 = new Case();
			case1.setCreatetime(DateTime.now());
			case1.setHref("Href" + i);
			case1.setName("name" + i);
			case1.setType(ECase.link);
			case1.setId("Id" + i);
			case1.setCountdl(56 + i);
			case1.setStatus(EStatus.enable);
			dao.insert(case1);
		}
	}

}
