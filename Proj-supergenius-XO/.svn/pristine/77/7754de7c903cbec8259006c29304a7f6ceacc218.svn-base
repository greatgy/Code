package com.supergenius.xo.sudokuapi.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
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
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.mock.testconstants.TestConst;
import com.supergenius.xo.sudokuapi.entity.Versions;
import com.supergenius.xo.sudokuapi.enums.EGameStatus;
import com.supergenius.xo.sudokuapi.service.VersionsSO;

/**
 * 版本更新测试类
 * 
 * @CreateTime 2018年5月23日--下午7:04:18
 * @author LiuBin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class VersionsDaoTest {

	@Autowired
	VersionsDao dao;
	
	@Autowired
	VersionsSO so;

	Versions entity = null;

	@Before
	public void before() {
		entity = new Versions();
		entity.setUid(TestConst.uid);
		entity.setCreatetime(new Date());
		entity.setUpdatetime(new Date());
		entity.setCompany("com.supergenius.sudokuapp.HUAWEI");
		entity.setCode(2);
		entity.setIntroduce("1、增加了新的功能呢 \n 2、我们会尽力做好很多事情\n 3、修改了好多bug哦");
		boolean result = dao.insert(entity);
		assertTrue(result);
	}

	@After
	public void after() {
		entity = null;
		boolean result = dao.delete(TestConst.uid);
		assertTrue(result);
	}

	@Test
	public void testGet() {
		assertNotNull(dao.get(TestConst.uid));
	}

	@Test
	public void testGetOne() {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.company, "com.supergenius.sudokuapp.HUAWEI");
		assertNotNull(dao.getOne(map));
	}

	@Test
	public void testGetCount() {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.company, "com.supergenius.sudokuapp.HUAWEI");
		assertEquals(1, dao.getCount(map));
	}

	@Test
	public void testGetList() {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.company, "com.supergenius.sudokuapp.HUAWEI");
		List<Versions> list = dao.getList(map);
		assertEquals(1, list.size());
	}

	@Test
	public void testUpdate() {
		entity = dao.get(TestConst.uid);
		entity.setStatus(EGameStatus.init);
		assertTrue(dao.update(entity));
		Versions version = dao.get(TestConst.uid);
		assertEquals(EGameStatus.init, version.getStatus());
	}

	@Test
	public void testUpdateFields() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.status, EGameStatus.init);
		map.put(BaseMapperDict.uid, entity.getUid());
		dao.updateFields(map);
		assertEquals(EGameStatus.init, dao.get(TestConst.uid).getStatus());
	}

	@Test
	public void testDeleteByMap() {
		for (int i = 0; i < 3; i++) {
			Versions version = new Versions();
			version.setStatus(EGameStatus.start);
			dao.insert(version);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.status, EGameStatus.start);
		assertEquals(3, dao.getList(map).size());
		dao.deleteByMap(map);
		assertTrue(dao.getList(map).size() == 0);
	}

}
