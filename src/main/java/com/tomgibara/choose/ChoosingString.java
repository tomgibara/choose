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


class ChoosingString implements Choosing<String, String> {

	@Override
	public Choosable<String, String> choosing(final int k) {
		return new Choosable<String, String>() {

			String source = null;
			char[] chars = new char[k];

			@Override
			public void from(String source) {
				this.source = source;
			}

			@Override
			public int size() {
				return source.length();
			}

			@Override
			public void take(int from, int to) {
				chars[to] = source.charAt(from);
			}

			@Override
			public String chosen() {
				return new String(chars);
			}

		};
	}

}
