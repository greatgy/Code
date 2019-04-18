package com.supergenius.xo.manager.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.manager.dao.AppExpertDetailDao;
import com.supergenius.xo.manager.entity.AppExpertDetail;
import com.supergenius.xo.manager.enums.EAppExpertStage;
import com.supergenius.xo.manager.service.AppExpertDetailSO;

/** 
 * 专家申请明细Impl
 * @author guanshiqian
 * @date 2016-4-29 下午12:05:31 
 */
@Service
public class AppExpertDetailSOImpl extends BaseSOImpl<AppExpertDetail> implements AppExpertDetailSO {

	@Autowired
	AppExpertDetailDao dao;

	@Override
	protected BaseDao<AppExpertDetail> getDao() {
		return dao;
	}

	@Override
	public AppExpertDetail getOne(String userUid, String appExpertUid, EAppExpertStage stageTo) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, userUid);
		map.put(MapperDict.appexpertuid, appExpertUid);
		map.put(MapperDict.stageto, stageTo);
		return dao.getOne(map);
	}

	@Override
	public List<String> getFileList(String uid, List<EAppExpertStage> stages) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.appexpertuid, uid);
		map.put(MapperDict.stagefrom + MapperDict.suffix_in_key, stages);
		return dao.getFileList(map);
	}
}
