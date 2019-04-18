package com.supergenius.xo.ai.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.base.entity.Pager;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.genius.xo.baseadmin.service.AdminLogSO;
import com.supergenius.xo.ai.dao.LabelDao;
import com.supergenius.xo.ai.entity.Label;
import com.supergenius.xo.ai.service.LabelSO;
import com.supergenius.xo.common.constants.MapperDict;

/**
 * LabelSOImpl
 * 
 * @author ChenQi
 * @date 2017年9月19日10:43:33
 */
@Service("AiLabelSOImpl")
public class LabelSOImpl extends BaseSOImpl<Label> implements LabelSO {

	@Autowired
	LabelDao dao;

	@Autowired
	AdminLogSO adminlogSO;

	@Override
	protected BaseDao<Label> getDao() {
		return dao;
	}

	@Override
	public boolean add(Label label, AdminLog adminLog) {
		return dao.insert(label) && adminlogSO.add(adminLog);
	}

	@Override
	public boolean update(Label label, AdminLog adminLog) {
		return dao.update(label) && adminlogSO.add(adminLog);
	}

	@Override
	public List<Label> getHotLabelList(Pager pager) {
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.orderBy, MapperDict.sql_order_by + MapperDict.count + MapperDict.desc);
		return dao.getList(map);
	}

	@Override
	public List<Label> getRelatedLabel(String refuid, int size) {
		Pager pager = new Pager(size);
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.orderBy, MapperDict.sql_order_by + MapperDict.count + MapperDict.desc);
		map.put(MapperDict.articleuid, refuid);
		return dao.getList(map);
	}
}
