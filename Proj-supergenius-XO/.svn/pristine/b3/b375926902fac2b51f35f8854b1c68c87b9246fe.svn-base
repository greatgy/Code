package com.supergenius.xo.moral.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.core.base.constant.BaseStrDict;
import com.genius.model.base.enums.EStatus;
import com.supergenius.moral.moral.dao.ExamstatisticsDao;
import com.supergenius.xo.mock.testconstants.TestConst;
import com.supergenius.xo.moral.entity.Examstatistics;

/**
 * ExamstatisticsDao测试类
 * 
 * @author LiJiacheng
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class ExamstatisticsDaoTest {

	@Autowired
	ExamstatisticsDao dao;

	Examstatistics entity = null;

	@Before
	public void before() {
		List<String> list = new ArrayList<String>();
		list.add("aaaa");
		list.add("bbbb");
		list.add("cccc");
		list.add("dddd");
		entity = new Examstatistics();
		entity.setUid(TestConst.uid);
		entity.setCorrectuids(list);
		entity.setErroruids(list);
		entity.setStatus(EStatus.get(0));
		entity.setUseruid("awkufhawuhfiu");
		entity.setId("aaasda");

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
		map.put(BaseStrDict.uid, TestConst.uid);
		assertNotNull(dao.getOne(map));
	}

	@Test
	public void testGetCount() {
		Map<String, Object> map = new HashMap<>();
		map.put(BaseStrDict.uid, TestConst.uid);
		assertEquals(1, dao.getCount(map));
	}

	@Test
	public void testGetList() {
		Map<String, Object> map = new HashMap<>();
		map.put(BaseStrDict.uid, TestConst.uid);
		List<Examstatistics> exams = dao.getList(map);
		assertEquals(1, exams.size());
	}

	@Test
	public void testUpdate() {
		entity = dao.get(TestConst.uid);
		String userid = entity.getUseruid();
		entity.setUseruid(TestConst.title1);
		assertTrue(dao.update(entity));
		Examstatistics examstatistics = dao.get(TestConst.uid);
		assertEquals(TestConst.title1, examstatistics.getUseruid());
		entity.setUseruid(userid);
		dao.update(entity);
	}

	@Test
	public void testUpdateFields() {
		Map<String, Object> map = new HashMap<String, Object>();
		String useruid = entity.getUseruid();
		map.put(BaseMapperDict.uid, entity.getUid());
		map.put("useruid", "liujnralrnavja");
		dao.updateFields(map);
		map.put(BaseMapperDict.uid, entity.getUid());
		map.put("useruid", useruid);
		dao.updateFields(map);
	}

	@Test
	public void testDeleteByMap() {
		int size = 3;
		String useruid = "auaekjhbraelvgbahjhab";
		for (int i = 0; i < size; i++) {
			Examstatistics examstatistics = new Examstatistics();
			examstatistics.setUseruid(useruid);
			dao.insert(examstatistics);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("useruid", useruid);
		assertEquals(size, dao.getList(map).size());
		dao.deleteByMap(map);
		assertTrue(dao.getList(map).size() == 0);
	}

}
