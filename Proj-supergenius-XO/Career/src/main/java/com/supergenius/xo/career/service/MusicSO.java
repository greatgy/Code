package com.supergenius.xo.career.service;

import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.career.entity.Music;

/**
 * 背景音乐so
 * @author ChenQi
 * @date 2017年12月27日19:22:00
 */
public interface MusicSO extends BaseSO<Music> {
	
	/**
	 * 根据状态返回数量
	 * @author ChenQi
	 * @date 2017年12月27日19:22:09
	 * @return int
	 */
	int getCount(EStatus status);
	
}
