package main;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Abbreviation {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = Integer.parseInt(sc.nextLine());
		String a, b;
		char[] aArray;

		int size, index, lastIndex, lastJ,j;
		outer: for (int i = 0; i < T; i++) {
			a = sc.nextLine();
			b = sc.nextLine();
			aArray = a.toCharArray();
			size = aArray.length;

			index = 0;
			lastJ = 0;
			lastIndex = 0;
			second: for (j = 0; j < size; j++) {
				if (aArray[j] >= 'A' && aArray[j] <= 'Z') {
					for (; index < b.length(); index++) {
						if (b.charAt(index) == aArray[j]) {
							int q = lastJ;
							loop: for (int p = lastIndex; p < index; p++) {
								for (; q < j; q++) {
									if (Character.toUpperCase(aArray[q]) == b
											.charAt(p)) {
										q++;
										continue loop;
									}
								}
								System.out.println("NO");
								continue outer;
							}
							lastIndex = index+1;
							lastJ = j+1;
							index++;
							continue second;
						}
						
					}
					System.out.println("NO");
					continue outer;
				}
			}
			
			if(lastJ==j && index!=b.length()){
				System.out.println("NO");
				continue outer;
			}
			else if(lastJ!=j){
				int q = lastJ;
				loop: for (int p = lastIndex; p < b.length(); p++) {
					for (; q < size; q++) {
						if (Character.toUpperCase(aArray[q]) == b.charAt(p)) {
							q++;
							continue loop;
						}
					}
					System.out.println("NO");
					continue outer;
				}
			}
			
			System.out.println("YES");
		}
	}

}