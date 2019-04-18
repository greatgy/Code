package com.supergenius.xo.career.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.base.entity.Pager;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.career.dao.TeaseDao;
import com.supergenius.xo.career.entity.Tease;
import com.supergenius.xo.career.enums.EComment;
import com.supergenius.xo.career.service.TeaseSO;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.EChannel;

/**
 * 
 * @author ChenQi
 * @date 2017年12月27日19:22:57
 */
@Service("careerTeaseSOImpl")
public class TeaseSOImpl extends BaseSOImpl<Tease> implements TeaseSO {

	@Autowired
	private TeaseDao dao;

	@Override
	protected BaseDao<Tease> getDao() {
		return dao;
	}

	@Override
	public int searchCount(Map<String, Object> map) {
		return dao.searchCount(map);
	}

	@Override
	public List<Tease> search(Map<String, Object> map) {
		return dao.search(map);
	}

	@Override
	public List<Tease> getSecondList(EComment type, String firstuid, int pagesize, Integer pagenum, EChannel channel) {
		Pager pager = new Pager(pagenum, pagesize);
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.data, firstuid);
		map.put(MapperDict.type, type);
		map.put(MapperDict.channel, channel);
		return dao.getList(map);
	}

	@Override
	public Integer getCountByType(EComment type) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.type, type);
		return dao.getCount(map);
	}

	@Override
	public int getCountByfromuseruid(EChannel channel) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.channel, channel);
		return dao.getCountByfromuseruid(map);
	}

	@Override
	public boolean deleteByPraise(String fromuseruid, String touid, EChannel channel) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.fromuseruid, fromuseruid);
		map.put(MapperDict.touid, touid);
		map.put(MapperDict.type, EComment.praise);
		map.put(MapperDict.channel, channel);
		return dao.deleteByMap(map);
	}

	@Override
	public List<Tease> getCommentList(EComment type, int pagesize, Integer pagenum, EChannel channel) {
		Pager pager = new Pager(pagenum, pagesize);
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.type, type);
		map.put(MapperDict.touidnull, true);
		map.put(MapperDict.channel, channel);
		return dao.getList(map);
	}

	@Override
	public boolean isNotPrized(String touid, String fromuseruid, EChannel channel) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.touid, touid);
		map.put(MapperDict.fromuseruid, fromuseruid);
		map.put(MapperDict.channel, channel);
		map.put(MapperDict.type, EComment.praise);
		return dao.getCount(map) == 0;
	}
}
