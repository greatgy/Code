package com.supergenius.xo.tpi.service;

import java.util.List;

import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.tpi.entity.Comment;
import com.supergenius.xo.tpi.enums.ECommentType;

/**
 * 并购平台评论SO
 * 
 * @author ShangJianguo
 */
public interface CommentSO extends BaseSO<Comment>{
	
	/**
	 * 修改状态
	 * @param uids
	 * @param status
	 * @return
	 * @author ShangJianguo
	 */
	public boolean setStatus(String[] uids, EStatus status);
	
	/**
	 * 根据以下条件获取Comment列表
	 * @param fromuid
	 * @param type
	 * @param pager
	 * @param order
	 * @return
	 * @author ShangJianguo
	 */
	List<Comment> getList(String fromuid, ECommentType type, Pager pager, String order);

	/**
	 * 判断会员是否赞过
	 * @param useruid
	 * @param fromuid
	 * @param channel
	 * @return
	 * @author liushaomin
	 */
	boolean isNotPrized(String useruid, String fromuid, EChannel channel);

	/**
	 * 删除赞
	 * @param fromuseroid
	 * @param fromuid
	 * @param channel
	 * @return
	 * @author liushaomin
	 */
	boolean deleteByPraise(String fromuseruid, String fromuid, EChannel channel);

	/**
	 * 获取评论的赞数
	 * @param fromuid
	 * @param comment
	 * @param prize
	 * @return
	 * @author liushaomin
	 */
	int getCount(String fromuid, EChannel comment, ECommentType prize);
}
