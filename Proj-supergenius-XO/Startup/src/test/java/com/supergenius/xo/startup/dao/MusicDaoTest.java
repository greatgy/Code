package com.supergenius.xo.startup.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.core.base.constant.BaseMapperDict;
import com.supergenius.xo.mock.testconstants.TestConst;
import com.supergenius.xo.startup.dao.MusicDao;
import com.supergenius.xo.startup.entity.Music;

/**
 * 背景音乐测试类
 * @author 许志翔
 * @date 2017年8月9日15:03:54
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class MusicDaoTest {
	

	@Autowired
	private MusicDao dao;

	private Music entity;
	/**
	 * Test Get()
	 */
	@Test
	public void testGet() {
		entity = dao.get(TestConst.uid1);
		assertNotNull(entity);
		assertEquals(TestConst.uid1, entity.getUid());
	}

	/**
	 * Test GetOne()
	 */
	@Test
	public void testGetOne() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.uid, TestConst.uid1);
		assertNotNull(dao.getOne(map));
	}

	/**
	 * Test GetCount()
	 */
	@Test
	public void testCount() {
		assertTrue(dao.getCount(null) >= 1);
	}

	/**
	 * Test GetList()
	 */
	@Test
	public void testGetList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.uid, TestConst.uid1);
		assertTrue(dao.getList(map).size() > 0);
	}

	/**
	 * Test Insert()
	 */
	@Test
	public void testInsert() {
		entity = new Music();
		entity.setUid(TestConst.uid2);
		entity.setName("测试音乐2");
		entity.setUrl("http://www.baidu.com");
		entity.setTemp(1);
		entity.setOrder(2);
		entity.setCreatetime(new DateTime());
		entity.setAdminuid(TestConst.uid3);
		dao.insert(entity);
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		dao.delete(uid);
		assertNull(dao.get(uid));
	}

	/**
	 * Test Update()
	 */
	@Test
	public void testUpdate() {
		entity = new Music();
		entity.setUid(TestConst.uid2);
		entity.setName("测试音乐2");
		entity.setUrl("http://www.baidu.com");
		entity.setTemp(1);
		entity.setOrder(2);
		entity.setCreatetime(new DateTime());
		entity.setAdminuid(TestConst.uid3);
		dao.insert(entity);
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		entity.setTemp(0);
		assertTrue(dao.update(entity));
		dao.delete(uid);
		assertNull(dao.get(uid));
	}

	/**
	 * Test Delete()
	 */
	@Test
	public void testDelete() {
		entity = new Music();
		entity.setUid(TestConst.uid2);
		entity.setName("测试音乐2");
		entity.setUrl("http://www.baidu.com");
		entity.setTemp(1);
		entity.setOrder(2);
		entity.setCreatetime(new DateTime());
		entity.setAdminuid(TestConst.uid3);
		dao.insert(entity);
		assertNotNull(dao.get(TestConst.uid2));
		dao.delete(TestConst.uid2);
		assertNull(dao.get(TestConst.uid2));
	}
}
