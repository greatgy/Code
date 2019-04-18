package com.genius.server.validcode.captcha;

import java.awt.Font;
import java.io.File;

import com.genius.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.genius.captcha.component.image.backgroundgenerator.FileReaderRandomBackgroundGenerator;
import com.genius.captcha.component.image.backgroundgenerator.StreamReaderRandomBackgroundGenerator;
import com.genius.captcha.component.image.color.RandomRangeColorGenerator;
import com.genius.captcha.component.image.fontgenerator.FontGenerator;
import com.genius.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.genius.captcha.component.image.textpaster.RandomTextPaster;
import com.genius.captcha.component.image.textpaster.TextPaster;
import com.genius.captcha.component.image.wordtoimage.ComposedWordToImage;
import com.genius.captcha.component.image.wordtoimage.WordToImage;
import com.genius.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.genius.captcha.component.word.wordgenerator.WordGenerator;
import com.genius.captcha.engine.image.ListImageCaptchaEngine;
import com.genius.core.base.conf.BaseSysConf;

/**
 * 可以将想作为验证码背景图放在BaseSysConf.SerialBasePath + BaseSysConf.CaptchaImgDir路径中
 * 若没有增加则默认从jar包的"/imgs/captcha"路径加载图片
 * @author Architect.bian
 */
@SuppressWarnings("deprecation")
public class MyImageCaptchaEngine extends ListImageCaptchaEngine {
	
	@Override
	protected void buildInitialFactories() {
		WordGenerator wgen = new RandomWordGenerator(BaseSysConf.CaptchaRandomWordGenerator);
        RandomRangeColorGenerator cgen = new RandomRangeColorGenerator(new int[] {0, 100}, new int[] {0, 100}, new int[] {0, 100});
        TextPaster textPaster = new RandomTextPaster(new Integer(BaseSysConf.CaptchaMinTextSize), new Integer(BaseSysConf.CaptchaMaxTextSize), cgen, true);  
        BackgroundGenerator backgroundGenerator = null;
		String captchaImgPath = BaseSysConf.SerialBasePath + BaseSysConf.CaptchaImgDir; //图片地址
        File file = new File(captchaImgPath);
        if (file.exists() && file.isDirectory() && file.list().length > 0) {//判断文件夹是否存在，并且有图片
        	backgroundGenerator = new FileReaderRandomBackgroundGenerator(new Integer(BaseSysConf.CaptchaImgWidth), new Integer(BaseSysConf.CaptchaImgHeight), captchaImgPath);
		} else {//如果配置文件不存在，则获取jar包中的默认图片
			backgroundGenerator = new StreamReaderRandomBackgroundGenerator(new Integer(BaseSysConf.CaptchaImgWidth), new Integer(BaseSysConf.CaptchaImgHeight), BaseSysConf.CaptchaImgDir);
		}
        Font[] fontsList = new Font[] {new Font("Arial", 0, BaseSysConf.CaptchaMinFontSize), new Font("Tahoma", 0, BaseSysConf.CaptchaMinFontSize), new Font("Verdana", 0, BaseSysConf.CaptchaMinFontSize)};  
        FontGenerator fontGenerator = new RandomFontGenerator(BaseSysConf.CaptchaMinFontSize, BaseSysConf.CaptchaMaxFontSize, fontsList);
        WordToImage wordToImage = new ComposedWordToImage(fontGenerator, backgroundGenerator, textPaster);
        this.addFactory(new MyGimpyFactory(wgen, wordToImage));
	}
}
