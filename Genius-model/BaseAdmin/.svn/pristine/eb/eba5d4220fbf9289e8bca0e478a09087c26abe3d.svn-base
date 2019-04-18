package com.genius.model.baseadmin.entity;

import static org.junit.Assert.*;

import org.junit.Test;

public class AdminTest {

	@Test
	public void testSet() {
		Admin admin = new Admin();
		Admin admin2 = new Admin();
		String email = "xx@yy.com";
		admin2.setEmail(email);
		admin.set(admin2);
		assertEquals(email, admin.getEmail());
	}

}
