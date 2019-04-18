package com.supergenius.xo.moralnews.service;

import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.moralnews.entity.Announcement;

/**
 * 公告so
 * 
 * @author JiaShitao
 * @date 2018年9月19日09:54:02
 */
public interface AnnouncementSO extends BaseSO<Announcement> {
	
	/**
	 * 冻结和解冻
	 * 
	 * @param eStatus
	 * @param ids
	 * @author tf
	 * @Datetime 2018年9月19日
	 */
	boolean update(EStatus eStatus, String ids);
	
	/**
	 * 设置公告置顶
	 * 
	 * @param id
	 * @param istop
	 * @return
	 * @author tf
	 */
	boolean setTop(String id, boolean istop);
	
	/**
	 * 检查是否有置顶的公告,返回true便可以置顶，返回false不可以置顶
	 * 
	 * @return
	 * @author tf
	 */
	boolean checkIsTop(String id);
}
