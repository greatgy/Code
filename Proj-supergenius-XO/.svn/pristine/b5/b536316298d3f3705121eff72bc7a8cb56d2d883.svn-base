package com.supergenius.xo.moral.service;

import org.joda.time.DateTime;

import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.moral.entity.Student;
import com.supergenius.xo.moral.enums.ELevel;

/**
 * 学员so
 * @author liushaomin
 */
public interface StudentSO extends BaseSO<Student> {

	/**
	 * @param eStatus
	 * @param ids
	 * @return
	 * @author liushaomin
	 */
	boolean update(EStatus eStatus, String[] ids);

	/**
	 * 根据证书编号等到学员
	 * 
	 * @param sn
	 * @return
	 * @author liushaomin
	 */
	Student getOneByCertificateSN(String sn);

	/**
	 * 根据useruid等到学员
	 * 
	 * @param useruid
	 * @return
	 * @author liushaomin
	 */
	Student getOneByUseruid(String useruid);

	/**
	 * 更新学员积分的时候，需要更新学员的等级
	 * 
	 * @param level
	 * @param useruid
	 * @return
	 * @author LiJiacheng
	 */
	boolean updateLevel(ELevel level, String useruid);

	/**
	 * 只更新考试成绩
	 * @param student
	 * @author liushaomin
	 */
	boolean updateScore(Student student);

	/**
	 * 获取在一定时间内的学员数
	 * @param startTime
	 * @author liushaomin
	 */
	int getCountBeforeNow(DateTime startTime);

	/**
	 * 获取在一定时间 获取证书的学员数
	 * @param startTime
	 * @return
	 * @author liushaomin
	 */
	int getCountCertificate(DateTime startTime);

}
