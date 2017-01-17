package main;

import java.util.Scanner;

public class AlmostSorted {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] data = new int[N];

		for (int i = 0; i < N; i++) {
			data[i] = sc.nextInt();
		}

		int i = 0, j = N - 1;

		while (i < N - 1) {
			if (data[i + 1] < data[i]) {

				while (i > 0 && data[i] == data[i - 1])
					i--;

				break;
			}
			i++;
		}

		while (j > 0) {
			if (data[j - 1] > data[j]) {
				break;
			}
			j--;
		}

		if (i < N && j >= 0 && i < j) {
			// Swap i and j
			int temp;
			temp = data[j];
			data[j] = data[i];
			data[i] = temp;
		}

		if (isSorted(data)) {
			System.out.println("yes");
			System.out.println("swap " + (i + 1) + " " + (j + 1));
			return;
		} else {
			int l = i + 1;
			int r = j - 1;
			if (l < r) {
				while (l < r) {
					if (data[l] < data[l + 1]) {
						System.out.println("no");
						return;
					}
					l++;
				}
				System.out.println("yes");
				System.out.println("reverse " + (i + 1) + " " + (j + 1));

			}
			else{
				System.out.println("no");
			}
		}

	}

	private static boolean isSorted(int[] data) {

		for (int i = 0; i < data.length - 1; i++)
			if (data[i + 1] < data[i]) {
				return false;
			}

		return true;
	}

}
