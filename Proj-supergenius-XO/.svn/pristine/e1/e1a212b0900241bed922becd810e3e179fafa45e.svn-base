package com.supergenius.xo.tpi.serviceimpl;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.tpi.constants.MapperDict;
import com.supergenius.xo.tpi.dao.AccountDetailDao;
import com.supergenius.xo.tpi.entity.AccountDetail;
import com.supergenius.xo.tpi.entity.TpiUser;
import com.supergenius.xo.tpi.enums.EAccountDetailType;
import com.supergenius.xo.tpi.enums.EAccountState;
import com.supergenius.xo.tpi.service.AccountDetailSO;

/**
 * 
 * @author LiJiacheng
 */
@Service
public class AccountDetailSOImpl extends BaseSOImpl<AccountDetail> implements AccountDetailSO {

	@Autowired
	AccountDetailDao accountDetailDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.genius.xo.base.serviceimpl.BaseSOImpl#getDao()
	 * 
	 * @author: LiJiacheng 2015-10-29 下午5:53:28
	 */
	@Override
	protected BaseDao<AccountDetail> getDao() {
		return accountDetailDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.tpi.service.AccountDetailSO#add(com.supergenius.xo.tpi.enums.EAccountDetailType, java.lang.Double, java.lang.String, java.lang.String, java.lang.String,
	 * com.supergenius.xo.tpi.enums.EAccountState)
	 */
	@Override
	public boolean add(EAccountDetailType detailType, Double money, String useruid, String payuid, String fromuid, EAccountState state) {
		AccountDetail detail = new AccountDetail();
		detail.setType(detailType);
		if (money != null) {
			detail.setMoney(money);
		}
		detail.setUseruid(useruid);
		detail.setPayuid(payuid);
		detail.setFromuid(fromuid);
		detail.setState(state);
		return accountDetailDao.insert(detail);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.tpi.service.AccountDetailSO#get(java.lang.String, java.lang.String)
	 */
	@Override
	public AccountDetail get(String useruid, String payuid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, useruid);
		map.put(MapperDict.payuid, payuid);
		return accountDetailDao.getOne(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.tpi.service.AccountDetailSO#update(com.supergenius.xo.tpi.entity.AccountDetail, double, com.supergenius.xo.tpi.enums.EAccountState)
	 */
	@Override
	public boolean update(AccountDetail detail, double money, EAccountState state) {
		if (detail == null) {
			return false;
		}
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.uid, detail.getUid());
		map.put(MapperDict.money, money);
		map.put(MapperDict.state, state);
		return accountDetailDao.updateFields(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.tpi.service.AccountDetailSO#add(com.supergenius.xo.tpi.entity.TpiUser, java.lang.String)
	 * 
	 * @author: LiJiacheng 2015-11-2 上午10:40:37
	 */
	@Override
	public boolean add(TpiUser tpiUser, String money, String payuid) {
		AccountDetail accountDetail = new AccountDetail();
		accountDetail.setUseruid(tpiUser.getUid());
		accountDetail.setMoney(Double.parseDouble(money));
		accountDetail.setType(EAccountDetailType.registe);
		accountDetail.setState(EAccountState.finish);
		accountDetail.setPayuid(payuid);
		accountDetail.setStatus(EStatus.enable);
		accountDetail.setCreatetime(DateTime.now());
		return accountDetailDao.insert(accountDetail);
	}

}
