package com.supergenius.xo.___.init;

import java.util.HashMap;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.core.base.utils.GlobalUtil;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.tpi.dao.TeamDao;
import com.supergenius.xo.tpi.dao.WishDao;
import com.supergenius.xo.tpi.entity.Team;
import com.supergenius.xo.tpi.entity.Wish;
import com.supergenius.xo.tpi.enums.EWishFromType;
import com.supergenius.xo.tpi.enums.EWishType;

/**
 * 添加团队相关的初始化数据
 * 
 * @author ShangJianguo
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class InitWishDataTest {
	
	@Autowired
	WishDao dao;
	@Autowired
	TeamDao teamDao;
	
	@Ignore
	@Test
	public void insertInitData(){
		List<Team> teams = teamDao.getList(new HashMap<String, Object>());
		Wish wish = new Wish();
		wish.setFromtype(EWishFromType.merger);
		wish.setTitle("目标题目");
		wish.setContent("超高级，超满意，超合理，的并购方案");
		wish.setType(EWishType.wantMergers);
		wish.setChannel(EChannel.project);
		for (Team team : teams) {
			wish.setUid(GlobalUtil.getUUID());
			wish.setFromuid(team.getUid());
			wish.setFromname(team.getName());
			wish.setTouid(GlobalUtil.getUUID());
			dao.insert(wish);
		}
	}
	
}
