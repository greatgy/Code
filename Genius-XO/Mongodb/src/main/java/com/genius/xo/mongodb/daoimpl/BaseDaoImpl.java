package com.genius.xo.mongodb.daoimpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.mongojack.DBCursor;
import org.mongojack.DBQuery;
import org.mongojack.DBQuery.Query;
import org.mongojack.DBUpdate.Builder;
import org.mongojack.JacksonDBCollection;
import org.mongojack.MongoCollection;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.genius.core.base.constant.BaseMapperDict;
import com.genius.core.base.utils.ReflectUtil;
import com.genius.xo.base.dao.BaseDao;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoException;

/**
 * MongoDB DAO 的基础实现类
 * @author ShangJianguo
 * @modifier architect.bian
 */
public abstract class BaseDaoImpl<T> extends AbstractMongoDao<T> implements BaseDao<T>{
	
	private JacksonDBCollection<T, String> coll = null;
	private static ObjectMapper mapper = new ObjectMapper();
	static {
		mapper.registerModule(new JodaModule());
	}

	/**
	 * 获取 JacksonDBCollection 实例
	 * @return
	 * @author ShangJianguo
	 */
	protected JacksonDBCollection<T, String> getColl() {
		if (coll == null) {
			Class<T> clazz = getTClass();
			MongoCollection mongoCollection = clazz.getAnnotation(MongoCollection.class);
			String collName = null;
			if (mongoCollection == null) {
				collName = clazz.getSimpleName().toLowerCase();
			} else {
				collName = mongoCollection.name();
			}
			DBCollection dbcoll = getDB().getCollection(collName);
			coll = JacksonDBCollection.wrap(dbcoll, getTClass(), String.class, mapper);
		}
		return coll;
	}
	
	/* (non-Javadoc)
	 * @see com.genius.xo.mongodb.dao.BaseDao#get(java.lang.String)
	 * @author: ShangJianguo
	 * 2014-12-22 下午4:56:58
	 */
	@Override
	public T get(String id) {
		return getColl().findOneById(id);
	}

	/* (non-Javadoc)
	 * @see com.genius.xo.mongodb.dao.BaseDao#getOne(java.util.Map)
	 * @author: ShangJianguo
	 * 2014-12-22 下午4:56:58
	 */
	@Override
	public T getOne(Map<String, Object> map) {
		Query query = getQuery(map);
		return getColl().findOne(query);
	}

	/* (non-Javadoc)
	 * @see com.genius.xo.mongodb.dao.BaseDao#getCount(java.util.Map)
	 * @author: ShangJianguo
	 * 2014-12-22 下午4:56:58
	 */
	@Override
	public int getCount(Map<String, Object> map) {
		Query query = getQuery(map);
		return (int)getColl().getCount(query);
	}

