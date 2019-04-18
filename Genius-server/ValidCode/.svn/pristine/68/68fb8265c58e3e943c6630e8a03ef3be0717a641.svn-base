/*
 * JCaptcha, the open source java framework for captcha definition and integration
 * Copyright (c)  2007 jcaptcha.net. All Rights Reserved.
 * See the LICENSE.txt file distributed with this package.
 */

package com.genius.captcha.service.image;

import java.awt.image.BufferedImage;
import java.util.Locale;

import com.genius.captcha.service.CaptchaService;
import com.genius.captcha.service.CaptchaServiceException;

/**
 * <p/>
 * Specialize a Captcha service that return Image Captchas </p>
 *
 * @author <a href="mailto:mag@jcaptcha.net">Marc-Antoine Garrigue</a>
 * @version 1.0
 */
public interface ImageCaptchaService extends CaptchaService {

    /**
     * Method to retrive the image challenge corresponding to the given ticket.
     *
     * @param ID the ticket
     *
     * @return the challenge
     *
     * @thrcom.genius.captcha.serviceervice.CaptchaServiceException
     *          if the ticket is invalid
     */
    BufferedImage getImageChallengeForID(String ID) throws CaptchaServiceException;

    /**
     * Method to retrive the image challenge corresponding to the given ticket.
     *
     * @param ID the ticket
     *
     * @return the challenge
     *
     * @tcom.genius.captcha.service.service.CaptchaServiceException
     *          if the ticket is invalid
     */
    BufferedImage getImageChallengeForID(String ID, Locale locale) throws CaptchaServiceException;

}
