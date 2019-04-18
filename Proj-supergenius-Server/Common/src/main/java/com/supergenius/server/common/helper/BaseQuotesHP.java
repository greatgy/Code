package com.supergenius.server.common.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.utils.FileUtil;
import com.genius.core.serial.utils.SerialUtil;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.xo.common.entity.Quotes;
import com.supergenius.xo.common.enums.EQuotes;

/**
 * 名人名言的基类(HP),前后台HP都继承这个HP
 * 
 * @author LiJiacheng
 */
public class BaseQuotesHP extends BaseHP {

	/**
	 * 随机获取一条名人名言
	 * 
	 * @param path
	 * @return
	 * @author LiJiacheng
	 */
	public static Quotes getRandomOne(String path) {
		List<Quotes> quotes = getSerialize(path);
		Random random = new Random();
		if (quotes != null && quotes.size() != 0) {
			return quotes.get(random.nextInt(quotes.size()));
		}
		return null;
	}
	
	/**
	 * 根据type随机获取一条名人名言
	 * @param path
	 * @param type
	 * @return
	 * @author chenminchang
	 */
	public static Quotes getRandomOne(String path, EQuotes type) {
		List<Quotes> quotes = getSerialize(path, type);
		Random random = new Random();
		if (quotes != null && quotes.size() != 0) {
			return quotes.get(random.nextInt(quotes.size()));
		}
		return null;
	}

	/**
	 * 获取某个类型中最新的一条
	 * @param path
	 * @param type
	 * @return
	 * @author chenminchang
	 */
	public static Quotes getOneByUpdateTime(String path, EQuotes type) {
		List<Quotes> quotes = getSerialize(path, type);
		if (quotes != null && quotes.size() != 0) {
			return quotes.get(0);
		}
		return null;
	}
	
	/**
	 * 根据type从序列化文件中，获取所有的名人名言(列表)
	 * @param path
	 * @param type
	 * @return
	 * @author chenminchang
	 */
	@SuppressWarnings("unchecked")
	public static List<Quotes> getSerialize(String path, EQuotes type) {
		List<Quotes> list = SerialUtil.deserializeFromJson(path, List.class, Json.cacheStrategy);
		if (list != null && list.size() != 0) {
			List<Quotes> list2 = new ArrayList<>();
			for (Quotes quotes : list) {
				if (quotes.getType().equals(type))
					list2.add(quotes);
			}
			return list2;
		}
		return null;
	}
	
	/**
	 * 从序列化文件中，获取所有的名人名言(列表)
	 * 
	 * @return
	 * @author LiJiacheng
	 */
	@SuppressWarnings("unchecked")
	public static List<Quotes> getSerialize(String path) {
		return SerialUtil.deserializeFromJson(path, List.class, Json.cacheStrategy);
	}

	/**
	 * 重新序列化文件
	 * 
	 * @param quotes
	 * @author LiJiacheng
	 */
	public static void serializeToJson(List<Quotes> quotes, String path) {
		deleteFile(path);
		Collections.sort(quotes);
		SerialUtil.serializeToJson( quotes, path, Json.cacheStrategy);
	}

	/**
	 * 删除以前的序列化文件
	 * 
	 * @param path
	 * @author LiJiacheng
	 */
	protected static void deleteFile(String path) {
		FileUtil.delete(path);
	}

}
