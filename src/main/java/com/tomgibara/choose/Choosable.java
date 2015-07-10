package com.tomgibara.choose;

public interface Choosable<S,T> {

	// always called first
	void from(S source);

	// called to determine size of source
	int size();

	// called k times in strict succession
	void take(int from, int to);

	// always called last - may be reused after call
	T chosen();

}