package com.supergenius.xo.entrepreneur.service;

import java.util.List;
import java.util.Map;

import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.entrepreneur.enums.EComment;
import com.supergenius.xo.entrepreneur.enums.EEntrepreneurChannel;
import com.supergenius.xo.entrepreneur.entity.Comments;

/**
 * 评论SO
 * @author XueZhenYong
 * @date 2017年12月29日下午3:25:28
 */
public interface CommentsSO extends BaseSO<Comments>{

	/**
	 * 获得一级评论List
	 * 
	 * @param map
	 * @return
	 * @author xuzhixiang
	 * @create 2017年8月28日16:16:42
	 */
	List<Comments> getCommentList(EComment eChannel, String fromuid, int pagesize, Integer pagenum);

	/**
	 * 获得二级评论List
	 * 
	 * @param map
	 * @return
	 * @author xuzhixiang
	 * @create 2017年8月28日17:55:45
	 */
	List<Comments> getSecondList(EComment eChannel, String firstuid, int pagesize, Integer pagenum);

	/**
	 * 获得会员所有的赞
	 * 
	 * @param map
	 * @return
	 * @author xuzhixiang
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
	boolean isNotPrized(String fromuid, String fromuseruid, EEntrepreneurChannel channel);

	/**
	 * 删除赞
	 * 
	 * @param fromuseroid
	 * @param fromuid
	 * @param channel
	 */
	boolean deleteByPraise(String fromuseruid, String fromuid, EEntrepreneurChannel channel);
	
	
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
	 * 冻结或解冻文章
	 * 
	 * @author xuzhixiang
	 * @date 2017年9月19日09:50:45
	 * @return boolean
	 */
	boolean updateStatusByUid(String adminUid, String uid);
	
	/**
	 * 根据搜索条件得到List
	 * 
	 * @param map
	 * @return
	 * @author xuzhixiang
	 * @create 2017年9月19日09:50:53
	 */
	List<Comments> search(Map<String, Object> map);
	
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
	 * 获取收藏的人数
	 * @return
	 * @author xuzhixiang
	 * @date 2017年9月5日18:18:27
	 */
	int getCountByfromuseruid();
}