package com.genius.core.base.utils;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;

import com.genius.core.base.constant.BaseStrDict;

/**
 * @author Architect.bian
 *
 */
public class I18N extends BaseUtil {

	private static final String ErrPrefix = "err_";

	public static String getGlobal(String key, Locale locale) {
		ResourceBundle bundle = ResourceBundle.getBundle("i18n/global", locale);
		if (bundle.containsKey(key)) {
			return bundle.getString(key);
		}
		return null;
	}
	
	public static String getGlobal(String key, Locale locale, String... args) {
		ResourceBundle bundle = ResourceBundle.getBundle("i18n/global", locale);
		if (bundle.containsKey(key)) {
		   String pattern = bundle.getString(key);
		   return MessageFormat.format(pattern, (Object[])args);
		}
		return null;
	}
	
	public static String getSEO(String key, Locale locale) {
		ResourceBundle bundle = ResourceBundle.getBundle("i18n/seo", locale);
		if (bundle.containsKey(key)) {
			return bundle.getString(key);
		}
		return null;
	}
	
	public static String getSEO(String key, Locale locale, String... args) {
		ResourceBundle bundle = ResourceBundle.getBundle("i18n/seo", locale);
		if (bundle.containsKey(key)) {
			String pattern = bundle.getString(key);
			return MessageFormat.format(pattern, (Object[])args);
		}
		return null;
	}
	
	public static String getMsg(String key, Locale locale) {
		ResourceBundle bundle = ResourceBundle.getBundle("i18n/msg", locale);
		if (bundle.containsKey(key)) {
			return bundle.getString(key);
		}
		return null;
	}
	public static String getMsg(String key, Locale locale, String... args) {
		ResourceBundle bundle = ResourceBundle.getBundle("i18n/msg", locale);
		if (bundle.containsKey(key)) {
			String pattern = bundle.getString(key);
			return MessageFormat.format(pattern, (Object[])args);
		}
		return null;
	}

	/**
	 * @param key
	 * @param locale
	 * @return
	 */
	public static String getError(String key, Locale locale) {
		if (StringUtils.isEmpty(key)) {
			return "";
		} else if (!key.startsWith(ErrPrefix)) {
			key = ErrPrefix + key;
		}
		return ResourceBundle.getBundle("i18n/error", locale).getString(key);
	}

	public static String getError(String key, Locale locale, String... args) {
		ResourceBundle bundle = ResourceBundle.getBundle("i18n/error", locale);
		if (bundle.containsKey(key)) {
			String pattern = bundle.getString(key);
			return MessageFormat.format(pattern, (Object[])args);
		}
		return null;
	}
	
	public static ResourceBundle getEnum(Locale locale) {
		return ResourceBundle.getBundle("i18n/enum", locale);
	}
	
	public static String getEnum(Locale locale, String key) {
		ResourceBundle bundle = ResourceBundle.getBundle("i18n/enum", locale);
		if (bundle.containsKey(key)) {
			return bundle.getString(key);
		}
		return null;
	}

	/**
	 * @param locale
	 * @param e
	 * @return
	 */
	public static String getEnumName(Enum<?> e, Locale locale) {
		if (e == null) {
			return "";
		}
		String name = null;
		try {
			name = getEnum(locale, e.getClass().getSimpleName() + BaseStrDict.dot + e.name());
		} catch (MissingResourceException ex) {
			try {
				name = getEnum(locale,e.getClass().getName() + BaseStrDict.dot + e.name());
			} catch (MissingResourceException exception) {
				name = e.name();
			}
		}
		return name;
	}

	public static boolean fresh() {
		ResourceBundle.clearCache();
		return true;
	}

}
