package com.genius.xo.mongodb;


import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.xo.mongodb.util.MongoUtil;

/**
 * 测试恢复mongodb导出文件方法
 * 
 * @author ShangJianguo
 * @modifier architect.bian
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class InsertDataTest {

	@Autowired
	@Qualifier("dbConn2")
	private DBConnection dbConn;
	
	@Test
	public void testInsert(){
		File dir = new File(ClassLoader.getSystemResource("DB/Test").toString().replace("file:/", ""));
		MongoUtil.restorePath(dbConn, dir);
	}
}
