package com.supergenius.xo.gupage.dao;

import org.springframework.stereotype.Component;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.gupage.entity.Patent;

/**
 * 专利Dao
 * @author loupengyu
 * @date 2018年1月10日11:23:06
 */
@Component("gupagePagerDao")
public interface PatentDao extends BaseDao<Patent>{

}
