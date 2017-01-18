package main;

import java.util.Arrays;
import java.util.Scanner;

public class CoinChangeProblem {

	static int[] C ;
	static int M,count=0 ;
	static long[][] lookup;
	static String string;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc= new Scanner(System.in);
		int N = sc.nextInt();
		M = sc.nextInt();
		C = new int[M];
		for(int i=0;i<M;i++){
			C[i] = sc.nextInt();
		}
		lookup = new long[M+1][N+1];
		for(int i=0;i<M;i++){
			Arrays.fill(lookup[i],-1);
		}
		
		System.out.println(CCP(N,"",0));
		System.out.println(string);
	}

	private static long CCP(int remaining,String s,int index) {
		//count++;
		if(remaining==0){
			//System.out.println(s);
			return 1;
		}
		if(remaining<0){
			lookup[index][remaining]=0;
			return 0;
		}
		if(lookup[index][remaining]!=-1){
			return lookup[index][remaining];
		}
		long min=Long.MAX_VALUE,temp;
		for(int i=index;i<M;i++){
			if(C[i]<=remaining){
				temp = 1 + CCP(remaining-C[i],s+" "+C[i],i);
				if(temp>0 && temp<min){
					min = temp;
					string = s+" "+C[i];
				}
			}
			//coinsCollected+=C[index];
		}
		lookup[index][remaining] = min;
		return min;
	}

}
