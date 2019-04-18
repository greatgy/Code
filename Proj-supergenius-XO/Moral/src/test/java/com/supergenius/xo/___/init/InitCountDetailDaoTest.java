package com.supergenius.xo.___.init;

import org.joda.time.DateTime;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.model.base.enums.EStatus;
import com.supergenius.moral.moral.dao.CountDetailDao;
import com.supergenius.xo.mock.testconstants.TestConst;
import com.supergenius.xo.moral.entity.CountDetail;
import com.supergenius.xo.moral.enums.ECountDetail;

/**
 * 添加测试数据
 * 
 * @author LiJiacheng
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class InitCountDetailDaoTest {

	@Autowired
	CountDetailDao dao;

	@Ignore
	@Test
	public void InsertInitData() {
		CountDetail countDetail = new CountDetail();
		countDetail.setFromuseruid(TestConst.uid);
		countDetail.setStatus(EStatus.enable);
		countDetail.setArticleuid(TestConst.uid1);
		countDetail.setType(ECountDetail.collect);
		countDetail.setCreatetime(DateTime.now());
		dao.insert(countDetail);
		CountDetail countDetail1 = new CountDetail();
		countDetail1.setFromuseruid(TestConst.uid);
		countDetail1.setStatus(EStatus.enable);
		countDetail1.setArticleuid(TestConst.uid2);
		countDetail1.setType(ECountDetail.praise);
		countDetail1.setCreatetime(DateTime.now());
		dao.insert(countDetail1);
		CountDetail countDetail2 = new CountDetail();
		countDetail2.setFromuseruid(TestConst.uid1);
		countDetail2.setStatus(EStatus.enable);
		countDetail2.setArticleuid(TestConst.uid2);
		countDetail2.setType(ECountDetail.praise);
		countDetail2.setCreatetime(DateTime.now());
		dao.insert(countDetail2);
	}

}
