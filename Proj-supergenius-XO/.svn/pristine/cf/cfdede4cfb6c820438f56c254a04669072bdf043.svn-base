package com.supergenius.xo.manager.service;

import java.util.List;
import java.util.Map;

import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.manager.entity.MyVideo;

/**
 * 我的视频SO
 * 
 * @author liubin
 * @date 2016-8-12 上午10:45:22
 */
public interface MyVideoSO extends BaseSO<MyVideo> {

	/**
	 * 根据查询得到我的视频的List
	 * @param map
	 * @return
	 */
	List<MyVideo> getListBySearch(Map<String, Object> map);
	
	/**
	 * 得到我已购视频的状态，0表示即将到期，-1表示已经到期，1表示还未到期
	 * @param userUid
	 * @param videoUid
	 * @param days
	 * @return
	 */
	int getStatus(String userUid, String videoUid, int days);
	
	/**
	 * 得到MyVideo实体
	 * @param videoUid
	 * @param userUid
	 * @author liubin
	 * @createtime 2016-8-16下午7:07:08
	 * @return MyVideo
	 */
	MyVideo getOne(String videoUid, String userUid);
	
	/**
	 * 得到还有三天就过期的视频
	 * @param expiretimeBeforeThreeday
	 * @return
	 * @author liubin
	 * @createtime 2016-9-8下午9:12:18
	 * @return List<MyVideo>
	 */
	List<MyVideo> getList(String expiretimeBeforeThreeday);
	
	/**
	 * 根据useruid得到MyVideo对象
	 * @param userUid
	 * @return
	 * @author liubin
	 * @createtime 2016-9-28下午2:48:21
	 * @return MyVideo
	 */
	MyVideo getOne (String userUid);
}
