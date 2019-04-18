package com.supergenius.xo.sudokuapi.service;

import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.sudokuapi.entity.Share;

/**
 * 分享SO
 * 
 * @CreateTime 2018年5月30日--下午5:46:20
 * @author LiuBin
 */
public interface ShareSO extends BaseSO<Share> {

	/**
	 * 根据version字段排序，获取share对象
	 * @param version
	 * @return
	 * @CreateTime  2018年5月30日--下午5:59:34
	 * @Author  LiuBin
	 */
	Share getOne(String ordery_version);
}
