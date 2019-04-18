package com.supergenius.xo.manager.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.manager.dao.AppJudgementDetailDao;
import com.supergenius.xo.manager.entity.AppJudgementDetail;
import com.supergenius.xo.manager.enums.EAppJudgementStage;
import com.supergenius.xo.manager.service.AppJudgementDetailSO;

/** 
 * 裁判申请明细Impl
 * @author guanshiqian
 * @date 2016-4-28 下午12:29:23 
 */
@Service
public class AppJudgementDetailSOImpl extends BaseSOImpl<AppJudgementDetail> implements AppJudgementDetailSO {

	@Autowired
	AppJudgementDetailDao dao;
	
	@Override
	protected BaseDao<AppJudgementDetail> getDao() {
		return dao;
	}

	@Override
	public int getCount(EAppJudgementStage stageto) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.stageto, stageto);
		return dao.getCount(map);
	}

	@Override
	public List<String> getFileList(String appjudgementuid, List<EAppJudgementStage> stages) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.appjudgementuid, appjudgementuid);
		map.put(MapperDict.stagefrom + MapperDict.suffix_in_key, stages);
		return dao.getFileList(map);
	}

}
