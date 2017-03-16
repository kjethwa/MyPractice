package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CutTheTree {

	static Integer difference = Integer.MAX_VALUE;
	static Map<Integer, List<Integer>> graph;
	static int[] data;
	static boolean[] visited;
	static int totalSum = 0;
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		data = new int[N + 1];
		visited = new boolean[N+1];
		int u, v;
		List<Integer> temp;
		
		graph = new HashMap<Integer, List<Integer>>();
		for (int i = 1; i <= N; i++) {
			data[i] = sc.nextInt();
			totalSum += data[i];
		}

		for (int i = 0; i < N - 1; i++) {
			u = sc.nextInt();
			v = sc.nextInt();
			if (graph.get(u) == null) {
				temp = new ArrayList<Integer>();
				temp.add(v);
				graph.put(u, temp);
			} else {
				graph.get(u).add(v);
			}
			if (graph.get(v) == null) {
				temp = new ArrayList<Integer>();
				temp.add(u);
				graph.put(v, temp);
			} else {
				graph.get(v).add(u);
			}
		}
		calculateDifference(1);
		System.out.println(difference);
	}
	private static int calculateDifference(int node) {
		int sum=data[node];
		visited[node] = true;
		for(Integer edge : graph.get(node)){
			if(!visited[edge])
				sum += calculateDifference(edge);
		}
		if (Math.abs(Math.abs(totalSum - sum) - sum) < difference)
			difference = Math.abs(Math.abs(totalSum - sum) - sum); 
		return sum;
	}
}
