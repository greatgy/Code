package com.supergenius.server.user.helper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;

import com.genius.core.base.utils.StrUtil;
import com.genius.core.serial.utils.SerialUtil;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.user.entity.User;

/**
 * 用户信息相关hp
 * @author YuYingJie
 */
public class BaseUserConfigHP extends BaseHP {
	
	/**
	 * 用户忽略消息
	 * @param oid
	 * @param filePath
	 * @param channel
	 * @return
	 * @author YuYingJie
	 */
	@SuppressWarnings("unchecked")
	public static boolean userIgnoreMsg(int oid, String filePath, String channel) {
		filePath = String.format(filePath, oid);
		Map<String, Object> userIgnoreMsg = getUserIgnoreMsg(filePath);
		if (userIgnoreMsg != null && userIgnoreMsg.containsKey(ViewKeyDict.msg)) {
			Map<String, Object> ignoreMsg = (Map<String, Object>) userIgnoreMsg.get(ViewKeyDict.msg);
			if (ignoreMsg.containsKey(channel)) {
				Map<String, Object> ignoreChannel = (Map<String, Object>) ignoreMsg.get(channel);
				ignoreChannel.put(ViewKeyDict.ignoremsgtime, DateTime.now());
				return SerialUtil.serializeToJson(userIgnoreMsg, filePath);
			}
		}
		return false;
	}
	
	/**
	 * 获取新消息数量(不进行序列化)
	 * @param oid
	 * @param filePath
	 * @param channel
	 * @return
	 * @author YuYingJie
	 */
	@SuppressWarnings("unchecked")
	public static int getUserNewMsgCount(int oid, String filePath, String channel) {
		int count = 0;
		filePath = String.format(filePath, oid);
		Map<String, Object> userIgnoreMsg = getUserIgnoreMsg(String.format(filePath, oid));
		if (userIgnoreMsg != null && userIgnoreMsg.containsKey(ViewKeyDict.msg)) {
			Map<String, Object> ignoreMsg = (Map<String, Object>) userIgnoreMsg.get(ViewKeyDict.msg);
			if (ignoreMsg.containsKey(channel)) {
				Map<String, Object> ignoreChannel = (Map<String, Object>) ignoreMsg.get(channel);
				DateTime newsendmsgtime = new DateTime(ignoreChannel.get(ViewKeyDict.newsendmsgtime));
				DateTime ignoremsgtime = new DateTime(ignoreChannel.get(ViewKeyDict.ignoremsgtime));
				if (newsendmsgtime.compareTo(ignoremsgtime) >= 0) {
					for (String msgtype : ignoreChannel.keySet()) {
						if (msgtype.endsWith("msgcount")) {
							count += (int) ignoreChannel.get(msgtype);
						}
					}
				}
			} 
		}
		return count;
	}

	/**
	 * 减少用户新消息的数量
	 * @param oid
	 * @param filePath
	 * @param type 消息类型
	 * @param channel 频道或者站点
	 * @return
	 * @author YuYingJie
	 */
	public static boolean addUserIgnoreMsgCount(int oid, String filePath, String type, String channel) {
		return addUserIgnoreMsgCount(oid, filePath, type, channel, null);
	}
	
	/**
	 * 减少用户新消息的数量 (默认设置为0)
	 * @param oid
	 * @param filePath
	 * @param type 消息类型
	 * @param channel 频道或者站点
	 * @param time 更新时间
	 * @return
	 * @author YuYingJie
	 */
	public static boolean addUserIgnoreMsgCount(int oid, String filePath, String type, String channel, DateTime time) {
		return addUserIgnoreMsgCount(oid, filePath, type, channel, time, true);
	}
	
