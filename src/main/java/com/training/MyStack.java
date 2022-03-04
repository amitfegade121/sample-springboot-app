package com.training;

import java.util.LinkedList;

import com.training.exception.EmptyMyStackException;

public class MyStack<T> {

	private LinkedList<T> data = new LinkedList<>();
	
	public void push(T element) {
		
		data.addLast(element);
	}
	
	public int size() {
		return data.size();
	}

	public T pop() {
	    if (isEmpty())
	    	throw new EmptyMyStackException("Stack is empty.");
		return data.removeLast();
	}
	
	public T peek() {
		if (isEmpty())
			throw new EmptyMyStackException("Stack is empty.");
		return data.getLast();
	}
	
	public boolean isEmpty() {
		return data.isEmpty();
	}
}















