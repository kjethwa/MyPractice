package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EvenTree {
	
	static int count=0; 

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int M,N;
		N = sc.nextInt();
		M = sc.nextInt();
		Node[] nodes = new Node[N+1];
		EvenTree tree = new EvenTree();
		for (int i = 1; i <= N; i++) {
			nodes[i] = tree.new Node();
			nodes[i].number = i;
		}
		
		int U,V;
		for (int i = 0; i < M; i++) {
			U =sc.nextInt();
			V =sc.nextInt();
			nodes[U].edges.add(nodes[V]);
			nodes[V].edges.add(nodes[U]);
		}
		
		nodes[1].numberOfVertices =  tree.calculateVertices(nodes[1]);
		
		System.out.println(count-1);
		
	}

	private int calculateVertices(Node node) {
		int sum=1;
		node.visited = true;
		for(Node edge : node.edges){
			if(!edge.visited){
				sum+= calculateVertices(edge);
			}
		}
		node.numberOfVertices = sum; 
		if(sum%2==0){
			count++;
		}
		return sum;
	}

	class Node{
		int number;
		List<Node> edges = new ArrayList<Node>();
		int numberOfVertices;
		boolean visited;
	}
}
