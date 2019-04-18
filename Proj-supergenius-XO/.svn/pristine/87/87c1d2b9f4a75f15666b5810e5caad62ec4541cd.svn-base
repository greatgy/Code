package com.supergenius.xo.tpi.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.tpi.constants.MapperDict;
import com.supergenius.xo.tpi.dao.TpiUserDao;
import com.supergenius.xo.tpi.entity.Project;
import com.supergenius.xo.tpi.entity.TpiUser;
import com.supergenius.xo.tpi.entity.Wish;
import com.supergenius.xo.tpi.enums.EInvestType;
import com.supergenius.xo.tpi.enums.EMergeType;
import com.supergenius.xo.tpi.enums.EPayType;
import com.supergenius.xo.tpi.enums.ETpiUserType;
import com.supergenius.xo.tpi.enums.ETpiuserState;
import com.supergenius.xo.tpi.enums.EWishType;
import com.supergenius.xo.tpi.service.MessageSO;
import com.supergenius.xo.tpi.service.ProjectSO;
import com.supergenius.xo.tpi.service.TpiuserSO;
import com.supergenius.xo.tpi.service.WishSO;

/**
 * 会员管理SO实现
 * 
 * @author ShangJianguo
 */
@Service
public class TpiuserSOImpl extends BaseSOImpl<TpiUser> implements TpiuserSO {

	@Autowired
	TpiUserDao dao;

	@Autowired
	private WishSO wishSO;

	@Autowired
	private ProjectSO projectSO;

