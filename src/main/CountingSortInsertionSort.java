package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CountingSortInsertionSort {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		CountingSortInsertionSort countingSort = new CountingSortInsertionSort();
		int N = sc.nextInt();
		Data tempData;
		StringBuilder solution = new StringBuilder();
		List<Data> listData = new ArrayList<Data>(N);

		for (int i = 0; i < N; i++) {
			tempData = countingSort.new Data();
			tempData.index = i;
			tempData.number = sc.nextInt();
			tempData.string = sc.next();
			insertInTheSortedList(listData, tempData);
		}
		
		for (Data data : listData) {
			if(data.index<N/2){
				solution.append("- ");
			}
			else{
				solution.append(data.string+" ");
			}
		}

		System.out.println(solution);
		
		for (Data data : listData) {
			System.out.print(data.number + " ");
		}
	}

	private static void insertInTheSortedList(List<Data> listData, Data tempData) {

		if (!listData.isEmpty()) {
			int i = listData.size() - 1;
			while (i >= 0) {
				if (tempData.number >= listData.get(i).number) {
					if (i == listData.size()-1) {
						listData.add(tempData);
						return;
					} else {
						listData.add(i + 1, tempData);
						return;
					}
				}
				
				i--;
			}
			if (i == -1) {
				listData.add(0, tempData);
			}
		}
		else{
			listData.add(tempData);
		}
	}

	class Data {
		int number;
		String string;
		int index;
	}

}
