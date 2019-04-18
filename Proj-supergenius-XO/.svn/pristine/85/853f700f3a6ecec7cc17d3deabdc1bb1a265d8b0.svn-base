package com.supergenius.xo.manager.service;

import java.util.Map;

import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.manager.entity.Message;

/**
 * 消息SO
 * @author XieMing
 * @date 2016-7-18 下午2:28:06
 */
public interface MessageSO extends BaseSO<Message> {

	/**
	 * 增加消息,当有touseruid时自动发送到inbox中，因消息type不同，map中需要存放的字段需要在消息模板中查看
	 * 消息模板路径：E:\MyWork2\Proj-supergenius-data\data\manager\tmpl\message
	 * @param map
	 * @return
	 * @author chenminchang
	 */
	Message addMsg(Map<String, Object> map);
}
