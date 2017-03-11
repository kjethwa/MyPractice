package main;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class BigSorting {

	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        BigInteger[] data = new BigInteger[n];
		for (int unsorted_i = 0; unsorted_i < n; unsorted_i++) {
			data[unsorted_i] = new BigInteger(in.next());
		}
		Arrays.sort(data);
		for (int i = 0; i < n; i++) {
			System.out.println(data[i]);
		}
    }
}
