package com.genius.server.base.converter;

import java.io.IOException;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.genius.core.base.constant.BaseViewKeyDict;
import com.genius.core.base.utils.JsonUtil;

/**
 * 将controller返回的object转成Jackson
 * @author architect.bian
 * @createtime 2014-6-29 下午5:38:03
 */
public class JacksonHttpMessageConverter extends MappingJackson2HttpMessageConverter {

	/* (non-Javadoc)
	 * @see org.springframework.http.converter.AbstractHttpMessageConverter#writeInternal(java.lang.Object, org.springframework.http.HttpOutputMessage)
	 * @author Architect.bian
	 * @createtime 2014-6-29 下午5:38:03
	 */
	@Override
	protected void writeInternal(Object object, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
		JsonUtil.write(outputMessage.getBody(), object, getJsonEncoding(outputMessage.getHeaders().getContentType()), outputMessage.getHeaders().getFirst(BaseViewKeyDict._jsonStrategy));
	}

	/* (non-Javadoc)
	 * @see org.springframework.http.converter.AbstractHttpMessageConverter#readInternal(java.lang.Class, org.springframework.http.HttpInputMessage)
	 * @author Architect.bian
	 * @createtime 2014-6-29 下午5:38:03
	 */
	@Override
	protected Object readInternal(Class<? extends Object> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
		return super.readInternal(clazz, inputMessage);
	}

}
