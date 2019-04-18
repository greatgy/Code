package com.supergenius.xo.life.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.base.entity.Pager;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.life.dao.EssayDao;
import com.supergenius.xo.life.entity.Essay;
import com.supergenius.xo.life.enums.EComment;
import com.supergenius.xo.life.enums.ELifeChannel;
import com.supergenius.xo.life.service.EssaySO;

/**
 * 
 * @author ChenQi
 * @date 2018年5月9日17:17:13
 */
@Service("lifeEssaySOImpl")
public class EssaySOImpl extends BaseSOImpl<Essay> implements EssaySO {

	@Autowired
	private EssayDao dao;

	@Override
	protected BaseDao<Essay> getDao() {
		return dao;
	}

	@Override
	public int searchCount(Map<String, Object> map) {
		return dao.searchCount(map);
	}

	@Override
	public List<Essay> search(Map<String, Object> map) {
		return dao.search(map);
	}

	@Override
	public List<Essay> getSecondList(EComment type, String firstuid, int pagesize, Integer pagenum) {
		Pager pager = new Pager(pagenum, pagesize);
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.data, firstuid);
		map.put(MapperDict.type, type);
		return dao.getList(map);
	}

	@Override
	public Integer getCountByType(EComment type) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.type, type);
		return dao.getCount(map);
	}

	@Override
	public int getCountByfromuseruid() {
		Map<String, Object> map = getParamMap();
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
	public List<Essay> getCommentList(EComment type, int pagesize, Integer pagenum, long cid) {
		Pager pager = new Pager(pagenum, pagesize);
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.type, type);
		map.put(MapperDict.touidnull, true);
		map.put(MapperDict.cid, cid);
		return dao.getList(map);
	}

	@Override
	public boolean isNotPrized(String touid, String fromuseruid, ELifeChannel channel) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.touid, touid);
		map.put(MapperDict.fromuseruid, fromuseruid);
		map.put(MapperDict.channel, channel);
		map.put(MapperDict.type, EComment.praise);
		return dao.getCount(map) == 0;
	}
}