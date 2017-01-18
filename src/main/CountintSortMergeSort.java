package main;

import java.util.Scanner;

public class CountintSortMergeSort {

	static Data[] data;
	static Data[] solutionData;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		CountintSortMergeSort countingSort = new CountintSortMergeSort();
		int N = sc.nextInt();
		StringBuilder solution = new StringBuilder();
		Data tempData;
		data = new Data[N];
		solutionData = new Data[N];

		for (int i = 0; i < N; i++) {
			tempData = countingSort.new Data();
			tempData.index = i;
			tempData.number = sc.nextInt();
			tempData.string = sc.next();
			data[i] = tempData;
		}

		mergeSort(0, N - 1);

		for (int i=0; i<data.length;i++) {
			if (data[i].index < N / 2) {
				solution.append("- ");
			} else {
				solution.append(data[i].string + " ");
			}
		}

		System.out.println(solution);
		
		for (int i=0; i<data.length;i++) {
			System.out.print(data[i].number + " ");
		}
	}

	private static void mergeSort(int l, int h) {
		if (l < h) {
			int m = (l + h) / 2;
			mergeSort(l, m);
			mergeSort(m + 1, h);

			merge(l, m, h);
		}
	}

	private static void merge(int l, int m, int h) {
		int ll = m + 1, i = l,initialL=l;
		while (l <= m && ll <= h) {
			if (data[l].number <= data[ll].number) {
				solutionData[i++] = data[l++];
			} else {
				solutionData[i++] = data[ll++];
			}
		}

		while (l <= m) {
			solutionData[i++] = data[l++];
		}

		while (ll <= h) {
			solutionData[i++] = data[ll++];
		}

		for (int j = initialL; j <= h; j++) {
			data[j] = solutionData[j];
		}
	}

	class Data {
		int number;
		String string;
		int index;
	}
}
