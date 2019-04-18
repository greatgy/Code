package com.genius.xo.portal.dao;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.core.base.utils.GlobalUtil;
import com.genius.model.base.enums.EStatus;
import com.genius.model.portal.entity.Content;
import com.genius.model.portal.enums.EContent;
import com.genius.xo.portal.mock.testconstants.TestConst;

/**
 * @author Architect.bian
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class ContentDaoTest {

	@Autowired
	ContentDao dao;

	Content entity;

	/**
	 * Test method for
	 * {@link com.mygenius.web.base.dao.BaseDao#get(java.lang.String)}.
	 */
	@Test
	public void testGet() {
		entity = dao.get(1);
		assertNotNull(entity);
		assertEquals(TestConst.uid, entity.getAdminuid());
	}

	/**
	 * Test method for
	 * {@link com.mygenius.web.base.dao.BaseDao#getOne(java.util.Map)}.
	 */
	@Test
	public void testGetOne() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.oid, 1);
		assertNotNull(dao.getOne(map));
	}

	/**
	 * Test method for
	 * {@link com.mygenius.web.base.dao.BaseDao#getList(java.util.Map)}.
	 */
	@Test
	public void testGetList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.oid, 1);
		assertTrue(dao.getList(map).size() > 0);
	}

	/**
	 * Test method for
	 * {@link com.mygenius.web.base.dao.BaseDao#insert(java.lang.Object)}.
	 */
	@Test
	public void testInsert() {
		entity = new Content();
		String uid = GlobalUtil.getUUID();
		entity.setType(EContent.ad.toString());
		entity.setAdminuid(uid);
		entity.setTitle("my title");
		entity.setTitleshort("简短标题");
		entity.setTitleimg("titleimg.jpg");
		entity.setAuthor(TestConst.userId);
		entity.setOrigin("google");
		entity.setOriginurl("haap://g.com");
		entity.setContent("内容");
		entity.setStatus(EStatus.disable);
		dao.insert(entity);
		int id = entity.getOid();
		assertNotNull(dao.get(id));
		dao.delete(id);
		assertNull(dao.get(id));
	}

	/**
	 * Test method for
	 * {@link com.mygenius.web.base.dao.BaseDao#update(java.lang.Object)}.
	 */
	@Test
	public void testUpdate() {
		entity = dao.get(1);
		entity.setType(EContent.ad.toString());
		dao.update(entity);
		assertEquals(EContent.ad.toString(), dao.get(1).getType());
	}

	/**
	 * Test method for
	 * {@link com.mygenius.web.base.dao.BaseDao#updateFields(java.util.Map)}.
	 */
	@Test
	public void testUpdateFields() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.oid, 1);
		map.put(BaseMapperDict.type, EContent.ad.toString());
		dao.updateFields(map);
		assertEquals(EContent.ad.toString(), dao.get(1).getType());
	}

}
