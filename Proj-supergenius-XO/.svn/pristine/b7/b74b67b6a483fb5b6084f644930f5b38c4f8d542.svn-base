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
import com.genius.core.base.utils.GlobalUtil;
import com.supergenius.xo.mock.testconstants.TestConst;
import com.supergenius.xo.tpi.entity.Message;
import com.supergenius.xo.tpi.enums.EFromUserType;
import com.supergenius.xo.tpi.enums.EMsg;
import com.supergenius.xo.tpi.enums.EMsgState;
/**
 * Message单元测试
 * @author liushaomin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class MessageDaoTest {
	
	@Autowired
	MessageDao dao;
	
	/**
	 * 在执行所有单元测试方法之前插入user
	 */
	@Before
	public void before() {
		Message message = new Message();
		message.setUid(TestConst.uid);
		message.setTitle(TestConst.title);
		assertTrue(dao.insert(message));
	}
	
	@After
	public void after() {
		dao.delete(TestConst.uid);
		assertNull(dao.get(TestConst.uid));
	}
	
	@Test
	public void testGet() {
		assertNotNull(dao.get(TestConst.uid));
	}
	
	@Test
	public void testGetOne() {
		Map<String, Object> map = new HashMap<>();
		map.put(BaseMapperDict.title, TestConst.title);
		assertNotNull(dao.getOne(map));
	}
	
	@Test
	public void testGetCount() {
		Map<String, Object> map = new HashMap<>();
		map.put(BaseMapperDict.title, TestConst.title);
		assertEquals(1, dao.getCount(map));
	}
	
	@Test
	public void testGetList() {
		Map<String, Object> map = new HashMap<>();
		map.put(BaseMapperDict.title, TestConst.title);
		List<Message> list = dao.getList(map);
		assertEquals(1, list.size());
	}
	
	@Test
	public void testUpdate() {
		Message entity  = dao.get(TestConst.uid);
		entity.setTitle(TestConst.title1);
		dao.update(entity);
		assertEquals(TestConst.title1, dao.get(TestConst.uid).getTitle());
	}
	
	@Test
	public void testUpdateFields() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.title, TestConst.title);
		map.put(BaseMapperDict.uid, TestConst.uid);
		dao.updateFields(map);
		assertEquals(TestConst.title, dao.get(TestConst.uid).getTitle());
	}
	
	@Test
	public void testDeleteByMap() {
		String title = "testtitle";
		for (int i = 0; i < 3; i++) {
			Message entity = new Message();
			entity.setTitle(title);
			dao.insert(entity);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.title, title);
		assertTrue(dao.getList(map).size() == 3);
		dao.deleteByMap(map);
		assertTrue(dao.getList(map).size() == 0);
	}

	/**
	 * test 添加数据
	 * @author liushaomin
	 */
	@Test
	@Ignore
	public void test() {
		Message msg = new Message();
		String uid = GlobalUtil.getUUID();
		msg.setUid(uid);
		msg.setFromusername("lisi");
		msg.setFromuseruid(TestConst.uid2);
		msg.setTitle("创建团队邀请函");
		msg.setContent("hello world ");
		msg.setTouseruid("93483b04795f48d0a70b1f9004c1f98f");
		msg.setTousername("张三");
		msg.setState(EMsgState.init);
		msg.setUsertype(EFromUserType.merger);
		msg.setType(EMsg.usermsg);
		dao.insert(msg);
	}
	
}
