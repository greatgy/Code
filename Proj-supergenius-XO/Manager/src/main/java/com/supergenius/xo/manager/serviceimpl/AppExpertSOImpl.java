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
import com.supergenius.xo.manager.dao.AppExpertDao;
import com.supergenius.xo.manager.entity.AppExpert;
import com.supergenius.xo.manager.entity.AppExpertDetail;
import com.supergenius.xo.manager.enums.EAppExpert;
import com.supergenius.xo.manager.enums.EAppExpertStage;
import com.supergenius.xo.manager.enums.EExpertLevel;
import com.supergenius.xo.manager.enums.EMajor;
import com.supergenius.xo.manager.service.AppExpertDetailSO;
import com.supergenius.xo.manager.service.AppExpertSO;

/** 
 * 专家申请SOImpl
 * @author guanshiqian
 * @date 2016-4-29 上午10:24:58 
 */
@Service
public class AppExpertSOImpl extends BaseSOImpl<AppExpert> implements AppExpertSO {

	@Autowired
	AppExpertDao dao;
	
	@Autowired
	AppExpertDetailSO appExpertDetailSO;
	
	@Autowired
	AdminLogSO adminLogSO;
	
	@Override
	protected BaseDao<AppExpert> getDao() {
		return dao;
	}

	@Override
	public boolean update(AppExpert appExpert, EAppExpertStage stagefrom, EAppExpertStage stageto) {
		AppExpertDetail appExpertDetail = new AppExpertDetail(appExpert.getUseruid(), appExpert.getUid(), stagefrom, stageto, appExpert.getFile());
		return dao.update(appExpert) && appExpertDetailSO.add(appExpertDetail);
	}

	@Override
	public boolean add(AppExpert appExpert, EAppExpertStage stagefrom, EAppExpertStage stageto) {
		AppExpertDetail appExpertDetail = new AppExpertDetail(appExpert.getUseruid(), appExpert.getUid(), stagefrom, stageto, appExpert.getFile());
		return dao.insert(appExpert) && appExpertDetailSO.add(appExpertDetail);
	}

	@Override
	public AppExpert getOne(String userUid, EMajor major, EExpertLevel levelTo) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, userUid);
		map.put(MapperDict.major, major);
		map.put(MapperDict.levelto, levelTo);
		return dao.getOne(map);
		
	}

	@Override
	public AppExpert getOneByLevelFrom(String userUid, EMajor major, EExpertLevel levelFrom) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, userUid);
		map.put(MapperDict.major, major);
		map.put(MapperDict.levelfrom, levelFrom);
		return dao.getOne(map);
	}

	@Override
	public AppExpert getOne(String userUid, EMajor major, EAppExpert type) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, userUid);
		map.put(MapperDict.major, major);
		map.put(MapperDict.type, type);
		return dao.getOne(map);
	}

	@Override
	public int getCount(EExpertLevel levelto) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.levelto, levelto);
		return dao.getCount(map);
	}

	@Override
	public boolean updateStage(AppExpert appExpert, AppExpertDetail appExpertDetail, AdminLog adminLog) {
		if(adminLog != null) {
			adminLogSO.add(adminLog);
		}
		return dao.update(appExpert) && appExpertDetailSO.add(appExpertDetail);
	}

	@Override
	public List<AppExpert> getList(EAppExpertStage stage) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.stage, stage);
		return dao.getList(map);
	}

}
