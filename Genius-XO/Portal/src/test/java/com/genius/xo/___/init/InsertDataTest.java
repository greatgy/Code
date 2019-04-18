package com.genius.xo.___.init;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.sql.Connection;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

/**
 * @author architect.bian
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class InsertDataTest {

	@Autowired
	SqlSessionFactory sqlSessionFactory;

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void insertData() throws IOException {
		logger.info("-----------invoking method: insertData");
		File dir = new File(ClassLoader.getSystemResource("DB/InitData").toString().replace("file:/", ""));
		FileFilter filter = new FileFilter() {
			@Override
			public boolean accept(File f) {
				String p = f.getPath();
				return p.indexOf(".sql") > 0;
			}
		};
		String dbsql = "";
		File[] files = dir.listFiles(filter);
		for (File f : files) {
			dbsql += Files.toString(f, Charsets.UTF_8);
		}
		Connection conn = null;
		try {
			dbsql = dbsql.replaceAll("/\\*[\\s\\S]*?\\*/", "");
			String[] strs = StringUtils.splitByWholeSeparator(dbsql.replace("\r", "\n"), ";\n");
			conn = sqlSessionFactory.openSession().getConnection();
			for (String sql : strs) {
				sql = sql.replaceAll("^[^\\w]*", "").trim();// 去除前面的空白及特殊字符
				if (!StringUtils.isEmpty(sql)) {
					QueryRunner runner = new QueryRunner();
					runner.update(conn, sql);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		} finally {
			DbUtils.closeQuietly(conn);
		}
	}

}
