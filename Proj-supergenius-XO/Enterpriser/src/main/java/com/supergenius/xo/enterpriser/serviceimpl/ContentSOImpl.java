package com.supergenius.xo.enterpriser.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.genius.xo.baseadmin.service.AdminLogSO;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.enterpriser.dao.ContentDao;
import com.supergenius.xo.enterpriser.entity.Content;
import com.supergenius.xo.enterpriser.enums.ECatalogue;
import com.supergenius.xo.enterpriser.enums.EContent;
import com.supergenius.xo.enterpriser.service.ContentSO;

/**
 * 内容SOImpl
 * @author XieMing
 * @date 2016-10-24 下午4:45:49
 */
@Service("enterpriserContentSOImpl")
public class ContentSOImpl extends BaseSOImpl<Content> implements ContentSO {

	@Autowired
	private ContentDao dao;
	
	@Autowired
	private AdminLogSO adminlogSO ;
	
	@Override
	protected BaseDao<Content> getDao() {
		return dao;
	}

	@Override
	public Content getOneByType(EContent type) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.type, type);
		return dao.getOne(map);
	}
	
	@Override
	public List<Content> getADContentList(ECatalogue catalogue) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.type, EContent.advertising);
		map.put(MapperDict.cid, catalogue);
		return dao.getList(map);
	}

	@Override
	public int getCount(EContent type) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.type, type);
		return dao.getCount(map);
	}
	
	@Override
	public boolean add(Content content, AdminLog adminLog) {
		return dao.insert(content) && adminlogSO.add(adminLog);
	}

	@Override
	public boolean update(Content content, AdminLog adminLog) {
		return dao.update(content) && adminlogSO.add(adminLog);
	}

	@Override
	public boolean updateStatusByUid(String uid) {
		Content content = dao.get(uid);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.uid, uid);
		if (content.getStatus() == EStatus.disable) {
			map.put(MapperDict.status, EStatus.enable);
		} else {
			map.put(MapperDict.status, EStatus.disable);
		}
		return dao.updateFields(map);
	}

}
