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
