package main;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class GeatXOR {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        long index,sum;
        for(int a0 = 0; a0 < q; a0++){
            long x = in.nextLong();
            index = 0;sum=0;
            while(x>0){
                if(x%2==0){
                    sum+=Math.pow(2,index);
                }
                index++;
                x = x >> 1;
            }
            System.out.println(sum);
            
        }
    }
}
