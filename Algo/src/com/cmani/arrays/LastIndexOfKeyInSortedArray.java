package com.cmani.arrays;


/**
 * Given a sorted array of integers containing duplicates, write a program which returns 
 * the last index of an element.
 * @author chintamani.prasad
 * 
 */
public class LastIndexOfKeyInSortedArray {

	 private static int  binarySearchLastEle(int[]arr,int key){
		    int index = -1;
		    int high = arr.length -1;
		    int low =0;
		  
		    while(low <= high){
		        int mid = (high+low)/2;
		      if(key == arr[mid] && (mid == high || arr[mid+1] > key)){
		       return mid;
		      }else if(key < arr[mid]){
		        high = mid;
		      }else{
		        low = mid+1;
		      }
		    }
		    return index;
		  }

		  public static void main(String[] args) {
		    int[] arr = {0, 1, 2, 2, 4, 5,5,5, 8};
		    int key=5;
		    int index = binarySearchLastEle(arr,key);
		    System.out.println(index);
		  }

}
