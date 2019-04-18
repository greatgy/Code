package com.genius.server.validcode.captcha;

import com.genius.captcha.service.captchastore.FastHashMapCaptchaStore;
import com.genius.captcha.service.image.DefaultManageableImageCaptchaService;
import com.genius.captcha.service.image.ImageCaptchaService;

/**
 * @author Architect.bian
 *
 */
public class CaptchaServiceSingleton {
	
	private static ImageCaptchaService instance = new DefaultManageableImageCaptchaService();
	
	static {
		instance = new DefaultManageableImageCaptchaService(new FastHashMapCaptchaStore(), new MyImageCaptchaEngine(), 180, 100000, 75000);
	}
	
    public static ImageCaptchaService getInstance(){
        return instance;
    }
}
