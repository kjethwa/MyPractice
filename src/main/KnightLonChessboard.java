package main;

import java.util.Scanner;

public class KnightLonChessboard {

	private static int[][] lookup;
	private static int N;
	private static int a,b;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		for (int i = 1; i < N; i++) {
			for (int j = i; j < N; j++) {
				resetLookup();
				a = i;
				b = j;
				System.out.print(minSteps(0, 0));
			}
			System.out.println();
		}
	}

	private static void resetLookup() {
		lookup = new int[N][N];
	}

	private static int minSteps(int y, int x) {
		if(y==N-1 && x==N-1){
			return 1;
		}
		if(lookup[y][x]!=0)
			return lookup[y][x];
		
		
		return 0;
	}
}
