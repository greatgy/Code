package com.supergenius.xo.manager.dao;

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
import com.supergenius.xo.manager.entity.MyVideo;
import com.supergenius.xo.manager.enums.EMajor;
import com.supergenius.xo.manager.enums.EVideoFrom;
import com.supergenius.xo.mock.testconstants.TestConst;

/**
 * 我的视频的单元测试类
 * 
 * @author liubin
 * @date 2016-8-12 上午10:35:02
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class MyVideoDaoTest {

	@Autowired
	MyVideoDao dao;

	MyVideo entity;

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
		map.put(BaseMapperDict.uid, TestConst.uid);
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
		map.put(BaseMapperDict.uid, TestConst.uid);
		assertTrue(dao.getList(map).size() > 0);
	}

	/**
	 * Test Insert()
	 */
	@Test
	public void testInsert() {
		entity = new MyVideo();
		entity.setUid(TestConst.uid1);
		entity.setUseruid(TestConst.uid1);
		entity.setVideouid(TestConst.uid3);
		entity.setExpiretime(new DateTime().plusDays(30));
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
		entity = dao.get(TestConst.uid);
		entity.setStatus(EStatus.disable);
		dao.update(entity);
		assertEquals(EStatus.disable, dao.get(TestConst.uid).getStatus());
	}

	/**
	 * Test UpdateFields()
	 */
	@Test
	public void testUpdateFields() {
		entity = new MyVideo();
		entity.setUid(TestConst.uid1);
		entity.setUseruid(TestConst.uid1);
		entity.setVideouid(TestConst.uid3);
		entity.setExpiretime(new DateTime().plusDays(30));
		entity.setCreatetime(new DateTime());
		entity.setUpdatetime(new DateTime());
		dao.insert(entity);
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.uid, TestConst.uid1);
		map.put(BaseMapperDict.status, EStatus.enable);
		dao.updateFields(map);
		assertEquals(TestConst.uid3, dao.get(TestConst.uid1).getVideouid());
		dao.delete(uid);
		assertNull(dao.get(uid));
	}

	/**
	 * Test Delete()
	 */
	@Test
	public void testDelete() {
		entity = new MyVideo();
		entity.setUid(TestConst.uid1);
		entity.setUseruid(TestConst.uid1);
		entity.setVideouid(TestConst.uid3);
		entity.setExpiretime(new DateTime().plusDays(30));
		entity.setCreatetime(new DateTime());
		entity.setUpdatetime(new DateTime());
		dao.insert(entity);
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		dao.delete(uid);
		assertNull(dao.get(uid));
	}
	
	private static final String channelfrom = "channelfrom";
	private static final String major = "major";
	private static final String expiretime_insevenday = "expiretime_insevenday";
	
	/**
	 * Test getSearchList()
	 */
	@Test
	public void testGetSearchList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(channelfrom, EVideoFrom.pk);
		map.put(major, EMajor.strategicManage);
		map.put(expiretime_insevenday, "30");
		assertNotNull(dao.getSearchList(map));
	}
}
