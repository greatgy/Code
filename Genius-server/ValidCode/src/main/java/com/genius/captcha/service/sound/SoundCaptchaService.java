/*
 * JCaptcha, the open source java framework for captcha definition and integration
 * Copyright (c)  2007 jcaptcha.net. All Rights Reserved.
 * See the LICENSE.txt file distributed with this package.
 */

package com.genius.captcha.service.sound;

import java.util.Locale;

import javax.sound.sampled.AudioInputStream;

import com.genius.captcha.service.CaptchaService;
import com.genius.captcha.service.CaptchaServiceException;

/**
 * @author <a href="mailto:marc.antoine.garrigue@gmail.com">Marc-Antoine Garrigue</a>
 * @version 1.0
 */
public interface SoundCaptchaService extends CaptchaService {
    /**
     * Method to retrive the sound challenge corresponding to the given ticket.
     *
     * @param ID the ticket
     *
     * @return the challenge
     *
     * @thrcom.genius.captcha.serviceervice.CaptchaServiceException
     *          if the ticket is invalid
     */
    AudioInputStream getSoundChallengeForID(String ID) throws CaptchaServiceException;

    /**
     * Method to retrive the sound challenge corresponding to the given ticket.
     *
     * @param ID the ticket
     *
     * @return the challenge
     *
     * @tcom.genius.captcha.service.service.CaptchaServiceException
     *          if the ticket is invalid
     */
    AudioInputStream getSoundChallengeForID(String ID, Locale locale) throws CaptchaServiceException;

}