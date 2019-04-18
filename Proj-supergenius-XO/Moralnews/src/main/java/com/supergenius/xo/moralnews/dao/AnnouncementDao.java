package com.supergenius.xo.moralnews.dao;

import org.springframework.stereotype.Component;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.moralnews.entity.Announcement;

/**
 * 公告Dao
 * @author JiaShitao
 * @date 2018年9月19日09:47:55
 */
@Component("moralnewsAnnouncementDao")
public interface AnnouncementDao extends BaseDao<Announcement> {
	
}
