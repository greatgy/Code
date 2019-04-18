package com.supergenius.xo.manager.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.genius.xo.baseadmin.dao.AdminLogDao;
import com.supergenius.xo.base.enums.EUser;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.manager.dao.CertificateDao;
import com.supergenius.xo.manager.dao.UserLevelDao;
import com.supergenius.xo.manager.entity.Certificate;
import com.supergenius.xo.manager.entity.UserLevel;
import com.supergenius.xo.manager.enums.ELevelChannel;
import com.supergenius.xo.manager.enums.EMajor;
import com.supergenius.xo.manager.enums.EStudentLevel;
import com.supergenius.xo.manager.service.CertificateSO;
import com.supergenius.xo.manager.service.UserLevelSO;
import com.supergenius.xo.user.dao.ScoreDao;
import com.supergenius.xo.user.dao.ScoreDetailDao;
import com.supergenius.xo.user.dao.TradeDetailDao;
import com.supergenius.xo.user.entity.Score;
import com.supergenius.xo.user.entity.ScoreDetail;
import com.supergenius.xo.user.entity.TradeDetail;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.service.UserSO;

/**
 * 级别明细Impl
 * 
 * @author guanshiqian
 * @date 2016-4-27 下午2:20:43
 */
@Service
public class UserLevelSOImpl extends BaseSOImpl<UserLevel> implements UserLevelSO {

	@Autowired
	private UserLevelDao dao;

	@Autowired
	private ScoreDao scoreDao;

	@Autowired
	private ScoreDetailDao scoreDetailDao;

	@Autowired
	private UserSO userSO;

	@Autowired
	private TradeDetailDao tradeDetailDao;

	@Autowired
	private AdminLogDao adminLogDao;

	@Autowired
	private CertificateDao certificateDao;
	
	@Autowired
	private CertificateSO certificateSO;

	@Override
	protected BaseDao<UserLevel> getDao() {
		return dao;
	}

	@Override
	public int getUserCount(Map<String, Object> map) {
		Map<String, Object> newmap = getParamMap();
		newmap.putAll(map);
		return dao.getUserCount(newmap);
	}

	@Override
	public List<UserLevel> getUserList(Map<String, Object> map) {
		Map<String, Object> newmap = getParamMap();
		if (map.get(MapperDict.pager) != null) {
			Pager pager = (Pager) map.get(MapperDict.pager);
			newmap = getParamMap(pager);
		}
		newmap.putAll(map);
		return dao.getUserList(newmap);
	}

