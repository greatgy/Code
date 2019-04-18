package com.supergenius.xo.user.serviceimpl;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.user.dao.ScoreDao;
import com.supergenius.xo.user.entity.Score;
import com.supergenius.xo.user.enums.EScore;
import com.supergenius.xo.user.service.ScoreSO;

/**
 * 积分
 * @author liubin
 * @date 2016-7-18 下午4:56:24
 */
@Service("userScoreSOImpl")
public class ScoreSOImpl extends BaseSOImpl<Score> implements ScoreSO {

	@Autowired
	ScoreDao dao;

	@Override
	protected BaseDao<Score> getDao() {
		return dao;
	}

	@Override
	public boolean updateTotal(String useruid, int total, EScore type) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, useruid);
		map.put(MapperDict.total, total);
		map.put(MapperDict.type, type);
		return dao.updateFields(map);
	}

	@Override
	public int getTotalByTypes(String useruid, EScore... types) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, useruid);
		if (types.length > 0)
			map.put(MapperDict.types, Arrays.asList(types));
		Integer total = dao.getScoreTotal(map);
		if (total == null) {
			return 0;
		}
		return total;
	}

	@Override
	public Score getOne(String useruid, EScore type) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, useruid);
		map.put(MapperDict.type, type);
		return dao.getOne(map);
	}

	@Override
	public int getTotal(String useruid) {
		return getTotalByTypes(useruid, EScore.getAddScoreTypes());
	}
}
