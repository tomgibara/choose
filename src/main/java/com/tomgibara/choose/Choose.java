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

import static java.math.BigInteger.valueOf;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

/**
 * Entry point for the API. Provides a method for computing the possible choices
 * of k from n, together with methods for computing the total number of such
 * choices (n choose k).
 * 
 * @author Tom Gibara
 *
 */

public final class Choose {

	private static final BigInteger MAX_LONG_VALUE = BigInteger.valueOf(Long.MAX_VALUE);
	private static final long NO_LONG = -1L;

	/** Chooses elements from a byte array. */
	public static final Choosing<byte[],byte[]>     CHOOSING_BYTES   = new ChoosingBytes();
	/** Chooses elements from a short array. */
	public static final Choosing<short[],short[]>   CHOOSING_SHORTS  = new ChoosingShorts();
	/** Chooses elements from a char array. */
	public static final Choosing<char[],char[]>     CHOOSING_CHARS   = new ChoosingChars();
	/** Chooses elements from an int array. */
	public static final Choosing<int[],int[]>       CHOOSING_INTS    = new ChoosingInts();
	/** Chooses elements from a float array. */
	public static final Choosing<float[],float[]>   CHOOSING_FLOATS  = new ChoosingFloats();
	/** Chooses elements from a long array. */
	public static final Choosing<long[],long[]>     CHOOSING_LONGS   = new ChoosingLongs();
	/** Chooses elements from a double array. */
	public static final Choosing<double[],double[]> CHOOSING_DOUBLES = new ChoosingDoubles();

	/** Chooses characters from a string. */
	public static final Choosing<String,String> CHOOSING_STRING = new ChoosingString();

	/** Chooses a set of elements from list. */
	public static <E> Choosing<List<E>, Set<E>> choosingSet() {
		return new ChoosingSet<E>();
	}

	/**
	 * Computes the number of ways that k items can be selected from a set of n
	 * elements.
	 *
	 * @param n
	 *            the number of elements chosen from
	 * @param k
	 *            the number of elements chosen
	 * @throws IllegalArgumentException
	 *             if either n or k is negative
	 * @return n choose k
	 */

	public static Choose from(int n, int k) throws IllegalArgumentException {
		if (n < 0) throw new IllegalArgumentException("negative n");
		if (k < 0) throw new IllegalArgumentException("negative k");
		return new Choose(n, k);
	}
	
	final int n;
	final int k;
	private final long cLong;
	private BigInteger cInt;
	private Choices choices = null;

	private Choose(int n, int k) {
		this.n = n;
		this.k = k;
		BigInteger cInt;
		long cLong;

		if (n < k) {
			cLong = 0L;
			cInt = BigInteger.ZERO;
		} else if (n == k || k == 0) {
			cLong = 1L;
			cInt = BigInteger.ONE;
		} else {
			final long delta, max;
			if (k < n - k) {
				delta = n - k;
				max = k;
			} else {
				delta = k;
				max = n - k;
			}
	
			cInt = null;
			cLong = delta + 1;
			for (long i = 2; i <= max; i++) {
				long t = cLong * (delta + i) / i;
				if (t >= cLong) {
					cLong = t;
				} else {
					// overflow case
					cInt = valueOf(cLong);
					cLong = NO_LONG;
					for (long j = i; j <= max; j++) {
						cInt = cInt.multiply(valueOf(delta + j)).divide(valueOf(j));
					}
					if (cInt.compareTo(MAX_LONG_VALUE) <= 0) {
						cLong = cInt.longValue();
					}
					break;
				}
			}
		}

		this.cInt = cInt;
		this.cLong = cLong;
	}

	/**
	 * The number of items being chosen from.
	 * 
	 * @return n
	 */

	public int getN() {
		return n;
	}

	/**
	 * The number of items being chosen.
	 * 
	 * @return k
	 */

	public int getK() {
		return k;
	}
	
	/**
	 * Create choices of k items selected from n elements, without regard to
	 * order. The upfront costs of creating new choices may be significant for
	 * large values of n and k.
	 * 
	 * @return the choices
	 */

	public Choices choices() {
		return choices == null ? choices = newChoices() : choices;
	}

	/**
	 * The number of ways that k items can be selected from a set of n
	 * elements.
	 *
	 * @return n choose k
	 */

	public BigInteger asBigInt() {
		return cInt == null ? cInt = BigInteger.valueOf(cLong) : cInt;
	}

	/**
	 * The number of ways that k items can be selected from a set of n elements.
	 * Using this method may avoid the cost of creating a BigInteger at the risk
	 * of overflow.
	 *
	 * @throws IllegalStateException
	 *             if the value of n choose k exceeds Long.MAX_VALUE
	 * @return n choose k
	 */

	//TODO add method to test for longness
	public long asLong() throws IllegalStateException {
		if (cLong == NO_LONG) throw new IllegalStateException("n choose k exceeds Long.MAX_VALUE");
		return cLong;
	}

	private Choices newChoices() {
		if (cLong == 0L) return new ChoicesNone(this);
		BigInteger c = k > n/2 ? from(n,n/2).asBigInt() : asBigInt();
		if (ChoicesPacked.isPackable(n, k)) return new ChoicesPacked(this);
		if (c.compareTo(MAX_LONG_VALUE) <= 0) return new ChoicesLong(this);
		return new ChoicesBigInt(this);
	}
	
	@Override
	public int hashCode() {
		return n * 1337 + k;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof Choose)) return false;
		Choose that = (Choose) obj;
		return this.n == that.n && this.k == that.k;
	}
	
	@Override
	public String toString() {
		return asBigInt() + "(" + n + " choose " + k + ")";
	}
}
