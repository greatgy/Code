package com.supergenius.xo.sudokuapi.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.sudokuapi.dao.VersionsDao;
import com.supergenius.xo.sudokuapi.entity.Versions;
import com.supergenius.xo.sudokuapi.service.VersionsSO;

/**
 * 版本更新SOImple
 * 
 * @CreateTime 2018年5月23日--下午7:04:18
 * @author LiuBin
 */
@Service
public class VersionsSOImpl extends BaseSOImpl<Versions> implements VersionsSO {

	@Autowired
	VersionsDao dao;
	
	@Override
	protected BaseDao<Versions> getDao() {
		return dao;
	}

	@Override
	public Versions getOne(String company, String code) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.company, company);
		map.put(MapperDict.orderBy, code);
		map.put(BaseMapperDict.pageSize, 1);
		List<Versions> list = dao.getList(map);
		if (list != null && list.size() > 0) {
			return list.get(0);
		} 
		return null;
	}

}