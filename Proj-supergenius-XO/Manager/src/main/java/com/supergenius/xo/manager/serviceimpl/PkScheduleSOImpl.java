package com.supergenius.xo.manager.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.genius.xo.baseadmin.service.AdminLogSO;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.manager.dao.PkScheduleDao;
import com.supergenius.xo.manager.entity.Conference;
import com.supergenius.xo.manager.entity.Judge;
import com.supergenius.xo.manager.entity.PKJudgement;
import com.supergenius.xo.manager.entity.PKSchedule;
import com.supergenius.xo.manager.entity.PKStateDetail;
import com.supergenius.xo.manager.enums.EMajor;
import com.supergenius.xo.manager.enums.EPKStage;
import com.supergenius.xo.manager.enums.EStudentLevel;
import com.supergenius.xo.manager.service.ConfeMemberSO;
import com.supergenius.xo.manager.service.ConferenceSO;
import com.supergenius.xo.manager.service.JudgeSO;
import com.supergenius.xo.manager.service.PkJudgementSO;
import com.supergenius.xo.manager.service.PkScheduleSO;
import com.supergenius.xo.manager.service.PkStateDetailSO;
import com.supergenius.xo.user.dao.OrderGoodsDao;
import com.supergenius.xo.user.entity.TradeDetail;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.enums.EGoods;
import com.supergenius.xo.user.service.OrderGoodsSO;
import com.supergenius.xo.user.service.OrderLogSO;
import com.supergenius.xo.user.service.OrderSO;
import com.supergenius.xo.user.service.TradeDetailSO;
import com.supergenius.xo.user.service.UserSO;

/**
 * 挑战日程SOImpl
 * 
 * @author XieMing
 * @date 2016-4-29 下午3:20:05
 */
@Service
public class PkScheduleSOImpl extends BaseSOImpl<PKSchedule> implements PkScheduleSO {

	@Autowired
	PkScheduleDao dao;
	@Autowired
	PkJudgementSO pkJudgementSO;
	@Autowired
	TradeDetailSO tradeDetailSO;
	@Autowired
	UserSO userSO;
	@Autowired
	OrderSO orderSO;
	@Autowired
	OrderGoodsSO orderGoodsSO;
	@Autowired
	OrderGoodsDao orderGoodsDao;
	@Autowired
	PkStateDetailSO pkStateDetailSO;
	@Autowired
	OrderLogSO orderLogSO;
	@Autowired
	AdminLogSO adminLogSO;
	@Autowired
	JudgeSO judgeSO;
	@Autowired
    ConferenceSO conferenceSO;
	@Autowired
	ConfeMemberSO confeMemberSO;

	@Override
	protected BaseDao<PKSchedule> getDao() {
		return dao;
	}

	@Override
	public List<PKSchedule> getList(Pager pager, List<EPKStage> stages) {
		Map<String, Object> map = getParamMap();
		if (pager != null) {
			 map = getParamMap(pager);
		}
		map.put(MapperDict.stage + MapperDict.suffix_in_key, stages);
		List<PKSchedule> list = dao.getList(map);
		return list;
	}

	@Override
	public List<PKSchedule> getList(Pager pager, EMajor major, EPKStage stage) {
		Map<String, Object> map = getParamMap(pager);
		if (stage == null) {
			map.put(MapperDict.stage + MapperDict.suffix_in_key, EPKStage.getShowStages());
		} else if (stage == EPKStage.challengeSuccess) {
			map.put(MapperDict.stage + MapperDict.suffix_in_key, EPKStage.getPKOver());
		} else {
			map.put(MapperDict.stage, stage);
		}
		if (major != null) {
			map.put(MapperDict.major, major);
		}
		return dao.getList(map);
	}

	@Override
	public List<PKSchedule> getList(Pager pager, String useruid, List<EPKStage> stages, boolean isPKuser1) {
		Map<String, Object> map = getParamMap(pager);
		if (isPKuser1 == true)
			map.put(MapperDict.pkuseruid, useruid);
		else
			map.put(MapperDict.pkuseruid2, useruid);
		map.put(MapperDict.stage + MapperDict.suffix_in_key, stages);
		map.put(MapperDict.orderBy, MapperDict.sql_order_by + MapperDict.updatetime + MapperDict.desc);
		return dao.getList(map);
	}

