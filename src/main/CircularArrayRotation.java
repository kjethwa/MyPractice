package main;

import java.util.Scanner;

public class CircularArrayRotation {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int q = in.nextInt();
        int[] a = new int[n];
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
        }
        
        int tempA,tempB,nextIndex;
        tempA = a[0];
        k = k%n;
        nextIndex = (k)%n;
        if (k > 0)
            for (int i = 1; i < n; i++) {
                tempB = a[nextIndex];
                a[nextIndex] = tempA;
                tempA = tempB;
                nextIndex = (nextIndex + k) % n;
            }
        a[nextIndex] = tempA;
        for(int a0 = 0; a0 < q; a0++){
            int m = in.nextInt();
            System.out.println(a[m]);
        }
        
        
        
        
    }

}

