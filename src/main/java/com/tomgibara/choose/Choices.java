package com.tomgibara.choose;

import java.math.BigInteger;
import java.util.Random;

public interface Choices {

	int getN();

	int getK();

	BigInteger getNumberOfChoices();

	<S,T> Chooser<S,T> choosing(Choosing<S,T> choosing);

	void choiceAsArray(long index, int[] array);

	void choiceAsArray(BigInteger index, int[] array);

	void randomChoiceAsArray(Random random, int[] array);

}
