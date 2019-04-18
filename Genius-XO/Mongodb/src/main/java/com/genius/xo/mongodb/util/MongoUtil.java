package com.genius.xo.mongodb.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bson.types.BasicBSONList;
import org.bson.types.ObjectId;

import com.genius.core.base.constant.SysConst;
import com.genius.xo.mongodb.DBConnection;
import com.genius.xo.mongodb.DBFactory;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

/**
 * 从备份文件中恢复备份的数据，此备份文件的数据格式必须是使用mongoexport命令导出的文件不变，并且文件名为 .json 后缀。 <br>
 * 此操作会删除掉原有集合中的所有数据，以防止出现 _id 冲突的情况发生。 <br>
 * 另外，文件名表示此文件内所对应的collection，即：文件名就是集合名。
 * 
 * @author ShangJianguo
 */
public class MongoUtil {

	/**
	 * 将map转成DBObject类型
	 * 
	 * @param map
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-1-29 下午4:34:37
	 */
	@SuppressWarnings("unchecked")
	public static DBObject toDBObject(Map<String, Object> map) {
		BasicDBObjectBuilder builder = BasicDBObjectBuilder.start();
		for (String key : map.keySet()) {
			Object val = map.get(key);
			if (val instanceof Map) {
				builder.add(key, toDBObject((Map<String, Object>) val));
			} else if (val instanceof List) {
				builder.add(key, toDBList((List<?>) val));
			} else {
				builder.add(key, val);
			}
		}
		return builder.get();
	}

	/**
	 * 将list转成BasicDBList
	 * 
	 * @param list
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-1-29 下午4:51:35
	 */
	@SuppressWarnings("unchecked")
	public static BasicDBList toDBList(List<?> list) {
		BasicDBList dbList = new BasicDBList();
		for (Object item : list) {
			if (item instanceof Map) {
				dbList.add(toDBObject((Map<String, Object>) item));
			} else if (item instanceof List) {
				dbList.add(toDBList((List<?>) item));
			} else {
				dbList.add(item);
			}
		}
		return dbList;
	}

	/**
	 * 将dbobject直接转成map
	 * 
	 * @param dbObject
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-1-20 下午6:58:07
	 */
	public static Map<String, Object> toMap(DBObject dbObj) {
		@SuppressWarnings("unchecked")
		Map<String, Object> map = dbObj.toMap();
		for (String key : map.keySet()) {
			Object val = map.get(key);
			if (val instanceof BasicDBList) {
				map.put(key, toList((BasicBSONList) val));
			} else if (val instanceof DBObject) {
				map.put(key, toMap((DBObject) val));
			} else {
				// map.put(key, val); //不变
			}
		}
		return map;
	}

	/**
	 * 将BasicBSONList循环toMap后再次放到list中
	 * 
	 * @param jsonList
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-1-20 下午6:59:48
	 */
	public static List<Object> toList(BasicBSONList jsonList) {
		List<Object> list = new ArrayList<>();
		for (Object item : jsonList) {
			if (item instanceof DBObject) {
				list.add(toMap((DBObject) item));
			} else {
				list.add(item);
			}
		}
		return list;
	}

	/**
	 * 恢复一个路径（datapath）下的所有数据文件
	 * 
	 * @param dbConn
	 *            数据库连接
	 * @param dirPath
	 *            数据文件目录
	 * @author Architect.bian
	 * @createtime 2015-3-31 下午3:04:25
	 */
	public static void restorePath(DBConnection dbConn, String dirPath) {
		File dir = new File(dirPath);
		restorePath(dbConn, dir);
	}

	/**
	 * 恢复一个路径（datapath）下的所有数据文件
	 * 
	 * @param db
	 *            {@link com.mongodb.DB}的实例
	 * @param datapath
	 *            数据文件所在路径
	 * @author ShangJianguo
	 */
	public static void restorePath(DBConnection dbConn, File dir) {
		DB db = DBFactory.createDB(dbConn);
		if (!dir.exists()) {
			System.err.println(dir + " not exists");
			return;
		}
		FileFilter filter = new FileFilter() {
			@Override
			public boolean accept(File file) {
				if (file.isFile() && file.getName().endsWith(".json")) {
					return true;
				} else {
					return false;
				}
			}
		};
		File[] files = dir.listFiles(filter);
		System.out.println("there are total " + files.length + " files. \r\n");
		for (File file : files) {
			restoreSingleFile(db, file);
		}
	}

	/**
	 * 恢复单个文件内的数据
	 * 
	 * @param db
	 *            {@link com.mongodb.DB}的实例
	 * @param file
	 *            数据文件File对象
	 * @author ShangJianguo
	 */
	public static void restoreSingleFile(DB db, File file) {
		String filename = file.getName();
		System.out.println("start retoring file: " + file.getName());
		String collName = filename.substring(0, filename.indexOf("."));
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.err.println("file " + filename + "not exist");
			return;
		}
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(fis, SysConst.UTF8));
		} catch (UnsupportedEncodingException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		List<String> jsonList = new ArrayList<>();
		String dataline = "";
		try {
			while ((dataline = br.readLine()) != null) {
				if (!"".equals(dataline.trim())) {
					jsonList.add(dataline);
				}
			}
		} catch (IOException e) {
			System.err.println("read file " + filename + " err");
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
			}
			try {
				fis.close();
			} catch (IOException e) {
			}
		}
		List<DBObject> dbObjList = new ArrayList<>();
		for (String strJson : jsonList) {
			DBObject obj = (DBObject) JSON.parse(strJson);
			dbObjList.add(obj);
		}
		DBCollection coll = db.getCollection(collName);
		coll.remove(new BasicDBObject());
		if (dbObjList.size() != 0) {
			coll.insert(dbObjList);
		} else {
			System.out.println("cancel retore, the file: " + file.getName() + " is empty");
		}
		System.out.println("retore file: " + file.getName() + " success \r\n");
	}

	/**
	 * 获取一个ObjectId,返回String类型
	 * 
	 * @return
	 * @author YuYingJie
	 */
	public static String getObjectIdStr() {
		return getObjectId().toString();
	}

	/**
	 * 获取一个ObjectId
	 * 
	 * @return
	 * @author YuYingJie
	 */
	public static ObjectId getObjectId() {
		return ObjectId.get();
	}

}
