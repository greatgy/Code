package com.supergenius.xo.gupage.rule;

import com.genius.core.cache.rule.CountRule;

/**
 * 顾雏军专栏图片的Rule规则基类
 * @author YangGuang
 * @date 2018年1月12日11:11:03
 */
public class GupagePhotoCountRule extends CountRule{
	protected static String p = "gupage/photo";
	
	public GupagePhotoCountRule() {
		this("", 0);
	}
	
	/**
	 * @param k
	 */
	public GupagePhotoCountRule(String k) {
		this(k, 0);
	}

	/**
	 * 
	 * @param k
	 * @param exp seconds
	 */
	public GupagePhotoCountRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
