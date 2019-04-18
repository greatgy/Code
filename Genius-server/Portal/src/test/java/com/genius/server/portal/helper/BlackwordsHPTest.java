package com.genius.server.portal.helper;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.regex.Pattern;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.core.base.utils.StrUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class BlackwordsHPTest {

	private static String str4Test = "一切都像刚睡醒的样子，欣欣然张开了眼。山朗润起来了，水涨起来了，太阳的脸红起来了。 小草偷偷地从土地里钻出来，嫩嫩的，绿绿的。园子里，田野里，瞧去，一大片一大片满是的。坐着，躺着，打两个滚，踢几脚球，赛几趟跑，捉几回迷藏。风轻俏俏的，草软绵绵的。 桃树，杏树，梨树，你不让我，我不让你，都开满了花赶趟儿。红的像火，粉的像霞，白的像雪。花里带着甜味；闭了眼，树上仿佛已经满是桃儿，杏儿，梨儿。花下成千成百的蜜蜂嗡嗡的闹着，大小的蝴蝶飞来飞去。野花遍地是：杂样儿，有名字的，没名字的，散在草丛里像眼睛像星星，还眨呀眨法轮功。 ";
	
	static {
		for (int i = 0; i < 0; i++) {
			str4Test = StrUtil.getRandomString(1000) + str4Test;
//			str += StrUtil.getRandomString(1000);
		}
	}
	
	@Test
	public void testGetBlackWords(){
		assertTrue(BlackwordsHP.getBlackWords().size() > 0);
	}
	
	@Test
	public void testRegxTime() {
		String str = "aaabbbccc";
		String regex = "(.*bbb.*)|(.*ddd.*)";
		System.out.println(Pattern.matches(regex, str));
	}
	
	/**
	 * 先拼接成成正则  用正则来验证是否包含敏感词  用时：3090ms
	 * 开始时间:1384135922902
	 * 结束时间:1384135925992
	 * 所用时间:3090ms
	 * 
	 * 长文本上万字符1000次循环：
	 * 开始时间：1383986748141
	 * 结束时间：1383987096445
	 * 所用时间：348304
	 */
	@Test
	public void testRegxMatchOne() {
		List<String> list = BlackwordsHP.getBlackWords();
		StringBuffer regex = new StringBuffer();
		for (String item : list) {
			regex.append("(.*" + item.trim() + ".*)").append("|");
		}
		long timestart = System.currentTimeMillis();
		System.out.print("开始时间：" + timestart);
		for (int i = 0; i < 1000; i++) {
			assertTrue(Pattern.matches(regex.toString(), str4Test));
		}
		long timeend = System.currentTimeMillis();
		System.out.print("结束时间：" + timeend);
		long duration = timeend - timestart;
		System.out.print("testRegxMatchOne()所用时间：" + duration);
		
	}
	
	/**
	 * 先拼接成成正则  用正则来验证是否包含敏感词  用时：3054ms
	 * 开始时间1383984453438
	 * 结束时间1383984453682
	 * 所用时间244
	 * 
	 * 长文本上万字符，1000次，方法是assertTrue(p.matcher(str).find());
	 * 开始时间：1383987830647
	 * 结束时间：1383988161020
	 * 所用时间：330373
	 * 
	 * 拼接成正则，循环正则判断是否包含敏感词
	 * 长文本上万字符，1000次，方法是assertTrue(p.matcher(str).matches());
	 * 开始时间：1383988201699
	 * 结束时间：1383988533244
	 * 所用时间：331545
	 */
	@Test
	public void testRegxMatchWithOnePattern() {
		List<String> list = BlackwordsHP.getBlackWords();
		StringBuffer regex = new StringBuffer();
		for (String item : list) {
			regex.append("(.*" + item.trim() + ".*)").append("|");
		}
		Pattern p = Pattern.compile(regex.toString());
		long timestart = System.currentTimeMillis();
		System.out.print("开始时间：" + timestart);
		for (int i = 0; i < 1000; i++) {
			assertTrue(p.matcher(str4Test).matches());
		}
		long timeend = System.currentTimeMillis();
		System.out.print("结束时间：" + timeend);
		long duration = timeend - timestart;
		System.out.print("testRegxMatchWithOnePattern()所用时间：" + duration);
		
	}
	
	/**
	 * 拼接成正则，循环正则判断是否包含敏感词    
	 * 长文本上万字符，1000次循环
	 * 开始时间：1383987096451
	 * 结束时间：1383987425728
	 * 所用时间：329277
	 */
	@Test
	public void testRegxMatchEach() {
		List<String> listtoken = BlackwordsHP.getBlackWords();
		StringBuffer token = new StringBuffer();
		for (String item : listtoken) {
			token.append("(.*" + item.trim() + ".*)").append(",");
		}
		String[] string = token.toString().split(",");
		long timestart = System.currentTimeMillis();
		System.out.print("开始时间：" + timestart);
		for (int i = 0; i < 1000; i++) {
			for (String item : string) {
				if (Pattern.matches(item, str4Test)){
					//System.out.println("i = " + i);
					break;
				}
			}
		}
		long timeend = System.currentTimeMillis();
		System.out.print("结束时间：" + timeend);
		long duration = timeend - timestart;
		System.out.print("testRegxMatchEach()所用时间：" + duration);
		
	}
	
	/**
	 * 直接循环判断是否包含敏感词   用时： 227ms
	 * 开始时间:1383882770132
	 * 结束时间:1383882770381
	 * 所用时间:227ms
	 * 
	 * 长文本上万字符，1000次循环 str.contains(item)
	 * 开始时间：1383987425734
	 * 结束时间：1383987437375
	 * 所用时间：11641
	 */
	@Test
	public void testContainsEach() {
		List<String> list = BlackwordsHP.getBlackWords();
		long timestart = System.currentTimeMillis();
		System.out.print("开始时间：" + timestart);
		for (int i = 0; i < 1000; i++) {
			for (String item : list) {
				if (str4Test.contains(item)) {
					//System.out.println("i = " + i);
					break;
				}
			}
		}
		long timeend = System.currentTimeMillis();
		System.out.print("结束时间：" + timeend);
		long duration = timeend - timestart;
		System.out.print("testContainsEach()所用时间：" + duration);
	}

	/**
	 * 是否包含敏感词
	 */
	@Test
	public void testContainBlackWords() {
		assertTrue(BlackwordsHP.containBlackWords("<《<h1>你好, FucK is a word<h2>"));
	}
}
