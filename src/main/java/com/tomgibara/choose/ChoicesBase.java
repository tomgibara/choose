package com.tomgibara.choose;

import java.math.BigInteger;
import java.util.Random;

public abstract class ChoicesBase implements Choices {

	final int n;
	final int k;
	final BigInteger c;
	
	public ChoicesBase(int n, int k, BigInteger c) {
		this.n = n;
		this.k = k;
		this.c = c;
	}
	
	@Override
	public int getN() {
		return n;
	}
	
	@Override
	public int getK() {
		return k;
	}
	
	@Override
	public BigInteger getNumberOfChoices() {
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
		if (index.signum() < 0) throw new IndexOutOfBoundsException("negative index");
		if (index.compareTo(c) >= 0) throw new IndexOutOfBoundsException("index too large");
	}
	
	void checkArray(int[] array) {
		if (array == null) throw new IllegalArgumentException("null array");
		if (array.length < k) throw new IllegalArgumentException("array too short");
	}
	
	void checkRandom(Random random) {
		if (random == null) throw new IllegalArgumentException("null random");
	}
	
	private int[] ints() {
		return new int[k];
	}

	private void choose(int[] cs, Choosable<?,?> choosable) {
		for (int i = 0; i < cs.length; i++) {
			choosable.take(cs[i], i);
		}
	}
	
}
