package main;

import java.util.Scanner;

public class Encryption {

    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)!=' '){
                sb.append(s.charAt(i));
            }
        }
        int L = sb.length(),row,col;
        row = (int) Math.round(Math.sqrt(L));
        if(row>=Math.sqrt(L))
            col = row;
        else
            col = row +1;

        for(int i=0;i<col;i++){
            for(int j=i;j<L;j=j+col){
                System.out.print(sb.charAt(j));
            }
            System.out.print(" ");
        }
            
        
    }
}

