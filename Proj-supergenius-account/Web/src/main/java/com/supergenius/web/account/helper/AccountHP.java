package com.supergenius.web.account.helper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.genius.core.base.utils.DateUtil;
import com.genius.core.base.utils.EmailUtil;
import com.genius.core.base.utils.FreemarkerUtil;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.global.conf.SysConf;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.xo.account.entity.Account;
import com.supergenius.xo.account.entity.AccountLog;
import com.supergenius.xo.account.entity.Refund;
import com.supergenius.xo.account.enums.EState;
import com.supergenius.xo.account.service.AccountLogSO;
import com.supergenius.xo.account.service.AccountSO;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.ESite;

/**
 * accountHP
 * @author liushaomin
 */
public class AccountHP extends BaseHP{
	
	private static Logger log = LoggerFactory.getLogger(AccountHP.class);

	private static AccountSO so;
	
	private static AccountLogSO accountLogSO;

	private static AccountSO getSO() {
		if (so == null) {
			so = (AccountSO) spring.getBean(AccountSO.class);
		}
		return so;
	}
	
	private static AccountLogSO getAccountLogSO() {
		if (accountLogSO == null) {
			accountLogSO = (AccountLogSO) spring.getBean(AccountLogSO.class);
		}
		return accountLogSO;
	}
	
	/**
	 * 生成accountsn
	 * @return
	 * @author liushaomin
	 */
	public static String getAccountsn(String site) {
		String prefix ;
		if (ESite.sudoku.toString().equals(site)) {
			prefix = "S";
		}else if (ESite.tpi.toString().equals(site)) {
			prefix = "T";
		}else {
			prefix = "C";
		}
		return prefix + DateUtil.getNowForID();
	}

	/**
	 * 更新account状态，记录accountlog
	 * @param account
	 * @param pay
	 * @author liushaomin
	 */
	public static boolean updateAccountState(Account account, EState state) {
		log.info("begin to start updateAccountState ...");
		PlatformTransactionManager tm = (PlatformTransactionManager) spring.getBean("transactionManagerAccountDB");
		DefaultTransactionDefinition def = new DefaultTransactionDefinition(TransactionDefinition.ISOLATION_REPEATABLE_READ);
		def.setIsolationLevel(TransactionDefinition.ISOLATION_SERIALIZABLE);
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = (TransactionStatus) tm.getTransaction(def);
		log.info("begin to start transactionManagerAccountDB ...");
		try {
			account = getSO().get(account.getUid());
			log.debug(String.format("begin to invoke updateAccountState(account.username:%s, account.state:%s, state:%s)", account.getUsername(), account.getState(), state));
			EState accountstate = account.getState();
			if (accountstate == state) {// 状态一样不需修改
				log.info("accountstate no need update, return true.");
				return true;
			} else if (accountstate == EState.success) {// 状态已为成功，不需修改
				log.info("account.getState() == EState.success, accountstate no need update, return true.");
				return true;
			}
			Map<String, Object> map = new HashMap<>();
			map.put(MapperDict.uid, account.getUid());
			map.put(MapperDict.state, state);
			if (state == EState.success) {//需要更新成功时间
				map.put(MapperDict.successtime, DateUtil.NowTime());
			}else if (state == EState.failed) {//需要更新失败时间
				map.put(MapperDict.failedtime, DateUtil.NowTime());
			}
			if (getSO().updateFields(map)) {
				Account account2 = so.get(account.getUid());
				if (account2.getState() == EState.success) {//充值成功给管理员发送邮件,失败给另外一个管理员发
					sendRechargeEmail(account2, SysConf.ManagerEmailsSuccess);
				}else if(account2.getState() == EState.failed || account2.getState() == EState.notifyex){
					sendRechargeEmail(account2, SysConf.ManagerEmailsFailed);
				}
				log.info(String.format("update account state to %s where account.uid:%s", account2.getStateName(), account2.getUid()));
				AccountLog accountlog = new AccountLog(account2);
				accountlog.setAccountstatefrom(accountstate);
				getAccountLogSO().add(accountlog);
				log.info("add accountlog success");
				return true;
			} else {
				log.info("recharge update failed");
			}
		} catch (Exception e) {
			// 回滚事务
			tm.rollback(status);
			log.info("finished rollback transaction");
			e.printStackTrace();
		} finally {
			if (!status.isCompleted()) {
				tm.commit(status);
				log.info("finished commit transaction");
			}
		}
		return false;
	}

