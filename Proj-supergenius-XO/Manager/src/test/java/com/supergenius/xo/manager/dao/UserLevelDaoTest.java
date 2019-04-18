package com.supergenius.xo.manager.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.model.base.enums.EStatus;
import com.supergenius.xo.base.enums.EUser;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.manager.entity.UserLevel;
import com.supergenius.xo.manager.enums.ELevelChannel;
import com.supergenius.xo.manager.enums.EMajor;
import com.supergenius.xo.manager.service.UserLevelSO;
import com.supergenius.xo.mock.testconstants.TestConst;
import com.supergenius.xo.user.dao.UserDao;
import com.supergenius.xo.user.entity.User;

/**
 * UserLevel测试类
 * @author liubin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class UserLevelDaoTest {

	@Autowired
	private UserLevelDao dao;
	@Autowired
	private UserLevelSO so;
	@Autowired
	private UserDao userDao;

	private UserLevel entity;
	
	@Test
	@Ignore
	public void testUserLevelData() {
		int i = 0;
		List<User> list =userDao.getList(null);
		EMajor[] majorArr = EMajor.values();
		for (User user : list) {
			for (EMajor major : majorArr) {
				List<UserLevel> userlevelList = so.getList(user.getUid(), EUser.student, major);
				if (userlevelList.size() > 1) {
					i++;
					for (UserLevel userlevel : userlevelList) {
						System.out.println( "------------ userleveluid:" + userlevel.getUid());
					}
				}
			}
		}
		System.out.println("共：" + i);//i=0
	}

	/**
	 * Test Get()
	 */
	@Test
	public void testGet() {
		entity = dao.get(TestConst.uid3);
		assertNotNull(entity);
		assertNotNull(entity.getUseruid());
		assertNotNull(entity.getMajor());
		assertNotNull(entity.getLevelfrom());
		assertNotNull(entity.getLevelto());
		assertNotNull(entity.getType());
		assertNotNull(entity.getChannel());
		assertNotNull(entity.getStatus());
		assertNotNull(entity.getCreatetime());
		assertNotNull(entity.getUpdatetime());
	}

	/**
	 * Test GetOne()
	 */
	@Test
	public void testGetOne() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.useruid, TestConst.uid);
		entity = dao.getOne(map);
		assertNotNull(entity);
	}

	/**
	 * Test GetCount()
	 */
	@Test
	public void testGetCount() {
		assertTrue(dao.getCount(null) >= 1);
	}

	/**
	 * Test GetList()
	 */
	@Test
	public void testGetList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.status, EStatus.enable);
		assertTrue(dao.getList(map).size() > 0);
	}

	/**
	 * Test Insert()
	 */
	@Test
	public void testInsert() {
		entity = new UserLevel();
		entity.setUid(TestConst.uid);
		entity.setUseruid((TestConst.uid2));
		entity.setMajor(EMajor.marketManage);
		entity.setLevelfrom(1);
		entity.setLevelto(2);
		entity.setType(EUser.student);
		entity.setCertificateuid("certificateuid123456796");
		entity.setArguments("something");
		entity.setDesc("nothings");
		entity.setChannel(ELevelChannel.fulltimeJudgement);
		entity.setStatus(EStatus.enable);
		entity.setUpdatetime(new DateTime());
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
		entity = new UserLevel();
		entity.setUid(TestConst.uid);
		entity.setUseruid((TestConst.uid2));
		entity.setMajor(EMajor.marketManage);
		entity.setLevelfrom(1);
		entity.setLevelto(2);
		entity.setType(EUser.student);
		entity.setCertificateuid("certificateuid123456796");
		entity.setArguments("something");
		entity.setDesc("nothings");
		entity.setChannel(ELevelChannel.fulltimeJudgement);
		entity.setStatus(EStatus.enable);
		entity.setUpdatetime(new DateTime());
		dao.insert(entity);
		String uid = entity.getUid();
		entity.setUseruid((TestConst.uid1));
		entity.setMajor(EMajor.mediaManage);
		entity.setLevelfrom(1);
		entity.setLevelto(2);
		assertTrue(dao.update(entity));
		dao.delete(uid);
		assertNull(dao.get(uid));
	}

	/**
	 * Test UpdateFields()
	 */
	@Test
	public void testUpdateFields() {
		entity = new UserLevel();
		entity.setUid(TestConst.uid);
		entity.setUseruid((TestConst.uid2));
		entity.setMajor(EMajor.marketManage);
		entity.setLevelfrom(1);
		entity.setLevelto(2);
		entity.setType(EUser.student);
		entity.setCertificateuid("certificateuid123456796");
		entity.setArguments("something");
		entity.setDesc("nothings");
		entity.setChannel(ELevelChannel.fulltimeJudgement);
		entity.setStatus(EStatus.enable);
		entity.setUpdatetime(new DateTime());
		dao.insert(entity);
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.uid, uid);
		map.put(MapperDict.useruid, TestConst.uid1);
		map.put(MapperDict.status, EStatus.deleted);
		assertTrue(dao.updateFields(map));
		dao.delete(uid);
		assertNull(dao.get(uid));
	}

	/**
	 * Test Delete()
	 */
	@Test
	public void testDelete() {
		entity = new UserLevel();
		entity.setUid(TestConst.uid);
		entity.setUseruid((TestConst.uid2));
		entity.setMajor(EMajor.marketManage);
		entity.setLevelfrom(1);
		entity.setLevelto(2);
		entity.setType(EUser.student);
		entity.setCertificateuid("certificateuid123456796");
		entity.setArguments("something");
		entity.setDesc("nothings");
		entity.setChannel(ELevelChannel.fulltimeJudgement);
		entity.setStatus(EStatus.enable);
		entity.setUpdatetime(new DateTime());
		dao.insert(entity);
		assertNotNull(dao.get(TestConst.uid));
		dao.delete(TestConst.uid);
		assertNull(dao.get(TestConst.uid));
	}

}