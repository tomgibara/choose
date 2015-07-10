package com.tomgibara.choose;

import java.math.BigInteger;
import java.util.Random;

public class ChoicesBigInt extends ChoicesBase {

	private final BigInteger[][] cs;

	public ChoicesBigInt(int n, int k, BigInteger c) {
		super(n, k, c);

		cs = new BigInteger[k + 1][n + 1];
		for (int i = 0; i <= k; i++) {
			for (int j = 0; j <= n; j++) {
				//TODO use a more efficient approach
				cs[i][j] = Choose.asBigInt(j, i);
			}
		}
	}

	@Override
	public void choiceAsArray(long index, int[] array) {
		choiceAsArray(BigInteger.valueOf(index), array);
	}

	@Override
	public void choiceAsArray(BigInteger m, int[] as) {
		final BigInteger[][] cs = this.cs;
		if (m.signum() < 0) throw new IndexOutOfBoundsException();
		if (m.compareTo(cs[k][n] /* choose(n, k) */) >= 0) throw new IndexOutOfBoundsException();
		if (as.length < k) throw new IllegalArgumentException();

		int a = n;
		int b = k;
		BigInteger x = (cs[b][a] /*choose(a, b)*/).subtract(BigInteger.ONE).subtract(m);

		for (int j = 0; j < k; j++) {
			a = largest(a, b, x);
			x = x.subtract(cs[b][a] /* choose(a, b) */);
			as[j] = a;
			b --;
		}

		for (int j = 0; j < k; j++) {
			as[j] = n - 1 - as[j];
		}
	}

	@Override
	public void randomChoiceAsArray(Random random, int[] array) {
		int bits = c.bitCount();
		while (true) {
			BigInteger r = new BigInteger(bits, random);
			if (r.compareTo(c) >= 0) continue;
			choiceAsArray(r, array);
			return;
		}
	}

	private int largest(int a, int b, BigInteger x) {
		int v;
		for (v = a - 1; x.compareTo(cs[b][v] /*choose(v, b)*/) < 0; v--);
		return v;
	}
}
