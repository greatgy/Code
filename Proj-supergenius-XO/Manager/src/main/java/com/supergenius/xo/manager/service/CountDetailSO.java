package com.supergenius.xo.manager.service;

import java.util.List;

import com.genius.model.base.entity.Pager;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.manager.entity.CountDetail;
import com.supergenius.xo.manager.enums.ECount;

/** 
 * 计数明细SO
 * @author guanshiqian
 * @date 2016-4-27 下午5:21:15 
 */
public interface CountDetailSO extends BaseSO<CountDetail> {

	/**
	 * 根据挑战的uid获取对应的投票记录
	 * @param uid
	 * @return
	 * @author XieMing
	 * 2016-8-5 上午10:58:34
	 */
	List<CountDetail> getListByRefuid(String uid);
	
	/**
	 * 根据挑战的uid获取对应的投票记录
	 * @param uid
	 * @return
	 * @author XieMing
	 * 2016-8-5 上午10:58:34
	 */
	List<CountDetail> getListByRefuid(String uid, Pager pager);	
	
	/**
	 * 获得list
	 * @param userUid
	 * @param refUid
	 * @param type
	 * @author liubin
	 * @createtime 2016-8-16下午1:55:14
	 * @return List<CountDetail>
	 */
	List<CountDetail> getList(String userUid, String refUid, ECount type);
	
	/**
	 * 根据相关uid和点赞类型获得list
	 * @param refuid
	 * @param type
	 * @author liubin
	 * @createtime 2016-8-16下午4:25:48
	 * @return List<CountDetail>
	 */
	List<CountDetail> getList(String refuid, ECount type);
	
	/**
	 * 根据相关uid和点赞类型获得数量
	 * @param refuid
	 * @param type
	 * @author liubin
	 * @createtime 2016-8-16下午4:57:12
	 * @return int
	 */
	int getCount(String refuid, ECount type);
	
	/**
	 * 根据refUid,type分页获得大小
	 * @param userUid
	 * @param refUid
	 * @param pageSize
	 * @author liubin
	 * @createtime 2016-8-16下午5:03:29
	 * @return List<CountDetail>
	 */
	List<CountDetail> getListBySize(String refuid, ECount type, int pageSize);
	
	/**
	 * 得到CountDetail对象
	 * @param userUid
	 * @param refUid
	 * @param type
	 * @author liubin
	 * @createtime 2016-8-17上午9:45:37
	 * @return CountDetail
	 */
	CountDetail getOne(String userUid, String refUid, ECount type);
}
