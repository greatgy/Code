package com.supergenius.xo.manager.service;

import java.util.List;

import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.manager.entity.AppReplyDetail;
import com.supergenius.xo.manager.enums.EReplyStage;

/**
 * 答辩状态明细SO
 * @author XieMing
 * @date 2016-7-18 下午2:29:30
 */
public interface AppReplyDetailSO extends BaseSO<AppReplyDetail> {

	/**
	 * 根据replystagefrom查询总数
	 * @param replystagefrom
	 * @return
	 * @author liubin
	 * @createtime 2016-11-9下午3:40:45
	 * @return int
	 */
	int getCount(EReplyStage replystagefrom);
	
	/**
	 * 根据replystageto查询总数
	 * @param replystageto
	 * @return
	 * @author liubin
	 * @createtime 2016-11-9下午4:13:10
	 * @return int
	 */
	int getCountByStage(EReplyStage replystageto);
	
	/**
	 * 根据初始状态到结束状态得到总数
	 * @param replystagefrom
	 * @param replystageto
	 * @return
	 * @author liubin
	 * @createtime 2016-11-9下午4:18:33
	 * @return int
	 */
	int getCount(EReplyStage replystagefrom, EReplyStage replystageto);
	
	/**
	 * 查询多个状态的数量
	 * @param replystagefrom
	 * @return
	 * @author liubin
	 * @createtime 2016-11-10下午4:07:13
	 * @return int
	 */
	int getCount(List<EReplyStage> replystagefrom);
	
	/**
	 * 根据申请表得到申请详情list
	 * @param appreplyuid
	 * @return
	 * @author liubin
	 * @createtime 2016-11-10下午4:25:15
	 * @return List<AppReplyDetail>
	 */
	List<AppReplyDetail> getList(String appreplyuid);
	
	/**
	 * 根据答辩表uid和开始状态得到AppReplyDetail对象
	 * @param appreplyuid
	 * @param replystagefrom
	 * @return
	 * @author liubin
	 * @createtime 2016-11-12下午5:49:20
	 * @return AppReplyDetail
	 */
	AppReplyDetail getOne(String appreplyuid, EReplyStage replystagefrom);
}
