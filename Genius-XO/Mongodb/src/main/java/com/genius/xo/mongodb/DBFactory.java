package com.genius.xo.mongodb;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.genius.core.base.utils.StrUtil;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions.Builder;
import com.mongodb.MongoClientURI;

/**
 * mongodb工厂，根据使用db的类的package匹配一个dbconnection并创建DB
 * 
 * @author architect.bian
 * @createtime 2015-1-7 下午4:07:40
 */
@Component
public class DBFactory {

	@Autowired
	private List<DBConnection> dbConnections;
	
	//所有的db缓存
	private static Map<String, DB> dbs = new HashMap<String, DB>();

	/**
	 * 根据使用方的类匹配一个最正确的conn，然后创建一个db，在dbconnection中指定basepackage，若basepackage为空则是默认的conn
	 * @param clz
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-3-19 下午8:03:04
	 */
	public DB getDB(Class<?> clz) {
		if (dbConnections == null || dbConnections.size() == 0) {
//			throw new Exception("cannot find any dbConnections");
			System.err.println("cannot find any dbConnections");
			return null;
		} else if (dbConnections.size() == 1) {// TODO 判断basepackage为空
			return createDB(dbConnections.get(0));
		} else {
			String packName = clz.getPackage().getName();
			DB defaultDB = null;
			for (DBConnection conn : dbConnections) {
				if (StrUtil.isNotEmpty(conn.getBasePackage())) {
					if (packName.startsWith(conn.getBasePackage())) {// 用第一个匹配的dbconn创建一个DB
						return createDB(conn);
					}
				} else {
					defaultDB = createDB(conn);
				}
			}
			if (defaultDB == null) {// 没有匹配的也没有默认的(默认:basepackage为空的)
				System.err.println("cannot find any dbConnections");
			}
			return defaultDB;
		}
	}

	/**
	 * 获取DB实例
	 * @return
	 * @throws UnknownHostException
	 * @author Architect.bian
	 * @createtime 2015-1-7 下午4:24:21
	 */
	@SuppressWarnings({ "resource", "deprecation" })
	public static synchronized DB createDB(DBConnection conn) {
		String basePackage = conn.getBasePackage();
		if (dbs.get(basePackage) == null) {
			MongoClientURI uri = new MongoClientURI(conn.toUri(), getBuilder(conn));
			MongoClient mongoClient = new MongoClient(uri);
			dbs.put(basePackage, mongoClient.getDB(uri.getDatabase()));
		}
		return dbs.get(basePackage);
	}

	/**
	 * 通过dbconn获取builder
	 * @param dbconn
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-1-7 下午6:35:55
	 */
	private static Builder getBuilder(DBConnection dbconn) {
		return new Builder();
	}

}
