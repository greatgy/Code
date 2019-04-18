package com.supergenius.xo.tpi.service;

import java.util.List;

import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.tpi.entity.Project;
import com.supergenius.xo.tpi.enums.EProjectChannel;
import com.supergenius.xo.tpi.enums.EProjectState;

/**
 * Project项目so
 * @author liushaomin
 */
public interface ProjectSO extends BaseSO<Project>{

	/**
	 * @param ids
	 * @return
	 */
	boolean deleteByUids(String[] ids);

	/**
	 * 更新状态（发布和取消发布）
	 * @param eStatus
	 * @param ids
	 * @return
	 */
	boolean update(EStatus eStatus, String[] ids);

	/**
	 * 设置置顶
	 * @param ids
	 * @param istop
	 * @return
	 */
	boolean setTop(String[] ids, boolean istop);

	/**
	 * 设置推荐
	 * @param ids
	 * @param isrecommend
	 * @return
	 */
	boolean setRecommend(String[] ids, boolean isrecommend);

	/**
	 * 设置公开
	 * @param ids
	 * @param ispublic
	 * @return
	 */
	boolean setPublic(String[] ids, boolean ispublic);

	/**
	 * 设置魂牵梦绕
	 * @param ids
	 * @param ischerished
	 * @return
	 */
	boolean setCherished(String[] ids, boolean ischerished);

	/**
	 * 更新进度-推荐机构推荐项目可特批免费
	 * @param state
	 * @param ids
	 * @return
	 */
	boolean update(EProjectState state, String[] ids);

	/**
	 * 获取置顶（根据推荐渠道）
	 * @param channel
	 * @return
	 */
	List<Project> getListByCherished(Pager pager);

	/**
	 * 获取推荐（根据推荐渠道）
	 * @param channel
	 * @return
	 */
	List<Project> getListByRecommend(Pager pager, EProjectChannel channel);

	/**
	 * 获取某个渠道的一个推荐项目
	 * @param channel
	 * @return
	 */
	Project getOneRecommend(EProjectChannel channel);

	/**
	 * 获取不同类别
	 * @param pager
	 * @param typeuid
	 * @return
	 */
	List<Project> getList(Pager pager, EProjectChannel channel, String typeuid);

	/**
	 * 获取每个推荐渠道的数量
	 * @param channel
	 * @return
	 */
	int getCount(EProjectChannel channel);
	
	/**
	 * 获取所有条件中所有uid的实例
	 * @param uids
	 * @return
	 * @author ShangJianguo
	 */
	List<Project> getList(List<String> uids);

	/**
	 * 某个类别的总数
	 * @param typeuid
	 * @return
	 * @author liushaomin
	 */
	int getCount(String typeuid, EProjectChannel channel);
	
	/**
	 * 分页获取一个Uid推荐的项目
	 * @param typeuid
	 * @return
	 * @author liushaomin
	 */
	List<Project> getList(String fromuid, Pager pager);
	
	/**
	 * 获取一个Uid推荐的项目
	 * @param typeuid
	 * @return
	 * @author liushaomin
	 */
	List<Project> getList(String fromuid);
	
	/**
	 * 获取置顶的项目
	 * @param istop
	 * @param num
	 * @return
	 * @author ShangJianguo
	 */
	List<Project> getList(EProjectChannel channel, boolean istop, int num);

	/**
	 * 获取某人推荐的项目数量
	 * @param fromuid
	 * @return
	 * @author ShangJianguo
	 */
	int getCount(String fromuid);

	/**
	 * 更新指定uid的project的status和State
	 * @param uid
	 * @param enable
	 * @param payed
	 * @return
	 * @author YuYingJie
	 */
	boolean update(String uid, EStatus status, EProjectState state);
	
}
