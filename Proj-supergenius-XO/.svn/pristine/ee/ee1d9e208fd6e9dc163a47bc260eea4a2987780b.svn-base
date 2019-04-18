package com.supergenius.xo.moral.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.moral.moral.dao.ScoreDetailDao;
import com.supergenius.xo.moral.constants.MapperDict;
import com.supergenius.xo.moral.entity.ScoreDetail;
import com.supergenius.xo.moral.enums.EScoreDetail;
import com.supergenius.xo.moral.service.ScoreDetailSO;

/**
 *  积分明显so的实现
 * @author liushaomin
 */
@Service("moralScoreDetailSOImpl")
public class ScoreDetailSOImpl extends BaseSOImpl<ScoreDetail> implements ScoreDetailSO{

	@Autowired
	ScoreDetailDao dao;
	
	/* (non-Javadoc)
	 * @see com.genius.xo.base.serviceimpl.BaseSOImpl#getDao()
	 */
	@Override
	protected BaseDao<ScoreDetail> getDao() {
		return dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.supergenius.xo.moral.service.ScoreDetailSO#getList(java.lang.String, com.supergenius.xo.moral.enums.EScoreDetail, java.lang.String)
	 */
	@Override
	public List<ScoreDetail> getList(String useruid, EScoreDetail type, String today) {
		Map<String, Object> map = new HashMap<String, Object>();
		DateTime endTime = DateTime.parse(today);
		map.put(MapperDict.useruid, useruid);
		map.put(MapperDict.type, type);
		map.put(MapperDict.createtime + MapperDict.suffix_greater_key, endTime);
		return dao.getList(map);
	}

}
