package com.supergenius.xo.sudokuapi.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.x500.X500Principal;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.Lifecycle;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.core.base.utils.JsonUtil;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.mock.testconstants.TestConst;
import com.supergenius.xo.sudokuapi.entity.Share;
import com.supergenius.xo.sudokuapi.enums.EAddress;
import com.supergenius.xo.sudokuapi.enums.EGameStatus;
import com.supergenius.xo.sudokuapi.service.ShareSO;

/**
 * 分享测试类
 * 
 * @CreateTime 2018年5月23日--下午7:04:18
 * @author LiuBin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class ShareDaoTest {

	@Autowired
	ShareDao dao;
	
	@Autowired
	ShareSO so;

	Share entity = null;

	//@Before
	public void before() {
		entity = new Share();
		entity.setUid(TestConst.uid);
		entity.setCreatetime(new Date());
		entity.setUpdatetime(new Date());
		entity.setText("测试分享内容");
		entity.setVersion(2);
		entity.setAddress(EAddress.computergameover);
		entity.setUrl("http://share.supergenius.cn");
		boolean result = dao.insert(entity);
		assertTrue(result);
	}

	//@After
	public void after() {
		entity = null;
		boolean result = dao.delete(TestConst.uid);
		assertTrue(result);
	}

	@Test
	public void testGet() {
		
		dao.delete(TestConst.uid);
	}

	@Test
	@Ignore
	public void testGetOne() {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.uid, TestConst.uid);
		assertNotNull(dao.getOne(map));
	}

	@Test
	@Ignore
	public void testGetCount() {
		Map<String, Object> map = new HashMap<>();
		map.put(TestConst.version, 2);
		assertEquals(1, dao.getCount(map));
	}

	@Test
	@Ignore
	public void testGetList() {
		Map<String, Object> map = new HashMap<>();
		map.put(TestConst.version, 2);
		List<Share> list = dao.getList(map);
		assertEquals(1, list.size());
	}

	@Test
	public void testUpdate() {
		entity = dao.get(TestConst.uid);
		entity.setStatus(EGameStatus.init);
		assertTrue(dao.update(entity));
		Share version = dao.get(TestConst.uid);
		assertEquals(EGameStatus.init, version.getStatus());
	}

	@Test
	public void testUpdateFields() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.status, EGameStatus.enable);
		map.put(BaseMapperDict.uid, entity.getUid());
		dao.updateFields(map);
		assertEquals(EGameStatus.enable, dao.get(TestConst.uid).getStatus());
	}

	@Test
	public void testDeleteByMap() {
		for (int i = 0; i < 3; i++) {
			Share share = new Share();
			share.setStatus(EGameStatus.start);
			dao.insert(share);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.status, EGameStatus.start);
		assertEquals(3, dao.getList(map).size());
		dao.deleteByMap(map);
		assertTrue(dao.getList(map).size() == 0);
	}

}