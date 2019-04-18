package com.supergenius.xo.enterpriser.serviceimpl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.enterpriser.dao.LectureDao;
import com.supergenius.xo.enterpriser.entity.Lecture;
import com.supergenius.xo.enterpriser.service.LectureSO;

/** 
 *  讲座SOImpl
 * @author chenminchang
 * @date 2016-10-24 下午5:01:38 
 */
@Service
public class LectureSOImpl extends BaseSOImpl<Lecture> implements LectureSO {

	@Autowired
	LectureDao dao;
	
	@Override
	protected BaseDao<Lecture> getDao() {
		return dao;
	}

	@Override
	public Lecture getOne(EStatus status) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.status, status);
		return dao.getOne(map);
	}

	@Override
	public boolean updateRegistercount(Lecture lecture) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.uid, lecture.getUid());
		map.put(MapperDict.registercount, lecture.getRegistercount());
		return dao.updateFields(map);
	}

	@Override
	public int getLectureCount(Map<String, Object> map) {
		return dao.getLectureCount(map);
	}

	@Override
	public boolean updateStatus(String uid, EStatus status) {
		Map<String, Object> map = getParamMap(true);
		map.put(MapperDict.uid, uid);
		map.put(MapperDict.status, status);
		return dao.updateFields(map);
	}

	@Override
	public List<Lecture> getListBySn(String sn) {
		Map<String, Object> map = getParamMap(true);
		map.put(MapperDict.sn, sn);
		return dao.getList(map);
	}

	@Override
	public boolean updateTime(String uid, DateTime time) {
		Map<String, Object> map = getParamMap(true);
		map.put(MapperDict.uid, uid);
		map.put(MapperDict.time, time);
		return dao.updateFields(map);
	}

	@Override
	public boolean updateAddress(String uid, String address) {
		Map<String, Object> map = getParamMap(true);
		map.put(MapperDict.uid, uid);
		map.put(MapperDict.address, address);
		return dao.updateFields(map);
	}

	@Override
	public boolean updateName(String uid, String name) {
		Map<String, Object> map = getParamMap(true);
		map.put(MapperDict.uid, uid);
		map.put(MapperDict.name, name);
		return dao.updateFields(map);
	}

	@Override
	public boolean updateFile(String uid, String file) {
		Map<String, Object> map = getParamMap(true);
		map.put(MapperDict.uid, uid);
		map.put(MapperDict.file, file);
		return dao.updateFields(map);
	}

}
