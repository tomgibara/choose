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


class ChoosingChars implements Choosing<char[], char[]> {

	@Override
	public Choosable<char[], char[]> choosing(final int k) {
		return new Choosable<char[], char[]>() {

			char[] source = null;
			char[] chosen = new char[k];

			@Override
			public void from(char[] source) {
				this.source = source;
			}

			@Override
			public int size() {
				return source.length;
			}

			@Override
			public void take(int from, int to) {
				chosen[to] = source[from];
			}

			@Override
			public char[] chosen() {
				return chosen;
			}

		};
	}

}
