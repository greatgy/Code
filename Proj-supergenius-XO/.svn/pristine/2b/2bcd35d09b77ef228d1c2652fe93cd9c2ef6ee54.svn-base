package com.supergenius.xo.manager.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.core.base.utils.StrUtil;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.genius.xo.baseadmin.service.AdminLogSO;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.manager.dao.AppReplyDao;
import com.supergenius.xo.manager.entity.AppReply;
import com.supergenius.xo.manager.entity.AppReplyDetail;
import com.supergenius.xo.manager.entity.AppReplyExpert;
import com.supergenius.xo.manager.entity.Certificate;
import com.supergenius.xo.manager.entity.Conference;
import com.supergenius.xo.manager.enums.EMajor;
import com.supergenius.xo.manager.enums.EReplyStage;
import com.supergenius.xo.manager.enums.EStudentLevel;
import com.supergenius.xo.manager.service.AppReplyDetailSO;
import com.supergenius.xo.manager.service.AppReplyExpertSO;
import com.supergenius.xo.manager.service.AppReplySO;
import com.supergenius.xo.manager.service.CertificateSO;
import com.supergenius.xo.manager.service.ConferenceSO;
import com.supergenius.xo.user.entity.TradeDetail;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.service.TradeDetailSO;
import com.supergenius.xo.user.service.UserSO;

/**
 * 答辩SOImpl
 * @author XieMing
 * @date 2016-4-29 下午3:18:22
 */
@Service
public class AppReplySOImpl extends BaseSOImpl<AppReply> implements AppReplySO {

	@Autowired
	AppReplyDao dao;
	@Autowired
	UserSO userSO;
	@Autowired
	TradeDetailSO tredeDetailSO;
	@Autowired
	AppReplyDetailSO appReplyDetailSO;
	@Autowired
	AdminLogSO adminLogSO;
	@Autowired
	AppReplyExpertSO appReplyExpertSO;
	@Autowired
	CertificateSO certificateSO;
	@Autowired
	ConferenceSO conferenceSO;

	@Override
	protected BaseDao<AppReply> getDao() {
		return dao;
	}

