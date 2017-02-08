package main;

import java.util.Scanner;

public class NoPrefixSet {

    public static void main(String[] args) {
     
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Node tree = new Node(),temp=null;
        String string;
        for(int i=0;i<N;i++){
            string = sc.next();
            temp = tree;
            for(int j=0;j<string.length();j++){
                if(temp.child[string.charAt(j)-'a']==null){
                    temp.child[string.charAt(j)-'a'] = new Node();
                    if(j==string.length()-1){
                        temp.child[string.charAt(j)-'a'].leaf = true;
                    }
                }
                else if(temp.child[string.charAt(j)-'a'].leaf==true){
                    System.out.println("BAD SET");
                    System.out.println(string);
                    return ;
                }

                temp = temp.child[string.charAt(j)-'a'];
            }
        }
        
    }
    static class Node{
        boolean leaf = false;
        Node[] child = new Node[26];
    }
}

