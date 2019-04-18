package com.supergenius.web.front.user.helper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Duanlei Song <br>
 *         2011-4-8
 */
public class FilePathMgr {
	private static String propertyFileName = "licene.txt";
	private static final String LICENE;
	static {
		String path = "";
		try {
			path = FilePathMgr.class.getClassLoader().getResource(propertyFileName).getPath();
		} catch (Exception e) {
			e.printStackTrace();
		}
		LICENE = readLicene(path);
	}

	public static String getLicene() {
		return LICENE;
	}

	@SuppressWarnings("resource")
	public static String readLicene(String path) {
		String licensecode = null;
		BufferedReader in;
		try {
			in = new BufferedReader(new FileReader(path));
			licensecode = in.readLine();
			return licensecode;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

}
