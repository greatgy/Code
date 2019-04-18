package com.supergenius.xo.official.daoimpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.bson.BSONObject;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.springframework.stereotype.Component;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.core.base.utils.MapsUtil;
import com.genius.xo.mongodb.Mongo;
import com.genius.xo.mongodb.daoimpl.BaseMongoDaoImpl;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.official.dao.BannerDao;
import com.supergenius.xo.official.entity.Banner;

/**
 * BannerDao实现
 * 
 * @author Liuxiaoke
 * @Modifier YuYingJie
 */
@Component
public class BannerDaoImpl extends BaseMongoDaoImpl<Banner> implements BannerDao {
	
	@Override
	public List<Banner> getList(Map<String, Object> map) {
		return getList(map, Mongo.defaultStrategy);
	}
	
	@Override
	public List<Banner> getList(Map<String, Object> map, String strategy) {
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
	
	/**
	 * 获取排序规则，健为orderBy，值为BSONObject时直接使用，值作为唯一的排序健，否则获取健为ascDesc的值判断升降序
	 * @param map
	 * @return
	 */
	@Override
	protected DBObject getOrderDBObject(Map<String, Object> map){
		BasicDBObject orderBy = new BasicDBObject();
		Object value = map.get(BaseMapperDict.orderBy);
		if (value instanceof BSONObject) {// 值为BJSONObject的实例或BasicDBObject时，直接putall，可实现复杂的排序
			orderBy.putAll((BSONObject)value);
		} else {
			String orderbykey = MapperDict.sortorder;
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
	 * 根据传来的map组装成可以在mongodb中查询的条件，具体的前后缀请参考 {@link com.genius.core.base.constant.BaseMapperDict}
	 * 
	 * @param map
	 * @return
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
				query.put(getRealField(key, false, BaseMapperDict.suffix_no_key)).not().is(value);
				continue;
			}
			if (key.equals(BaseMapperDict.ids)) {
				query.put(DefaultCollectionID).in(value);
				continue;
			}
			query.put(key).is(value);
		}
		return query.get();
	}
	
	/**
	 * 将dbcursor转成arraylist
	 * 
	 * @param cursor
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<Banner> result(DBCursor cursor, String strategy) {
		if (cursor == null) {
			return null;
		}
		List<Banner> list = new ArrayList<>();
		if (cursor != null) {
			List<DBObject> tmpList = cursor.toArray();
			for (DBObject item : tmpList) {
				list.add((Banner) MapsUtil.fromMap(item.toMap(), getTClass(), strategy));
			}
		}
		return list;
	}
	
	/**
	 * 处理map中的特殊类型，转化为基本类型，比如枚举 jodatime等
	 * 
	 * @param value
	 * @return
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
}
