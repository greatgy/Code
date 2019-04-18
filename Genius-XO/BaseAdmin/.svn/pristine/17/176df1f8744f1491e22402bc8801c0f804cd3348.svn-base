package com.genius.xo.baseadmin.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.core.base.utils.GlobalUtil;
import com.genius.model.baseadmin.entity.Authority;
import com.genius.xo.baseadmin.mock.testconstants.TestConst;

/**
 * @author LiuYongjian
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class AuthorityDaoTest {
	
	@Autowired
	AuthorityDao dao;

	Authority entity;

	@Test
	public void testGet() {
		entity = dao.get(TestConst.uid);
		assertNotNull(entity);
	}
	
	/**
	 * Test method for
	 * {@link com.wbcom.web.base.dao.BaseDao#getOne(java.util.Map)}.
	 */
	@Test
	public void testGetOne() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.uid, TestConst.uid);
		assertNotNull(dao.getOne(map));
	}

	/**
	 * Test method for
	 * {@link com.wbcom.web.base.dao.BaseDao#getList(java.util.Map)}.
	 */
	@Test
	public void testGetList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.uid, TestConst.uid);
		assertTrue(dao.getList(map).size() > 0);
	}

	/**
	 * Test method for
	 * {@link com.wbcom.web.base.dao.BaseDao#insert(java.lang.Object)}.
	 */
	@Test
	public void testInsert() {
		entity = new Authority();
		String uid = GlobalUtil.getUUID();
		entity.setUid(uid);
		entity.setRoleuid(uid);
		entity.setRoleid("authority");
		entity.setDesc("desc");
		entity.setName("name");
		entity.setUrl("url");
		dao.insert(entity);
		assertNotNull(dao.get(uid));
		dao.delete(uid);
		assertNull(dao.get(uid));
	}

	/**
	 * Test method for
	 * {@link com.wbcom.web.base.dao.BaseDao#update(java.lang.Object)}.
	 */
	@Test
	public void testUpdate() {
		entity = dao.get(TestConst.uid);
		dao.update(entity);
		// assertEquals(EStatus.disable,
		// dao.get(TestConstants.uidpk).getStatus());
	}

	/**
	 * Test method for
	 * {@link com.wbcom.web.base.dao.BaseDao#updateFields(java.util.Map)}.
	 */
	@Test
	public void testUpdateFields() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.uid, TestConst.uid);
		map.put(BaseMapperDict.name, TestConst.name);
		dao.updateFields(map);
		assertEquals(TestConst.name, dao.get(TestConst.uid).getName());
	}

	/**
	 * Test method for
	 * {@link com.wbcom.web.base.dao.BaseDao#deleteByMap(java.util.Map)}.
	 */
	@Test
	public void testDeleteByMap() {
		String uid = GlobalUtil.getUUID();
		for (int i = 0; i < 3; i++) {
			entity = new Authority();
			entity.setUid(GlobalUtil.getUUID());
			entity.setRoleuid(uid);
			entity.setRoleid("authority");
			entity.setDesc("desc");
			entity.setName("name");
			entity.setUrl("url");
			dao.insert(entity);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "name");
		dao.deleteByMap(map);
		
	}

}
