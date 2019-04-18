package com.supergenius.xo.tpi.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.tpi.constants.MapperDict;
import com.supergenius.xo.tpi.dao.TypeDao;
import com.supergenius.xo.tpi.entity.Type;
import com.supergenius.xo.tpi.enums.EType;
import com.supergenius.xo.tpi.service.TypeSO;

/**
 * @author liushaomin
 */
@Service
public class TypeSOImpl extends BaseSOImpl<Type> implements TypeSO{
	
	@Autowired
	private TypeDao dao;

	@Override
	protected TypeDao getDao() {
		return dao;
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.TypeSO#getListByType(com.supergenius.xo.tpi.enums.EType)
	 */
	@Override
	public List<Type> getListByType(EType type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.type, type);
		return dao.getList(map);
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.TypeSO#deleteByUids(java.lang.String[])
	 */
	@Override
	public boolean deleteByUids(String[] ids) {
		for (String id : ids) {
			dao.delete(id);
		}
		return true;
	}

}
