package com.tomgibara.choose.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import com.tomgibara.choose.Choices;
import com.tomgibara.choose.Choose;
import com.tomgibara.choose.Chooser;

public class ChoicesTest {

	@Test
	public void testSmall() {
		testSmall(4, 2);
		testSmall(8, 5);
	}

	private void testSmall(int n, int k) {
		Choose choose = Choose.from(n, k);
		Choices choices = choose.choices();
		Chooser<String, String> chooser = choices.choosing(Choose.CHOOSING_STRING);
		int count = choose.asBigInt().intValue();
		List<String> strs = new ArrayList<String>();
		String string;
		{
			StringBuilder sb = new StringBuilder(n);
			for (int i = 0; i < n; i++) {
				sb.append((char) (97 + i));
			}
			string = sb.toString();
		}
		for (int i = 0; i < count; i++) {
			String str = chooser.choose(i, string);
			assertEquals(k, str.length());
			strs.add(str);
		}
		assertEquals(count, strs.size());
		assertEquals(strs.size(), new HashSet<String>(strs).size());
	}
	
	@Test
	public void testOrderedIndices() {
		Random r = new Random(0);
		for (int n = 1; n < 10; n++) {
			for (int k = 1; k < n; k++) {
				testOrderedIndices(n, k, r);
			}
		}
		for (int n = 1; n < 100; n += 10) {
			for (int k = 1; k < n; k+= 10) {
				testOrderedIndices(n, k, r);
			}
		}
	}
	
	private void testOrderedIndices(int n, int k, Random r) {
		Choices choices = Choose.from(n, k).choices();
		int[] choice = new int[k];
		int[] sorted = new int[k];
		for (int i = 0; i < 100; i++) {
			choices.randomChoiceAsArray(r, choice);
			System.arraycopy(choice, 0, sorted, 0, k);
			Arrays.sort(sorted);
			assertTrue(Arrays.equals(sorted, choice));
		}
	}

	@Test
	public void testNullIndex() {
		Choose.from(3, 2).choices().choiceAsArray(BigInteger.ONE, new int[2]);
		try {
			Choose.from(3, 2).choices().choiceAsArray(null, new int[2]);
			Assert.fail();
		} catch (IllegalArgumentException e) {
			/* expected */
		}
	}
	
	@Test
	public void testNoChoices() {
		try {
			Choose.from(2, 3).choices().randomChoiceAsArray(new Random(), new int[3]);
			Assert.fail();
		} catch (IndexOutOfBoundsException e) {
			/* expected */
		}
	}
}
