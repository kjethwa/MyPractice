package main;

import java.util.Scanner;

public class MonketsInTheGarden {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int [] data = {1,2,3,4};
		System.out.println(traveltime(data, data.length));
	}
	
	public static int traveltime(int[] input1,int input2)
    {
		int data[] = input1;
		int N =input2;
		int maxDistance=0,distance=0;
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				distance = data[i]+data[j]+getDistance(i,j,data);
				if(distance>maxDistance){
					maxDistance = distance;
				}
			}
		}
       return maxDistance;
    }

	private static int getDistance(int i, int j, int[] data) {
		
		return (j - i) < (data.length - (j - i)) ? j - i : data.length
				- (j - i);
	}
}
