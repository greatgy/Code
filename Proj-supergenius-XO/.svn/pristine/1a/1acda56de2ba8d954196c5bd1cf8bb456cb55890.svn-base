package com.supergenius.xo.tpi.service;

import java.util.List;
import java.util.Map;

import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.tpi.entity.Project;
import com.supergenius.xo.tpi.entity.TpiUser;
import com.supergenius.xo.tpi.enums.EInvestType;
import com.supergenius.xo.tpi.enums.EMergeType;
import com.supergenius.xo.tpi.enums.EPayType;
import com.supergenius.xo.tpi.enums.ETpiUserType;
import com.supergenius.xo.tpi.enums.ETpiuserState;
import com.supergenius.xo.tpi.enums.EWishType;

/**
 * 并购平台会员管理SO
 * 
 * @author ShangJianguo
 */
public interface TpiuserSO extends BaseSO<TpiUser> {

	/**
	 * 修改状态
	 * 
	 * @param uids
	 * @param status
	 * @return
	 * @author ShangJianguo
	 */
	boolean setStatus(String[] uids, EStatus status);

	/**
	 * 修改是否置顶
	 * 
	 * @param uids
	 * @param state
	 * @return
	 * @author ShangJianguo
	 */
	boolean setTop(String[] uids, boolean state);

	/**
	 * 设置是否放在首页
	 * 
	 * @param uids
	 * @param index
	 * @return
	 * @author ShangJianguo
	 */
	boolean setIndex(String[] uids, boolean index);

	/**
	 * 审核
	 * 
	 * @param uids
	 * @param result
	 * @param content
	 * @return
	 * @author ShangJianguo
	 */
	boolean audit(String[] uids, boolean result, String content);

	/**
	 * 特批会员
	 * 
	 * @param uids
	 * @return
	 * @author ShangJianguo
	 */
	boolean specialaudit(String[] uids, EPayType paytype);

	/**
	 * 并购机构 的类型获取
	 * 
	 * @param mergetype
	 * @return
	 */
	public TpiUser getOne(EMergeType mergetype, ETpiuserState state);

	/**
	 * 获取不同并购类型的并购机构的数量
	 * 
	 * @param mergetype
	 * @param state
	 * @return
	 */
	public int getCount(EMergeType mergetype, ETpiuserState state);

	/**
	 * 获取不同注册类型的数量
	 * 
	 * @param tpiusertype
	 * @param isindex
	 * @return
	 */
	int getCount(ETpiUserType tpiusertype, boolean isindex);

	/**
	 * 获取某类机构的数量
	 * 
	 * @param type
	 * @return
	 * @author ShangJianguo
	 */
	int getCount(ETpiUserType type);

	/**
	 * 获取不同并购类型的并购机构的集合
	 * 
	 * @param pager
	 *            数量
	 * @param mergetype
	 * @param boo
	 *            如果为true，查询的只是置顶的
	 * @param state
	 *            状态
	 * @return
	 */
	public List<TpiUser> getList(Pager pager, EMergeType mergetype, boolean istop, ETpiuserState state);

	/**
	 * 根据以下条件查询数据
	 * 
	 * @param istop
	 * @param type
	 * @param pager
	 * @param state
	 * @return
	 * @author ShangJianguo
	 */
	List<TpiUser> getList(boolean istop, ETpiUserType type, Pager pager, ETpiuserState state);

	/**
	 * 根据tpiusertype获取数据列表
	 * 
	 * @param tpiusertype
	 * @param isindex
	 * @param pager
	 * @param state
	 * @return
	 */
	List<TpiUser> getList(ETpiUserType tpiusertype, boolean isindex, Pager pager, ETpiuserState state);

	/**
	 * 根据条件查询数据
	 * 
	 * @param queryMap
	 * @param pager
	 * @return
	 * @author ShangJianguo
	 */
	List<TpiUser> getList(Map<String, Object> queryMap, Pager pager);

	/**
	 * 根据以下条件查询数据
	 * 
	 * @param istop
	 * @param type
	 * @param pager
	 * @return
	 * @author ShangJianguo
	 */
	List<TpiUser> getList(boolean istop, EInvestType type, Pager pager, ETpiuserState state);

	/**
	 * 根据以下条件查询数据
	 * 
	 * @param istop
	 * @param type
	 * @param pager
	 * @return
	 * @author ShangJianguo
	 */
	List<TpiUser> getList(EInvestType type, Pager pager, ETpiuserState state);

	/**
	 * 查询单个类别下的用户的信息列表
	 * 
	 * @param type
	 * @param pager
	 * @return
	 * @author ShangJianguo
	 */
	List<TpiUser> getList(ETpiUserType type, Pager pager);

	/**
	 * 查询特定状态的机构信息列表
	 * 
	 * @param type
	 * @param pager
	 * @param state
	 * @return
	 * @author ShangJianguo
	 */
	List<TpiUser> getList(ETpiUserType type, Pager pager, ETpiuserState state);

	/**
	 * 获取ids内的所有数据
	 * 
	 * @param uids
	 * @return
	 * @author ShangJianguo
	 */
	List<TpiUser> getList(List<String> uids);

	/**
	 * 根据用户的uid获取想要投资的项目
	 * 
	 * @param useruid
	 * @param wishType
	 * @param pager
	 * @return
	 * @author ShangJianguo
	 */
	List<Project> getWishProjects(String useruid, EWishType wishType, Pager pager);

	/**
	 * 完成用户的邮箱校验
	 * 
	 * @param uid
	 * @return
	 * @author ShangJianguo
	 */
	boolean validemail(String uid);

	/**
	 * 验证邮箱是否存在
	 * 
	 * @param email
	 * @return
	 * @author liushaomin
	 */
	TpiUser getByEmail(String email);

	/**
	 * 登录
	 * 
	 * @param email
	 * @return
	 * @author liushaomin
	 */
	TpiUser getByEmailLogin(String email);

	/**
	 * 获取邮箱是否已经注册
	 * 
	 * @param email
	 * @return 若未注册，则返回true
	 * @author ShangJianguo
	 */
	boolean isUniqueEmail(String email);

	/**
	 * 更新密码
	 * 
	 * @param tpiUser
	 * @author liushaomin
	 */
	boolean updatePwd(TpiUser tpiUser);

	/**
	 * 更新头像(需要更新消息表的中的头像)
	 * 
	 * @param tpiUser
	 * @author liushaomin
	 */
	boolean updateAvatar(TpiUser tpiUser);

	/**
	 * 获取数量
	 * 
	 * @param type
	 * @param state
	 * @return
	 * @author ShangJianguo
	 */
	int getCount(ETpiUserType type, ETpiuserState state);

	/**
	 * 缴费成功之后，更新状态
	 * 
	 * @return
	 * @author LiJiacheng
	 */
	boolean updateTpiUserStatus(String uid);

}
