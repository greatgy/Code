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
import com.genius.captcha.component.image.backgroundgenerator.UniColorBackgroundGenerator;
import com.genius.captcha.component.image.color.SingleColorGenerator;
import com.genius.captcha.component.image.fontgenerator.FontGenerator;
import com.genius.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.genius.captcha.component.image.textpaster.DecoratedRandomTextPaster;
import com.genius.captcha.component.image.textpaster.TextPaster;
import com.genius.captcha.component.image.textpaster.textdecorator.BaffleTextDecorator;
import com.genius.captcha.component.image.textpaster.textdecorator.TextDecorator;
import com.genius.captcha.component.image.wordtoimage.ComposedWordToImage;
import com.genius.captcha.component.image.wordtoimage.WordToImage;
import com.genius.captcha.component.word.FileDictionary;
import com.genius.captcha.component.word.wordgenerator.ComposeDictionaryWordGenerator;
import com.genius.captcha.component.word.wordgenerator.WordGenerator;
import com.genius.captcha.engine.image.ListImageCaptchaEngine;
import com.genius.captcha.image.gimpy.GimpyFactory;

/**
 * <p>Description: </p>
 *
 * @author <a href="mailto:mag@jcaptcha.net">Marc-Antoine Garrigue</a>
 * @version 1.0
 */
@SuppressWarnings("deprecation")
public class BaffleListGimpyEngine extends ListImageCaptchaEngine {

    protected void buildInitialFactories() {
        //word generator
        WordGenerator dictionnaryWords = new ComposeDictionaryWordGenerator(
                new FileDictionary(
                        "toddlist"));
        //wordtoimage components
        TextPaster randomPaster = new DecoratedRandomTextPaster(
                new Integer(8), new Integer(15),
                new SingleColorGenerator(Color.BLACK),
                new TextDecorator[]{new BaffleTextDecorator(2,Color.black)});
        BackgroundGenerator back = new UniColorBackgroundGenerator(
                new Integer(200), new Integer(100), Color.white);
        //BackgroundGenerator back = new FunkyBackgroundGenerator(new Integer(200), new Integer(100));
        FontGenerator shearedFont = new RandomFontGenerator(new Integer(20),
                new Integer(25));
        //word2image 1
        WordToImage word2image = new ComposedWordToImage(
                shearedFont, back, randomPaster);

        this.addFactory(
                new GimpyFactory(dictionnaryWords,
                        word2image));
    }
}
