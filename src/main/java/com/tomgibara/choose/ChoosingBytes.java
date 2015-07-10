package com.tomgibara.choose;

import java.util.Arrays;

class ChoosingBytes implements Choosing<byte[], byte[]> {

	@Override
	public Choosable<byte[], byte[]> choosing(final int k) {
		return new Choosable<byte[], byte[]>() {

			byte[] source = null;
			byte[] chosen = new byte[k];

			@Override
			public void from(byte[] source) {
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
			public byte[] chosen() {
				Arrays.sort(chosen);
				return chosen;
			}

		};
	}

}