	/**
	 * 给管理员发送邮件
	 * @param account
	 * @param emails
	 * @author liushaomin
	 */
	private static void sendRechargeEmail(Account account, String [] emails) {
		log.info("begin to invoke sendRechargeEmail");
		try {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put(ViewKeyDict.bean, account);
			model.put(ViewKeyDict.type, ViewKeyDict.title);
			String emailTitle = FreemarkerUtil.process(SysConf.EmailTemplatePath, ViewKeyDict.emailconfirm +  ViewKeyDict.tempsuffix, model);
			model.put(ViewKeyDict.type, ViewKeyDict.content);
			String content = FreemarkerUtil.process(SysConf.EmailTemplatePath, ViewKeyDict.emailconfirm + ViewKeyDict.tempsuffix, model);
			for (String emailAddress : emails) {
				log.info(String.format("send email success, email:%s", emailAddress));
				EmailUtil.send(emailAddress, emailTitle, content);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 给管理员发送退款邮件
	 * @param account
	 * @param emails
	 * @author YangGuang
	 */
	public static void sendRefundEmail(Refund refund, String [] emails) {
		log.info("begin to invoke sendRechargeEmail");
		try {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put(ViewKeyDict.bean, refund);
			model.put(ViewKeyDict.type, ViewKeyDict.title);
			String emailTitle = FreemarkerUtil.process(SysConf.EmailTemplatePath, SysConf.emailrefund +  ViewKeyDict.tempsuffix, model);
			model.put(ViewKeyDict.type, ViewKeyDict.content);
			String content = FreemarkerUtil.process(SysConf.EmailTemplatePath, SysConf.emailrefund + ViewKeyDict.tempsuffix, model);
			for (String emailAddress : emails) {
				log.info(String.format("send email success, email:%s", emailAddress));
				EmailUtil.send(emailAddress, emailTitle, content);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将apiuid,payuid,banktype,money拼接，然后md5加密
	 * @param account
	 * @return
	 * @author liushaomin
	 */
	public static String getPwdmd5(Account account) {
		 StringBuffer strb = new StringBuffer();
		 if (account.getSite().equals(ESite.sudoku)) {
			 strb.append(SysConf.SiteSudokuApiuid);
		 } else if (account.getSite().equals(ESite.supergenius)) {
			 strb.append(SysConf.SiteSuperGeniusApiuid);
		 } else if (account.getSite().equals(ESite.user)) {
			 strb.append(SysConf.SiteUserApiuid);
		 } else if (account.getSite().equals(ESite.moral)) {
			 strb.append(SysConf.SiteMoralApiuid);
		 } else if (account.getSite().equals(ESite.manager)) {
			 strb.append(SysConf.SiteManagerApiuid);
		 } else if (account.getSite().equals(ESite.sns)) {
			 strb.append(SysConf.SiteSnsApiuid);
		 } else if (account.getSite().equals(ESite.enterpriser)) {
			 strb.append(SysConf.SiteEnterpriserApiuid);
		 } else if (account.getSite().equals(ESite.tpi)) {
			 strb.append(SysConf.SiteTpiApiuid);
		 } else if (account.getSite().equals(ESite.demopay)) {
			 strb.append(SysConf.DemoPayApiuid);
		 } else {
			 //TODO 有新的站点使用account接口时需要改这里的代码
		 }
		 strb.append(account.getPayuid()).append(account.getBank()).append(account.getMoney()).append(account.getUseruid());
		 return DigestUtils.md5Hex(strb.toString());
	}

}
