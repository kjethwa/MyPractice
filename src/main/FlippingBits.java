package main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class FlippingBits {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		String number = sc.next();
		int j = sc.nextInt();
		int k = sc.nextInt();
		
		
		System.out.println(nochange_bits(number, k, j));
		

	}

	public static int nochange_bits(String input1,int input2,int input3)
    {
		String number = input1;
		int j = input2;
		int k = input3;
		int N = number.length();
		List<Integer> list = new ArrayList<Integer>();
		if(j<=0 || k<=0){
			return -1;
		}
		
		for (int i = j; i <= N; i=i+j) {
			list.add(i);
		}
		
		for (int i = k; i <= N; i=i+k) {
			if(list.contains(i)){
				list.remove(new Integer(i));
			}
			else{
				list.add(new Integer(i));
			}
		}
		
		return N-list.size();
    }
}
