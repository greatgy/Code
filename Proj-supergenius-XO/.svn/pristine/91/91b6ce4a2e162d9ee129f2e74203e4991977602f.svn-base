package com.supergenius.xo.gupage.service;

import java.util.Map;

import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.gupage.entity.Photo;

/**
 * 图片so
 * 
 * @author 杨光
 * @date 2018年1月10日10:46:05
 */
public interface PhotoSO extends BaseSO<Photo> {

	/**
	 * 设置是否推荐
	 * @param ids
	 * @param isrecommend
	 * @return
	 */
	boolean setRecommend(String[] ids, boolean isrecommend);
	
	/**
	 * 获取上一张图片与下一张图片
	 * @param photo
	 * @return
	 * @author XieMing
	 * 2017年2月10日 上午11:57:20
	 */
	Map<String, Photo> getLastNext(Photo photo);

}
