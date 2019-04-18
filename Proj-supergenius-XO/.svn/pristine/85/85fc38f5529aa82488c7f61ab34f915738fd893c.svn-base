package com.supergenius.xo.life.service;

import java.util.List;
import java.util.Map;

import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.life.entity.Comments;
import com.supergenius.xo.life.enums.EComment;
import com.supergenius.xo.life.enums.ELifeChannel;

/**
 * 评论SO
 * @author ChenQi
 * @date 2018年5月9日17:03:02
 */
public interface CommentsSO extends BaseSO<Comments>{
	
	/**
	 * 根据搜索条件得到count
	 * 
	 * @param map
	 * @return
	 * @author ChenQi
	 * @create 2018年5月9日17:03:08
	 */
	int searchCount(Map<String, Object> map);

	/**
	 * 根据搜索条件得到List
	 * 
	 * @param map
	 * @return
	 * @author ChenQi
	 * @create 2018年5月9日17:03:29
	 */
	List<Comments> search(Map<String, Object> map);

	/**
	 * 获得一级评论List
	 * 
	 * @param map
	 * @return
	 * @author ChenQi
	 * @create 2018年5月9日17:03:29
	 */
	List<Comments> getCommentList(EComment eChannel, String fromuid, int pagesize, Integer pagenum);

	/**
	 * 获得二级评论List
	 * 
	 * @param map
	 * @return
	 * @author ChenQi
	 * @create 2018年5月9日17:03:29
	 */
	List<Comments> getSecondList(EComment eChannel, String firstuid, int pagesize, Integer pagenum);

	/**
	 * 获得会员所有的赞
	 * 
	 * @param map
	 * @return
	 * @author ChenQi
	 * @create 2018年5月9日17:03:29
	 */
	List<String> getList(String useruid, EComment echannel);

	/**
	 * 是否已点赞
	 * 
	 * @param fromuid
	 * @param fromuseruid
	 * @param channel
	 * @return
	 */
	boolean isNotPrized(String fromuid, String fromuseruid, ELifeChannel channel);

	/**
	 * 删除赞
	 * 
	 * @param fromuseroid
	 * @param fromuid
	 * @param channel
	 */
	boolean deleteByPraise(String fromuseruid, String fromuid, ELifeChannel channel);
	
	/**
	 * 获取评论和点赞的数量
	 * 
	 * @param type
	 * @return
	 * @author ChenQi
	 * @create 2018年5月9日17:03:29
	 */
	Integer getCountByType(EComment type);
	
	/**
	 * 获取收藏的人数
	 * @return
	 * @author ChenQi
	 * @date 2018年5月9日17:03:29
	 */
	int getCountByfromuseruid();
}
