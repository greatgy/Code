package com.genius.server.base.converter;

import org.joda.time.LocalDate;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.core.convert.converter.Converter;

/**
 * @author Architect.bian
 *
 */
public class StringToJodaDateConverter implements Converter<String, LocalDate> {
	
	@Override
	public LocalDate convert(String source) {
		String value = source.trim();
		if ("".equals(value)) {
			return null;
		}
		try {
			LocalDate date = ISODateTimeFormat.dateParser().parseDateTime(source).toLocalDate();
			return date;
		} catch (Exception e) {
			throw new IllegalArgumentException("Invalid joda LocalDate value '" + source + "',inner exception msg:" + e.getMessage());
		}
	}

}
