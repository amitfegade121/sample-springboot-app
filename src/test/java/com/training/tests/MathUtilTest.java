package com.training.tests;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.training.app.MathUtil;

public class MathUtilTest {
	
	MathUtil math;
	
	@BeforeEach
	public void setUp() {
		math = new MathUtil();
	}
	
	@Test
	public void testAdditionWithTwoPositiveIntegers() {
		assertEquals(9, math.addition(5, 4));	
	}
	
	@Test
	public void testAddition() {
		assertEquals(9, math.addition(5,4));
		assertEquals(-9, math.addition(-4, -5));
		assertEquals(12, math.addition(15, -3));
		assertEquals(12, math.addition(12, 0));
	}
	
	@Test
	public void testMultiplication() {
		
//		Executable e1 = () -> assertEquals(20, math.multiplication(4, 5));
//		Executable e2 = () -> assertEquals(15, math.multiplication(-3, -5));
//		Executable e3 = () -> assertEquals(0, math.multiplication(0, 4));
//		Executable e4 = () -> assertEquals(-15, math.multiplication(-3, 5));
//		
//		assertAll(e1, e2, e3, e4);
		
		assertAll(() -> assertEquals(20, math.multiplication(4, 5)),
				  () -> assertEquals(15, math.multiplication(-3, -5)),
				  () -> assertEquals(0, math.multiplication(0, 4)),
				  () -> assertEquals(-15, math.multiplication(-3, 5)));
		
	}
	
	@ParameterizedTest
	@ValueSource(ints = {3, 5, 7, -11, -13, 17})
	public void testOddReturnsTrueForOddNumber(int n) {		
		assumeTrue(n > 0);
		assertTrue(math.isOdd(n));
	}
	
	@ParameterizedTest
	@ValueSource(ints = {2, 4, 6, 8, 24, 36})
	public void testOddReturnsFalseForEvenNumber(int n) {
		assumeTrue(n > 0);
		assertFalse(math.isOdd(n));
	}
	
	
	@ParameterizedTest
	@NullSource
	@EmptySource
	@ValueSource(strings = {"", "   "})
	public void testEmptyString(String s) {
		assertTrue(math.isBlank(s));
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/input_data.csv", numLinesToSkip = 1, delimiter = ',')
	public void testPower(int x, int y, long result) {
		
		assertEquals(result, math.power(x, y));
	}
	
	@ParameterizedTest
	@CsvSource(value = {			
			"2,3,8",
			"2,0,1",
			"2,5,32",
			"3,3,27"
	})
	public void testPower_2(int x, int y, long result) {
		
		assertEquals(result, math.power(x, y));
	}
	
	
	@ParameterizedTest
	@MethodSource("getTestData")
	public void testPower_3(int x, int y, long result) {

		assertEquals(result, math.power(x, y));
	}
		
	public static List getTestData() {
		return Arrays.asList(new Object[][] {
								 {2, 3, 8},
								 {2, 0, 1},
								 {3, 3, 27},
								 {2, 5, 32}
		                     });
	}
	

}