	/**
	 * 减少用户新消息的数量
	 * @param oid
	 * @param filePath
	 * @param type 消息类型
	 * @param channel 频道或者站点
	 * @param time 更新时间
	 * @param isClearCount 是否将对应的数量设置为0
	 * @return
	 * @author YuYingJie
	 */
	@SuppressWarnings("unchecked")
	public static boolean addUserIgnoreMsgCount(int oid, String filePath, String type, String channel, DateTime time, boolean isClearCount) {
		filePath = String.format(filePath, oid);
		Map<String, Object> userIgnoreMsg = getUserIgnoreMsg(filePath);
		if (userIgnoreMsg != null && userIgnoreMsg.containsKey(ViewKeyDict.msg)) {
			Map<String, Object> ignoreMsg = (Map<String, Object>) userIgnoreMsg.get(ViewKeyDict.msg);
			if (ignoreMsg.containsKey(channel)) {
				Map<String, Object> ignoreChannel = (Map<String, Object>) ignoreMsg.get(channel);
				if (isClearCount) {
					ignoreChannel.put(type, 0);
				} else {
					if (ignoreChannel.containsKey(type)) {
						int count = (int) ignoreChannel.get(type);
						count = (count - 1) > 0 ? (count - 1) : 0 ;
						ignoreChannel.put(type, count);
					} else {
						ignoreChannel.put(type, 0);
					}
				}
				if (time != null) {
					ignoreChannel.put(ViewKeyDict.updatetime, time);
				}
			} else {
				Map<String, Object> ignoreChannel = new HashMap<>();
				ignoreChannel.put(ViewKeyDict.newsendmsgtime, DateTime.now());
				ignoreChannel.put(ViewKeyDict.ignoremsgtime, DateTime.now());
				ignoreChannel.put(type, 0);
				if (time != null) {
					ignoreChannel.put(ViewKeyDict.updatetime, time);
				}
				ignoreMsg.put(channel, ignoreChannel);
			}
		} else {
			userIgnoreMsg = new HashMap<>();
			Map<String, Object> ignoreMsg = new HashMap<>();
			Map<String, Object> ignoreChannel = new HashMap<>();
			ignoreChannel.put(ViewKeyDict.newsendmsgtime, DateTime.now());
			ignoreChannel.put(ViewKeyDict.ignoremsgtime, DateTime.now());
			if (time != null) {
				ignoreChannel.put(ViewKeyDict.updatetime, time);
			}
			ignoreChannel.put(type, 0);
			ignoreMsg.put(channel, ignoreChannel);
			userIgnoreMsg.put(ViewKeyDict.msg, ignoreMsg);
		}
		return SerialUtil.serializeToJson(userIgnoreMsg, filePath);
	}
	
	/**
	 * 更新用户的忽略消息文件中新消息数量
	 * @param oid 被更新人oid
	 * @param filePath
	 * @param type 消息类型
	 * @param channel 哪个站点
	 * @author YuYingJie
	 */
	public static boolean updateUserIgnoreMsgFile(int oid, String filePath, String type, String channel) {
		return updateSerialUserIgnoreMsgFile(oid, filePath, type, channel);
	}
	
	/**
	 * 更新用户的忽略消息文件中新消息数量
	 * @param oids
	 * @param filePath 文件路径
	 * @param type 消息类型
	 * @param channel 站点
	 * @author YuYingJie
	 */
	public static boolean updateUserIgnoreMsgFile(List<Integer> oids, String filePath, String type, String channel) {
		boolean bool = false;
		for (Integer integer : oids) {
			bool = updateSerialUserIgnoreMsgFile(integer, filePath, type, channel);
		}
		return bool;
	}
	
	/**
	 * 更新用户的忽略消息文件中新消息数量(+1)
	 * @param oid
	 * @param filePath
	 * @param type
	 * @param channel
	 * @return
	 * @author YuYingJie
	 */
	private static boolean updateSerialUserIgnoreMsgFile(int oid, String filePath, String type, String channel) {
		return updateSerialUserIgnoreMsgFile(oid, filePath, type, channel, null, 1);
	}
	
	/**
	 * 更新用户的忽略消息文件中新消息数量
	 * @param oid
	 * @param filePath
	 * @param type 消息类型
	 * @param channel
	 * @author YuYingJie
	 */
	@SuppressWarnings("unchecked")
	public static boolean updateSerialUserIgnoreMsgFile(int oid, String filePath, String type, String channel, DateTime time, int count) {
		filePath = String.format(filePath, oid);
		Map<String, Object> userIgnoreMsg = getUserIgnoreMsg(filePath);
		if (userIgnoreMsg != null && userIgnoreMsg.containsKey(ViewKeyDict.msg)) {
			Map<String, Object> ignoreMsg = (Map<String, Object>) userIgnoreMsg.get(ViewKeyDict.msg);
			if (ignoreMsg.containsKey(channel)) {
				Map<String, Object> ignoreChannel = (Map<String, Object>) ignoreMsg.get(channel);
				if (ignoreChannel.containsKey(type)) {
					int newMsgCount = (int) ignoreChannel.get(type);
					ignoreChannel.put(type, newMsgCount + count);
				} else {
					ignoreChannel.put(type, count);
				}
				ignoreChannel.put(ViewKeyDict.newsendmsgtime, DateTime.now());
				if (time != null) {
					ignoreChannel.put(ViewKeyDict.updatetime, time);
				}
			} else {
				Map<String, Object> ignoreChannel = new HashMap<>();
				ignoreChannel.put(ViewKeyDict.ignoremsgtime, DateTime.now());
				ignoreChannel.put(ViewKeyDict.newsendmsgtime, DateTime.now());
				ignoreChannel.put(type, 1);
				if (time != null) {
					ignoreChannel.put(ViewKeyDict.updatetime, time);
				}
				ignoreMsg.put(channel, ignoreChannel);
			}
		} else {
			userIgnoreMsg = new HashMap<>();
			Map<String, Object> ignoreMsg = new HashMap<>();
			Map<String, Object> ignoreChannel = new HashMap<>();
			ignoreChannel.put(ViewKeyDict.ignoremsgtime, DateTime.now());
			ignoreChannel.put(ViewKeyDict.newsendmsgtime, DateTime.now());
			ignoreChannel.put(type, 1);
			if (time != null) {
				ignoreChannel.put(ViewKeyDict.updatetime, time);
			}
			ignoreMsg.put(channel, ignoreChannel);
			userIgnoreMsg.put(ViewKeyDict.msg, ignoreMsg);
		}
		return SerialUtil.serializeToJson(userIgnoreMsg, filePath);
	}
	
