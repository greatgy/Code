package com.supergenius.xo.manager.service;

import java.util.List;
import java.util.Map;

import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.base.enums.EUser;
import com.supergenius.xo.manager.entity.Certificate;
import com.supergenius.xo.manager.entity.UserLevel;
import com.supergenius.xo.manager.enums.ELevelChannel;
import com.supergenius.xo.manager.enums.EMajor;
import com.supergenius.xo.manager.enums.EStudentLevel;
import com.supergenius.xo.user.entity.Score;
import com.supergenius.xo.user.entity.ScoreDetail;
import com.supergenius.xo.user.entity.TradeDetail;
import com.supergenius.xo.user.entity.User;

/**
 * 级别明细SO
 * 
 * @author guanshiqian
 * @date 2016-4-27 下午2:19:08
 */
public interface UserLevelSO extends BaseSO<UserLevel> {

	/**
	 * 将用户同type和同major的userlevel设置为disable
	 * @param userUid
	 * @param type
	 * @param major
	 * @return
	 * @author chenminchang
	 * @create 2016-10-31上午11:49:09
	 */
	boolean disableOther(String userUid, EUser type, EMajor major);
	
	/**
	 * 根据搜索得到数量
	 * 
	 * @param map
	 * @return
	 */
	int getUserCount(Map<String, Object> map);

	/**
	 * 根据搜索条件得到list
	 * 
	 * @param map
	 * @return
	 */
	List<UserLevel> getUserList(Map<String, Object> map);

	/**
	 * 获得userlevel对象
	 * 
	 * @param userUid
	 * @param type
	 * @param major
	 * @author liubin
	 * @createtime 2016-8-17下午3:24:32
	 * @return UserLevel
	 */
	UserLevel getOne(String userUid, EUser type, EMajor major);
	
	/**
	 * 根据create的升序获取
	 * @param useruid
	 * @param type
	 * @param channels
	 * @return
	 * @author chenminchang
	 * @create 2016-11-1上午11:09:44
	 */
	List<UserLevel> getList(String useruid, EUser type, List<ELevelChannel> channels);

	/**
	 * 学员积分晋级更新所有状态
	 * 
	 * @param user
	 * @param forwardScore
	 * @param forwardScoreDetail
	 * @param originalScore
	 * @param originalScoreDetail
	 * @param upGradeScore
	 * @param upGradeScoreDetail
	 * @param studentLevel
	 * @param major
	 * @author liubin
	 * @createtime 2016-8-24下午3:34:01
	 * @return boolean
	 */
	boolean update(User user, Score forwardScore, ScoreDetail forwardScoreDetail, Score originalScore, ScoreDetail originalScoreDetail, Score upGradeScore, ScoreDetail upGradeScoreDetail,
			EStudentLevel studentLevel, EMajor major, TradeDetail tradeDetail);

	/**
	 * 获取学员某个级别的记录
	 * @param uid
	 * @param majordomo
	 * @return
	 * @author XieMing
	 * 2016-9-5 下午7:08:08
	 */
	UserLevel getOne(String uid, EUser type);
	
	/**
	 * 通过channel获取总数
	 * @param channel
	 * @return
	 * @author chenminchang
	 * @create 2016-10-31上午9:40:42
	 */
	int getCountByChannel(ELevelChannel channel);
	
	/**
	 * 根据type和major获取总数
	 * @param major
	 * @param type
	 * @return
	 * @author chenminchang
	 * @create 2016-10-31下午12:00:20
	 */
	int getCount(EMajor major, EUser type);
	
	/**
	 * 更新指定userlevel的状态
	 * @param uid
	 * @param status
	 * @return
	 * @author chenminchang
	 * @create 2016-10-31上午10:36:24
	 */
	boolean updateStatus(String uid, EStatus status);
	
	
	/**
	 * 根据type和major获取list
	 * @param userUid
	 * @param type
	 * @param major
	 * @return
	 * @author chenminchang
	 * @create 2016-10-31下午2:54:15
	 */
	List<UserLevel> getList(String userUid, EUser type, EMajor major);
	
	/**
	 * 根据useruid和type获取list,并根据等级降序
	 * @param userUid
	 * @param type
	 * @return
	 * @author chenminchang
	 * @create 2016-11-2上午10:32:15
	 */
	List<UserLevel> getList(String userUid, EUser type);
	
	/**
	 * 获取查询总数
	 * @return
	 * @author chenminchang
	 * @create 2016-10-31下午6:33:42
	 */
	int searchCount(Map<String, Object> map);
	
	/**
	 * 获取查询对象list
	 * @param map
	 * @return
	 * @author chenminchang
	 * @create 2016-10-31下午6:34:45
	 */
	List<UserLevel> search(Map<String, Object> map);
	
	/**
	 * 增加userlevel，并增加相应的证书
	 * @param userLevel
	 * @param adminLog
	 * @return
	 * @author chenminchang
	 * @create 2016-11-1下午4:47:21
	 */
	boolean addAndDisablether(List<UserLevel> userLevels, List<Certificate>  addCertificates, List<Certificate>  removeCertificates, AdminLog adminLog);
	
	/**
	 * 根据专业和级别得到某个学员的对象
	 * @param useruid
	 * @param major
	 * @param level
	 * @return
	 * @author liubin
	 * @createtime 2016-11-4下午9:08:21
	 * @return UserLevel
	 */
	UserLevel getOne(String useruid, EMajor major, int levelTo);
	
	/**
	 * 学员级别变动时，将最新的级别记录状态改为disable，并插入新的userlevel记录
	 * @param status
	 * @param userLevel
	 * @return
	 * @author liubin
	 * @createtime 2016-11-4下午9:27:12
	 * @return boolean
	 */
	boolean update(UserLevel userLevel, UserLevel newUserLevel);

	/**
	 * 根据useruid和level获取一条 
	 * @param useruid
	 * @param level
	 * @return
	 * @author chenminchang
	 * @create 2016年11月20日下午5:28:37
	 */
	UserLevel getOne(String useruid, EStudentLevel level);
}