	@Override
	public List<AppReply> getList(EMajor major, String useruid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, useruid);
		map.put(MapperDict.major, major);
		return dao.getList(map);
	}

	@Override
	public AppReply getOneByMajorAndLevel(EMajor major, String useruid, EStudentLevel majorlevel) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.major, major);
		map.put(MapperDict.useruid, useruid);
		map.put(MapperDict.majorlevel, majorlevel);
		return dao.getOne(map);
	}

	@Override
	public List<AppReply> getList(String useruid, EReplyStage replystage, EStudentLevel majorlevel) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.replystage, replystage);
		map.put(MapperDict.useruid, useruid);
		map.put(MapperDict.majorlevel, majorlevel);
		return dao.getList(map);
	}

	@Override
	public boolean updateReplyStage(AppReply appReply) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.uid, appReply.getUid());
		map.put(MapperDict.replystage, appReply.getReplystage());
		AppReplyDetail appReplyDetail = appReply.getAppReplyDetail();
		if(appReplyDetail != null) {
			appReplyDetailSO.add(appReplyDetail);
		}
		return dao.updateFields(map);
	}

	@Override
	public boolean updateStagePayed(AppReply appReply, TradeDetail tradeDetail) {
		AppReplyDetail appReplyDetail = null;
		if(EReplyStage.waitRepay.equals(appReply.getReplystage())) {
			appReply.setReplystage(EReplyStage.checkReplyData);
		} else {
			appReply.setReplystage(EReplyStage.checkData);
		}
		User user = userSO.get(appReply.getUseruid());
		user.subtractAccount(tradeDetail.getMoney());
		user.setTotalpay(user.getTotalpay() + tradeDetail.getMoney());
		return userSO.updateAccountAndTotalpay(user) && tredeDetailSO.add(tradeDetail) && updateReplyStage(appReply) && appReplyDetailSO.add(appReplyDetail);
	}

	@Override
	public boolean updateUploadData(EReplyStage stageFrom, AppReply appReply) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.uid, appReply.getUid());
		map.put(MapperDict.file, appReply.getFile());
		map.put(MapperDict.opentimes, appReply.getOpentimes());
		if(StrUtil.isNotEmpty(appReply.getReplytimes())) {
			map.put(MapperDict.replytimes, appReply.getReplytimes());
		}
		if(StrUtil.isNotEmpty(appReply.isIsvideoreply()+"")) {
			map.put(MapperDict.isvideoreply, appReply.isIsvideoreply());
		}
		map.put(MapperDict.replystage, appReply.getReplystage());
		map.put(MapperDict.isvideotopic, appReply.isIsvideotopic());
		AppReplyDetail appReplyDetail = new AppReplyDetail(appReply.getUseruid(), null, appReply.getUid(), null, null, appReply.getFile(), null, appReply.getReplytimes(), appReply.isIsvideoreply(), stageFrom, appReply.getReplystage());
		appReplyDetailSO.add(appReplyDetail);
		return dao.updateFields(map);
	}

	@Override
	public AppReply getOneByMajor(String useruid, EMajor major) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, useruid);
		map.put(MapperDict.major, major);
		return dao.getOne(map);
	}

	@Override
	public AppReply getOneByMajorAndStage(String useruid, EMajor major, EReplyStage replystage) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, useruid);
		map.put(MapperDict.major, major);
		map.put(MapperDict.replystage, replystage);
		return dao.getOne(map);
	}

	@Override
	public List<AppReply> getList(String useruid, EMajor major, EReplyStage replystage) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, useruid);
		map.put(MapperDict.major, major);
		map.put(MapperDict.replystage, replystage);
		return dao.getList(map);
	}

	@Override
	public int getCount(String useruid, EMajor major, EReplyStage replystage) {
		Map<String , Object> map = getParamMap();
		map.put(MapperDict.major, major);
		map.put(MapperDict.useruid, useruid);
		map.put(MapperDict.replystage, replystage);
		return dao.getCount(map);
	}

	@Override
	public AppReply getOne(String uid, EMajor major, EReplyStage replystage) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, uid);
		map.put(MapperDict.major, major);
		map.put(MapperDict.replystage, replystage);
		return dao.getOne(map);
	}

	@Override
	public List<AppReply> getList(EReplyStage replyStage) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.replystage, replyStage);
		return dao.getList(map);
	}

	@Override
	public int searchCount(Map<String, Object> map) {
		Map<String, Object> newMap = getParamMap(true);
		newMap.putAll(map);
		return dao.searchCount(map);
	}

	@Override
	public List<AppReply> searchList(Map<String, Object> map) {
		Map<String, Object> newMap = getParamMap(true);
		newMap.putAll(map);
		return dao.searchList(newMap);
	}

	@Override
	public boolean update(AppReply appReply, AppReplyDetail appReplyDetail, AdminLog adminLog) {
		return dao.update(appReply) && appReplyDetailSO.add(appReplyDetail) && adminLogSO.add(adminLog);
	}

	@Override
	public AppReply getOneByCertificate(String uid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.certificated, uid);
		return dao.getOne(map);
	}

	@Override
	public boolean update(AppReply appReply, List<AppReplyExpert> list, AppReplyDetail appReplyDetail, AdminLog adminLog) {
		boolean flag = false;
		for (AppReplyExpert appReplyExpert : list) {
			appReplyExpertSO.add(appReplyExpert);
			flag = true;
		}
		return dao.update(appReply) && appReplyDetailSO.add(appReplyDetail) && adminLogSO.add(adminLog) && flag;
	}

	@Override
	public boolean update(AppReply appReply, AppReplyDetail appReplyDetail, AdminLog adminLog, Certificate certificate) {
		return dao.update(appReply) && appReplyDetailSO.add(appReplyDetail) && adminLogSO.add(adminLog) && certificateSO.add(certificate);
	}

	@Override
	public boolean update(AppReply appReply, AdminLog adminLog) {
		return dao.update(appReply) && adminLogSO.add(adminLog);
	}

	@Override
	public boolean update(AppReply appReply, AppReplyDetail appReplyDetail) {
		return dao.update(appReply) && appReplyDetailSO.add(appReplyDetail);
	}

	@Override
	public boolean update(AppReply appReply, Conference conference) {
		return dao.update(appReply) && conferenceSO.add(conference);
	}
}
