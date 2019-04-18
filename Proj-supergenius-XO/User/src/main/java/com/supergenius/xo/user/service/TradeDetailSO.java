package com.supergenius.xo.user.service;

import java.util.List;

import com.genius.model.base.entity.Pager;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.user.entity.TradeDetail;

/** 
 * 交易明细SO
 * @author chenminchang
 * @date 2016-3-24 上午9:58:04 
 */
public interface TradeDetailSO extends BaseSO<TradeDetail> {
	
	/**
	 * 根据useruid查询交易明细并分页
	 * @param useruid
	 * @param pager
	 * @return
	 */
	List<TradeDetail>  getList(String useruid, Pager pager);
	
	/**
	 * 根据useruid获取count
	 * @param useruid
	 * @return
	 */
	int getCountByUseruid(String useruid);
	
	/**
	 * 获取所有平台的交易总金额
	 * @return
	 */
	double getSumMoney();
	
	/**
	 * 根据site(来源平台)获取总的交易金额
	 * @param typesmap
	 * @return
	 */
	double getSumMoney(ESite site);
}
