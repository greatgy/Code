package com.supergenius.xo.___.init;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.core.base.utils.GlobalUtil;
import com.supergenius.xo.tpi.dao.TypeDao;
import com.supergenius.xo.tpi.entity.Type;
import com.supergenius.xo.tpi.enums.EType;

/**
 * 添加团队相关的初始化数据
 * 
 * @author ShangJianguo
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class InitTypeDataTest {
	
	@Autowired
	TypeDao dao;
	
	@Ignore
	@Test
	public void insertInitData(){
		Type type = new Type();
		type.setType(EType.team);
		
		type.setName("专业并购");
		dao.insert(type);
		
		type.setUid(GlobalUtil.getUUID());
		type.setName("带资个人");
		dao.insert(type);
		
		type.setUid(GlobalUtil.getUUID());
		type.setName("智慧型野心");
		dao.insert(type);
		
		type.setUid(GlobalUtil.getUUID());
		type.setName("实干型野心");
		dao.insert(type);
	}
	
}
