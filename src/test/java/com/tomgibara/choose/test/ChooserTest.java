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
