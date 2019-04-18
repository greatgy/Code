package com.supergenius.xo.___.init;

import org.joda.time.DateTime;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.model.base.enums.EStatus;
import com.supergenius.moral.moral.dao.AnnouncementDao;
import com.supergenius.xo.moral.entity.Announcement;

/**
 * 
 * @author LiJiacheng
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class InitAnnouncementDataTest {

	@Autowired
	AnnouncementDao dao;

	@Ignore
	@Test
	public void InsertInitData() {
		for (int i = 0; i < 3; i++) {
			Announcement announcement = new Announcement();
			announcement.setSn(i);
			announcement.setTitle("社区公告 :" + i);
			announcement.setContent("公告内容 :" + i + "------------------------------");
			announcement.setAddtime(DateTime.now());
			announcement.setEndtime(DateTime.now());
			announcement.setCreatetime(DateTime.now());
			announcement.setUpdatetime(DateTime.now());
			announcement.setIstop(false);
			announcement.setStatus(EStatus.enable);
			dao.insert(announcement);
		}
	}

}
