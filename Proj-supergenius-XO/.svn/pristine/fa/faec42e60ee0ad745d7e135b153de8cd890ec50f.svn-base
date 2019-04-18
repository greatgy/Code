package com.supergenius.xo.___.init;

import org.joda.time.DateTime;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.model.base.enums.EStatus;
import com.supergenius.moral.moral.dao.VideoDao;
import com.supergenius.xo.moral.entity.Video;
import com.supergenius.xo.moral.enums.EChapter;

/**
 * 添加视频的初始化数据
 * 
 * @author LiJiacheng
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class InitVideoDataTest {

	@Autowired
	VideoDao dao;

	@Ignore
	@Test
	public void insertInitData() {
		for (int i = 0; i <= 5; i++) {
			Video video = new Video();
			video.setName("Name" + i);
			video.setChapter(EChapter.one);
			video.setCode("Code" + i);
			video.setImglittle("Imglittle" + i);
			video.setImg("Img" + i);
			video.setImgbig("Imgbig" + i);
			video.setImgoriginal("Imgoriginal" + i);
			video.setCreatetime(DateTime.now());
			video.setStatus(EStatus.enable);
			video.setCountpl(50 + i);
			dao.insert(video);
		}
	}

}
