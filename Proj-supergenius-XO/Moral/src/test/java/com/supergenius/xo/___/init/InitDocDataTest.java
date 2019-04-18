package com.supergenius.xo.___.init;

import org.joda.time.DateTime;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.model.base.enums.EStatus;
import com.supergenius.moral.moral.dao.DocDao;
import com.supergenius.xo.moral.entity.Doc;
import com.supergenius.xo.moral.enums.EChapter;

/**
 * 添加文档的初始化数据
 * 
 * @author LiJiacheng
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class InitDocDataTest {

	@Autowired
	DocDao dao;

	@Ignore
	@Test
	public void insertInitData() {
		for (int i = 1; i <= 6; i++) {
			Doc doc = new Doc();
			doc.setCreatetime(DateTime.now());
			doc.setCountdl(36 + i);
			doc.setName("name" + i);
			doc.setDocintro("Docintro" + i);
			doc.setSn(String.valueOf(i));
			doc.setStatus(EStatus.enable);
			doc.setChapter(EChapter.one);
			doc.setOid(i);
			dao.insert(doc);
		}
	}

}
