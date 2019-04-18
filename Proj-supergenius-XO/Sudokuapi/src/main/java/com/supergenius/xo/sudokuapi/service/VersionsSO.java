package com.supergenius.xo.sudokuapi.service;

import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.sudokuapi.entity.Versions;

/**
 * 版本信息SO
 * 
 * @CreateTime 2018年5月23日--下午7:00:55
 * @author LiuBin
 */
public interface VersionsSO extends BaseSO<Versions> {

	/**
	 * 根据company得到最高版本的包
	 * @param company
	 * @return
	 * @CreateTime  2018年5月24日--上午11:55:14
	 * @Author  LiuBin
	 */
	Versions getOne(String company, String code);
}
