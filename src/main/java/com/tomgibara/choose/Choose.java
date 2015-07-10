package com.tomgibara.choose;

import static java.math.BigInteger.valueOf;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

public class Choose {

	private static final BigInteger MAX_LONG_VALUE = BigInteger.valueOf(Long.MAX_VALUE);

	public static final Choosing<byte[],byte[]>     CHOOSING_BYTES   = new ChoosingBytes();
	public static final Choosing<short[],short[]>   CHOOSING_SHORTS  = new ChoosingShorts();
	public static final Choosing<char[],char[]>     CHOOSING_CHARS   = new ChoosingChars();
	public static final Choosing<int[],int[]>       CHOOSING_INTS    = new ChoosingInts();
	public static final Choosing<float[],float[]>   CHOOSING_FLOATS  = new ChoosingFloats();
	public static final Choosing<long[],long[]>     CHOOSING_LONGS   = new ChoosingLongs();
	public static final Choosing<double[],double[]> CHOOSING_DOUBLES = new ChoosingDoubles();
	
	public static final Choosing<String,String> CHOOSING_STRING = new ChoosingString();
	
	public static <E> Choosing<List<E>, Set<E>> choosingSet() {
		return new ChoosingSet<E>();
	}
	
	/**
	 * Create choices of k items selected from n elements, without regard to
	 * order. The upfront costs of creating new choices may be significant for
	 * large values of n and k.
	 * 
	 * @param n
	 *            the number of elements chosen from
	 * @param k
	 *            the number of elements chosen
	 * 
	 * @return the choices
	 */

	public static Choices from(int n, int k) {
		if (k < 1) throw new IllegalArgumentException();
		if (k > n || n == Integer.MAX_VALUE) throw new IllegalArgumentException();
		
		BigInteger c = asBigInt(n, k);
		int bits = 32 - Integer.numberOfLeadingZeros(n);
		if (bits * k <= 64) return new ChoicesPacked(n, k, c);
		if (c.compareTo(MAX_LONG_VALUE) <= 0) return new ChoicesLong(n, k, c);
		return new ChoicesBigInt(n, k, c);
	}
	
	/**
	 * Computes the number of ways that k items can be selected from a set of n
	 * elements.
	 * 
	 * @param n
	 *            the number of elements chosen from
	 * @param k
	 *            the number of elements chosen
	 * @return n choose k
	 */
	
	public static BigInteger asBigInt(int n, int k) {
		if (n < 0) throw new IllegalArgumentException(); 
		if (k < 0) throw new IllegalArgumentException(); 
		if (n < k) return BigInteger.ZERO;
		if (k == n || k == 0) return BigInteger.ONE;
		
		final long delta, max;
		if (k < n - k) {
			delta = n - k;
			max = k;
		} else {
			delta = k;
			max = n - k;
		}
		
		BigInteger c = valueOf(delta + 1);
		for (long i = 2; i <= max; i++) {
			 c  = c.multiply(BigInteger.valueOf(delta + i)).divide(valueOf(i));
		}
		
		return c;
	}
	
	/**
	 * Computes the number of ways that k items can be selected from a set of n
	 * elements. This method provides much better performance than
	 * {@link #asBigInt(int, int)} but may overflow.
	 * 
	 * @param n
	 *            the number of elements chosen from
	 * @param k
	 *            the number of elements chosen
	 * @return n choose k
	 */
	
	public static long asLong(int n, int k) {
		if (n < 0) throw new IllegalArgumentException(); 
		if (k < 0) throw new IllegalArgumentException(); 
		if (n < k) return 0;
		if (k == n || k == 0) return 1;
		
		final long delta, max;
		if (k < n - k) {
			delta = n - k;
			max = k;
		} else {
			delta = k;
			max = n - k;
		}
		
		long c = delta + 1;
		for (long i = 2; i <= max; i++) {
			 c  = c * (delta + i) / i;
		}
		
		return c;
	}
	
}
