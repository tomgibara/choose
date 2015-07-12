Choose
======

A small java library for combinations.

Overview
--------

The binomial coefficient, informally referred to as "n choose k" is a fundamental concept in combinatorics, counting the number of ways that k items can be chosen from n items without repetition and without regard to order.

This library provides convenient way to calculate the a binomial coefficient, and generate and apply the possible choices.

Examples
--------

The library has a fluent yet efficient API.

```java
		// how many ways are there of choosing 7 items from 9?
		long _7_of_9 = Choose.from(9, 7).asLong();
		// 36

		// how many ways are there of choosing 20 items from 70?
		long _20_of_70 = Choose.from(70, 20).asLong();
		// 161884603662657876

		// how many ways are there of choosing 100 items from 200?
		BigInteger _100_of_200 = Choose.from(200, 100).asBigInt();
		// 90548514656103281165404177077484163874504589675413336841320

		// how many ways are there of choosing 200 items from 100?
		BigInteger _200_of_100 = Choose.from(100, 200).asBigInt();
		// 0

		// the first choice in lexical order
		int[] first_3_of_5 = Choose.from(5, 3).choices().choiceAsArray(0);
		// [0, 1, 2]

		// the last choice in lexical order
		int[] last_3_of_5 = Choose.from(5, 3).choices().choiceAsArray(9);
		// [2, 3, 4]

		// choose random characters from a string
		String str = Choose.from(5, 3).choices().choosing(Choose.CHOOSING_STRING).chooseRandom(new Random(0L), "acdef");
		// "cef"

```

Usage
-----

The choose library is available from the Maven central repository:

> Group ID:    `com.tomgibara.choose`
> Artifact ID: `choose`
> Version:     `1.0.0`

The Maven dependency being:

    <dependency>
      <groupId>com.tomgibara.choose</groupId>
      <artifactId>choose</artifactId>
      <version>1.0.0</version>
    </dependency>

Release History
---------------

**2015.07.12** Version 1.0.0

Initial release
