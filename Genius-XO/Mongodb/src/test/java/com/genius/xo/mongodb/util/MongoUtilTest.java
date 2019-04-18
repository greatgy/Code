package com.genius.xo.mongodb.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * MongoUtil的单元测试
 * @author YuYingJie
 */
public class MongoUtilTest {

	@Test
	public void testGetObjectIdStr() {
		assertEquals(24, MongoUtil.getObjectIdStr().length());
	}

	@Test
	public void testGetObjectId() {
		assertNotNull(MongoUtil.getObjectId());
	}

}
