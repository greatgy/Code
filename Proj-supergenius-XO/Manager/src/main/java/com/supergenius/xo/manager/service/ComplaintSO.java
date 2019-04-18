package com.supergenius.xo.manager.service;

import java.util.List;
import java.util.Map;

import com.genius.model.base.entity.Pager;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.base.enums.EUser;
import com.supergenius.xo.manager.entity.Certificate;
import com.supergenius.xo.manager.entity.Complaint;
import com.supergenius.xo.manager.entity.Expert;
import com.supergenius.xo.manager.entity.Judge;

/**
 * 投诉举报SO
 * @author XieMing
 * @date 2016-7-18 下午2:27:32
 */
public interface ComplaintSO extends BaseSO<Complaint> {

	/**
	 * 根据被举报人的类型获取举报列表
	 * @param tousertype
	 * @return
	 */
	List<Complaint> getListByToUserType(Pager pager, EUser tousertype);
	
	/**
	 * 根据搜索条件得到list
	 * @param maps
	 * @return
	 */
	List<Complaint> search(Map<String, Object> map);

	/**
	 * 根据相关事件的uid即refuid获取一条记录
	 * @param uid
	 * @author XieMing
	 * 2016-8-16 下午3:32:50
	 */
	Complaint getOneByRefuid(String uid);
	
	/**
	 * 根据举报者，被举报者，相关事件，获取一条数据
	 * @param fromuseruid
	 * @param touseruid
	 * @param refuid
	 * @return
	 * @author chenminchang
	 * @create 2016-10-17下午2:35:58
	 */
	Complaint getOneByRefuid(String fromuseruid, String touseruid, String refuid);
	
	/**
	 * 根据用户类型获得数量
	 * @param eUser
	 * @return
	 * @author liubin
	 * @createtime 2016-11-6下午6:32:33
	 * @return int
	 */
	int getCount(EUser tousertype);
	
	/**
	 * 得到状态为enable的数量
	 * @return
	 * @author liubin
	 * @createtime 2016-11-6下午6:33:00
	 * @return int
	 */
	int getEableCount();
	
	/**
	 * 根据搜索条件得到count
	 * @param map
	 * @return
	 * @author liubin
	 * @createtime 2016-11-6下午7:06:19
	 * @return int
	 */
	int searchCount(Map<String, Object> map);
	
	/**
	 * 根据fromuseruid得到list
	 * @param fromuseruid
	 * @return
	 * @author liubin
	 * @createtime 2016-11-7下午2:49:53
	 * @return int
	 */
	List<Complaint> getList(String fromuseruid);
	
	/**
	 * 更新举报结果
	 * @param complaint
	 * @param adminLog
	 * @return
	 * @author liubin
	 * @createtime 2016-11-7下午4:45:39
	 * @return boolean
	 */
	boolean update(Complaint complaint, AdminLog adminLog);
	
	/**
	 * 根据被举报者、相关事件、和被举报人类型获得一个Complaint对象
	 * @param touseruid
	 * @param refuid
	 * @param tousertype
	 * @return
	 * @author liubin
	 * @createtime 2016-11-8上午10:41:12
	 * @return Complaint
	 */
	Complaint getOne(String touseruid, String refuid, EUser tousertype);
	
	/**
	 * 根据搜索条件得到list
	 * @param map
	 * @return
	 * @author liubin
	 * @createtime 2016-11-13下午6:37:14
	 * @return List<Complaint>
	 */
	List<Complaint> searchList(Map<String, Object> map);
	
	/**
	 * 裁判受到惩罚时对裁判和证书同时更新
	 * @param complaint
	 * @param judge
	 * @param certificate
	 * @return
	 * @author liubin
	 * @createtime 2016年11月18日下午6:06:36
	 * @return boolean
	 */
	boolean update(Complaint complaint, Judge judge, Certificate certificate);
	
	/**
	 * 专家被举报受到惩罚
	 * @param complaint
	 * @param expert
	 * @param certificate
	 * @return
	 * @author liubin
	 * @createtime 2016年11月18日下午6:15:34
	 * @return boolean
	 */
	boolean update(Complaint complaint, Expert expert, Certificate certificate);
}
