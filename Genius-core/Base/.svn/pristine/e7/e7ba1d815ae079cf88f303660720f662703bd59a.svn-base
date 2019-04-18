package com.genius.core.base.utils;

import java.util.Random;

/**
 * @author Architect.bian
 *
 */
public class NumUtil extends BaseUtil {

	/**
	 * 返回[0,i)的随机数
	 * @param i
	 * @return
	 */
	public static int random(int i) {
		if (i == 0) {
			return 0;
		} else if(i < 0) {
			Random random = new Random();
			return random.nextInt(Math.abs(i)) * -1;
		} else {
			Random random = new Random();
			return random.nextInt(i);
		}
	}

}
