package com.supergenius.xo.enterpriser.service;

import java.util.List;
import java.util.Map;

import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.enterpriser.entity.Comments;
import com.supergenius.xo.enterpriser.enums.EComment;

/**
 * commentsSO
 * @author loupengyu
 * @date 2018年1月29日11:10:48
 */
public interface CommentsSO extends BaseSO<Comments>{

	/**
	 * 根据搜索条件得到count
	 * 
	 * @param map
	 * @return
	 * @author loupengyu
     * @date 2018年1月30日16:54:31
	 */
	int searchCount(Map<String, Object> map);

	/**
	 * 根据搜索条件得到List
	 * 
	 * @param map
	 * @return
	 * @author loupengyu
     * @date 2018年1月30日16:54:56
	 */
	List<Comments> search(Map<String, Object> map);

	/**
	 * 获得一级评论List
	 * 
	 * @param map
	 * @return
	 * @author loupengyu
     * @date 2018年1月30日16:54:31	 
     */
	List<Comments> getCommentList(EComment eChannel, String fromuid, int pagesize, Integer pagenum);

	/**
	 * 获得二级评论List
	 * 
	 * @param map
	 * @return
	 * @author loupengyu
     * @date 2018年1月30日16:55:52
	 */
	List<Comments> getSecondList(EComment eChannel, String firstuid, int pagesize, Integer pagenum);

	/**
	 * 获得会员所有的赞
	 * 
	 * @param map
	 * @return
	 * @author loupengyu
     * @date 2018年1月30日16:56:12
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
	 * @author loupengyu
     * @date 2018年1月30日16:56:40
	 */
	Integer getCountByType(EComment type);
	
	/**
	 * 获取收藏的人数
	 * @return
	 * @author loupengyu
     * @date 2018年1月30日16:56:50
	 */
	int getCountByfromuseruid();
	
}
