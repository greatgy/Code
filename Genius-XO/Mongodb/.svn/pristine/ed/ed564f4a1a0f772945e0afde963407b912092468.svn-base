package com.genius.xo.mongodb.daoimpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.core.base.utils.MapsUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.genius.xo.mongodb.Mongo;
import com.genius.xo.mongodb.MongoDict;
import com.genius.xo.mongodb.dao.BaseMongoDao;
import com.genius.xo.mongodb.util.MongoUtil;
import com.google.common.collect.Lists;
import com.mongodb.AggregationOptions;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.Cursor;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.mongodb.QueryBuilder;

/**
 * MongoDB DAO 的基础实现类,基于MapsUtil来实现 对象或map中的key或属性所有的uid或_id都会转成_id 对应的实体类的属性类型不可为short，要与mongodb中相匹配
 * 
 * @param <T>
 * @author architect.bian
 * @createtime 2015-1-8 下午4:49:29
 * TODO 后期加入日志记录
 */
public abstract class BaseMongoDaoImpl<T> extends AbstractMongoDao<T> implements BaseMongoDao<T> {

	private DBCollection coll = null;
//	private GridFS fs = null;
	private String defaultStrategy = Mongo.defaultStrategy;

	/**
	 * 获取DBCollection
	 * 
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-1-9 下午6:14:00
	 */
	protected DBCollection getColl() {
		if (coll == null) {
			Class<T> clazz = getTClass();
			Mongo mongoAnno = clazz.getAnnotation(Mongo.class);
			String collName = clazz.getSimpleName().toLowerCase();
			if (mongoAnno != null) {
				if (StrUtil.isNotEmpty(mongoAnno.collection())) {
					collName = mongoAnno.collection();
				}
				// if (mongoAnno.strategy().length == 1) {
				// defaultStrategy = mongoAnno.strategy()[0];// 设置strategy
				// }
			}
			coll = getDB().getCollection(collName);
		}
		return coll;
	}
	
	/**
	 * 返回GridFS对象
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-1-25 下午12:19:44
	 */
//	private GridFS getGridFS() {
//		if (fs == null) {
//			fs = new GridFS(db);
//		}
//		return fs;
//	}

	@Override
	public T get(String id) {
		return get(id, defaultStrategy);
	}

	@Override
	public T get(String id, String strategy) {
		BasicDBObject query = new BasicDBObject(DefaultCollectionID, getObjectID(id));
		DBObject dbObj = getColl().findOne(query);
		return result(dbObj, strategy);
	}

	@Override
	public T getOne(Map<String, Object> map) {
		return getOne(map, defaultStrategy);
	}
	
	@Override
	public T getOne(Map<String, Object> map, String strategy) {
		return getOne(getQuery(map), strategy);
	}

	@Override
	public T getOne(DBObject query) {
		return getOne(query, defaultStrategy);
	}

	@Override
	public T getOne(DBObject query, String strategy) {
		DBObject dbObj = getColl().findOne(query);
		return result(dbObj, strategy);
	}

	@Override
	public int getCount(Map<String, Object> map) {
		return getCount(getQuery(map));
	}

	@Override
	public int getCount(DBObject query) {
		return (int) getColl().getCount(query);
	}

	@Override
	public List<T> getList(Map<String, Object> map) {
		return getList(map, defaultStrategy);
	}

	@Override
	public List<T> getList(Map<String, Object> map, String strategy) {
		int skip = 0;
		if (map.get(BaseMapperDict.startIndex) != null) {
			skip = Integer.parseInt(map.get(BaseMapperDict.startIndex).toString());
		}
		DBObject orderby = getOrderDBObject(map);
		DBCursor cursor = null;
		int limit = 0;
		if (map.get(BaseMapperDict.pageSize) != null) {
			limit = Integer.parseInt(map.get(BaseMapperDict.pageSize).toString());
			cursor = getColl().find(getQuery(map)).skip(skip).limit(limit).sort(orderby);
		} else {
			cursor = getColl().find(getQuery(map)).skip(skip).sort(orderby);
		}

		return result(cursor, strategy);
	}

	@Override
	public List<T> getList(DBObject query, Pager pager) {
		return getList(query, pager, null, defaultStrategy);
	}
	
