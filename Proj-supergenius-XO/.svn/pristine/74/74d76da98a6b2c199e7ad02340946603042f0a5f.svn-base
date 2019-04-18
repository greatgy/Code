package com.supergenius.xo.moral.service;

import java.util.List;

import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.moral.entity.ScoreDetail;
import com.supergenius.xo.moral.enums.EScoreDetail;

/**
 *  积分明显so
 * @author liushaomin
 */
public interface ScoreDetailSO extends BaseSO<ScoreDetail>{

	/**
	 * 通过会员uid,积分类别,当前时间获取 今天 积分明细记录
	 * @param useruid
	 * @param type
	 * @param today
	 * @return
	 * @author YuYingJie
	 */
	List<ScoreDetail> getList(String useruid, EScoreDetail type, String today);

}
