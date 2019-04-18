package com.supergenius.xo.finance.serviceimpl;

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
import com.supergenius.xo.finance.dao.ContentDao;
import com.supergenius.xo.finance.entity.Content;
import com.supergenius.xo.finance.enums.EContent;
import com.supergenius.xo.finance.service.ContentSO;

/**
 * 内容So实现
 * 
 * @author XueZhenYong
 * @date 2017年12月29日下午3:37:10
 */
@Service("financeContentSOImpl")
public class ContentSOImpl extends BaseSOImpl<Content> implements ContentSO {
	@Autowired
	private ContentDao dao;

	@Autowired
	private AdminLogSO adminlogSO;
	
	@Override
	protected BaseDao<Content> getDao() {
		return dao;
	}

	@Override
	public List<Content> getADContentList(EContent eContent) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.type, eContent);
		map.put(MapperDict.orderBy, MapperDict.sql_order_by + MapperDict.createtime + MapperDict.desc);
		return dao.getList(map);
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