	@Override
	public List<T> getList(DBObject query, Pager pager, DBObject orderby, String strategy) {
		DBCursor cursor = null;
		if (pager != null) {
			cursor = getColl().find(query).skip(pager.getStartIndex()).limit(pager.getPageSize()).sort(orderby);
		} else {
			cursor = getColl().find(query).skip(0).sort(orderby);
		}

		return result(cursor, strategy);
	}
	
	@Override
	public List<Map<String, Object>> group(Map<String, Object> where, List<String> fields, Map<String, Object> group, Map<String, Object> sort, Pager pager) {
		DBObject matchs = new BasicDBObject(MongoDict.$match, getQuery(where));
		DBObject groups = new BasicDBObject(MongoDict.$group, getGroup(fields, group));
		DBObject project = new BasicDBObject(MongoDict.$project, getFields(fields, group));
		DBObject sorts = new BasicDBObject(MongoDict.$sort, new BasicDBObject(sort));
		List<DBObject> pipeline = Lists.newArrayList(matchs, groups, sorts, project);
		return aggregate(pipeline, null, pager);
	}
	
	/**
	 * 返回group pipe
	 * @param fields
	 * @param group
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-1-28 下午5:14:33
	 */
	private DBObject getGroup(List<String> fields, Map<String, Object> group) {
		BasicDBObjectBuilder builder = BasicDBObjectBuilder.start();
		for (String f : fields) {
			builder.add(f, MongoDict.$ + f);
		}
		DBObject groupFields = new BasicDBObject(DefaultCollectionID, builder.get());
		
//		groupFields.putAll(MongoUtil.toDBObject(group));//暂不需要，没影响...
		groupFields.putAll(group);
		return groupFields;
	}

	/**
	 * 返回project pipe
	 * @param fields
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-1-28 下午5:15:06
	 */
	private DBObject getFields(List<String> fields, Map<String, Object> group) {
		BasicDBObjectBuilder builder = BasicDBObjectBuilder.start(DefaultCollectionID, 0);//不显示_id
		for (String f : fields) {
			builder.add(f, "$_id." + f);
		}
		for (String k : group.keySet()) {
			builder.add(k, 1);
		}
		return builder.get();
	}

	@Override
	public List<Map<String, Object>> aggregate(List<DBObject> pipeline, AggregationOptions options, Pager pager) {
		if (options == null) {
			options = AggregationOptions.builder().build();
		}
		if (pager != null) {
			DBObject limit = new BasicDBObject(MongoDict.$limit, pager.getPageSize());
			DBObject skip = new BasicDBObject(MongoDict.$skip, pager.getStartIndex());
			pipeline.add(limit);
			pipeline.add(skip);
		}
		Cursor cursor = getColl().aggregate(pipeline, options);
		return resultToMaps(cursor);
	}
	
	@Override
	public boolean insert(T t) {
		return insert(t, defaultStrategy);
	}

	@Override
	public boolean insert(T t, String strategy) {
		return insert(new BasicDBObject(MapsUtil.toMap(t, strategy)));
	}

