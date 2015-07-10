package com.tomgibara.choose.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
		Choices choices = Choose.from(n, k);
		Chooser<String, String> chooser = choices.choosing(Choose.CHOOSING_STRING);
		int count = choices.getNumberOfChoices().intValue();
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
		assertEquals(Choose.asLong(n, k), strs.size());
		assertEquals(strs.size(), new HashSet<String>(strs).size());
	}
}
