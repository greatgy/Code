package com.supergenius.xo.life.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.base.entity.Pager;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.life.dao.AnswerDao;
import com.supergenius.xo.life.entity.Answer;
import com.supergenius.xo.life.enums.ELifeChannel;
import com.supergenius.xo.life.enums.EComment;
import com.supergenius.xo.life.service.AnswerSO;

/**
 * 回答SO实现
 * 
 * @author ChenQi
 * @date 2018年5月9日16:35:37
 */
@Service("lifeAnswerSOImpl")
public class AnswerSOImpl extends BaseSOImpl<Answer> implements AnswerSO {

	@Autowired
	private AnswerDao dao;

	@Override
	protected BaseDao<Answer> getDao() {
		return dao;
	}

	@Override
	public int searchCount(Map<String, Object> map) {
		return dao.searchCount(map);
	}

	@Override
	public List<Answer> search(Map<String, Object> map) {
		return dao.search(map);
	}

	@Override
	public List<Answer> getCommentList(EComment type, String fromuid, int pagesize, Integer pagenum) {
		Pager pager = new Pager(pagenum, pagesize);
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.type, type);
		map.put(MapperDict.fromuid, fromuid);
		map.put(MapperDict.touidnull, true);
		return dao.getList(map);
	}

	@Override
	public List<String> getList(String useruid, EComment echannel) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.fromuseruid, useruid);
		map.put(MapperDict.type, echannel);
		return dao.getFromuidList(map);
	}

	@Override
	public List<Answer> getSecondList(EComment eChannel, String firstuid, int pagesize, Integer pagenum) {
		Pager pager = new Pager(pagenum, pagesize);
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.data, firstuid);
		map.put(MapperDict.type, eChannel);
		return dao.getList(map);
	}

	@Override
	public boolean isNotPrized(String fromuid, String fromuseruid, ELifeChannel channel) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.fromuid, fromuid);
		map.put(MapperDict.fromuseruid, fromuseruid);
		map.put(MapperDict.channel, channel);
		map.put(MapperDict.type, EComment.praise);
		return dao.getCount(map) == 0;
	}

	@Override
	public boolean deleteByPraise(String fromuseruid, String fromuid, ELifeChannel channel) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.fromuseruid, fromuseruid);
		map.put(MapperDict.fromuid, fromuid);
		map.put(MapperDict.type, EComment.praise);
		map.put(MapperDict.channel, channel);
		return dao.deleteByMap(map);
	}

	@Override
	public Integer getCountByType(EComment type) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.type, type);
		return dao.getCount(map);
	}

}