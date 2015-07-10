package com.tomgibara.choose;

import java.math.BigInteger;
import java.util.Random;

public final class Chooser<S,T> {

	private final ChoicesBase choices;
	private final Choosing<S,T> choosing;
	
	Chooser(ChoicesBase choices, Choosing<S,T> choosing) {
		this.choices = choices;
		this.choosing = choosing;
	}
	
	public Choices getChoices() {
		return choices;
	}

	public T choose(long index, S source) throws IndexOutOfBoundsException, IllegalArgumentException {
		Choosable<S,T> choosable = choosable(source);
		choices.choose(index, choosable);
		return choosable.chosen();
	}

	public T choose(BigInteger index, S source) throws IndexOutOfBoundsException, IllegalArgumentException {
		Choosable<S,T> choosable = choosable(source);
		choices.choose(index, choosable);
		return choosable.chosen();
	}

	public T chooseRandom(Random random, S source) throws IndexOutOfBoundsException, IllegalArgumentException {
		Choosable<S,T> choosable = choosable(source);
		choices.choose(random, choosable);
		return choosable.chosen();
	}

	private Choosable<S,T> choosable(S source) {
		if (source == null) throw new IllegalArgumentException("null source");
		Choosable<S,T> choosable = choosing.choosing(choices.k);
		choosable.from(source);
		int n = choices.n;
		int s = choosable.size();
		if (s != n) throw new IllegalArgumentException("choosable size is " + s + " but expected " + n);
		return choosable;
	}
}
