package com.supergenius.xo.sudokuapi.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.sudokuapi.dao.PuzzlesDao;
import com.supergenius.xo.sudokuapi.entity.Puzzles;
import com.supergenius.xo.sudokuapi.service.PuzzlesSO;

/**
 * 游戏SO实现
 * 
 * @author ChenQi
 */
@Service
public class PuzzlesSOImpl extends BaseSOImpl<Puzzles> implements PuzzlesSO {

	@Autowired
	PuzzlesDao puzzlesDao;

	@Override
	protected BaseDao<Puzzles> getDao() {
		return puzzlesDao;
	}

	/**
	 * 根据难度获得随机一道题
	 * 
	 * @param account
	 * @return
	 * @author ChenQi
	 */
	@Override
	public Puzzles getRandomPuzzlesByLevel(String level) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.level, level);
		map.put(BaseMapperDict.startIndex, (int)(Math.random() * (puzzlesDao.getCount(map) - 1)));
		map.put(BaseMapperDict.pageSize, 1);
		List<Puzzles> list = puzzlesDao.getList(map);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
}
