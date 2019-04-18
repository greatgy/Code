package com.supergenius.xo.___.init;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.supergenius.xo.mock.testconstants.TestConst;
import com.supergenius.xo.sudokuapi.dao.LoginhistoriesDao;
import com.supergenius.xo.sudokuapi.entity.Loginhistories;


/**
 * 登录历史初始化数据
 * @author YuYingJie
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class InitLoginhistoriesDaoTest {

	@Autowired
	LoginhistoriesDao dao;

	@Ignore
	@Test
	public void insertInitData() {
		for (int i = 0; i <= 5; i++) {
			Loginhistories loginhistories = new Loginhistories();
			loginhistories.setUserid(TestConst.uid1);
			loginhistories.setIp("192.168.1." + i);
			dao.insert(loginhistories);
		}
	}
	
}
