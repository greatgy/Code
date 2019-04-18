package com.genius.server.base.converter;

import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.core.convert.converter.Converter;

/**
 * @author Architect.bian
 *
 */
public class StringToJodaDateTimeConverter implements Converter<String, DateTime> {
	
	@Override
	public DateTime convert(String source) {
		String value = source.trim();
		if ("".equals(value)) {
			return null;
		}
		try {
			DateTime datetime = ISODateTimeFormat.dateTimeParser().parseDateTime(source.replace(" ", "T"));
			return datetime;
		} catch (Exception e) {
			throw new IllegalArgumentException("Invalid joda DateTime value '" + source + "',inner exception msg:" + e.getMessage());
		}
	}

}
