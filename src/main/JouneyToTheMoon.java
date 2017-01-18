package main;

import java.io.*;
import java.util.*;

public class JouneyToTheMoon {
	static long combinations = 0;
	static Map<Integer, List<Integer>> graph;
	static boolean visited[]; 
	static Stack<Integer> stack = new Stack<Integer>();
	static long ones = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(
				new InputStreamReader(System.in));
		String[] temp = bfr.readLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		int I = Integer.parseInt(temp[1]);
		graph = new HashMap<Integer, List<Integer>>();
		List<Long> peoplesFromOneCountry = new ArrayList<Long>();
		for (int i = 0; i < N; i++) {
			graph.put(i, new ArrayList<Integer>());
		}
		for (int i = 0; i < I; i++) {
			temp = bfr.readLine().split(" ");
			int a = Integer.parseInt(temp[0]);
			int b = Integer.parseInt(temp[1]);
			graph.get(a).add(b);
			graph.get(b).add(a);
			// Store a and b in an appropriate data structure of your choice
		}

		visited = new boolean[N];
		Arrays.fill(visited, false);
		for (int j = 0; j < visited.length; j++) {
			if (!visited[j]) {
				stack = new Stack<Integer>();
				stack.push(j);
				DFS(j);
				if(combinations==1)
					ones++;
				else
					peoplesFromOneCountry.add(combinations);
				combinations = 0;
			}
		}
		peoplesFromOneCountry.add(ones);

		for (int j = 0; j < peoplesFromOneCountry.size(); j++) {
			for (int k = j+1; k < peoplesFromOneCountry.size(); k++) {
				combinations += peoplesFromOneCountry.get(j)
						* peoplesFromOneCountry.get(k);
			}
		}
		if(ones>1){
			combinations += (ones * (ones - 1)) / 2;
		}
		System.out.println(combinations);

	}

	private static void DFS(int j) {
		visited[j]=true;
		combinations++;
		while (!stack.isEmpty()) {
			for (Integer edge : graph.get(stack.pop())) {
				if (!visited[edge]) {
					visited[edge] = true;
					combinations++;
					stack.push(edge);
				}
			}
		}
	}
}
