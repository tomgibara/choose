package com.tomgibara.choose;


class ChoosingFloats implements Choosing<float[], float[]> {

	@Override
	public Choosable<float[], float[]> choosing(final int k) {
		return new Choosable<float[], float[]>() {

			float[] source = null;
			float[] chosen = new float[k];

			@Override
			public void from(float[] source) {
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
			public float[] chosen() {
				return chosen;
			}

		};
	}

}
