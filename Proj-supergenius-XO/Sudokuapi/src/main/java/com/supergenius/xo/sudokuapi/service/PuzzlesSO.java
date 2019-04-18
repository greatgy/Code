package com.supergenius.xo.sudokuapi.service;

import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.sudokuapi.entity.Puzzles;

/**
 * 用户SO
 * 
 * @author LiJiacheng
 */
public interface PuzzlesSO extends BaseSO<Puzzles> {

	/**
	 * 根据难度获取随机一道题
	 * 
	 * @param level
	 * @return
	 * @author ChenQi
	 */
	Puzzles getRandomPuzzlesByLevel(String level);
}
