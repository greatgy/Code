package com.supergenius.xo.tpi.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.base.entity.Pager;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.tpi.constants.MapperDict;
import com.supergenius.xo.tpi.dao.WishDao;
import com.supergenius.xo.tpi.entity.Wish;
import com.supergenius.xo.tpi.enums.EWishType;
import com.supergenius.xo.tpi.service.WishSO;

/**
 * 意愿明细
 * @author liushaomin
 */
@Service
public class WishSOImpl extends BaseSOImpl<Wish> implements WishSO{
	
	@Autowired
	private WishDao dao;

	@Override
	protected WishDao getDao() {
		return dao;
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.WishSO#getList(com.supergenius.xo.tpi.enums.EWishType, com.supergenius.xo.tpi.enums.EChannel)
	 */
	@Override
	public List<Wish> getList(EWishType wishtype, EChannel channel) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.channel, channel);
		map.put(MapperDict.type, wishtype);
		return dao.getList(map);
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.WishSO#getCount(java.lang.String, com.supergenius.xo.tpi.enums.EWishType, com.supergenius.xo.tpi.enums.EChannel)
	 * @author: ShangJianguo
	 * 2015-1-14 下午1:39:23
	 */
	@Override
	public int getCount(String fromuid, String touid, EWishType type, EChannel channel) {
		Map<String, Object> map = getParamMap(true);
		if (StringUtils.isNotEmpty(fromuid)) {
			map.put(MapperDict.fromuid, fromuid);
		}
		if (StringUtils.isNotEmpty(touid)) {
			map.put(MapperDict.touid, touid);
		}
		map.put(MapperDict.type, type);
		map.put(MapperDict.channel, channel);
		return dao.getCount(map);
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.WishSO#getList(java.lang.String, com.supergenius.xo.tpi.enums.EWishType, com.supergenius.xo.tpi.enums.EChannel)
	 * @author: ShangJianguo
	 * 2015-1-14 下午4:16:01
	 */
	@Override
	public List<Wish> getList(String fromuid, EWishType type, EChannel channel) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.fromuid, fromuid);
		map.put(MapperDict.type, type);
		map.put(MapperDict.channel, channel);
		return dao.getList(map);
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.WishSO#getList(java.lang.String, java.lang.String, com.supergenius.xo.tpi.enums.EWishType, com.supergenius.xo.tpi.enums.EChannel)
	 * @author: ShangJianguo
	 * 2015-1-14 下午4:54:58
	 */
	@Override
	public List<Wish> getList(String fromuid, String touid, EWishType type, EChannel channel, Pager pager) {
		Map<String, Object> map = null;
		if (pager == null) {
			map = getParamMap();
		} else {
			map = getParamMap(pager);
		}
		if (StringUtils.isNotEmpty(fromuid)) {
			map.put(MapperDict.fromuid, fromuid);
		}
		if (StringUtils.isNotEmpty(touid)) {
			map.put(MapperDict.touid, touid);
		}
		map.put(MapperDict.type, type);
		map.put(MapperDict.channel, channel);
		return dao.getList(map);
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.WishSO#getList(java.lang.String, java.lang.String, com.supergenius.xo.tpi.enums.EWishType, com.supergenius.xo.tpi.enums.EChannel)
	 * @author: ShangJianguo
	 * 2015-1-15 下午6:24:51
	 */
	@Override
	public List<Wish> getList(String fromuid, String touid, EWishType type, EChannel channel) {
		Map<String, Object> map = getParamMap();
		if (StringUtils.isNotEmpty(fromuid)) {
			map.put(MapperDict.fromuid, fromuid);
		}
		if (StringUtils.isNotEmpty(touid)) {
			map.put(MapperDict.touid, touid);
		}
		map.put(MapperDict.type, type);
		map.put(MapperDict.channel, channel);
		return dao.getList(map);
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.WishSO#deleteByTouid(java.lang.String[])
	 */
	@Override
	public boolean deleteByTouid(String[] ids) {
		Map<String, Object> map = getParamMap();
		for (String touid : ids) {
			map.put(MapperDict.touid, touid);
			dao.deleteByMap(map);
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.WishSO#deleteByFromuid(java.lang.String[])
	 */
	@Override
	public boolean deleteByFromuid(String[] ids) {
		Map<String, Object> map = getParamMap();
		for (String fromuid : ids) {
			map.put(MapperDict.fromuid, fromuid);
			dao.deleteByMap(map);
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.WishSO#isNotAttention(java.lang.String, java.lang.String, com.supergenius.xo.tpi.enums.EChannel)
	 */
	@Override
	public boolean isNotAttention(String fromuid, String touid, EChannel channel) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.fromuid, fromuid);
		map.put(MapperDict.touid, touid);
		map.put(MapperDict.channel, channel);
		map.put(MapperDict.type, EWishType.attention);
		return dao.getCount(map) == 0;
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.WishSO#deleteByAttention(java.lang.String, java.lang.String, com.supergenius.xo.tpi.enums.EChannel)
	 */
	@Override
	public boolean deleteByAttention(String fromuid, String touid, EChannel channel) {
		Map<String, Object> map = getParamMap();
		if (fromuid != null) {
			map.put(MapperDict.fromuid, fromuid);
		}
		map.put(MapperDict.touid, touid);
		map.put(MapperDict.channel, channel);
		map.put(MapperDict.type, EWishType.attention);
		return dao.deleteByMap(map);
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.WishSO#deleteByFromuidType(java.lang.String, com.supergenius.xo.tpi.enums.EWishType)
	 */
	@Override
	public boolean deleteByFromuidType(String fromuid, EWishType type) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.fromuid, fromuid);
		map.put(MapperDict.type, type);
		return dao.deleteByMap(map);
	}
}
