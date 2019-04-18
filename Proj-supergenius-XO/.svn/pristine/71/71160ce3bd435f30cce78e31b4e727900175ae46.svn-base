package com.supergenius.xo.moral.serviceimpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.moral.moral.dao.DownloadDetailDao;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.moral.entity.DownloadDetail;
import com.supergenius.xo.moral.enums.EDoc;
import com.supergenius.xo.moral.service.DownloadDetailSO;

/**
 * 下载明细so的实现
 * @author liushaomin
 */
@Service
public class DownloadDetailSOImpl extends BaseSOImpl<DownloadDetail> implements DownloadDetailSO{

	@Autowired
	DownloadDetailDao dao;
	
	/* (non-Javadoc)
	 * @see com.genius.xo.base.serviceimpl.BaseSOImpl#getDao()
	 */
	@Override
	protected BaseDao<DownloadDetail> getDao() {
		return dao;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.supergenius.xo.moral.service.DownloadDetailSO#getOne(com.supergenius.xo.moral.enums.EDoc, java.lang.String, java.lang.String)
	 */
	@Override
	public DownloadDetail getOne(EDoc doc, String fuid, String suid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.type, doc);
		map.put(MapperDict.useruid, suid);
		map.put(MapperDict.fromuid, fuid);
		return dao.getOne(map);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.supergenius.xo.moral.service.DownloadDetailSO#add(com.supergenius.xo.moral.enums.EDoc, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean add(EDoc doc, String fuid, String suid) {
		DownloadDetail detail = new DownloadDetail();
		detail.setType(doc);
		detail.setUseruid(suid);
		detail.setFromuid(fuid);
		return dao.insert(detail);
	}

}
