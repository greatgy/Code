package com.supergenius.xo.moral.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.core.base.utils.MathUtil;
import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.genius.xo.mongodb.MongoDict;
import com.supergenius.moral.moral.dao.QuestionDao;

import com.supergenius.xo.moral.constants.MapperDict;
import com.supergenius.xo.moral.entity.Question;
import com.supergenius.xo.moral.enums.EChapter;
import com.supergenius.xo.moral.enums.EQst;
import com.supergenius.xo.moral.service.QuestionSO;

/**
 * 题库SO实现类
 * 
 * @author LiJiacheng
 */
@Service
public class QuestionSOImpl extends BaseSOImpl<Question> implements QuestionSO {

	@Autowired
	QuestionDao dao;

	@Override
	protected BaseDao<Question> getDao() {
		return dao;
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.moral.service.QuestionSO#update(com.genius.model.base.enums.EStatus, java.lang.String[])
	 */
	@Override
	public boolean update(EStatus eStatus, String[] ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (String id : ids) {
			map.put(MapperDict.status, eStatus);
			map.put(MapperDict.uid, id);
			dao.updateFields(map);
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.moral.service.QuestionSO#getCount(com.supergenius.xo.moral.enums.EChapter)
	 */
	@Override
	public Object getCount(EChapter chapter) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.chapter, chapter);
		return dao.getCount(map);
	}
	
	/* (non-Javadoc)
	 * @see com.supergenius.xo.moral.service.QuestionSO#getCount(com.supergenius.xo.moral.enums.EChapter, com.supergenius.xo.moral.enums.EQst)
	 */
	@Override
	public int getCount(EChapter chapter, EQst type) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.chapter, chapter);
		map.put(MapperDict.type, type);
		return dao.getCount(map);
	}


	/* (non-Javadoc)
	 * @see com.supergenius.xo.moral.service.QuestionSO#getList(com.supergenius.xo.moral.enums.EChapter, com.supergenius.xo.moral.enums.EQst, java.util.List)
	 */
	@Override
	public List<Question> getListIn(Pager pager, EChapter chapter, EQst type, List<String> qstuids) {
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.chapter, chapter);
		map.put(MapperDict.type, type);
		map.put(MapperDict.uid + MapperDict.suffix_in_key, qstuids);
		return dao.getList(map);
	}
	
	/* (non-Javadoc)
	 * @see com.supergenius.xo.moral.service.QuestionSO#getRandomQstsIn(com.supergenius.xo.moral.enums.EQst, com.supergenius.xo.moral.enums.EChapter, com.genius.model.base.entity.Pager)
	 */
	@Override
	public List<Question> getRandomQstsIn(EQst type, EChapter chapter, List<String> qstuids, Pager pager) {
		Map<String, Object> map = getParamMap(pager);
		double d1 = 0.9;
		if (type.equals(EQst.single)) {
			d1 = MathUtil.div(10, qstuids.size(), 2);
		}else {
			d1 = MathUtil.div(1, qstuids.size(), 2);
		}
		map.put(MongoDict.$where, "function(){if(Math.random()< " + d1 + "){return true} else{return false}}");
		map.put(MapperDict.type, type);
		map.put(MapperDict.chapter, chapter);
		map.put(MapperDict.uid + MapperDict.suffix_in_key, qstuids);
		return dao.getList(map);
	}
	
	/* (non-Javadoc)
	 * @see com.supergenius.xo.moral.service.QuestionSO#getListNoin(com.supergenius.xo.moral.enums.EChapter, com.supergenius.xo.moral.enums.EQst, java.util.List)
	 */
	@Override
	public List<Question> getListNotin(Pager pager, EChapter chapter, EQst type, List<String> qstuids) {
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.chapter, chapter);
		map.put(MapperDict.type, type);
		map.put(MapperDict.uid + MapperDict.suffix_nin_key, qstuids);
		return dao.getList(map);
	}
	
	/* (non-Javadoc)
	 * @see com.supergenius.xo.moral.service.QuestionSO#getRandomQstsNotin(com.supergenius.xo.moral.enums.EQst, com.supergenius.xo.moral.enums.EChapter, java.util.List, com.genius.model.base.entity.Pager)
	 */
	@Override
	public List<Question> getRandomQstsNotin(EQst type, EChapter chapter, List<String> qstuids, Pager pager) {
		Map<String, Object> map = getParamMap(pager);
		double d1 = 0.9;
		if(chapter != null){
			map.put(MapperDict.chapter, chapter);
			Map<String, Object> countmap = getParamMap(pager);
			countmap.put(MapperDict.type, type);
			countmap.put(MapperDict.chapter, chapter);
			int count = dao.getCount(countmap);
			if (type.equals(EQst.single)) {
				d1 = MathUtil.div(10, count - qstuids.size(), 2);
			}else {
				d1 = MathUtil.div(1, count - qstuids.size(), 2);
			}
		}else {
			Map<String, Object> countmap = getParamMap(pager);
			countmap.put(MapperDict.type, type);
			int count = dao.getCount(countmap);
			if (type.equals(EQst.single)) {
				d1 = MathUtil.div(60, count - qstuids.size(), 2);
			}else {
				d1 = MathUtil.div(8, count - qstuids.size(), 2);
			}
		}
		map.put(MongoDict.$where, "function(){if(Math.random()<" + d1 + "){return true} else{return false}}");
		map.put(MapperDict.type, type);
		map.put(MapperDict.uid + MapperDict.suffix_nin_key, qstuids);
		return dao.getList(map);
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.moral.service.QuestionSO#getListRandom(com.supergenius.xo.moral.enums.EQst, com.genius.model.base.entity.Pager)
	 */
	@Override
	public List<Question> getListRandom(EQst type, EChapter chapter, Pager pager) {
		Map<String, Object> map = getParamMap(pager);
		double d1 = 0.9;
		if (chapter != null) {
			map.put(MapperDict.chapter, chapter);
			Map<String, Object> countmap = getParamMap(pager);
			countmap.put(MapperDict.type, type);
			countmap.put(MapperDict.chapter, chapter);
			int count = dao.getCount(countmap);
			if (type.equals(EQst.single)) {
				d1 = MathUtil.div(10, count > 0 ? count : 1, 2);
			}else {
				d1 = MathUtil.div(1, count > 0 ? count : 1, 2);
			}
		}else {
			Map<String, Object> countmap = getParamMap(pager);
			countmap.put(MapperDict.type, type);
			int count = dao.getCount(countmap);
			if (type.equals(EQst.single)) {
				d1 = MathUtil.div(60, count > 0 ? count : 1, 2);
			}else {
				d1 = MathUtil.div(8, count > 0 ? count : 1, 2);
			}
		}
		map.put(MongoDict.$where, "function(){if(Math.random()<" + d1 + "){return true} else{return false}}");
		map.put(MapperDict.type, type);
		return dao.getList(map);
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.moral.service.QuestionSO#getListInUid(java.util.List)
	 */
	@Override
	public List<Question> getListInUid(List<String> questionuids) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.uid + MapperDict.suffix_in_key, questionuids);
		return dao.getList(map);
	}

}
