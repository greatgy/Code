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
import com.genius.captcha.component.image.backgroundgenerator.GradientBackgroundGenerator;
import com.genius.captcha.component.image.color.RandomListColorGenerator;
import com.genius.captcha.component.image.fontgenerator.DeformedRandomFontGenerator;
import com.genius.captcha.component.image.fontgenerator.FontGenerator;
import com.genius.captcha.component.image.textpaster.NonLinearTextPaster;
import com.genius.captcha.component.image.textpaster.TextPaster;
import com.genius.captcha.component.image.wordtoimage.ComposedWordToImage;
import com.genius.captcha.component.image.wordtoimage.WordToImage;
import com.genius.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.genius.captcha.component.word.wordgenerator.WordGenerator;
import com.genius.captcha.engine.image.ListImageCaptchaEngine;
import com.genius.captcha.image.gimpy.GimpyFactory;

/**
 * Use the non-linear text paster
 * @date 19 mars 2007
 * @author <a href="mailto:antoine.veret@gmail.com">Antoine Vret</a>
 */
@SuppressWarnings("deprecation")
public class NonLinearTextGimpyEngine extends ListImageCaptchaEngine {

    protected void buildInitialFactories() {
        
        WordGenerator wordGenerator =
                new RandomWordGenerator(
                "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");

        TextPaster textPaster = new NonLinearTextPaster(new Integer(5),
                new Integer(7), new RandomListColorGenerator(new Color[] {Color.BLACK, Color.YELLOW,
                Color.WHITE}), Boolean.TRUE);

        BackgroundGenerator backgroundGenerator = new GradientBackgroundGenerator(
                new Integer(200), new Integer(100), Color.CYAN, Color.GRAY);

        FontGenerator fontGenerator = new DeformedRandomFontGenerator(
                new Integer(25), new Integer(30));

        WordToImage wordToImage = new ComposedWordToImage(fontGenerator,
                backgroundGenerator, textPaster);

        this.addFactory(new GimpyFactory(wordGenerator, wordToImage));
    }
}
