package com.supergenius.web.front.life.helper;

import java.util.HashMap;
import java.util.Map;

import com.genius.server.base.helper.BaseHP;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.life.entity.Comments;
import com.supergenius.xo.life.enums.ELifeMsg;
import com.supergenius.xo.life.service.NewsSO;

/**
 * 消息HP
 * 
 * @author yangguang
 * @date 2018年5月16日10:47:38
 */
public class NewsHP extends BaseHP {

	private static NewsSO so;

	private static NewsSO getSO() {
		if (so == null) {
			so = (NewsSO) spring.getBean(NewsSO.class);
		}
		return so;
	}

	/**
	 * 评论/点赞之后发送消息
	 * 
	 * @param comment
	 * @return
	 * @author yangguang
	 */
	public static void sendMsg(Comments comment, String touseruid, String title, ELifeMsg eMsg, String href) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.title, title);
		map.put(MapperDict.touid, touseruid);
		map.put(MapperDict.content, comment.getContent());
		map.put(MapperDict.type, eMsg);
		map.put(MapperDict.href, href);
		map.put(MapperDict.fromuseruid, comment.getFromuseruid());
		getSO().add(map);
	}

}
