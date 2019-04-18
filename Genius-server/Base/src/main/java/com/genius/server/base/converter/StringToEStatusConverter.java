package com.genius.server.base.converter;

import org.springframework.core.convert.converter.Converter;

import com.genius.model.base.enums.EStatus;

/**
 * @author Architect.bian
 *
 */
public class StringToEStatusConverter implements Converter<String, EStatus> {
	
	@Override
	public EStatus convert(String source) {
		String value = source.trim();
		if ("".equals(value)) {
			return null;
		}
		return EStatus.get(Integer.parseInt(source));
	}

}
