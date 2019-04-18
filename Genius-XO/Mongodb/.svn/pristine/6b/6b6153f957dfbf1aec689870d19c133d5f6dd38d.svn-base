package com.genius.xo.mongodb.daoimpl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bson.BSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.core.base.utils.ReflectUtil;
import com.genius.xo.mongodb.DBFactory;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;

/**
 * MongoDB DAO 的公共方法类
 * @param <T>
 * @author architect.bian
 * @createtime 2015-1-28 下午3:16:26
 */
public abstract class AbstractMongoDao<T> {

	@Autowired
	private DBFactory dbFactory;
	private DB db;
	
	protected static final String DefaultCollectionID = "_id";
	protected static String DefaultMapperID = BaseMapperDict.uid;
	protected static String DefaultGetIDMethod = "getUid";

	// 存储一些特殊的非数据库字段的值
	protected static List<String> filterKeys = new ArrayList<>();
	static {
		filterKeys.add(BaseMapperDict.ascDesc);
		filterKeys.add(BaseMapperDict.orderBy);
		filterKeys.add(BaseMapperDict.asc);
		filterKeys.add(BaseMapperDict.pageSize);
		filterKeys.add(BaseMapperDict.startIndex);
	}
	
	protected DB getDB() {
		if (db == null) {
			db = dbFactory.getDB(this.getClass());
		}
		return db;
	}
	
	/**
	 * 目前操作的model对象的Class实例。
	 * @return Class<T>
	 * @author ShangJianguo
	 */
	@SuppressWarnings("unchecked")
	protected Class<T> getTClass(){
		Type type = this.getClass().getGenericSuperclass();
		Type[] paramsTypes = ((ParameterizedType)type).getActualTypeArguments();
		return (Class<T>)paramsTypes[0];
	}

	/**
	 * 获取对象的id
	 * @param t
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @author Architect.bian
	 * @createtime 2015-1-7 下午8:00:34
	 */
	protected String getID(T t) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		return (String)ReflectUtil.invoke(t, t.getClass().getMethod(DefaultGetIDMethod, new Class[]{}));
	}

	/**
	 * 返回mongodb对应的id类型，可string或ObjectID类型，默认是string类型
	 * @param id
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-9-25 下午3:25:33
	 */
	protected Object getObjectID(String id) {
		return id;
	}

	/**
	 * 获取排序规则，健为orderBy，值为BSONObject时直接使用，值作为唯一的排序健，否则获取健为ascDesc的值判断升降序
	 * @param map
	 * @return
	 */
	protected DBObject getOrderDBObject(Map<String, Object> map){
		BasicDBObject orderBy = new BasicDBObject();
		Object value = map.get(BaseMapperDict.orderBy);
		if (value instanceof BSONObject) {// 值为BJSONObject的实例或BasicDBObject时，直接putall，可实现复杂的排序
			orderBy.putAll((BSONObject)value);
		} else {
			String orderbykey = BaseMapperDict.createtime;
			if (value != null) {
				orderbykey = value.toString();
			}
			if (map.containsKey(BaseMapperDict.ascDesc)) {
				String ascdesc = map.get(BaseMapperDict.ascDesc).toString();
				if (ascdesc.trim().equals(BaseMapperDict.asc.trim())) {
					orderBy.put(orderbykey, 1);
				} else {
					orderBy.put(orderbykey, -1);
				}
			} else {
				orderBy.put(orderbykey, -1);
			}
		}
		return orderBy;
	}
	
	/**
	 * 过滤一些特殊的字段
	 * @param map
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-1-28 下午3:17:27
	 */
	protected static Map<String, Object> filterMap(Map<String, Object> map){
		Map<String, Object> result = new HashMap<>(map);
		Set<String> keySet = map.keySet();
		for (String key : keySet) {
			if (filterKeys.contains(key)) {
				result.remove(key);
			} else if (key.equals(DefaultMapperID)) {
				result.put(DefaultCollectionID, result.get(DefaultMapperID));
				result.remove(DefaultMapperID);
			}
		}
		return result;
	}
	
	/**
	 * 对传过来的串去掉前缀或者后缀，然后返回真实的field值
	 * @param origin 传过来的源字符串
	 * @param prefixOrSuffix 前缀或者后缀，如果为true则表示前缀，如果为false则表示后缀
	 * @param fix 前缀（或后缀）
	 * @return 去掉前缀或者后缀后的真实field值
	 * @author ShangJianguo
	 * @createtime 2015-1-28 下午3:17:42
	 */
	protected static String getRealField(String origin, boolean prefixOrSuffix, String fix){
		String key = "";
		if (prefixOrSuffix) {// 前缀
			key = origin.substring(origin.indexOf(fix) + fix.length());
		} else {
			key = origin.substring(0, origin.lastIndexOf(fix));
		}
		if (DefaultMapperID.equals(key)) {
			return DefaultCollectionID;
		}
		return key;
	}

}
