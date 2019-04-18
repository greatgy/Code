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

package com.genius.captcha.engine.image.fisheye;

import java.awt.image.ImageFilter;

import com.genius.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.genius.captcha.component.image.backgroundgenerator.FileReaderRandomBackgroundGenerator;
import com.genius.captcha.component.image.deformation.ImageDeformation;
import com.genius.captcha.component.image.deformation.ImageDeformationByFilters;
import com.genius.captcha.engine.image.ListImageCaptchaEngine;
import com.genius.captcha.image.fisheye.FishEyeFactory;

/**
 * Produce fishEye from files. FishEye are done from sphere
 *
 * @author <a href="mailto:mag@jcaptcha.net">Marc-Antoine Garrigue</a>
 * @version 1.0
 */
public class SimpleFishEyeEngine extends ListImageCaptchaEngine {

    /**
     * this method should be implemented as folow : <ul> <li>First construct all the factories you want to initialize
     * the gimpy with</li> <li>then call the this.addFactoriy method for each factory</li> </ul>
     */
    protected void buildInitialFactories() {
        //build filters
        com.jhlabs.image.SphereFilter sphere = new com.jhlabs.image.SphereFilter();
        com.jhlabs.image.RippleFilter ripple = new com.jhlabs.image.RippleFilter();
        com.jhlabs.image.TwirlFilter twirl = new com.jhlabs.image.TwirlFilter();
        com.jhlabs.image.WaterFilter water = new com.jhlabs.image.WaterFilter();

        ripple.setWaveType(com.jhlabs.image.RippleFilter.NOISE);
        ripple.setXAmplitude(10);
        ripple.setYAmplitude(10);
        ripple.setXWavelength(10);
        ripple.setYWavelength(10);
        ripple.setEdgeAction(com.jhlabs.image.TransformFilter.CLAMP);

        water.setAmplitude(10);
 //       water.setAntialias(true);
        water.setWavelength(20);

        twirl.setAngle(4);

        sphere.setRefractionIndex(2);

        ImageDeformation rippleDef = new ImageDeformationByFilters(
                new ImageFilter[]{});
        ImageDeformation sphereDef = new ImageDeformationByFilters(
                new ImageFilter[]{});
        ImageDeformation waterDef = new ImageDeformationByFilters(
                new ImageFilter[]{});
        ImageDeformation twirlDef = new ImageDeformationByFilters(
                new ImageFilter[]{});

        //add background from files
        BackgroundGenerator generator = new FileReaderRandomBackgroundGenerator(
                new Integer(250), new Integer(250),
                "./fisheyebackgrounds");
        addFactory(
                new FishEyeFactory(generator, sphereDef, new Integer(10),
                        new Integer(5)));
        addFactory(
                new FishEyeFactory(generator, rippleDef, new Integer(10),
                        new Integer(5)));
        addFactory(
                new FishEyeFactory(generator, waterDef, new Integer(10),
                        new Integer(5)));
        addFactory(
                new FishEyeFactory(generator, twirlDef, new Integer(10),
                        new Integer(5)));

    }
}