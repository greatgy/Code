package com.supergenius.core.converters;

import org.springframework.core.convert.converter.Converter;

import com.supergenius.xo.tpi.enums.EArticleChannel;

/**
 * 配置spring mvc自动接收EArticleChannel
 * 
 * @author ShangJianguo
 */
public class StringToEArticleChannelConverter implements Converter<String, EArticleChannel> {

	@Override
	public EArticleChannel convert(String source) {
		String value = source.trim();
		if ("".equals(value)) {
			return null;
		}
		return EArticleChannel.get(Integer.parseInt(source));
	}
}
