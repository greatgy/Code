package com.supergenius.web.account.helper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.junit.Ignore;
import org.junit.Test;

import com.genius.core.base.utils.FreemarkerUtil;
import com.genius.core.base.utils.GlobalUtil;
import com.genius.core.base.utils.HttpUtil;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.xo.account.entity.Account;
import com.supergenius.xo.account.enums.EBank;
import com.supergenius.xo.account.enums.EState;
import com.supergenius.xo.common.enums.ESite;

/**
 *  
 * @author liushaomin
 */
public class RechargeHPTest {
	
	/**
	 * Test method for {@link com.supergenius.web.account.helper.RechargeHP#getSiteNotifyUrl(com.supergenius.xo.account.entity.Account)}.
	 */
	@Test
	public void testGetSiteNotifyUrl() {
		String url = "www.baidu.com";
		Map<String, String> map = new HashMap<String, String>();
		String result = HttpUtil.get(url, map);
		System.out.println(result);
	}
	
	/**
	 * 测试发送邮件
	 * @author liushaomin
	 */
	@Test
	@Ignore
	public void testSendEmail() {
		try {
			Map<String, Object> model = new HashMap<String, Object>();
			Account account = new Account();
			account.setUid(GlobalUtil.getUUID());
			account.setAccountsn("accountsn");
			account.setUseremail("jasonhung_shao@163.com");
			account.setPayuid(GlobalUtil.getUUID());
			account.setUseruid(GlobalUtil.getUUID());
			account.setUsername("name");
			account.setUserip("userip");
			account.setResulturl("resulturl");
			account.setNotifyurl("notifyurl");
			account.setSite(ESite.supergenius);
			account.setMoney(0.1);
			account.setBank(EBank.abchina);
			account.setState(EState.init);
			account.setSuccesstime(DateTime.now());
			account.setFailedtime(DateTime.now());
			account.setDesc("desc");
			account.setUpdatetime(DateTime.now());
			model.put(ViewKeyDict.bean, account);
			model.put(ViewKeyDict.type, ViewKeyDict.title);
			String emailTitle = FreemarkerUtil.process("E:/MyWork2/Proj-supergenius-account/Web/src/main/webapp/WEB-INF/Data/Templ", ViewKeyDict.emailconfirm + ViewKeyDict.tempsuffix, model);
			System.out.println(emailTitle);
			model.put(ViewKeyDict.type, ViewKeyDict.content);
			String content = FreemarkerUtil.process("E:/MyWork2/Proj-supergenius-account/Web/src/main/webapp/WEB-INF/Data/Templ", ViewKeyDict.emailconfirm + ViewKeyDict.tempsuffix, model);
			System.out.println(content);
//			for (String emailAddress : SysConf.ManagerEmailsSuccess) {
//				EmailUtil.send("jasonhung_shao@163.com", "ss", "sssssssssss");
//			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
