package com.genius.core.base.thirdpart;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

/**
 * @author Architect.bian
 * 
 */
public class GuavaTest {

	@Test
	public void joinSomeStrings() {
		ImmutableSet<String> strings = ImmutableSet.of("A", "B", "C");
		String joined = Joiner.on(":").join(strings);
		assertEquals("A:B:C", joined);
	}

	@Test
	public void splitSomeStrings() {
		String string = "A:B:C";

		String[] parts = string.split(":"); // the old way
		String backTogether = Joiner.on(":").join(parts);
		assertEquals(string, backTogether);

		String gorbleString = ": A::: B : C :::";
		Iterable<String> gorbleParts = Splitter.on(":").omitEmptyStrings().trimResults().split(gorbleString);
		String gorbleBackTogether = Joiner.on(":").join(gorbleParts);
		assertEquals(string, gorbleBackTogether); // A:B:C
	}

	@Test
	public void moreFunWithStrings() {
		assertNull(Strings.emptyToNull(""));
		assertEquals("", Strings.nullToEmpty(null));
		assertTrue(Strings.isNullOrEmpty("")); // About the only thing we ever
												// used in commons-lang? :)
		assertEquals("oioioi", Strings.repeat("oi", 3));

		String a = "Too short      ";
		String b = a + " ";
		assertEquals("Too short      ", Strings.padEnd("Too short", a.length(), ' '));
		assertFalse("Too short      ".equals(Strings.padEnd(b, a.length(), ' ')));

		assertEquals(a, Strings.commonPrefix(a, b));
		assertEquals("      ", Strings.commonSuffix(a, b));
	}

	@Test
	public void someSets() {
		Customer bob = new Customer(1, "Bob");
		Customer lisa = new Customer(2, "Lisa");
		Customer stephen = new Customer(3, "Stephen");
		Customer ken = new Customer(null, "Ken");

		ImmutableSet<Customer> customers1 = ImmutableSet.of(bob, lisa, stephen);
		ImmutableSet<Customer> customers2 = ImmutableSet.of(stephen, ken);

		assertEquals(4, Sets.union(customers1, customers2).size());

		assertEquals(ImmutableSet.of(stephen), Sets.intersection(customers1, customers2));
	}

	@Test
	public void shouldTestHowMultimapWorks() throws Exception {
		// given
		Multimap<String, String> multimap = ArrayListMultimap.create();
		// when
		multimap.put("Poland", "Warsaw");
		multimap.put("Poland", "Cracow");
		multimap.put("Poland", "Plock");
		multimap.put("Poland", "Gdansk");

		multimap.put("Germany", "Berlin");
		multimap.put("Germany", "Bremen");
		multimap.put("Germany", "Dortmund");
		multimap.put("Germany", "Koln");

		multimap.put("Portugal", "Lisbone");
		multimap.put("Portugal", "Porto");
		multimap.put("Portugal", "Leira");
		multimap.put("Portugal", "Funchal");
		multimap.put("Portugal", "Funchal");

		// then
		assertEquals(multimap.get("Poland").size(), 4);
		assertEquals(multimap.get("Portugal").size(), 5); // duplicate values
															// are fine
		assertTrue(multimap.get("Poland").contains("Warsaw"));
		assertEquals(multimap.keys().size(), 13); // keys can have duplicates

	}
}

class Customer {
	private Integer id;
	private String name;

	public Customer(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Customer)) {
			return false;
		}
		Customer that = (Customer) obj;
		return Objects.equal(id, that.getId()) && Objects.equal(name, that.getName());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id, name);
	}

	@Override
	public String toString() {
		return name + " (id " + id + ")";
	}

	public Integer getId() {
		return id;
	}

	public boolean isSick() {
		return false;
	}

	public String getAddress() {
		return null;
	}

	public String getName() {
		return name;
	}
}
