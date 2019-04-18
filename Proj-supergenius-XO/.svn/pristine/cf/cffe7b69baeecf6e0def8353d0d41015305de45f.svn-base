package com.supergenius.xo.user.serviceimpl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.user.dao.ScoreDao;
import com.supergenius.xo.user.dao.ScoreDetailDao;
import com.supergenius.xo.user.entity.Score;
import com.supergenius.xo.user.entity.ScoreDetail;
import com.supergenius.xo.user.enums.EScore;
import com.supergenius.xo.user.service.ScoreDetailSO;
import com.supergenius.xo.user.service.ScoreSO;

/**
 * 积分明细SOImpl
 * 
 * @author liubin
 * @createtime 2016-7-18 下午5:49:35
 */
@Service("userScoreDetailSOImpl")
public class ScoreDetailSOImpl extends BaseSOImpl<ScoreDetail> implements ScoreDetailSO {

	private static Logger log = LoggerFactory.getLogger(ScoreDetailSOImpl.class);
	@Autowired
	ScoreDetailDao dao;
	
	@Autowired
	ScoreDao scoredao;
	
	@Autowired
	ScoreSO scoreSO;

	@Override
	protected BaseDao<ScoreDetail> getDao() {
		return dao;
	}

	@Override
	public boolean add(ScoreDetail scoreDetail) {
		if (dao.insert(scoreDetail)){
			EScore type = scoreDetail.getType();
			String useruid = scoreDetail.getUseruid();
			Score score = scoreSO.getOne(useruid, type);
			int total = 0;
			if (score == null) {
				score = new Score();
				score.setUseruid(useruid);
				score.setTotal(total);
				score.setType(type);
				scoredao.insert(score);
			} else {
				total = score.getTotal();
			}
			if (type == EScore.scoreUpGrade)
				total -= scoreDetail.getScore();
			else 
				total += scoreDetail.getScore();
			if (scoreSO.updateTotal(scoreDetail.getUseruid(), total, type))
				return true;
			log.info("ScoreDetail insert success, but the total of Score type update failed. the ScoreDetailuid is:" + scoreDetail.getUid());
		}
		log.info("Failed to insert scoreDetail");
		return false;
	}

	@Override
	public ScoreDetail getOne(String refuid) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.refuid, refuid);
		return dao.getOne(map);
	}

}
