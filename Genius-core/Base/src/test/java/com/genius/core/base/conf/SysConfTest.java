package com.genius.core.base.conf;

import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.core.base.conf.BaseSysConf;

/**
 * @author Architect.bian
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class SysConfTest {

	@Test
	public void test() {
		// File f = new File(ClassLoader.getSystemResource("").getFile());
		// f = f.getParentFile().getParentFile();
		// String expected = StrUtil.trim(f.getPath(),"/");
		String expected = BaseSysConf.WebAppDir;
		System.out.println(expected);
		assertTrue(StringUtils.isNotEmpty(expected));
	}

}
