package main;

import java.util.Arrays;

public class MinimumPalindromPartition {

	static String data;
	static int[] C;
	static int count=0;
	static int palondromCount=0;
	static int N;
	public static void main(String[] args) {
		data = "sasfdadf";
		C = new int[data.length()];
		Arrays.fill(C,Integer.MAX_VALUE);
		N = data.length();
		System.out.println(MPP(0));
		System.out.println(count);
		System.out.println(palondromCount);
	}

	private static int MPP(int startIndex) {
		count++;
		if(startIndex == N)
			return 0;
		
		if(C[startIndex]!=Integer.MAX_VALUE)
			return C[startIndex]; 
		
		int min = Integer.MAX_VALUE,temp;
		
		for (int j = startIndex; j < N; j++) {
			if (isPalindrom(startIndex, j)) {
				temp = 1 + MPP(j + 1);
				if(temp>0 && temp<min)
					min = temp;
			}
		}
		C[startIndex]=min;
		return min;
	}

	private static boolean isPalindrom(int startIndex, int endIndex) {
		palondromCount++;
		while(startIndex<=endIndex){
			if(data.charAt(startIndex++)!=data.charAt(endIndex--))
				return false;
		}
		return true;
	}

}
