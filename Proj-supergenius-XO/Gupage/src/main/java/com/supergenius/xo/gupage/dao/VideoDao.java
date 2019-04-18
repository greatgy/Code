package com.supergenius.xo.gupage.dao;

import org.springframework.stereotype.Component;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.gupage.entity.Video;

/**
 * 视频Dao
 * @author yangguang
 * @date 2018年1月10日10:44:49
 */
@Component("gupageVideoDao")
public interface VideoDao extends BaseDao<Video> {
	
}
