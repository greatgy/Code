package com.genius.model.base.enums;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.junit.Test;

import com.genius.core.base.utils.StrUtil;

public class EStatusTest {

	@Test
	public final void ToStringtest() {
		assertEquals("1", EStatus.enable.toString());
		assertEquals("0", EStatus.disable.toString());
	}

	@Test
	public final void ValueOfTest() {
		// Status status = Status.enable;
		assertEquals(EStatus.enable, EStatus.get(1));
		assertEquals(EStatus.disable, EStatus.get(0));
	}

	@Test
	public final void testGetName() {
		assertEquals("正常", EStatus.getName(EStatus.enable, Locale.CHINA));
		assertEquals("冻结", EStatus.getName(EStatus.disable, Locale.CHINA));
	}
	
	@Test
	public final void testGetMap() {
		Map<String, String> map = new HashMap<>();
		for (EStatus e : EStatus.values()) {
			map.put(e.toString(), EStatus.getName(e, Locale.CHINA));
			System.out.println(EStatus.getName(e, Locale.CHINA));
		}
		assertEquals(EStatus.values().length, map.size());
	}

	@Test
	public void testEnumToString() {
		EStatus[] arr = new EStatus[3];
		arr[0] = EStatus.disable;
		arr[1] = EStatus.enable;
		arr[2] = EStatus.init;
		StringBuffer sb = new StringBuffer();
		for (EStatus status : arr) {
			sb.append(status.toString());
			sb.append(",");
		}
		System.out.println(StrUtil.trim(sb.toString(), ","));
	}
}
