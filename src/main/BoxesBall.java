package main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class BoxesBall {

	static Set<Integer> selectedBoxes = new HashSet<Integer>();
	static int K,N;
	static int [] data;
	static int F,minSum =Integer.MAX_VALUE;
	static List<Set<Integer>> solutionList = new ArrayList<Set<Integer>>();
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int [] data = {1,2,3,4,5,6};
		int k = 4;
		int F = 10;
		
		System.out.println(ball_count(data, F, k));

	}
	
	public static int ball_count(int[] input1,int input2,int input3)
    {
		data = input1;
		F = input2;
		K = input3;
		N = data.length;
		
		if(K>=N || N<=0)
			return -1;
		
		pickBall(0);
				
        return minSum;
    }

	private static void pickBall(int selectedIndex) {
		
		if (K == selectedIndex) {
			if(selectedBoxes.size() == K){
				int sum=0;
				for (Iterator iterator = selectedBoxes.iterator(); iterator
						.hasNext();) {
					Integer integer = (Integer) iterator.next();
					sum+=data[integer];
				}
				
				if (sum > 0 && sum % F == 0 && sum < minSum) {
					minSum = sum;
				}
			}
		}
		else{
			for(int i=selectedIndex;i<N;i++){
				selectedBoxes.add(new Integer(i));
				pickBall(selectedIndex+1);
				selectedBoxes.remove(new Integer(i));
			}
		}
	}

}
