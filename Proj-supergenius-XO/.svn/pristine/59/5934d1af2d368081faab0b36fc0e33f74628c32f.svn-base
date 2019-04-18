package com.supergenius.xo.manager.dao;

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
import com.genius.core.base.utils.JsonUtil;
import com.genius.model.base.enums.EStatus;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.manager.entity.CountDetail;
import com.supergenius.xo.manager.enums.ECount;
import com.supergenius.xo.mock.testconstants.TestConst;

/**
 * CountDetail测试类
 * @author liubin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class CountDetailDaoTest {

	@Autowired
	private CountDetailDao dao;

	private CountDetail entity;

	/**
	 * Test Get()
	 */
	@Test
	public void testGet() {
		entity = dao.get(TestConst.uid2);
		assertNotNull(entity);
		assertNotNull(entity.getUseruid());
		assertNotNull(entity.getRefuid());
		assertNotNull(entity.getData());
		assertNotNull(entity.getType());
		assertNotNull(entity.getStatus());
		assertNotNull(entity.getCreatetime());
		assertNotNull(entity.getUpdatetime());
	}

	/**
	 * Test GetOne()
	 */
	@Test
	public void testGetOne() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.useruid, TestConst.uid);
		entity = dao.getOne(map);
		assertNotNull(entity);
		assertNotNull(entity.getUseruid());
		assertNotNull(entity.getRefuid());
		assertNotNull(entity.getData());
		assertNotNull(entity.getType());
		assertNotNull(entity.getStatus());
		assertNotNull(entity.getCreatetime());
		assertNotNull(entity.getUpdatetime());
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
		map.put(BaseMapperDict.userid, TestConst.uid);
		assertTrue(dao.getList(map).size() > 0);
	}

	/**
	 * Test Insert()
	 */
	@Test
	public void testInsert() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(TestConst.uid1, true);
		entity = new CountDetail();
		entity.setUid(TestConst.uid1);
		entity.setUseruid(TestConst.uid3);
		entity.setRefuid(TestConst.uid3);
		entity.setData(JsonUtil.toJson(map));
		entity.setType(ECount.vote);
		entity.setStatus(EStatus.disable);
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
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(TestConst.uid1, true);
		entity = new CountDetail();
		entity.setUid(TestConst.uid1);
		entity.setUseruid(TestConst.uid3);
		entity.setRefuid(TestConst.uid3);
		entity.setData(JsonUtil.toJson(map));
		entity.setType(ECount.vote);
		entity.setStatus(EStatus.disable);
		entity.setUpdatetime(new DateTime());
		dao.insert(entity);
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		entity.setStatus(EStatus.enable);
		assertTrue(dao.update(entity));
		assertTrue(entity.getStatus().equals(EStatus.enable));
		dao.delete(uid);
		assertNull(dao.get(uid));
	}

	/**
	 * Test UpdateFields()
	 */
	@Test
	public void testUpdateFields() {
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put(TestConst.uid1, true);
		entity = new CountDetail();
		entity.setUid(TestConst.uid1);
		entity.setUseruid(TestConst.uid3);
		entity.setRefuid(TestConst.uid3);
		entity.setData(JsonUtil.toJson(map1));
		entity.setType(ECount.vote);
		entity.setStatus(EStatus.disable);
		entity.setUpdatetime(new DateTime());
		dao.insert(entity);
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.uid, uid);
		map.put(MapperDict.type, ECount.vote);
		assertTrue(dao.updateFields(map));
		dao.delete(uid);
		assertNull(dao.get(uid));
	}

	/**
	 * Test Delete()
	 */
	@Test
	public void testDelete() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(TestConst.uid1, true);
		entity = new CountDetail();
		entity.setUid(TestConst.uid1);
		entity.setUseruid(TestConst.uid3);
		entity.setRefuid(TestConst.uid3);
		entity.setData(JsonUtil.toJson(map));
		entity.setType(ECount.vote);
		entity.setStatus(EStatus.disable);
		entity.setUpdatetime(new DateTime());
		dao.insert(entity);
		assertNotNull(dao.get(TestConst.uid1));
		dao.delete(TestConst.uid1);
		assertNull(dao.get(TestConst.uid1));
	}

}