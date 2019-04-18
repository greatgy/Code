package com.supergenius.xo.enterpriser.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.base.entity.Pager;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.enterpriser.dao.ForumDao;
import com.supergenius.xo.enterpriser.entity.Forum;
import com.supergenius.xo.enterpriser.service.ForumSO;

/**
 * 文章SO实现
 * 
 * @author XueZhenYong
 * @date 2017年12月29日下午3:26:31
 */
@Service("enterpriserForumSOImpl")
public class ForumSOImpl extends BaseSOImpl<Forum> implements ForumSO {

	@Autowired
	ForumDao dao;

	@Override
	protected BaseDao<Forum> getDao() {
		return dao;
	}

	@Override
	public List<Forum> getRecommendList(Pager pager, boolean isrecommend, int cid) {
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.isrecommend, isrecommend);
		map.put(MapperDict.cid, cid);
		return dao.getList(map);
	}

	@Override
	public List<Forum> getListByCid(Integer cid) {
		Map<String, Object> map = getParamMap();
		if (cid == 0) { // 0表示首页
			return dao.getList(map);
		}
		map.put(MapperDict.cid, cid);
		return dao.getList(map);
	}
	
	@Override
	public List<Forum> getRelatecarticleList(Pager pager, String useruid) {
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.useruid, useruid);
		return dao.getList(map);
	}

	@Override
	public List<Forum> getFirstForum(Pager pager, int cid) {
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.cid, cid);
		map.put(MapperDict.istop, false);
		return dao.getList(map);
	}
	
	@Override
	public List<Forum> getCollectList(String useruid, Pager pager) {
		Map<String, Object> map;
		if (pager != null) {
			map = getParamMap(pager);
		} else {
			map = getParamMap();
		}
		map.put(MapperDict.useruid, useruid);
		return dao.getCollectList(map);
	}
	
	@Override
	public boolean setRecommend(String[] ids, boolean isrecommend) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (String id : ids) {
			map.put(MapperDict.uid, id);
			map.put(MapperDict.isrecommend, isrecommend);
			dao.updateFields(map);
		}
		return true;
	}
	
	@Override
	public boolean setTop(String[] ids, boolean istop) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (String id : ids) {
			map.put(MapperDict.uid, id);
			map.put(MapperDict.istop, istop);
			map.put(MapperDict.toptime, new DateTime(DateTimeZone.forOffsetHours(8)));
			dao.updateFields(map);
		}
		return true;
	}
}
