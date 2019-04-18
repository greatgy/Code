package com.supergenius.xo.user.service;

import java.util.List;

import com.genius.model.base.entity.Pager;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.user.entity.Order;
import com.supergenius.xo.user.entity.OrderGoods;
import com.supergenius.xo.user.enums.EGoods;

/**
 * 订单商品Service
 * @author diaobisong
 */
public interface OrderGoodsSO extends BaseSO<OrderGoods> {
	
	/**
	 * 根据多个orderuid和useruid获取订单商品
	 * @param userUid
	 * @param orderList
	 * @return
	 */
	public List<OrderGoods> getByOrderList(String userUid, List<Order> orderList);

	/**
	 * 根据orderuid获取商品
	 * @param orderuid
	 * @return
	 */
	public List<OrderGoods> getList(String orderuid);
	
	/**
	 * 根据商品的编号获取相关的订单条目
	 * @param sn
	 * @return
	 * @author XieMing
	 * 2016-8-1 上午11:07:59
	 */
	List<OrderGoods> getListBySn(String sn);
	
	/**
	 * 根据商品的编号分页获取相关的订单条目
	 * @param sn
	 * @param pager
	 * @return
	 * @author XieMing
	 * 2016-9-13 下午2:55:54
	 */
	List<OrderGoods> getListBySn(String sn, Pager pager);

	/**
	 * 根据用户的uid和商品的sn获取记录
	 * @param sn
	 * @param uid
	 * @return
	 * @author XieMing
	 * 2016-8-5 下午3:29:30
	 */
	public List<OrderGoods> getListBySnAndUseruid(String sn, String useruid);
	
	/**
	 * 得到最近五个购买该挑战的List
	 * @param pkScheduelSN
	 * @param type
	 * @param pageSize
	 * @author liubin
	 * @createtime 2016-8-16下午5:26:18
	 * @return List<OrderGoods>
	 */
	List<OrderGoods> getListBySize(String pkScheduelSN, EGoods type, int pageSize);
	
	/**
	 * 得到List
	 * @param userUid
	 * @param type
	 * @author liubin
	 * @createtime 2016-8-22下午5:24:56
	 * @return List<OrderGoods>
	 */
	List<OrderGoods> getList(String userUid, EGoods type);

	/**
	 * 根据sn获取一个订单条目
	 * @param sn
	 * @return
	 * @author XieMing
	 * 2016-8-24 下午4:25:29
	 */
	public OrderGoods getOne(String useruid, String sn);

	/**
	 * 根据商品的编号获取相关的记录条数
	 * @param sn
	 * @return
	 * @author XieMing
	 * 2016-9-13 下午3:16:05
	 */
	public int getCount(String sn);

	/**
	 * 获取购买某视频的人数(不是人次)
	 * @param sn
	 * @param video
	 * @return
	 * @author XieMing
	 * 2016-10-21 上午9:28:38
	 */
	public int getBuyUserCount(String sn, EGoods type);

	/**
	 * 获取购买某视频的列表
	 * @param sn
	 * @param video
	 * @return
	 * @author XieMing
	 * 2016-10-21 上午10:50:21
	 */
	public List<String> getBuyUserList(String sn, EGoods type);
	
	/**
	 * 获取购买某挑战的列表
	 * @param refuid
	 * @param type
	 * @return
	 * @author liubin
	 * @createtime 2016-11-15下午8:43:57
	 * @return List<OrderGoods>
	 */
	List<OrderGoods> getListByRefuid(String refuid, EGoods type);
}
