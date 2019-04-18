package com.genius.xo.mongodb;


import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mongojack.JacksonDBCollection;
import org.mongojack.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.genius.xo.mongodb.mock.model.User;
import com.genius.xo.mongodb.mock.testconstants.TestConst;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClientURI;

/**
 * 测试类
 * 
 * @author ShangJianguo
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
@Ignore
public class DBTest {

	@Autowired
	private DB db;
	
	private JacksonDBCollection<User, String> coll = null;
	private static ObjectMapper mapper = new ObjectMapper();
	
	static {
		mapper.registerModule(new JodaModule());
	}
	
	@Before
	public void before() throws UnknownHostException{
		DBCollection dbcoll = db.getCollection("testcollection");
		coll = JacksonDBCollection.wrap(dbcoll, User.class, String.class, mapper);
	}
	
	@Test
	public void testMongoClientURI() throws URISyntaxException, MalformedURLException{
		String uri = "mongodb://localhost:27017/test";
		MongoClientURI client = new MongoClientURI(uri);
		assertEquals(uri, client.getURI());
		assertEquals("test", client.getDatabase());
		assertTrue(client.getHosts().size() == 1);
		assertTrue(client.getHosts().contains("localhost:27017"));
		uri = "mongodb://localhost:27017,localhost:27018/test";
		client = new MongoClientURI(uri);
		assertEquals(uri, client.getURI());
		assertEquals("test", client.getDatabase());
		assertTrue(client.getHosts().size() == 2);
		assertTrue(client.getHosts().contains("localhost:27017"));
		assertTrue(client.getHosts().contains("localhost:27018"));
	}
	
	@Test
	public void testMongoDB() {
		System.out.println(db.getCollectionNames());
		System.out.println(db.getStats());
	}
	
	@Test
	public void testInsert(){
		User user = new User();
		user.setName(TestConst.name);
		user.setAge(TestConst.age);
		user.setId(TestConst.uid1);
		user.setBirthday(DateTime.now());
		WriteResult<User, String> insertresult = coll.insert(user);
		Assert.assertEquals(user.getAge(), insertresult.getSavedObject().getAge());
		coll.removeById(TestConst.uid1);
		assertNull(coll.findOneById(TestConst.uid1));
	}
}
