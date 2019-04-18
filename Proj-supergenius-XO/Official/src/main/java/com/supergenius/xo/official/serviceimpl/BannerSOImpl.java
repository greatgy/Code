package com.supergenius.xo.official.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.official.constants.MapperDict;
import com.supergenius.xo.official.dao.BannerDao;
import com.supergenius.xo.official.entity.Banner;
import com.supergenius.xo.official.enums.EBanner;
import com.supergenius.xo.official.service.BannerSO;

/**
 * BannerSO实现
 * @author LiuXiaoke
 */
@Service
public class BannerSOImpl extends BaseSOImpl<Banner> implements BannerSO {

	@Autowired
	BannerDao dao;
	
	/*
	 * (non-Javadoc)
	 * @see com.genius.xo.base.serviceimpl.BaseSOImpl#getDao()
	 */
	@Override
	protected BaseDao<Banner> getDao() {
		return dao;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.supergenius.xo.official.service.BannerSO#banner_updown()
	 */
	@Override
	public List<Banner> banner_updown(){
		Map<String, Object> map = getParamMap(true);
		return dao.getList(map);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.genius.xo.base.serviceimpl.BaseSOImpl#update(java.lang.Object)
	 */
	@Override
	public boolean update(Banner b) {
		Banner banner = dao.get(b.getUid());
		banner.set(b);
		return dao.update(banner);
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.official.service.BannerSO#getList(com.supergenius.xo.official.enums.EBanner)
	 */
	@Override
	public List<Banner> getList(EBanner type) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.bannertype, type);
		return dao.getList(map);
	}

}
