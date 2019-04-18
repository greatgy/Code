package com.supergenius.xo.career.serviceimpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.career.dao.MusicDao;
import com.supergenius.xo.career.entity.Music;
import com.supergenius.xo.career.service.MusicSO;

/**
 * 背景音乐SO实现
 * @author ChenQi
 * @date 2017年12月27日19:23:14
 */
@Service("careerMusicSOImpl")
public class MusicSOImpl extends BaseSOImpl<Music> implements MusicSO {
	
	@Autowired
	private MusicDao dao;
	
	@Override
	protected BaseDao<Music> getDao() {
		return dao;
	}

	@Override
	public int getCount(EStatus status) {
		Map<String,Object> map = getParamMap();
		return dao.getCount(map);
	}


}
