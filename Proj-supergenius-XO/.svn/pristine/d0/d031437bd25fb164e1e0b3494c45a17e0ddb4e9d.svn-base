package com.supergenius.xo.tpi.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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
import com.genius.model.base.enums.EStatus;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.mock.testconstants.TestConst;
import com.supergenius.xo.tpi.entity.Comment;
import com.supergenius.xo.tpi.enums.ECommentType;

/**
 * Comment单元测试
 * @author liushaomin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class CommentDaoTest {
	
	@Autowired
	CommentDao dao;
	
	/**
	 * 在执行所有单元测试方法之前插入user
	 */
	@Before
	public void before() {
		Comment comment = new Comment();
		comment.setUid(TestConst.uid);
		comment.setTousername(TestConst.name);
		comment.setFromusername(TestConst.name1);
		assertTrue(dao.insert(comment));
	}
	
	@After
	public void after() {
		dao.delete(TestConst.uid);
		assertNull(dao.get(TestConst.uid));
	}
	
	@Ignore
	@Test
	public void insertInitData(){
		for (EChannel channel : EChannel.values()) {
			Comment comment = new Comment();
			comment.setTousername(TestConst.name);
			comment.setFromusername(TestConst.name1);
			comment.setTouseruid(TestConst.uid1);
			comment.setStatus(EStatus.enable);
			comment.setType(ECommentType.comment);
			comment.setChannel(channel);
			comment.setContent("一个特别长的评论内容content");
			assertTrue(dao.insert(comment));
		}
	}
	
	@Test
	public void testGet() {
		assertNotNull(dao.get(TestConst.uid));
	}
	
	@Test
	public void testGetOne() {
		Map<String, Object> map = new HashMap<>();
		map.put("tousername", TestConst.name);
		assertNotNull(dao.getOne(map));
	}
	
	@Test
	public void testGetCount() {
		Map<String, Object> map = new HashMap<>();
		map.put("tousername", TestConst.name);
		assertEquals(1, dao.getCount(map));
	}
	
	@Test
	public void testGetList() {
		Map<String, Object> map = new HashMap<>();
		map.put("tousername", TestConst.name);
		List<Comment> list = dao.getList(map);
		assertEquals(1, list.size());
	}
	
	@Test
	public void testUpdate() {
		Comment entity  = dao.get(TestConst.uid);
		entity.setFromusername(TestConst.name);
		dao.update(entity);
		assertEquals(TestConst.name, dao.get(TestConst.uid).getFromusername());
	}
	
	@Test
	public void testUpdateFields() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fromusername", TestConst.name1);
		map.put(BaseMapperDict.uid, TestConst.uid);
		dao.updateFields(map);
		assertEquals(TestConst.name1, dao.get(TestConst.uid).getFromusername());
	}
	
	@Test
	public void testDeleteByMap() {
		String name = "testname";
		for (int i = 0; i < 3; i++) {
			Comment entity = new Comment();
			entity.setTousername(name);
			dao.insert(entity);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tousername", name);
		assertTrue(dao.getList(map).size() == 3);
		dao.deleteByMap(map);
		assertTrue(dao.getList(map).size() == 0);
	}

}
