package com.tomgibara.choose;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


class ChoosingSet<E> implements Choosing<List<E>, Set<E>> {

	@Override
	public Choosable<List<E>, Set<E>> choosing(final int k) {
		return new Choosable<List<E>, Set<E>>() {

			List<E> source = null;
			Set<E> target = new HashSet<E>(k);

			@Override
			public void from(List<E> source) {
				this.source = source;
			}

			@Override
			public int size() {
				return source.size();
			}

			@Override
			public void take(int from, int to) {
				target.add(source.get(from));
			}

			@Override
			public Set<E> chosen() {
				return target;
			}

		};
	}

}
