package com.training.exception;

public class EmptyMyStackException extends RuntimeException {
	
	public EmptyMyStackException() { } 
	
	public EmptyMyStackException(String message) {
		super(message);
	}
}
