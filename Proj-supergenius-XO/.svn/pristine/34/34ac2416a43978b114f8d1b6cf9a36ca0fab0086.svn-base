package com.supergenius.xo.managernews.serviceimpl;

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
import com.supergenius.xo.managernews.dao.ContentDao;
import com.supergenius.xo.managernews.entity.Content;
import com.supergenius.xo.managernews.enums.EContent;
import com.supergenius.xo.managernews.service.ContentSO;

/**
 * 内容SO实现
 * 
 * @author tf
 * @date 2018年7月5日
 */
@Service("managernewsContentSOImpl")
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
 
	@Override
	public List<Content> getADContentList(EContent content,int cid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.type, content);
		map.put(MapperDict.cid, cid);
		map.put(MapperDict.orderBy, MapperDict.sql_order_by + MapperDict.createtime + MapperDict.desc);
		return dao.getList(map);
	}

}
