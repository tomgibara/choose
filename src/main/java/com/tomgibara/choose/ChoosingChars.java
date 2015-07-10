package com.tomgibara.choose;

import java.util.Arrays;

class ChoosingChars implements Choosing<char[], char[]> {

	@Override
	public Choosable<char[], char[]> choosing(final int k) {
		return new Choosable<char[], char[]>() {

			char[] source = null;
			char[] chosen = new char[k];

			@Override
			public void from(char[] source) {
				this.source = source;
			}

			@Override
			public int size() {
				return source.length;
			}

			@Override
			public void take(int from, int to) {
				chosen[to] = source[from];
			}

			@Override
			public char[] chosen() {
				Arrays.sort(chosen);
				return chosen;
			}

		};
	}

}
