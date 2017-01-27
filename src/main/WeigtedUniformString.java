package main;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class WeigtedUniformString {

	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        
        Set<Integer> set = new HashSet<Integer>();
        
        int i=0,length=s.length(),j;
        while(i<length){
        	j=i+1;
        	while(j<length && s.charAt(i)==s.charAt(j)){
        			set.add((j-i)*(s.charAt(i)-'a'+1));
        			j++;
        	}
        	set.add((j-i)*(s.charAt(i)-'a'+1));
        	i=j;
        }
        
        int n = in.nextInt();
        for(int a0 = 0; a0 < n; a0++){
            int x = in.nextInt();
            if(set.contains(x))
            	System.out.println("Yes");
            else
            	System.out.println("No");
        }
    }

}
