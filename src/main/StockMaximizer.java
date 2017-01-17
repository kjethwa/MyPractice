package main;

import java.util.Arrays;
import java.util.Scanner;

public class StockMaximizer {
	static int N;
	static int[] Cost;
    static long[][] lookup;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int i=0;i<T;i++){
			N = sc.nextInt();
			Cost = new int[N+1];
			lookup = new long[N][3];
            
			for(int j=0;j<N;j++){
				Cost[j] = sc.nextInt();
			}
			System.out.println(SM(0,0,0,0));
		}
	}

	private static long  SM(int day, int numberOfStock, long profit, long cost) {
		
		if(day==N){
			if(profit<0)
				return 0;
			return profit;
		}
		
		if(lookup[day][0]!=0&&lookup[day][1]!=0&&lookup[day][2]!=0)
            return max(lookup[day][0],lookup[day][1],lookup[day][2]);
		
		lookup[day][0] = SM(day + 1, numberOfStock + 1, profit - Cost[day], cost
						+ Cost[day]);
		lookup[day][1] = SM(day + 1, numberOfStock, profit, cost);
		lookup[day][2] = SM(day + 1, 0, profit + numberOfStock * Cost[day], 0);
		
		return  max(lookup[day][0],lookup[day][1],lookup[day][2]);

	}

	private static long max(long a, long b, long c) {
		return max(max(a,b),c);
	}

	private static long max(long a, long b) {
		return a>b?a:b;
	}

}
