package com.supergenius.xo.manager.dao;

import java.util.List;
import java.util.Map;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.manager.entity.PKSchedule;

/**
 * 挑战日程Dao
 * @author XieMing
 * @date 2016-4-29 下午3:12:57
 */
public interface PkScheduleDao extends BaseDao<PKSchedule> {
	
	/**
	 * 联表(ordergoods)查询得到已购挑战的数量
	 * @param map
	 * @return
	 * @author liubin
	 * @createtime 2016-9-2下午5:42:46
	 * @return int
	 */
	int getCountByOrderGoods(Map<String, Object> map);
	
	/**
	 * 联表(ordergoods)查询得到已购挑战的list
	 * @param map
	 * @return
	 * @author liubin
	 * @createtime 2016-9-2下午7:19:19
	 * @return List<PKSchedule>
	 */
	List<PKSchedule> getListByOrderGoods(Map<String, Object> map);
	
	/**
	 * 联表(userinfo)查询得到挑战的count
	 * @param map
	 * @return
	 * @author liubin
	 * @createtime 2016-10-25下午12:25:57
	 * @return int
	 */
	int getCountBySearch(Map<String, Object> map);
	
	/**
	 * 联表(userinfo)查询得到挑战的list
	 * @param map
	 * @return
	 * @author liubin
	 * @createtime 2016-10-24下午1:54:49
	 * @return List<PKSchedule>
	 */
	List<PKSchedule> getListBySearch(Map<String, Object> map);

}
