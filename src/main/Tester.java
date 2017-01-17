package main;

public class Tester {

	public static SuDoData suData = new SuDoData();

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		suData.copyGridDataToSolutionData();
		solveNext(1, 1);
		/*
		 * if (solveNext(1, 1)) System.out.println("solved"); else
		 * System.out.println("not solved");
		 * 
		 * for (int i = 1; i <= 9; i++) {
		 * 
		 * for (int j = 1; j <= 9; j++) { System.out.print(suData.getData(i,
		 * j)); } System.out.println(); }
		 */
	}

	public static boolean solveNext(int x, int y) {
		boolean flag = false;
		y++;
		if (y == 10) {
			if (x == 9) {
				// System.out.println("danger");
				for (int i = 1; i <= 9; i++) {

					for (int j = 1; j <= 9; j++) {
						System.out.print(suData.getData(i, j));
					}
					System.out.println();
				}
				return true;
			}

			y = 1;
			x++;

		}
		if (suData.getData(x, y) == 0) {
			for (int data = 1; data < 10; data++) {
				flag = false;
				// System.out.println(x + "" + y + "" + data);
				if (suData.checkIsSafe(x, y, data)) {
					suData.setData(x, y, 0);
					suData.setData(x, y, data);
					flag = solveNext(x, y);
				}
			}
			if (!flag) {
				// System.out.println("returnning false");
				suData.setData(x, y, 0);
				return false;
			}
		} else {
			flag = solveNext(x, y);
			if (flag) {
				suData.setData(x, y, 0);
				return true;
			}
		}
		return false;

	}
}
