package com.supergenius.xo.sudokuapi.service;

import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.sudokuapi.entity.Games;

/**
 * 游戏SO
 * 
 * @author ChenQi
 */
public interface GamesSO extends BaseSO<Games> {

	/**
	 * 根据创建者获取games
	 * @param creator
	 * @return
	 * @CreateTime  2018年6月4日--下午6:04:02
	 * @Author  LiuBin
	 */
	boolean update(String creator, String newAccount);
}
