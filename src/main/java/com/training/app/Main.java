package com.training.app;

public class Main {

	public static void main(String[] args) {
		
//		Printable p = new PrintableImpl();
//		p.print();
//	
//		
//		Printable p2 = () -> System.out.println("Good morning");
//		p2.print();
		
		Calculator c = (x, y) ->  x + y;
		System.out.println(c.calculate(3, 4));
		
		Calculator c2 = (x, y) -> x - y;
		System.out.println(c2.calculate(4, 2));
		
		Calculator c3 = (x, y) -> {
			int result = x * y;
			return result;
		};
		System.out.println(c3.calculate(5, 6));
		
	}
}

interface Calculator {
	
	public int calculate(int x, int y);
}

//class Addition implements Calculator {
//	
//	public int calculate(int x, int y) {
//		return x + y;
//	}
//}
//
//
//class Subtraction implements Calculator {
//	public int calculate(int x, int y) {
//		return x - y;
//	}
//}