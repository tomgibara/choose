package com.tomgibara.choose;

import java.math.BigInteger;
import java.util.Random;

class ChoicesPacked extends ChoicesLong {

	// statics
	
	static boolean isPackable(int n, int k) {
		int bits = 32 - Integer.numberOfLeadingZeros(n);
		return bits * k <= 64;
	}
	
	// fields

	private final int b; // number of bits in n
	private final int m; // mask of least b bits

	// constructors

	public ChoicesPacked(Choose c) {
		super(c);
		b = 32 - Integer.numberOfLeadingZeros(c.n);
		m = (1 << b) - 1;
	}

	// choices methods

	@Override
	void choose(long index, Choosable<?,?> choosable) {
		checkIndex(index);
		long bits = bits(index, b);
		chooseBits(bits, choosable);

	}

	@Override
	void choose(BigInteger index, Choosable<?,?> choosable) {
		checkIndex(index);
		long bits = bits(index.longValue(), b);
		chooseBits(bits, choosable);
	}

	@Override
	void choose(Random random, Choosable<?,?> choosable) {
		checkRandom(random);
		long bits = bits(randomIndex(random), b);
		chooseBits(bits, choosable);
	}

	// package scoped methods

	private void chooseBits(long bits, Choosable<?,?> choosable) {
		int k = c.k;
		for (int j = 0; j < k; j++) {
			int i = (int) (bits & m);
			choosable.take(i, j);
			bits >>>= b;
		}
	}

}
