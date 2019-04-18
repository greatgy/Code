package com.supergenius.xo.startup.service;

import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.startup.entity.Music;

/**
 * 背景音乐so
 * @author 许志翔
 * @date 2017年8月9日14:55:32
 */
public interface MusicSO extends BaseSO<Music> {
	
	/**
	 * 根据状态返回数量
	 * @author 许志翔
	 * @date 2017年8月9日15:36:29
	 * @return int
	 */
	int getCount(EStatus status);
	
}
