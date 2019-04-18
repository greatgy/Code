package com.supergenius.xo.official.service;

import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.official.entity.Vote;

/**
 * 投票so
 * @author liushaomin
 */
public interface VoteSO extends BaseSO<Vote>{

	/**
	 * 根据ip得到
	 * @param loginip
	 * @return
	 * @author liushaomin
	 */
	public Vote getLoginIP(String loginip);

}
