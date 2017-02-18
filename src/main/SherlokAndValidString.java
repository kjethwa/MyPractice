package main;

import java.io.*;
import java.util.*;

public class SherlokAndValidString {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String string = sc.next();
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();

		int[] lookup = new int[26];
		int dif = 0;
		for (int i = 0; i < string.length(); i++)
			lookup[string.charAt(i) - 'a']++;
		for (int i = 0; i < 26; i++) {
			if (lookup[i] > 0) {
				if (map.get(lookup[i]) == null)
					map.put(lookup[i], 1);
				else
					map.put(lookup[i], map.get(lookup[i]) + 1);
			}
		}
		if (map.keySet().size() > 2) {
			System.out.println("NO");
			return;
		}
		if (map.keySet().size() == 1) {
			System.out.println("YES");
			return;
		}
		int i = 0;
		int[] set = new int[2];
		for (Integer num : map.keySet()) {
			set[i++] = num;
		}
		if ((map.get(set[0]) == 1 && set[0] == 1)
				|| (map.get(set[1]) == 1 && set[1] == 1)) {
			System.out.println("YES");
			return;
		} else if ((map.get(set[0]) == 1 || map.get(set[1]) == 1)
				&& Math.abs(set[0] - set[1]) == 1) {
			System.out.println("YES");
			return;
		} else {
			System.out.println("NO");
			return;
		}

	}
}