	@Override
	public boolean insert(DBObject dbObj) {
		try {
			getColl().insert(dbObj);
			return true;
		} catch (MongoException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean insertList(List<T> list) {
		return insertList(list, defaultStrategy);
	}

	@Override
	public boolean insertList(List<T> list, String strategy) {
		List<DBObject> all = new ArrayList<>();
		for (T t : list) {
			all.add(new BasicDBObject(MapsUtil.toMap(t, strategy)));
		}
		return insert(all);
	}

	@Override
	public boolean insert(List<DBObject> list) {
		try {
			getColl().insert(list);
			return true;
		} catch (MongoException e) {
			return false;
		}
	}
	
	@Override
	public boolean update(T t) {
		return update(t, defaultStrategy);
	}

	@Override
	public boolean update(T t, String strategy) {
		try {
			getColl().update(BasicDBObjectBuilder.start(DefaultCollectionID, getObjectID(getID(t))).get(), new BasicDBObject(MapsUtil.toMap(t, strategy)), false, false);// 更新一条记录
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean update(Map<String, Object> where, Map<String, Object> fields) {
		DBObject setFields = new BasicDBObject(MongoDict.$set, new BasicDBObject(fields));
		return update(getQuery(where), setFields);
	}

	/**
	 * 按照_id更新多条记录的字段
	 */
	@Override
	public boolean updateFields(Map<String, Object> map) {
		Map<String, Object> m = filterMap(map);
		String id = (String) m.get(DefaultCollectionID);
		m.remove(DefaultCollectionID);
		BasicDBObjectBuilder builder = BasicDBObjectBuilder.start();
		for (String key : m.keySet()) {
			builder.add(key, getValue(m.get(key)));
		}
		try {
			DBObject dbObj = new BasicDBObject(MongoDict.$set, builder.get());
			BasicDBObjectBuilder whereBuilder = BasicDBObjectBuilder.start();
			if (id != null) {
				whereBuilder.append(DefaultCollectionID, getObjectID(id));
			}
			update(whereBuilder.get(), dbObj);
			return true;
		} catch (MongoException e) {
			return false;
		}
	}
	
	/**
	 * 更新where条件的所有记录的dbObj的字段,dbobj需附带修改器
	 * @see com.genius.xo.mongodb.dao.BaseMongoDao.update(DBObject, DBObject)
	 * 
	 */
	@Override
	public boolean update(DBObject where, DBObject dbObj) {
		try {
			getColl().update(where, dbObj, false, true);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean increase(Map<String, Object> map) {
		Map<String, Object> m = filterMap(map);
		String id = (String) m.get(DefaultCollectionID);
		m.remove(DefaultCollectionID);
		BasicDBObjectBuilder builder = BasicDBObjectBuilder.start();
		for (String key : m.keySet()) {
			builder.add(key, Integer.valueOf(m.get(key).toString()));
		}
		DBObject dbObj = new BasicDBObject(MongoDict.$inc, builder.get());
		update(BasicDBObjectBuilder.start(DefaultCollectionID, getObjectID(id)).get(), dbObj);
		return true;
	}

	@Override
	public boolean delete(String id) {
		getColl().remove(new BasicDBObject(DefaultCollectionID, getObjectID(id)));
		return true;
	}

	@Override
	public boolean deleteByMap(Map<String, Object> map) {
		getColl().remove(getQuery(map));
		return true;
	}

	/**
	 * 根据传来的map组装成可以在mongodb中查询的条件，具体的前后缀请参考 {@link com.genius.core.base.constant.BaseMapperDict}
	 * 
	 * @param map
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-1-9 下午7:09:50
	 */
	private DBObject getQuery(Map<String, Object> map) {
		QueryBuilder query = QueryBuilder.start();
		map = filterMap(map);
		Iterator<String> iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			Object value = getValue(map.get(key));
//			if (value instanceof DBObject) {// 已用DBOject做封装，直接使用value
//				query.and((DBObject) value);// 这里不对，需要对嵌套文档查询的支持
//				continue;
//			}
			if (key.endsWith(BaseMapperDict.suffix_greaterOrEqual_key)) {
				String field = getRealField(key, false, BaseMapperDict.suffix_greaterOrEqual_key);
				query.put(field).greaterThanEquals(value);
				continue;
			}
			if (key.endsWith(BaseMapperDict.suffix_greater_key)) {
				String field = getRealField(key, false, BaseMapperDict.suffix_greater_key);
				query.put(field).greaterThan(value);
				continue;
			}
			if (key.endsWith(BaseMapperDict.suffix_lessOrEqual_key)) {
				String field = getRealField(key, false, BaseMapperDict.suffix_lessOrEqual_key);
				query.put(field).lessThanEquals(value);
				continue;
			}
			if (key.endsWith(BaseMapperDict.suffix_less_key)) {
				String field = getRealField(key, false, BaseMapperDict.suffix_less_key);
				query.put(field).lessThan(value);
				continue;
			}
			if (key.endsWith(BaseMapperDict.suffix_like_key)) {
				String field = getRealField(key, false, BaseMapperDict.suffix_like_key);
				query.put(field).regex(Pattern.compile((String) value));
				continue;
			}
			if (key.endsWith(BaseMapperDict.suffix_in_key)) {
				query.put(getRealField(key, false, BaseMapperDict.suffix_in_key)).in(value);
				continue;
			}
			if (key.endsWith(BaseMapperDict.suffix_inall_key)) {
				query.put(getRealField(key, false, BaseMapperDict.suffix_inall_key)).all(value);
				continue;
			}
			if (key.endsWith(BaseMapperDict.suffix_nin_key)) {
				query.put(getRealField(key, false, BaseMapperDict.suffix_nin_key)).notIn(value);
				continue;
			}
			if (key.endsWith(BaseMapperDict.suffix_exist_key)) {
				query.put(getRealField(key, false, BaseMapperDict.suffix_exist_key)).exists(value);
				continue;
			}
			if (key.endsWith(BaseMapperDict.suffix_nexist_key)) {
				query.put(getRealField(key, false, BaseMapperDict.suffix_nexist_key)).not().exists(value);
				continue;
			}
			if (key.endsWith(BaseMapperDict.suffix_mod_key)) {
				query.put(getRealField(key, false, BaseMapperDict.suffix_mod_key)).mod(value);
				continue;
			}
			if (key.endsWith(BaseMapperDict.suffix_arrsize_key)) {
				query.put(getRealField(key, false, BaseMapperDict.suffix_arrsize_key)).size(value);
				continue;
			}
			if (key.endsWith(BaseMapperDict.suffix_no_key)) {
				query.put(getRealField(key, false, BaseMapperDict.suffix_no_key)).notEquals(value);
				continue;
			}
			if (key.endsWith(BaseMapperDict.suffix_notEqual_key)) {
				query.put(getRealField(key, false, BaseMapperDict.suffix_notEqual_key)).notEquals(value);
				continue;
			}
			if (key.equals(BaseMapperDict.ids)) {
				query.put(DefaultCollectionID).in(value); //没有使用getObjectID做转化，需要在此方法外部提前转化。
				continue;
			}
			query.put(key).is(value);
		}
		return query.get();
	}

	/**
	 * 处理map中的特殊类型，转化为基本类型，比如枚举 jodatime等
	 * 
	 * @param value
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-1-12 下午12:43:15
	 */
	private Object getValue(Object value) {
		if (value != null) {
			if (value instanceof DateTime) {
				value = ((DateTime) value).getMillis();
			} else if (value instanceof LocalDate) {
				value = ((LocalDate) value).toDateTimeAtStartOfDay().toDate().getTime();
			} else if (value instanceof LocalTime) {
				value = ((LocalTime) value).getMillisOfDay();
			} else if (value.getClass().isEnum()) {
				value = value.toString();
			}
		}
		return value;
	}

	/**
	 * 将dbobject转成对象
	 * 
	 * @param dbObj
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-1-9 下午7:22:09
	 */
	protected T result(DBObject dbObj, String strategy) {
		if (dbObj == null) {
			return null;
		}
		return (T) MapsUtil.fromMap(MongoUtil.toMap(dbObj), getTClass(), strategy);
	}

	/**
	 * 将dbcursor转成arraylist
	 * 
	 * @param cursor
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-1-9 下午7:22:27
	 */
	@SuppressWarnings("unchecked")
	private List<T> result(DBCursor cursor, String strategy) {
		if (cursor == null) {
			return null;
		}
		List<T> list = new ArrayList<>();
		if (cursor != null) {
			List<DBObject> tmpList = cursor.toArray();
			for (DBObject item : tmpList) {
				list.add((T) MapsUtil.fromMap(item.toMap(), getTClass(), strategy));
			}
		}
		return list;
	}

	/**
	 * 通过cursor返回List<Map>的结果
	 * @param cursor
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-1-28 下午2:51:59
	 */
	private List<Map<String, Object>> resultToMaps(Cursor cursor) {
		if (cursor == null) {
			return null;
		}
		List<Map<String, Object>> list = new ArrayList<>();
		if (cursor != null) {
			while (cursor.hasNext()) {
				DBObject item = cursor.next();
				if (item != null) {
					list.add(MongoUtil.toMap(item));
				} else {
					list.add(null);
				}
			}
		}
		return list;
	}
}
