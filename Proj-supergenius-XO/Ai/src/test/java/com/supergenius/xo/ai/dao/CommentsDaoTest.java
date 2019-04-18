package com.supergenius.xo.ai.dao;

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
import com.supergenius.xo.mock.testconstants.TestConst;
import com.supergenius.xo.ai.entity.Comments;
import com.supergenius.xo.ai.enums.EComment;

/**
 * 评论测试类
 * 
 * @author xuzhixiang
 * @date 2017年9月19日09:59:01
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
		map.put(BaseMapperDict.uid, TestConst.uid1);
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
		map.put(BaseMapperDict.uid, TestConst.uid1);
		assertTrue(dao.getList(map).size() > 0);
	}

	/**
	 * Test Insert()
	 */
	@Test
	public void testInsert() {
		entity = new Comments();
		entity.setUid(TestConst.uid2);
		entity.setFromuid("uidf1000000000000000000000000000");
		entity.setTouid("uidt1000000000000000000000000000");
		entity.setFromuseruid("uidf2000000000000000000000000000");
		entity.setFromuseroid(1);
		entity.setFromusername("zhangsan");
		entity.setTouseruid("uidt2000000000000000000000000000");
		entity.setTouseroid(2);
		entity.setTousername("lisi");
		entity.setContent("我是一条评论");
		entity.setType(EComment.comment);
		entity.setCreatetime(new DateTime());
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
		entity = new Comments();
		entity.setUid(TestConst.uid2);
		entity.setFromuid("uidf1000000000000000000000000000");
		entity.setTouid("uidt1000000000000000000000000000");
		entity.setFromuseruid("uidf2000000000000000000000000000");
		entity.setFromuseroid(1);
		entity.setFromusername("zhangsan");
		entity.setTouseruid("uidt2000000000000000000000000000");
		entity.setTouseroid(2);
		entity.setTousername("lisi");
		entity.setContent("我是一条评论");
		entity.setType(EComment.comment);
		entity.setCreatetime(new DateTime());
		dao.insert(entity);
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		entity.setFromusername("wangwu");
		assertTrue(dao.update(entity));
		dao.delete(uid);
		assertNull(dao.get(uid));
	}

	/**
	 * Test UpdateFields()
	 */
	@Test
	public void testUpdateFields() {
		entity = new Comments();
		entity.setUid(TestConst.uid2);
		entity.setFromuid("uidf1000000000000000000000000000");
		entity.setTouid("uidt1000000000000000000000000000");
		entity.setFromuseruid("uidf2000000000000000000000000000");
		entity.setFromuseroid(1);
		entity.setFromusername("zhangsan");
		entity.setTouseruid("uidt2000000000000000000000000000");
		entity.setTouseroid(2);
		entity.setTousername("lisi");
		entity.setContent("我是一条评论");
		entity.setType(EComment.comment);
		entity.setCreatetime(new DateTime());
		dao.insert(entity);
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		Map<String, Object> map = new HashMap<>();
		map.put("uid", uid);
		map.put("fromusername", "xiaoming");
		map.put("tousername", "xiaohong");
		assertTrue(dao.updateFields(map));
		dao.delete(uid);
		assertNull(dao.get(uid));
	}

	/**
	 * Test Delete()
	 */
	@Test
	public void testDelete() {
		entity = new Comments();
		entity.setUid(TestConst.uid2);
		entity.setFromuid("uidf1000000000000000000000000000");
		entity.setTouid("uidt1000000000000000000000000000");
		entity.setFromuseruid("uidf2000000000000000000000000000");
		entity.setFromuseroid(1);
		entity.setFromusername("zhangsan");
		entity.setTouseruid("uidt2000000000000000000000000000");
		entity.setTouseroid(2);
		entity.setTousername("lisi");
		entity.setContent("我是一条评论");
		entity.setType(EComment.comment);
		entity.setCreatetime(new DateTime());
		dao.insert(entity);
		assertNotNull(dao.get(TestConst.uid2));
		dao.delete(TestConst.uid2);
		assertNull(dao.get(TestConst.uid2));
	}
}
