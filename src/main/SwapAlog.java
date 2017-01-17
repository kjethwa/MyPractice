package main;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class SwapAlog {

	static class Node{
		int data;
		Node left;
		Node right;
	}
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		int N = sc.nextInt(),i,a,b,T;
		Node[] nodes = new Node[N+1];
		Node temp;
		for(i=1;i<=N;i++){
			temp = new Node();
			temp.data = i;
			nodes[i] = temp;		
		}
		for(i=1;i<=N;i++){
			a =sc.nextInt();
			b =sc.nextInt();
			if(a!=-1)
				nodes[i].left = nodes[a];
			if(b!=-1)
				nodes[i].right = nodes[b];
		}
		
		T =sc.nextInt();
		for(i=1;i<=T;i++)
			swap(sc.nextInt(),nodes[1]);

	}
	private static void swap(int K,Node root) {
		
		int d=0,dept=K;
		List<Node> firstList = new LinkedList<Node>();
		List<Node> secondList = new LinkedList<Node>();
		if(root!=null)
			firstList.add(root);
		
		
		while(!firstList.isEmpty()){
			d++;		
			for (Node node : firstList) {
				if(node.left!=null)
					secondList.add(node.left);
				if(node.right!=null)
					secondList.add(node.right);
			}
			if(d==dept && !secondList.isEmpty()){
				swap(firstList);
				dept+=K;
			}
			firstList.clear();
			firstList.addAll(secondList);
			secondList.clear();
		}

		inorder(root);
		System.out.println();
	}
	private static void inorder(Node root) {
		if(root!=null){
			inorder(root.left);
			System.out.print(root.data+" ");
			inorder(root.right);
		}
	}
	private static void swap(List<Node> list) {
		Node temp;
		for (Node node : list) {
			temp = node.left;
			node.left = node.right;
			node.right = temp;
		}
	}
}
