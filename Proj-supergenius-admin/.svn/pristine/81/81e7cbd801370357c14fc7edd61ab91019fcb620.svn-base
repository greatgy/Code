package com.supergenius.web.admin.admin.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;

import com.genius.core.base.utils.GlobalUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.supergenius.global.conf.SysConf;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.server.common.helper.BaseQuotesHP;
import com.supergenius.xo.common.entity.Quotes;
import com.supergenius.xo.common.enums.EQuotes;

/**
 * 名人名言HP
 * 
 * @author LiJiacheng
 */
public class QuotesHP extends BaseQuotesHP {

	/**
	 * 组织数据，从序列化文件中获取列表
	 * 
	 * @param model
	 * @return
	 * @author LiJiacheng
	 */
	public static Map<String, Object> query(Map<?, ?> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		String path = SysConf.SerialBasePath + SysConf.QuotesPath + SysConf.Separator_Directory + SysConf.Quotes;
		Map<String, Object> result = new HashMap<String, Object>();
		List<Quotes> quotes;
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.type))) {
			quotes = getSerialize(path, EQuotes.get((String)model.get(ViewKeyDict.type)));
		} else {
			quotes = getSerialize(path);
		}
		if (quotes != null) {
			result.put(ViewKeyDict.rows, getListByPager(pager, quotes));
			result.put(ViewKeyDict.total, quotes.size());
			return result;
		}
		result.put(ViewKeyDict.rows, null);
		result.put(ViewKeyDict.total, 0);
		return result;
	}

	/**
	 * 添加名人名言
	 * 
	 * @param path
	 * @return
	 * @author LiJiacheng
	 */
	public static void add(String path, Quotes quotes) {
		List<Quotes> quotesList = getSerialize(path);
		if (quotesList != null && quotesList.size() > 0) {
			quotes.setOid(quotesList.get(quotesList.size() - 1).getOid() + 1);
		} else {
			quotes.setOid(1);
			quotesList = new ArrayList<>();
		}
		quotes.setUid(GlobalUtil.getUUID());
		quotes.setStatus(EStatus.enable);
		quotes.setCreatetime(DateTime.now());
		quotes.setUpdatetime(DateTime.now());
		quotesList.add(quotes);
		BaseQuotesHP.serializeToJson(quotesList, path);
	}

	/**
	 * 编辑名人名言
	 * 
	 * @param path
	 * @param quotes
	 * @param Site
	 * @author LiJiacheng
	 */
	public static void edit(String path, Quotes newQuotes, int Site) {
		List<Quotes> quotesList = getSerialize(path);
		Quotes oldQuotes = quotesList.get(Site);
		oldQuotes.setAuthor(newQuotes.getAuthor());
		oldQuotes.setContent(newQuotes.getContent());
		oldQuotes.setType(newQuotes.getType());
		oldQuotes.setUpdatetime(DateTime.now());
		quotesList.set(Site, oldQuotes);
		BaseQuotesHP.serializeToJson(quotesList, path);
	}

	/**
	 * 删除名人名言
	 * 
	 * @param path
	 * @param Site
	 * @author LiJiacheng
	 */
	public static void delete(String path, int Site) {
		List<Quotes> quotesList = getSerialize(path);
		quotesList.remove(Site);
		BaseQuotesHP.serializeToJson(quotesList, path);
	}

	/**
	 * 根据Pager类，获取相应部分的列表(翻页)
	 * 
	 * @param pager
	 * @param quotes
	 * @return
	 * @author LiJiacheng
	 */
	private static List<Quotes> getListByPager(Pager pager, List<Quotes> quotes) {
		List<Quotes> subList = null;
		int from = pager.getStartIndex();
		int to = pager.getStartIndex() + pager.getPageSize();
		if (quotes.size() < to) {
			subList = quotes.subList(from, quotes.size());
		} else {
			subList = quotes.subList(from, to);
		}
		for (Quotes quotes2 : subList) {
			quotes2.setSite(from);
			from++;
		}
		return subList;
	}

}
