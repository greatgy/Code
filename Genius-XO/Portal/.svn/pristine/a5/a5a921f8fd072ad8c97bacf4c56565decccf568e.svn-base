package com.genius.xo.portal.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.core.base.constant.BaseStrDict;
import com.genius.model.base.entity.Pager;
import com.genius.model.portal.entity.Content;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.baseadmin.serviceimpl.BaseSOImpl;
import com.genius.xo.portal.dao.ContentDao;
import com.genius.xo.portal.service.ContentSO;

/**
 * @author Architect.bian
 * 
 */
@Service
public class ContentSOImpl extends BaseSOImpl<Content> implements ContentSO {

	@Autowired
	private ContentDao dao;

	@Override
	protected BaseDao<Content> getDao() {
		return dao;
	}

	@Override
	public Content getOneByType(String type) {
		Map<String, Object> map = getParamMap();
		map.put(BaseMapperDict.type, type);
		return dao.getOne(map);
	}

	@Override
	public Content get(Integer oid) {
		return dao.get(oid);
	}

	@Override
	public List<Content> getListByType(Pager pager, String e) {
		Map<String, Object> map = getParamMap(pager,true);
		map.put(BaseMapperDict.type, e);
		return dao.getList(map);
	}
	
	@Override
	public List<Content> getListByPaperMap(Pager pager, Map<String, Object> map) {
		Map<String, Object> map1 = getParamMap(pager,true);
		map1.putAll(map);
		return dao.getList(map);
	}

	@Override
	public int getCountByType(String e) {
		Map<String, Object> map = getParamMap(true);
		map.put(BaseMapperDict.type, e);
		return dao.getCount(map);
	}
	
	@Override
	public int getCountTopByType(String type) {
		Map<String, Object> map = getParamMap(true);
		map.put(BaseMapperDict.type, type);
		map.put(BaseMapperDict.istop, true);
		return dao.getCount(map);
	}

	@Override
	public void deleteByIds(String oids) {
		String[] oidarray = oids.split(BaseStrDict.comma);
		Map<String,Object> map = new HashMap<String,Object>();
		ArrayList<String> list = new ArrayList<String>();
		for (String uid : oidarray) {
			list.add(uid);
		}
		map.put(BaseMapperDict.oids,list);
		dao.deleteByMap(map);
	}

	@Override
	public List<Content> getTopListByType(String type) {
		Map<String, Object> map = getParamMap();
		map.put(BaseMapperDict.type, type);
		map.put(BaseMapperDict.istop, true);
		return getList(map);
	}

	@Override
	public List<Content> getSearchList(Pager pager, Map<String, Object> searchMap) {
		Map<String, Object> map = getParamMap(pager);
		map.putAll(searchMap);
		return dao.getList(map);
	}

	@Override
	public int getSearchCount(Map<String, Object> searchMap) {
		Map<String, Object> map = getParamMap();
		map.putAll(searchMap);
		return dao.getCount(map);
	}

	@Override
	public boolean setTop(String[] ids, boolean istop) {
		Map<String, Object> map = getParamMap();
		for (String id : ids) {
			map.put(BaseMapperDict.oid, id);
			map.put(BaseMapperDict.istop, istop);
			dao.updateFields(map);
		}
		return true;
	}

	/**
	 * 更新某几个字段
	 */
	@Override
	public boolean updateFields(Content t) {
		Content content = dao.get(t.getOid());
		content.set(t);
		dao.update(content);
		return true;
	}
}
