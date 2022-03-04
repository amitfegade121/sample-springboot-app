package com.training.app;

public class BinarySearch {

	public static int binarySearch(int [] arr, int key) {
		int low = 0;
		int high = arr.length -1;
		int mid;
		// int mid = (low + high) / 2;
		
		while(low <= high) {
			mid = (low + high) / 2;
			System.out.println(low + " - " + mid + " - " + high);
			if(key == arr[mid]) 
				return mid;
			else if(key < arr[mid]) 
			    high = mid - 1;
			else if(key > arr[mid])
				low = mid + 1;			
		}
		
		return -1;
	}
	
	public static void main(String[] args) {
		int index = BinarySearch.binarySearch(new int[] {12, 34, 56, 78, 99, 123, 167, 299}, 167);
		System.out.println(index);
	}
}

