package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RichieRich {

    static List<Integer> iPosition;
    static String number;
    static int n;
    static char[] dataArray;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        int k = in.nextInt();
        number = in.next();

        dataArray = number.toCharArray();
        
        iPosition = new ArrayList<Integer>();
        for (int i = 0, j = n - 1; i < j; i++, j--) {
            if (dataArray[i] != dataArray[j]) {
                iPosition.add(i);
                if (iPosition.size() > k) {
                    System.out.println("-1");
                    return;
                }
            }
        }

        if (k >= n) {
            dataArray = new char[n];
            for (int i = 0; i < n; i++)
                dataArray[i]='9';
        }
        else if (k == iPosition.size()) {
            appendMax();
        }
        else {
            int temp = k;
            int j = 0;
            int i = iPosition.size();
            while (temp > i && temp > 0 && j < n) {
                int index = j;

                if (index == (n - 1 - index)) {
                    if (dataArray[index] != '9') {
                        dataArray[index]='9';
                        temp--;
                    }
                }
                else if (temp > 1) {
                    if (dataArray[index]!= '9') {
                        dataArray[index]='9';
                        temp--;
                    }
                    if (dataArray[n - 1 - index] != '9') {
                        dataArray[n - 1 - index]='9';
                        temp--;
                    }
                }
                if (iPosition.contains(index))
                    iPosition.remove(new Integer(index));

                i = iPosition.size();
                j++;
            }
            appendMax();
        }
        for (int i = 0; i < n; i++)
            System.out.print(dataArray[i]);
    }

    public static void appendMax() {
        for (int j = 0; j < iPosition.size(); j++) {
            int index = iPosition.get(j);
            if (dataArray[index] > dataArray[n - 1 - index]) {
                dataArray[n - 1 - index] = dataArray[index];
            }
            else {
                dataArray[index] = dataArray[n - 1 - index];
            }
        }
    }
}