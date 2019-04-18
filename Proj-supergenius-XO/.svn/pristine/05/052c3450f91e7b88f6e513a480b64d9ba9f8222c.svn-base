package com.supergenius.xo.tpi.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.core.base.utils.GlobalUtil;
import com.genius.core.base.utils.MapsUtil;
import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.tpi.constants.MapperDict;
import com.supergenius.xo.tpi.dao.CommentDao;
import com.supergenius.xo.tpi.dao.NoticeDao;
import com.supergenius.xo.tpi.dao.TeamDao;
import com.supergenius.xo.tpi.entity.Link;
import com.supergenius.xo.tpi.entity.Notice;
import com.supergenius.xo.tpi.entity.Team;
import com.supergenius.xo.tpi.entity.Type;
import com.supergenius.xo.tpi.enums.ENoticeChannel;
import com.supergenius.xo.tpi.enums.ENoticeType;
import com.supergenius.xo.tpi.enums.EWishType;
import com.supergenius.xo.tpi.service.TeamSO;
import com.supergenius.xo.tpi.service.TypeSO;
import com.supergenius.xo.tpi.service.WishSO;
import com.supergenius.xo.user.entity.User;

/**
 * 团队
 * @author liushaomin
 * @modifier ShangJianguo
 */
@Service
public class TeamSOImpl extends BaseSOImpl<Team> implements TeamSO{

	@Autowired
	private TeamDao dao;
	@Autowired
	private CommentDao commentDao;
	@Autowired
	private TypeSO typeSO;
	@Autowired
	private WishSO wishSO;
	@Autowired
	private NoticeDao noticeDao;
	
	@Override
	protected TeamDao getDao() {
		return dao;
	}
	
