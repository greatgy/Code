package com.supergenius.xo.life.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.life.dao.VideoDao;
import com.supergenius.xo.life.entity.Video;
import com.supergenius.xo.life.service.VideoSO;

/**
 * 視頻SOImpl
 * @Author:JiaShitao
 * @Date:2018年5月7日下午6:08:45
 */
@Service("lifeVideoSOImpl")
public class VideoSOImpl extends BaseSOImpl<Video> implements VideoSO {
	@Autowired
	VideoDao dao;
	@Override
	protected BaseDao<Video> getDao() {
		return dao;
	}

	@Override
	public boolean updateStatusByUid(String uid) {
		Video video = dao.get(uid);
		if (video.getStatus() == EStatus.disable) {
			video.setStatus(EStatus.enable);
		} else {
			video.setStatus(EStatus.disable);
		}
		return dao.update(video);
	}
}
