package main;

import java.util.Scanner;

class SquareTransaction {
    public static int data[];
    public static void main(String args[]) throws Exception {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(), Q;
        data = new int[N];

        data[0] = sc.nextInt();
        for (int i = 1; i < N; i++) {
            data[i] = data[i - 1] + sc.nextInt();
        }
        Q = sc.nextInt();
        for (int i = 0; i < Q; i++) {
            System.out.println(search(sc.nextInt(),0,N-1)+1);
        }

    }

    private static int search(int key,int start,int end) {
        if(start<end){
            int mid = (start+end)/2;
            if(data[mid]<key && data[mid+1]>=key)
                return mid+1;
            if(key>data[mid])
                return search(key,mid+1,end);
            else
                return search(key,start,mid);
        }
        return -1;
    }
}
