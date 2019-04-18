package com.supergenius.xo.moral.service;

import java.util.List;

import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.moral.entity.Announcement;

/**
 * 社区公告SO
 * 
 * @author LiJiacheng
 */
public interface AnnouncementSO extends BaseSO<Announcement> {

	/**
	 * 冻结和解冻
	 * 
	 * @param eStatus
	 * @param ids
	 * @return
	 * @author LiJiacheng
	 */
	boolean update(EStatus eStatus, String ids);

	/**
	 * 社区公告上移
	 * 
	 * @param ids
	 * @return
	 * @author LiJiacheng
	 */
	boolean announcement_up(String ids);

	/**
	 * 社区公告下移
	 * 
	 * @param ids
	 * @return
	 * @author LiJiacheng
	 */
	boolean announcement_down(String ids);

	/**
	 * 设置公告置顶
	 * 
	 * @param id
	 * @param istop
	 * @return
	 * @author LiJiacheng
	 */
	boolean setTop(String id, boolean istop);

	/**
	 * 检查是否有置顶的公告,返回true便可以置顶，返回false不可以置顶
	 * 
	 * @return
	 * @author LiJiacheng
	 */
	boolean checkIsTop();

	/**
	 * 获取公告列表
	 * 
	 * @return
	 * @author LiJiacheng
	 */
	List<Announcement> getAnnouncements(Pager pager);

}
