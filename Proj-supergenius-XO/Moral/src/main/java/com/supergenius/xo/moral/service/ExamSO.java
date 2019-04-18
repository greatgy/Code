package com.supergenius.xo.moral.service;

import java.util.List;

import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.moral.entity.Exam;
import com.supergenius.xo.moral.enums.EExam;
import com.supergenius.xo.moral.enums.EExamState;

/**
 * 考试SO
 * 
 * @author LiJiacheng
 */
public interface ExamSO extends BaseSO<Exam> {

	/**
	 * 获取学员的不同阶段考试
	 * @param useruid
	 * @param type
	 * @param state
	 * @return
	 * @author liushaomin
	 */
	Exam get(String useruid, EExam type, EExamState state);
	
	/**
	 * 获取学员的考试
	 * @param useruid
	 * @param type
	 * @param state
	 * @return
	 * @author liushaomin
	 */
	Exam get(String useruid, EExam type);
	
	/**
	 * 获取学员的考试（考试通过或者已颁发证书）
	 * @param useruid
	 * @param type
	 * @param String[]
	 * @return
	 * @author liushaomin
	 */
	Exam get(String useruid, EExam type, String[] states);
	
	/**
	 * 得到最大值或最小值的pageSize条
	 * 
	 * @param ascdesc
	 *            （asc为最小值）
	 * @param pageSize
	 * @return
	 * @author liushaomin
	 */
	List<Exam> getListExamScoreOrder(String ascdesc, int pageSize);

	/**
	 * 获取某个分数段的数量
	 * 
	 * @param type
	 * @param beginscore
	 * @param endscore
	 * @return
	 * @author liushaomin
	 */
	int getCountByScore(EExam type, int beginscore, int endscore);

	/**
	 * 平均成绩
	 * 
	 * @return
	 * @author liushaomin
	 * @param type
	 */
	String getAveragehScore(EExam type);

	/**
	 * 获取考试通过
	 * 
	 * @param exam
	 * @return
	 * @author liushaomin
	 */
	int getCountFinish(EExam exam);

	/**
	 * 获取每天平均练习多少题
	 * 
	 * @return
	 * @author LiJiacheng
	 */
	String getAveragedays(int questionNums, String useruid);

	/**
	 * 考试结束更新状态 和 分数
	 * @param exam
	 * @author liushaomin
	 */
	boolean updateState(Exam exam);

	/**
	 * 获取学员所有考试
	 * @param useruid
	 * @param type
	 * @return
	 * @author liushaomin
	 */
	List<Exam> getList(String useruid, EExam type);

	/**
	 * 获取所有未结束的开始
	 * @param exam
	 * @param start
	 * @return
	 * @author liushaomin
	 */
	List<Exam> getList(EExam type, EExamState state);
}
