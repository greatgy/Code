package com.supergenius.web.front.life.helper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.genius.core.cache.rule.Rule;
import com.genius.core.cache.utils.RedisUtil;
import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.life.entity.Video;
import com.supergenius.xo.life.rule.LifeVideoClickCountRlue;
import com.supergenius.xo.life.service.VideoSO;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.service.UserSO;

/**
 * 精彩视频HP
 * 
 * @author ChenQi
 * @date 2018年5月17日12:22:12
 */
public class VideoHP extends BaseHP {

	private static VideoSO so;
	
	private static UserSO userSO;

	public static VideoSO getSO() {
		if (so == null) {
			so = (VideoSO) spring.getBean(VideoSO.class);
		}
		return so;
	}
	
	private static UserSO getUserSO() {
		if (userSO == null) {
			userSO = (UserSO) spring.getBean(UserSO.class);
		}
		return userSO;
	}

	/**
	 * @param uid
	 * @return
	 */
	public static Video getOneVideo(String uid){
		return getSO().get(uid);
	}
	/**
	 * 获得视频
	 * 
	 * @return
	 * @author ChenQi
	 * @date 2018年5月17日12:22:12
	 * @return List<Article>
	 */
	public static List<Video> getVideo(int pagerSize, int pagenum, Long cid) {
		Pager pager = Pager.getNewInstance(pagenum, pagerSize);
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.cid, cid);
		List<Video> list = getSO().getList(map);
		organized(list);
		return list;
	}
	/**
	 * @author 雍雪振
	 * @time 2018年6月2日下午12:40:45
	 * @description:获得会员专区的视频
	 */
	public static List<Video> getMemberVideo(int pagerSize, int pagenum, Long cid) {
		Pager pager = Pager.getNewInstance(pagenum, pagerSize);
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.cid, cid);
		map.put(MapperDict.ismember, 1);
		List<Video> list = getSO().getList(map);
		organized(list);
		return list;
	}
	
	/**
	 * 获得热门视频
	 * 
	 * @return
	 * @author ChenQi
	 * @date 2018年5月17日12:22:12
	 * @return List<Article>
	 */
	public static List<Video> getHotVideo(int pagerSize, int pagenum, Long cid) {
		List<Video> result = new ArrayList<>();
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.cid, cid);
		List<Video> list = getSO().getList(map);
		organized(list);
		list.sort(COMPARATOR);
		int maxHotsize = pagenum * pagerSize;
		if (maxHotsize > list.size()) {
			maxHotsize = list.size();
		}
		if ((pagenum - 1) * pagerSize <= maxHotsize) {
			result = list.subList((pagenum - 1) * pagerSize, maxHotsize);
		}
		return result;
	}

	/**
	 * 获得相关视频
	 * 
	 * @return
	 * @author ChenQi
	 * @date 2018年5月17日12:22:12
	 * @return List<Video>
	 */
	public static List<Video> getRelateVideo(int pagerSize, Long cid, Video video) {
		Pager pager = Pager.getNewInstance(0, pagerSize);
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.cid, cid);
		map.put(MapperDict.grade, video.getGrade());
		map.put(MapperDict.sid, video.getSid());
		map.put(MapperDict.keywords, video.getKeywords());
		List<Video> list = getSO().getList(map);
		organized(list);
		return list;
	}

	/**
	 * 对视频list设置fromuser
	 * 
	 * @param list
	 * @return
	 */
	public static void organized(List<Video> list) {
		Map<String, Object> map = new HashMap<>();
		List<EStatus> liststatus = new ArrayList<>();
		liststatus.add(EStatus.start);
		liststatus.add(EStatus.enable);
		liststatus.add(EStatus.wait);
		map.put(MapperDict.statuslist, liststatus);
		List<User> users = getUserSO().getList(map);
		Rule rule = null;
		for (Video video : list) {
			for (User user : users) {
				if (video.getUseruid().equals(user.getUid())) {
					video.setUser(user);
				}
			}
			rule = new LifeVideoClickCountRlue(video.getUid());
			video.setClickCount(RedisUtil.getIncr(rule) > 0 ? RedisUtil.getIncr(rule) : 0);
		}
	}
	
	/**
	 * 对视频设置fromuser
	 * 
	 * @param list
	 * @return
	 */
	public static void organized(Video video) {
		video.setUser(getUserSO().get(video.getUseruid()));
		Rule rule = new LifeVideoClickCountRlue(video.getUid());
		video.setClickCount(RedisUtil.getIncr(rule) > 0 ? RedisUtil.getIncr(rule) : 0);
	}
	
	private static final Comparator<Video> COMPARATOR = new Comparator<Video>() {
		public int compare(Video o1, Video o2) {
			return o1.compareTo(o2);// 运用Video类的compareTo方法比较两个对象
		}
	};
}
