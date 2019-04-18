package com.supergenius.xo.sudokuapi.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.sudokuapi.dao.ProptypesDao;
import com.supergenius.xo.sudokuapi.entity.Proptypes;
import com.supergenius.xo.sudokuapi.service.ProptypesSO;

/**
 * 道具类型so实现
 * 
 * @author liushaomin
 */
@Service
public class ProptypesSOImpl extends BaseSOImpl<Proptypes> implements ProptypesSO {

	@Autowired
	ProptypesDao proptypesDao;

	@Override
	protected BaseDao<Proptypes> getDao() {
		return proptypesDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sudoku.xo.service.ProptypesSO#updateStatus(com.genius.model.base.enums.EStatus, java.lang.String)
	 * 
	 * @author: LiJiacheng 2015-9-24 下午3:55:37
	 */
	@Override
	public boolean updateStatus(EStatus status, String ids) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.uid, ids);
		map.put(MapperDict.status, status);
		return proptypesDao.updateFields(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sudoku.xo.service.ProptypesSO#proptypes_up(java.lang.String)
	 * 
	 * @author: LiJiacheng 2015-9-24 下午4:13:09
	 */
	@Override
	public boolean proptypes_up(String ids) {
		Proptypes proptypes = proptypesDao.get(ids);
		int order = proptypes.getOrder();
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.order + MapperDict.suffix_less_key, order);
		map.put(MapperDict.orderBy, MapperDict.order);
		List<Proptypes> list = proptypesDao.getList(map);
		if (list.size() == 0) {
			return false;
		} else {
			int up = list.get(0).getOrder();
			list.get(0).setOrder(order);
			proptypes.setOrder(up);
			return proptypesDao.update(proptypes) && proptypesDao.update(list.get(0));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sudoku.xo.service.ProptypesSO#proptypes_down(java.lang.String)
	 * 
	 * @author: LiJiacheng 2015-9-24 下午4:13:09
	 */
	@Override
	public boolean proptypes_down(String ids) {
		Proptypes proptypes = proptypesDao.get(ids);
		int order = proptypes.getOrder();
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.order + MapperDict.suffix_greater_key, order);
		map.put(MapperDict.orderBy, MapperDict.order);
		map.put(MapperDict.ascDesc, MapperDict.asc);
		List<Proptypes> list = proptypesDao.getList(map);
		if (list.size() == 0) {
			return false;
		} else {
			int down = list.get(0).getOrder();
			list.get(0).setOrder(order);
			proptypes.setOrder(down);
			return proptypesDao.update(proptypes) && proptypesDao.update(list.get(0));
		}
	}
}