	@Override
	public UserLevel getOne(String userUid, EUser type, EMajor major) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, userUid);
		map.put(MapperDict.type, type);
		map.put(MapperDict.major, major);
		return dao.getOne(map);
	}

	@Override
	public boolean update(User user, Score forwardScore, ScoreDetail forwardScoreDetail, Score originalScore, ScoreDetail originalScoreDetail, Score upGradeScore, ScoreDetail upGradeScoreDetail,
			EStudentLevel studentLevel, EMajor major, TradeDetail tradeDetail) {
		UserLevel preUserLevel = getOne(user.getUid(), EUser.student, major);
		UserLevel userLevel = null;
		if (studentLevel == EStudentLevel.basic) {
			userLevel = new UserLevel(user.getUid(), major, Integer.valueOf(studentLevel.toString()), Integer.valueOf(EStudentLevel.manager.toString()), EUser.student, ELevelChannel.scoreLevel);
		} else {
			userLevel = new UserLevel(user.getUid(), major, Integer.valueOf(studentLevel.toString()), Integer.valueOf(EStudentLevel.majordomo.toString()), EUser.student, ELevelChannel.scoreLevel);
		}
		if (scoreDao.get(upGradeScore.getUid()) == null) {
			scoreDao.insert(upGradeScore);
		} else {
			scoreDao.update(upGradeScore);
		}
		if (forwardScore != null) {
			scoreDao.update(forwardScore);
		}
		if (forwardScoreDetail != null) {
			scoreDetailDao.insert(forwardScoreDetail);
		}
		if (preUserLevel != null) {
			preUserLevel.setStatus(EStatus.disable);
			dao.update(preUserLevel);
		}
		return scoreDao.update(originalScore) && dao.insert(userLevel) && scoreDetailDao.insert(upGradeScoreDetail) && scoreDetailDao.insert(originalScoreDetail) && userSO.update(user)
				&& tradeDetailDao.insert(tradeDetail);
	}

	@Override
	public UserLevel getOne(String uid, EUser type) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, uid);
		map.put(MapperDict.type, type);
		return dao.getOne(map);
	}

	@Override
	public List<UserLevel> getList(String useruid, EUser type, List<ELevelChannel> channels) {
		Map<String, Object> map = getParamMap(true);
		map.put(MapperDict.useruid, useruid);
		map.put(MapperDict.type, type);
		map.put(MapperDict.channel + MapperDict.suffix_in_key, channels);
		map.put(MapperDict.orderBy, MapperDict.sql_order_by + MapperDict.createtime + MapperDict.asc);
		return dao.getList(map);
	}

	@Override
	public int getCountByChannel(ELevelChannel channel) {
		Map<String, Object> map = getParamMap(true);
		map.put(MapperDict.channel, channel);
		return dao.getCount(map);
	}

	@Override
	public int getCount(EMajor major, EUser type) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.major, major);
		map.put(MapperDict.type, type);
		return dao.getCount(map);
	}

	@Override
	public boolean updateStatus(String uid, EStatus status) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.uid, uid);
		map.put(MapperDict.status, status);
		return dao.updateFields(map);
	}

	@Override
	public boolean disableOther(String userUid, EUser type, EMajor major) {
		UserLevel userlevel = getOne(userUid, type, major);
		if (userlevel != null)
			return updateStatus(userlevel.getUid(), EStatus.disable);
		return true;
	}

	@Override
	public List<UserLevel> getList(String userUid, EUser type, EMajor major) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, userUid);
		map.put(MapperDict.type, type);
		map.put(MapperDict.major, major);
		return dao.getList(map);
	}

	@Override
	public List<UserLevel> getList(String userUid, EUser type) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, userUid);
		map.put(MapperDict.type, type);
		map.put(MapperDict.orderBy, MapperDict.sql_order_by + MapperDict.levelto + MapperDict.desc);
		return dao.getList(map);
	}

	@Override
	public int searchCount(Map<String, Object> map) {
		return dao.searchCount(map);
	}

	@Override
	public List<UserLevel> search(Map<String, Object> map) {
		return dao.search(map);
	}

	@Override
	public boolean addAndDisablether(List<UserLevel> userLevels, List<Certificate> addCertificates, List<Certificate> removeCertificates, AdminLog adminLog) {
		for (Certificate certificate : addCertificates) {//插入证书
			certificateDao.insert(certificate);
		}
		for (Certificate certificate : removeCertificates) {//修改原有证书状态
			certificateSO.updateStatus(certificate.getUid(), EStatus.disable);
		}
		for (UserLevel userLevel : userLevels) {//插入userlevel
			disableOther(userLevel.getUseruid(), userLevel.getType(), userLevel.getMajor());
			dao.insert(userLevel);
		}
		adminLogDao.insert(adminLog);
		return true;
	}

	@Override
	public UserLevel getOne(String useruid, EMajor major, int levelTo) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, useruid);
		map.put(MapperDict.major, major);
		map.put(MapperDict.levelto, levelTo);
		return dao.getOne(map);
	}

	@Override
	public boolean update(UserLevel userLevel, UserLevel newUserLevel) {
		return dao.update(userLevel) && dao.insert(newUserLevel);
	}

	@Override
	public UserLevel getOne(String useruid, EStudentLevel level) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, useruid);
		map.put(MapperDict.levelto, level);
		return dao.getOne(map);
	}

}
