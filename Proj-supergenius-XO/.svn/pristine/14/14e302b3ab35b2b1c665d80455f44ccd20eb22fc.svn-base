package com.supergenius.xo.manager.service;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.core.base.utils.DateUtil;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.manager.entity.Message;
import com.supergenius.xo.manager.enums.EMsg;
import com.supergenius.xo.manager.enums.EMsgGroup;
import com.supergenius.xo.mock.testconstants.TestConst;
import com.supergenius.xo.user.service.UserSO;

/** 
 * 测试消息so是能成功
 * @author chenminchang
 * @date 2016-8-1 下午3:09:32 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
@Ignore
public class MessageSOTest {
	
	@Autowired
	MessageSO so;
	
	@Autowired
	UserSO userso;
	
	/**
	 * 测试messageSO的addMsg方法 
	 * @author chenminchang
	 */
	@Test
	@Ignore
	public void testAddMsg() {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.adminuid, TestConst.adminuid);
		map.put(MapperDict.fromuseruid, userso.getList().get(new Random().nextInt(120)).getUid());
		map.put(MapperDict.type, EMsg.financeScore); 
		map.put(MapperDict.typegroup, EMsgGroup.notify);
		map.put(MapperDict.content, "欢迎使用职业经理人培训!8t0 ");
		map.put(MapperDict.content, "你好呀，么么哒哈哈哈呵呵 223");
		map.put(MapperDict.score , 5);
		map.put(MapperDict.level , "高级专家");
		map.put(MapperDict.year, "2016"); 
		map.put(MapperDict.month, "10");
		map.put(MapperDict.count, "5");
		map.put(MapperDict.count2, "3");
		map.put(MapperDict.time, DateUtil.NowTime());
		map.put(MapperDict.href, "www.baidu.com");
		map.put(MapperDict.title, "改革开放三十多年以来，中国企业，尤其是民营企业迅速发展壮大，中国职业经理人队伍也随之成长起来，但中国职业经理人的良好从业规范却没有随之确立起来。超天才职业经理人培训学院致力于为中国企业培训德才识兼备的高绩效职业经理人，尤其是具有雄才大略可纵横天下的商界领袖，率先提出了“最纯洁职业经理人”的职场理念，并创立了相应的资格认证体系。");
		map.put(MapperDict.title, "市场营销管理RMBA答辩材料未通过");
		map.put(MapperDict.title, "我觉得这个项目不错，应该受到投资人的注意");
		map.put(MapperDict.title, "市场营销管理经理级别挑战赛");
		map.put(MapperDict.title, "点击查看详情");
		map.put(MapperDict.touseruid, TestConst.uid);
		Message m = so.addMsg(map);
		assertNotNull(m);
		System.out.println(m.getTitle());
	}

}
