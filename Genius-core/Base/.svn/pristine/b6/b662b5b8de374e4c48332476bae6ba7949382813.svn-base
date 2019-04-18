package com.genius.core.base.utils;

import static org.junit.Assert.assertNotNull;

import java.util.Locale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.core.base.mock.testenums.EStatus;

/**
 * @author Architect.bian
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class I18NTest {

	/**
	 * Test method for
	 * {@link com.wbcom.global.constants.I18N#getEnum(java.util.Locale)}.
	 */
	@Test
	public void testGetEnum() {
		assertNotNull(I18N.getEnum(Locale.CHINA));
		System.out.println(I18N.getEnumName(EStatus.enable, Locale.CHINA));
		assertNotNull(I18N.getEnumName(EStatus.enable, Locale.CHINA));
	}

	/**
	 * Test method for
	 * {@link com.wbcom.global.constants.I18N#getEnumName(java.lang.Enum, java.util.Locale)}
	 * .
	 */
	@Test
	public void testGetEnumName() {
		// System.out.println(I18N.getEnumName(EContent.aboutus, Locale.CHINA));
		// System.out.println(I18N.getEnumName(EContent.aboutus, Locale.ITALY));
		assertNotNull(I18N.getEnumName(EStatus.enable, Locale.CHINA));
	}

	/**
	 * Test method for {@link com.wbcom.global.constants.I18N#fresh()}.
	 */
	@Test
	public void testFresh() {
		I18N.fresh();
	}

}
