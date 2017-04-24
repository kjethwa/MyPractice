package main;

import java.util.ArrayList;
import java.util.List;

public class CandidateTest {

    public static SuDoData suData;
    public static int size;
    public static boolean found = false;

    public static int SolveMagicSquare(int[][] input1) {

        size = input1.length;
        suData = new SuDoData(size);
        if (input1[0][0] == 0)
            input1[0][0] = 1;
        else if (input1[0][0] != 1)
            return 0;
        suData.setGridData(input1);
        suData.copyGridDataToSolutionData();
        solveNext(1, 1);
        return found ? 1 : 0;
    }

    public static boolean solveNext(int x, int y) {
        boolean flag = false;
        y++;
        if (y == size + 1) {
            if (x == size) {
                if (suData.isMagicMatrix()) {
                    found = true;

                    return true;
                }
                else
                    return false;
            }

            y = 1;
            x++;

        }
        if (suData.getData(x, y) == 0) {
            for (int data = 1; data <= size; data++) {
                flag = false;
                // System.out.println(x + "" + y + "" + data);
                if (suData.checkIsSafe(x, y, data)) {
                    suData.setData(x, y, 0);
                    suData.setData(x, y, data);
                    if (!found)
                        flag = solveNext(x, y);
                    else
                        return true;
                }
            }
            if (!flag) {
                // System.out.println("returnning false");
                suData.setData(x, y, 0);
                return false;
            }
        }
        else if (!found) {
            flag = solveNext(x, y);
            if (flag) {
                suData.setData(x, y, 0);
                return true;
            }
        }
        return false;

    }

    private static class SuDoData {

        /*
         * x and y is row and column index starting with 1 till 9 x == row y == column
         */

        /*
         * private int[][] gridData = { { 8, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 3, 6, 0, 0, 0, 0, 0 }, { 0, 7, 0, 0, 9, 0, 2, 0, 0
         * }, { 0, 5, 0, 0, 0, 7, 0, 0, 0 }, { 0, 0, 0, 0, 4, 5, 7, 0, 0 }, { 0, 0, 0, 1, 0, 0, 0, 3, 0 }, { 0, 0, 1, 0, 0, 0, 0,
         * 6, 8 }, { 0, 0, 8, 5, 0, 0, 0, 1, 0 }, { 0, 9, 0, 0, 0, 0, 4, 0, 0 } };
         */
        private int[][] gridData = {
                {
                        1, 2, 3, 0, 0, 0, 0, 0, 0 },
                {
                        0, 0, 0, 0, 0, 0, 1, 2, 3 },
                {
                        0, 0, 0, 1, 2, 3, 0, 0, 0 },
                {
                        2, 3, 1, 0, 0, 0, 0, 0, 0 },
                {
                        0, 0, 0, 0, 0, 0, 2, 3, 1 },
                {
                        0, 0, 0, 2, 3, 1, 0, 0, 0 },
                {
                        3, 1, 2, 0, 0, 0, 0, 0, 0 },
                {
                        0, 0, 0, 0, 0, 0, 3, 1, 2 },
                {
                        0, 0, 0, 3, 1, 2, 0, 0, 0 } };

        /*
         * private int[][] gridData = { { 1, 0, 0, 0 }, { 0, 0, 2, 0 }, { 0, 3, 0, 0 }, { 0, 0, 0, 4 } }; private int[][] gridData
         * = { { 1, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } };
         */

        private int[][] solutionGridData;
        private int N;
        private int size;

        public SuDoData() {

        }

        public SuDoData(int size) {
            solutionGridData = new int[size][size];
            this.size = size;
            N = (int) Math.sqrt(size);
        }

        public void copyGridDataToSolutionData() {
            for (int i = 0; i < size; i++)
                for (int j = 0; j < size; j++) {
                    solutionGridData[i][j] = gridData[i][j];
                }
        }

        public void setData(int x, int y, int data) {
            solutionGridData[x - 1][y - 1] = data;
        }

        public int getData(int x, int y) {
            return solutionGridData[x - 1][y - 1];
        }

        public boolean checkIsSafe(int x, int y, int data) {

            if (checkHori(x, y, data) && checkVerti(x, y, data) && checkBox(x, y, data))
                return true;
            else {
                return false;
            }
        }

        public boolean checkHori(int x, int y, int data) {

            for (int i = 0; i < y - 1; i++)
                if (solutionGridData[x - 1][i] == data)
                    return false;

            for (int i = y - 1; i < size; i++)
                if (gridData[x - 1][i] == data)
                    return false;

            return true;
        }

        public boolean checkVerti(int x, int y, int data) {
            for (int i = 0; i < x - 1; i++)
                if (solutionGridData[i][y - 1] == data)
                    return false;
            for (int i = x - 1; i < size; i++)
                if (gridData[i][y - 1] == data)
                    return false;

            return true;
        }

        public boolean checkBox(int x, int y, int data) {

            int boxX, boxY;
            boxX = x % N == 0 ? x / N : x / N + 1;
            boxY = y % N == 0 ? y / N : y / N + 1;
            for (int i = (boxX - 1) * N; (i < (boxX - 1) * N + N); i++)
                for (int j = (boxY - 1) * N; (j < (boxY - 1) * N + N); j++) {
                    // System.out.println(i + "" + j);
                    // check in solution data grid

                    if (i == x - 1 && j == y - 1) {
                        return checkInGridData(x, y, i, j, data);

                    }
                    if (solutionGridData[i][j] == data)
                        return false;

                }
            return true;
        }

        public boolean checkInGridData(int x, int y, int i, int jj, int data) {

            int boxX, boxY;
            boxX = x % N == 0 ? x / N : x / N + 1;
            boxY = y % N == 0 ? y / N : y / N + 1;

            for (; i < ((boxX - 1) * N + N); i++) {
                for (int j = (boxY - 1) * N; (j < (boxY - 1) * N + N); j++) {
                    // System.out.println(i + "" + j);
                    if (gridData[i][j] == data)
                        return false;
                    // check in data grid
                }
            }
            return true;
        }

        public boolean isMagicMatrix() {

            // check column
            for (int r = 0; r < N; r++) {
                for (int i = 0; i < N; i++) {
                    List<Integer> list = new ArrayList<Integer>();
                    for (int t = 0; t < N; t++)
                        list.add(getData(r * N + t + 1, i + 1));

                    for (int ii = i + N; ii < size; ii = ii + N) {
                        for (int t = 0; t < N; t++) {
                            if (!list.contains(getData(r * N + t + 1, ii + 1)))
                                return false;
                        }
                    }

                }
            }

            // check row
            for (int r = 0; r < N; r++) {
                for (int i = 0; i < N; i++) {
                    List<Integer> list = new ArrayList<Integer>();
                    for (int t = 0; t < N; t++)
                        list.add(getData(i + 1, r * N + t + 1));

                    for (int ii = i + N; ii < size; ii = ii + N) {
                        for (int t = 0; t < N; t++) {
                            if (!list.contains(getData(ii + 1, r * N + t + 1)))
                                return false;
                        }
                    }
                }
            }
            return true;
        }

        public void setGridData(int[][] gridData) {
            this.gridData = gridData;
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
