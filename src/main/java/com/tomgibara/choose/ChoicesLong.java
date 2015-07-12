package com.tomgibara.choose;

import java.math.BigInteger;
import java.util.Random;

class ChoicesLong extends ChoicesBase {

	private final long[][] cs;

	ChoicesLong(Choose c) {
		super(c);
		int n = c.n;
		int k = c.k;

		cs = new long[k + 1][n + 1];
		for (int i = 0; i <= k; i++) {
			for (int j = 0; j <= n; j++) {
				cs[i][j] = Choose.from(j,i).asLong();
			}
		}
	}

	@Override
	public void choiceAsArray(long index, int[] array) {
		checkIndex(index);
		checkArray(array);
		choiceAsArrayImpl(index, array);
	}

	@Override
	public void choiceAsArray(BigInteger index, int[] array) {
		checkIndex(index);
		checkArray(array);
		choiceAsArrayImpl(index.longValue(), array);
	}

	@Override
	public void randomChoiceAsArray(Random random, int[] array) {
		checkRandom(random);
		checkArray(array);
		choiceAsArrayImpl(randomIndex(random), array);
	}

	void checkIndex(long index) {
		if (index < 0) throw new IndexOutOfBoundsException("negative index");
		if (index >= c.asLong()) throw new IndexOutOfBoundsException("index too large");
	}

	long randomIndex(Random random) {
		long lc = c.asLong();
		//TODO could cache
		long mask = -1L >>> Long.numberOfLeadingZeros(lc);
		while (true) {
			long r = random.nextLong() & mask;
			if (r >= lc) continue;
			return r;
		}
	}

	void choiceAsArrayImpl(long i, int[] array) {
		int n = c.n;
		int k = c.k;
		int a = n;
		int b = k;
		long x = cs[b][a] /*choose(a, b)*/ - 1 - i;

		for (int j = 0; j < k; j++) {
			a = largest(a, b, x);
			x -= cs[b][a] /* choose(a, b) */;
			array[j] = a;
			b --;
		}

		for (int j = 0; j < k; j++) {
			array[j] = n - 1 - array[j];
		}
	}

	// may only be called from packed extension
	long bits(long i, int nBits) throws IllegalStateException, IndexOutOfBoundsException {
		final long[][] cs = this.cs;
		if (i < 0) throw new IndexOutOfBoundsException();
		if (i >= c.asLong()) throw new IndexOutOfBoundsException();
		int n = c.n;
		int k = c.k;
		int a = n;
		int b = k;
		long x = cs[b][a] /*choose(a, b)*/ - 1 - i;
		long bits = 0L;

		for (int j = 0; j < k; j++) {
			a = largest(a, b, x);
			x -= cs[b][a] /* choose(a, b) */;
			// this yields bits in 'wrong' order...
			//bits = (bits << nBits) | (n - 1 - a);
			//... so we do this, slightly slower to assemble, slightly cheaper to deconstruct
			bits |= (long) (n - 1 - a) << nBits * j;
			b --;
		}

		return bits;
	}

	int largest(int a, int b, long x) {
		int v;
		for (v = a - 1; cs[b][v] /*choose(v, b)*/ > x; v--);
		return v;
	}
}
