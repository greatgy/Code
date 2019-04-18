package com.supergenius.xo.life.service;

import java.util.List;
import java.util.Map;

import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.life.entity.Essay;
import com.supergenius.xo.life.enums.EComment;
import com.supergenius.xo.life.enums.ELifeChannel;

/**
 * 评论so
 * 
 * @author ChenQi
 * @date 2018年5月9日17:16:04
 */
public interface EssaySO extends BaseSO<Essay> {

	/**
	 * 根据搜索条件得到count
	 * 
	 * @param map
	 * @return
	 * @author ChenQi
	 * @create 2018年5月9日17:16:04
	 */
	int searchCount(Map<String, Object> map);

	/**
	 * 根据搜索条件得到List
	 * 
	 * @param map
	 * @return
	 * @author ChenQi
	 * @create 2018年5月9日17:16:04
	 */
	List<Essay> search(Map<String, Object> map);

	/**
	 * 获得二级评论List
	 * 
	 * @param map
	 * @return
	 * @author ChenQi
	 * @create 2018年5月9日17:16:04
	 */
	List<Essay> getSecondList(EComment type, String firstuid, int pagesize, Integer pagenum);

	/**
	 * 获取评论和点赞的数量
	 * 
	 * @param type
	 * @return
	 * @author ChenQi
	 * @create 2018年5月9日17:16:04
	 */
	Integer getCountByType(EComment type);
	
	/**
	 * 获取参与动态的人数
	 * @return
	 * @author ChenQi
	 * @date 2018年5月9日17:16:04
	 */
	int getCountByfromuseruid();
	
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
	 * @create 2018年5月9日17:16:04
	 */
	List<Essay> getCommentList(EComment type, int pagesize, Integer pagenum, long cid);
	
	/**
	 * 是否已点赞
	 * 
	 * @param fromuid
	 * @param fromuseruid
	 * @param channel
	 * @return
	 */
	boolean isNotPrized(String fromuid, String fromuseruid, ELifeChannel channel);
	
}