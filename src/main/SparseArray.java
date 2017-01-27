package main;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SparseArray {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String,Integer> map = new HashMap<String,Integer>();
        int N = sc.nextInt(),Q;
        String temp;
        for(int i=0;i<N;i++){
            temp = sc.next();
            if(map.get(temp)==null)
                map.put(temp,1);
            else{
                map.put(temp,map.get(temp)+1);
            }
        }
        Q = sc.nextInt();
        for(int i=0;i<Q;i++){
            temp = sc.next();
            if(map.get(temp)==null)
                System.out.println(0);
            else
                System.out.println(map.get(temp));
        }
    }

}

