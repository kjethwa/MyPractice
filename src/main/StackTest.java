package main;

import java.util.Stack;

public class StackTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stack<PiecePosition> s = new Stack<PiecePosition>();
		s.push(null);
		s.push(null);
		System.out.println("size = "+s.size());
		System.out.println(s.pop());
		System.out.println(s.pop());
	}

}

