package com.genius.core.serial.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.genius.core.base.utils.StrUtil;
import com.genius.core.serial.engine.KryoSerialEngine;
import com.genius.core.serial.engine.SerialEngine;
import com.genius.core.serial.mock.testentity.User;

/**
 * @author Architect.bian
 * 
 */
public class KryoSerialEngineTest {

	private static String filepath_user = StrUtil.trim(ClassLoader.getSystemResource("serials/user").getFile(), "/");

	/**
	 * Test method for
	 * {@link com.wbcom.core.serial.KryoSerialEngine#Serialize(java.lang.Object, java.lang.String, boolean)}
	 * .
	 * 
	 * @throws IOException
	 */
	@Test
	public void testSerialize() throws IOException {
		User user = new User();
		List<String> uids = Arrays.asList("a", "b", "c", "d", "e", "f", "g");
		user.getUid();
		user.setChilduids(uids);
		List<String> friends = new ArrayList<>();
		friends.add("111111111111");
		friends.add("2222222222222");
		user.setFriends(friends);
		SerialEngine engine = new KryoSerialEngine();
		engine.serialize(user, filepath_user, true);
		User user2 = (User) engine.deserialize(filepath_user, true);
		assertEquals(user.getUid(), user2.getUid());
		assertNull(user2.getChilduids());
		assertEquals(friends, user2.getFriends());
		System.out.println(user2.getFriends());
	}

}
