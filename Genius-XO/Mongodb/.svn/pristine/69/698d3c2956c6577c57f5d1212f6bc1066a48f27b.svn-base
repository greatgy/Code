package com.genius.xo.mongodb.dao;

import java.util.List;
import java.util.Map;

import com.genius.model.base.entity.Pager;
import com.genius.xo.base.dao.BaseDao;
import com.mongodb.AggregationOptions;
import com.mongodb.DBObject;

/**
 * mongodb的basedao，可扩展mongodb的特定方法
 * @param <T>
 * @author architect.bian
 * @createtime 2015-1-7 下午5:06:21
 */
public interface BaseMongoDao<T> extends BaseDao<T> {
	
	/**
	 * 通过ID及指定strategy查询数据库，返回对象
	 * @param id
	 * @param strategy
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-1-11 上午11:18:04
	 */
	T get(String id, String strategy);
	
	/**
	 * 将键值对作为参数查询数据库，并指定strategy，返回一个对象
	 * @param map
	 * @param strategy
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-1-11 上午11:19:05
	 */
	T getOne(Map<String, Object> map, String strategy);
	
	/**
	 * 直接通过dbobject获取一个对象
	 * @param query
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-1-26 下午6:05:28
	 */
	T getOne(DBObject query);
	
	/**
	 * 直接通过dbobject获取一个对象,使用strategy进行返回对象
	 * @param query
	 * @param strategy
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-1-26 下午6:19:42
	 */
	T getOne(DBObject query, String strategy);
	
	/**
	 * 通过dbobject获取数量
	 * @param query
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-1-26 下午6:37:16
	 */
	int getCount(DBObject query);
	
	/**
	 * 将键值对作为参数查询数据库，并指定strategy，返回一组对象
	 * @param map
	 * @param strategy
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-1-11 上午11:19:54
	 */
	List<T> getList(Map<String, Object> map, String strategy);
	
	/**
	 * 直接使用dbobject获取一个list
	 * @param query
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-1-26 下午6:06:16
	 */
	List<T> getList(DBObject query, Pager pager);
	
	/**
	 * 通过dbobject及strategy返回一个list
	 * @param query
	 * @param strategy
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-1-26 下午6:38:34
	 */
	List<T> getList(DBObject query, Pager pager, DBObject orderby, String strategy);
	
	/**
	 * 进行group操作
	 * @param where 查询条件
	 * @param fields group依照及需要返回的字段名
	 * @param group 聚合字段
	 * @param sort 排序条件
	 * @param pager 分页
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-1-28 下午5:16:35
	 */
	List<Map<String, Object>> group(Map<String, Object> where, List<String> fields, Map<String, Object> group, Map<String, Object> sort, Pager pager);
	
	/**
	 *  聚合方法
	 * @param pipeline
	 * @param options
	 * @param pager
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-1-29 上午10:51:41
	 */
	List<Map<String, Object>> aggregate(List<DBObject> pipeline, AggregationOptions options, Pager pager);
	
	/**
	 * 将对象采用指定strategy插入数据库
	 * @param t
	 * @param strategy
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-1-11 上午11:20:22
	 */
	boolean insert(T t, String strategy);
	
	/**
	 * 插入一个dbobject
	 * @param dbObj
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-1-26 下午6:07:37
	 */
	boolean insert(DBObject dbObj);

	/**
	 * 一次性插入多个dbobject
	 * @param list
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-1-26 下午6:08:15
	 */
	boolean insert(List<DBObject> list);
	
	/**
	 * 将多个对象批量，指定strategy，插入数据库
	 * @param list
	 * @param strategy
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-1-11 上午11:21:33
	 */
	boolean insertList(List<T> list, String strategy);
	
	/**
	 * 指定strategy更新一个对象所有的属性
	 * @param t
	 * @param strategy
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-1-11 上午11:21:58
	 */
	boolean update(T t, String strategy);
	
	/**
	 * 更新where条件对应的多条记录的dbObj字段 <br />
	 * $set和$unset:修改字段值和删除字段； <br />
	 * $rename，修改字段名字，db.dm.update({}, {$rename:{"age":"ages"}}, false, true)； <br />
	 * $inc:数字类型字段增减，db.dm.update({}, {$inc:{"age":1}}, false, true)； <br />
	 * $push和$pushAll:向数组类型末尾追加数据，字段不存在则创建； <br />
	 * $addToSet:向数组类型追加数据时避免重复； <br />
	 * $each:遍历数组； <br />
	 * $pop:从数组中删除数据，{$pop:{key:1}}从末尾删除数据，-1从头部删除； <br />
	 * $pull和$pullAll:从数组中删除指定数据； <br />
	 * @param dbObj
	 * @param where
	 * @param dbObj 需要附带修改器，如{"$set" : {"age" : 10}}
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-1-26 下午6:08:52
	 */
	boolean update(DBObject where, DBObject dbObj);
	
	/**
	 * 赋值($set)更新特定条件的多条记录的某些字段
	 * @param condition
	 * @param fields
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-1-26 下午2:01:17
	 */
	boolean update(Map<String, Object> where, Map<String, Object> fields);
	
}
