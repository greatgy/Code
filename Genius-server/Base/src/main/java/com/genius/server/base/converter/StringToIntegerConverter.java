package com.genius.server.base.converter;

import org.springframework.core.convert.converter.Converter;

/**
 * @author Architect.bian
 *
 */
public class StringToIntegerConverter implements Converter<String, Integer> {
	
	@Override
	public Integer convert(String source) {
		String value = source.trim();
		if ("".equals(value)) {
			return null;
		}
		return Integer.valueOf(source.replace(",", ""));
	}

}
