package com.supergenius.xo.moral.service;

import java.util.List;
import java.util.Map;

import com.genius.model.base.entity.Pager;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.moral.entity.Sign;

/**
 * 签到so
 * 
 * @author liushaomin
 */
public interface SignSO extends BaseSO<Sign> {

	/**
	 * 签到
	 * 
	 * @return
	 * @author LiJiacheng
	 */
	boolean sign(String useruid);

	/**
	 * 取得最近的签到信息
	 * 
	 * @return
	 * @author LiJiacheng
	 */
	List<Sign> getLatestSigns(Pager pager, Map<String, Object> map);

	/**
	 * 将所有的签到信息进行分组查询，取出所有用户最近的签到信息，用于凌晨定时器更新
	 * 
	 * @return
	 * @author LiJiacheng
	 */
	List<Map<String, Object>> groupSign();

}
