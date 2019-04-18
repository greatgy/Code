package com.genius.xo.base.service;

import java.util.List;
import java.util.Map;

import com.genius.model.base.entity.Pager;

/**
 * @author Architect.bian
 *
 */
public interface BaseSO<T> {
	
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
	 * 通过某个字段获取一个对象
	 * @param map
	 * @return
	 */
	T getOneByField(String fieldname, Object obj);
	
	/**
	 * 返回统计，若报错返回-1
	 * @param map
	 * @return
	 */
	int getCount(Map<String, Object> map);
	
	/**
	 * 返回统计，若报错返回-1
	 * @param map
	 * @return
	 */
	int getCountByField(String fieldname, Object obj);
	
	/**
	 * 返回所有行的统计，若报错返回-1
	 * @param map
	 * @return
	 */
	int getCount();
	
	/**
	 * 将键值对作为参数查询数据库返回一组对象
	 * @param map
	 * @return
	 */
	List<T> getList(Map<String, Object> map);
	
	/**
	 * 将键值对作为参数查询数据库返回一组对象
	 * @param map
	 * @return
	 */
	List<T> getListByField(String fieldname, Object obj);
	
	/**
	 * 将键值对作为参数查询数据库返回一组对象
	 * @param map
	 * @return
	 */
	List<T> getList(Pager pager);
	
	/**
	 * 将键值对作为参数查询数据库返回一组对象
	 * @param map
	 * @return
	 */
	List<T> getList();
	
	/**
	 * 添加对象
	 * @param t
	 */
	boolean add(T t);
	
	/**
	 * 添加多个对象
	 * @param t
	 */
	boolean add(List<T> list);
	
	/**
	 * 更新对象所有的属性
	 * @param t
	 */
	boolean update(T t);
	
	/**
	 * 若已重写则可更新对象某几个属性，否则更新所有字段，同update方法
	 * TODO 从配置文件读取需要更新的字段，并组成map针对性的更新，或者使用fields...参数
	 * @param t
	 */
	boolean updateFields(T t);
	
	/**
	 * 针对性的更新某几个字段
	 * @param map
	 */
	boolean updateFields(Map<String, Object> map);
	
	/**
	 * 增量某几个字段
	 * @param map
	 */
	boolean increase(Map<String, Object> map);
	
	/**
	 * 删除操作
	 * @param ids
	 */
	boolean delete(String... ids);

	/**
	 * 批量删除
	 * @param map
	 */
	boolean deleteByMap(Map<String, Object> map);
	
	/**
	 * 批量删除
	 * @param map
	 */
	boolean deleteByField(String fieldname, Object obj);
}
