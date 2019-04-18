package com.supergenius.xo.enterpriser.service;

import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.enterpriser.entity.Announcement;

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
	 * @author XueZhenYong
	 * @Datetime 2018年2月9日下午3:16:44
	 */
	boolean update(EStatus eStatus, String ids);

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

}
