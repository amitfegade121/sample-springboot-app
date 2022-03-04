package com.training.app;

@FunctionalInterface
public interface Printable {
	
	public void print();

}

class PrintableImpl implements Printable {
	
	public void print() {
		System.out.println("Hello");
	}
}



