package com.supergenius.xo.moralnews.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.base.entity.Pager;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.moralnews.dao.CommentsDao;
import com.supergenius.xo.moralnews.entity.Comments;
import com.supergenius.xo.moralnews.enums.EComment;
import com.supergenius.xo.moralnews.enums.EMoralnewsChannel;
import com.supergenius.xo.moralnews.service.CommentsSO;

/**
 * 评论SO实现
 * 
 * @author JiaShitao
 * @date 2018年9月19日09:47:55
 */
@Service("moralnewsCommentsSOImpl")
public class CommentsSOImpl extends BaseSOImpl<Comments> implements CommentsSO {

	@Autowired
	private CommentsDao dao;

	@Override
	protected BaseDao<Comments> getDao() {
		return dao;
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
	public List<Comments> search(Map<String, Object> map) {
		return dao.search(map);
	}

	@Override
	public int searchCount(Map<String, Object> map) {
		return dao.searchCount(map);
	}

	@Override
	public List<String> getList(String useruid, EComment echannel) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.fromuseruid, useruid);
		map.put(MapperDict.type, echannel);
		return dao.getFromuidList(map);
	}
	
	@Override
	public boolean deleteByPraise(String fromuseruid, String fromuid, EMoralnewsChannel channel) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.fromuseruid, fromuseruid);
		map.put(MapperDict.fromuid, fromuid);
		map.put(MapperDict.type, EComment.praise);
		map.put(MapperDict.channel, channel);
		return dao.deleteByMap(map);
	}

	@Override
	public List<Comments> getCommentList(EComment type, String fromuid, int pagesize, Integer pagenum) {
		Pager pager = new Pager(pagenum, pagesize);
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.type, type);
		map.put(MapperDict.fromuid, fromuid);
		map.put(MapperDict.touidnull, true);
		return dao.getList(map);
	}

	@Override
	public List<Comments> getSecondList(EComment eChannel, String firstuid, int pagesize, Integer pagenum) {
		Pager pager = new Pager(pagenum, pagesize);
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.data, firstuid);
		map.put(MapperDict.type, eChannel);
		return dao.getList(map);
	}
	
	@Override
	public boolean isNotPrized(String fromuid, String fromuseruid, EMoralnewsChannel channel) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.fromuid, fromuid);
		map.put(MapperDict.fromuseruid, fromuseruid);
		map.put(MapperDict.channel, channel);
		map.put(MapperDict.type, EComment.praise);
		return dao.getCount(map) == 0;
	}

}
