package com.genius.core.serial.utils;

import static org.junit.Assert.assertEquals;

import org.joda.time.DateTime;
import org.junit.Test;

import com.genius.core.base.utils.StrUtil;
import com.genius.core.serial.engine.SpringSerialEngine;
import com.genius.core.serial.mock.testentity.User;
import com.genius.core.serial.mock.testenums.EStatus;

/**
 * @author Architect.bian
 * 
 */
public class SerialUtilTest {

	private static String fileUserJsonPath = StrUtil.trim(ClassLoader.getSystemResource("files/user.json").getFile(), "/");
	
	/**
	 * 10000 times,serial with kryo bytes.length:107,duration:494
	 */
	@Test
	public void serialObjectTest() {
		User obj = new User();
		String uid = obj.getUid();
		User expected = null;
		byte[] bytes = null;
		long timestart = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			bytes = SerialUtil.serialize(obj);
			expected = (User) SerialUtil.deserialize(bytes);
		}
		long timeend = System.currentTimeMillis();
		long duration = timeend - timestart;
		System.out.println("serial with kryo bytes.length:" + bytes.length + ",duration:" + duration);
		assertEquals(uid, expected.getUid());
		assertEquals(obj, expected);
	}

	/**
	 * 10000 times,serial with spring bytes.length:961,duration:1091
	 * 
	 * @throws Exception
	 */
	@Test
	public void springSerialObjectTest() throws Exception {
		User obj = new User();
		String uid = obj.getUid();
		SpringSerialEngine engine = new SpringSerialEngine();
		User expected = null;
		byte[] bytes = null;
		long timestart = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			bytes = engine.serialize(obj, false);
			expected = (User) engine.deserialize(bytes, false);
		}
		long timeend = System.currentTimeMillis();
		long duration = timeend - timestart;
		System.out.println("serial with spring bytes.length:" + bytes.length + ",duration:" + duration);
		assertEquals(uid, expected.getUid());
		assertEquals(obj, expected);
	}

	@Test
	public void serializeToJsonTest(){
		String strategy = "forsave";
		User user = new User();
		String uid = user.getUid();
		user.setStatus(EStatus.deleted);
		user.setCreatetime(new DateTime());
		SerialUtil.serializeToJson(user, fileUserJsonPath, strategy);
		User userFromJson = SerialUtil.deserializeFromJson(fileUserJsonPath, User.class, strategy);
		assertEquals(user.getStatus(), userFromJson.getStatus());
		assertEquals(user.getCreatetime(), userFromJson.getCreatetime());
		assertEquals(uid, userFromJson.getUid());
		assertEquals(user, userFromJson);
	}
}
