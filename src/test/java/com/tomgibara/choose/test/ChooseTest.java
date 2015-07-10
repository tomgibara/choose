package com.tomgibara.choose.test;

import static com.tomgibara.choose.Choose.asBigInt;
import static com.tomgibara.choose.Choose.asLong;
import static java.math.BigInteger.valueOf;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ChooseTest {

	@Test
	public void testChooseAsLong() {
		assertEquals(1, asLong(0, 0));
		assertEquals(1, asLong(1, 1));
		assertEquals(1, asLong(1, 0));
		assertEquals(1, asLong(2, 0));
		assertEquals(2, asLong(2, 1));
		assertEquals(1, asLong(2, 2));
		assertEquals(10 * 9 * 8 * 7 / 4 / 3 / 2 / 1, asLong(10, 4));
	}

	@Test
	public void testSymmetry() {
		for (int n = 0; n < 20; n++) {
			for (int k = 0; k <= n; k++) {
				assertEquals(asLong(n, k), asLong(n, n - k));
			}
		}
	}

	@Test
	public void testConsistency() {
		for (int n = 0; n < 20; n++) {
			for (int k = 0; k <= n; k++) {
				assertEquals(valueOf(asLong(n, k)), asBigInt(n, k));
			}
		}
	}

}
