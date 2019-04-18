package com.genius.core.base.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author Architect.bian
 * 
 */
public class WebUtilTest {

	@Test
	public void testclearXSS() {
		String js = "<script src=''>1</script><script>2\n\r</script><Script src='' /><p onLoad='alert(0)' style='x:ex/*x*/pression:alert(1);'>你好</p><meta p='x' /><base p='x' >";
		assertEquals("<p 'alert(0)' >你好</p>", WebUtil.clearXSS(js));
	}
	
	@Test
	public void testMobileRegex() {
		String ua = "mozilla/4.0 (compatible;android;320x480)";
		assertTrue(ua.matches("(?i).*(android|bb\\d+|meego|avantgo|bada\\/|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|iris|kindle|lge |maemo|midp|mmp|mobile.+firefox|netfront|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\\/|plucker|pocket|psp|series(4|6)0|symbian|treo|up\\.(browser|link)|vodafone|wap|windows (ce|phone)|xda|xiino).*"));
	}
	
	@Test
	public void testEncode() {
		String url = "Succeed=Y&CoNo=007639&BillNo=7047920011&Amount=20&Date=20131216&MerchantPara=&Msg=00100076392013121613321671900000001460&Signature=46%7C143%7C187%7C15%7C159%7C42%7C208%7C41%7C79%7C139%7C107%7C198%7C45%7C152%7C142%7C29%7C243%7C158%7C157%7C161%7C163%7C247%7C2%7C15%7C23%7C215%7C217%7C177%7C168%7C46%7C210%7C151%7C87%7C220%7C67%7C90%7C253%7C127%7C122%7C203%7C8%7C29%7C209%7C178%7C245%7C170%7C161%7C13%7C244%7C242%7C186%7C14%7C167%7C23%7C115%7C175%7C136%7C77%7C4%7C93%7C161%7C242%7C139%7C108%7C";
		System.out.println(WebUtil.encodeURI(url));
		System.out.println(WebUtil.decodeURI(url));
	}
	
	@Test
	public void testClearHtml() {
		String str = "<h1>hi&nbsp;world &lt; hello&nbsp;world</h1>";
		String result = WebUtil.clearHtml(str).toString();
		assertEquals("hi&nbsp;world&lt;hello&nbsp;world", result);
		System.out.println(result);
	}
	
	@Test
	public void testClearHtmlTag() {
		String str = "<h1>hi&nbsp;world &lt; <>hello&nbsp;world<<<</h1>";
		String result = WebUtil.clearHtmlTag(str).toString();
		assertEquals("hi&nbsp;world &lt; <>hello&nbsp;world<<<", result);
		System.out.println(result);
	}
}
