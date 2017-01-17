package main;

import java.util.StringTokenizer;

public class Pyramid {

	static int endX,endY;
	static String solutionPath = "";
	static int solutionCost = Integer.MAX_VALUE;

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(minimumCost(new String []{"5#7#2#4","1#8#1#3","6#2#9#5","1#6#2#8"},4));
	}

	
	public static String minimumCost(String[] input1,int input2)
    {
		StringTokenizer st = new StringTokenizer(input1[0],"#");
		endX = st.countTokens();
		endY = input2;
		
		int data[][] = new int[endY][endX];
		for(int i=0;i<endY;i++){
			st = new StringTokenizer(input1[i],"#");
			for(int j=0;j<endX;j++){
				data[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		findMinCost(data,0,0,"",0);
		return solutionCost+","+solutionPath;
    }


	private static void findMinCost(int[][] data,int X,int Y,String strPath,int cost) {
		if(X>=0 && X<endX && Y>=0 && Y<endY){

			if (X == endX - 1 && Y == endY - 1) {
				int currCost = cost + data[Y][X];
				if(currCost<solutionCost){
					solutionCost = currCost;
					solutionPath = strPath;
				}
				
			} else {
					findMinCost(data, X, Y+1,strPath+"B",cost + data[Y][X]);
					findMinCost(data, X+1, Y,strPath+"R",cost + data[Y][X]);
					findMinCost(data, X+1, Y+1,strPath+"D",cost + data[Y][X]);
					findMinCost(data, X-1, Y+1,strPath+"D",cost + data[Y][X]);
			}
		}
	}
}
