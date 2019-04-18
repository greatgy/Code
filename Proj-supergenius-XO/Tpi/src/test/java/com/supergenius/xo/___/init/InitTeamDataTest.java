package com.supergenius.xo.___.init;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.core.base.utils.GlobalUtil;
import com.supergenius.xo.tpi.constants.MapperDict;
import com.supergenius.xo.tpi.dao.TeamDao;
import com.supergenius.xo.tpi.dao.TypeDao;
import com.supergenius.xo.tpi.entity.ContactInfo;
import com.supergenius.xo.tpi.entity.Team;
import com.supergenius.xo.tpi.entity.Type;
import com.supergenius.xo.tpi.enums.ETeamStage;
import com.supergenius.xo.tpi.enums.EType;

/**
 * 添加团队相关的初始化数据
 * 
 * @author ShangJianguo
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class InitTeamDataTest {
	
	@Autowired
	TeamDao dao;
	@Autowired
	TypeDao typeDao;
	
	@Ignore
	@Test
	public void insertInitData(){
		Team team = new Team();
		team.setSn("teamsn");
		team.setName("团队测试名字1");
		team.setUseruid(GlobalUtil.getUUID());
		team.setUsername("张三");
		team.setTeamlevel(3);
		team.setSummary("这是一个有着神一样的对手，猪一样的队友的团队。所以，超神，没得说~");
		team.setDeclaration("打破诅咒！");
		List<String> memberuids = new ArrayList<>();
		memberuids.add("a46b63d6e7d247069497168496808eeb");
		team.setMemberuids(memberuids);
		ContactInfo ci = new ContactInfo();
		ci.setEmail("test@supergenius.cn");
		ci.setTel("010-8888888");
		ci.setProvince("China");
		ci.setCity("Beijing");
		team.setContactinfo(ci);
		
		team.setIstop(true);
		team.setIsrecommend(true);		
		team.setStage(ETeamStage.success);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.type, EType.team);
		List<Type> types = typeDao.getList(map);
		for (Type type : types) {
			team.setUid(GlobalUtil.getUUID());
			team.setTypeuid(type.getUid());
			dao.insert(team);
		}
		
	}
	
}
