package com.training.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import com.training.MyStack;
import com.training.exception.EmptyMyStackException;

@DisplayName("Testing MyStack class")
public class MyStackTest {
	
	private MyStack<String> stack;
	
	@BeforeEach  
	public void setUp() {
		stack = new MyStack<>();
	}
	
	@Disabled
	@DisplayName("Push operation adds new element in stack.")
	@Test
	 public void should_Increase_SizeOfStack_By_One_WhenAnElementIsPushedIntoStack() {
		
		 stack.push("Hello");
		 stack.push("Hiii");
		 
		 assertEquals(2, stack.size());
	 }
	
	@Disabled
	@Test
	public void should_Decrease_Size_By_One_WhenAnElementIsPoppedFromStack_And_AlsoReturnIt() {
		
		stack.push("One");   stack.push("Two");
		assertEquals(2, stack.size());
		
		String removedElement = stack.pop();
	    assertEquals(1, stack.size());
	    assertEquals("Two", removedElement);
	}
	
	@Test
	public void should_Throw_EmptyMyStackException_WhenAnElementIsPoppedFromEmptyStack() {
		
		assertThrows(EmptyMyStackException.class, () -> stack.pop());
	}
	
	@Timeout(value = 1, unit = TimeUnit.SECONDS)
	@Test
	public void peek_Should_Return_TopMostElement_Without_RemovingIt() {
		
		stack.push("One");
		stack.push("Two");
		
		String topMostElement = stack.peek();
		assertEquals("Two", topMostElement);
		assertEquals(2, stack.size());
	}
	
	@Test
	public void peek_Should_Throw_EmptyMyStackException_WhenStackIsEmpty() {
		
		assertThrows(EmptyMyStackException.class, () -> stack.peek());
	}
	
	@Test
	public void isEmpty_Should_Return_True_IfStackIsEmpty() {
		
		assertTrue(stack.isEmpty());
	}
	
	@Test
	public void isEmpty_Should_Return_False_IfStackIsEmpty() {
			
		stack.push("One");
		stack.push("Two");
		
		assertFalse(stack.isEmpty());
	}
 	
	@AfterEach
	public void tearDown() {		
		stack = null;
	}
}














