package main;

import java.util.Scanner;

public class MaximumSubArray {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        int N=0;
        int data[] = null; 
        for(int i=0;i<T;i++){
            N = sc.nextInt();
            data = new int[N];
            for(int j=0;j<N;j++){
                data[j] = sc.nextInt();
            }
            
            int currentSum = data[0];
            int maximumSum = data[0];
            int allPositive = data[0];
            int negativeMaxNumber = data[0];
            
            for (int k = 1; k < N; k++) {
                if ((currentSum + data[k]) > data[k]) {
                    currentSum = currentSum + data[k];
                }
                else {
                    currentSum = data[k];
                }
                if (currentSum > maximumSum) {
                    maximumSum = currentSum;
                }
                if (data[k] > 0) {
                    if (allPositive < 0)
                        allPositive = 0;
                    
                    allPositive += data[k];
                }
                else{
                    if (data[k] > negativeMaxNumber) {
                        negativeMaxNumber = data[k];
                    }
                }
            }
            
            System.out.println(maximumSum + " " + (allPositive>0?allPositive:negativeMaxNumber));
            
        }
    }

}

