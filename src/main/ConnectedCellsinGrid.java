package main;

import java.util.Scanner;

public class ConnectedCellsinGrid {

    public static boolean[][] data;
    public static int N;
    public static int M;
    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       N = sc.nextInt();
       M = sc.nextInt();
       int tempCount=0,maxCount=0;
       data = new boolean[N][M];
       for(int i=0;i<N;i++){
           for(int j=0;j<M;j++){
               data[i][j] = sc.nextInt()>0;
           }
       }
       
       for(int i=0;i<N;i++){
           for(int j=0;j<M;j++){
               if(data[i][j]){
                   tempCount = countRegion(i,j);
                   if(tempCount>maxCount)
                       maxCount = tempCount;
               }
           }
       }
       System.out.println(maxCount);

    }

    private static int countRegion(int i, int j) {
        if(inGrid(i,j) && data[i][j]){
            data[i][j] = false;
            int tempCount=1;
            tempCount+=countRegion(i+1, j+1);
            tempCount+=countRegion(i+1, j-1);
            tempCount+=countRegion(i-1, j+1);
            tempCount+=countRegion(i-1, j-1);
            tempCount+=countRegion(i, j+1);
            tempCount+=countRegion(i, j-1);
            tempCount+=countRegion(i+1, j);
            tempCount+=countRegion(i-1, j);
            return tempCount;
        }
        return 0;
    }

    private static boolean inGrid(int i, int j) {
        return i>=0 && i<N && j>=0 &&j<M;
    }

}