	@Override
	public List<PKSchedule> getList(Pager pager, String useruid, List<EPKStage> stages) {
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.pkuseruid + MapperDict.or_suffix, useruid);
		map.put(MapperDict.pkuseruid2 + MapperDict.or_suffix, useruid);
		map.put(MapperDict.stage + MapperDict.suffix_in_key, stages);
		map.put(MapperDict.orderBy, MapperDict.sql_order_by + MapperDict.updatetime + MapperDict.desc);
		return dao.getList(map);
	}

	@Override
	public boolean updatePayStage(PKSchedule pkSchedule, TradeDetail tradeDetail, PKStateDetail pkStateDetail) {
		User user = userSO.get(pkSchedule.getPkuseruid());
		return userSO.updateFreezeaccount(user, tradeDetail.getMoney()) && updateStage(pkSchedule, pkStateDetail)
				&& pkJudgementSO.updateList(pkSchedule.getUid(), EStatus.start) && tradeDetailSO.add(tradeDetail);
	}

	@Override
	public boolean updateStage(PKSchedule pkSchedule, PKStateDetail pkStateDetail) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.uid, pkSchedule.getUid());
		map.put(MapperDict.stage, pkSchedule.getStage());
		return pkStateDetailSO.add(pkStateDetail) && dao.updateFields(map);
	}

	@Override
	public boolean add(PKSchedule pkSchedule, String pkjudges) {
		for (String judgeid : pkjudges.split(",")) {
			PKJudgement pkJudgement = null;
			if(StrUtil.isNotEmpty(judgeid)) {
				pkJudgement = new PKJudgement(pkSchedule.getUid(), pkSchedule.getPkuseruid(), judgeid, pkSchedule.getLevel(), EStatus.init);
			}
			if(pkJudgement != null) {
				pkJudgementSO.add(pkJudgement);
			}
		}
		return dao.insert(pkSchedule);
	}

	@Override
	public PKSchedule getOneByMajorAndStage(String uid, EMajor major, EPKStage stage) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.major, major);
		map.put(MapperDict.stage, stage);
		map.put(MapperDict.pkuseruid, uid);
		return dao.getOne(map);
	}

	@Override
	public PKSchedule getOne(String orderGoodsSN, EMajor major, EPKStage stage) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.sn, orderGoodsSN);
		if (StrUtil.isNotEmpty(major)) {
			map.put(MapperDict.major, major);
		}
		if (StrUtil.isNotEmpty(stage)) {
			map.put(MapperDict.stage, stage);
		}
		return getOne(map);
	}

	@Override
	public int getCount(String useruid, String sn) {
		Map<String, Object> map = getParamMap();
		Map<String, Object> map1 = getParamMap();
		Map<String, Object> map2 = getParamMap();
		map.put(MapperDict.judgementuid, useruid);
		map1.put(MapperDict.judgementuid2, useruid);
		map2.put(MapperDict.judgementchairsn, sn);
		return dao.getCount(map) + dao.getCount(map1) + dao.getCount(map2); // TODO
																			// 修改映射文件实现
	}

	@Override
	public boolean update(PKSchedule pkSchedule, int count) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.uid, pkSchedule.getUid());
		map.put(MapperDict.ticketsalecount, count);
		return dao.updateFields(map);
	}

	@Override
	public boolean updateFields(PKSchedule pkSchedule, PKStateDetail pkStateDetail) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.uid, pkSchedule.getUid());
		map.put(MapperDict.stage, pkSchedule.getStage());
		if (StrUtil.isNotEmpty(pkSchedule.getPktime()))
			map.put(MapperDict.pktime, pkSchedule.getPktime());
		if (StrUtil.isNotEmpty(pkSchedule.getPkdates2()))
			map.put(MapperDict.pkdates2, pkSchedule.getPkdates2());
		return pkStateDetailSO.add(pkStateDetail) && dao.updateFields(map);
	}

	@Override
	public PKSchedule getOne(String uid, EMajor major) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.pkuseruid, uid);
		map.put(MapperDict.major, major);
		return dao.getOne(map);
	}

	@Override
	public List<PKSchedule> getList(String orderGoodsSN, EMajor major, List<EPKStage> stages) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.sn, orderGoodsSN);
		map.put(MapperDict.major, major);
		map.put(MapperDict.stage + MapperDict.suffix_in_key, stages);
		return dao.getList(map);
	}

	@Override
	public int getCountByOrderGoods(String userUid, EGoods type, List<EPKStage> stages) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, userUid);
		map.put(MapperDict.type, type);
		map.put(MapperDict.stage + MapperDict.suffix_in_key, stages);
		return dao.getCountByOrderGoods(map);
	}

	@Override
	public int getCountByOrderGoods(String userUid, EGoods type, EPKStage stage) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, userUid);
		map.put(MapperDict.type, type);
		map.put(MapperDict.stage, stage);
		return dao.getCountByOrderGoods(map);
	}

	@Override
	public List<PKSchedule> getListByOrderGoods(Map<String, Object> map) {
		Map<String, Object> newMap = getParamMap();
		newMap.putAll(map);
		return dao.getListByOrderGoods(newMap);
	}

	@Override
	public boolean updateRetuenPKFee(User user, PKSchedule pkSchedule, TradeDetail tradeDetail, PKStateDetail pkStateDetail) {
		return dao.update(pkSchedule) && pkStateDetailSO.add(pkStateDetail) && userSO.updateUnFreezeaccount(user, pkSchedule.getPkprice()) && tradeDetailSO.add(tradeDetail);
	}

	@Override
	public boolean updatePayPKFee(User user, PKSchedule pkSchedule, TradeDetail tradeDetail, PKStateDetail pkStateDetail) {
		user.plusTotalpay(pkSchedule.getPkprice());
		return dao.update(pkSchedule) && pkStateDetailSO.update(pkStateDetail) && tradeDetailSO.add(tradeDetail) && userSO.updateAccountAndTotalpayAndFreezeAccount(user, pkSchedule.getPkprice());
	}

	@Override
	public boolean updateJudgeAccept(PKSchedule pkSchedule, PKStateDetail pkStageDetail, String judgementuid) {
		return pkJudgementSO.updateAccept(judgementuid) && dao.update(pkSchedule) && pkStateDetailSO.add(pkStageDetail);
	}
	
	@Override
	public boolean updateJudgeRefuse(PKSchedule pkSchedule, PKStateDetail pkStageDetail, String judgementuid) {
		PKJudgement pkJudgement = pkJudgementSO.get(judgementuid);
		return pkJudgementSO.updateStatus(pkJudgement) && dao.update(pkSchedule) && pkStateDetailSO.add(pkStageDetail);
	}

	@Override
	public int getCount(List<EPKStage> stages) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.stage + MapperDict.suffix_in_key, stages);
		return dao.getCount(map);
	}

	@Override
	public int getCountBySearch(Map<String, Object> map) {
		return dao.getCountBySearch(map);
	}

	@Override
	public List<PKSchedule> getListBySearch(Map<String, Object> map) {
		return dao.getListBySearch(map);
	}

	@Override
	public boolean update(PKSchedule pkSchedule, PKStateDetail pkStateDetail) {
		return dao.update(pkSchedule) && pkStateDetailSO.add(pkStateDetail);
	}

	@Override
	public boolean update(PKSchedule pkSchedule, AdminLog adminLog) {
		return dao.update(pkSchedule) && adminLogSO.add(adminLog);
	}

	@Override
	public List<PKSchedule> getList(String pkuseruid, EMajor major, EStudentLevel level, List<EPKStage> stages) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.pkuseruid, pkuseruid);
		map.put(MapperDict.major, major);
		map.put(MapperDict.level, level);
		map.put(MapperDict.stage + MapperDict.suffix_in_key, stages);
		return dao.getList(map);
	}

	@Override
	public boolean update(PKSchedule pkSchedule, User user, Judge judge, TradeDetail tradeDetail, PKJudgement pkJudgement) {
		return dao.update(pkSchedule) && userSO.update(user) && judgeSO.update(judge) && tradeDetailSO.add(tradeDetail) && pkJudgementSO.update(pkJudgement);
	}

	@Override
	public List<PKSchedule> getListByAllJudgementuid(Pager pager, String judgementuid, EMajor major, List<EPKStage> stages, DateTime createtime) {
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.judgementuid_all, judgementuid);
		map.put(MapperDict.major, major);
		map.put(MapperDict.createtime + MapperDict.suffix_greaterOrEqual_key, createtime);
		map.put(MapperDict.stage + MapperDict.suffix_in_key, stages);
		return dao.getList(map);
	}

	@Override
	public boolean update(PKSchedule pkSchedule, PKStateDetail pkStateDetail, Conference conference) {
		return dao.update(pkSchedule) && conferenceSO.add(conference) && pkStateDetailSO.add(pkStateDetail);
	}

	@Override
	public boolean update(PKSchedule pkSchedule, PKStateDetail pkStateDetail, AdminLog adminLog) {
		return dao.update(pkSchedule) && pkStateDetailSO.add(pkStateDetail) && adminLogSO.add(adminLog);
	}

	@Override
	public boolean updateFile(String uid, String file) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.uid, uid);
		map.put(MapperDict.file, file);
		return dao.updateFields(map);
	}

	@Override
	public boolean update(PKSchedule pkSchedule, PKStateDetail pkStateDetail, User user, TradeDetail tradeDetail) {
		return dao.update(pkSchedule) && userSO.update(user) && tradeDetailSO.add(tradeDetail) && pkStateDetailSO.add(pkStateDetail);
	}

	@Override
	public List<PKSchedule> getListByPKUser2(String pkuseruid2, EMajor major, EStudentLevel level, List<EPKStage> stages) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.pkuseruid2, pkuseruid2);
		map.put(MapperDict.major, major);
		map.put(MapperDict.level, level);
		map.put(MapperDict.stage + MapperDict.suffix_in_key, stages);
		return dao.getList(map);
	}

	@Override
	public boolean update(PKSchedule pkSchedule, PKStateDetail pkStateDetail, List<PKJudgement> list) {
		if (list != null && list.size() > 0) {
			for (PKJudgement pkJudgement : list) {
				pkJudgementSO.add(pkJudgement);
			}
		}
		return update(pkSchedule) && pkStateDetailSO.add(pkStateDetail);
	}
}
