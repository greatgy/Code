package com.supergenius.xo.moral.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.moral.moral.dao.DocDao;
import com.supergenius.xo.moral.constants.MapperDict;
import com.supergenius.xo.moral.entity.Doc;
import com.supergenius.xo.moral.service.DocSO;

/**
 * 讲义SO实现
 * 
 * @author LiJiacheng
 */
@Service
public class DocSOImpl extends BaseSOImpl<Doc> implements DocSO {

	@Autowired
	DocDao dao;

	@Override
	protected BaseDao<Doc> getDao() {
		return dao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.DocSO#update(com.genius.model.base.enums.EStatus, java.lang.String[])
	 * 
	 * @author: LiJiacheng 2015-6-8 下午6:25:15
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.DocSO#doc_up(java.lang.String)
	 * 
	 * @author: LiJiacheng 2015-6-9 下午1:40:18
	 */
	@Override
	public boolean doc_up(String ids) {
		Map<String, Object> map = getParamMap(true);
		Doc doc = dao.get(ids);
		if (doc == null) {
			return false;
		}
		map.put(MapperDict.sortorder + BaseMapperDict.suffix_greater_key, doc.getSortorder());
		List<Doc> list = getList(map);
		if (list.size() > 0) {
			Doc doc2 = list.get(list.size() - 1);
			int sortorder = doc.getSortorder();
			doc.setSortorder(doc2.getSortorder());
			doc2.setSortorder(sortorder);
			return dao.update(doc) && dao.update(doc2);
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.DocSO#doc_down(java.lang.String)
	 * 
	 * @author: LiJiacheng 2015-6-9 下午1:40:18
	 */
	@Override
	public boolean doc_down(String ids) {
		Map<String, Object> map = getParamMap(true);
		Doc doc = dao.get(ids);
		if (doc == null) {
			return false;
		}
		map.put(MapperDict.sortorder + BaseMapperDict.suffix_less_key, doc.getSortorder());
		List<Doc> list = getList(map);
		if (list.size() > 0) {
			Doc doc2 = list.get(0);
			int sortorder = doc.getSortorder();
			doc.setSortorder(doc2.getSortorder());
			doc2.setSortorder(sortorder);
			return dao.update(doc) && dao.update(doc2);
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see com.supergenius.xo.moral.service.DocSO#updateCount(com.supergenius.xo.moral.entity.Doc)
	 */
	@Override
	public boolean updateCount(Doc file) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.uid, file.getUid());
		map.put(MapperDict.countdl, file.getCountdl()+1);
		return dao.updateFields(map);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.genius.xo.base.serviceimpl.BaseSOImpl#getList(com.genius.model.base.entity.Pager)
	 */
	@Override
	public List<Doc> getList(Pager pager) {
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.orderBy, MapperDict.sortorder);
		return dao.getList(map);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.genius.xo.base.serviceimpl.BaseSOImpl#getList(java.util.Map)
	 */
	@Override
	public List<Doc> getList(Map<String, Object> map) {
		map.put(MapperDict.orderBy, MapperDict.sortorder);
		map.put(MapperDict.status, EStatus.enable);
		return dao.getList(map);
	}

	/*
	 * (non-Javadoc)
	 * @see com.supergenius.xo.moral.service.DocSO#getList(com.genius.model.base.entity.Pager, java.lang.String)
	 */
	@Override
	public List<Doc> getList(Pager pager, String orderBy) {
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.orderBy, orderBy);
		return dao.getList(map);
	}

}
