package com.genius.core.base.utils;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * @author Architect.bian
 * 
 */
public class GlobalUtilTest {

	@Test
	public void getUUIDTest() {
		assertNotNull(GlobalUtil.getUUID());
		assertThat(32, is(GlobalUtil.getUUID().length()));
		assertThat("Contain char '-'.", GlobalUtil.getUUID(), not(containsString("-")));
		assertThat(GlobalUtil.getUUID(), not(equalTo(GlobalUtil.getUUID())));
	}
}
