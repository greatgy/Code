package com.genius.core.base.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Architect.bian
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class ImgUtilTest {

	private static String imglarge = StrUtil.trim(ClassLoader.getSystemResource("images/large.jpg").getFile(), "/");
	private static String imggates = StrUtil.trim(ClassLoader.getSystemResource("images/gates.jpg").getFile(), "/");
	private static String imgnature = StrUtil.trim(ClassLoader.getSystemResource("images/nature.jpg").getFile(), "/");
	private static String imgwater = StrUtil.trim(ClassLoader.getSystemResource("images/watermark.png").getFile(), "/");

	@Test
	public void testResizeImage() {
		String expected = imggates.substring(0, imggates.lastIndexOf(".")) + "_300_300.jpg";
		String actual = ImgUtil.resizeImage(300, 300, imggates);
		assertEquals(expected, actual);
		String expected2 = imglarge.substring(0, imglarge.lastIndexOf(".")) + "_300_300.jpg";
		String actual2 = ImgUtil.resizeImage(300, 300, imglarge);
		assertEquals(expected2, actual2);
	}

	@Test
	public void testWaterMarkNoCreate() {
		String expected = imgnature;
		String actual = ImgUtil.waterMark(imgnature, imgwater, ImgUtil.southeast);
		assertEquals(expected, actual);
	}

	@Test
	public void testWaterMark() {
		String expected = imgnature.substring(0, imgnature.lastIndexOf(".")) + "_w.jpg";
		String actual = ImgUtil.waterMark(imgwater, ImgUtil.southeast, 100, imgnature);
		assertEquals(expected, actual);
	}

	@Test
	public void testTextWaterMark() {
		String expected = imgnature.substring(0, imgnature.lastIndexOf(".")) + "_wt.jpg";
		String actual = ImgUtil.waterTextMark("http://zizhuan.me", 20, "#fff", ImgUtil.southeast, 0, imgnature);
		assertEquals(expected, actual);
	}

	@Test
	public void testRotate() {
		String expected = imgnature.substring(0, imgnature.lastIndexOf(".")) + "_r_" + Double.valueOf(30).toString() + ".jpg";
		String actual = ImgUtil.rotate(30, imgnature);
		assertEquals(expected, actual);
	}

	@Test
	public void testCropImage() {
		String expected = imgnature.substring(0, imgnature.lastIndexOf(".")) + "_50_500.jpg";
		String actual = ImgUtil.cropImage(50, 500, imgnature);
		assertEquals(expected, actual);
	}

	@Test
	public void testCutImage() {
		String expected = imgnature.substring(0, imgnature.lastIndexOf(".")) + "_200_50_800_500.jpg";
		String actual = ImgUtil.cutImage(200, 50, 800, 500, imgnature);
		assertEquals(expected, actual);
	}

	@Test
	public void testConcatenateImage() {
		String expected = imggates.substring(0, imggates.lastIndexOf(".")) + "_c3.jpg";
		String actual = ImgUtil.concatenateImage(400, 200, imggates, imgnature, imglarge);
		assertEquals(expected, actual);
	}

	@Test
	public void testShowImageInfo() {
		String actual = ImgUtil.showImageInfo(imgnature);
		System.out.println(actual);
		assertTrue(actual.length() > 0);
	}

	@Test
	public void testGetImageWdith() {
		int expected = 1024;
		int actual = ImgUtil.getImageWidth(imgnature);
		assertEquals(expected, actual);
	}

	@Test
	public void testGetImageHeight() {
		int expected = 768;
		int actual = ImgUtil.getImageHeight(imgnature);
		assertEquals(expected, actual);
	}

	@Test
	public void testGetGeometry() {
		String expected = "0x0";
		String actual = ImgUtil.getGeometry(imgnature);
		System.out.println(actual);
		assertEquals(expected, actual);
	}

	@Test
	public void testGetImageFormat() {
		String expected = "JPEG";
		String actual = ImgUtil.getImageFormat(imgnature);
		System.out.println(actual);
		assertEquals(expected, actual);
	}

	@Test
	public void testGray() {
		String expected = imggates.substring(0, imggates.lastIndexOf(".")) + "_gray.jpg";
		String actual = ImgUtil.gray(imggates);
		assertEquals(expected, actual);
	}
}
