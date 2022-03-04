package com.training;

public enum Month {
	
	JAN(1), FEB(2), MAR(3), APR(4), MAY(5), JUN(6), JULY(7), AUG(8), SEPT(9), OCT(10), NOV(11), DEC(12);
    
	private final int value;
	
	 private Month(int value) {
		this.value = value;
	}
	 
	 public int getValue() {
		 return value;
	 }
	
}
