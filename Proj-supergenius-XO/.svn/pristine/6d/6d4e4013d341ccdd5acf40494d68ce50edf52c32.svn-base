package com.supergenius.xo.___.init;

import java.io.File;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.xo.mongodb.DBConnection;
import com.genius.xo.mongodb.util.MongoUtil;

/**
 * 根据数据库备份文件恢复数据库数据
 * @author LiuXiaoke
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
@Ignore
public class InsertOfficialDataTest {
	
	@Autowired
	private DBConnection dbconn;
	
	/**
	 * 将测试数据使用mongoexport命令导出后，保存为*.json格式,*为表名
	 * 再将据库文件 *.json 放到src/test/resources/DB下
	 * 该操作先清空表再导入，注意数据安全，不用的时候@Ignore
	 * @author LiuXiaoke
	 */
	@Test
	public void testInsertOfficialData() {
		File dir = new File(ClassLoader.getSystemResource("DB/Official").toString().replace("file:/", ""));
		String datapath = dir.getAbsolutePath();
		MongoUtil.restorePath(dbconn, datapath);
	}
	
}
