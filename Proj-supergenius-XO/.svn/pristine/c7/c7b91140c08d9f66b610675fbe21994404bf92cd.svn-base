package com.supergenius.xo.career.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.model.base.enums.EStatus;
import com.supergenius.xo.career.entity.News;
import com.supergenius.xo.career.enums.ECareerMsg;
import com.supergenius.xo.mock.testconstants.TestConst;

/**
 * 消息单元测试类
 * @author ChenQi
 * @date 2017年11月13日16:51:52
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class NewsDaoTest {
	
	@Autowired
	NewsDao dao;
	
	News entity;
	
	/**
	 * Test Get()
	 */
	@Test
	public void testGet() {
		entity = dao.get(TestConst.uid2);
		assertNotNull(entity);
		assertEquals(TestConst.uid2, entity.getUid());
	}
	
	/**
	 * Test GetOne()
	 */
	@Test
	public void testGetOne() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.uid, TestConst.uid2);
		assertNotNull(dao.getOne(map));
	}

	/**
	 * Test GetCount()
	 */
	@Test
	public void testGetCount() {
		assertTrue(dao.getCount(null) >= 1);
	}

	/**
	 * Test GetList()
	 */
	@Test
	public void testGetList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.uid, TestConst.uid2);
		assertTrue(dao.getList(map).size() > 0);
	}
	
	/*@Test
	public void testGetListByUseruid() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.useruid, TestConst.uid1);
		assertTrue(dao.getListByUseruid(map).size() > 0);
	}
*/
	/**
	 * Test Insert()
	 */
	@Test
	public void testInsert() {
		entity = new News();
		entity.setUid(TestConst.uid1);
		entity.setFromuid(TestConst.uid2);
		entity.setTouid(TestConst.uid);
		entity.setType(ECareerMsg.commentsarticle);
		entity.setTitle("111");
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
		entity = dao.get(TestConst.uid2);
		entity.setStatus(EStatus.disable);
		dao.update(entity);
		assertEquals(EStatus.disable, dao.get(TestConst.uid2).getStatus());
	}

	/**
	 * Test UpdateFields()
	 */
	@Test
	public void testUpdateFields() {
		entity = new News();
		entity.setUid(TestConst.uid1);
		entity.setFromuid(TestConst.uid2);
		entity.setTouid(TestConst.uid);
		entity.setType(ECareerMsg.commentsarticle);
		dao.insert(entity);
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.uid, TestConst.uid1);
		map.put(BaseMapperDict.status, EStatus.enable);
		dao.updateFields(map);
		assertEquals(EStatus.enable, dao.get(TestConst.uid1).getStatus());
		dao.delete(uid);
		assertNull(dao.get(uid));
	}

	/**
	 * Test Delete()
	 */
	@Test
	public void testDelete() {
		entity = new News();
		entity.setUid(TestConst.uid1);
		entity.setFromuid(TestConst.uid2);
		entity.setTouid(TestConst.uid);
		entity.setType(ECareerMsg.commentsarticle);
		dao.insert(entity);
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		dao.delete(uid);
		assertNull(dao.get(uid));
	}
}
