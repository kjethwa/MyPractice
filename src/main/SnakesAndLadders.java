package main;

import java.io.IOException;
import java.util.*;

public class SnakesAndLadders {

    static boolean visited[];
    static Map<Integer, Integer> ladderSnakesMap;
    static int DESTINATION_NODE = 100;

    // Complete the quickestWayUp function below.
    static int quickestWayUp(int[][] ladders, int[][] snakes) {

        ladderSnakesMap = new HashMap<>();
        visited = new boolean[101];

        int ladderSize = ladders.length;
        int snakesSize = snakes.length;
        for (int i = 0; i < ladderSize; i++) {
            ladderSnakesMap.put(ladders[i][0], ladders[i][1]);
        }

        for (int i = 0; i < snakesSize; i++) {
            ladderSnakesMap.put(snakes[i][0], snakes[i][1]);
        }

        int steps = BFS();

		return steps == Integer.MAX_VALUE ? -1 : steps;
    }

    private static int BFS() {
        Queue<Integer> queue = new LinkedList<>();
        Queue<Integer> queueTemp = new LinkedList<>();
        queue.add(1);
        visited[1] = true;

        int levelCount = 0;
        int minSteps = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            int currNode = queue.remove();
            if (currNode == DESTINATION_NODE && levelCount < minSteps) {
                minSteps = levelCount;
            }

            for (int i = 1; i <= 6; i++) {
                int temp = currNode + i;
                if (temp <= 100 && !visited[temp]) {
					if (ladderSnakesMap.get(temp) != null) {
						if (!visited[ladderSnakesMap.get(temp)]) {
							queueTemp.add(ladderSnakesMap.get(temp));
							visited[ladderSnakesMap.get(temp)] = true;
						}
                    } else {
                        queueTemp.add(temp);
                        visited[temp] = true;
                    }

                }
            }

            if (queueTemp.size() > 0 & queue.size() == 0) {
                queue.addAll(queueTemp);
                queueTemp.clear();
                levelCount++;
            }

        }

        return minSteps;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[][] ladders = new int[n][2];

            for (int i = 0; i < n; i++) {
                String[] laddersRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 2; j++) {
                    int laddersItem = Integer.parseInt(laddersRowItems[j]);
                    ladders[i][j] = laddersItem;
                }
            }

            int m = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[][] snakes = new int[m][2];

            for (int i = 0; i < m; i++) {
                String[] snakesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 2; j++) {
                    int snakesItem = Integer.parseInt(snakesRowItems[j]);
                    snakes[i][j] = snakesItem;
                }
            }

            int result = quickestWayUp(ladders, snakes);

            System.out.println(result);
            //bufferedWriter.write(String.valueOf(result));
            //bufferedWriter.newLine();
        }

        //bufferedWriter.close();

        scanner.close();
    }
}
