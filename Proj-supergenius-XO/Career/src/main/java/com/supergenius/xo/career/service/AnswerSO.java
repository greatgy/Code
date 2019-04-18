package com.supergenius.xo.career.service;

import java.util.List;
import java.util.Map;

import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.career.entity.Answer;
import com.supergenius.xo.career.enums.EComment;
import com.supergenius.xo.common.enums.EChannel;

/**
 * 回答so
 * 
 * @author ChenQi
 * @date 2017年11月13日17:32:25
 */
public interface AnswerSO extends BaseSO<Answer> {

	/**
	 * 根据搜索条件得到count
	 * 
	 * @param map
	 * @return
	 * @author ChenQi
	 * @create 2017年11月14日18:26:08
	 */
	int searchCount(Map<String, Object> map);

	/**
	 * 根据搜索条件得到List
	 * 
	 * @param map
	 * @return
	 * @author ChenQi
	 * @create 2017年11月14日18:26:12
	 */
	List<Answer> search(Map<String, Object> map);

	/**
	 * 获得一级评论List
	 * 
	 * @param map
	 * @return
	 * @author ChenQi
	 * @create 2017年11月14日18:26:15
	 */
	List<Answer> getCommentList(EComment eChannel, String fromuid, int pagesize, Integer pagenum);

	/**
	 * 获得二级评论List
	 * 
	 * @param map
	 * @return
	 * @author ChenQi
	 * @create 2017年11月14日18:26:17
	 */
	List<Answer> getSecondList(EComment eChannel, String firstuid, int pagesize, Integer pagenum);

	/**
	 * 获得会员所有的赞
	 * 
	 * @param map
	 * @return
	 * @author ChenQi
	 * @create 2017年11月14日18:26:21
	 */
	List<String> getList(String useruid, EComment echannel);

	/**
	 * 是否已点赞
	 * 
	 * @param touid
	 * @param fromuseruid
	 * @param channel
	 * @return
	 */
	boolean isNotPrized(String touid, String fromuseruid, EChannel channel);

	/**
	 * 删除赞
	 * 
	 * @param fromuseroid
	 * @param fromuid
	 * @param channel
	 */
	boolean deleteByPraise(String fromuseruid, String fromuid, EChannel channel);
	
	/**
	 * 获取评论和点赞的数量
	 * 
	 * @param type
	 * @return
	 * @author ChenQi
	 * @create 2017年11月14日18:26:27
	 */
	Integer getCountByType(EComment type);
	
	/**
	 * 获取参与的人数
	 * @return
	 * @author ChenQi
	 * @date 2017年11月14日18:26:31
	 */
	int getCountByfromuseruid();
}
