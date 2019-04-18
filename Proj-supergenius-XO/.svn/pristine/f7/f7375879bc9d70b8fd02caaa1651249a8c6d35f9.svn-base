package com.supergenius.xo.startup.service;

import java.util.List;

import com.genius.model.base.entity.Pager;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.startup.entity.Label;

/**
 * 标签SO
 * 
 * @author 杨光
 * @date 2017年8月23日14:20:31
 */
public interface LabelSO extends BaseSO<Label> {

	/**
	 * 添加标签
	 * 
	 * @author yangguagn
	 * @date 2017年8月24日09:55:28
	 * @return boolean
	 */
	boolean add(Label label, AdminLog adminLog);

	/**
	 * 更新标签
	 * 
	 * @author yangguang
	 * @date 2017年8月24日10:01:18
	 * @return boolean
	 */
	boolean update(Label label, AdminLog adminLog);

	/**
	 * 热门标签
	 * 
	 * @author ChenQi
	 * @date 2017年8月28日18:10:20
	 * @return List<Label>
	 */
	List<Label> getHotLabelList(Pager pager);
	
	/**
	 * 文章相关标签
	 * 
	 * @author yangguang
	 * @date 2017年9月1日10:03:37
	 * @return List<Label>
	 */
	List<Label> getRelatedLabel(String refuid, int size);
}
