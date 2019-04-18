package com.supergenius.xo.enterpriser.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
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
import com.supergenius.xo.enterpriser.entity.Comments;
import com.supergenius.xo.mock.testconstants.TestConst;

/**
 * 评论测试类
 * @author loupengyu
 * @date 2016-10-24 下午4:56:51
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class CommentsDaoTest {
	
	@Autowired
	private CommentsDao dao;

	private Comments entity;
	
	/**
	 * Test Get()
	 */
	@Before
	public void before(){

		entity = new Comments();
		entity.setUid(TestConst.uid);
		entity.setFromuid(TestConst.uid1);
		entity.setCataloguename("ss");
		entity.setContent("测试");
		entity.setData("以json的方式记录");
		entity.setCreatetime(new DateTime());
		entity.setUpdatetime(new DateTime());
		boolean result = dao.insert(entity);
		assertTrue(result);
	}
	
	@After
	public void After(){
		
		entity = null;
		boolean result = dao.delete(TestConst.uid);
		assertTrue(result);
	}
	/**
	 * Test Get()
	 */
	@Test
	public void testGet() {
		entity = dao.get(TestConst.uid);
		assertNotNull(entity);
		assertEquals(TestConst.uid,entity.getUid());
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
	public void testCount() {
		assertTrue(dao.getCount(null) >= 1);
	}
	
	/**
	 * Test GetList()
	 */
	@Test
	public void testGetList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.uid,TestConst.uid);
		assertTrue(dao.getList(map).size() > 0);
	}
	
	/**
	 * Test Update()
	 */
	@Test
	public void testUpdate() {
		entity = new Comments();
		entity.setUid(TestConst.uid);
		entity.setFromuid(TestConst.uid1);
		entity.setCataloguename("sss");
		entity.setContent("测试1");
		entity.setData("以json的方式记录");
		entity.setCreatetime(new DateTime());
		entity.setUpdatetime(new DateTime());
		assertTrue(dao.update(entity));
	}
}