	@Autowired
	private MessageSO MsgSO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.genius.xo.base.serviceimpl.BaseSOImpl#getDao()
	 * 
	 * @author: ShangJianguo 2015-1-6 下午5:38:15
	 */
	@Override
	protected BaseDao<TpiUser> getDao() {

		return dao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.genius.xo.base.serviceimpl.BaseSOImpl#update(java.lang.Object)
	 * 
	 * @author: ShangJianguo 2015-1-7 下午12:09:48
	 */
	@Override
	public boolean update(TpiUser t) {
		return dao.update(t);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.tpi.service.ArticleSO#updateIstop(java.lang.String[], boolean)
	 * 
	 * @author: ShangJianguo 2015-1-7 下午4:44:26
	 */
	@Override
	public boolean setTop(String[] uids, boolean istop) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.istop, istop);
		for (String item : uids) {
			map.put(BaseMapperDict.uid, item);
			dao.updateFields(map);
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.tpi.service.ArticleSO#setStatus(java.lang.String[], int)
	 * 
	 * @author: ShangJianguo 2015-1-7 下午6:18:45
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.tpi.service.TpiuserSO#setIndex(java.lang.String[], boolean)
	 * 
	 * @author: ShangJianguo 2015-1-9 下午1:39:28
	 */
	@Override
	public boolean setIndex(String[] uids, boolean index) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.isindex, index);
		for (String item : uids) {
			map.put(BaseMapperDict.uid, item);
			dao.updateFields(map);
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.tpi.service.TpiuserSO#audit(java.lang.String[], boolean, java.lang.String)
	 * 
	 * @author: ShangJianguo 2015-1-9 下午4:14:12
	 */
	@Override
	public boolean audit(String[] uids, boolean result, String content) {
		Map<String, Object> map = new HashMap<>();
		if (result) {// 审核通过
			map.put(MapperDict.state, ETpiuserState.infosuccess);
		} else {
			map.put(MapperDict.state, ETpiuserState.infofailed);
		}
		for (String item : uids) {
			map.put(BaseMapperDict.uid, item);
			dao.updateFields(map);
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.tpi.service.TpiuserSO#specialaudit(java.lang.String[])
	 * 
	 * @author: ShangJianguo 2015-1-9 下午5:06:57
	 */
	@Override
	public boolean specialaudit(String[] uids, EPayType paytype) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.state, ETpiuserState.payed);
		map.put(MapperDict.paytype, paytype);
		for (String item : uids) {
			map.put(BaseMapperDict.uid, item);
			dao.updateFields(map);
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.tpi.service.TpiuserSO#getOne(com.supergenius.xo.tpi.enums.EMergeType)
	 */
	@Override
	public TpiUser getOne(EMergeType mergetype, ETpiuserState state) {
		Map<String, Object> map = getParamMap();
		if (state != null) {
			map.put(MapperDict.state, state);
		}
		map.put(MapperDict.mergerInfo_type, mergetype);
		TpiUser tpiUser = dao.getOne(map);
		if (tpiUser != null) {
			tpiUser.setWantpronum(wishSO.getCount(tpiUser.getUid(), null, EWishType.wantMergers, EChannel.project));
		}
		return tpiUser;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.tpi.service.TpiuserSO#getCount(com.supergenius.xo.tpi.enums.EMergeType)
	 */
	@Override
	public int getCount(EMergeType mergetype, ETpiuserState state) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.mergerInfo_type, mergetype);
		map.put(MapperDict.state, state);
		return dao.getCount(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.tpi.service.TpiuserSO#getCount(com.supergenius.xo.tpi.enums.ETpiUserType)
	 */
	@Override
	public int getCount(ETpiUserType tpiusertype, boolean isindex) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.type, tpiusertype);
		map.put(MapperDict.isindex, isindex);
		return dao.getCount(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.tpi.service.TpiuserSO#getList(com.genius.model.base.entity.Pager, com.supergenius.xo.tpi.enums.EMergeType)
	 */
	@Override
	public List<TpiUser> getList(Pager pager, EMergeType mergetype, boolean istop, ETpiuserState state) {
		Map<String, Object> map = getParamMap(pager);
		if (istop) {
			map.put(MapperDict.istop, istop);
		}
		if (state != null) {
			map.put(MapperDict.state, state);
		}
		map.put(MapperDict.mergerInfo_type, mergetype);
		List<TpiUser> tpiUsers = dao.getList(map);
		for (TpiUser item : tpiUsers) {
			item.setWantpronum(wishSO.getCount(item.getUid(), null, EWishType.wantMergers, EChannel.project));
		}
		return tpiUsers;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.tpi.service.TpiuserSO#getList(boolean, com.supergenius.xo.tpi.enums.ETpiUserType, com.genius.model.base.entity.Pager)
	 * 
	 * @author: ShangJianguo 2015-1-15 下午1:54:52
	 */
	@Override
	public List<TpiUser> getList(boolean istop, ETpiUserType type, Pager pager, ETpiuserState state) {
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.istop, istop);
		map.put(MapperDict.type, type);
		map.put(MapperDict.state, state);
		return dao.getList(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.tpi.service.TpiuserSO#getList(com.supergenius.xo.tpi.enums.ETpiUserType, com.genius.model.base.entity.Pager)
	 */
	@Override
	public List<TpiUser> getList(ETpiUserType tpiusertype, boolean isindex, Pager pager, ETpiuserState state) {
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.type, tpiusertype);
		map.put(MapperDict.isindex, isindex);
		map.put(MapperDict.state, state);
		List<TpiUser> tpiUsers = dao.getList(map);
		for (TpiUser item : tpiUsers) {
			item.setWantpronum(wishSO.getCount(item.getUid(), null, EWishType.wantMergers, EChannel.project));
		}
		return tpiUsers;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.tpi.service.TpiuserSO#getList(java.util.Map, com.genius.model.base.entity.Pager)
	 * 
	 * @author: ShangJianguo 2015-1-15 下午3:13:52
	 */
	@Override
	public List<TpiUser> getList(Map<String, Object> queryMap, Pager pager) {
		Map<String, Object> map = getParamMap(pager);
		map.putAll(queryMap);
		return dao.getList(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.tpi.service.TpiuserSO#getList(boolean, com.supergenius.xo.tpi.enums.EInvestType, com.genius.model.base.entity.Pager)
	 * 
	 * @author: ShangJianguo 2015-1-15 下午3:31:56
	 */
	@Override
	public List<TpiUser> getList(boolean istop, EInvestType type, Pager pager, ETpiuserState state) {
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.istop, istop);
		map.put(MapperDict.type, ETpiUserType.investment);
		map.put(MapperDict.investInfo + MapperDict.dot + MapperDict.type, type);
		map.put(MapperDict.state, state);
		List<TpiUser> list = dao.getList(map);
		for (TpiUser user : list) {
			int count = wishSO.getCount(user.getUid(), null, EWishType.wantInvestment, EChannel.project);
			user.setWantpronum(count);
			int support = wishSO.getCount(user.getUid(), null, EWishType.attention, EChannel.project);
			user.setPraisenum(support);
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.tpi.service.TpiuserSO#getList(com.supergenius.xo.tpi.enums.EInvestType, com.genius.model.base.entity.Pager)
	 * 
	 * @author: ShangJianguo 2015-1-15 下午3:41:06
	 */
	@Override
	public List<TpiUser> getList(EInvestType type, Pager pager, ETpiuserState state) {
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.type, ETpiUserType.investment);
		map.put(MapperDict.investInfo + MapperDict.dot + MapperDict.type, type);
		map.put(MapperDict.state, state);
		return dao.getList(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.tpi.service.TpiuserSO#getList(java.util.List)
	 * 
	 * @author: ShangJianguo 2015-1-15 下午7:03:49
	 */
	@Override
	public List<TpiUser> getList(List<String> uids) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.ids, uids);
		return dao.getList(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.tpi.service.TpiuserSO#getWishProjects(java.lang.String)
	 * 
	 * @author: ShangJianguo 2015-1-19 下午1:58:53
	 */
	@Override
	public List<Project> getWishProjects(String useruid, EWishType wishType, Pager pager) {
		List<Wish> wishs = wishSO.getList(useruid, null, wishType, EChannel.project, pager);
		if (wishs.size() == 0) {
			return new ArrayList<>();
		}
		List<String> projectuids = new ArrayList<>();
		for (Wish wish : wishs) {
			projectuids.add(wish.getTouid());
		}
		return projectSO.getList(projectuids);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.tpi.service.TpiuserSO#getByEmail(java.lang.String)
	 */
	@Override
	public TpiUser getByEmail(String email) {
		Map<String, Object> map = getParamMap(true);
		map.put(MapperDict.email, email);
		return dao.getOne(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.tpi.service.TpiuserSO#getByEmailLogin(java.lang.String)
	 */
	@Override
	public TpiUser getByEmailLogin(String email) {
		Map<String, Object> map = getParamMap(true);
		map.put(MapperDict.email, email);
		map.put(MapperDict.state + MapperDict.suffix_greater_key, ETpiuserState.emailvalid);
		return dao.getOne(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.tpi.service.TpiuserSO#validemail(java.lang.String)
	 * 
	 * @author: ShangJianguo 2015-1-23 上午11:07:33
	 */
	@Override
	public boolean validemail(String uid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.uid, uid);
		map.put(MapperDict.state, ETpiuserState.completeinfo);
		return dao.updateFields(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.tpi.service.TpiuserSO#isUniqueEmail(java.lang.String)
	 * 
	 * @author: ShangJianguo 2015-1-27 上午11:44:41
	 */
	@Override
	public boolean isUniqueEmail(String email) {
		return getByEmail(email) == null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.tpi.service.TpiuserSO#updatePwd(com.supergenius.xo.tpi.entity.TpiUser)
	 */
	@Override
	public boolean updatePwd(TpiUser tpiUser) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.uid, tpiUser.getUid());
		map.put(MapperDict.password, tpiUser.getPassword());
		return dao.updateFields(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.tpi.service.TpiuserSO#updateAvatar(com.supergenius.xo.tpi.entity.TpiUser)
	 */
	@Override
	public boolean updateAvatar(TpiUser tpiUser) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.uid, tpiUser.getUid());
		map.put(MapperDict.avatarorigin, tpiUser.getAvatarorigin());
		map.put(MapperDict.avatarbig, tpiUser.getAvatarorigin());
		map.put(MapperDict.avatarmidium, tpiUser.getAvatarmidium());
		map.put(MapperDict.avatarlittle, tpiUser.getAvatarlittle());
		if (dao.update(tpiUser)) {
			Map<String, Object> wheremap = new HashMap<>();
			wheremap.put(MapperDict.fromuseruid, tpiUser.getUid());
			Map<String, Object> map1 = new HashMap<>();
			map1.put(MapperDict.fromuseravatar, tpiUser.getAvatarlittle());
			MsgSO.update(wheremap, map1);
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.tpi.service.TpiuserSO#getList(com.supergenius.xo.tpi.enums.ETpiUserType, com.genius.model.base.entity.Pager)
	 * 
	 * @author: ShangJianguo 2015-2-9 上午10:26:09
	 */
	@Override
	public List<TpiUser> getList(ETpiUserType type, Pager pager) {
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.type, type);
		return dao.getList(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.tpi.service.TpiuserSO#getCount(com.supergenius.xo.tpi.enums.ETpiUserType)
	 * 
	 * @author: ShangJianguo 2015-2-9 上午10:37:23
	 */
	@Override
	public int getCount(ETpiUserType type) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.type, type);
		map.put(MapperDict.state, ETpiuserState.payed);
		return dao.getCount(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.tpi.service.TpiuserSO#getList(com.supergenius.xo.tpi.enums.ETpiUserType, com.genius.model.base.entity.Pager, com.supergenius.xo.tpi.enums.ETpiuserState)
	 * 
	 * @author: ShangJianguo 2015-2-10 上午10:28:28
	 */
	@Override
	public List<TpiUser> getList(ETpiUserType type, Pager pager, ETpiuserState state) {
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.type, type);
		map.put(MapperDict.state, state);
		return dao.getList(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.tpi.service.TpiuserSO#getCount(com.supergenius.xo.tpi.enums.ETpiUserType, com.supergenius.xo.tpi.enums.ETpiuserState)
	 * 
	 * @author: ShangJianguo 2015-2-13 下午12:02:21
	 */
	@Override
	public int getCount(ETpiUserType type, ETpiuserState state) {
		if (state == null) {
			return getCount(type);
		}
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.type, type);
		map.put(MapperDict.state, state);
		return dao.getCount(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.tpi.service.TpiuserSO#updateTpiUserStatus(java.lang.String)
	 * 
	 * @author: LiJiacheng 2015-10-30 下午2:11:43
	 */
	@Override
	public boolean updateTpiUserStatus(String uid) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.uid, uid);
		map.put(MapperDict.state, "5");
		return dao.updateFields(map);
	}

}
