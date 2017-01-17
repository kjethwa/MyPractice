package main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DetectCycleInGraph {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int a,b;
		int[] parent = new int[2*N+1];
		Arrays.fill(parent,-1);
		
		for(int i=0;i<N;i++){
			a = sc.nextInt();
			b = sc.nextInt();
			
			a = find(parent,a);
			b = find(parent,b);
			
			if(a != b){
				Union(parent, a, b);
			}
				
		}
		int [] minmax = findMinMax(parent);
		System.out.println(minmax[0]+" Max = "+minmax[1]);
		System.out.println("asdf");
	}

	private static int[] findMinMax(int[] parent) {
		
		int[] minmax = new int[2];
		minmax[0] = Integer.MAX_VALUE;
		minmax[1] = 1;
		boolean visited[]  = new boolean[parent.length];
		Arrays.fill(visited, false);
		Map<Integer,Integer> map = new HashMap<Integer, Integer>();
		for(int i=1;i<parent.length;i++){
			if (!visited[i]) {
				int count = findSubset(parent, i, visited);
				int a = find(parent,i);

				if (map.get(a) == null) {
					map.put(a, count);
				} else {
					map.put(a, count + map.get(a));
				}
			}				
		}
		for(Integer end : map.keySet()){
			if(map.get(end)>1){
				if(map.get(end)>minmax[1])
					minmax[1] = map.get(end);
				if(map.get(end)<minmax[0])
					minmax[0] = map.get(end);
			}
		}
		if(minmax[0]==Integer.MAX_VALUE)
			minmax[0] = minmax[1];
		return minmax;
	}

	private static int findSubset(int[] parent, int i,boolean[] visited) {
		visited[i] = true;
		if(parent[i]!=-1){
			if(!visited[parent[i]])
				return 1 + findSubset(parent, parent[i],visited);
			else
				return 1;
		}
		else{
			return 1;
		}
	}

	private static int find(int[] parent, int b) {
		if(parent[b]==-1)
			return b;
		return find(parent,parent[b]);
	}
	private static void Union(int parent[], int x, int y)
	    {
	        int xset = find(parent, x);
	        int yset = find(parent, y);
	        parent[xset] = yset;
	    }
}
