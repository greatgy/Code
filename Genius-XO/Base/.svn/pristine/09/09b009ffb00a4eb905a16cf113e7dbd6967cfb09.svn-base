package com.genius.xo.base.dao;

import java.util.List;
import java.util.Map;

/**
 * @author Architect.bian
 *
 */
public interface BaseDao<T> {
	
	/**
	 * 通过ID查询数据库，返回对象
	 * @param id 对应数据库中uid
	 * @return
	 */
	T get(String id);
	
	/**
	 * 将键值对作为参数查询数据库返回一个对象
	 * @param map
	 * @return
	 */
	T getOne(Map<String, Object> map);
	
	/**
	 * 根据键值对获取符合条件的数量
	 * @param map
	 * @return int
	 */
	int getCount(Map<String, Object> map);
	
	/**
	 * 将键值对作为参数查询数据库返回一组对象
	 * @param map
	 * @return
	 */
	List<T> getList(Map<String, Object> map);
	
	/**
	 * 将对象插入数据库
	 * @param t
	 */
	boolean insert(T t);
	
	/**
	 * 将多个对象批量插入数据库
	 * @param list
	 * @author Architect.bian
	 * @createtime 2014-7-24 下午2:51:13
	 */
	boolean insertList(List<T> list);
	
	/**
	 * 更新一个对象所有的属性
	 * @param t
	 */
	boolean update(T t);
	
	/**
	 * 针对性的更新某几个字段
	 * @param map
	 */
	boolean updateFields(Map<String, Object> map);
	
	/**
	 * 增量某一条记录的某几个字段
	 * @param map
	 */
	boolean increase(Map<String, Object> map);
	
	/**
	 * 删除操作
	 * @param id
	 */
	boolean delete(String id);
	
	//TODO 批量删除。。。
	//void deleteList(String id);
	
	/**
	 * 批量删除
	 * @param map
	 */
	boolean deleteByMap(Map<String, Object> map);
}
