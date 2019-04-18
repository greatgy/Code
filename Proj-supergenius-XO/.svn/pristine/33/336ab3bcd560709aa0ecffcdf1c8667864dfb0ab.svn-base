package com.supergenius.xo.startup.service;

import java.util.List;
import java.util.Map;

import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.startup.entity.Comments;
import com.supergenius.xo.startup.enums.EComment;

/**
 * 评论so
 * 
 * @author ChenQi
 * @date 2017-8-23 11:13:45
 */
public interface CommentsSO extends BaseSO<Comments> {

	/**
	 * 冻结或解冻文章
	 * 
	 * @author ChenQi
	 * @date 2017年8月24日18:01:24
	 * @return boolean
	 */
	boolean updateStatusByUid(String adminUid, String uid);

	/**
	 * 根据搜索条件得到count
	 * 
	 * @param map
	 * @return
	 * @author ChenQi
	 * @create 2017年8月25日16:28:40
	 */
	int searchCount(Map<String, Object> map);

	/**
	 * 根据搜索条件得到List
	 * 
	 * @param map
	 * @return
	 * @author ChenQi
	 * @create 2017年8月25日16:30:52
	 */
	List<Comments> search(Map<String, Object> map);

	/**
	 * 获得一级评论List
	 * 
	 * @param map
	 * @return
	 * @author yangguang
	 * @create 2017年8月28日16:16:42
	 */
	List<Comments> getCommentList(EComment eChannel, String fromuid, int pagesize, Integer pagenum);

	/**
	 * 获得二级评论List
	 * 
	 * @param map
	 * @return
	 * @author yangguang
	 * @create 2017年8月28日17:55:45
	 */
	List<Comments> getSecondList(EComment eChannel, String firstuid, int pagesize, Integer pagenum);

	/**
	 * 获得会员所有的赞
	 * 
	 * @param map
	 * @return
	 * @author yangguang
	 * @create 2017年8月28日16:43:29
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
	boolean isNotPrized(String fromuid, String fromuseruid, EChannel channel);

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
	 * @create 2017年9月5日17:40:55
	 */
	Integer getCountByType(EComment type);
	
	/**
	 * 获取收藏的人数
	 * @return
	 * @author ChenQi
	 * @date 2017年9月5日18:18:27
	 */
	int getCountByfromuseruid();
}
