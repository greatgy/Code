package com.supergenius.xo.manager.service;

import java.util.List;
import java.util.Map;

import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.manager.entity.Video;
import com.supergenius.xo.manager.enums.EMajor;
import com.supergenius.xo.manager.enums.EVideoFrom;

/**
 * 视频SO
 * 
 * @author XieMing
 * @date 2016-7-18 下午2:28:41
 */
public interface VideoSO extends BaseSO<Video> {

	/**
	 * 根据挑战的uid获取视频
	 * @param string
	 * @param uid
	 * @author XieMing
	 * 2016-8-3 下午12:42:18
	 */
	Video getOneByPkuid(String uid);

	/**
	 * 获得热门视频
	 * 
	 * @param map
	 * @return
	 */
	List<Video> getHotVideoList(Map<String, Object> map);

	/**
	 * 根据专业获得视频数量
	 * 
	 * @param map
	 * @return
	 */
	int getCount(EMajor major);

	/**
	 * 根据关键字获得视频数量
	 * 
	 * @param keyword
	 * @return
	 */
	int getCountByKeyword(Map<String, Object> map);

	/**
	 * 根据关键字获得视频list
	 * 
	 * @param keyword
	 * @return
	 */
	List<Video> getVideosByKeyword(Map<String, Object> map);

	/**
	 * 根据查询获得视频数量
	 * 
	 * @param map
	 * @return
	 */
	int getCountBySearch(Map<String, Object> map);

	/**
	 * 根据查询获得视频list
	 * 
	 * @param map
	 * @return
	 */
	List<Video> getlistBySearch(Map<String, Object> map);
	
	/**
	 * 根据专业获得list
	 * @param major
	 * @author liubin
	 * @createtime 2016-8-16下午7:23:10
	 * @return List<Video>
	 */
	List<Video> getList(EMajor major);
	
	/**
	 * 根据专业获得视频指定大小List
	 * @param major
	 * @param pager
	 * @return
	 * @author liubin
	 * @createtime 2016-9-2上午11:32:39
	 * @return List<Video>
	 */
	List<Video> getList(EMajor major, Pager pager);
	
	/**
	 * 根据专业获得该专业所有视频的总播放量
	 * @param major
	 * @return
	 * @author liubin
	 * @createtime 2016-9-12上午11:10:02
	 * @return int
	 */
	int getTotalPlayCount(EMajor major);

	/**
	 * 获取某个来源的视频的数量
	 * @param pk
	 * @return
	 * @author XieMing
	 * 2016-10-20 下午2:29:23
	 */
	int getCount(EVideoFrom channelfrom);
	
	/**
	 * 获取所有视频个数
	 */
	int getCount();
	
	/**
	 * 得到指定数量的推荐视频
	 * @param isrecommend
	 * @param pager
	 * @return
	 * @author liubin
	 * @createtime 2016-10-20上午10:37:59
	 * @return List<Video>
	 */
	List<Video> getList(boolean isrecommend, Pager pager);

	/**
	 * 后台操作修改视频(冻结，解冻)
	 * @param video
	 * @param adminLog
	 * @return
	 * @author XieMing
	 * 2016-10-27 下午7:10:06
	 */
	boolean updateStatus(String uid, EStatus status, AdminLog adminLog);

	/**
	 * 后台操作是否推荐某视频(推荐，取消推荐)
	 * @param uid
	 * @param isrecommend
	 * @param adminLog
	 * @return
	 * @author XieMing
	 * 2016-10-31 下午2:37:25
	 */
	boolean updateStatus(String uid, boolean isrecommend, AdminLog adminLog);

	/**
	 * 更新视频状态
	 * @param uid
	 * @param status
	 * @return
	 * @author XieMing
	 * 2016-10-31 下午2:43:14
	 */
	boolean updateStatus(String uid, EStatus status);

	/**
	 * 更新视频是否推荐
	 * @param uid
	 * @param isrecommend
	 * @return
	 * @author XieMing
	 * 2016-10-31 下午2:43:31
	 */
	boolean updateIsrecommend(String uid, boolean isrecommend);
	
	/**
	 * 根据视频来源和相关uid获得视频
	 * @param refuid
	 * @param channelfrom
	 * @return
	 * @author liubin
	 * @createtime 2016-11-7下午1:56:58
	 * @return Video
	 */
	Video getOne(String refuid, EVideoFrom channelfrom);
}
