package main;

import java.util.Scanner;

public class StrangeCounter {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long T = sc.nextLong();
        long counter = 3;
        long time = 3;
        if(T<=3){
            System.out.println(3-T+1);
            return ;
        }
        while (time < T) {
            if (2 * counter + time < T) {
                time += 2 * counter;
                counter = 2 * counter;
            }
            else
                break;
        }
        System.out.println((2*counter -(T-time-1)));
         
    }
}
