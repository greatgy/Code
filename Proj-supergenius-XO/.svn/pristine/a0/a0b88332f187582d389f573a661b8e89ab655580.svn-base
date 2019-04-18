package com.supergenius.xo.moral.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.model.base.enums.EStatus;
import com.supergenius.moral.moral.dao.AnnouncementDao;
import com.supergenius.xo.mock.testconstants.TestConst;
import com.supergenius.xo.moral.constants.MapperDict;
import com.supergenius.xo.moral.entity.Announcement;

/**
 * 社区公告单元测试
 * 
 * @author LiJiacheng
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class AnnouncementDaoTest {

	@Autowired
	AnnouncementDao dao;

	Announcement entity = null;

	@Before
	public void before() {
		entity = new Announcement();
		entity.setUid(TestConst.uid);
		entity.setSn(1);
		entity.setTitle(TestConst.title);
		entity.setContent("公告内容");
		entity.setAddtime(DateTime.now());
		entity.setStatus(EStatus.enable);
		assertTrue(dao.insert(entity));
	}

	@After
	public void after() {
		entity = null;
		assertTrue(dao.delete(TestConst.uid));
	}

	@Test
	public void testGet() {
		assertNotNull(dao.get(TestConst.uid));
	}

	@Test
	public void testGetOne() {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.title, TestConst.title);
		assertNotNull(dao.getOne(map));
	}

	@Test
	public void testGetCount() {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.title, TestConst.title);
		assertEquals(1, dao.getCount(map));
	}

	@Test
	public void testGetList() {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.title, TestConst.title);
		List<Announcement> list = dao.getList(map);
		assertEquals(1, list.size());
	}

	@Test
	public void testUpdate() {
		entity = dao.get(TestConst.uid);
		entity.setTitle(TestConst.title1);
		assertTrue(dao.update(entity));
		Announcement announcement = dao.get(TestConst.uid);
		assertEquals(TestConst.title1, announcement.getTitle());
	}

	@Test
	public void testUpdateFields() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.title, TestConst.title1);
		map.put(BaseMapperDict.uid, entity.getUid());
		dao.updateFields(map);
		assertEquals(TestConst.title1, dao.get(TestConst.uid).getTitle());
	}

	@Test
	public void testDeleteByMap() {
		String title = "titletest";
		for (int i = 0; i < 3; i++) {
			Announcement entity = new Announcement();
			entity.setTitle(title);
			dao.insert(entity);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.title, title);
		assertEquals(3, dao.getList(map).size());
		dao.deleteByMap(map);
		assertTrue(dao.getList(map).size() == 0);
	}

}
