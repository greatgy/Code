package com.supergenius.xo.startup.serviceimpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.startup.dao.MusicDao;
import com.supergenius.xo.startup.entity.Music;
import com.supergenius.xo.startup.service.MusicSO;

/**
 * 背景音乐SO实现
 * @author 许志翔
 * @date 2017年8月9日14:56:05
 */
@Service
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
