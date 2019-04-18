package com.supergenius.server.moral.helper;

import com.genius.model.baseadmin.entity.Admin;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.xo.moral.entity.ScoreDetail;
import com.supergenius.xo.moral.enums.ELevel;
import com.supergenius.xo.moral.enums.EScoreDetail;
import com.supergenius.xo.moral.service.ScoreDetailSO;
import com.supergenius.xo.moral.service.StudentSO;
import com.supergenius.xo.moral.service.UserStatisticsSO;

/**
 * 和学员相关，修改用户积分、头衔等
 * 
 * @author liushaomin
 */
public class BaseStudentHP extends BaseHP {

	public static ScoreDetailSO scoreDetailSO;

	public static UserStatisticsSO UserStatisticsSO;

	public static StudentSO so;

	public static ScoreDetailSO getScoreDetailSO() {
		if (scoreDetailSO == null) {
			scoreDetailSO = (ScoreDetailSO) spring.getBean(ScoreDetailSO.class);
		}
		return scoreDetailSO;
	}

	public static UserStatisticsSO getUserStatisticsSO() {
		if (UserStatisticsSO == null) {
			UserStatisticsSO = (UserStatisticsSO) spring.getBean(UserStatisticsSO.class);
		}
		return UserStatisticsSO;
	}

	public static StudentSO getSO() {
		if (so == null) {
			so = (StudentSO) spring.getBean(StudentSO.class);
		}
		return so;
	}

	/**
	 * 更新用户积分，更新用户等级和头衔，插入积分明细
	 * 
	 * @param useruid
	 * @param endscore
	 * @param beginscore
	 * @param type
	 * @return
	 * @author YuYingJie
	 * @modifier Lijiacheng
	 */
	public static void updateScore(String useruid, int endscore, int beginscore, EScoreDetail type, Admin admin, String desc) {
		if (getUserStatisticsSO().updateScore(useruid, endscore)) {
			getSO().updateLevel(getLevel(endscore), useruid);
			ScoreDetail detail = new ScoreDetail();
			if (admin != null) {
				detail.setAdminid(admin.getAdminid());
				detail.setAdminname(admin.getName());
			}
			detail.setDesc(desc);
			detail.setUseruid(useruid);
			detail.setBeginscore(beginscore);
			detail.setFinishscore(endscore);
			detail.setType(type);
			getScoreDetailSO().add(detail);
		}
	}

	/**
	 * 根据积分 得到最新的等级
	 * 
	 * @param score
	 * @author liushaomin
	 * @return
	 */
	private static ELevel getLevel(double score) {
		int iscore = (int) score;
		if (5 <= iscore && iscore < 10) {
			return ELevel.one;
		} else if (10 <= iscore && iscore < 50) {
			return ELevel.two;
		} else if (50 <= iscore && iscore < 100) {
			return ELevel.three;
		} else if (100 <= iscore && iscore < 200) {
			return ELevel.four;
		} else if (200 <= iscore && iscore < 500) {
			return ELevel.fives;
		} else if (500 <= iscore && iscore < 1000) {
			return ELevel.six;
		} else if (1000 <= iscore && iscore < 2000) {
			return ELevel.seven;
		} else if (2000 <= iscore && iscore < 5000) {
			return ELevel.eight;
		} else if (5000 <= iscore && iscore < 10000) {
			return ELevel.nine;
		} else {
			return ELevel.ten;
		}
	}
}
