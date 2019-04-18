package com.supergenius.server.common.helper;

import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * 
 * @author liubin
 * @date 2016-4-19 下午4:41:15
 */
public class RecommendHP {

	/**
	 * 返回相似文章的uid集合
	 * 
	 * @param host
	 * @param port
	 * @param dbbase
	 * @param collection
	 * @param uid
	 */
	@SuppressWarnings({ "resource", "unchecked" })
	public static List<String> getUids(String host, int port, String dbbase, String collection, String uid) {
		MongoClient mongoClient = new MongoClient(host, port);
		MongoDatabase database = mongoClient.getDatabase(dbbase);
		MongoCollection<Document> coll = database.getCollection(collection);
		Document document = coll.find().first();
		return (List<String>) document.get(uid);
	}

}