	/**
	 * 获取用户忽略的消息
	 * @param filepath
	 * @return
	 * @author YuYingJie
	 */
	public static Map<String, Object> getUserIgnoreMsg(String filepath) {
		return getUserSerialFileToMap(filepath);
	}
	
	/**
	 * 将用户相关的文件反序列化为map
	 * @param filepath
	 * @return
	 * @author YuYingJie
	 */
	@SuppressWarnings("unchecked")
	private static Map<String, Object> getUserSerialFileToMap(String filepath) {
		if (StrUtil.isEmpty(filepath)) {
			return null;
		}
		File file = new File(filepath);
		if (!file.exists()) {
			file.getParentFile().mkdirs();
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		return SerialUtil.deserializeFromJson(filepath, Map.class);
	}
	
	/**
	 * 获取用户上次取群发消息的时间
	 * @param user
	 * @param filepath
	 * @param echannel
	 * @return
	 * @author YuYingJie
	 */
	public static DateTime getUpdateTime(User user, String filepath, EChannel echannel) {
		return getUpdateTime(user.getOid(), filepath, echannel);
	}
	
	/**
	 * 获取用户上次取群发消息的时间
	 * @param user
	 * @author YuYingJie
	 */
	@SuppressWarnings("unchecked")
	public static DateTime getUpdateTime(int oid, String filepath, EChannel echannel) {
		if (StrUtil.isEmpty(filepath)) {
			return null;
		}
	 	Map<String, Object> userIgnoreMsg = getUserSerialFileToMap(String.format(filepath, oid));
		if (userIgnoreMsg != null) {
			Map<String, Object> ignoreMsg = (Map<String, Object>) userIgnoreMsg.get(ViewKeyDict.msg);
			String channel = "";
			if (StrUtil.isEmpty(echannel)) {
				channel = EChannel.tpi.name();
			} else {
				channel = echannel.name();
			}
			if (ignoreMsg != null) {
				Map<String, Object> ignoreChannel = (Map<String, Object>) ignoreMsg.get(channel);
				if (ignoreChannel != null && ignoreChannel.containsKey(ViewKeyDict.updatetime)) {
					if (ignoreChannel.containsKey(ViewKeyDict.updatetime)) {
						return new DateTime(ignoreChannel.get(ViewKeyDict.updatetime));
					}
				}
			}
		}
		return null;
	}

	/**
	 * 设置用户上次取群发消息的时间,更新新消息数量
	 * @param user
	 * @param count
	 * @param updatetime
	 * @param filepath
	 * @param echannel (默认tpi)
	 * @return
	 * @author YuYingJie
	 */
	public static boolean setUpdateTime(User user, int count, DateTime updatetime, String filepath, EChannel echannel) {
		return setUpdateTime(user.getOid(), count, updatetime, filepath, echannel);
	}
	
	/**
	 * 设置用户上次取群发消息的时间,更新新消息数量
	 * @param oid
	 * @param count
	 * @param updatetime
	 * @param filepath
	 * @param echannel
	 * @return
	 * @author YuYingJie
	 */
	public static boolean setUpdateTime(int oid, int count, DateTime updatetime, String filepath, EChannel echannel) {
		if (count <= 0) {
			return false;
		}
		String channel = "";
		if (StrUtil.isEmpty(echannel)) {
			channel = EChannel.tpi.name();
		} else {
			channel = echannel.name();
		}
		return updateSerialUserIgnoreMsgFile(oid, filepath, ViewKeyDict.systemmsgcount, channel, updatetime, count);
		
	}
	
	/**
	 * 获取用户上次取群发消息的时间
	 * @param user
	 * @author YuYingJie
	 */
	public static DateTime getLastestUpdateTimeOrLogintime(User user, DateTime updatetime) {
		return getLastestUpdateTimeOrLogintime(user.getLastlogintime(), user.getCreatetime(), updatetime);
	}

	/**
	 * 获取用户上次取群发消息的时间
	 * @param lastlogintime
	 * @param createtime
	 * @param updatetime
	 * @return
	 * @author YuYingJie
	 */
	public static DateTime getLastestUpdateTimeOrLogintime(DateTime lastlogintime, DateTime createtime, DateTime updatetime) {
		if (updatetime != null) {
			return updatetime;
		} else if (lastlogintime != null) {
			return lastlogintime;
		} else {
			return createtime;
		}
	}

}
