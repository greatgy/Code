package com.supergenius.xo.startup.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.startup.entity.News;

/**
 * 消息Dao
 * @author yangguang
 * @date 2017年8月29日10:02:46
 */
@Component("StartupNewsDao")
public interface NewsDao extends BaseDao<News> {
	/**
	 * 获取我的消息
	 * @param map
	 * @return
	 * @author 许志翔
	 * @date 2017年9月8日15:07:51
	 */
	List<News> getListByUseruid(Map<String, Object> map);
}
