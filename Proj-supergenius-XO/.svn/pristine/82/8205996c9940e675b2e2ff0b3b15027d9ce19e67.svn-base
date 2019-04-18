package com.supergenius.xo.official.service;

import java.util.List;

import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.official.entity.Recruit;

/**
 * 招聘SO
 * 
 * @author liushaomin
 */
public interface RecruitSO extends BaseSO<Recruit> {
	/**
	 * 修改是否置顶
	 * 
	 * @param uids
	 * @param state
	 * @return
	 * @author LiJiacheng
	 */
	boolean setTop(String[] uids, boolean istop);

	/**
	 * 冻结或解冻招聘信息
	 * 
	 * @param uids
	 * @param statue
	 * @return
	 * @author LiJiacheng
	 */
	boolean status(String[] uids, EStatus status);

	/**
	 * 获取招聘信息的总数量,为了进行分页
	 * 
	 * @return
	 * @author LiJiacheng
	 */
	int getCount();

	/**
	 * 根据查询条件获取招聘信息的数量，为了进行分页
	 * 
	 * @return
	 * @author LiJiacheng
	 */
	int getCount(String keyword);

	/**
	 * 根据输入的职位名称，进行模糊查询
	 * 
	 * @param keyword
	 * @return
	 * @author LiJiacheng
	 */
	List<Recruit> getTitleRecruitList(String keyword, Pager pager);
}
