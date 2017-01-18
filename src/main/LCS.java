package main;

import java.util.Arrays;

public class LCS {

	static public String A = " 12341";
	static public String B = " 341213";
	static public int[][] count = new int[A.length()][B.length()];
	static public char[][] string = new char[A.length()][B.length()];
	
	
	public static void main(String[] args) {		
		for(int i=0;i<A.length();i++){
			Arrays.fill(count[i],0);
		}
		//int a =_LCS(A.length()-1,B.length()-1);
		int a =_LCS();
		System.out.println(a);
		_LCS(A.length()-1, B.length()-1);
	}
	
	private static int _LCS() {
		
		for(int i=1;i<A.length();i++){
			for(int j=1;j<B.length();j++){
				if(A.charAt(i)==B.charAt(j)){
					count[i][j] = 1 + count[i-1][j-1];
					string[i][j] = 's';
				}
				else if(count[i-1][j]>count[i][j-1]){
					count[i][j] = count[i-1][j];
					string[i][j] = 'i';
				}
				else{
					count[i][j] = count[i][j-1];
					string[i][j] = 'j';
				}	
			}
		}
		return count[A.length()-1][B.length()-1];
		
	}


	private static void _LCS(int i,int j) {
		
		if(i<0 || j<0){
			return;
		}
		if(string[i][j]=='s'){
			_LCS(i-1, j-1);
			System.out.print(A.charAt(i));
		}
		else if(string[i][j]=='i'){
			_LCS(i-1, j);
		}
		else{
			_LCS(i,j-1);
		}
		
	}

	private static int max(int a, int b) {
		return a>b?a:b;
	}

}