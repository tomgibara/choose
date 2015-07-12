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

import java.math.BigInteger;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import com.tomgibara.choose.Choose;

public class ChooserTest {

	@Test
	public void testNullIndex() {
		Choose.from(3, 2).choices().choosing(Choose.CHOOSING_INTS).choose(BigInteger.ONE, new int[3]);
		try {
			Choose.from(3, 2).choices().choosing(Choose.CHOOSING_INTS).choose(null, new int[3]);
			Assert.fail();
		} catch (IllegalArgumentException e) {
			/* expected */
		}
	}
	
	@Test
	public void testNoChoices() {
		try {
			Choose.from(2, 3).choices().choosing(Choose.CHOOSING_INTS).chooseRandom(new Random(), new int[2]);
			Assert.fail();
		} catch (IndexOutOfBoundsException e) {
			/* expected */
		}
	}
	
	@Test
	public void testConsistency() {
		Random r = new Random(0L);
		String str = "abcdefghijklmnopqrstuvwxyz012345";
		for (int count = 0; count < 1000; count++) {
			int n = r.nextInt(str.length());
			int k = r.nextInt(n + 1);
			String s = str.substring(0, n);

			Choose choose = Choose.from(n, k);
			int c = (int) choose.asLong();
			int i = r.nextInt(c);

			String t = choose.choices().choosing(Choose.CHOOSING_STRING).choose(i, s);
			int[] cs = choose.choices().choiceAsArray(i);
			StringBuilder sb = new StringBuilder(k);
			for (int j = 0; j < k; j++) {
				sb.append(s.charAt(cs[j]));
			}
			Assert.assertEquals(n + " choose " + k, t, sb.toString());
		}
	}
}
