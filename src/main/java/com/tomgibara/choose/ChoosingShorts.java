package com.tomgibara.choose;


class ChoosingShorts implements Choosing<short[], short[]> {

	@Override
	public Choosable<short[], short[]> choosing(final int k) {
		return new Choosable<short[], short[]>() {

			short[] source = null;
			short[] chosen = new short[k];

			@Override
			public void from(short[] source) {
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
			public short[] chosen() {
				return chosen;
			}

		};
	}

}
