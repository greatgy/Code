package com.supergenius.xo.enterpriser.dao;

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
import com.genius.core.base.utils.DateUtil;
import com.genius.model.base.enums.EStatus;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.enterpriser.entity.Lecture;
import com.supergenius.xo.mock.testconstants.TestConst;

/** 
 * 演讲表dao测试
 * @author chenminchang
 * @date 2016-10-24 下午5:57:58 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class LectureDaoTest {
	@Autowired
	private LectureDao dao;

	private Lecture entity;

	/**
	 * Test Get()
	 */
	@Test
	public void testGet() {
		entity = dao.get(TestConst.uid);
		assertNotNull(entity);
	}

	/**
	 * Test GetOne()
	 */
	@Test
	public void testGetOne() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.uid, TestConst.uid);
		entity = dao.getOne(map);
		assertNotNull(entity);
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
		entity = new Lecture();
		entity.setUid(TestConst.uid2);
		entity.setSn(TestConst.usersn);
		entity.setName("引资购商专题讲座");
		entity.setUsername("张三");
		entity.setTime(DateUtil.NowTime());
		entity.setAddress("北京");
		entity.setNotice("报名须知");
		entity.setFee(40000);
		entity.setMaxcount(100);
		entity.setRegistercount(10);
		entity.setSemester(1);
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
		entity = new Lecture();
		entity.setUid(TestConst.uid2);
		entity.setSn(TestConst.usersn);
		entity.setName("引资购商专题讲座");
		entity.setUsername("张三");
		entity.setTime(DateUtil.NowTime());
		entity.setAddress("北京");
		entity.setNotice("报名须知");
		entity.setFee(40000);
		entity.setMaxcount(100);
		entity.setRegistercount(10);
		entity.setSemester(1);
		entity.setStatus(EStatus.deleted);
		entity.setUpdatetime(new DateTime());
		dao.insert(entity);
		String uid = entity.getUid();
		entity.setUsername("张四");
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
		entity = new Lecture();
		entity.setUid(TestConst.uid2);
		entity.setSn(TestConst.usersn);
		entity.setName("引资购商专题讲座");
		entity.setUsername("张三");
		entity.setTime(DateUtil.NowTime());
		entity.setAddress("北京");
		entity.setNotice("报名须知");
		entity.setFee(40000);
		entity.setMaxcount(100);
		entity.setRegistercount(10);
		entity.setSemester(1);
		entity.setStatus(EStatus.deleted);
		entity.setUpdatetime(new DateTime());
		dao.insert(entity);
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.uid, uid);
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
		entity = new Lecture();
		entity.setUid(TestConst.uid2);
		entity.setSn(TestConst.usersn);
		entity.setName("引资购商专题讲座");
		entity.setUsername("张三");
		entity.setTime(DateUtil.NowTime());
		entity.setAddress("北京");
		entity.setNotice("报名须知");
		entity.setFee(40000);
		entity.setMaxcount(100);
		entity.setRegistercount(10);
		entity.setSemester(1);
		entity.setStatus(EStatus.deleted);
		entity.setUpdatetime(new DateTime());
		dao.insert(entity);
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		dao.delete(uid);
		assertNull(dao.get(uid));
	}

}