package com.supergenius.xo.tpi.service;

import java.util.List;

import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.tpi.entity.Link;
import com.supergenius.xo.tpi.entity.Notice;
import com.supergenius.xo.tpi.entity.Team;
import com.supergenius.xo.user.entity.User;

/**
 * 团队so
 * @author liushaomin
 */
public interface TeamSO extends BaseSO<Team>{
	
	/**
	 * @param eStatus
	 * @param ids
	 * @return
	 */
	boolean update(EStatus eStatus, String[] ids);

	/**
	 * @param item
	 */
	boolean deleteByUids(String[] ids);

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
	 * @param b
	 * @return
	 */
	boolean setRecommend(String[] ids, boolean isrecommend);

	/**
	 * 根据类别和是否置顶获取一条数据
	 * @param typeuid
	 * @param istop
	 * @return Team
	 * @author ShangJianguo
	 */
	Team getOne(String typeuid, boolean istop);
	
	/**
	 * 获取该分类下的数量
	 * @param typeuid
	 * @return
	 * @author ShangJianguo
	 */
	int getCount(String typeuid);
	
	/**
	 * 根据类别获取分页数据
	 * @param typeuid
	 * @param pager
	 * @return
	 * @author ShangJianguo
	 */
	List<Team> getList(String typeuid, Pager pager);

	/**
	 * 获取所有的ids内的数据
	 * @param ids
	 * @return
	 * @author ShangJianguo
	 */
	List<Team> getList(List<String> ids);
	
	/**
	 * 得到推荐到首页的数据
	 * @param pager
	 * @return
	 * @modifier ShangJianguo 修改注释
	 */
	List<Team> getListByRecommend(Pager pager);

	/**
	 * 根据创建人uid得到团队
	 * @param useruid
	 * @author liushaomin
	 */
	Team getUseruid(String useruid);
	
	/**
	 * 添加一条资金需求
	 * @param teamuid
	 * @param fundneed
	 * @return
	 * @author ShangJianguo
	 */
	boolean addFundneed(String teamuid, Notice fundneed);
	
	/**
	 * 删除一条资金需求
	 * @param teamuid
	 * @param funduid
	 * @return
	 * @author ShangJianguo
	 */
	boolean deleteFundneeds(String teamuid, String funduid);
	
	/**
	 * 更新一个字段，主要是用来进行内嵌文档或者值为对象的处理
	 * @param teamuid
	 * @param initmemuids
	 * @return
	 * @author ShangJianguo
	 */
	boolean updateField(String teamuid, List<String> initmemuids);
	
	/**
	 * 获取一个用户加入的团队列表
	 * @param uid
	 * @return 
	 * @author LiuXiaoke
	 */
	List<Team> getJoinedList(String memberuid);
	
	/**
	 * 获取一个用户加入的团队列表，加分页
	 * @param memberuid
	 * @param pager
	 * @return
	 * @author liushaomin
	 */
	List<Team> getJoinedList(String memberuid, Pager pager);
	
	/**
	 * 统计一个用户加入团队的数量
	 * @param uid
	 * @return 
	 * @author LiuXiaoke
	 */
	int getJoinedCount(String memberuid);
	
	/**
	 * 保存编辑的团队信息
	 * @param team
	 * @param imgs
	 * @return
	 * @author ShangJianguo
	 */
	boolean update(Team team, String[] imgs);
	
	/**
	 * 添加团队信息
	 * @param team
	 * @param imgs
	 * @param achieve 团队成就
	 * @return
	 * @author ShangJianguo
	 */
	boolean add(Team team, String[] imgs, String[] achieve, User user);
	
	/**
	 * 添加团队成就
	 * @param teamuid
	 * @param achieve
	 * @return
	 * @author ShangJianguo
	 */
	boolean addAchieve(String teamuid, Link achieve);
	
	/**
	 * 删除团队成就
	 * @param teamuid
	 * @param achieveuid
	 * @return
	 * @author ShangJianguo
	 */
	boolean deleteAchieve(String teamuid, String achieveuid);

	/**
	 * 更新团队内成员uid
	 * @param uid
	 * @param useruids
	 * @author liushaomin
	 */
	boolean updateMember(String uid, List<String> useruids);
	
	/**
	 * 删除团队成员
	 * @param teamuid
	 * @param status 若为0，则是未同意的数据，若为1，则是已经同意的数据
	 * @param memuid
	 * @return
	 * @author ShangJianguo
	 */
	boolean delMember(String teamuid, int status, String memuid);

	/**
	 * 分页获取置顶的数据
	 * @param istop
	 * @param pager 若为空，则获取所有的数据
	 * @return
	 * @author ShangJianguo
	 */
	List<Team> getList(Boolean istop, Pager pager);

	/**
	 * 分页获取置顶的数据
	 * @param exclude 不包括的uid
	 * @param istop
	 * @param pager  若为空，则获取所有的数据
	 * @return
	 * @author ShangJianguo
	 */
	List<Team> getList(List<String> exclude, Boolean istop, Pager pager);
	
	/**
	 * 根据如下参数获取数据
	 * @param typeuid
	 * @param istop
	 * @param pager
	 * @return
	 * @author ShangJianguo
	 */
	List<Team> getList(String typeuid, Boolean istop, Pager pager);
}
