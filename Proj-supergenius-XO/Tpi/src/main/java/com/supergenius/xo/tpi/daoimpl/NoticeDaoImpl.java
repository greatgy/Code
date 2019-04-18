package com.supergenius.xo.tpi.daoimpl;

import org.springframework.stereotype.Component;

import com.genius.xo.mongodb.daoimpl.BaseMongoDaoImpl;
import com.supergenius.xo.tpi.dao.NoticeDao;
import com.supergenius.xo.tpi.entity.Notice;

/**
 * NoticeDaoImpl
 * @author liushaomin
 */
@Component
public class NoticeDaoImpl extends BaseMongoDaoImpl<Notice> implements NoticeDao{

}
