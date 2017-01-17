package main;

import java.util.Queue;
import java.util.Scanner;

public class HeightOfTree {
	
	static class Node {
	    Node left;
	    Node right;
	    int data;
	    
	    Node(int data) {
	        this.data = data;
	        left = null;
	        right = null;
	    }
	}
	/*
    class Node 
    	int data;
    	Node left;
    	Node right;
	*/
	static int height(Node root) {
      	return preOrder(root,0);
    }
    static int preOrder(Node root,int h){
        if(root==null){
            return 0;
        }
        if(root.left==null && root.right==null){
        	return 0;
        } 
        return 1 + max(preOrder(root.left,h),preOrder(root.right,h));
    }
    static int max(int a,int b){
        return a>b?a:b;
    }
    
    static void decode(String S ,Node root)
    {
        Node temp=root;
        for(int i=0;i<S.length();i++){
            if(S.charAt(i)=='1')   
                temp = temp.right;
            else  
                temp = temp.left;
			if (temp != null)
				if (temp.left == null && temp.right == null) {
					System.out.print(temp.data);
					temp = root;
				}
        }
    }
    
	public static Node insert(Node root, int data) {
        if(root == null){
            return new Node(data);
        }
        else {
            Node cur;
            if(data <= root.data){
                cur = insert(root.left, data);
                root.left = cur;
            }
            else{
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        Node root = null;
        while(t-- > 0){
            int data = scan.nextInt();
            root = insert(root, data);
        }
        scan.close();
        int height = height(root);
        System.out.println(height);
        decode("1001011", root);
    }	
}