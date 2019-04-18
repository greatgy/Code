package com.supergenius.xo.___.init;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.supergenius.moral.moral.dao.CommentDao;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.moral.entity.Comment;
import com.supergenius.xo.moral.enums.EComment;

/**
 * 评论测试数据
 * 
 * @author liushaomin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class InitCommentDataTest {

	@Autowired
	CommentDao dao;

	@Ignore
	@Test
	public void InsertInitData() {
		for (int i = 0; i < 5; i++) {
			Comment comment = new Comment();
			comment.setFromuid("fromuid" + i);
			comment.setTouid("touid" + i);
			comment.setFromuseruid("fromuseruid" + i);
			comment.setFromuseroid(i);
			comment.setFromusername("fromusername" + i);
			comment.setTouseruid("touseruid" + i);
			comment.setTouseroid(i);
			comment.setTousername("tousername" + i);
			comment.setContent("content" + i);
			comment.setType(EComment.comment);
			comment.setChannel(EChannel.article);
			dao.insert(comment);
		}
	}
}
