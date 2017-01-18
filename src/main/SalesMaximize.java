package main;

import java.util.Scanner;

public class SalesMaximize {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        SalesMaximize maximize = new SalesMaximize();
        int N = sc.nextInt();
        Data[] data = new Data[N];
        int[] timpSlot = null;
        Data tempData = null;
        int minStart = Integer.MAX_VALUE, maxEnd = -1;
        for (int i = 0; i < N; i++) {
            tempData = maximize.new Data();
            tempData.startTime = sc.nextInt();
            tempData.endTime = sc.nextInt();
            tempData.price = sc.nextInt();
            data[i] = tempData;
            if (tempData.startTime < minStart) {
                minStart = tempData.startTime;
            }
            if (tempData.endTime > maxEnd) {
                maxEnd = tempData.endTime;
            }
        }

        timpSlot = new int[maxEnd + 1];
        for (int i = minStart; i <= maxEnd; i++) {
            timpSlot[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < data.length; i++) {
            for (int j = data[i].startTime; j <= data[i].endTime; j++) {
                if (timpSlot[j] >= data[i].price) {
                    timpSlot[j] = data[i].price;
                }
            }
        }

        for (int i = minStart; i <= maxEnd; i++) {
            if (timpSlot[i] == Integer.MAX_VALUE) {
                timpSlot[i] = 0;
            }
        }

        int i = minStart;
        boolean flag = false;
        for (i = minStart; i < maxEnd; i++) {
            int start = i;
            while (i < maxEnd && timpSlot[i] == timpSlot[i + 1]) {
                if (i + 1 == maxEnd) {
                    flag = true;
                }
                i++;
                continue;
            }
            if (flag)
                System.out.println(start + " " + (i >= maxEnd ? maxEnd : (i + 1)) + " " + timpSlot[i]);
            else
                System.out.println(start + " " + i + " " + timpSlot[i]);
        }
        if (!flag) {
            System.out.println(maxEnd + " " + maxEnd + " " + timpSlot[maxEnd]);
        }

    }

    class Data {
        int startTime;
        int endTime;
        int price;
    }
}
