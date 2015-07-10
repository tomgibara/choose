package com.tomgibara.choose;

import java.util.Arrays;


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
				Arrays.sort(chars);
				return new String(chars);
			}

		};
	}

}
