package com.supergenius.xo.user.dao;

import org.springframework.stereotype.Component;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.user.entity.ScoreDetail;

/**
 * 积分明细Dao
 * 
 * @author liubin
 * @createtime 2016-7-18 下午5:47:18
 */
@Component("userScoreDetailDao")
public interface ScoreDetailDao extends BaseDao<ScoreDetail> {

}
