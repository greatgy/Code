package com.supergenius.xo.moral.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
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
import com.genius.core.base.constant.BaseStrDict;
import com.supergenius.moral.moral.dao.VideoDao;
import com.supergenius.xo.mock.testconstants.TestConst;
import com.supergenius.xo.moral.entity.Video;

/**
 * VideoDao测试类
 * 
 * @author LiJiacheng
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class VideoDaoTest {

	@Autowired
	VideoDao dao;

	Video entity = null;

	@Before
	public void before() {
		entity = new Video();
		entity.setUid(TestConst.uid);
		entity.setCode("aweouifhflaiwasrgbaergaerfbgaergaerfgaergaerthsrhgjuhfwauawgj");
		entity.setCountpl(1);
		entity.setImg("aiuhfaiw");
		entity.setCreatetime(DateTime.now());

		boolean result = dao.insert(entity);
		assertTrue(result);
	}

	@After
	public void after() {
		entity = null;
		boolean result = dao.delete(TestConst.uid);
		assertTrue(result);
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
		List<Video> videos = dao.getList(map);
		assertEquals(1, videos.size());
	}

	@Test
	public void testUpdate() {
		entity = dao.get(TestConst.uid);
		String name = entity.getName();
		entity.setName(TestConst.name1);
		assertTrue(dao.update(entity));
		Video video = dao.get(TestConst.uid);
		assertEquals(TestConst.name1, video.getName());
		entity.setName(name);
		dao.update(entity);
	}

	@Test
	public void testUpdateFields() {
		Map<String, Object> map = new HashMap<String, Object>();
		String name = entity.getName();
		map.put(BaseMapperDict.uid, entity.getUid());
		map.put(BaseMapperDict.name, TestConst.name1);
		dao.updateFields(map);
		map.put(BaseMapperDict.uid, entity.getUid());
		map.put(BaseMapperDict.name, name);
		dao.updateFields(map);
	}

	@Test
	public void testDeleteByMap() {
		int size = 3;
		String name = "auaekjhbraelvgbahjhab";
		for (int i = 0; i < size; i++) {
			Video video = new Video();
			video.setName(name);
			dao.insert(video);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.name, name);
		assertEquals(size, dao.getList(map).size());
		dao.deleteByMap(map);
		assertTrue(dao.getList(map).size() == 0);
	}
}
