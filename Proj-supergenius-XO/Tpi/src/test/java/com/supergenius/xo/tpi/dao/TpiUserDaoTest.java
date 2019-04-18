package com.supergenius.xo.tpi.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
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
import com.supergenius.xo.tpi.entity.ContactInfo;
import com.supergenius.xo.tpi.entity.InvestInfo;
import com.supergenius.xo.tpi.entity.MergerInfo;
import com.supergenius.xo.tpi.entity.RecommendInfo;
import com.supergenius.xo.tpi.entity.TpiUser;
import com.supergenius.xo.tpi.enums.EAssetScale;
import com.supergenius.xo.tpi.enums.EInvestType;
import com.supergenius.xo.tpi.enums.EMergeType;
import com.supergenius.xo.tpi.enums.ERecommendType;
import com.supergenius.xo.tpi.enums.EStaffScale;
import com.supergenius.xo.tpi.enums.ETpiUserType;
import com.supergenius.xo.tpi.enums.ETpiuserState;

/**
 * 测试类
 * 
 * @author ShangJianguo
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class TpiUserDaoTest {

	@Autowired
	TpiUserDao dao;

	TpiUser entity = null;

	/**
	 * 在执行所有单元测试方法之前插入user
	 */
	@Before
	public void before() {
		entity = new TpiUser();
		entity.setUid(TestConst.uid);
		entity.setUsersn("usersn");
		entity.setUsername(TestConst.name);
		entity.setUpdatetime(DateTime.now());
		
		boolean result = dao.insert(entity);
		assertTrue(result);
	}

	/**
	 * 在执行完所有单元测试之后删除user
	 */
	@After
	public void after() {
		entity = null;
		boolean result = dao.delete(TestConst.uid);
		assertTrue(result);
	}

	@Ignore
	@Test
	public void insertInitdata(){
		TpiUser user = new TpiUser();
		user.setUsersn("usersninvest");
		user.setUsername("投资有限公司");
		user.setType(ETpiUserType.investment);
		user.setEmail("henushang@qq.com");
		user.setCommittime(DateTime.now());
		user.setState(ETpiuserState.completeinfo);
		user.setIsindex(true);
		user.setIstop(false);
		user.setAvatarlittle("小头像");
		
		ContactInfo info = new ContactInfo();
		info.setCity("Beijing");
		info.setContactman("河牛商");
		info.setContactmanemail("henushang@qq.com");
		info.setContactmantel("12346578");
		info.setProvince("China");
		info.setEmail("123123@163.com");
		info.setTel("1717117");
		
		InvestInfo investInfo = new InvestInfo();
		investInfo.setIntroduce("introduce介绍");
		investInfo.setGroup("团队信息");
		investInfo.setScale(EAssetScale.level2);
		investInfo.setIndustry("行业");
		investInfo.setStandard("投资标准");
		investInfo.setLimit("投限制");
		investInfo.setRiskmeasure("分先措施");
		investInfo.setType(EInvestType.professional);
		
		user.setContactinfo(info);
		user.setInvestInfo(investInfo);
		dao.insert(user);
		
		
		RecommendInfo recInfo = new RecommendInfo();
		user.setType(ETpiUserType.recommend);
		recInfo.setType(ERecommendType.company);
		recInfo.setBusinesslicense("营业执照路径");
		recInfo.setLegalpersoncard("法人身份路径");
//		user.setRecommendInfo(recInfo);
//		System.out.println(dao.insert(user));
		
		user.setUid(GlobalUtil.getUUID());
		recInfo.setType(ERecommendType.government);
		recInfo.setSealpersoncard("盖有公章身份证明路径");
		recInfo.setPersoncard("个人身份中招聘路径");
//		user.setRecommendInfo(recInfo);
//		System.out.println(dao.insert(user));

		user.setType(ETpiUserType.merger);
		MergerInfo mergerInfo = new MergerInfo();
		mergerInfo.setIntroduce("introduce");
		mergerInfo.setScale(EAssetScale.level1);
		mergerInfo.setStaffnum(EStaffScale.level2);
		mergerInfo.setAddress("address");
		mergerInfo.setSuperiority("superiority");
		mergerInfo.setMergercase("mergercase");
		mergerInfo.setIndustry("industry");
		mergerInfo.setStandard("standard");
		mergerInfo.setLimit("limit");
		mergerInfo.setOther("other");
		mergerInfo.setType(EMergeType.international);
		user.setMergerInfo(mergerInfo);
//		dao.insert(user);
//		user.setUid(GlobalUtil.getUUID());
//		mergerInfo.setType(EMergeType.nationallarge);
//		user.setMergerInfo(mergerInfo);
//		dao.insert(user);
		
	}
	
	@Test
	public void testGet() {
		assertNotNull(dao.get(TestConst.uid));
	}

	@Test
	public void testGetOne() {
		Map<String, Object> map = new HashMap<>();
		map.put(BaseMapperDict.username, TestConst.username);
		assertNotNull(dao.getOne(map));
	}
	
	@Test
	public void testGetCount() {
		Map<String, Object> map = new HashMap<>();
		map.put(BaseMapperDict.username, TestConst.username);
		assertEquals(1, dao.getCount(map));
	}

	@Test
	public void testGetList() {
		Map<String, Object> map = new HashMap<>();
		map.put(BaseMapperDict.username, TestConst.username);
		List<TpiUser> list = dao.getList(map);
		assertEquals(1, list.size());
	}
	
	
	@Test
	public void testUpdate() {
		entity = dao.get(TestConst.uid);
		entity.setUsername(TestConst.name1);
		dao.update(entity);
		assertEquals(TestConst.name1, dao.get(TestConst.uid).getUsername());
	}

	@Test
	public void testUpdateFields() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.username, TestConst.name1);
		map.put(BaseMapperDict.uid, entity.getUid());
		dao.updateFields(map);
		assertEquals(TestConst.name1, dao.get(TestConst.uid).getUsername());
	}

	@Test
	public void testDeleteByMap() {
		String name = "testname";
		for (int i = 0; i < 3; i++) {
			TpiUser entity = new TpiUser();
			entity.setUsername(name);
			dao.insert(entity);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.username, name);
		assertTrue(dao.getList(map).size() == 3);
		dao.deleteByMap(map);
		assertTrue(dao.getList(map).size() == 0);
	}
	
}
