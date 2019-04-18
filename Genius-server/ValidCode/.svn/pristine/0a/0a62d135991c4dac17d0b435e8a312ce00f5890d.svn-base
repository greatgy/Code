package com.genius.server.validcode.captcha;

import java.awt.image.BufferedImage;
import java.util.Locale;

import com.genius.captcha.CaptchaException;
import com.genius.captcha.CaptchaQuestionHelper;
import com.genius.captcha.component.image.wordtoimage.WordToImage;
import com.genius.captcha.component.word.wordgenerator.WordGenerator;
import com.genius.captcha.image.ImageCaptcha;
import com.genius.captcha.image.gimpy.GimpyFactory;
import com.genius.server.validcode.servlet.ImageCaptchaServlet;

/**
 * @author Architect.bian
 *
 */
public class MyGimpyFactory extends GimpyFactory {

	/**
	 * @param generator
	 * @param word2image
	 */
	public MyGimpyFactory(WordGenerator generator, WordToImage word2image) {
		super(generator, word2image);
	}

	public ImageCaptcha getImageCaptcha(Locale locale) {
		Integer wordLength = getRandomLength();

		String word = getWordGenerator().getWord(wordLength, locale);
		
		ImageCaptchaServlet.GeneratedCaptchaWord.replace(0, ImageCaptchaServlet.GeneratedCaptchaWord.length(), word);
//		System.out.println(word);

		BufferedImage image = null;
		try {
			image = getWordToImage().getImage(word);
		} catch (Throwable e) {
			throw new CaptchaException(e);
		}

		ImageCaptcha captcha = new MyGimpy(CaptchaQuestionHelper.getQuestion(locale, BUNDLE_QUESTION_KEY), image, word);

		return captcha;
	}
}
