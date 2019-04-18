package com.supergenius.xo.user.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.model.base.entity.Pager;
import com.supergenius.xo.mock.testconstants.TestConst;
import com.supergenius.xo.user.entity.Order;

/** 
 *
 * @author guanshiqian
 * @date 2016-4-18 下午1:34:43 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class OrderSOTest {

	@Autowired
	private OrderSO so;
	
	/**
	 * Test testGetList()
	 */
	@Test
	public void testGetList() {
		Pager pager = Pager.getNewInstance(0, 10);
		List<Order> list = so.getList(TestConst.uid, pager);
		System.out.println(list);
	}

}
