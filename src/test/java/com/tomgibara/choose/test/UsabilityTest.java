package com.tomgibara.choose.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.util.Random;

import org.junit.Test;

import com.tomgibara.choose.Choose;

public class UsabilityTest {

	@Test
	public void testUsability() {

		// how many ways are there of choosing 7 items from 9?
		long _7_of_9 = Choose.from(9, 7).asLong();
		// 36

		// how many ways are there of choosing 40 items from 70?
		long _20_of_70 = Choose.from(70, 20).asLong();
		// 161884603662657876

		// how many ways are there of choosing 100 items from 200?
		BigInteger _100_of_200 = Choose.from(200, 100).asBigInt();
		// 90548514656103281165404177077484163874504589675413336841320

		// how many ways are there of choosing 200 items from 100?
		BigInteger _200_of_100 = Choose.from(100, 200).asBigInt();
		// 0

		// the first choice in lexical order
		int[] first_3_of_5 = Choose.from(5, 3).choices().choiceAsArray(0);
		// [0, 1, 2]

		// the last choice in lexical order
		int[] last_3_of_5 = Choose.from(5, 3).choices().choiceAsArray(9);
		// [2, 3, 4]

		// choose random characters from a string
		String str = Choose.from(5, 3).choices().choosing(Choose.CHOOSING_STRING).chooseRandom(new Random(0L), "acdef");
		// "cef"

		assertEquals(36, _7_of_9);
		assertEquals(161884603662657876L, _20_of_70);
		assertEquals(new BigInteger("90548514656103281165404177077484163874504589675413336841320"), _100_of_200);
		assertEquals(BigInteger.ZERO, _200_of_100);
		assertArrayEquals(new int[] {0, 1, 2}, first_3_of_5);
		assertArrayEquals(new int[] {2, 3, 4}, last_3_of_5);
		assertEquals("cef", str);
	}

}
