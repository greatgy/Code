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
import com.genius.model.base.enums.EStatus;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.manager.entity.UserStatics;
import com.supergenius.xo.mock.testconstants.TestConst;

/**
 * UserStatics测试类
 * @author liubin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class UserStaticsDaoTest {

	@Autowired
	private UserStaticsDao dao;

	private UserStatics entity;

	/**
	 * Test Get()
	 */
	@Test
	public void testGet() {
		entity = dao.get(TestConst.uid1);
		assertNotNull(entity);
		assertNotNull(entity.getUseruid());
		assertNotNull(entity.getPkcount());
		assertNotNull(entity.getWatchpkcount());
		assertNotNull(entity.getReplycount());
		assertNotNull(entity.getJudgecount());
		assertNotNull(entity.getExpertcount());
		assertNotNull(entity.getVisitorcount());
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
		map.put(BaseMapperDict.uid, TestConst.uid1);
		entity = dao.getOne(map);
		assertNotNull(entity);
		assertNotNull(entity.getUseruid());
		assertNotNull(entity.getPkcount());
		assertNotNull(entity.getWatchpkcount());
		assertNotNull(entity.getReplycount());
		assertNotNull(entity.getJudgecount());
		assertNotNull(entity.getExpertcount());
		assertNotNull(entity.getVisitorcount());
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
		map.put(BaseMapperDict.status, EStatus.enable);
		assertTrue(dao.getList(map).size() > 0);
	}

	/**
	 * Test Insert()
	 */
	@Test
	public void testInsert() {
		entity = new UserStatics();
		entity.setUid(TestConst.uid);
		entity.setUseruid((TestConst.uid2));
		entity.setPkcount(20);
		entity.setWatchpkcount(30);
		entity.setReplycount(40);
		entity.setJudgecount(50);
		entity.setExpertcount(60);
		entity.setVisitorcount(70);
		entity.setStatus(EStatus.enable);
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
		entity = new UserStatics();
		entity.setUid(TestConst.uid);
		entity.setUseruid((TestConst.uid2));
		entity.setPkcount(20);
		entity.setWatchpkcount(30);
		entity.setReplycount(40);
		entity.setJudgecount(50);
		entity.setExpertcount(60);
		entity.setVisitorcount(70);
		entity.setStatus(EStatus.enable);
		entity.setUpdatetime(new DateTime());
		dao.insert(entity);
		String uid = entity.getUid();
		entity.setUseruid((TestConst.uid2));
		entity.setPkcount(30);
		entity.setWatchpkcount(50);
		entity.setReplycount(90);
		assertTrue(dao.update(entity));
		dao.delete(uid);
		assertNull(dao.get(uid));
	}

	/**
	 * Test UpdateFields()
	 */
	@Test
	public void testUpdateFields() {
		entity = new UserStatics();
		entity.setUid(TestConst.uid);
		entity.setUseruid((TestConst.uid2));
		entity.setPkcount(20);
		entity.setWatchpkcount(30);
		entity.setReplycount(40);
		entity.setJudgecount(50);
		entity.setExpertcount(60);
		entity.setVisitorcount(70);
		entity.setStatus(EStatus.enable);
		entity.setUpdatetime(new DateTime());
		dao.insert(entity);
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.uid, uid);
		map.put(MapperDict.useruid, TestConst.uid3);
		map.put(MapperDict.status, EStatus.deleted);
		assertTrue(dao.updateFields(map));
		dao.delete(uid);
		assertNull(dao.get(uid));
	}

	/**
	 * Test Delete()
	 */
	@Test
	public void testDelete() {
		entity = new UserStatics();
		entity.setUid(TestConst.uid2);
		entity.setUseruid((TestConst.uid3));
		entity.setPkcount(20);
		entity.setWatchpkcount(30);
		entity.setReplycount(40);
		entity.setJudgecount(50);
		entity.setExpertcount(60);
		entity.setVisitorcount(70);
		entity.setStatus(EStatus.enable);
		entity.setUpdatetime(new DateTime());
		dao.insert(entity);
		assertNotNull(dao.get(TestConst.uid2));
		dao.delete(TestConst.uid2);
		assertNull(dao.get(TestConst.uid2));
	}

}