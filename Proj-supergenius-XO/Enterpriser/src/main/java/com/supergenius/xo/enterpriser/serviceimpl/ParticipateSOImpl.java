package com.supergenius.xo.enterpriser.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.enterpriser.dao.ParticipateDao;
import com.supergenius.xo.enterpriser.entity.Lecture;
import com.supergenius.xo.enterpriser.entity.Participate;
import com.supergenius.xo.enterpriser.service.LectureSO;
import com.supergenius.xo.enterpriser.service.ParticipateSO;
import com.supergenius.xo.user.entity.Order;
import com.supergenius.xo.user.entity.OrderGoods;
import com.supergenius.xo.user.entity.OrderLog;
import com.supergenius.xo.user.entity.TradeDetail;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.service.OrderGoodsSO;
import com.supergenius.xo.user.service.OrderLogSO;
import com.supergenius.xo.user.service.TradeDetailSO;
import com.supergenius.xo.user.service.UserSO;

/**
 * 报名SOImpl
 * 
 * @author liubin
 * @date 2016-10-24 下午5:17:41
 */
@Service
public class ParticipateSOImpl extends BaseSOImpl<Participate> implements ParticipateSO {

	@Autowired
	private ParticipateDao dao;
	@Autowired
	LectureSO lectureSO;
	@Autowired
	TradeDetailSO tradeDetailSO;
	@Autowired
	OrderLogSO orderLogSO;
	@Autowired
	OrderGoodsSO orderGoodsSO;
	@Autowired
	UserSO userSO;

	@Override
	protected BaseDao<Participate> getDao() {
		return dao;
	}

	@Override
	public int getCount(EStatus status) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.status, status);
		return dao.getCount(map);
	}

	@Override
	public int getCountBySearch(Map<String, Object> map) {
		return dao.getCountBySearch(map);
	}

	@Override
	public List<Participate> getListBySearch(Map<String, Object> map) {
		return dao.getListBySearch(map);
	}

	@Override
	public int getCount(EStatus... status) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.status + MapperDict.suffix_in_key, status);
		return dao.getCount(map);
	}

	public boolean update(User user, Participate participate, Order order, TradeDetail tradeDetail, OrderLog orderLog, OrderGoods orderGoods) {
		Lecture lecture = lectureSO.get(participate.getLectureuid());
		lecture.setRegistercount(lecture.getRegistercount() + 1);
		return dao.update(participate) && userSO.add(user) && lectureSO.updateRegistercount(lecture) && tradeDetailSO.add(tradeDetail) && orderLogSO.add(orderLog) && orderGoodsSO.add(orderGoods);
	}

	@Override
	public Participate get(String uid, EStatus status) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.uid, uid);
		map.put(MapperDict.status, status);
		return dao.getOne(map);
	}

	@Override
	public List<Participate> getListByLecture(String lectureuid) {
		Map<String, Object> map = getParamMap(true);
		map.put(MapperDict.lectureuid, lectureuid);
		return dao.getList(map);
	}

	@Override
	public Participate getOneByEmail(String email) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.email, email);
		return dao.getOne(map);
	}
}
