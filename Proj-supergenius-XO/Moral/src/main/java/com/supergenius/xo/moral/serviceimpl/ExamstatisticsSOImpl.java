package com.supergenius.xo.moral.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.moral.moral.dao.ExamstatisticsDao;

import com.supergenius.xo.moral.constants.MapperDict;
import com.supergenius.xo.moral.entity.Examstatistics;
import com.supergenius.xo.moral.enums.EChapter;
import com.supergenius.xo.moral.enums.EQst;
import com.supergenius.xo.moral.service.ExamstatisticsSO;

/**
 * 考试结果类SO实现
 * 
 * @author LiJiacheng
 */
@Service
public class ExamstatisticsSOImpl extends BaseSOImpl<Examstatistics> implements ExamstatisticsSO {

	@Autowired
	ExamstatisticsDao dao;

	@Override
	protected BaseDao<Examstatistics> getDao() {
		return dao;
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.moral.service.ExamstatisticsSO#getList(java.lang.String, com.supergenius.xo.moral.enums.EChapter)
	 */
	@Override
	public Examstatistics getOne(String useruid, EChapter chapter, EQst type) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, useruid);
		map.put(MapperDict.chapter, chapter);
		if (type != null) {
			map.put(MapperDict.type, type);
		}
		return dao.getOne(map);
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.moral.service.ExamstatisticsSO#getList(java.lang.String)
	 */
	@Override
	public List<Examstatistics> getList(String useruid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, useruid);
		return dao.getList(map);
	}

}
