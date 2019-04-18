package com.supergenius.xo.manager.service;

import java.util.List;
import java.util.Map;

import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.manager.entity.Certificate;
import com.supergenius.xo.manager.enums.ECertificate;
import com.supergenius.xo.manager.enums.EMajor;

/**
 * 证书明细SO
 * @author XieMing
 * @date 2016-7-18 下午2:28:30
 */
public interface CertificateSO extends BaseSO<Certificate> {

	/**
	 * 根据用户uid和专业获取最新的记录
	 * @param major
	 * @param useruid
	 * @return
	 * @author XieMing
	 * 2016-8-18 下午4:04:46
	 */
	Certificate getOne(EMajor major, String useruid, ECertificate type);

	/**
	 * 根据用户uid与专业获取最新的一个证书
	 * @param uid
	 * @param major
	 * @return
	 * @author XieMing
	 * 2016-8-19 上午9:55:04
	 */
	Certificate getOne(String useruid, EMajor major);

	/**
	 * 根据证书类型获取用户该类证书的个数
	 * @param smba
	 * @return
	 * @author XieMing
	 * 2016-11-2 下午12:56:08
	 */
	int getCountByType(String useruid, ECertificate type);
	
	/**
	 * 获得本专业的指定几个类型的证书
	 * @param useruid
	 * @param major
	 * @param list
	 * @return
	 * @author chenminchang
	 * @create 2016-11-2下午2:41:17
	 */
	List<Certificate> getList(String useruid, EMajor major, List<ECertificate> list);
	
	/**
	 * 更新证书状态
	 * @param uid
	 * @return
	 * @author chenminchang
	 * @create 2016-11-2下午2:52:52
	 */
	boolean updateStatus(String uid, EStatus status);
	
	/**
	 * 根据查询条件得到数量
	 * @param map
	 * @return
	 * @author liubin
	 * @createtime 2016-11-8下午5:41:56
	 * @return int
	 */
	int searchCount(Map<String, Object> map);
	
	/**
	 * 根据查询条件得到List
	 * @param map
	 * @return
	 * @author liubin
	 * @createtime 2016-11-8下午5:42:39
	 * @return List<Certificate>
	 */
	List<Certificate> searchList(Map<String, Object> map);
	
	/**
	 * 更新证书状态并增加管理员操作信息
	 * @param certificate
	 * @param adminLog
	 * @return
	 * @author liubin
	 * @createtime 2016-11-9下午12:37:06
	 * @return boolean
	 */
	boolean update(Certificate certificate, AdminLog adminLog);
	
	/**
	 * 根据证书级别和useruid获得证书
	 * @param useruid
	 * @param type
	 * @return
	 * @author liubin
	 * @createtime 2016-11-13下午2:10:29
	 * @return Certificate
	 */
	Certificate getOne(String useruid, ECertificate type);
}
