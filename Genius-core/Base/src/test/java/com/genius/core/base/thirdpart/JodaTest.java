package com.genius.core.base.thirdpart;

import java.io.IOException;
import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * @author Architect.bian
 * 
 */
public class JodaTest {

	@Test
	public void JsonTest() throws JsonGenerationException, JsonMappingException, IOException {
		DateTime dateTime = new DateTime();
		System.out.println(dateTime.toString());
		System.out.println(dateTime.toString("YYYY-MM-dd HH:mm:ss"));
		System.out.println(dateTime.toString("YYYY-MM-dd HH:mm:ss", Locale.CHINA));
		dateTime = dateTime.toDateTime(DateTimeZone.forOffsetHours(1));
		System.out.println(dateTime.toString());
		System.out.println(dateTime.toString("YYYY-MM-dd HH:mm:ss"));
		System.out.println(dateTime.toString("YYYY-MM-dd HH:mm:ss", Locale.CHINA));
		dateTime = dateTime.toDateTime(DateTimeZone.forOffsetHours(8));
		System.out.println(dateTime.toString());
		System.out.println(dateTime.toString("YYYY-MM-dd HH:mm:ss"));
		System.out.println(dateTime.toString("YYYY-MM-dd HH:mm:ss", Locale.CHINA));
	}
}
