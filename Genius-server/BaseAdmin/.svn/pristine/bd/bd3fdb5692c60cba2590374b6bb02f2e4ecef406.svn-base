package com.genius.server.baseadmin.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.genius.core.base.utils.DateUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.core.cache.rule.AutoIncrRule;
import com.genius.core.cache.rule.Rule;
import com.genius.core.cache.utils.RedisUtil;
import com.genius.model.baseadmin.enums.ESerialID;
import com.genius.server.base.helper.BaseHP;

/**
 * @author architect.bian
 *
 */
public class AutoIncrHP extends BaseHP {

	private static Logger log = LoggerFactory.getLogger(AutoIncrHP.class);
	private static String [] number  = {"1","2","3","6","8","9"};
	
	private static long getID(ESerialID type) {
		Rule rule = new AutoIncrRule(type.name());
		log.info("getID rule:" + rule.toString());
		return RedisUtil.incr(rule);
	}

	/**
	 * 重置为0，下次自增时返回1
	 * @param type
	 * @return
	 */
	private static boolean resetID(ESerialID type) {
		Rule rule = new AutoIncrRule(type.name());
		return RedisUtil.setIncr(rule, 0);
	}
	
	/**
	 * 工单号号生成规则
	 * 三个字符FLO-工单
	 * @return
	 */
	public synchronized static String getWorkordersn() {
		String prefix = DateUtil.NowTime().toString(DateUtil.FORMAT_DATE_SHORT);
		long id = getID(ESerialID.workordersn);
		if (id >= 9999) {
			resetID(ESerialID.workordersn);
		}
		int index = (int)(Math.random()*number.length);
		String rand = number[index];
		String workordersn = "WO" +prefix + StrUtil.format("0000", id) + rand;
		log.info("getFlowordersn:" + workordersn);
		return workordersn;
	}
	
}
