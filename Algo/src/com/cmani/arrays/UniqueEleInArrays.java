package com.cmani.arrays;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;



/**
 * 
 * In an array having positive integers, except for one number which occurs odd number of times, all other numbers occur even number of times. Find the number.
 *	Example -  
 *	Input : 2 5 9 1 5 1 8 2 8
 *	Output: 9
 * @author chintamani.prasad
 *
 */
public class UniqueEleInArrays {

	 public static int filterDuplicates(int[] arr){
		    Map<Integer,Integer> countMap=new HashMap<>();
		    for(int i=0;i<arr.length;i++){
		      if(countMap.containsKey(arr[i])){
		        countMap.put(arr[i],countMap.get(arr[i])+1);
		      }else{
		        countMap.put(arr[i], 1);
		      }
		    }
		   // System.out.println(countMap);
		    for(Entry<Integer,Integer> entry:countMap.entrySet()){
		      if(entry.getValue() == 1){
		        return entry.getKey();
		      }
		    }
		    return -1;
		  }

		  public static void main(String[] args) {
		    int[] arr = {2, 5 ,9 ,1 ,5 ,1 ,8 ,2 ,8};
		    int uniqueVal= filterDuplicates(arr);
		    if(uniqueVal>0) {
		     System.out.println("Unique value:"+uniqueVal);
		    }
		  }

}
