package com.supergenius.xo.sudokuapi.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.sudokuapi.dao.ShareDao;
import com.supergenius.xo.sudokuapi.entity.Share;
import com.supergenius.xo.sudokuapi.service.ShareSO;

/**
 * 分享SOImpl
 * 
 * @CreateTime 2018年5月30日--下午5:47:22
 * @author LiuBin
 */
@Service
public class ShareSOImpl extends BaseSOImpl<Share> implements ShareSO {

	@Autowired
	ShareDao dao;
	
	@Override
	protected BaseDao<Share> getDao() {
		return dao;
	}

	@Override
	public Share getOne(String orderby) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.orderBy, orderby);
		map.put(BaseMapperDict.pageSize, 1);
		List<Share> list = (List<Share>) dao.getList(map);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
}
