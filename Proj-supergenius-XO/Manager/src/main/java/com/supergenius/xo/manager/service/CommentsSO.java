package com.supergenius.xo.manager.service;

import java.util.List;
import java.util.Map;

import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.manager.entity.Comments;
import com.supergenius.xo.user.enums.EComment;

/**
 * 评论SO
 * @author XieMing
 * @date 2016-7-18 下午2:28:16
 */
public interface CommentsSO extends BaseSO<Comments> {

	/**
	 * 根据fromuid和type获取所有的类别
	 * @param fromuid
	 * @param type
	 * @param pager 如果为空，则不分页，查询所有的
	 * @param order 排序方式
	 * @return
	 * @author ShangJianguo
	 */
	List<Comments> getList(String fromuid, EComment type, Pager pager, String order);
	
	/**
	 * 获得评论的数量
	 * @param fromuid
	 * @param channel
	 * @param type
	 * @author liubin
	 * @createtime 2016-8-17上午9:35:22
	 * @return int
	 */
	int getCount(String fromuid, EChannel channel, EComment type);
	
	/**
	 * 获得评论的List
	 * @param fromuid
	 * @param channel
	 * @param type
	 * @author liubin
	 * @createtime 2016-8-17上午9:36:41
	 * @return List<Comments>
	 */
	List<Comments> getList(String fromuid, EChannel channel, EComment type);

	/**
	 * 得到用户的赞
	 * @param uid
	 * @param praise
	 * @return
	 * @author XieMing
	 * 2016-8-25 上午11:54:14
	 */
	List<Comments> getList(String uid, EComment praise);

	/**
	 * 判断用户是否对该挑战点赞
	 * @param uid
	 * @param uid2
	 * @param eChannel
	 * @return
	 * @author XieMing
	 * 2016-8-26 下午7:03:38
	 */
	Comments getOne(String touid, String fromuseruid, EComment type, EChannel eChannel);
	
	/**
	 * 得到评论或赞的总数
	 * @param channel
	 * @return
	 * @author chenminchang
	 * @create 2016-11-3下午3:48:26
	 */
	int getCount(EComment type);
	
	/**
	 * 得到评论或赞的总人数
	 * @param channel
	 * @return
	 * @author chenminchang
	 * @create 2016-11-3下午3:49:23
	 */
	int getSpeakCount(EComment... type);
	
	/**
	 * 连表查询count
	 * @param map
	 * @return
	 * @author chenminchang
	 * @create 2016-11-4下午4:43:54
	 */
	int searchCount(Map<String, Object> map);
	
	/**
	 * 连表查询list
	 * @param map
	 * @return
	 * @author chenminchang
	 * @create 2016-11-4下午4:44:38
	 */
	List<Comments> search(Map<String, Object> map);
	
	/**
	 * 根据touid删除评论
	 * @param touid
	 * @return
	 * @author chenminchang
	 * @create 2016-11-8下午5:16:55
	 */
	boolean deleteByTouid(String touid);
	
	/**
	 * 根据uid更新评论状态
	 * @param uid
	 * @param status
	 * @return
	 * @author chenminchang
	 * @create 2016-11-8下午6:04:40
	 */
	boolean updateStatus(String uid, EStatus status, AdminLog adminLog);
	
	/**
	 * 根据touid更改状态
	 * @param touid
	 * @param status
	 * @return
	 * @author chenminchang
	 * @create 2016-11-8下午6:07:26
	 */
	boolean updateStatusByTouid(String touid, EStatus status);
	
	/**
	 * 根据touid获取评论对象
	 * @param touid
	 * @return
	 * @author chenminchang
	 * @create 2016-11-9上午10:16:12
	 */
	List<Comments> getList(String touid);
}
