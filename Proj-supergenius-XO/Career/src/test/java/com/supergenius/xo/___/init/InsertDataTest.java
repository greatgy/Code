package com.supergenius.xo.___.init;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.core.base.constant.SysConst;
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
	SqlSessionFactory sqlSessionFactoryCareerDB;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final String strDBName = "GCareerDB";
	
	@Test
	public void insertGCareerDBData() throws IOException {
		System.out.println("-----------invoking method: insertGCareerDBData");
		InputStream dbStream = ClassLoader.getSystemResourceAsStream(String.format("DB/InitData/%s.sql", strDBName));
		String dbsql = IOUtils.toString(dbStream, SysConst.UTF8);
		File filealtdb = new File(ClassLoader.getSystemResource(String.format("DB/InitData/%s_Alt-1.0.0.0.sql", strDBName)).toString());
		File dir = new File(filealtdb.getParent().replace("file:\\", ""));
		FileFilter filter = new FileFilter() {
			@Override
			public boolean accept(File f) {
				String p = f.getPath();
				return p.indexOf(String.format("%s_Alt", strDBName)) > 0;// 匹配需要压缩的文件夹
			}
		};
		File[] files = dir.listFiles(filter);
		for (File f : files) {
			dbsql += Files.toString(f, Charsets.UTF_8);
		}
		Connection conn = null;
		try {
			dbsql = dbsql.replaceAll("/\\*[\\s\\S]*?\\*/", "");
			String[] strs = StringUtils.splitByWholeSeparator(dbsql.replace("\r", "\n"), ";\n");
			conn = sqlSessionFactoryCareerDB.openSession().getConnection();
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

	@Test
	public void insertGCareerDBTestData() throws IOException {
		System.out.println("-----------invoking method: insertGCareerDBTestData");
		InputStream dbStream = ClassLoader.getSystemResourceAsStream(String.format("DB/InitData/%s_Test.sql", strDBName));
		String dbsql = IOUtils.toString(dbStream, SysConst.UTF8);
//		File filealtdb = new File(ClassLoader.getSystemResource(String.format("DB/InitData/%s_Test_Alt-1.0.0.0.sql", strDBName)).toString());
//		File dir = new File(filealtdb.getParent().replace("file:\\", ""));
//		FileFilter filter = new FileFilter() {
//			@Override
//			public boolean accept(File f) {
//				String p = f.getPath();
//				return p.indexOf(String.format("%s_Test_Alt", strDBName)) > 0;// 匹配需要压缩的文件夹
//			}
//		};
//		File[] files = dir.listFiles(filter);
//		for (File f : files) {
//			dbsql += Files.toString(f, Charsets.UTF_8);
//		}
		Connection conn = null;
		try {
			dbsql = dbsql.replaceAll("/\\*[\\s\\S]*?\\*/", "");
			String[] strs = StringUtils.splitByWholeSeparator(dbsql.replace("\r", "\n"), ";\n");
			conn = sqlSessionFactoryCareerDB.openSession().getConnection();
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
