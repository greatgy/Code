package com.supergenius.xo.managernews.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.base.entity.Pager;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.managernews.dao.CommentsDao;
import com.supergenius.xo.managernews.entity.Comments;
import com.supergenius.xo.managernews.enums.EComment;
import com.supergenius.xo.managernews.enums.EManagernewsChannel;
import com.supergenius.xo.managernews.service.CommentsSO;

/**
 * 评论SO实现
 * 
 * @author XueZhenYong
 * @date 2017年12月29日下午3:35:03
 */
@Service("managernewsCommentsSOImpl")
public class CommentsSOImpl extends BaseSOImpl<Comments> implements CommentsSO {


	@Autowired
	private CommentsDao dao;

	@Override
	protected BaseDao<Comments> getDao() {
		return dao;
	}
	
	@Override
	public int searchCount(Map<String, Object> map) {
		return dao.searchCount(map);
	}

	@Override
	public List<Comments> search(Map<String, Object> map) {
		return dao.search(map);
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
	public List<String> getList(String useruid, EComment echannel) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.fromuseruid, useruid);
		map.put(MapperDict.type, echannel);
		return dao.getFromuidList(map);
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
	public boolean isNotPrized(String fromuid, String fromuseruid, EManagernewsChannel channel) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.fromuid, fromuid);
		map.put(MapperDict.fromuseruid, fromuseruid);
		map.put(MapperDict.channel, channel);
		map.put(MapperDict.type, EComment.praise);
		return dao.getCount(map) == 0;
	}

	@Override
	public boolean deleteByPraise(String fromuseruid, String fromuid, EManagernewsChannel channel) {
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

	@Override
	public int getCountByfromuseruid() {
		Map<String, Object> map = getParamMap();
		return dao.getCountByfromuseruid(map);
	}
}
