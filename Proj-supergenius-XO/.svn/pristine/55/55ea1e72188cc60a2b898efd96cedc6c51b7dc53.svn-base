package com.supergenius.xo.userdel.dao;

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
import com.genius.model.base.enums.EStatus;
import com.supergenius.xo.common.dao.MessageDao;
import com.supergenius.xo.common.entity.Message;
import com.supergenius.xo.common.enums.EMsg;
import com.supergenius.xo.common.enums.EMsgState;
import com.supergenius.xo.mock.testconstants.TestConst;

/**
 * 消息的单元测试
 * @author YuYingJie
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class MessageDaoTest {

	@Autowired
	MessageDao dao;
	
	Message entity;

	@Test
	public void testGet() {
		entity = dao.get(TestConst.uid);
		assertNotNull(entity);
		assertEquals(TestConst.uid, entity.getUid());
	}

	@Test
	public void testGetOne() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.uid, TestConst.uid);
		assertNotNull(dao.getOne(map));
	}

	/**
	 * Test GetList()
	 */
	@Test
	public void testGetList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.uid, TestConst.uid);
		assertTrue(dao.getList(map).size() > 0);
	}
	
	/**
	 * Test GetCount()
	 */
	@Test
	public void testGetCount(){
		assertTrue(dao.getCount(null) >= 1);
	}

	/**
	 * Test Insert()
	 */
	@Test
	public void testInsert() {
		entity = new Message();
		String fromuid = GlobalUtil.getUUID();
		String touid = GlobalUtil.getUUID();
		entity.setFromuseruid(fromuid);
		entity.setTouseruid(touid);
		entity.setContent("测试");
		String commentuid = GlobalUtil.getUUID();
		entity.getDataMap().put("commentuid", commentuid);
		dao.insert(entity);
		String uid = entity.getUid();
		Message message = dao.get(uid);
		assertNotNull(message);
		assertEquals(message.getFromuseruid(), fromuid);
		assertEquals(message.getTouseruid(), touid);
		assertEquals(message.getDataMap().get("commentuid"), commentuid);
		dao.delete(uid);
		assertNull(dao.get(uid));
	}

	/**
	 * Test Update()
	 */
	@Test
	public void testUpdate() {
		entity = dao.get(TestConst.uid);
		entity.setStatus(EStatus.disable);
		dao.update(entity);
		assertEquals(EStatus.disable, dao.get(TestConst.uid).getStatus());
	}

	/**
	 * Test UpdateFields()
	 */
	@Test
	public void testUpdateFields() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.where + BaseMapperDict.touseruid, TestConst.uid);
		map.put(BaseMapperDict.status, EStatus.enable);
		dao.updateFields(map);
		assertEquals(EStatus.enable, dao.get(TestConst.uid).getStatus());
	}

	/**
	 *  Test GetCountByMsgType()
	 */
	@Test
	public void testGetCountByMsgType(){
		entity = new Message();
		String fromuid = GlobalUtil.getUUID();
		String touid = GlobalUtil.getUUID();
		entity.setFromuseruid(fromuid);
		entity.setTouseruid(touid);
		entity.setContent("获得证书");
		entity.setType(EMsg.getcertificate);
		dao.insert(entity);
		String uid = entity.getUid();
		entity = new Message();
		entity.setFromuseruid(fromuid);
		entity.setTouseruid(touid);
		entity.setContent("评论被删除");
		entity.setType(EMsg.delcomment);
		dao.insert(entity);
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> type = new ArrayList<>();
		type.add(EMsg.getcertificate.toString());
		type.add(EMsg.delcomment.toString());
		map.put(BaseMapperDict.type, type);
		assertTrue(dao.getCountByMsgType(map) > 1);
		assertTrue(dao.delete(uid));
		assertTrue(dao.delete(entity.getUid()));
	}

	/**
	 *  Test GetListByMsgType()
	 */
	@Test
	public void testGetListByMsgType(){
		entity = new Message();
		String fromuid = GlobalUtil.getUUID();
		String touid = GlobalUtil.getUUID();
		entity.setFromuseruid(fromuid);
		entity.setTouseruid(touid);
		entity.setContent("获得证书");
		entity.setType(EMsg.getcertificate);
		dao.insert(entity);
		String uid = entity.getUid();
		entity = new Message();
		entity.setFromuseruid(fromuid);
		entity.setTouseruid(touid);
		entity.setContent("评论被删除");
		entity.setType(EMsg.delcomment);
		dao.insert(entity);
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> type = new ArrayList<>();
		type.add(EMsg.getcertificate.toString());
		type.add(EMsg.delcomment.toString());
		map.put(BaseMapperDict.type, type);
		assertNotNull(dao.getListByMsgType(map));
		assertTrue(dao.delete(uid));
		assertTrue(dao.delete(entity.getUid()));
	}
	
	/**
	 * Test DeleteByMap()
	 */
	@Test
	public void testDeleteByMap() {
		String touseruid = GlobalUtil.getUUID();
		for (int i = 0; i < 3; i++) {
			entity = new Message();
			entity.setTouseruid(touseruid);
			entity.setStatus(EStatus.enable);
			dao.insert(entity);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.touseruid, touseruid);
		map.put(BaseMapperDict.status, EStatus.enable);
		assertTrue(dao.getList(map).size() == 3);
		dao.deleteByMap(map);
		assertTrue(dao.getList(map).size() == 0);
	}
	
	/**
	 * Test DeleteByMap()
	 */
	@Test
	public void testUpdateWhere() {
		String touseruid = GlobalUtil.getUUID();
		entity = new Message();
		entity.setTouseruid(touseruid);
		entity.setState(EMsgState.init);
		entity.setStatus(EStatus.enable);
		dao.insert(entity);
		Map<String, Object> map = new HashMap<>();
		map.put(BaseMapperDict.where + BaseMapperDict.touseruid, touseruid);
		map.put(BaseMapperDict.where + "state", EMsgState.init);
		map.put("state", EMsgState.success);
		assertTrue(dao.updateWhere(map));
		assertEquals(dao.get(entity.getUid()).getState(), EMsgState.success);
		assertTrue(dao.delete(entity.getUid()));
	}
	
}
