package com.training.tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.training.Calculator;
import com.training.Month;

// @EnabledOnOs(OS.WINDOWS)
// @DisabledOnOs(OS.LINUX)
// @EnabledOnJre(JRE.JAVA_8)
// @EnabledIfEnvironmentVariable(named = "PROCESSOR_ARCHITECTURE", matches = "[A-Z]*64")
//@EnabledIfSystemProperty(named = "java.version", matches = "1.8.0_281")
public class TestCalculator {
	
	@Test
	  public void testAdditionWithTwoPositiveNumbers() {
		  	
		   assertEquals(30, Calculator.addition(10, 20));
	  }
	
	@Test	
	public void testAdditionWithTwoNegativeNumbers() {
			
		assertEquals(-20, Calculator.addition(-16, -4));
		
	}
	
	@Test
	public void testSubtraction() {
			
//		Executable e1 = () -> assertEquals(-1, Calculator.subtraction(4, 5));
//		Executable e2 = () -> assertEquals(5, Calculator.subtraction(12, 7));
//		Executable e3 = () -> assertEquals(-12, Calculator.subtraction(-17, -5));
//		Executable e4 = () -> assertEquals(16, Calculator.subtraction(4, -12));
//		 
//		 assertAll(e1, e2, e3, e4);
		
		assertAll(() -> assertEquals(-1, Calculator.subtraction(4, 5)),
				      () -> assertEquals(5, Calculator.subtraction(12, 7)),
				      () -> assertEquals(-12, Calculator.subtraction(-17, -5)),
				      () -> assertEquals(16, Calculator.subtraction(4, -12)));
	}
	
	@Test
	public void testAdditionWithOnePositiveAndOneNegativeNumber() {
		
		// Map<String, String> envs = System.getenv();
		// Properties props = System.getProperties();
	    // System.setProperty("name", "value");	
		// System.getProperties().forEach((k, v) -> System.out.println(k + " => " + v ));
		
		assertEquals(12, Calculator.addition(16, -4));
	}
	
	@Test
	public void testDivisionWithZeroDivisor() {
		
		assertThrows(ArithmeticException.class, () -> Calculator.division(10, 0));		
	}
	
	@Test
	public void testPower() {
		
		assertEquals(81, Calculator.power(3, 4));
	}
	
	@ParameterizedTest
	// @ValueSource(ints = { 1, 3, -5, 15 })
	@MethodSource("com.training.provider.DataProvider#proideDataForTestIsOddMethod")
	public void testOddNumber(int n) {
		
		assertTrue(Calculator.isOdd(n));
	}
	
	@ParameterizedTest
	@NullSource
	public void isBlank_Should_Return_True_ForNullValues(String s) {
		assertTrue(Calculator.isBlank(s));
	}
	
	@ParameterizedTest
//	@NullSource
//	@EmptySource
	@NullAndEmptySource
	@ValueSource(strings = { "", " " })
	public void testEmptyString(String s) {		
		assertTrue(Calculator.isBlank(s));		
	}
		
	@ParameterizedTest
	@EnumSource(value = Month.class, names = { "JAN", "FEB", "MAR" })
	public void getValueForAMonth_IsAlwaysBetweenOneAndTwelve(Month month) {
		int monthNumber = month.getValue();
		assertTrue(monthNumber >= 1 && monthNumber <= 12);
	}
	
	
	  // @ParameterizedTest(name = "#{index} - {0} ^ {1} = {2}")
	  @ParameterizedTest(name = "#{index} - Test with arguments = {arguments}")
	  @CsvSource(value = { 
			                               "2, 3, 8",
			                               "5, 2, 25",
			                               "3, 3, 27",
			                               "9, 2, 81"
			                         })
	  public void testPower2(int x, int y, int z) { 
		  
		  assertEquals(z, Calculator.power(x, y));
	  }
	  
	  @ParameterizedTest(name = "#{index} - Test with arguments = {arguments}")
	  @CsvFileSource(resources = "/input_data.csv", numLinesToSkip = 1)
	  public void testPower3(int x, int y, int z) {
		 
		  assertEquals(z, Calculator.power(x, y));
	  }
	  
	  @ParameterizedTest(name = "#{index} - Test with arguments = {arguments}")
	  // @MethodSource("com.training.tests.TestCalculator#provideDataForTestPowerMethod")
	  @MethodSource("com.training.provider.DataProvider#provideDataForTestPowerMethod")
	  public void testPower4(int x, int y, int z) {
		  
		  assertEquals(z, Calculator.power(x, y));
	  }
	  
	  	
//		  public static Stream<Arguments> provideDataForTestPowerMethod() {
//		  
//		       return Stream.of(Arguments.of(2, 3, 8), Arguments.of(3, 3, 27),Arguments.of(5, 2, 25), Arguments.of(9, 2, 81));
//		  }
		 
	  
	 
	 
}
















