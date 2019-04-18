package com.supergenius.xo.moral.daoimpl;

import org.springframework.stereotype.Component;

import com.genius.xo.mongodb.daoimpl.BaseMongoDaoImpl;
import com.supergenius.moral.moral.dao.AnnouncementDao;
import com.supergenius.xo.moral.entity.Announcement;

/**
 * 社区公告Dao实现
 * 
 * @author LiJiacheng
 */
@Component
public class AnnouncementDaoImpl extends BaseMongoDaoImpl<Announcement> implements AnnouncementDao {

}
