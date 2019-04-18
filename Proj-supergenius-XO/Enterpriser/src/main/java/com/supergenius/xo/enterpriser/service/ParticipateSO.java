package com.supergenius.xo.enterpriser.service;

import java.util.List;
import java.util.Map;

import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.enterpriser.entity.Participate;
import com.supergenius.xo.user.entity.Order;
import com.supergenius.xo.user.entity.OrderGoods;
import com.supergenius.xo.user.entity.OrderLog;
import com.supergenius.xo.user.entity.TradeDetail;
import com.supergenius.xo.user.entity.User;

/**
 * 报名SO
 * 
 * @author liubin
 * @date 2016-10-24 下午5:15:37
 */
public interface ParticipateSO extends BaseSO<Participate> {

	/**
	 * 得到指定状态的数量
	 * 
	 * @param status
	 * @author liubin
	 * @createtime 2016-10-25下午5:17:56
	 */
	int getCount(EStatus status);

	/**
	 * 根据查询条件得到count
	 * 
	 * @param map
	 * @return
	 * @author liubin
	 * @createtime 2016-10-25下午6:49:44
	 * @return int
	 */
	int getCountBySearch(Map<String, Object> map);

	/**
	 * 根据查询条件得到list
	 * 
	 * @param map
	 * @return
	 * @author liubin
	 * @createtime 2016-10-25下午6:50:00
	 * @return List<Participate>
	 */
	List<Participate> getListBySearch(Map<String, Object> map);

	/**
	 * 得到多个状态的数量
	 * 
	 * @param status
	 * @return
	 * @author liubin
	 * @createtime 2016-10-26下午12:24:14
	 * @return int
	 */
	int getCount(EStatus... status);

	/**
	 * 有一个报名的同时更新讲座表
	 */
	boolean update(User user, Participate participate, Order order, TradeDetail tradeDetail, OrderLog orderLog, OrderGoods orderGoods);

	/**
	 * 根据报名的uid和状态得到一条记录
	 * 
	 * @param useruid
	 * @param disable
	 * @return
	 * @author XieMing 2016-10-25 下午6:41:27
	 */
	Participate get(String useruid, EStatus disable);
	
	/**
	 * 根据邮箱获取一个报名
	 * @param email
	 * @return
	 * @author chenminchang
	 * @create 2016年12月5日下午6:08:29
	 */
	Participate getOneByEmail(String email);
	
	/**
	 * 根据讲座uid获取
	 * @param lectureuid
	 * @return
	 * @author chenminchang
	 * @create 2016-10-26下午5:15:26
	 */
	List<Participate> getListByLecture(String lectureuid);

}
