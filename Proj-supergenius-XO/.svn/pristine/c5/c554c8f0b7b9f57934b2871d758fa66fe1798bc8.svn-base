package com.supergenius.xo.enterpriser.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.enterpriser.entity.Catalogue;

/**
 * 模块dao
 * @author loupengyu
 * @date 2018年1月29日11:28:23
 */
@Component("enterpriserCatalogueDao")
public interface CatalogueDao extends BaseDao<Catalogue> {

	/**
	 * 通过cid删除一个对象
	 * @author loupengyu
	 * @typename Catalogue
	 * @data 2018年1月30日17:15:45
	 */
	boolean delete(int cid);

	/**
	 * 通过cid得到一个对象
	 * @author loupengyu
	 * @typename Catalogue
	 * @data 2018年1月30日17:15:45
	 */
	Catalogue get(int cid);
	
	/**
	 * 通过pcid获得二级模块cids
	 * @author YangGuang
	 * @typename pcid
	 * @data 2018年2月1日21:18:57
	 */
	List<Integer> getCidsByPcid(Map<String, Object> map);
}
