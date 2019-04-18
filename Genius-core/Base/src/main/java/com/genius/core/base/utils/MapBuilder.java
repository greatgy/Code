package com.genius.core.base.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * hashmap的构造类
 * 
 * @author architect.bian
 * @createtime 2015-1-29 下午5:29:54
 */
public class MapBuilder {

	private Map<String, Object> map = new HashMap<>();
	
	/**
	 * 开始一个MapBuilder
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-1-29 下午6:13:32
	 */
	public static MapBuilder start() {
		return new MapBuilder();
	}
	
	/**
	 * 通过一个map开始一个MapBuilder
	 * @param map
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-1-29 下午6:13:46
	 */
	public static MapBuilder start(Map<String, Object> map) {
		MapBuilder b = new MapBuilder();
		for (String key : map.keySet()) {
			b.add(key, map.get(key));
		}
        return b;
	}
	
	/**
	 * 通过一个k,v创建一个MapBuilder
	 * @param k
	 * @param v
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-1-29 下午6:14:00
	 */
	public static MapBuilder start(String k, Object v) {
		return new MapBuilder(k, v);
	}
	
	public MapBuilder() {}
	
	public MapBuilder(String k, Object v) {
		map.put(k, v);
	}

	/**
	 * 添加一项
	 * @param k
	 * @param v
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-1-29 下午6:14:26
	 */
	public MapBuilder add(String k, Object v) {
		map.put(k, v);
		return this;
	}
	
	/**
	 * 返回Map结果
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-1-29 下午6:14:50
	 */
	public Map<?, ?> get() {
		return map;
	}
}
