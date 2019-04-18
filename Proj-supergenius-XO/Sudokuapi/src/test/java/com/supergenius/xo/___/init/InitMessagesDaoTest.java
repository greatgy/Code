package com.supergenius.xo.___.init;


import java.util.*;

import com.supergenius.xo.common.constants.MapperDict;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.supergenius.xo.mock.testconstants.TestConst;
import com.supergenius.xo.sudokuapi.dao.MessagesDao;
import com.supergenius.xo.sudokuapi.entity.Messages;


/**
 * 站内消息初始化数据
 * @author YuYingJie
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class InitMessagesDaoTest {
	
	@Autowired
	MessagesDao dao;

	@Ignore
	@Test
	public void insertInitData() {
		List<String> list = new ArrayList<>();
		list.add(TestConst.uid2);
		for (int i = 0; i <= 5; i++) {
			Messages messages = new Messages();
			messages.setFromid(TestConst.uid1);
			messages.setToid(list);
			messages.setTitle(TestConst.title);
            Map<String, Object> map1 = new HashMap<>();
            map1.put(MapperDict.content, "welcome to sudokus");
			messages.setContent(map1);
			messages.setCreatetime(new Date());
			messages.setUpdatetime(new Date());
			dao.insert(messages);
		}
	}
	
}
