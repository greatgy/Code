package com.supergenius.xo.sudokuapi.service;

import java.util.List;

import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.sudokuapi.entity.Messages;

/**
 * 站内消息SO
 * @author YuYingJie
 */
public interface MessagesSO extends BaseSO<Messages> {

	/**
	 * 根据to和title字段得到messages
	 * @param to
	 * @param title
	 * @return
	 * @CreateTime  2018年5月22日--下午2:59:23
	 * @Author  LiuBin
	 */
	List<Messages> getList(String to, String title);
}
