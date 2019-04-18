package com.supergenius.xo.tpi.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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
import com.genius.core.base.utils.GlobalUtil;
import com.genius.model.base.enums.EStatus;
import com.supergenius.xo.mock.testconstants.TestConst;
import com.supergenius.xo.tpi.entity.Notice;
import com.supergenius.xo.tpi.entity.Team;

/**
 * Team单元测试
 * @author liushaomin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class TeamDaoTest {
	
	@Autowired
	TeamDao dao;
	
	/**
	 * 在执行所有单元测试方法之前插入user
	 */
	@Before
	public void before() {
		Team team = new Team();
		team.setUid(TestConst.uid);
		team.setName(TestConst.name);
		team.setUsername(TestConst.name);
		assertTrue(dao.insert(team));
	}
	
	@After
	public void after() {
		dao.delete(TestConst.uid);
		assertNull(dao.get(TestConst.uid));
	}
	
	@Test
	public void testGet() {
		assertNotNull(dao.get(TestConst.uid));
	}
	
	@Test
	public void testGetOne() {
		Map<String, Object> map = new HashMap<>();
		map.put(BaseMapperDict.name, TestConst.name);
		assertNotNull(dao.getOne(map));
	}
	
	@Test
	public void testGetCount() {
		Map<String, Object> map = new HashMap<>();
		map.put(BaseMapperDict.name, TestConst.name);
		assertEquals(1, dao.getCount(map));
	}
	
	@Test
	public void testGetList() {
		Map<String, Object> map = new HashMap<>();
		map.put(BaseMapperDict.name, TestConst.name);
		List<Team> list = dao.getList(map);
		assertEquals(1, list.size());
	}
	
	@Test
	public void testUpdate() {
		Team entity  = dao.get(TestConst.uid);
		entity.setUsername(TestConst.name1);
		dao.update(entity);
		assertEquals(TestConst.name1, dao.get(TestConst.uid).getUsername());
	}
	
	@Test
	public void testUpdateFields() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.username, TestConst.name);
		map.put(BaseMapperDict.uid, TestConst.uid);
		dao.updateFields(map);
		assertEquals(TestConst.name, dao.get(TestConst.uid).getUsername());
	}
	
	@Test
	public void testDeleteByMap() {
		String name = "testname";
		for (int i = 0; i < 3; i++) {
			Team entity = new Team();
			entity.setName(name);
			dao.insert(entity);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.name, name);
		assertTrue(dao.getList(map).size() == 3);
		dao.deleteByMap(map);
		assertTrue(dao.getList(map).size() == 0);
	}
	
	@Test
	public void testInsert() {
		Team team = new Team();
		String uid = GlobalUtil.getUUID();
		team.setUid(uid);
		team.setName("myteam");
		team.setUsername("caption");
		List<Notice> fundneeds = new ArrayList<>();
		Notice notice = new Notice();
		notice.setUid(GlobalUtil.getUUID());
		notice.setTitle("noticetitle");
		notice.setContent("content...");
		notice.setStatus(EStatus.disable);
		fundneeds.add(notice);
		team.setFundneeds(fundneeds);
		assertTrue(dao.insert(team));
		Team t = dao.get(uid);
		assertNotNull(t);
		assertTrue(t.getFundneeds().size() == 1);
		assertEquals(notice.getUid(), t.getFundneeds().get(0).getUid());
		dao.delete(uid);
	}

}
