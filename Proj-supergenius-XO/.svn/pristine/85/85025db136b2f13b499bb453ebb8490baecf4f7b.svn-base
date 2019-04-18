package com.supergenius.xo.sudokuapi.serviceimpl;

import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.sudokuapi.dao.FeedbackDao;
import com.supergenius.xo.sudokuapi.entity.Feedbacks;
import com.supergenius.xo.sudokuapi.service.FeedbackSO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 反馈信息SO实现
 * @author Yongxuezhen
 */
@Service
public class FeedbackSOImpl extends BaseSOImpl<Feedbacks> implements FeedbackSO {


	@Autowired
	FeedbackDao dao;
	
	@Override
	protected BaseDao<Feedbacks> getDao() {
		return dao;
	}

	
}
