package main;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class KnightLonChessboard {

    static int[][] knightlOnAChessboard(int n) {
        int[][] solution = new int[n - 1][n - 1];
        for (int i = 1; i < n; i++) {
            for (int j = i; j < n; j++) {
                solution[i - 1][j - 1] = BFS(i, j, n);
				solution[j - 1][i - 1] = solution[i - 1][j - 1];
            }
        }

        return solution;
    }

    static class Move {
        int x, y;

        public Move(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int BFS(int a, int b, int n) {
        Queue<Move> queue = new LinkedList<>();
        Queue<Move> tempQueue = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];
        int count = 0;
        queue.add(new Move(0, 0));
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            Move currNode = queue.remove();

            if (currNode.x == n - 1 && currNode.y == n - 1) {
                return count;
            }

            addToTempQueue(currNode.x + a, currNode.y + b, n, tempQueue, visited);
            addToTempQueue(currNode.x + a, currNode.y - b, n, tempQueue, visited);
            addToTempQueue(currNode.x - a, currNode.y + b, n, tempQueue, visited);
            addToTempQueue(currNode.x - a, currNode.y - b, n, tempQueue, visited);

			addToTempQueue(currNode.x + b, currNode.y + a, n, tempQueue, visited);
			addToTempQueue(currNode.x + b, currNode.y - a, n, tempQueue, visited);
			addToTempQueue(currNode.x - b, currNode.y + a, n, tempQueue, visited);
			addToTempQueue(currNode.x - b, currNode.y - a, n, tempQueue, visited);

            if (queue.isEmpty()) {
                count++;
                queue.addAll(tempQueue);
                tempQueue.clear();
            }

        }

        return -1;
    }

    private static void addToTempQueue(int x, int y, int n, Queue<Move> tempQueue, boolean[][] visited) {
        if (inRange(x,y,n) && (!visited[x][y])) {
            tempQueue.add(new Move(x, y));
            visited[x][y] = true;
        }
    }

	private static boolean inRange(int x, int y, int n) {
		return x >= 0 && x < n && y >= 0 && y < n;
	}

	private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[][] result = knightlOnAChessboard(n);

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                //bufferedWriter.write(String.valueOf(result[i][j]));
				System.out.print(String.valueOf(result[i][j]));

                if (j != result[i].length - 1) {
					System.out.print(" ");
                }
            }

            if (i != result.length - 1) {
				System.out.print("\n");
            }
        }

        //bufferedWriter.newLine();

		//bufferedWriter.close();

        scanner.close();
    }
}
