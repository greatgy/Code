package com.genius.core.base.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.format.ISODateTimeFormat;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Architect.bian
 * 
 */
public class DateUtilTest {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Test
	public void JodaLocalDateTest() {
		LocalDate date = new LocalDate();
		logger.info("-----------print local date:" + date.toString());
	}

	@Test
	public void JodaDateTimeTest() {
		DateTime dateTime = new DateTime();
		logger.info("-----------print date time:" + dateTime.toString(DateUtil.FORMAT_DATETIME_CHINA));
	}

	@Test
	public void StrToJodaDateTest() {
		LocalDate date = ISODateTimeFormat.dateParser().parseDateTime("2012-09-09").toLocalDate();
		LocalDate expected = new LocalDate(2012, 9, 9);
		assertEquals(date, expected);
	}
	
	@Test
	public void StrToJodaDateTimeTest() {
		DateTime datetime = ISODateTimeFormat.dateTimeParser().parseDateTime("2012-09-09 12:36:56".replace(" ", "T"));
		DateTime expected = new DateTime(2012, 9, 9, 12, 36, 56, 0);
		assertEquals(datetime, expected);
	}
	
	@Test
	public void testSysOut() {
		System.out.println(DateUtil.getToday());
		assertTrue(DateUtil.getToday().length() == 8);
		System.out.println(DateUtil.getNowForID());
	}

	@Test
	public void testDateTime() {
		System.out.println(new DateTime(DateTimeZone.UTC));
	}

	@Test
	public void testDateTimeFormat() {
		DateTime date = new DateTime(DateTimeZone.forOffsetHours(8));
		System.out.println(date);
		System.out.println(date.toString());
		System.out.println(date.toString(DateUtil.FORMAT_DATE_Sitemap));
	}

}