	/* (non-Javadoc)
	 * @see com.genius.xo.mongodb.dao.BaseDao#getList(java.util.Map)
	 * @author: ShangJianguo
	 * 2014-12-22 下午4:56:58
	 */
	@Override
	public List<T> getList(Map<String, Object> map) {
		int skip = 0;
		if (map.get(BaseMapperDict.startIndex) != null) {
			skip = Integer.parseInt(map.get(BaseMapperDict.startIndex).toString());
		}
		int limit = 10;
		if (map.get(BaseMapperDict.pageSize) != null) {
			limit = Integer.parseInt(map.get(BaseMapperDict.pageSize).toString());
		}
//		Map<String, Object> map2 = new HashMap<String, Object>();
//		map2.putAll(map);
		DBObject orderby = getOrderDBObject(map);
		DBCursor<T> cursor = getColl().find(getQuery(map)).skip(skip).limit(limit).sort(orderby);
		List<T> list = new ArrayList<>();
		if (cursor != null) {
			list = cursor.toArray();
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.genius.xo.mongodb.dao.BaseDao#insert(java.lang.Object)
	 * @author: ShangJianguo
	 * 2014-12-22 下午4:56:58
	 */
	@Override
	public boolean insert(T t) {
		try {
			getColl().insert(t);
			return true;
		} catch (MongoException e) {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.genius.xo.mongodb.dao.BaseDao#insertList(java.util.List)
	 * @author: ShangJianguo
	 * 2014-12-22 下午4:56:58
	 */
	@Override
	public boolean insertList(List<T> list) {
		try {
			getColl().insert(list);
			return true;
		} catch (MongoException e) {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.genius.xo.mongodb.dao.BaseDao#update(java.lang.Object)
	 * @author: ShangJianguo
	 * 2014-12-22 下午4:56:58
	 */
	@Override
	public boolean update(T t) {
		try {
			getColl().updateById(getID(t), t);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.genius.xo.mongodb.dao.BaseDao#updateFields(java.util.Map)
	 * @author: ShangJianguo
	 * 2014-12-22 下午4:56:58
	 */
	@Override
	public boolean updateFields(Map<String, Object> map) {
		Map<String, Object> m = filterMap(map);
		String id = (String) m.get(DefaultCollectionID);
		m.remove(DefaultCollectionID);
		Builder update = new Builder();
		for (String key : m.keySet()) {
			update.set(key, m.get(key));
		}
		try {
			getColl().updateById(id, update);
			return true;
		} catch (MongoException e) {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.genius.xo.mongodb.dao.BaseDao#increase(java.util.Map)
	 * @author: ShangJianguo
	 * 2014-12-22 下午4:56:58
	 */
	@Override
	public boolean increase(Map<String, Object> map) {
		Map<String, Object> m = filterMap(map);
		String id = (String) m.get(DefaultCollectionID);
		m.remove(DefaultCollectionID);
		Builder update = new Builder();
		for (String key : m.keySet()) {
			update.inc(key, Integer.valueOf(m.get(key).toString()));
		}
		getColl().updateById(id, update);
		return true;
	}

	/* (non-Javadoc)
	 * @see com.genius.xo.mongodb.dao.BaseDao#delete(java.lang.String)
	 * @author: ShangJianguo
	 * 2014-12-22 下午4:56:58
	 */
	@Override
	public boolean delete(String id) {
		getColl().removeById(id);
		return true;
	}

	/* (non-Javadoc)
	 * @see com.genius.xo.mongodb.dao.BaseDao#deleteByMap(java.util.Map)
	 * @author: ShangJianguo
	 * 2014-12-22 下午4:56:58
	 */
	@Override
	public boolean deleteByMap(Map<String, Object> map) {
		getColl().remove(getQuery(map));
		return true;
	}

	/**
	 * 根据传来的map组装成可以在mongodb中查询的条件，具体的前后缀请参考 {@link com.genius.xo.mongodb.dao.constants.MongoMapperDic}
	 * @param map
	 * @return
	 * @author ShangJianguo
	 */
	private Query getQuery(Map<String, Object> map){
		Query query = DBQuery.empty();
		map = filterMap(map);
		Iterator<String> iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			if (key.endsWith(BaseMapperDict.suffix_greaterOrEqual_key)) {
				query.greaterThanEquals(getRealField(key, false, BaseMapperDict.suffix_greaterOrEqual_key), map.get(key));
				continue;
			}
			if (key.endsWith(BaseMapperDict.suffix_greater_key)) {
				query.greaterThan(getRealField(key, false, BaseMapperDict.suffix_greater_key), map.get(key));
				continue;
			}
			if (key.endsWith(BaseMapperDict.suffix_lessOrEqual_key)) {
				query.lessThanEquals(getRealField(key, false, BaseMapperDict.suffix_lessOrEqual_key), map.get(key));
				continue;
			}
			if (key.endsWith(BaseMapperDict.suffix_less_key)) {
				query.lessThan(getRealField(key, false, BaseMapperDict.suffix_less_key), map.get(key));
				continue;
			}
			if (key.endsWith(BaseMapperDict.suffix_like_key)) {
				String value = (String) map.get(key);
				query.regex(getRealField(key, false, BaseMapperDict.suffix_like_key), Pattern.compile(value));
				continue;
			}
			if (key.endsWith(BaseMapperDict.suffix_in_key)) {
				String value = (String) map.get(key);
				query.in(getRealField(key, false, BaseMapperDict.suffix_in_key), value);
				continue;
			}
			if (key.endsWith(BaseMapperDict.suffix_inall_key)) {
				String value = (String) map.get(key);
				query.all(getRealField(key, false, BaseMapperDict.suffix_inall_key), value);
				continue;
			}
			if (key.endsWith(BaseMapperDict.suffix_nin_key)) {
				String value = (String) map.get(key);
				query.notIn(getRealField(key, false, BaseMapperDict.suffix_nin_key), value);
				continue;
			}
			if (key.endsWith(BaseMapperDict.suffix_exist_key)) {
				query.exists(getRealField(key, false, BaseMapperDict.suffix_exist_key));
				continue;
			}
			if (key.endsWith(BaseMapperDict.suffix_nexist_key)) {
				query.notExists(getRealField(key, false, BaseMapperDict.suffix_nexist_key));
				continue;
			}
			if (key.endsWith(BaseMapperDict.suffix_mod_key)) {
				int mod = 0;
				int value = 0;
				Object val = map.get(key);
				if (val.getClass().isArray()) {
					int[] arr = (int[])val;
					if (arr.length == 2) {
						mod = arr[0];
						value = arr[1];
					}
				} else if ( ReflectUtil.isList(val)) {
					@SuppressWarnings("unchecked")
					List<Integer> list = (List<Integer>) val;
					if (list.size() == 2) {
						mod = list.get(0);
						value = list.get(1);
					}
				}
				if (mod != 0 && value != 0) {
					query.mod(getRealField(key, false, BaseMapperDict.suffix_mod_key), mod, value);
				}
				continue;
			}
			if (key.endsWith(BaseMapperDict.suffix_arrsize_key)) {
				int value = (int) map.get(key);
				query.size(getRealField(key, false, BaseMapperDict.suffix_arrsize_key), value);
				continue;
			}
			if (key.endsWith(BaseMapperDict.suffix_no_key)) {
				query.notEquals(getRealField(key, false, BaseMapperDict.suffix_arrsize_key), map.get(key));
				continue;
			}
			if (key.equals(BaseMapperDict.ids)) {
				query.in(DefaultCollectionID, map.get(BaseMapperDict.ids));
				continue;
			}
			query.is(key, map.get(key));
		}
		return query;
	}

}
