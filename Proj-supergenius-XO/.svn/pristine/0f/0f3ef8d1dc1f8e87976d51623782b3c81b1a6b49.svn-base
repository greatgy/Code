package com.supergenius.xo.official.serviceimpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.official.constants.MapperDict;
import com.supergenius.xo.official.dao.VoteDao;
import com.supergenius.xo.official.entity.Vote;
import com.supergenius.xo.official.service.VoteSO;

/**
 * 投票
 * @author liushaomin
 */
@Service
public class VoteSOImpl extends BaseSOImpl<Vote> implements VoteSO{
	
	@Autowired
	VoteDao dao;

	@Override
	protected BaseDao<Vote> getDao() {
		return dao;
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.official.service.VoteSO#getLoginIP()
	 */
	@Override
	public Vote getLoginIP(String loginip) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.loginip, loginip);
		return dao.getOne(map);
	}

}
