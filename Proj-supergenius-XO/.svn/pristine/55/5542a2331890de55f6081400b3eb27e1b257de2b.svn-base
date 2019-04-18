package com.supergenius.xo.official.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.official.constants.MapperDict;
import com.supergenius.xo.official.dao.DiscussDao;
import com.supergenius.xo.official.entity.Discuss;
import com.supergenius.xo.official.enums.EDiscuss;
import com.supergenius.xo.official.service.DiscussSO;

/**
 * 评论互动so的实现
 * @author liushaomin
 */
@Service
public class DiscussSOImpl extends BaseSOImpl<Discuss> implements DiscussSO{
	
	@Autowired
	private DiscussDao dao;

	@Override
	protected DiscussDao getDao() {
		return dao;
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.official.service.DiscussSO#update(com.genius.model.base.enums.EStatus, java.lang.String[])
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
	 * @see com.supergenius.xo.official.service.DiscussSO#deleteByUids(java.lang.String[])
	 */
	@Override
	public boolean deleteByUids(String[] ids) {
		for (String id : ids) {
			dao.delete(id);
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.official.service.DiscussSO#getCount(com.supergenius.xo.official.enums.EDiscuss)
	 */
	@Override
	public int getCount(EDiscuss specialcolumn) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.type, specialcolumn);
		return dao.getCount(map);
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.official.service.DiscussSO#getList(com.genius.model.base.entity.Pager, com.supergenius.xo.official.enums.EDiscuss)
	 */
	@Override
	public List<Discuss> getList(Pager pager, EDiscuss specialcolumn) {
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.type, specialcolumn);
		return dao.getList(map);
	}


}
