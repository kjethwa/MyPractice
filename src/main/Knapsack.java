package main;

/* A Naive recursive implementation of 0-1 Knapsack problem */
public class Knapsack {

	// A utility function that returns maximum of two integers
	static int max(int a, int b) {
		return (a > b) ? a : b;
	}

	static int count = 0;

	static int[][] lookup ;
	// Returns the maximum value that can be put in a knapsack of capacity W
	static int knapSack(int W, int wt[], int val[], int n) {
		count++;
		// Base Case
		if (n == 0 || W == 0)
			return 0;

		if(lookup[n][W]!=0)
			return lookup[n][W];
		// If weight of the nth item is more than Knapsack capacity W, then
		// this item cannot be included in the optimal solution
		if (wt[n - 1] > W){
			lookup[n][W] = knapSack(W, wt, val, n - 1);
			return lookup[n][W];  
		}

		// Return the maximum of two cases:
		// (1) nth item included
		// (2) not included
		else{
			lookup[n][W]= max(val[n - 1] + knapSack(W - wt[n - 1], wt, val, n - 1),
					knapSack(W, wt, val, n - 1));
			return lookup[n][W];
		}
	}

	// Driver program to test above function
	public static void main(String args[]) {
		int val[] = new int[] { 60, 100, 120,20,20,20, 100, 100 };
		int wt[] = new int[] { 10, 20, 30,20,20,20 ,20,20};
		int W = 50;
		lookup = new int[val.length + 1][W + 1];
		int n = val.length;
		System.out.println(knapSack(W, wt, val, n));
		System.out.println(count);
	}
}
