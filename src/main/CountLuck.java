package main;

import java.io.*;
import java.util.*;

public class CountLuck {

    static char[][] data;
    static int N;
    static int M;
    static boolean found = false;
    static int count=0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        int K;
		for (int k = 0; k < T; k++) {
			
			N = sc.nextInt();
			M = sc.nextInt();
			String temp;
			data = new char[N][M];
			found = false;
			count=0;
			for (int i = 0; i < N; i++) {
				temp = sc.next();
				for (int j = 0; j < M; j++) {
					data[i][j] = temp.charAt(j);
				}
			}
			K = sc.nextInt();
			int[] mM = findM();
			findStar(mM[0],mM[1] );
			if(count==K)
				System.out.println("Impressed");
			else
				System.out.println("Oops!");

		}
    }
    
    private static void findStar(int y, int x) {
    	if(data[y][x]=='*'){
    		found = true;
    		return ;
    	}
    	
		if (!found) {
			data[y][x] = 'K';

			if (inGrid(y + 1, x) && data[y + 1][x] != 'X' && data[y + 1][x] != 'K')
				findStar(y + 1, x);
			if (!found && inGrid(y - 1, x) && data[y - 1][x] != 'X' && data[y - 1][x] != 'K')
				findStar(y - 1, x);
			if (!found && inGrid(y, x + 1) && data[y][x + 1] != 'X' && data[y][x + 1] != 'K')
				findStar(y, x + 1);
			if (!found && inGrid(y, x - 1) && data[y][x - 1] != 'X' && data[y][x - 1] != 'K')
				findStar(y, x - 1);
		}
    	if(!found && data[y][x]=='K')
    		data[y][x] = '.';
    	
    	if(found && data[y][x]=='K'){
    		if(inGrid(y + 1, x) && data[y+1][x] == '.'){
    			count++;return;
    		}
    		if( inGrid(y - 1, x) && data[y-1][x] == '.'){
    			count++;return;
    		}
    		if(inGrid(y, x + 1) &&data[y][x+1] == '.'){
    			count++;return;
    		}
    		if(inGrid(y, x - 1) && data[y][x-1] == '.'){
    			count++;return;
    		}
    	}
	}

	private static boolean inGrid(int y, int x) {
		
		return y >= 0 && y < N && x >= 0 && x < M;
	}

	private static int[] findM(){
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(data[i][j] == 'M')       
                    return new int[]{i,j};
            }
        }
		return null;
    }
}