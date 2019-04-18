package com.supergenius.xo.moral.service;

import java.util.List;

import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.moral.entity.Examstatistics;
import com.supergenius.xo.moral.enums.EChapter;
import com.supergenius.xo.moral.enums.EQst;

/**
 * 考试结果类SO
 * 
 * @author LiJiacheng
 */
public interface ExamstatisticsSO extends BaseSO<Examstatistics> {

	/**
	 * 根据useruid和章节获取
	 * @param useruid
	 * @param chapter
	 * @param type
	 * @return
	 * @author liushaomin
	 */
	Examstatistics getOne(String useruid, EChapter one, EQst type);
	
	/**
	 * 根据useruid获取
	 * @param useruid
	 * @param one
	 * @return
	 * @author liushaomin
	 */
	List<Examstatistics> getList(String useruid);

}
