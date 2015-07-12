package com.tomgibara.choose;

import java.math.BigInteger;
import java.util.Random;

class ChoicesNone extends ChoicesBase {

	ChoicesNone(Choose c) {
		super(c);
	}

	@Override
	public void choiceAsArray(long index, int[] array) throws IndexOutOfBoundsException {
		noChoices();
	}

	@Override
	public void choiceAsArray(BigInteger index, int[] array) {
		noChoices();
	}

	@Override
	public void randomChoiceAsArray(Random random, int[] array) {
		noChoices();
	}

	private void noChoices() {
		throw new IndexOutOfBoundsException("no choices");
	}
	
}
