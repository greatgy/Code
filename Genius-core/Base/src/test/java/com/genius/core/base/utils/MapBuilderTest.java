package com.genius.core.base.utils;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

public class MapBuilderTest {

	@Test
	public void testStart() {
		MapBuilder builder = MapBuilder.start();
		assertNotNull(builder);
		String key = "k";
		String value= "v";
		builder.add(key, value);
		assertTrue(builder.get().get(key).equals(value));
	}

	@Test
	public void testAdd() {
		String key = "k";
		String value= "v";
		String key2 = "k2";
		String value2= "v2";
		Map<?, ?> map = MapBuilder.start(key, value).add(key2, value2).get();
		assertTrue(map.size() == 2);
		assertTrue(map.get(key).equals(value));
		assertTrue(map.get(key2).equals(value2));
	}

}
