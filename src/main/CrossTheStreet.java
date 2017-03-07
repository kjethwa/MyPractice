package main;

import java.util.Scanner;

public class CrossTheStreet {

    public static int[][] data;
    public static int[][][] lookup;
    public static int N,M;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        N = sc.nextInt();
        M = sc.nextInt();
        data = new int[N][M];
        lookup = new int[N][M][N*M];
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                data[i][j] = sc.nextInt();
            }
        }
        System.out.println(crossTheStreet(0,0,0));
    }

    private static int crossTheStreet(int x,int y,int numberOfBlocks) {
        if(x>=0&&x<N && y>=0 && y<M){
            
             
        }
        return 0;
    }

}

