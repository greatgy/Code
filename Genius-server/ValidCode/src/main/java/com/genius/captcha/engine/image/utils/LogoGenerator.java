/*
 * JCaptcha, the open source java framework for captcha definition and integration
 * Copyright (c)  2007 jcaptcha.net. All Rights Reserved.
 * See the LICENSE.txt file distributed with this package.
 */

/*
 * jcaptcha, the open source java framework for captcha definition and integration
 * copyright (c)  2007 jcaptcha.net. All Rights Reserved.
 * See the LICENSE.txt file distributed with this package.
 */

/*
 * jcaptcha, the open source java framework for captcha definition and integration
 * copyright (c)  2007 jcaptcha.net. All Rights Reserved.
 * See the LICENSE.txt file distributed with this package.
 */

package com.genius.captcha.engine.image.utils;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import com.genius.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.genius.captcha.component.image.backgroundgenerator.FileReaderRandomBackgroundGenerator;
import com.genius.captcha.component.image.fontgenerator.FontGenerator;
import com.genius.captcha.component.image.fontgenerator.TwistedAndShearedRandomFontGenerator;
import com.genius.captcha.component.image.textpaster.SimpleTextPaster;
import com.genius.captcha.component.image.textpaster.TextPaster;
import com.genius.captcha.component.image.wordtoimage.ComposedWordToImage;
import com.genius.captcha.component.image.wordtoimage.WordToImage;
import com.genius.captcha.component.word.wordgenerator.DummyWordGenerator;
import com.genius.captcha.component.word.wordgenerator.WordGenerator;
import com.genius.captcha.image.ImageCaptcha;
import com.genius.captcha.image.ImageCaptchaFactory;
import com.genius.captcha.image.gimpy.GimpyFactory;

/**
 * <p>Description: Generate a sample logo for the master webSite. Main method takes one arg : the file path of the
 * generated logo</p>
 *
 * @author <a href="mailto:mag@jcaptcha.net">Marc-Antoine Garrigue</a>
 * @version 1.0
 */
@SuppressWarnings("deprecation")
public class LogoGenerator {

	public static void main(String[] args) throws IOException {
        TextPaster paster = new SimpleTextPaster(new Integer(8), new Integer(8), Color.white);
        BackgroundGenerator back = new FileReaderRandomBackgroundGenerator(
                new Integer(200), new Integer(100),
                "/gimpybackgrounds");
        FontGenerator font = new TwistedAndShearedRandomFontGenerator(
                new Integer(30), null);
        WordGenerator words = new DummyWordGenerator("JCAPTCHA");
        WordToImage word2image = new ComposedWordToImage(font, back, paster);
        ImageCaptchaFactory factory = new GimpyFactory(words, word2image);
        ImageCaptcha pix = factory.getImageCaptcha();
        ImageToFile.serialize(pix.getImageChallenge(), new File(args[0]));
    }
}
