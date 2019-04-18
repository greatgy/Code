package com.genius.server.validcode.servlet;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.genius.captcha.service.CaptchaServiceException;
import com.genius.core.cache.rule.Rule;
import com.genius.core.cache.utils.MemcacheUtil;
import com.genius.core.session.SessionFactory;
import com.genius.core.session.constant.SessDict;
import com.genius.core.session.rule.SessionRule;
import com.genius.server.validcode.captcha.CaptchaServiceSingleton;
import com.genius.server.validcode.rule.AppUidRule;

/**
 * 生成验证码servlet
 * 可以将想作为验证码背景图放在BaseSysConf.SerialBasePath + BaseSysConf.CaptchaImgDir路径中
 * 若没有增加则默认从jar包的"/imgs/captcha"路径加载图片
 * @author Architect.bian
 * 
 */
public class ImageCaptchaServletForApp extends ImageCaptchaServlet {
	
	private static final long serialVersionUID = -6218503175145594818L;

	private static Logger log = LoggerFactory.getLogger(ImageCaptchaServletForApp.class);
	
	public static final String PhoneUID = "phoneuid";

	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		byte[] captchaChallengeAsJpeg = null;
		// the output stream to render the captcha image as jpeg into
		ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
		try {
			// get the session id that will identify the generated captcha.
			// the same id must be used to validate the response, the session id
			// is a good candidate!
			String phoneuid = req.getParameter(PhoneUID);
			HttpSession sess = SessionFactory.getHttpSession(req);
			String sessCaptchaKey = new SessionRule(req, SessDict.CAPTCHA).toString() + phoneuid;
			Rule rule = new AppUidRule(phoneuid);
			int i = 0;
			while (i++ < 10) {
				BufferedImage challenge;
				synchronized (GeneratedCaptchaWord) {//因使用到了GeneratedCaptchaWord 所以要同步
					// call the ImageCaptchaService getChallenge method
					challenge = CaptchaServiceSingleton.getInstance().getImageChallengeForID(sessCaptchaKey, req.getLocale());
					sess.setAttribute(sessCaptchaKey, GeneratedCaptchaWord.toString());
					MemcacheUtil.set(rule, GeneratedCaptchaWord.toString());//因phone没有cookie，根据phoneuid存到memcache中
				}
				if (CaptchaServiceSingleton.getInstance().validateResponseForID(sessCaptchaKey, GeneratedCaptchaWord.toString())) {
					ImageIO.write(challenge, CAPTCHA_IMAGE_FORMAT, jpegOutputStream);
					break;
				}
			}
		} catch (IllegalArgumentException e) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			super.logException(log, e);
			return;
		} catch (CaptchaServiceException e) {
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			super.logException(log, e);
			return;
		}

		captchaChallengeAsJpeg = jpegOutputStream.toByteArray();

		super.flushResp(resp, captchaChallengeAsJpeg);
	}
	
}
