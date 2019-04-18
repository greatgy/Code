package com.genius.xo.mongodb.model;

import static org.junit.Assert.*;
import java.util.Map;

import org.joda.time.DateTime;
import org.junit.Test;

import com.genius.core.base.annotation.Maps;
import com.genius.core.base.utils.MapsUtil;
import com.genius.xo.mongodb.mock.model.Address;
import com.genius.xo.mongodb.mock.model.Library;
import com.genius.xo.mongodb.mock.testconstants.TestConst;

public class LibraryTest {

	@Test
	public void MapsUtilTest() {
		Address address = new Address("china", "beijing", "haidian", "shangdi", "park");
		Library data = new Library();
		data.setUid(TestConst.uid);
		data.setName(TestConst.name);
		data.setVisitorCount(100);
		data.setAddress(address);
		data.setFoundTime(DateTime.now().minusDays(2));
		Map<String, Object> map = MapsUtil.toMap(data, Maps.dbStrategy);
		System.out.println(map);
		assertEquals(TestConst.uid, map.get("_id"));
		Library library = MapsUtil.fromMap(map, Library.class, Maps.dbStrategy);
		assertEquals(TestConst.uid, library.getUid());
		assertEquals(TestConst.name, library.getName());
		assertEquals(data.getVisitorCount(), library.getVisitorCount());
		assertEquals(data.getFoundTime().getMillis(), library.getFoundTime().getMillis());
		assertEquals(address, library.getAddress());
	}
}
