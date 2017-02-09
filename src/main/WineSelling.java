package main;

public class WineSelling {

    public static int price[];
    public static int N;
    public static int count = 0;
    public static int[][] lookup;

    public static void main(String[] args) {
        price = new int[] {
                2, 3, 5, 1, 4 };
        N = price.length;
        lookup = new int[N][N];
        System.out.println(solve(0, 4));
        System.out.println(count);
    }

    private static int solve(int start, int end) {
        count++;

        if (start <= end) {
            if (lookup[start][end] != 0)
                return lookup[start][end];
            int year = N - (end - start + 1) + 1, A = 0, B = 0;

            A = year * price[start] + solve(start + 1, end);

            B = year * price[end] + solve(start, end - 1);
            lookup[start][end] = Math.max(A, B);
            return lookup[start][end];
        }
        return 0;
    }

}
