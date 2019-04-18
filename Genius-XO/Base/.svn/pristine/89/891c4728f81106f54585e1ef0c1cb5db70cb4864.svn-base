package com.genius.xo.base.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.service.BaseSO;

/**
 * 所有service层的基类
 * @param <T>
 * @author architect.bian
 * @createtime 2014-8-25 下午7:52:41
 */
public abstract class BaseSOImpl<T> implements BaseSO<T> {

	protected Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public T get(String id) {
		return getDao().get(id);
	}

	@Override
	public T getOne(Map<String, Object> map) {
		return getDao().getOne(map);
	}
	
	@Override
	public T getOneByField(String fieldname, Object obj) {
		Map<String, Object> map = new HashMap<>();
		map.put(fieldname, obj);
		return getDao().getOne(map);
	}
	
	@Override
	public int getCount(Map<String, Object> map) {
		return getDao().getCount(map);
	}
	
	@Override
	public int getCountByField(String fieldname, Object obj) {
		Map<String, Object> map = new HashMap<>();
		map.put(fieldname, obj);
		return getDao().getCount(map);
	}
	
	@Override
	public int getCount() {
		Map<String, Object> map = new HashMap<>();
		return getDao().getCount(map);
	}
	
	@Override
	public List<T> getList(Map<String, Object> map) {
		return getDao().getList(map);
	}
	
	@Override
	public List<T> getListByField(String fieldname, Object obj) {
		Map<String, Object> map = new HashMap<>();
		map.put(fieldname, obj);
		return getDao().getList(map);
	}
	
	@Override
	public List<T> getList(Pager pager) {
		return getDao().getList(getParamMap(pager));
	}
	
	@Override
	public List<T> getList() {
		return getList(getParamMap());
	}

	@Override
	public boolean add(T t) {
		if (t != null) {
			getDao().insert(t);
		}
		return true;
	}
	
	@Override
	public boolean add(List<T> list) {
		if (list != null && list.size() > 0) {
			getDao().insertList(list);
		}
		return true;
	}

	@Override
	public boolean update(T t) {
		if (t != null) {
			getDao().update(t);
		}
		return true;
	}
	
	@Override
	public boolean updateFields(T t) {
		if (t != null) {
			this.update(t);
		}
		return true;
	}

	@Override
	public boolean updateFields(Map<String, Object> map) {
		getDao().updateFields(map);
		return true;
	}

	@Override
	public boolean increase(Map<String, Object> map) {
		getDao().increase(map);
		return true;
	}

	@Override
	public boolean delete(String... ids) {
		if (ids == null) {
			return true;
		} else if (ids.length == 1) {
			getDao().delete(ids[0]);
		} else {
			Map<String, Object> map = new HashMap<>();
			map.put(BaseMapperDict.ids, ids);
			getDao().deleteByMap(map);
		}
		return true;
	}

	@Override
	public boolean deleteByMap(Map<String, Object> map) {
		getDao().deleteByMap(map);
		return true;
	}
	
	@Override
	public boolean deleteByField(String fieldname, Object obj) {
		Map<String, Object> map = new HashMap<>();
		map.put(fieldname, obj);
		getDao().deleteByMap(map);
		return true;
	}

	/**
	 * 
	 * @param allstatus 是否包含所有状态,默认仅选择enable的数据项
	 * @return
	 */
	protected Map<String, Object> getParamMap(boolean... allstatus) {
		Map<String, Object> map = new HashMap<>();
		map.put(BaseMapperDict.ascDesc, BaseMapperDict.desc);
		map.put(BaseMapperDict.startIndex, 0);
		if (allstatus.length == 0 || !allstatus[0]) {
			map.put(BaseMapperDict.status, EStatus.enable);
		}
		return map;
	}

	protected abstract BaseDao<T> getDao();
	
	/**
	 * @param pager
	 * @return
	 */
	protected Map<String, Object> getParamMap(Pager pager, boolean... allstatus) {
		Map<String, Object> map = getParamMap(allstatus);
		map.put(BaseMapperDict.startIndex, pager.getStartIndex());
		map.put(BaseMapperDict.pageSize, pager.getPageSize());
		return map;
	}
	
	/**
	 * @param top
	 * @return
	 */
	protected Map<String, Object> getParamMap(int top, boolean... allstatus) {
		Map<String, Object> map = getParamMap(allstatus);
		map.put(BaseMapperDict.pageSize, top);
		return map;
	}
}
