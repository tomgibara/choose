package com.tomgibara.choose;

public interface Choosing<S, T> {

	Choosable<S, T> choosing(int k);

}
