package main;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Pairs {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);    
        int N = sc.nextInt();
        int K = sc.nextInt();
        int count=0;
        int[] data = new int[N];
        
        for(int i=0;i<N;i++){
            data[i] = sc.nextInt();
        }
        
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        for (int i = 0; i < N; i++) {
            map.put(data[i], 1);
        }
        
        for(int i=0;i<N;i++){
            if(map.get(data[i]+K)!=null){
                count++;
            }
        }
        System.out.println(count);
    }
}

