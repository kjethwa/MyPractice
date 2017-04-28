package main;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class GridlandMetro {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(), M = sc.nextInt(), K = sc.nextInt();
        Map<Integer,LinkedList<Integer>> map = new HashMap<Integer,LinkedList<Integer>>();
        int r,c1,c2,count=0;
        LinkedList<Integer> list;
        for (int i = 0; i < K; i++) {
            r = sc.nextInt();
            c1 = sc.nextInt();
            c2 = sc.nextInt();
            if(map.get(r)==null){
                list = new LinkedList<Integer>();
                list.add(c1);
                list.add(c2);
                map.put(r,list);
            }
            else{
                list = map.get(r);
                int index = c1istheend(c1,list);
                if(index!=-1){
                    list.remove(index);
                    list.add(index, c2);
                }
                else{
                list.add(c1);
                list.add(c2);
                }
                index = c2isthestart(c2,list);
                
            }
        }
        
        for (int i = 1; i <= N; i++) {
            if(map.get(i)==null)
                count+=M;
            else{
                count+=calculateFreeCells(map.get(i),i);
            }
        }
        System.out.println(count);
    }
    
    private static int c1istheend(int c1, LinkedList<Integer> list) {
        int size = list.size();
        for (int i = 0; i < size; i += 2) {
            if(list.get(i+1)==c1)
                return i;
        }
        return -1;
    }
    
    private static int c2isthestart(int c2, LinkedList<Integer> list) {
        int size = list.size();
        for (int i = 0; i < size; i += 2) {
            if(list.get(i)==c2)
                return i;
        }
        return -1;
    }

    private static int calculateFreeCells(LinkedList<Integer> list, int r) {
        int size = list.size();
        int totallength=0,effectivelength=0;
        for (int i = 0; i < size; i += 2) {
            int index = getEffectiveStartDate(list.get(i),list);
            if(index!=-1){
                list.remove(index);
                list.add(index, i);
            }
        }
        return 0;
    }

    private static int getEffectiveStartDate(Integer start, LinkedList<Integer> list) {
        int size = list.size();
        for (int i = 0; i < size; i += 2) {
            if (start >= list.get(i) && start <= list.get(i + 1)){
                return i + 1;
            }
        }
        return -1;
    }

    private static boolean isCellFree(LinkedList<Integer> list, int m) {
        int size = list.size();
        for (int i = 0; i < size; i += 2) {
            if (m >= list.get(i) && m <= list.get(i + 1))
                return false;
        }
        return true;
    }
}

