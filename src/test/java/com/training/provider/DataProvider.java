package com.training.provider;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

public class DataProvider {

	public static Stream<Arguments> provideDataForTestPowerMethod() {

		return Stream.of(Arguments.of(2, 3, 8), Arguments.of(3, 3, 27), Arguments.of(5, 2, 25), Arguments.of(9, 2, 81));
	}
	
	public static Stream<Arguments> proideDataForTestIsOddMethod() {
		
		return Stream.of(Arguments.of(1), Arguments.of(7), Arguments.of(-27), Arguments.of(99));
	}
}
