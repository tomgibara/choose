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
 * A chooser applies a method of {@link Choosing} to {@link Choices}, allowing items to be chosen from a specific object type.
 *
 * @author Tom Gibara
 *
 * @param <S>
 *            the type of object that contains the items being chosen
 * @param <T>
 *            the type of object that collates the chosen items
 */

public final class Chooser<S,T> {

	private final ChoicesBase choices;
	private final Choosing<S,T> choosing;

	Chooser(ChoicesBase choices, Choosing<S,T> choosing) {
		this.choices = choices;
		this.choosing = choosing;
	}

	/**
	 * The choices that can be made.
	 *
	 * @return the choices
	 */

	public Choices getChoices() {
		return choices;
	}

	/**
	 * Chooses items from the supplied source object.
	 *
	 * @param index
	 *            specifies the choice to be made
	 * @param source
	 *            the object from which items should be selected
	 * @return the chosen items
	 * @throws IndexOutOfBoundsException
	 *             if the index is negative or not less than n choose k
	 * @throws IllegalArgumentException
	 *             if the source does not contain n items
	 */

	public T choose(long index, S source) throws IndexOutOfBoundsException, IllegalArgumentException {
		Choosable<S,T> choosable = choosable(source);
		choices.choose(index, choosable);
		return choosable.chosen();
	}

	/**
	 * Chooses items from the supplied source object.
	 *
	 * @param index
	 *            specifies the choice to be made
	 * @param source
	 *            the object from which items should be selected
	 * @return the chosen items
	 * @throws IndexOutOfBoundsException
	 *             if the index is negative or not less than n choose k
	 * @throws IllegalArgumentException
	 *             if the source does not contain n items
	 */

	public T choose(BigInteger index, S source) throws IndexOutOfBoundsException, IllegalArgumentException {
		Choosable<S,T> choosable = choosable(source);
		choices.choose(index, choosable);
		return choosable.chosen();
	}

	/**
	 * Makes a random choice of items from the supplied source object.
	 *
	 * @param random
	 *            a source of random data
	 * @param source
	 *            the object from which items should be selected
	 * @return the chosen items
	 * @throws IllegalArgumentException
	 *             if the source does not contain n items
	 */

	public T chooseRandom(Random random, S source) throws IllegalArgumentException {
		Choosable<S,T> choosable = choosable(source);
		choices.choose(random, choosable);
		return choosable.chosen();
	}

	private Choosable<S,T> choosable(S source) {
		if (source == null) throw new IllegalArgumentException("null source");
		Choosable<S,T> choosable = choosing.choosing(choices.c.k);
		choosable.from(source);
		int n = choices.c.n;
		int s = choosable.size();
		if (s != n) throw new IllegalArgumentException("choosable size is " + s + " but expected " + n);
		return choosable;
	}
}
