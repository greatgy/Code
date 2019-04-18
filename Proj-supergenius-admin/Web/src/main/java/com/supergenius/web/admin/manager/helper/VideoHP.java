package com.supergenius.web.admin.manager.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.manager.entity.Video;
import com.supergenius.xo.manager.enums.EMajor;
import com.supergenius.xo.manager.enums.EVideoFrom;
import com.supergenius.xo.manager.service.VideoSO;
import com.supergenius.xo.user.enums.EGoods;
import com.supergenius.xo.user.service.OrderGoodsSO;
import com.supergenius.xo.user.service.UserSO;

/**
 * 视频管理HP
 * @author XieMing
 * @date 2016-10-20 上午11:30:21
 */
public class VideoHP extends BaseHP {
	
	private static VideoSO so;
	private static OrderGoodsSO orderGoodsSO;
	private static UserSO userSO;
	

	private static VideoSO getSO() {
		if (so == null) {
			so = spring.getBean(VideoSO.class);
		}
		return so;
	}
	
	private static OrderGoodsSO getOrderGoodsSO() {
		if (orderGoodsSO == null) {
			orderGoodsSO = spring.getBean(OrderGoodsSO.class);
		}
		return orderGoodsSO;
	}
	
	private static UserSO getUserSO() {
		if (userSO == null) {
			userSO = spring.getBean(UserSO.class);
		}
		return userSO;
	}
	
	/**
	 * 加载数据
	 * 
	 * @param model
	 * @return
	 * @author XieMing 2016-5-27 下午3:29:04
	 */
	public static Map<String, Object> query(Map<String, Object> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model);
		Map<String, Object> result = new HashMap<String, Object>();
		//map.put(BaseMapperDict.status, EStatus.enable);
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.keyword))) {
			map.put(MapperDict.title + MapperDict.suffix_like_key, model.get(ViewKeyDict.keyword).toString());
			map.put(MapperDict.sn + MapperDict.suffix_like_key, model.get(ViewKeyDict.keyword).toString());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.channelfrom))) {
			map.put(MapperDict.channelfrom, model.get(ViewKeyDict.channelfrom).toString());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.major))) {
			map.put(MapperDict.major, model.get(ViewKeyDict.major).toString());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.isrecommend))) {
			map.put(MapperDict.isrecommend, Boolean.parseBoolean(model.get(ViewKeyDict.isrecommend).toString()));
		}
		result.put(ViewKeyDict.total, getSO().getCount(map));
		List<Video> list = getSO().getList(map);
		for (Video video : list) {
			video.setBuyUserCount(getOrderGoodsSO().getBuyUserCount(video.getSn(), EGoods.video));
			String userNames = "";
			for(String useruid : getOrderGoodsSO().getBuyUserList(video.getSn(), EGoods.video)) {
				userNames += getUserSO().get(useruid).getShowname() + " ";
			}
			video.setUserNames(userNames);
		}
		result.put(ViewKeyDict.rows, list);
		return result;
	}

	/**
	 * 加载页面信息
	 * @return
	 * @author XieMing
	 * 2016-11-1 上午10:01:05
	 */
	public static Map<String, Object> loadMsg() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(ViewKeyDict.channel, EChannel.managervideo.name());
		map.put(ViewKeyDict.channelname, EChannel.managervideo.getName());
		map.put(ViewKeyDict.site, EChannel.manager.name());
		map.put(ViewKeyDict.totalvideo, getSO().getCount());
		map.put(ViewKeyDict.pkvideo, getSO().getCount(EVideoFrom.pk));
		map.put(ViewKeyDict.replyvideo, getSO().getCount(EVideoFrom.reply));
		map.put(ViewKeyDict.openvideo, getSO().getCount(EVideoFrom.open));
		map.put(ViewKeyDict.expertvideo, getSO().getCount(EVideoFrom.expert));
		map.put(ViewKeyDict.othervideo, getSO().getCount(EVideoFrom.other));
		map.put(ViewKeyDict.specialvideo, getSO().getCount(EVideoFrom.example));
		map.put(ViewKeyDict.majors, EMajor.getValueAndChinese());
		map.put(ViewKeyDict.channelfrom, EVideoFrom.getValueAndChinese());
		return map;
	}
}
