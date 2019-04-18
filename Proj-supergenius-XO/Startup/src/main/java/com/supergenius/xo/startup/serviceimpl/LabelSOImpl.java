package com.supergenius.xo.startup.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.base.entity.Pager;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.genius.xo.baseadmin.service.AdminLogSO;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.startup.dao.LabelDao;
import com.supergenius.xo.startup.entity.Label;
import com.supergenius.xo.startup.service.LabelSO;

/**
 * LabelSOImpl
 * 
 * @author 杨光
 * @date 2017年8月23日14:28:34
 */
@Service
public class LabelSOImpl extends BaseSOImpl<Label> implements LabelSO {

	@Autowired
	LabelDao dao;

	@Autowired
	AdminLogSO adminlogSO;

	@Override
	protected BaseDao<Label> getDao() {
		return dao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.supergenius.xo.startup.service.LabelSO#add(com.supergenius.xo.startup
	 * .entity.Label, com.genius.model.baseadmin.entity.AdminLog)
	 */
	@Override
	public boolean add(Label label, AdminLog adminLog) {
		return dao.insert(label) && adminlogSO.add(adminLog);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.supergenius.xo.startup.service.LabelSO#update(com.supergenius.xo.
	 * startup.entity.Label, com.genius.model.baseadmin.entity.AdminLog)
	 */
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
