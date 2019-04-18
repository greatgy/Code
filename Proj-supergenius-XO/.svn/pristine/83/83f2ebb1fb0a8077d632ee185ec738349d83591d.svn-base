package com.supergenius.xo.tpi.service;

import java.util.List;

import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.tpi.entity.Notice;
import com.supergenius.xo.tpi.enums.ENoticeChannel;
import com.supergenius.xo.tpi.enums.ENoticeType;

/**
 * 招聘信息
 * @author liushaomin
 */
public interface NoticeSO extends BaseSO<Notice>{

	/**
	 * 删除
	 * @param ids
	 * @return
	 */
	boolean deleteByUids(String[] ids);

	/**
	 * 更新状态
	 * @param eStatus
	 * @param ids
	 * @return
	 */
	boolean update(EStatus eStatus, String[] ids);
	
	/**
	 * 根据下述条件获取列表信息
	 * @param fromuid
	 * @param type
	 * @param channel
	 * @return
	 * @author ShangJianguo
	 */
	List<Notice> getList(String fromuid, ENoticeType type, ENoticeChannel channel);

}
