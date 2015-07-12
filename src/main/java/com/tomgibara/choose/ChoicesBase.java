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

	void choose(BigInteger index, Choosable<?,?> choosable) {
		int[] cs = ints();
		choiceAsArray(index, cs);
		choose(cs, choosable);
	}

	void choose(long index, Choosable<?,?> choosable) {
		int[] cs = ints();
		choiceAsArray(index, cs);
		choose(cs, choosable);
	}

	void choose(Random random, Choosable<?,?> choosable) {
		int[] cs = ints();
		randomChoiceAsArray(random, cs);
		choose(cs, choosable);
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
