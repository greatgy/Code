package com.supergenius.xo.life.service;

import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.life.entity.Video;

/**
 * 視頻SO
 *@Author:JiaShitao
 *@Date:2018年5月7日下午6:04:30
 */
public interface VideoSO extends BaseSO<Video> {

	/**
	 * 冻结或解冻
	 * 
	 * @author YangGuang
	 * @date 2018年5月11日10:11:31
	 * @return boolean
	 */
	boolean updateStatusByUid(String uid);
}