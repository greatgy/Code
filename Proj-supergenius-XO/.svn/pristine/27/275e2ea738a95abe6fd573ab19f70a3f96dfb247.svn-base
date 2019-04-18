package com.supergenius.xo.manager.service;

import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.manager.entity.Major;
import com.supergenius.xo.manager.enums.EMajor;

/** 
 * 专业SO
 * @author chenminchang
 * @date 2016-3-18 下午4:47:23 
 */
public interface MajorSO extends BaseSO<Major> {
	
	/**
	 * 根据类型获得一个Major对象
	 * @param type
	 * @return
	 */
	Major getOneByType(EMajor type);
	
	/**
	 * 更新
	 * @param uid
	 * @param status
	 * @return
	 * @author chenminchang
	 * @create 2016-11-2下午7:52:47
	 */
	boolean updateContent(String uid, String content, String summary);
}
