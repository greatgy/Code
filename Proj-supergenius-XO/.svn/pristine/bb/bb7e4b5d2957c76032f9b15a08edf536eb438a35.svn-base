package com.supergenius.xo.moral.service;

import java.util.List;

import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.moral.entity.Question;
import com.supergenius.xo.moral.enums.EChapter;
import com.supergenius.xo.moral.enums.EQst;

/**
 * 题库SO
 * 
 * @author LiJiacheng
 */
public interface QuestionSO extends BaseSO<Question> {

	/**
	 * 修改状态
	 * @param eStatus
	 * @param ids
	 * @return
	 * @author liushaomin
	 */
	boolean update(EStatus eStatus, String[] ids);

	/**
	 * 根据章节获取题数
	 * @param item
	 * @return
	 * @author liushaomin
	 */
	Object getCount(EChapter chapter);
	
	/**
	 * 根据章节和题型获取题数
	 * @param chapter
	 * @param type
	 * @return
	 * @author liushaomin
	 */
	int getCount(EChapter chapter, EQst type);

	/**
	 * 获取题目
	 * @param chapter
	 * @param type
	 * @param qstuids
	 * @return
	 * @author liushaomin
	 */
	List<Question> getListIn(Pager pager, EChapter chapter, EQst type, List<String> qstuids);
	
	/**
	 * 练习时 在一定范围内随机取
	 * @param type
	 * @param chapter
	 * @param pager
	 * @return
	 * @author liushaomin
	 */
	List<Question> getRandomQstsIn(EQst type, EChapter chapter, List<String> qstuids, Pager pager);
	
	/**
	 * 获取题目
	 * @param chapter
	 * @param type
	 * @param qstuids
	 * @return
	 * @author liushaomin
	 */
	List<Question> getListNotin(Pager pager, EChapter chapter, EQst type, List<String> qstuids);
	
	/**
	 * 随机获取试题（去除已经获取到的）
	 * @param type
	 * @param chapter （章节可以为空）
	 * @param qstuids
	 * @param pager
	 * @return
	 * @author liushaomin
	 */
	List<Question> getRandomQstsNotin(EQst type, EChapter chapter, List<String> qstuids, Pager pager);

	/**
	 * 随机获取试题
	 * @param type
	 * @param pager
	 * @param chapter （章节可以为空）
	 * @return
	 * @author liushaomin
	 */
	List<Question> getListRandom(EQst type, EChapter chapter, Pager pager);

	/**
	 * 根据uidlist获取试题
	 * @param questionuids
	 * @return
	 * @author liushaomin
	 * TODO 暂时没用
	 */
	List<Question> getListInUid(List<String> questionuids);

}
