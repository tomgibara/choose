package com.tomgibara.choose;

/**
 * Performs the selection of items from one type of object <code>S</code> into
 * another <code>T</code>. It is frequently the case that these two types are
 * the same.
 * 
 * @author Tom Gibara
 *
 * @param <S>
 *            the type of object that contains the items being chosen
 * @param <T>
 *            the type of object that collates the chosen items
 */

public interface Choosable<S,T> {

	/**
	 * Supplies the object from which items are to be chosen. This method on the
	 * interface will always be called first.
	 * 
	 * @param source
	 *            the object that contains the items being chosen, never null
	 */

	void from(S source);

	/**
	 * Returns the number of items stored by the object supplied to the
	 * {@link #from(Object)} method.
	 * 
	 * @return the number of items in the source object
	 */

	int size();

	/**
	 * Called exactly k times in strict succession to select k items from the
	 * source object. The first parameter supplies the index of the item being
	 * chosen. The second parameter is supplied as a convenience and may be
	 * ignored; it spans <code>0,..,k-1</code>.
	 * 
	 * @param from
	 *            the index of the item being chosen
	 * @param to
	 *            a unique index to which the chosen item may be recorded
	 */

	void take(int from, int to);

	/**
	 * Supplies the final object that contains the chosen items. This method on
	 * the interface is guaranteed to be called last. Implementations are free
	 * to reuse the {@link Choosable} after this call.
	 * 
	 * @return the chosen items
	 */
	T chosen();

}