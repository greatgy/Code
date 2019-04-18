package com.genius.core.search.engine;

import java.util.List;
import java.util.Map;

/**
 * 搜索引擎接口
 * 
 * @author architect.bian
 * @createtime 2015-12-28 下午4:30:49
 */
public interface SearchEngine {
	
	/**
	 * 搜索
	 * @param map
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-12-30 下午2:01:36
	 */
	public List<?> search(Map<String, Object> map);

	/**
	 * 添加索引
	 * @param map
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-12-30 下午12:15:41
	 */
	public boolean add(Map<String, Object> map);
	
	/**
	 * 批量添加索引
	 * @param map
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-12-30 下午12:15:49
	 */
	public boolean addBatch(List<Map<String, Object>> maps);
	
	/**
	 * 删除索引
	 * @param map
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-12-30 下午12:15:59
	 */
	public boolean delete(Map<String, Object> map);
	
	/**
	 * 通过id删除索引
	 * @param id
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-12-30 下午12:16:28
	 */
	public boolean deleteByID(String... ids);
}
