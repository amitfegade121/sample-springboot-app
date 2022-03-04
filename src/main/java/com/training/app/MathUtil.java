package com.training.app;

public class MathUtil {
	
	public int addition(int x, int y) {
		return x + y;
	}
	
	public long multiplication(int x, int y) {
		return x * y;
	}
	
	public boolean isOdd(int n) {
		return 	n % 2 != 0;
	}
	
	public boolean isBlank(String s) {
		return s == null || s.trim().isEmpty();
	}
	
	public long power(int x, int y) {
		
		if(y == 0)
			return 1;
		else 
			return x * power(x, y - 1);		
	}
}










