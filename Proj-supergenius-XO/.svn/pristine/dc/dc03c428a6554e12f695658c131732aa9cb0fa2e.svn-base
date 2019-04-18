package com.supergenius.xo.career.serviceimpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.genius.xo.baseadmin.service.AdminLogSO;
import com.supergenius.xo.career.dao.QuestionDao;
import com.supergenius.xo.career.entity.Question;
import com.supergenius.xo.career.service.QuestionSO;
import com.supergenius.xo.common.constants.MapperDict;

/**
 * 
 * @author ChenQi
 * @date 2017年6月20日12:02:08
 */
@Service("careerQuestionSOImpl")
public class QuestionSOImpl extends BaseSOImpl<Question> implements QuestionSO {
	
	@Autowired
	private QuestionDao dao;
	
	@Autowired
	private AdminLogSO adminlogSO;
	
	@Override
	protected BaseDao<Question> getDao() {
		return dao;
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.startup.service.QuestionSO#getCount(com.genius.core.base.mock.testenums.EStatus)
	 */
	@Override
	public int getCount(EStatus status) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put(MapperDict.status, status);
		return dao.getCount(map);
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.startup.service.QuestionSO#add(com.supergenius.xo.startup.entity.Question, com.genius.model.baseadmin.entity.AdminLog)
	 */
	@Override
	public boolean add(Question question, AdminLog adminLog) {
		return dao.insert(question) && adminlogSO.add(adminLog);
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.startup.service.QuestionSO#updateByFields(int)
	 */
	@Override
	public boolean updateFieldsByOrderIncr(int order) {
		Map<String,Object> map = getParamMap();
		map.put(MapperDict.order_incr, true);
		map.put(MapperDict.order_ge, order);
		return dao.updateByFields(map);
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.startup.service.QuestionSO#getOne(int)
	 */
	@Override
	public Question getOne(int order) {
		Map<String,Object> map = getParamMap();
		map.put(MapperDict.order, order);
		return dao.getOne(map);
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.startup.service.QuestionSO#updateFieldsByOrderDecr(int)
	 */
	@Override
	public boolean updateFieldsByOrderDecr(int order) {
		Map<String,Object> map = getParamMap();
		map.put(MapperDict.order_decr, true);
		map.put(MapperDict.order_ge, order);
		return dao.updateByFields(map);
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.startup.service.QuestionSO#update(com.supergenius.xo.startup.entity.Question, com.genius.model.baseadmin.entity.AdminLog)
	 */
	@Override
	public boolean update(Question question, AdminLog adminLog) {
		return dao.update(question) && adminlogSO.add(adminLog);
	}

}
