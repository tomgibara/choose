package com.tomgibara.choose;

/**
 * Creates {@link Choosable} instances for choosing k items from a source
 * object.
 * 
 * @author Tom Gibara
 *
 * @param <S>
 *            the type of object that contains the items being chosen
 * @param <T>
 *            the type of object that collates the chosen items
 */

public interface Choosing<S, T> {

	/**
	 * Returns an object for choosing items from a source. The k parameter is
	 * supplied as a hint to allow the returned {@link Choosable} to preallocate
	 * storage for the chosen items. Returned objects may be reused after a call
	 * to their {@link Choosable#chosen()} method.
	 * 
	 * @param k
	 *            the number of items that will be chosen
	 * @return a choosable
	 */

	Choosable<S, T> choosing(int k);

}
