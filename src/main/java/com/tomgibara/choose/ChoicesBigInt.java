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

class ChoicesBigInt extends ChoicesBase {

	private final BigInteger[][] cs;

	ChoicesBigInt(Choose c) {
		super(c);
		int n = c.n;
		int k = c.k;

		cs = new BigInteger[k + 1][n + 1];
		for (int i = 0; i <= k; i++) {
			for (int j = 0; j <= n; j++) {
				//TODO use a more efficient approach
				cs[i][j] = Choose.from(j, i).asBigInt();
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
		int n = c.n;
		int k = c.k;
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
		BigInteger m = c.asBigInt();
		int bits = m.bitCount();
		while (true) {
			BigInteger r = new BigInteger(bits, random);
			if (r.compareTo(m) >= 0) continue;
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
