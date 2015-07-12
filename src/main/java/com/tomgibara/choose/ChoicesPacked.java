/*
 * Copyright 2015 Tom Gibara
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
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
