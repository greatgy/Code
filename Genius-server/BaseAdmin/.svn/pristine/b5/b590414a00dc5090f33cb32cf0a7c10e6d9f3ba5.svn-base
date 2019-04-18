package com.genius.server.baseadmin.helper;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.core.base.constant.BaseViewKeyDict;
import com.genius.model.baseadmin.entity.Admin;
import com.genius.server.baseadmin.helper.AdminHP;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class AdminHPTest {

	@Test
	public void testQuery() {
		Map<String, String> args = new HashMap<String, String>();
		String adminid = "admin";
		args.put(BaseMapperDict.adminid, adminid);
		@SuppressWarnings("unchecked")
		List<Admin> list = (List<Admin>) AdminHP.query(args).get(BaseViewKeyDict.rows);
		assertEquals(adminid, list.get(0).getAdminid());
		
	}

}
