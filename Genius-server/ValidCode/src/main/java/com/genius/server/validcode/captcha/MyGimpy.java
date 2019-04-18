package com.genius.server.validcode.captcha;

import java.awt.image.BufferedImage;

import com.genius.captcha.image.ImageCaptcha;

/**
 * @author Architect.bian
 * 
 */
public class MyGimpy extends ImageCaptcha {
	private static final long serialVersionUID = 1L;
	
	private String response;

	/**
	 * @param question
	 * @param challenge
	 * @param response
	 */
	MyGimpy(String question, BufferedImage challenge, String response) {
		super(question, challenge);
		this.response = response;
	}

	public final Boolean validateResponse(Object response) {
		return (null != response) && ((response instanceof String)) ? validateResponse((String) response) : Boolean.FALSE;
	}

	private final Boolean validateResponse(String response) {
		return Boolean.valueOf(response.equals(this.response));
	}

}
