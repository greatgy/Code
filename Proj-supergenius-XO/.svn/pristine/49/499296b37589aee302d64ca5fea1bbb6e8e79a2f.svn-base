package com.supergenius.xo.sudokuapi.serviceimpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.sudokuapi.dao.GamesDao;
import com.supergenius.xo.sudokuapi.entity.Games;
import com.supergenius.xo.sudokuapi.service.GamesSO;

/**
 * 用户SO实现
 * 
 * @author LiJiacheng
 */
@Service
public class GamesSOImpl extends BaseSOImpl<Games> implements GamesSO {

	@Autowired
	GamesDao gamesDao;

	@Override
	protected BaseDao<Games> getDao() {
		return gamesDao;
	}

	@Override
	public boolean update(String creator, String newAccount) {
		Map<String, Object> where = getParamMap(true);
		Map<String, Object> fields = new HashMap<>();
		where.put(MapperDict.creator, creator);
		fields.put(MapperDict.creator, newAccount);
		return gamesDao.update(where, fields);
	}
}
