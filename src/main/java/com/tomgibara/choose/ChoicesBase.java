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

abstract class ChoicesBase implements Choices {

	final Choose c;

	ChoicesBase(Choose c) {
		this.c = c;
	}

	@Override
	public Choose getChoose() {
		return c;
	}

	public <S,T> Chooser<S,T> choosing(Choosing<S,T> choosing) {
		if (choosing == null) throw new IllegalArgumentException("null choosing");
		return new Chooser<S,T>(this, choosing);
	}

	@Override
	public int[] choiceAsArray(long index) throws IndexOutOfBoundsException {
		int[] cs = ints();
		choiceAsArray(index, cs);
		return cs;
	}

	@Override
	public int[] choiceAsArray(BigInteger index) {
		int[] cs = ints();
		choiceAsArray(index, cs);
		return cs;
	}

	@Override
	public int[] randomChoiceAsArray(Random random) {
		int[] cs = ints();
		randomChoiceAsArray(random, cs);
		return cs;
	}

	void choose(BigInteger index, Choosable<?,?> choosable) {
		choose(choiceAsArray(index), choosable);
	}

	void choose(long index, Choosable<?,?> choosable) {
		choose(choiceAsArray(index), choosable);
	}

	void choose(Random random, Choosable<?,?> choosable) {
		choose(randomChoiceAsArray(random), choosable);
	}

	void checkIndex(BigInteger index) {
		if (index == null) throw new IllegalArgumentException("null index");
		if (index.signum() < 0) throw new IndexOutOfBoundsException("negative index");
		//TODO should cache c.asBigInt()?
		if (index.compareTo(c.asBigInt()) >= 0) throw new IndexOutOfBoundsException("index too large");
	}

	void checkArray(int[] array) {
		if (array == null) throw new IllegalArgumentException("null array");
		if (array.length < c.k) throw new IllegalArgumentException("array too short");
	}

	void checkRandom(Random random) {
		if (random == null) throw new IllegalArgumentException("null random");
	}

	private int[] ints() {
		return new int[c.k];
	}

	private void choose(int[] cs, Choosable<?,?> choosable) {
		for (int i = 0; i < cs.length; i++) {
			choosable.take(cs[i], i);
		}
	}

}
