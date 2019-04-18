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
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.xo.baseadmin.mock.testconstants.TestConst;

/**
 * 管理员操作日志表的单元测试
 * @author liushaomin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class AdminLogDaoTest {
	
	@Autowired
	AdminLogDao dao;

	AdminLog entity;

	@Test
	public void testGet() {
		entity = dao.get(TestConst.uid1);
		assertNotNull(entity);
		assertEquals(TestConst.uid1, entity.getUid());
	}

	@Test
	public void testGetOne() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.adminuid, TestConst.uid1);
		assertNotNull(dao.getOne(map));
	}

	@Test
	public void testGetList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.adminuid, TestConst.uid1);
		assertTrue(dao.getList(map).size() > 0);
	}

	@Test
	public void testInsert() {
		entity = new AdminLog();
		String uid = GlobalUtil.getUUID();
		entity.setUid(uid);
		entity.setAdminuid(TestConst.uid1);
		entity.setAdminname("测试");
		entity.setDataid(TestConst.uid);
		entity.setChannel(0);
		entity.setOperation("测试");
		entity.setData("测试");
		entity.setDesc("测试");
		dao.insert(entity);
		assertNotNull(dao.get(uid));
		dao.delete(uid);
		assertNull(dao.get(uid));
	}

	@Test
	public void testGetOneByAdminid() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.adminuid, TestConst.uid1);
		assertNotNull(dao.getOne(map));
	}

}
