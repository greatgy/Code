package com.supergenius.xo.user.dao;

import java.util.Map;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.user.entity.TradeDetail;

/** 
 * 交易明细Dao
 * @author chenminchang
 * @date 2016-3-24 上午9:56:04 
 */
public interface TradeDetailDao extends BaseDao<TradeDetail> {
	
	double getSumMoney(Map<String, Object> typesmap);
}