	/* (non-Javadoc)
	 * @see com.genius.xo.base.serviceimpl.BaseSOImpl#getList()
	 */
	@Override
	public List<Team> getList(Map<String, Object> map) {
		List<Team> teams = dao.getList(map);
		if (teams.size() != 0) {
			for (Team item : teams) {
				item.setTypeName(typeSO.get(item.getTypeuid()).getName());
			}
		}
		return teams;
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.TeamSO#update(com.genius.model.base.enums.EStatus, java.lang.String[])
	 */
	@Override
	public boolean update(EStatus eStatus, String[] ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (String id : ids) {
			map.put(MapperDict.status, eStatus);
			map.put(MapperDict.uid, id);
			dao.updateFields(map);
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.TeamSO#deleteByUids(java.lang.String)
	 */
	@Override
	public boolean deleteByUids(String[] ids) {
		Map<String, Object> map = new HashMap<>();
		Team team = null;
		for (String id : ids) {
			team = dao.get(id);
			if (dao.delete(id)) {
				// 删除团队对应的评论
				map.put(MapperDict.fromuid, id);
				commentDao.deleteByMap(map);
				// 关联删除  删除用户点击的想并购的项目
				wishSO.deleteByFromuidType(team.getUseruid(), EWishType.wantMergers);
				// 删除团队的招聘信息
				map.clear();
				map.put(MapperDict.type, ENoticeType.team);
				map.put(MapperDict.channel, ENoticeChannel.recruitment);
				map.put(MapperDict.fromuid, team.getUseruid());
				noticeDao.deleteByMap(map);
			}
		}
		return true;
	}
	
	/* (non-Javadoc)
	 * @see com.genius.xo.base.serviceimpl.BaseSOImpl#delete(java.lang.String[])
	 * @author: ShangJianguo
	 * 2015-3-2 下午3:23:20
	 */
	@Override
	public boolean delete(String... ids) {
		return deleteByUids(ids);
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.TeamSO#setTop(java.lang.String[], boolean)
	 */
	@Override
	public boolean setTop(String[] ids, boolean istop) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (String id : ids) {
			map.put(MapperDict.uid, id);
			map.put(MapperDict.istop, istop);
			dao.updateFields(map);
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.TeamSO#setRecommend(java.lang.String[], boolean)
	 */
	@Override
	public boolean setRecommend(String[] ids, boolean isrecommend) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (String id : ids) {
			map.put(MapperDict.uid, id);
			map.put(MapperDict.isrecommend, isrecommend);
			map.put(MapperDict.rectime, DateTime.now());
			dao.updateFields(map);
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.TeamSO#getOne(java.lang.String, boolean)
	 * @author: ShangJianguo
	 * 2015-1-14 上午10:42:07
	 */
	@Override
	public Team getOne(String typeuid, boolean istop) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.typeuid, typeuid);
		map.put(MapperDict.istop, istop);
		return dao.getOne(map);
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.TeamSO#getCount(com.supergenius.xo.tpi.entity.Type)
	 * @author: ShangJianguo
	 * 2015-1-14 上午10:59:49
	 */
	@Override
	public int getCount(String typeuid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.typeuid, typeuid);
		return dao.getCount(map);
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.TeamSO#getList(java.lang.String, com.genius.model.base.entity.Pager)
	 * @author: ShangJianguo
	 * 2015-1-14 上午11:07:24
	 */
	@Override
	public List<Team> getList(String typeuid, Pager pager) {
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.typeuid, typeuid);
		List<Team> list = dao.getList(map);
		int num = 0;
		for (Team team : list) {
			num = wishSO.getCount(team.getUseruid(), null, EWishType.wantMergers, EChannel.project);
			team.setWishprojNum(num);
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.TeamSO#getList(java.util.List)
	 * @author: ShangJianguo
	 * 2015-1-15 下午6:58:19
	 */
	@Override
	public List<Team> getList(List<String> ids) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.ids, ids);
		return dao.getList(map);
		
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.TeamSO#getListByRecommend(com.genius.model.base.entity.Pager)
	 */
	@Override
	public List<Team> getListByRecommend(Pager pager) {
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.isrecommend, true);
		map.put(MapperDict.orderBy, MapperDict.rectime);
		List<Team> list = dao.getList(map);
		for (Team team : list) {
			Type type = typeSO.get(team.getTypeuid());
			team.setTypeName(type.getName());
		}
		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.genius.xo.base.serviceimpl.BaseSOImpl#get(java.lang.String)
	 * @author: ShangJianguo
	 * 2015-1-18 下午3:44:28
	 */
	@Override
	public Team get(String id) {
		Team team = super.get(id);
		if (team == null) {
			return team;
		}
		Type type = typeSO.get(team.getTypeuid());
		if (type != null) {
			team.setTypeName(type.getName());
		}
		int wishme = wishSO.getCount(null, team.getUseruid(), EWishType.attention, EChannel.team);
		team.setWishmeNum(wishme);
		int wishprojNum = wishSO.getCount(team.getUseruid(), null, EWishType.wantMergers, EChannel.project);
		team.setWishprojNum(wishprojNum);
		return team;
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.TeamSO#getUseruid(java.lang.String)
	 */
	@Override
	public Team getUseruid(String useruid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, useruid);
		return dao.getOne(map);
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.TeamSO#deleteFundnees(java.lang.String, java.lang.String)
	 * @author: ShangJianguo
	 * 2015-1-20 下午3:54:19
	 */
	@Override
	public boolean deleteFundneeds(String teamuid, String funduid) {
		Team team = dao.get(teamuid);
		if (team != null) {
			List<Notice> funds = team.getFundneeds();
			for (int i=0; i<funds.size(); i++) {
				if (funds.get(i).getUid().equals(funduid)) {
					funds.remove(i);
					break;
				}
			}
			Map<String, Object> map = new HashMap<>();
			map.put(MapperDict.uid, teamuid);
			map.put(MapperDict.fundneeds, toMapList(funds));
			return dao.updateFields(map);
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.TeamSO#addFundneed(java.lang.String, com.supergenius.xo.tpi.entity.Notice)
	 * @author: ShangJianguo
	 * 2015-1-20 下午4:16:33
	 */
	@Override
	public boolean addFundneed(String teamuid, Notice fundneed) {
		Team team = dao.get(teamuid);
		List<Notice> funds = team.getFundneeds();
		funds = (funds == null ? new ArrayList<Notice>() : funds);
		funds.add(fundneed);
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.uid, teamuid);
		map.put(MapperDict.fundneeds, toMapList(funds));
		return dao.updateFields(map);
	}
	
	private List<Map<String, Object>> toMapList(List<Notice> list){
		List<Map<String, Object>> mapList = new ArrayList<>();
		for (Notice notice : list) {
			mapList.add(MapsUtil.toMap(notice, "fundneed"));
		}
		return mapList;
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.TeamSO#updateField(java.lang.String, java.lang.String, java.lang.Object)
	 * @author: ShangJianguo
	 * 2015-1-25 下午5:02:16
	 */
	@Override
	public boolean updateField(String teamuid, List<String> initmemuids) {
		Map<String, Object> map = new HashMap<>();
		Team team = dao.get(teamuid);
		if (team != null) {
			List<String> initList = team.getInitmemuids() == null ? new ArrayList<String>() : team.getInitmemuids();
			for (String uid : initmemuids) {
				if (!initList.contains(uid)) {
					initList.add(uid);
				}
			}
			map.put(MapperDict.uid, teamuid);
			map.put(MapperDict.initmemuids, initList);
			return dao.updateFields(map);
		}
		return false;
	}
	
	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.TeamSO#updateMember(java.lang.String, java.util.List, java.util.List)
	 */
	@Override
	public boolean updateMember(String uid, List<String> useruids) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.uid, uid);
		map.put(MapperDict.memberuids, useruids);
		return dao.updateFields(map);
	}
	
	@Override
	public List<Team> getJoinedList(String memberuid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.memberuids, memberuid);
		return dao.getList(map);
	}
	
	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.TeamSO#getJoinedList(java.lang.String, com.genius.model.base.entity.Pager)
	 */
	@Override
	public List<Team> getJoinedList(String memberuid, Pager pager) {
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.memberuids, memberuid);
		return dao.getList(map);
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.TeamSO#saveEdit(com.supergenius.xo.tpi.entity.Team, org.springframework.web.multipart.MultipartFile)
	 * @author: ShangJianguo
	 * 2015-1-26 上午11:11:38
	 */
	@Override
	public boolean update(Team team, String[] imgs) {
		Team oldTeam = dao.get(team.getUid());
		if (imgs != null) {
			oldTeam.setImgoriginal(imgs[0]);
			oldTeam.setImgbig(imgs[1]);
			oldTeam.setImgmedium(imgs[2]);
			oldTeam.setImglittle(imgs[3]);
		}
		oldTeam.setContactinfo(team.getContactinfo());
		oldTeam.setSummary(team.getSummary());
		oldTeam.setDeclaration(team.getDeclaration());
		oldTeam.setFundneed(team.getFundneed());
		return dao.update(oldTeam);
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.TeamSO#add(com.supergenius.xo.tpi.entity.Team, java.lang.String[], java.lang.String[])
	 * @author: ShangJianguo
	 * 2015-1-26 下午2:28:54
	 */
	@Override
	public boolean add(Team team, String[] imgs, String[] achieve, User user) {
		team.setUseruid(user.getUid());
		team.setUsername(user.getName());
		List<String> memberList = new ArrayList<>();
		memberList.add(user.getUid());
		team.setMemberuids(memberList);
		team.setCreatetime(DateTime.now());
		if (imgs != null) {
			team.setImgoriginal(imgs[0]);
			team.setImgbig(imgs[1]);
			team.setImgmedium(imgs[2]);
			team.setImglittle(imgs[3]);
		}
		if (achieve != null) {
			List<Link> achievements = new ArrayList<>();
			String[] datas = null;
			Link tempLink = null;
			for (String item : achieve) {
				datas = item.split("s_m_t_a");
				tempLink = new Link();
				tempLink.setYear(Integer.parseInt(datas[0]));
				tempLink.setMonth(Integer.parseInt(datas[1]));
				tempLink.setTitle(datas[2]);
				tempLink.setHref(datas[3]);
				tempLink.setUid(GlobalUtil.getUUID());
				achievements.add(tempLink);
			}
			team.setAchievements(achievements);
		}
		return dao.insert(team);
	}
	
	@Override
	public int getJoinedCount(String memberuid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.memberuids, memberuid);
		return dao.getCount(map);
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.TeamSO#addAchieve(java.lang.String, com.supergenius.xo.tpi.entity.Link)
	 * @author: ShangJianguo
	 * 2015-1-26 下午4:33:45
	 */
	@Override
	public boolean addAchieve(String teamuid, Link achieve) {
		Team team = dao.get(teamuid);
		List<Link> achieves = team.getAchievements();
		achieve.setUid(GlobalUtil.getUUID());
		achieves = (achieves == null ? new ArrayList<Link>() : achieves);
		achieves.add(achieve);
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.uid, teamuid);
		map.put(MapperDict.achievements, toAchieveMapList(achieves, "achivement"));
		return dao.updateFields(map);
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.TeamSO#deleteAchieve(java.lang.String, java.lang.String)
	 * @author: ShangJianguo
	 * 2015-1-26 下午4:33:45
	 */
	@Override
	public boolean deleteAchieve(String teamuid, String achieveuid) {
		Team team = dao.get(teamuid);
		if (team != null) {
			List<Link> achieves = team.getAchievements();
			for (int i=0; i<achieves.size(); i++) {
				if (achieves.get(i).getUid().equals(achieveuid)) {
					achieves.remove(i);
					break;
				}
			}
			Map<String, Object> map = new HashMap<>();
			map.put(MapperDict.uid, teamuid);
			map.put(MapperDict.achievements, toAchieveMapList(achieves, "achivement"));
			return dao.updateFields(map);
		}
		return false;
	}
	
	/**
	 * 根据策略转化为相应的Map
	 * @param list
	 * @return
	 * @author ShangJianguo
	 */
	private List<Map<String, Object>> toAchieveMapList(List<Link> list, String strategy){
		List<Map<String, Object>> mapList = new ArrayList<>();
		for (Link achieve : list) {
			mapList.add(MapsUtil.toMap(achieve, strategy));
		}
		return mapList;
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.TeamSO#delMember(java.lang.String, int, java.lang.String)
	 * @author: ShangJianguo
	 * 2015-2-2 下午12:35:01
	 */
	@Override
	public boolean delMember(String teamuid, int status, String memuid) {
		Team team = dao.get(teamuid);
		if (team != null) {
			List<String> uids = null;
			Map<String, Object> map = new HashMap<>();
			map.put(MapperDict.uid, teamuid);
			if (status == 0) { // 已经同意的成员
				uids = team.getInitmemuids();
				uids.remove(memuid);
				map.put(MapperDict.initmemuids, uids);
			} else if (status == 1) { // 已经邀请未同意的成员 
				uids = team.getMemberuids();
				uids.remove(memuid);
				map.put(MapperDict.memberuids, uids);
			}
			return dao.updateFields(map);
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.genius.xo.base.service.BaseSO#getList(java.lang.Boolean, com.genius.model.base.entity.Pager)
	 * @author: ShangJianguo
	 * 2015-2-11 下午4:30:13
	 */
	@Override
	public List<Team> getList(Boolean istop, Pager pager) {
		if (istop == null) {
			return getList(pager);
		}
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.istop, istop);
		return dao.getList(map);
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.TeamSO#getList(java.util.List, boolean, com.genius.model.base.entity.Pager)
	 * @author: ShangJianguo
	 * 2015-2-11 下午4:38:57
	 */
	@Override
	public List<Team> getList(List<String> exclude, Boolean istop, Pager pager) {
		if (exclude == null || exclude.isEmpty()) {
			return getList(istop, pager);
		}
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.uid + MapperDict.suffix_nin_key, exclude);
		if (istop != null) {
			map.put(MapperDict.istop, istop);
		}
		return dao.getList(map);
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.TeamSO#getList(java.lang.String, java.lang.Boolean, com.genius.model.base.entity.Pager)
	 * @author: ShangJianguo
	 * 2015-2-11 下午5:55:37
	 */
	@Override
	public List<Team> getList(String typeuid, Boolean istop, Pager pager) {
		if (istop == null) {
			return getList(typeuid, pager);
		}
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.typeuid, typeuid);
		map.put(MapperDict.istop, istop);
		return dao.getList(map);
	}
	
}
