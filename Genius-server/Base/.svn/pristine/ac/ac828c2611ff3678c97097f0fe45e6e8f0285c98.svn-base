package com.genius.server.base.converter;

import org.springframework.core.convert.converter.Converter;

/**
 * @author Architect.bian
 *
 */
public class StringToDoubleConverter implements Converter<String, Double> {
	
	@Override
	public Double convert(String source) {
		String value = source.trim();
		if ("".equals(value)) {
			return null;
		}
		return Double.valueOf(source.replace(",", ""));
	}

}
