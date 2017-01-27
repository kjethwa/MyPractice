package main;

import java.util.Scanner;

public class Contacts {

    public final static String ADD = "add";
    public final static String FIND = "find";
    public static Node root = new Node();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String temp;
        for (int i = 0; i < n; i++) {
            temp = sc.next();
            if (temp.equals(ADD)) {
                add(sc.next());
            }
            else {
                System.out.println(find(sc.next()));
            }
        }
    }

    private static int find(String string) {
        Node temp = root;
        for(int i=0;i<string.length();i++){
            if(temp.child[string.charAt(i)-'a']==null){
                return 0;
            }
            else{
                temp = temp.child[string.charAt(i)-'a'];
            }
        }
        return temp.count;
    }

    private static void add(String string) {
        Node temp = root;
        for(int i=0;i<string.length();i++){
            if(temp.child[string.charAt(i)-'a']==null){
                temp.child[string.charAt(i)-'a'] = new Node(string.charAt(i));
                temp = temp.child[string.charAt(i)-'a'];
            }
            else{
                temp.child[string.charAt(i)-'a'].count++;
                temp = temp.child[string.charAt(i)-'a'];
            }
        }
    }

    static class Node {
        char value;
        int count = 1;
        Node[] child = new Node[26];
        Node(){
            
        }
        Node(char value){
            this.value = value; 
        }
    }
}
