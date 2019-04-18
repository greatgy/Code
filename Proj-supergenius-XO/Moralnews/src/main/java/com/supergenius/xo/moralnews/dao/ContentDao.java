package com.supergenius.xo.moralnews.dao;

import org.springframework.stereotype.Component;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.moralnews.entity.Content;

/**
 * 内容Dao
 * 
 * @author JiaShitao
 * @date 2018年9月19日09:47:55
 */
@Component("moralnewsContentDao")
public interface ContentDao extends BaseDao<Content> {

}
