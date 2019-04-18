package com.supergenius.xo.tpi.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.tpi.constants.MapperDict;
import com.supergenius.xo.tpi.dao.NoticeDao;
import com.supergenius.xo.tpi.entity.Notice;
import com.supergenius.xo.tpi.enums.ENoticeChannel;
import com.supergenius.xo.tpi.enums.ENoticeType;
import com.supergenius.xo.tpi.service.NoticeSO;

/**
 * 招聘信息SOImpl
 * @author liushaomin
 */
@Service
public class NoticeSOImpl extends BaseSOImpl<Notice> implements NoticeSO{
	
	@Autowired
	private NoticeDao dao;

	@Override
	protected NoticeDao getDao() {
		return dao;
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.NoticeSO#deleteByUids(java.lang.String[])
	 */
	@Override
	public boolean deleteByUids(String[] ids) {
		for (String id : ids) {
			dao.delete(id);
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.NoticeSO#update(com.genius.model.base.enums.EStatus, java.lang.String[])
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
	 * @see com.supergenius.xo.tpi.service.NoticeSO#getList(java.lang.String, com.supergenius.xo.tpi.enums.ENoticeType, com.supergenius.xo.tpi.enums.ENoticeChannel)
	 * @author: ShangJianguo
	 * 2015-1-14 下午7:22:06
	 */
	@Override
	public List<Notice> getList(String fromuid, ENoticeType type, ENoticeChannel channel) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.fromuid, fromuid);
		map.put(MapperDict.type, type);
		map.put(MapperDict.channel, channel);
		return dao.getList(map);
	}

}
