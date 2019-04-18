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
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.core.base.utils.GlobalUtil;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.mock.testconstants.TestConst;
import com.supergenius.xo.tpi.entity.Project;
import com.supergenius.xo.tpi.entity.Wish;
import com.supergenius.xo.tpi.enums.EWishType;

/**
 * WishDetail单元测试
 * @author liushaomin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class WishDaoTest {
	
	@Autowired
	WishDao dao;
	
	/**
	 * 在执行所有单元测试方法之前插入user
	 */
	@Before
	public void before() {
		Wish wish = new Wish();
		wish.setUid(TestConst.uid);
		wish.setTitle(TestConst.title);
		assertTrue(dao.insert(wish));
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
		map.put(BaseMapperDict.title, TestConst.title);
		assertNotNull(dao.getOne(map));
	}
	
	@Test
	public void testGetCount() {
		Map<String, Object> map = new HashMap<>();
		map.put(BaseMapperDict.title, TestConst.title);
		assertEquals(1, dao.getCount(map));
	}
	
	@Test
	public void testGetList() {
		Map<String, Object> map = new HashMap<>();
		map.put(BaseMapperDict.title, TestConst.title);
		List<Wish> list = dao.getList(map);
		assertEquals(1, list.size());
	}
	
	@Test
	public void testUpdate() {
		Wish entity  = dao.get(TestConst.uid);
		entity.setTitle(TestConst.title1);
		dao.update(entity);
		assertEquals(TestConst.title1, dao.get(TestConst.uid).getTitle());
	}
	
	@Test
	public void testUpdateFields() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.title, TestConst.title);
		map.put(BaseMapperDict.uid, TestConst.uid);
		dao.updateFields(map);
		assertEquals(TestConst.title, dao.get(TestConst.uid).getTitle());
	}
	
	@Test
	public void testDeleteByMap() {
		String title = "testtitle";
		for (int i = 0; i < 3; i++) {
			Wish entity = new Wish();
			entity.setTitle(title);
			dao.insert(entity);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.title, title);
		assertTrue(dao.getList(map).size() == 3);
		dao.deleteByMap(map);
		assertTrue(dao.getList(map).size() == 0);
	}
	
	
	/**
	 * 插入测试数据，用作并购方案管理功能的开发
	 * type 想并购
	 * channel 项目
	 */
	@Test
	@Ignore
	public void testWishDetail() {
		Wish wish = new Wish();
		wish.setUid(GlobalUtil.getUUID());
		wish.setFromuid(TestConst.uid2);
		wish.setFromname(TestConst.username);
		wish.setTitle("genius project");
		wish.setTouid(TestConst.uid1);
		wish.setContent("content");
		wish.setType(EWishType.wantMergers);
		wish.setChannel(EChannel.project);
		dao.insert(wish);
	}
	
	@Autowired
	ProjectDao projectDao;
	@Ignore
	@Test
	public void insert1(){
		List<Project> list = projectDao.getList(new HashMap<String, Object>());
		List<Wish> list2 = new ArrayList<>();
		for (Project project : list) {
			Wish wish = new Wish();
			wish.setUid(GlobalUtil.getUUID());
			wish.setFromuid("950781a28b1f40abaa1f0c761a35e218");
			wish.setFromname("投资有限公司");
			wish.setType(EWishType.wantMergers);
			wish.setChannel(EChannel.project);
//			wish.setTitle("genius project");
//			wish.setContent("content");
			wish.setTouid(project.getUid());
			list2.add(wish);
		}
		dao.insertList(list2);
//		dao.insert(wish);
	}

}
