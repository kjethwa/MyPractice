package main;

import java.math.BigInteger;
import java.util.Scanner;

public class ExtraLongFactorial {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        BigInteger big = new BigInteger("1");
        
        for(int i=1;i<=n;i++){
            big = big.multiply(new BigInteger(i+""));
        }
        System.out.println(big);
    }

}

