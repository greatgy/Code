package com.supergenius.xo.manager.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.genius.xo.baseadmin.service.AdminLogSO;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.manager.dao.AppJudgementDao;
import com.supergenius.xo.manager.entity.AppJudgement;
import com.supergenius.xo.manager.entity.AppJudgementDetail;
import com.supergenius.xo.manager.enums.EAppJudgementStage;
import com.supergenius.xo.manager.enums.EMajor;
import com.supergenius.xo.manager.service.AppJudgementDetailSO;
import com.supergenius.xo.manager.service.AppJudgementSO;

/** 
 * 裁判申请明细SOImpl
 * @author guanshiqian
 * @date 2016-4-28 上午11:55:32 
 */
@Service
public class AppJudgementSOImpl extends BaseSOImpl<AppJudgement> implements AppJudgementSO {

	@Autowired
	AppJudgementDao dao;
	@Autowired
	AppJudgementDetailSO appJudgementDetailSO;
	@Autowired
	AdminLogSO adminLogSO;
	
	@Override
	protected BaseDao<AppJudgement> getDao() {
		return dao;
	}

	@Override
	public AppJudgement getOne(EMajor major, String useruid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.major, major);
		map.put(MapperDict.useruid, useruid);
		return dao.getOne(map);
	}

	@Override
	public boolean update(AppJudgement appJudgement, EAppJudgementStage stageFrom) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.uid, appJudgement.getUid());
		map.put(MapperDict.file, appJudgement.getFile());
		map.put(MapperDict.providetimes, appJudgement.getProvidetimes());
		map.put(MapperDict.isonline, appJudgement.isIsonline());
		map.put(MapperDict.stage, appJudgement.getStage());
		AppJudgementDetail appJudgementDetail = new AppJudgementDetail(appJudgement.getUseruid(), appJudgement.getUid(), appJudgement.getFile(), stageFrom, appJudgement.getStage());
		appJudgementDetailSO.add(appJudgementDetail);
		return dao.updateFields(map);
	}

	@Override
	public boolean updateStage(AppJudgement appJudgement, AppJudgementDetail appJudgementDetail, AdminLog adminLog) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.uid, appJudgement.getUid());
		map.put(MapperDict.stage, appJudgement.getStage());
		map.put(MapperDict.topiccount, appJudgement.getTopiccount());
		map.put(MapperDict.descto, appJudgement.getDescto());
		map.put(MapperDict.providetime, appJudgement.getProvidetime());
		map.put(MapperDict.file2, appJudgement.getFile2());
		if(adminLog != null) {
			adminLogSO.add(adminLog);
		}
		return dao.update(appJudgement) && appJudgementDetailSO.add(appJudgementDetail);
	}

	@Override
	public int getCount(EAppJudgementStage stage) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.stage, stage);
		return dao.getCount(map);
	}

	@Override
	public List<AppJudgement> getList(EAppJudgementStage stage) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.stage, stage);
		return dao.getList(map);
	}
	
}
