package com.supergenius.xo.gupage.dao;

import org.springframework.stereotype.Component;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.gupage.entity.Photo;

/**
 * 图片Dao
 * @author yangguang
 * @date 2018年1月10日10:44:00
 */
@Component("gupagePhotoDao")
public interface PhotoDao extends BaseDao<Photo> {
}
