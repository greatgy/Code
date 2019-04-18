package com.supergenius.xo.career.service;

import java.util.List;
import java.util.Map;

import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.career.entity.Tease;
import com.supergenius.xo.career.enums.EComment;
import com.supergenius.xo.common.enums.EChannel;

/**
 * 评论so
 * 
 * @author xuzhixiang
 * @date 2017年9月19日09:50:42
 */
public interface TeaseSO extends BaseSO<Tease> {

	/**
	 * 根据搜索条件得到count
	 * 
	 * @param map
	 * @return
	 * @author xuzhixiang
	 * @create 2017年9月19日09:50:49
	 */
	int searchCount(Map<String, Object> map);

	/**
	 * 根据搜索条件得到List
	 * 
	 * @param map
	 * @return
	 * @author xuzhixiang
	 * @create 2017年9月19日09:50:53
	 */
	List<Tease> search(Map<String, Object> map);

	/**
	 * 获得二级评论List
	 * 
	 * @param map
	 * @return
	 * @author xuzhixiang
	 * @create 2017年8月28日17:55:45
	 */
	List<Tease> getSecondList(EComment type, String firstuid, int pagesize, Integer pagenum, EChannel channel);

	/**
	 * 获取评论和点赞的数量
	 * 
	 * @param type
	 * @return
	 * @author xuzhixiang
	 * @create 2017年9月5日17:40:55
	 */
	Integer getCountByType(EComment type);
	
	/**
	 * 获取参与点赞和发表鬼话或吐槽的人数
	 * @return
	 * @author xuzhixiang
	 * @date 2017年9月5日18:18:27
	 */
	int getCountByfromuseruid(EChannel channel);
	
	/**
	 * 删除赞
	 * 
	 * @param fromuseroid
	 * @param fromuid
	 * @param channel
	 */
	boolean deleteByPraise(String fromuseruid, String touid, EChannel channel);
	
	/**
	 * 获得一级评论List
	 * 
	 * @param map
	 * @return
	 * @author ChenQi
	 * @create 2017年11月14日18:26:15
	 */
	List<Tease> getCommentList(EComment type, int pagesize, Integer pagenum, EChannel channel);
	
	/**
	 * 是否已点赞
	 * 
	 * @param fromuid
	 * @param fromuseruid
	 * @param channel
	 * @return
	 */
	boolean isNotPrized(String fromuid, String fromuseruid, EChannel channel);
	
}
