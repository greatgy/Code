package com.supergenius.xo.life.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
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
import com.supergenius.xo.life.entity.Essay;
import com.supergenius.xo.life.enums.EComment;
import com.supergenius.xo.mock.testconstants.TestConst;

/**
 * 评论测试类
 * 
 * @author xuzhixiang
 * @date 2017年9月19日09:59:01
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class EssayDaoTest {

	@Autowired
	private EssayDao dao;

	private Essay entity;
	
	@Before
	public void before() {
		entity = new Essay();
		entity.setUid(TestConst.uid);
		entity.setAdminuid(TestConst.uid1);
		entity.setContent("文章");
		entity.setCreatetime(new DateTime());
		boolean result = dao.insert(entity);
		assertTrue(result);
	}

	@After
	public void after() {
		entity = null;
		boolean result = dao.delete(TestConst.uid);
		assertTrue(result);
	}
	
	/**
	 * Test Get()
	 */
	@Test
	public void testGet() {
		entity = dao.get(TestConst.uid);
		assertNotNull(entity);
		assertEquals(TestConst.uid, entity.getUid());
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
		entity = new Essay();
		entity.setUid(TestConst.uid1);
		entity.setTouid("uidt1000000000000000000000000000");
		entity.setFromuseruid("uidf2000000000000000000000000000");
		entity.setFromuseroid(1);
		entity.setFromusername("zhangsan");
		entity.setTouseruid("uidt2000000000000000000000000000");
		entity.setTouseroid(2);
		entity.setTousername("lisi");
		entity.setContent("我是一条评论");
		entity.setType(EComment.comment);
		entity.setCreatetime(new DateTime());
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
		entity = dao.get(TestConst.uid);
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		entity.setFromusername("wangwu");
		assertTrue(dao.update(entity));
	}

	/**
	 * Test UpdateFields()
	 */
	@Test
	public void testUpdateFields() {
		entity = dao.get(TestConst.uid);
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		Map<String, Object> map = new HashMap<>();
		map.put("uid", uid);
		map.put("fromusername", "xiaoming");
		map.put("tousername", "xiaohong");
		assertTrue(dao.updateFields(map));
	}
	
}
