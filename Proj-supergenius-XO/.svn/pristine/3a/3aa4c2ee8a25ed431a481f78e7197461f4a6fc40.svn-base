package com.supergenius.xo.user.dao;

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
import com.genius.model.base.enums.EStatus;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.mock.testconstants.TestConst;
import com.supergenius.xo.user.entity.ScoreDetail;
import com.supergenius.xo.user.enums.EScore;

/**
 * ScoreDetailDao测试
 * 
 * @author liubin
 * @date 2016-7-18 下午5:33:46
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class ScoreDetailDaoTest {

	@Autowired
	private ScoreDetailDao dao;

	private ScoreDetail entity;

	/**
	 * Test Get()
	 */
	@Test
	public void testGet() {
		entity = dao.get(TestConst.uid1);
		assertNotNull(entity);
		assertNotNull(entity.getUseruid());
		assertNotNull(entity.getScore());
		assertNotNull(entity.getSite());
		assertNotNull(entity.getRefuid());
		assertNotNull(entity.getType());
		assertEquals(TestConst.uid1, entity.getUid());
	}

	/**
	 * Test GetOne()
	 */
	@Test
	public void testGetOne() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.uid, TestConst.uid1);
		entity = dao.getOne(map);
		assertNotNull(entity);
		assertNotNull(entity.getUseruid());
		assertNotNull(entity.getScore());
		assertNotNull(entity.getSite());
		assertNotNull(entity.getRefuid());
		assertNotNull(entity.getType());
		assertEquals(TestConst.uid1, entity.getUid());
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
		map.put(BaseMapperDict.status, EStatus.enable);
		assertTrue(dao.getList(map).size() > 0);
	}

	/**
	 * Test Insert()
	 */
	@Test
	public void testInsert() {
		entity = new ScoreDetail();
		entity.setUid(TestConst.uid2);
		entity.setUseruid(TestConst.uid2);
		entity.setScore(2000);
		entity.setSite(ESite.finance);
		entity.setRefuid(TestConst.uid1);
		entity.setType(EScore.originalFinance);
		entity.setStatus(EStatus.deleted);
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
		entity = new ScoreDetail();
		entity.setUid(TestConst.uid2);
		entity.setUseruid(TestConst.uid2);
		entity.setScore(2000);
		entity.setSite(ESite.finance);
		entity.setRefuid(TestConst.uid1);
		entity.setType(EScore.originalFinance);
		entity.setStatus(EStatus.deleted);
		entity.setUpdatetime(new DateTime());
		dao.insert(entity);
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		entity.setType(EScore.scoreUpGrade);
		entity.setStatus(EStatus.init);
		assertTrue(dao.update(entity));
		assertEquals(EStatus.init, dao.get(uid).getStatus());
		assertEquals(EScore.scoreUpGrade, dao.get(uid).getType());
		dao.delete(uid);
		assertNull(dao.get(uid));
	}

	/**
	 * Test UpdateFields()
	 */
	@Test
	public void testUpdateFields() {
		entity = new ScoreDetail();
		entity.setUid(TestConst.uid2);
		entity.setUseruid(TestConst.uid2);
		entity.setScore(2000);
		entity.setSite(ESite.finance);
		entity.setRefuid(TestConst.uid1);
		entity.setType(EScore.originalFinance);
		entity.setStatus(EStatus.deleted);
		entity.setUpdatetime(new DateTime());
		dao.insert(entity);
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.uid, uid);
		map.put("type", EScore.forwardFinance);
		map.put(MapperDict.status, EStatus.deleted);
		assertTrue(dao.updateFields(map));
		assertEquals(EStatus.deleted, dao.get(uid).getStatus());
		assertEquals(EScore.forwardFinance, dao.get(uid).getType());
		dao.delete(uid);
		assertNull(dao.get(uid));
	}

	/**
	 * Test Delete()
	 */
	@Test
	public void testDelete() {
		entity = new ScoreDetail();
		entity.setUid(TestConst.uid2);
		entity.setUseruid(TestConst.uid2);
		entity.setScore(2000);
		entity.setSite(ESite.finance);
		entity.setRefuid(TestConst.uid1);
		entity.setType(EScore.originalFinance);
		entity.setStatus(EStatus.deleted);
		entity.setUpdatetime(new DateTime());
		dao.insert(entity);
		assertNotNull(dao.get(TestConst.uid2));
		dao.delete(TestConst.uid2);
		assertNull(dao.get(TestConst.uid2));
	}
}