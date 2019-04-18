package com.supergenius.xo.manager.dao;

import java.util.List;
import java.util.Map;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.manager.entity.AppJudgementDetail;

/** 
 * 裁判申请明细Dao
 * @author guanshiqian
 * @date 2016-4-28 下午12:24:54 
 */
public interface AppJudgementDetailDao extends BaseDao<AppJudgementDetail> {
	
	/**
	 * 获取某个裁判申请期间的所有材料
	 * @param appJudgementUid
	 * @return
	 * @author XieMing
	 * 2016-10-21 下午6:37:51
	 */
	List<String> getFileList(Map<String, Object> map);
	
}
