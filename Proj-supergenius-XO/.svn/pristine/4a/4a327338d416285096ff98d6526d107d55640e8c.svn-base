package com.supergenius.xo.tpi.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.tpi.constants.MapperDict;
import com.supergenius.xo.tpi.dao.CommentDao;
import com.supergenius.xo.tpi.dao.ProjectDao;
import com.supergenius.xo.tpi.dao.TeamDao;
import com.supergenius.xo.tpi.entity.Comment;
import com.supergenius.xo.tpi.entity.Project;
import com.supergenius.xo.tpi.entity.Team;
import com.supergenius.xo.tpi.enums.ECommentType;
import com.supergenius.xo.tpi.service.CommentSO;
import com.supergenius.xo.tpi.service.TpiuserSO;
import com.supergenius.xo.user.service.UserSO;

/**
 * 会员管理SO实现
 * 
 * @author ShangJianguo
 */
@Service
public class CommentSOImpl extends BaseSOImpl<Comment> implements CommentSO {

	@Autowired
	CommentDao dao;
	@Autowired
	TeamDao teamDao;
	@Autowired
	ProjectDao projectDao;
	@Autowired
	UserSO userSO;
	@Autowired
	TpiuserSO tpiuserSO;
	
	/* (non-Javadoc)
	 * @see com.genius.xo.base.serviceimpl.BaseSOImpl#getDao()
	 * @author: ShangJianguo
	 * 2015-1-6 下午5:38:15
	 */
	@Override
	protected BaseDao<Comment> getDao() {
		
		return dao;
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.CommentSO#setStatus(java.lang.String[], com.genius.model.base.enums.EStatus)
	 * @author: ShangJianguo
	 * 2015-1-11 下午5:00:52
	 */
	@Override
	public boolean setStatus(String[] uids, EStatus status) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.status, status);
		for (String item : uids) {
			map.put(BaseMapperDict.uid, item);
			dao.updateFields(map);
		}
		return true;
	}
	
	/* (non-Javadoc)
	 * @see com.genius.xo.base.serviceimpl.BaseSOImpl#getList(java.util.Map)
	 * @author: ShangJianguo
	 * 2015-1-12 下午2:22:01
	 */
	@Override
	public List<Comment> getList(Map<String, Object> map) {
		List<String> fromuids = new ArrayList<>();
		if (map.containsKey(MapperDict.fromtitle)) {
			Map<String, Object> newMap = new HashMap<>();
			newMap.put(MapperDict.name + MapperDict.suffix_like_key, map.get(MapperDict.fromtitle));
			List<Team> teamList = teamDao.getList(newMap);
			for (Team item : teamList) {
				fromuids.add(item.getUid());
			}
			List<Project> projectList = new ArrayList<>();
			for (Project item : projectList) {
				fromuids.add(item.getUid());
			}
			map.remove(MapperDict.fromtitle);
			if (fromuids.size() > 0) {
				map.put(MapperDict.fromuid + MapperDict.suffix_in_key, fromuids);
			}else {
				return new ArrayList<>();
			}
		}
		
		List<Comment> list = super.getList(map);
		String title = "";
		for (Comment comment : list) {// 设置团队或者项目的名字
			EChannel channel = comment.getChannel();
			if (channel == EChannel.team) {
				Team team = teamDao.get(comment.getFromuid());
				if (team != null) {
					title = team.getName();
				}
			}else if(channel == EChannel.project){
				Project project = projectDao.get(comment.getFromuid());
				if (project != null) {
					title = project.getName();
				}
			}
			comment.setFromtitle(title);
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.CommentSO#getList(java.lang.String, com.supergenius.xo.tpi.enums.ECommentType, com.genius.model.base.entity.Pager, java.lang.String)
	 * @author: ShangJianguo
	 * 2015-2-2 下午4:50:56
	 */
	@Override
	public List<Comment> getList(String fromuid, ECommentType type, Pager pager, String order) {
		Map<String, Object> map = pager == null ? getParamMap() : getParamMap(pager);
		map.put(MapperDict.fromuid, fromuid);
		map.put(MapperDict.type, type);
		if (StringUtils.isNotEmpty(order)) {
			map.put(MapperDict.ascDesc, order);
		}
		List<Comment> list = dao.getList(map);
		for (Comment item : list) {
			if (StringUtils.isNotEmpty(item.getFromuseruid())
					&& !MapperDict.DefaultAnonymousUid.equals(item.getFromuseruid())) {
				if ("user".equals(item.getFromusertype())) {
//TODO					item.setFromUser(userSO.getFromCache(item.getFromuseruid()));
				} else {
					item.setFromUser(tpiuserSO.get(item.getFromuseruid()));
				}
			}
			if (StringUtils.isNotEmpty(item.getTouseruid())
					&& !MapperDict.DefaultAnonymousUid.equals(item.getTouseruid())) {
//TODO				item.setToUser(userSO.getFromCache(item.getTouseruid()));
				if (item.getToUser() == null) {
					item.setToUser(tpiuserSO.get(item.getTouseruid()));
				}
			}
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.CommentSO#isNotPrized(java.lang.String, java.lang.String, com.supergenius.xo.tpi.enums.EChannel)
	 */
	@Override
	public boolean isNotPrized(String useruid, String fromuid, EChannel channel) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.fromuid, fromuid);
		map.put(MapperDict.fromuseruid, useruid);
		map.put(MapperDict.channel, channel);
		map.put(MapperDict.type, ECommentType.prize);
		return dao.getCount(map) == 0;
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.CommentSO#deleteByPraise(java.lang.String, java.lang.String, com.supergenius.xo.tpi.enums.EChannel)
	 */
	@Override
	public boolean deleteByPraise(String fromuseruid, String fromuid, EChannel channel) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.fromuid, fromuid);
		map.put(MapperDict.fromuseruid, fromuseruid);
		map.put(MapperDict.channel, channel);
		map.put(MapperDict.type, ECommentType.prize);
		return dao.deleteByMap(map);
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.CommentSO#getCount(java.lang.String, com.supergenius.xo.tpi.enums.EChannel, com.supergenius.xo.tpi.enums.ECommentType)
	 */
	@Override
	public int getCount(String fromuid, EChannel channel, ECommentType type) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.fromuid, fromuid);
		map.put(MapperDict.channel, channel);
		map.put(MapperDict.type, type);
		return dao.getCount(map);
	}

}
