package main;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


class TestClass {
    public static void main(String args[] ) throws Exception {
        int[] data;
        //Scanner
        Scanner sc = new Scanner(System.in);
        Set<Integer> set;
        Map<Integer,Integer> map ;
        int count=0;
        int N = sc.nextInt();
        data = new int[N];
        set = new HashSet<Integer>();
        map = new HashMap<Integer,Integer>();
        for (int i = 0; i < N; i++) {
            data[i] = sc.nextInt();
            set.add(data[i]);
        }
        int start=0,end=-1;
        
        while (end < N) {
            while (map.keySet().size() == set.size()) {
                count += N - end;
                // removeFromBeg(start,map);
                if (map.get(data[start]) == 1)
                    map.remove(data[start]);
                else
                    map.put(data[start], map.get(data[start]) - 1);

                start++;
            }
            end++;
            if (end < N) {
                // addToMap();
                if (map.get(data[end]) == null)
                    map.put(data[end], 1);
                else
                    map.put(data[end], map.get(data[end]) + 1);
            }

        }
        System.out.println(count);

        
    }
}
