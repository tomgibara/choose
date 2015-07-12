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

/**
 * <p>
 * Aggregates the possible ways of choosing k items from n disregarding order.
 * </p>
 * 
 * <p>
 * The methods on this class which return choices as arrays do so by returning
 * the k indices of each selected item, ranging from 0 through to n-1. The
 * indices are returned in strictly increasing numerical order.
 * </p>
 * 
 * <p>
 * All possible choices are indexed in strict numerical order, so that given two
 * combinations c1 and c2, that first differ at index i, c1[i] &lt; c2[i]
 * implies c1 precedes c2.
 * </p>
 * 
 * @author Tom Gibara
 *
 */

public interface Choices {

	/**
	 * The {@link Choose} that defines the available choices.
	 * 
	 * @return the parameters of the choices
	 */

	Choose getChoose();
	
	/**
	 * Creates chooser that can apply the choices to objects of a specified type
	 * using the supplied choosing.
	 * 
	 * @param choosing
	 *            specifying how choices are applied.
	 * @param <T>
	 *            the type of object from which items are chosen
	 * @param <S>
	 *            the type of object in which choices are returned
	 * @return a new chooser
	 */
	<S,T> Chooser<S,T> choosing(Choosing<S,T> choosing);

	/**
	 * Returns the choice identified by the supplied index.
	 * 
	 * @param index
	 *            the index of the choice
	 * @param array
	 *            an array containing the k chosen indices
	 * @throws IllegalArgumentException
	 *             if the array is null or shorter than the value returned by
	 *             {@link Choose#getK()}
	 * @throws IndexOutOfBoundsException
	 *             if the index is less than zero or exceeds the value returned
	 *             by {@link Choose#asBigInt()}
	 */
	void choiceAsArray(long index, int[] array) throws IllegalArgumentException, IndexOutOfBoundsException;

	/**
	 * Returns the choice identified by the supplied index.
	 * 
	 * @param index
	 *            the index of the choice
	 * @param array
	 *            an array containing the k chosen indices
	 * @throws IllegalArgumentException
	 *             if the array is null or shorter than the value returned by
	 *             {@link Choose#getK()}
	 * @throws IndexOutOfBoundsException
	 *             if the index is less than zero or exceeds the value returned
	 *             by {@link Choose#asBigInt()}
	 */
	void choiceAsArray(BigInteger index, int[] array) throws IllegalArgumentException, IndexOutOfBoundsException;

	/**
	 * Returns a choice of k items from n, chosen uniformly at random from all
	 * such possible choices.
	 * 
	 * @param random
	 *            a source of random data
	 * @param array
	 *            an array containing the k chosen indices
	 * @throws IllegalArgumentException
	 *             if the array is null or shorter than the value returned by
	 *             {@link Choose#getK()}
	 * @throws IllegalStateException
	 *             if there are no choices
	 */
	void randomChoiceAsArray(Random random, int[] array) throws IllegalStateException;

	/**
	 * Returns the choice identified by the supplied index.
	 * 
	 * @param index
	 *            the index of the choice
	 * @return
	 *            an array containing the k chosen indices
	 * @throws IndexOutOfBoundsException
	 *             if the index is less than zero or exceeds the value returned
	 *             by {@link Choose#asBigInt()}
	 */
	int[] choiceAsArray(long index) throws IndexOutOfBoundsException;

	/**
	 * Returns the choice identified by the supplied index.
	 * 
	 * @param index
	 *            the index of the choice
	 * @return
	 *            an array containing the k chosen indices
	 * @throws IndexOutOfBoundsException
	 *             if the index is less than zero or exceeds the value returned
	 *             by {@link Choose#asBigInt()}
	 */
	int[] choiceAsArray(BigInteger index);

	/**
	 * Returns a choice of k items from n, chosen uniformly at random from all
	 * such possible choices.
	 * 
	 * @param random
	 *            a source of random data
	 * @return
	 *            an array containing the k chosen indices
	 * @throws IllegalStateException
	 *             if there are no choices
	 */
	int[] randomChoiceAsArray(Random random);

}
