/*
 * Copyright 2015 Tom Gibara
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.tomgibara.choose.test;

import static com.tomgibara.choose.Choose.from;
import static java.math.BigInteger.valueOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;

import org.junit.Test;

import com.tomgibara.choose.Choose;

public class ChooseTest {

	private static final BigInteger MAX_LONG_VALUE = BigInteger.valueOf(Long.MAX_VALUE);

	@Test
	public void testChooseAsLong() {
		assertEquals(1, from(0, 0).asLong());
		assertEquals(1, from(1, 1).asLong());
		assertEquals(1, from(1, 0).asLong());
		assertEquals(1, from(2, 0).asLong());
		assertEquals(2, from(2, 1).asLong());
		assertEquals(1, from(2, 2).asLong());
		assertEquals(10 * 9 * 8 * 7 / 4 / 3 / 2 / 1, from(10, 4).asLong());
	}

	@Test
	public void testSymmetry() {
		for (int n = 0; n < 20; n++) {
			for (int k = 0; k <= n; k++) {
				Choose a = from(n, k);
				Choose b = from(n, n - k);
				assertEquals(a.asLong(), b.asLong());
				assertEquals(a.asBigInt(), b.asBigInt());
			}
		}
	}

	@Test
	public void testConsistency() {
		for (int n = 0; n < 20; n++) {
			for (int k = 0; k <= n; k++) {
				Choose choose = from(n,k);
				assertEquals(valueOf(choose.asLong()), choose.asBigInt());
			}
		}
	}

	@Test
	public void testWiderConsistency() {
		for (int n = 1; n < 100; n += 10) {
			for (int k = 1; k < n; k+= 10) {
				Choose choose = from(n,k);
				BigInteger bic = choose.asBigInt();
				long lgc;
				try {
					lgc = choose.asLong();
				} catch (IllegalStateException e) {
					assertTrue(bic.compareTo(MAX_LONG_VALUE) > 0);
					continue;
				}
				assertEquals(n + " choose " + k, bic, valueOf(lgc));
			}
		}
	}

}
