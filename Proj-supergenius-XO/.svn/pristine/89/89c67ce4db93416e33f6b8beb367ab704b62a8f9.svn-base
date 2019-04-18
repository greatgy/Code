package com.supergenius.xo.___.init;

import java.util.Date;
import java.util.UUID;

import org.bson.types.ObjectId;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.core.base.utils.GlobalUtil;
import com.supergenius.xo.sudokuapi.dao.UsersDao;
import com.supergenius.xo.sudokuapi.entity.Users;
import com.supergenius.xo.sudokuapi.enums.EGameStatus;
import com.supergenius.xo.sudokuapi.enums.EGrade;

/**
 * 添加用户测试数据
 * 
 * @author LiJiacheng
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class InitUsersDaoTest {

	@Autowired
	UsersDao usersDao;
	
	@Ignore
	@Test
	public void insertInitDate() {
		for (int i = 0; i < 3; i++) {
			Users users = new Users();
			users.setAccount("account" + i);
			users.setCreate_ip("TestCreate_IP" + i);
			users.setCreatetime(new Date());
			users.setEmail("TestEmail" + i);
			users.setGrade(EGrade.three);
			users.setIcon("TestIcon" + i);
			users.setLogin_ip("TestLogin_IP" + i);
			users.setLogintime(new Date());
			users.setMoney(1000 + i);
			users.setName("TestName" + i);
			users.setPassword("TestPassword" + i);
			users.setPoints(100 + i);
			users.setRecharge(true);
			users.setRounds(10 + i);
			users.setStatus(EGameStatus.enable);
			users.setWintimes(5 + i);
			usersDao.insert(users);
		}
	}

	@Ignore
	@Test
	public void testObjectID() {
		System.out.println(new ObjectId());
		System.out.println(new ObjectId(GlobalUtil.getUUID().substring(0, 24)));
		System.out.println(UUID.randomUUID());
		System.out.println(GlobalUtil.getUUID());
	}

}
