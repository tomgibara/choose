package com.tomgibara.choose;


class ChoosingLongs implements Choosing<long[], long[]> {

	@Override
	public Choosable<long[], long[]> choosing(final int k) {
		return new Choosable<long[], long[]>() {

			long[] source = null;
			long[] chosen = new long[k];

			@Override
			public void from(long[] source) {
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
			public long[] chosen() {
				return chosen;
			}

		};
	}

}
