package com.supergenius.xo.enterpriser.service;

import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.enterpriser.entity.Video;

/**
 * 视频so
 * 
 * @author 杨光
 * @date 2017年9月19日09:54:02
 */
public interface VideoSO extends BaseSO<Video> {

	/**
	 * 设置是否推荐
	 * @param ids
	 * @param isrecommend
	 * @return
	 */
	boolean setRecommend(String[] ids, boolean isrecommend);
}
