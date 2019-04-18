package com.supergenius.xo.manager.dao;

import java.util.List;
import java.util.Map;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.manager.entity.Certificate;

/**
 * 证书明细Dao
 * @author XieMing
 * @date 2016-7-18 下午2:25:26
 */
public interface CertificateDao extends BaseDao<Certificate> {

	/**
	 * 多个查询条件按得到数量
	 * @param map
	 * @return
	 * @author liubin
	 * @createtime 2016-11-8下午5:40:00
	 * @return int
	 */
	int searchCount(Map<String, Object> map);
	
	/**
	 * 多个查询条件按得到List
	 * @param map
	 * @return
	 * @author liubin
	 * @createtime 2016-11-8下午5:40:55
	 * @return List<Certificate>
	 */
	List<Certificate> searchList(Map<String, Object> map);
}
