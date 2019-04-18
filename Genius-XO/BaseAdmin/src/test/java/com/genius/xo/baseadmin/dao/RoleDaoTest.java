package com.genius.xo.baseadmin.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.core.base.utils.GlobalUtil;
import com.genius.model.baseadmin.entity.Role;
import com.genius.xo.baseadmin.mock.testconstants.TestConst;

/**
 * @author Architect.bian
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class RoleDaoTest {

	@Autowired
	RoleDao dao;

	Role entity;

	/**
	 * Test method for
	 * {@link com.mygenius.web.base.dao.BaseDao#get(java.lang.String)}.
	 */
	@Test
	public void testGet() {
		entity = dao.get(TestConst.uid1);
		assertNotNull(entity);
		assertEquals(TestConst.uid1, entity.getUid());
	}

	/**
	 * Test method for
	 * {@link com.mygenius.web.base.dao.BaseDao#getOne(java.util.Map)}.
	 */
	@Test
	public void testGetOne() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.userid, "admin");
		assertNotNull(dao.getOne(map));
	}

	/**
	 * Test method for
	 * {@link com.mygenius.web.base.dao.BaseDao#getList(java.util.Map)}.
	 */
	@Test
	public void testGetList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.userid, "admin");
		assertTrue(dao.getList(map).size() > 0);
	}
	
	/**
	 * Test method for
	 * {@link com.mygenius.web.base.dao.BaseDao#insert(java.lang.Object)}.
	 */
	@Test
	public void testInsert() {
		entity = new Role();
		String uid = GlobalUtil.getUUID();
		entity.setUid(uid);
		entity.setAdminid(GlobalUtil.getUUID());
		entity.setRoleid("ROLE_finance");
		entity.setDesc("出纳兼会计角色描述");
		entity.setRolename("出纳兼会计");
		dao.insert(entity);
		assertNotNull(dao.get(uid));
		dao.update(entity);
		dao.delete(uid);
		assertNull(dao.get(uid));
	}

	@Test
	public void testUpdate() {
		entity = dao.get(TestConst.uid1);
		entity.setAdminid(GlobalUtil.getUUID());
		entity.setRoleid("ROLE_finance");//最高管理权限
		entity.setDesc("出纳兼会计角色描述");
		entity.setRolename("出纳兼会计");
		dao.update(entity);
		assertEquals("出纳兼会计角色描述", dao.get(TestConst.uid1).getDesc());
	}

	@Test
	public void testUpdateFields() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.uid, TestConst.uid1);
		map.put(BaseMapperDict.adminid, TestConst.uid2);
		dao.updateFields(map);
		assertEquals(TestConst.uid2, dao.get(TestConst.uid1).getAdminid());
	}

	@Test
	public void testInsertValues() {
		List<Role> list = new ArrayList<>();
		String uid = GlobalUtil.getUUID();
		for (int i = 0; i < 2; i++) {
			entity = new Role();
			entity.setUid(GlobalUtil.getUUID());
			entity.setAdminid(uid);
			entity.setRoleid("ROLE_xxx");
			entity.setDesc("出纳兼会计角色描述");
			entity.setRolename("出纳兼会计");
			list.add(entity);
		}
		dao.insertList(list);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.adminid, uid);
		assertNotNull(dao.getList(map));
		assertTrue(dao.getList(map).size() == list.size());
		dao.deleteByMap(map);
		assertTrue(dao.getList(map).size() == 0);
	}
	
}
