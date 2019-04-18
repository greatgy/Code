package com.supergenius.web.admin.user.helper;

import java.util.List;

import com.genius.server.base.helper.BaseHP;
import com.supergenius.global.conf.SysConf;
import com.supergenius.xo.base.enums.EUser;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.manager.entity.UserLevel;
import com.supergenius.xo.manager.enums.EStudentLevel;
import com.supergenius.xo.manager.service.UserLevelSO;
import com.supergenius.xo.user.entity.ScoreDetail;
import com.supergenius.xo.user.enums.EScore;
import com.supergenius.xo.user.service.ScoreDetailSO;
import com.supergenius.xo.user.service.ScoreSO;
import com.supergenius.xo.user.service.UserSO;

/** 
 * 积分明细HP
 * @author chenminchang
 * @date 2016-8-18 下午3:24:11 
 */
public class ScoreDetailHP extends BaseHP {

	private static ScoreDetailSO so;
	private static ScoreSO scoreSO;
	private static UserSO userSO;
	private static UserLevelSO userLevelSO;
	
	private static ScoreDetailSO getSO() {
		if (so == null) {
			so = (ScoreDetailSO) spring.getBean(ScoreDetailSO.class);
		}
		return so;
	}
	
	private static ScoreSO getScoreSO() {
		if (scoreSO == null) {
			scoreSO = (ScoreSO) spring.getBean(ScoreSO.class);
		}
		return scoreSO;
	}
	
	private static UserSO getUserSO() {
		if (userSO == null) {
			userSO = (UserSO) spring.getBean(UserSO.class);
		}
		return userSO;
	}
	
	private static UserLevelSO getUserLevelSO() {
		if (userLevelSO == null) {
			userLevelSO = (UserLevelSO) spring.getBean(UserLevelSO.class);
		}
		return userLevelSO;
	}
	
	/**
	 * 添加一条积分明细
	 * @param useruid
	 * @param score
	 * @param site
	 * @param refuid
	 * @param type
	 * @return
	 * @author chenminchang
	 */
	public static boolean add(String useruid, int score, ESite site, String refuid, EScore type) {
		if (getUserSO().get(useruid) != null) {
			if (getSO().getOne(refuid) == null) {
				ScoreDetail scoreDetail = new ScoreDetail();
				scoreDetail.setUseruid(useruid);
				scoreDetail.setScore(score);
				scoreDetail.setSite(site);
				scoreDetail.setRefuid(refuid);
				scoreDetail.setType(type);
				if (getSO().add(scoreDetail)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 是否可以利用积分晋级
	 * 
	 * @return
	 * @author liubin
	 * @createtime 2016-8-23下午7:41:51
	 * @return boolean
	 */
	public static boolean isCanLevelUpByScore(String userUid) {
		int totalScore = getScoreSO().getTotal(userUid);
		int originalScore = getScoreSO().getTotalByTypes(userUid, EScore.originalFinance);
		List<UserLevel> list = getUserLevelSO().getList(userUid, EUser.student);
		for (UserLevel userLevel : list) {
			EStudentLevel level = EStudentLevel.get(userLevel.getLevelto());
			if (level == EStudentLevel.basic) {
				if (totalScore >= SysConf.FromBasicToManagerUseScore && originalScore >= totalScore * SysConf.UseOriginalScoreLevelUpOfFactor)
					return true;
			} else if (level == EStudentLevel.manager) {
				if (totalScore >= SysConf.FromaManagerToMajordomoUseScore && originalScore >= totalScore * SysConf.UseOriginalScoreLevelUpOfFactor) 
					return true;
			}
		}
		return false;
	}
	
}
