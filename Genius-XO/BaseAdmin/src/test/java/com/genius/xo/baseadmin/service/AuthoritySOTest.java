package com.genius.xo.baseadmin.service;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.core.base.constant.BaseMapperDict;

/**
 * @author liushaomin
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class AuthoritySOTest {

	@Autowired
	private AuthoritySO so;
	
	/**
	 * Test method for {@link com.mygenius.web.admin.official.serviceimpl.AuthoritySOImpl#getAuthorities(java.util.Map)}.
	 */
	@Test
	public void testGetAuthorities() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.urlregx, "/geniusadmin/index");
		List<String> list = so.getAuthorities(map);
		assertTrue(list.size() > 0);
	}

}
