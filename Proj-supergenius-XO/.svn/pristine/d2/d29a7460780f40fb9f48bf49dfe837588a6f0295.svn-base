package com.supergenius.xo.user.service;

import java.util.List;
import java.util.Map;

import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.base.enums.EUser;
import com.supergenius.xo.user.entity.TradeDetail;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.enums.EUserChannel;

/**
 * UserSO
 * 
 * @author chenminchang
 * @date 2016-3-24 下午12:53:10
 */
public interface UserSO extends BaseSO<User> {

	/**
	 * 根据oid获得用户
	 * 
	 * @param oid
	 * @return
	 */
	User get(int oid);

	/**
	 * 根据oids数组查询user
	 * 
	 * @param oids
	 * @return
	 */
	List<User> getList(int[] oids);
	
	/**
	 * 获得所有用户uid
	 * @param map
	 * @return
	 * @author yangguang
	 * @createtime 2017年9月4日11:24:22
	 * @return 
	 */
	List<String> getUids(Map<String, Object> map);

	/**
	 * 根据showname查询用户
	 * 
	 * @param name
	 * @return
	 */
	List<User> search(String showname);

	/**
	 * 根据usersn获得用户
	 * 
	 * @param object
	 * @return
	 */
	User getByUsersn(String usersn);
	
	/**
	 * 根据usersn得到一个status为enable的user
	 * @param usersn
	 * @return
	 * @author chenminchang
	 * @create 2016-10-8上午10:56:37
	 */
	User getEnableByUsersn(String usersn);

	/**
	 * 根据email获得用户,注意使用newemail和 email
	 * 
	 * @param email
	 * @return
	 */
	User getByEmail(String email);
	
	/**
	 * 根据email得到一个status为enable的user
	 * @param email
	 * @return
	 * @author chenminchang
	 * @create 2016-10-8上午10:55:45
	 */
	User getEnableByEmail(String email);

	/**
	 * 根据email和status获得用户
	 * @param email
	 * @param allstatus
	 * @return
	 */
	boolean isExistEmail(String email);

	/**
	 * 根据mobile（手机）获得用户
	 * 
	 * @param email
	 * @return
	 */
	User getByMobile(String mobile);

	/**
	 * 修改账户余额
	 * 
	 * @param user
	 * @param account
	 * @return
	 */
	boolean updateAccount(User user, Double account);

	/**
	 * 修改登录密码
	 * 
	 * @param user
	 * @return
	 */
	boolean updatePwd(User user);
	
	/**
	 * 更新user的data
	 * @param uid
	 * @param data
	 * @return
	 * @author chenminchang
	 * @create 2016年12月2日上午10:46:03
	 */
	boolean updateData(String uid, String data);

	/**
	 * 修改支付密码
	 * 
	 * @param user
	 * @return
	 */
	boolean updatePayPwd(User user);

	/**
	 * 修改绑定邮箱
	 * 
	 * @param user
	 * @return
	 */
	boolean updateEmail(User user);

	/**
	 * 修改新邮箱并更新邮箱验证码
	 * 
	 * @param user
	 * @return
	 */
	boolean updateNewemail(User user);

	/**
	 * 更新会员
	 * 
	 * @param user
	 * @return
	 */
	boolean updateUsersn(User user);

	/**
	 * 更新状态
	 * 
	 * @param user
	 * @return
	 */
	boolean updateStatus(User user);
	
	/**
	 * 更新用户发送重置密码邮件的时间以及resetpwd字段
	 * 
	 * @param user
	 * @return
	 */
	boolean updateresetpwd(User user);
	
	/**
	 * 更新用户account
	 * @param user
	 * @return
	 */
	boolean updateAccount(User user);

	/**
	 * 添加特邀嘉宾
	 * @param user
	 * @return
	 * @author XieMing
	 * 2016-5-17 上午10:44:53
	 */
	boolean insertInvite(User user);
	
	/**
	 * 通过状态获取用户的数量
	 * @param status
	 * @return
	 * @author XieMing
	 * 2016-5-27 下午7:15:51
	 */
	int getCount(EStatus status);
	
	/**
	 * 通过户来自渠道获取用户数量
	 * @param userChannel
	 * @return
	 */
	int getCount(EUserChannel userChannel);
	
	/**
	 * 根据裁判的sn与用户的showname模糊查询裁判,挑战双放如果是裁判则需要过滤掉
	 * @param map
	 * @return
	 * @author XieMing
	 * 2016-8-8 下午12:16:29
	 */
	List<Map<String, Object>> searchJudge(String keyword, Pager pager, int major, String useruid, String useruid2);

	/**
	 * 查询某一专业某一级别的所有学员
	 * @param map
	 * @return
	 * @author XieMing
	 * 2016-8-8 下午2:59:35
	 */
	List<User> searchStudent(String keyword, int major, int levelTo, EUser usertype, Pager pager);

	/**
	 * 冻结费用
	 * @param user
	 * @param cost
	 * @return
	 * @author XieMing
	 * 2016-8-11 下午1:51:26
	 */
	boolean updateFreezeaccount(User user, Double cost);

	/**
	 * 解冻费用
	 * @param user
	 * @param cost
	 * @return
	 * @author XieMing
	 * 2016-9-5 下午5:37:48
	 */
	boolean updateUnFreezeaccount(User user, Double cost);
	
	/**
	 * 更新账户余额与累计支出
	 * @param user
	 * @return
	 * @author XieMing
	 * 2016-8-24 下午4:42:17
	 */
	boolean updateAccountAndTotalpay(User user);
	
	/**
	 *  更新账户余额,累计支出和冻结金额
	 * @param user
	 * @param cost
	 * @return
	 * @author liubin
	 * @createtime 2016-9-8下午7:16:14
	 * @return boolean
	 */
	boolean updateAccountAndTotalpayAndFreezeAccount(User user, Double cost);

	/**
	 * 管理后台操作用户时添加adminlog记录
	 * @param user
	 * @param adminLog
	 * @return
	 * @author XieMing
	 * 2016-9-18 上午11:32:58
	 */
	boolean updateStatus(User user, AdminLog adminLog);

	/**
	 * 管理后台特批余额添加adminlog记录
	 * @param user
	 * @param adminLog
	 * @return
	 * @author XieMing
	 * 2016-9-18 下午12:02:55
	 */
	boolean updateAccount(User user, AdminLog adminLog);
	
	/**
	 * 用户获得收入，同时插入一条交易明细
	 * @param user
	 * @param tradeDetail
	 * @return
	 * @author liubin
	 * @createtime 2016-11-5下午4:48:04
	 * @return boolean
	 */
	boolean update(User user, TradeDetail tradeDetail);

	/**
	 * 更新用户的角色
	 * @param user
	 * @return
	 * @author XieMing
	 * 2016年12月7日 下午7:37:55
	 */
	boolean updateType(User user);
	
	/**
	 * 找人搜索
	 * @param map
	 * @return
	 * @author ChenQi
	 * 2017年4月28日 17:17:20
	 */
	List<User> searchCustomer(Map<String, Object> map, Pager pager);
	
	/**
	 * 找人搜索count
	 * @param map
	 * @return
	 * @author liubin
	 * @createtime 2017年5月31日下午4:16:22
	 * @return int
	 */
	int searchCount(Map<String, Object> map);
}
 