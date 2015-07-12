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
}
