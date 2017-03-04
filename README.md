# timebomb
[![Build Status](https://travis-ci.org/tonicsoft/timebomb.svg?branch=master)](https://travis-ci.org/tonicsoft/timebomb)

Simple test utility for ignoring a unit test for a limited period of time.

# download
`timebomb` is available from the [Maven Central Repository](https://search.maven.org/#search%7Cga%7C1%7Cg%3A%22org.tonicsoft.timebomb%22). Junit and TestNG are the supported test frameworks. To use timebomb, simply declare a dependency on one of the following:
 - Junit: [org.tonicsoft.timebomb:timebomb-junit](https://search.maven.org/#search%7Cga%7C1%7Cg%3A%22org.tonicsoft.timebomb%22%20AND%20a%3A%22timebomb-junit%22)
 - TestNG: [org.tonicsoft.timebomb:timebomb-testng](https://search.maven.org/#search%7Cga%7C1%7Cg%3A%22org.tonicsoft.timebomb%22%20AND%20a%3A%22timebomb-testng%22)

## What is a Time Bomb?
The obvious drawback of using your test framework's "ignore" functionality directly is that the test may be forgotten about and remain ignored indefinitely. A Time Bomb will allow the test to be ignored until a specified time, after which the test will fail, reminding you to return to it.

## Usage

The Time Bomb comes in two main flavours:

 - `TimeBomb.blowUpAfter(...)` - Run the test normally until the given point in time, after which the test will fail.
 - `TimeBomb.ignoreUntil(...)` - Ignore the test (i.e. code will not execute) until the given point in time, after which the test will fail.

##  Example
```java
	@Test
	public void myTest() {
	    // This will do nothing until 2018-01-01, after which it will
		// throw a RuntimeException, causing the test to fail
	    TimeBomb.blowUpAfter(new LocalDate(2018,1,1));

		//This code will continue to run until 2018-01-01
		...
		assertThat(...)
	}

	@Test
	public void anotherTest() {
	    // This will throw an exception (depending on test framework)
		// until 2018-01-01 causing the test to be ignored, after which
		// it will throw a RuntimeException causing the test to fail.
		TimeBomb.ignoreUntil(new LocalDate(2018,1,1));

		//This code will not run until the TimeBomb is removed
		...
		assertThat(...)
	}
```