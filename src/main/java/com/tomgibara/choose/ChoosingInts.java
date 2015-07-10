package com.tomgibara.choose;

import java.util.Arrays;

class ChoosingInts implements Choosing<int[], int[]> {

	@Override
	public Choosable<int[], int[]> choosing(final int k) {
		return new Choosable<int[], int[]>() {

			int[] source = null;
			int[] chosen = new int[k];

			@Override
			public void from(int[] source) {
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
			public int[] chosen() {
				Arrays.sort(chosen);
				return chosen;
			}

		};
	}

}
