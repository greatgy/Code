package com.supergenius.xo.___.init;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.supergenius.xo.tpi.dao.ArticleDao;
import com.supergenius.xo.tpi.entity.Article;
import com.supergenius.xo.tpi.enums.EArticleChannel;
import com.supergenius.xo.tpi.enums.EMergeCaseType;
import com.supergenius.xo.tpi.enums.EMergeNewsType;


/**
 * 添加文章相关的初始化数据
 * 
 * @author LiuXiaoke
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class InitArticleDataTest {
	
	@Autowired
	ArticleDao dao;
	@Ignore
	@Test
	public void insertInitData(){
		for(int i=0;i<40;i++) {
		Article article = new Article();
		String t = "国外动态" + i;
		article.setTitle(t);
		article.setOrigin("VIP投资机构");
		article.setAuthor("张三");
		String s = "";
		for(int j=0;j<100;j++)s += "国外动态";
		article.setContent(s);
		article.setChannel(EArticleChannel.mergenews);
		article.setNtype(EMergeNewsType.overseasnews.name());
		article.setKeyword("Keywords");
		article.setSummary("介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍");
		article.setImglittle("logo.jpg");
		article.setImgmedium("logo.jpg");
		article.setImgbig("logo.jpg");
		article.setIspublic(true);
		article.setIstop(false);
		article.setIsrecommend(false);
		dao.insert(article);
		Article article1 = new Article();
		t = "国内动态" + i;
		article1.setTitle(t);
		article1.setOrigin("VIP投资机构");
		article1.setAuthor("张三");
		s = "";
		for(int j=0;j<100;j++)s += "国内动态";
		article1.setContent(s);
		article1.setChannel(EArticleChannel.mergenews);
		article1.setNtype(EMergeNewsType.domesticnews.name());
		article1.setKeyword("Keywords");
		article1.setSummary("介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍");
		article1.setImglittle("logo.jpg");
		article1.setImgmedium("logo.jpg");
		article1.setImgbig("logo.jpg");
		article1.setIspublic(true);
		article1.setIstop(false);
		article1.setIsrecommend(false);
		dao.insert(article1);
		Article article2 = new Article();
		t = "并购花絮" + i;
		article2.setTitle(t);
		article2.setOrigin("VIP投资机构");
		article2.setAuthor("张三");
		s = "";
		for(int j=0;j<100;j++)s += "并购花絮";
		article2.setContent(s);
		article2.setChannel(EArticleChannel.mergenews);
		article2.setNtype(EMergeNewsType.tidbits.name());
		article2.setKeyword("Keywords");
		article2.setSummary("介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍");
		article2.setImglittle("logo.jpg");
		article2.setImgmedium("logo.jpg");
		article2.setImgbig("logo.jpg");
		article2.setIspublic(true);
		article2.setIstop(false);
		article2.setIsrecommend(false);
		dao.insert(article2);
		Article article3 = new Article();
		t = "并购猜想" + i;
		article3.setTitle(t);
		article3.setOrigin("VIP投资机构");
		article3.setAuthor("张三");
		s = "";
		for(int j=0;j<100;j++)s += "并购猜想";
		article3.setContent(s);
		article3.setChannel(EArticleChannel.mergenews);
		article3.setNtype(EMergeNewsType.predictions.name());
		article3.setKeyword("Keywords");
		article3.setSummary("介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍");
		article3.setImglittle("logo.jpg");
		article3.setImgmedium("logo.jpg");
		article3.setImgbig("logo.jpg");
		article3.setIspublic(true);
		article3.setIstop(false);
		article3.setIsrecommend(false);
		dao.insert(article3);
		Article article4 = new Article();
		t = "案例分析" + i;
		article4.setTitle(t);
		article4.setOrigin("VIP投资机构");
		article4.setAuthor("张三");
		s = "";
		for(int j=0;j<100;j++)s += "案例分析";
		article4.setContent(s);
		article4.setChannel(EArticleChannel.mergecase);
		article4.setCtype(EMergeCaseType.casestudies.name());
		article4.setKeyword("Keywords");
		article4.setSummary("介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍");
		article4.setImglittle("logo.jpg");
		article4.setImgmedium("logo.jpg");
		article4.setImgbig("logo.jpg");
		article4.setIspublic(true);
		article4.setIstop(false);
		article4.setIsrecommend(false);
		dao.insert(article4);
		Article article5 = new Article();
		t = "传奇人物" + i;
		article5.setTitle(t);
		article5.setOrigin("VIP投资机构");
		article5.setAuthor("张三");
		s = "";
		for(int j=0;j<100;j++)s += "传奇人物";
		article5.setContent(s);
		article5.setChannel(EArticleChannel.mergecase);
		article5.setCtype(EMergeCaseType.legend.name());
		article5.setKeyword("Keywords");
		article5.setSummary("介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍");
		article5.setImglittle("logo.jpg");
		article5.setImgmedium("logo.jpg");
		article5.setImgbig("logo.jpg");
		article5.setIspublic(true);
		article5.setIstop(false);
		article5.setIsrecommend(false);
		dao.insert(article5);
		Article article6 = new Article();
		t = "并购故事" + i;
		article6.setTitle(t);
		article6.setOrigin("VIP投资机构");
		article6.setAuthor("张三");
		s = "";
		for(int j=0;j<100;j++)s += "并购故事";
		article6.setContent(s);
		article6.setChannel(EArticleChannel.mergecase);
		article6.setCtype(EMergeCaseType.mergerstories.name());
		article6.setKeyword("Keywords");
		article6.setSummary("介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍");
		article6.setImglittle("logo.jpg");
		article6.setImgmedium("logo.jpg");
		article6.setImgbig("logo.jpg");
		article6.setIspublic(true);
		article6.setIstop(false);
		article6.setIsrecommend(false);
		dao.insert(article6);
		delay();
		}
		
		
		}
    void delay() {
    	try {
			Thread.sleep(1000);
			System.out.print("==");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	}
	
