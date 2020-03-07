package main;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class ClimbingTheLeaderBoard {

    // Complete the climbingLeaderboard function below.
    static int[] climbingLeaderboard(int[] scores, int[] alice) {
        int[] currentRanks = new int[scores.length];
        int[] solutions = new int[alice.length];
        int max = -1;
        int min = Integer.MAX_VALUE;

        int curRank=1;
        currentRanks[0] = 1;

        if(scores[0]<min) min = scores[0];
        if(scores[0]>max) max = scores[0];

        for (int i = 1; i < scores.length; i++) {
            if (scores[i] != scores[i-1]) {
                curRank++;
            }
            if(scores[i]<min) min = scores[i];
            if(scores[i]>max) max = scores[i];
            currentRanks[i] = curRank;
        }

        for (int i = 0; i < alice.length; i++) {
            if (alice[i] > max) {
                solutions[i] = 1;
            } else if (alice[i] < min) {
                solutions[i] = curRank + 1;
            } else {
                int index = binarySearch(scores, alice[i], 0, scores.length - 1);

                solutions[i] = currentRanks[index];
            }
        }

        return solutions;
    }

    private static int binarySearch(int[] scores, int key, int start, int end) {
        int mid = (start + end) / 2;
        if (start < end) {
            if (key == scores[mid]) {
                return mid;
            } else if (key > scores[mid]) {
                return binarySearch(scores, key, start, mid);
            } else {
                return binarySearch(scores, key, mid + 1, end);
            }
        } else if (start == end) {
            return start;
        }

        return -1;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int scoresCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] scores = new int[scoresCount];

        String[] scoresItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < scoresCount; i++) {
            int scoresItem = Integer.parseInt(scoresItems[i]);
            scores[i] = scoresItem;
        }

        int aliceCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] alice = new int[aliceCount];

        String[] aliceItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < aliceCount; i++) {
            int aliceItem = Integer.parseInt(aliceItems[i]);
            alice[i] = aliceItem;
        }

        int[] result = climbingLeaderboard(scores, alice);

        for (int i = 0; i < result.length; i++) {
            //bufferedWriter.write(String.valueOf(result[i]));
            System.out.print(result[i] + " " );

            if (i != result.length - 1) {
                //bufferedWriter.write("\n");
                //System.out.println("");
            }
        }

        //bufferedWriter.newLine();

        //bufferedWriter.close();

        scanner.close();
    }
}
