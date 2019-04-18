package com.supergenius.xo.life.dao;

import org.springframework.stereotype.Component;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.life.entity.Complaint;

/**
 * 投诉举报Dao
 * @author YangGuang
 * @date 2018年5月9日16:45:23
 */
@Component("LifeComplaintDao")
public interface ComplaintDao extends BaseDao<Complaint> {

}
