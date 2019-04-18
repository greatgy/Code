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

package com.genius.captcha.engine.image.gimpy;

import java.awt.Color;

import com.genius.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.genius.captcha.component.image.backgroundgenerator.FunkyBackgroundGenerator;
import com.genius.captcha.component.image.fontgenerator.FontGenerator;
import com.genius.captcha.component.image.fontgenerator.TwistedAndShearedRandomFontGenerator;
import com.genius.captcha.component.image.textpaster.RandomTextPaster;
import com.genius.captcha.component.image.textpaster.TextPaster;
import com.genius.captcha.component.image.wordtoimage.ComposedWordToImage;
import com.genius.captcha.component.image.wordtoimage.WordToImage;
import com.genius.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.genius.captcha.component.word.wordgenerator.WordGenerator;
import com.genius.captcha.engine.image.ListImageCaptchaEngine;
import com.genius.captcha.image.gimpy.GimpyFactory;

/**
 * <p>Description: </p>
 *
 * @author <a href="mailto:mga@octo.com">Mathieu Gandin</a>
 * @version 1.0
 */
@SuppressWarnings("deprecation")
public class SimpleListImageCaptchaEngine
        extends ListImageCaptchaEngine {

    protected void buildInitialFactories() {
        WordGenerator wordGenerator = new RandomWordGenerator(
                "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
        TextPaster textPaster = new RandomTextPaster(new Integer(5),
                new Integer(8), Color.white);
        BackgroundGenerator backgroundGenerator = new FunkyBackgroundGenerator(
                new Integer(200), new Integer(100));
        FontGenerator fontGenerator = new TwistedAndShearedRandomFontGenerator(
                new Integer(25), new Integer(30));
        WordToImage wordToImage = new ComposedWordToImage(
                fontGenerator, backgroundGenerator, textPaster);
        this.addFactory(
                new GimpyFactory(wordGenerator,
                        wordToImage));
    }

}
