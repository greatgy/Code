package com.supergenius.server.tpi.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.genius.core.base.utils.DateUtil;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.xo.tpi.entity.Link;
import com.supergenius.xo.tpi.entity.Project;
import com.supergenius.xo.tpi.enums.EProjectChannel;
import com.supergenius.xo.tpi.enums.ECurrency;
import com.supergenius.xo.tpi.enums.EMoneyUnit;

/**
 * 项目HP
 * @author liushaomin
 */
public class BaseProjectHP extends BaseHP{
	
	/**
	 * 获取一些枚举的map类型
	 * @return Map<String, Map<String, String>> 其中外层map的key，表示是哪一类枚举，第二层Map的
	 *  key表示枚举的name值，value值表示枚举对应的中文表示
	 * @author ShangJianguo
	 */
	public static Map<String, Map<String, String>> getEnums() {
		Map<String, Map<String, String>> map = new HashMap<>();
		Map<String, String> itemMap = new LinkedHashMap<>();
		// 货币种类
		for (ECurrency e : ECurrency.values()) {
			itemMap.put(e.toString(), ECurrency.getName(e, Locale.CHINA));
		}
		map.put(ECurrency.class.getSimpleName().toLowerCase(), itemMap);
		itemMap = new LinkedHashMap<>();
		// 货币单位
		for (EMoneyUnit e : EMoneyUnit.values()) {
			itemMap.put(e.toString(), EMoneyUnit.getName(e, Locale.CHINA));
		}
		map.put(EMoneyUnit.class.getSimpleName().toLowerCase(), itemMap);
		return map;
	}
	
	/**
	 * 项目编号
	 * 并购推荐项目编码为15位字母加数字编码组合。
	 * 为了防止编码资源不足问题，前三位为预留编码，目前暂时统一用AAA表示。
	 * 第四位为推荐资格编码，G表示超天才网推荐，I表示机构推荐，P表示个人推荐。channel
	 * 第五位为推荐类型编码，A表示野心推荐，V表示投资价值推荐。C表示行业整合推荐，O表示海外推荐。 Warning 推荐类型是可以管理的，有可能会增加、减少、修改。暂时去掉
	 * 第五至第十二位为公历年月日数字编码。
	 * 第十三至第十五位为项目流水号数字编码。
	 * 第十六位也是最后一位为国别字母编码。
	 * @return sn
	 */
	public static String getProJSN(Project project) {
		String reservednum = "AAA";
		String channelnum = "";
		if (project.getChannel() == EProjectChannel.supergenius) {
			channelnum = "G";
		}else if (project.getChannel() == EProjectChannel.personal) {
			channelnum = "P";
		}else {
			channelnum = "I";
		}
		String prefix = DateUtil.getToday();
		int randomnum = (int)(Math.random() * 900 + 100);
		return reservednum + channelnum + prefix + randomnum;
	}
	
	/**
	 * 将String[]转化为Link[]
	 * @param articles
	 * @param project
	 * @return 
	 */
	public static List<Link> getArticles(String[] articles) {
		if (articles == null) {
			return null;
		}
		List<Link> Listarticles = new ArrayList<>();
		for (int i = 0; i < articles.length; i++) {
			String[] articlearray = articles[i].split("TPI#");
			Link article = new Link();
			article.setTitle(articlearray[0]);
			article.setHref(articlearray[1]);
			article.setOrigin(articlearray[2]);
			Listarticles.add(article);
		}
		return Listarticles;
	}

	
}
