package com.tomgibara.choose;


class ChoosingDoubles implements Choosing<double[], double[]> {

	@Override
	public Choosable<double[], double[]> choosing(final int k) {
		return new Choosable<double[], double[]>() {

			double[] source = null;
			double[] chosen = new double[k];

			@Override
			public void from(double[] source) {
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
			public double[] chosen() {
				return chosen;
			}

		};
	}

}
