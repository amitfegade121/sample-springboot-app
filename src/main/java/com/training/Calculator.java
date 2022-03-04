package com.training;

public class Calculator {

	public static int addition(int x, int y) {
		return x + y;
	}	
	
	public static long power(int x, int y) {
		
		if (y == 0) 
			return 1;
		else
			return x * power(x, y - 1);
	}
	
	public static int division(int x, int y) {
		return x / y;
	}
	
	public static int subtraction(int x, int y) {
		return x - y;
	}
	
	public static boolean isOdd(int n) {
		return n % 2 != 0;
	}
	
	public static boolean isBlank(String s) {
		return s == null || s.trim().isEmpty();
	}
}






















