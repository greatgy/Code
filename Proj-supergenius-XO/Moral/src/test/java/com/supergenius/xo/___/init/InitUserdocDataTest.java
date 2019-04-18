package com.supergenius.xo.___.init;

import org.joda.time.DateTime;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.model.base.enums.EStatus;
import com.supergenius.moral.moral.dao.UserdocDao;
import com.supergenius.xo.moral.entity.Userdoc;

/**
 * 添加学员上传资料的初始化数据
 * 
 * @author LiJiacheng
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class InitUserdocDataTest {

	@Autowired
	UserdocDao dao;

	@Ignore
	@Test
	public void insertInitData() {
		for (int i = 0; i <= 5; i++) {
			Userdoc userdoc = new Userdoc();
			userdoc.setId("Id" + i);
			userdoc.setMd5sum("Md5sum" + i);
			userdoc.setName("Name" + i);
			userdoc.setStatus(EStatus.enable);
			userdoc.setUseruid("Useruid" + i);
			userdoc.setCountdl(23 + i);
			userdoc.setCreatetime(DateTime.now());
			dao.insert(userdoc);
		}
	}

}
