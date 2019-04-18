package com.supergenius.xo.moral.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.core.base.utils.MapBuilder;
import com.genius.model.base.entity.Pager;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.genius.xo.mongodb.MongoDict;
import com.supergenius.moral.moral.dao.SignDao;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.moral.entity.Sign;
import com.supergenius.xo.moral.service.SignSO;

/**
 * 签到so的实现
 * 
 * @author liushaomin
 * @modifier lijiacheng
 */
@Service
public class SignSOImpl extends BaseSOImpl<Sign> implements SignSO {

	@Autowired
	SignDao dao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.genius.xo.base.serviceimpl.BaseSOImpl#getDao()
	 */
	@Override
	protected BaseDao<Sign> getDao() {
		return dao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.SignSO#sign()
	 * 
	 * @author: LiJiacheng 2015-7-24 下午4:58:33
	 */
	@Override
	public boolean sign(String useruid) {
		Sign sign = new Sign(useruid);
		sign.setCreatetime(DateTime.now());
		return dao.insert(sign);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.SignSO#groupSign()
	 * 
	 * @author: LiJiacheng 2015-8-27 下午2:37:09
	 */
	@Override
	public List<Map<String, Object>> groupSign() {
		Map<String, Object> where = new HashMap<>();
		where.put(MapperDict.createtime + BaseMapperDict.suffix_less_key, DateTime.now());
		List<String> fields = new ArrayList<>();
		fields.add(MapperDict._id);
		fields.add(MapperDict.newestsign);
		Map<String, Object> groupMap = new HashMap<>();
		groupMap.put(MongoDict._id, MongoDict.$ + MapperDict.useruid);
		groupMap.put(MapperDict.newestsign, MapBuilder.start(MapperDict.$max, MongoDict.$ + MapperDict.createtime).get());
		Map<String, Object> sortMap = new HashMap<>();
		sortMap.put(MapperDict.newestsign, -1);
		return dao.group(where, fields, groupMap, sortMap, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.SignSO#getLatestSigns(com.genius.model.base.entity.Pager, java.util.Map)
	 * 
	 * @author: LiJiacheng 2015-9-1 上午11:42:05
	 */
	@Override
	public List<Sign> getLatestSigns(Pager pager, Map<String, Object> map) {
		map.putAll(getParamMap(pager));
		return dao.getList(map);
	}

}
