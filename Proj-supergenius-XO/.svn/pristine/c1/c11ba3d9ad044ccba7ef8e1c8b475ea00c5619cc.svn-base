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
import com.supergenius.xo.startup.dao.QuestionDao;
import com.supergenius.xo.startup.entity.Question;

/**
 * 规则测试类
 * @author ChenQi
 * @date 2017年6月20日15:31:54
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class QuestionDaoTest {
	

	@Autowired
	private QuestionDao dao;

	private Question entity;
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
		entity = new Question();
		entity.setUid(TestConst.uid2);
		entity.setName("在保证精神状态良好的情况下，你每天需要多少睡眠时间");
		entity.setOptions("{'A':'最多4个小时就够了', 'B':'5-6个小时', 'C':'7-8个小时', 'D':'9-10个小时', 'E':'11个小时以上'}");
		entity.setOptionscore("{'A':6, 'B':10, 'C':6, 'D':4, 'E':1}");
		entity.setOrder(2);
		entity.setImg("c://img");
		entity.setCreatetime(new DateTime());
		entity.setUpdatetime(new DateTime());
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
		entity = new Question();
		entity.setUid(TestConst.uid2);
		entity.setName("在保证精神状态良好的情况下，你每天需要多少睡眠时间");
		entity.setOptions("{'A':'最多4个小时就够了', 'B':'5-6个小时', 'C':'7-8个小时', 'D':'9-10个小时', 'E':'11个小时以上'}");
		entity.setOptionscore("{'A':6, 'B':10, 'C':6, 'D':4, 'E':1}");
		entity.setOrder(2);
		entity.setCreatetime(new DateTime());
		entity.setUpdatetime(new DateTime());
		entity.setImg("c://img");
		dao.insert(entity);
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		entity.setType(21);
		assertTrue(dao.update(entity));
		dao.delete(uid);
		assertNull(dao.get(uid));
	}

	/**
	 * Test UpdateFields()
	 */
	@Test
	public void testUpdateFields() {
		entity = new Question();
		entity.setUid(TestConst.uid2);
		entity.setName("在保证精神状态良好的情况下，你每天需要多少睡眠时间");
		entity.setOptions("{'A':'最多4个小时就够了', 'B':'5-6个小时', 'C':'7-8个小时', 'D':'9-10个小时', 'E':'11个小时以上'}");
		entity.setOptionscore("{'A':6, 'B':10, 'C':6, 'D':4, 'E':1}");
		entity.setOrder(2);
		entity.setCreatetime(new DateTime());
		entity.setUpdatetime(new DateTime());
		entity.setImg("c://img");
		dao.insert(entity);
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		Map<String, Object> map = new HashMap<>();
		map.put("uid", uid);
		map.put("type", "21");
		map.put("rejectcount", "3");
		assertTrue(dao.updateFields(map));
		dao.delete(uid);
		assertNull(dao.get(uid));
	}

	/**
	 * Test Delete()
	 */
	@Test
	public void testDelete() {
		entity = new Question();
		entity.setUid(TestConst.uid2);
		entity.setName("在保证精神状态良好的情况下，你每天需要多少睡眠时间");
		entity.setOptions("{'A':'最多4个小时就够了', 'B':'5-6个小时', 'C':'7-8个小时', 'D':'9-10个小时', 'E':'11个小时以上'}");
		entity.setOptionscore("{'A':6, 'B':10, 'C':6, 'D':4, 'E':1}");
		entity.setOrder(2);
		entity.setAdminuid(TestConst.uid2);
		entity.setImg("c://img");
		entity.setCreatetime(new DateTime());
		entity.setUpdatetime(new DateTime());
		dao.insert(entity);
		assertNotNull(dao.get(TestConst.uid2));
		dao.delete(TestConst.uid2);
		assertNull(dao.get(TestConst.uid2));
	}
}
