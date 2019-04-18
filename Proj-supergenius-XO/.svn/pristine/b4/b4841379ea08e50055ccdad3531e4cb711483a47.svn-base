package com.supergenius.xo.official.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.official.constants.MapperDict;
import com.supergenius.xo.official.dao.RecruitDao;
import com.supergenius.xo.official.entity.Recruit;
import com.supergenius.xo.official.service.RecruitSO;

/**
 * 招聘so实现
 * 
 * @author liushaomin
 */
@Service
public class RecruitSOImpl extends BaseSOImpl<Recruit> implements RecruitSO {

	@Autowired
	private RecruitDao dao;

	@Override
	protected RecruitDao getDao() {
		return dao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.official.service.RecruitSO#setTop(java.lang.String[], boolean)
	 * 
	 * @author: LiJiacheng
	 */

	@Override
	public boolean setTop(String[] uids, boolean istop) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.istop, istop);
		for (String item : uids) {
			map.put(BaseMapperDict.uid, item);
			dao.updateFields(map);
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.official.service.RecruitSO#status(java.lang.String[], boolean)
	 * 
	 * @author: LiJiacheng
	 */
	@Override
	public boolean status(String[] ids, EStatus status) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.status, status);
		for (String item : ids) {
			map.put(BaseMapperDict.uid, item);
			dao.updateFields(map);
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.official.service.RecruitSO#getTitleRecruitList(java.lang.String)
	 * 
	 * @author: LiJiacheng
	 */
	@Override
	public List<Recruit> getTitleRecruitList(String keyword, Pager pager) {
		Map<String, Object> map = getParamMap(pager);
		if (StringUtils.isNotEmpty(keyword)) {
			map.put(MapperDict.title + MapperDict.suffix_like_key, keyword);
		}
		return dao.getList(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.official.service.RecruitSO#getRecruitCount()
	 * 
	 * @author: LiJiacheng
	 */
	@Override
	public int getCount() {
		Map<String, Object> map = getParamMap();
		return dao.getCount(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.official.service.RecruitSO#getTitleRecruitCount()
	 * 
	 * @author: LiJiacheng
	 */
	@Override
	public int getCount(String keyword) {
		Map<String, Object> map = getParamMap();
		if (StringUtils.isNotEmpty(keyword)) {
			map.put(MapperDict.title + MapperDict.suffix_like_key, keyword);
		}
		return dao.getCount(map);
	}

}
