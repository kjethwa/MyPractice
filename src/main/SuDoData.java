package main;

public class SuDoData {

	/*
	 * x and y is row and column index starting with 1 till 9 x == row y ==
	 * column
	 */

	private int[][] gridData = { { 8, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 3, 6, 0, 0, 0, 0, 0 }, { 0, 7, 0, 0, 9, 0, 2, 0, 0 },
			{ 0, 5, 0, 0, 0, 7, 0, 0, 0 }, { 0, 0, 0, 0, 4, 5, 7, 0, 0 },
			{ 0, 0, 0, 1, 0, 0, 0, 3, 0 }, { 0, 0, 1, 0, 0, 0, 0, 6, 8 },
			{ 0, 0, 8, 5, 0, 0, 0, 1, 0 }, { 0, 9, 0, 0, 0, 0, 4, 0, 0 } };

	private int[][] solutionGridData = new int[9][9];

	public SuDoData() {

	}

	public void copyGridDataToSolutionData() {
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++) {
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

		if (checkHori(x, y, data) && checkVerti(x, y, data)
				&& checkBox(x, y, data))
			return true;
		else {
			return false;
		}
	}

	public boolean checkHori(int x, int y, int data) {

		for (int i = 0; i < y - 1; i++)
			if (solutionGridData[x - 1][i] == data)
				return false;

		for (int i = y - 1; i < 9; i++)
			if (gridData[x - 1][i] == data)
				return false;

		return true;
	}

	public boolean checkVerti(int x, int y, int data) {
		for (int i = 0; i < x - 1; i++)
			if (solutionGridData[i][y - 1] == data)
				return false;
		for (int i = x - 1; i < 9; i++)
			if (gridData[i][y - 1] == data)
				return false;

		return true;
	}

	public boolean checkBox(int x, int y, int data) {

		int boxX, boxY;
		boxX = x % 3 == 0 ? x / 3 : x / 3 + 1;
		boxY = y % 3 == 0 ? y / 3 : y / 3 + 1;
		for (int i = (boxX - 1) * 3; (i < (boxX - 1) * 3 + 3); i++)
			for (int j = (boxY - 1) * 3; (j < (boxY - 1) * 3 + 3); j++) {
				//System.out.println(i + "" + j);
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
		boxX = x % 3 == 0 ? x / 3 : x / 3 + 1;
		boxY = y % 3 == 0 ? y / 3 : y / 3 + 1;

		for (; i < ((boxX - 1) * 3 + 3); i++) {
			for (int j = (boxY - 1) * 3; (j < (boxY - 1) * 3 + 3); j++) {
				//System.out.println(i + "" + j);
				if (gridData[i][j] == data)
					return false;
				// check in data grid
			}
		}
		return true;
	}

	public static void main(String a[]) {
		new SuDoData().checkHori(5, 5, 5);
	}
}
