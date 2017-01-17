package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class ChessSimplified {

	final static int CHESS_SIZE = 4;
	static PiecePosition[][] chessBoard = new PiecePosition[CHESS_SIZE][CHESS_SIZE];
	final static String Q = "Q";
	final static String N = "N";
	final static String B = "B";
	final static String R = "R";
	final static String NO = "NO";
	final static String YES = "YES";
	static boolean flag = false;
	final static boolean WHITE = false;
	final static boolean BLACK = true;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		int g, w, b, m, Y, i;
		String piece, X;
		g = sc.nextInt();
		for (int z = 0; z < g; z++) {
			flag = false;
			chessBoard = new PiecePosition[CHESS_SIZE][CHESS_SIZE];
			w = sc.nextInt();
			b = sc.nextInt();
			m = sc.nextInt();

			for (i = 0; i < w; i++) {
				piece = sc.next();
				X = sc.next();
				Y = sc.nextInt();
				switch (X.charAt(0)) {
				case 'A':
					chessBoard[4 - Y][0] = new PiecePosition(WHITE, piece);
					break;
				case 'B':
					chessBoard[4 - Y][1] = new PiecePosition(WHITE, piece);
					break;
				case 'C':
					chessBoard[4 - Y][2] = new PiecePosition(WHITE, piece);
					break;
				case 'D':
					chessBoard[4 - Y][3] = new PiecePosition(WHITE, piece);
					break;

				default:
					break;
				}
			}
			for (i = 0; i < b; i++) {
				piece = sc.next();
				X = sc.next();
				Y = sc.nextInt();
				switch (X.charAt(0)) {
				case 'A':
					chessBoard[4 - Y][0] = new PiecePosition(BLACK, piece);
					break;
				case 'B':
					chessBoard[4 - Y][1] = new PiecePosition(BLACK, piece);
					break;
				case 'C':
					chessBoard[4 - Y][2] = new PiecePosition(BLACK, piece);
					break;
				case 'D':
					chessBoard[4 - Y][3] = new PiecePosition(BLACK, piece);
					break;

				default:
					break;
				}
			}

			/*System.out.println("CHESSBOARD");
			for (i = 0; i < CHESS_SIZE; i++) {
				for (int j = 0; j < CHESS_SIZE; j++) {
					if (chessBoard[i][j] != null) {
						System.out.print(chessBoard[i][j].getPiece());
					} else {
						System.out.print("X");
					}
				}
				System.out.println();
			}
*/
			placeNextMove(m);
			if (!flag) {
				System.out.println(NO);
			} else {
				System.out.println(YES);
			}
		}
	}

	private static void placeNextMove(int moves) {
		if (moves <= 0) {
			return;
		} else if (moves == 1) {
			// System.out.println(NO);
			for (int i = 0; i < CHESS_SIZE; i++) {
				for (int j = 0; j < CHESS_SIZE; j++) {
					if (chessBoard[i][j] != null) {
						if (chessBoard[i][j].color == WHITE) {
							if (captureQueen(chessBoard[i][j], j, i,BLACK)) {
								// System.out.println(YES);
								flag = true;
								return;
							}
						}
					}
				}
			}
		} else {
			List<int[]> allPossibleMoves;
			List<int[]> allPossibleBlackMoves;
			Stack<PiecePosition> stack = new Stack<PiecePosition>();
			for (int i = 0; i < CHESS_SIZE; i++) {
				for (int j = 0; j < CHESS_SIZE; j++) {
					if (chessBoard[i][j] != null) {
						if (chessBoard[i][j].color == WHITE) {
							if(captureQueen(chessBoard[i][j],j,i,BLACK)){
								//System.out.println(YES);
								flag=true;
								return ;
							}
							int[] axis = null ;
							allPossibleMoves = getAllPossibleMoves(
									chessBoard[i][j], j, i);
							if (allPossibleMoves != null) {
								for (int k = 0; k < allPossibleMoves.size(); k++) {
									axis = allPossibleMoves.get(k);
									if (k == 0) {
										stack.push(chessBoard[axis[1]][axis[0]]);
										chessBoard[axis[1]][axis[0]] = chessBoard[i][j];
										chessBoard[i][j] = null;
									} else {
										int[] lastAxis = allPossibleMoves
												.get(k - 1);
										
										 PiecePosition temp = stack.pop();
										stack.push(chessBoard[axis[1]][axis[0]]);
										chessBoard[axis[1]][axis[0]] = chessBoard[lastAxis[1]][lastAxis[0]];
										chessBoard[lastAxis[1]][lastAxis[0]] = temp;
									}
									if (isSafe()) {
										for(int l=0;l<CHESS_SIZE;l++){
											for(int m=0;m<CHESS_SIZE;m++){
												if(chessBoard[l][m]!=null){
													if(chessBoard[l][m].color == BLACK){
														int[] axisb = null ;
														allPossibleBlackMoves = getAllPossibleMoves(
																chessBoard[l][m], m, l);
														if(allPossibleBlackMoves!=null){
															for (int n = 0; n < allPossibleBlackMoves.size(); n++) {
																axisb = allPossibleBlackMoves.get(n);
																if (n == 0) {
																	stack.push(chessBoard[axisb[1]][axisb[0]]);
																	chessBoard[axisb[1]][axisb[0]] = chessBoard[l][m];
																	chessBoard[l][m] = null;
																} else {
																	int[] lastAxis = allPossibleBlackMoves
																			.get(n - 1);
																	
																	PiecePosition temp = stack.pop();

																	stack.push(chessBoard[axisb[1]][axisb[0]]);
																	chessBoard[axisb[1]][axisb[0]] = chessBoard[lastAxis[1]][lastAxis[0]];
																	chessBoard[lastAxis[1]][lastAxis[0]] = temp;
																}
																placeNextMove(moves - 2);
															}
															if(allPossibleBlackMoves.size()>0){
																PiecePosition temp = stack.pop();
																chessBoard[l][m] = chessBoard[axisb[1]][axisb[0]];
																chessBoard[axisb[1]][axisb[0]] = temp;
															}
														}
													}
												}
											}
										}

										
									}
								}
								if(allPossibleMoves.size()>0){
									PiecePosition temp = stack.pop();
									chessBoard[i][j] = chessBoard[axis[1]][axis[0]];
									chessBoard[axis[1]][axis[0]] = temp;
								}
							}
						}
					}
				}
			}
		}
	}

	private static List<int[]> getAllPossibleMoves(PiecePosition piece, int x,
			int y) {
		List<int[]> list = new ArrayList<int[]>();

		if (piece.getPiece().equalsIgnoreCase(Q)) {
			// vertical up
			for (int a = y - 1; a >= 0; a--) {
				if (chessBoard[a][x] == null ) {
					list.add(new int[] { x, a });
				}
				else if(chessBoard[a][x].color == piece.color){
					list.add(new int[] { x, a });
					break;
				}else{
					break;
				}
				
			}
			// vertical down
			for (int a = y + 1; a < CHESS_SIZE; a++) {
				if (chessBoard[a][x] == null ) {
					list.add(new int[] { x, a });
				}
				else if(chessBoard[a][x].color == piece.color){
					list.add(new int[] { x, a });
					break;
				}
				else{
					break;
				}
				
			}
			// horihontal left
			for (int a = x - 1; a >= 0; a--) {
				if (chessBoard[y][a] == null ) {
					list.add(new int[] { a, y });
				}
				else if(chessBoard[y][a].color == piece.color){
					list.add(new int[] { a, y });
					break;
				}
				else{
					break;
				}
				
			}
			// horizontal right
			for (int a = x + 1; a < CHESS_SIZE; a++) {
				if (chessBoard[y][a] == null ) {
					list.add(new int[] { a, y });
				}
				else if(chessBoard[y][a].color == piece.color){
					list.add(new int[] { a, y });
					break;
				}
				else{
					break;
				}
				
			}
			// top-left a->x b->y
			for (int a = x - 1, b = y - 1; a >= 0 && b >= 0; a--, b--) {
				if (chessBoard[b][a] == null ) {
					list.add(new int[] { a, b });
				}
				else if(chessBoard[b][a].color == piece.color){
					list.add(new int[] { a, b });
					break;
				}
				else{
					break;
				}
				
			}
			// top-right a->x b->y
			for (int a = x + 1, b = y - 1; a < CHESS_SIZE && b >= 0; a++, b--) {
				if (chessBoard[b][a] == null ) {
					list.add(new int[] { a, b });
				}
				else if(chessBoard[b][a].color == piece.color){
					list.add(new int[] { a, b });
					break;
				}
				else{
					break;
				}
				
			}
			// bottom-left a->x b->y
			for (int a = x - 1, b = y + 1; a >= 0 && b < CHESS_SIZE; a--, b++) {
				if (chessBoard[b][a] == null ) {
					list.add(new int[] { a, b });
				}
				else if(chessBoard[b][a].color == piece.color){
					list.add(new int[] { a, b });
				}
				else{
					break;
				}
				
			}
			// bottom-right a->x b->y
			for (int a = x + 1, b = y + 1; a < CHESS_SIZE && b < CHESS_SIZE; a++, b++) {
				if (chessBoard[b][a] == null ) {
					list.add(new int[] { a, b });
				}
				else if(chessBoard[b][a].color == piece.color){
					list.add(new int[] { a, b });
				}
				else{
					break;
				}
				
			}

		} else if (piece.getPiece().equalsIgnoreCase(N)) {
			// left a->x b->y
			int a, b;
			a = x - 2;
			if (a >= 0) {
				b = y - 1;
				if (b >= 0) {
					if (chessBoard[b][a] == null || chessBoard[b][a].color == piece.color) {
						list.add(new int[] { a, b });
					}
				}
				b = y + 1;
				if (b < CHESS_SIZE) {
					if (chessBoard[b][a] == null || chessBoard[b][a].color == piece.color) {
						list.add(new int[] { a, b });
					}
				}
			}
			// right
			a = x + 2;
			if (a < CHESS_SIZE) {
				b = y - 1;
				if (b >= 0) {
					if (chessBoard[b][a] == null || chessBoard[b][a].color == piece.color) {
						list.add(new int[] { a, b });
					}
				}
				b = y + 1;
				if (b < CHESS_SIZE) {
					if (chessBoard[b][a] == null || chessBoard[b][a].color == piece.color) {
						list.add(new int[] { a, b });
					}
				}
			}
			// top
			b = y - 2;
			if (b >= 0) {
				a = x - 1;
				if (a >= 0) {
					if (chessBoard[b][a] == null || chessBoard[b][a].color == piece.color) {
						list.add(new int[] { a, b });
					}
				}
				a = x + 1;
				if (a < CHESS_SIZE) {
					if (chessBoard[b][a] == null || chessBoard[b][a].color == piece.color) {
						list.add(new int[] { a, b });
					}
				}
			}
			// bottom
			b = y + 2;
			if (b < CHESS_SIZE) {
				a = x - 1;
				if (a >= 0) {
					if (chessBoard[b][a] == null || chessBoard[b][a].color == piece.color) {
						list.add(new int[] { a, b });
					}
				}
				a = x + 1;
				if (a < CHESS_SIZE) {
					if (chessBoard[b][a] == null || chessBoard[b][a].color == piece.color) {
						list.add(new int[] { a, b });
					}
				}
			}

		} else if (piece.getPiece().equalsIgnoreCase(B)) {
			// top-left a->x b->y
			for (int a = x - 1, b = y - 1; a >= 0 && b >= 0; a--, b--) {
				if (chessBoard[b][a] == null ) {
					list.add(new int[] { a, b });
				}
				else if(chessBoard[b][a].color == piece.color){
					list.add(new int[] { a, b });
					break;
				}
				else{
					break;
				}
			}
			// top-right a->x b->y
			for (int a = x + 1, b = y - 1; a < CHESS_SIZE && b >= 0; a++, b--) {
				if (chessBoard[b][a] == null ) {
					list.add(new int[] { a, b });
				}
				else if(chessBoard[b][a].color == piece.color){
					list.add(new int[] { a, b });
					break;
				}
				else{
					break;
				}
			}
			// bottom-left a->x b->y
			for (int a = x - 1, b = y + 1; a >= 0 && b < CHESS_SIZE; a--, b++) {
				if (chessBoard[b][a] == null ) {
					list.add(new int[] { a, b });
				}
				else if(chessBoard[b][a].color == piece.color){
					list.add(new int[] { a, b });
					break;
				}
				else{
					break;
				}
			}
			// bottom-right a->x b->y
			for (int a = x + 1, b = y + 1; a < CHESS_SIZE && b < CHESS_SIZE; a++, b++) {
				if (chessBoard[b][a] == null) {
					list.add(new int[] { a, b });
				}
				else if(chessBoard[b][a].color == piece.color){
					list.add(new int[] { a, b });
					break;
				}
				else{
					break;
				}
			}
		} else if (piece.getPiece().equalsIgnoreCase(R)) {
			// vertical up
			for (int a = y - 1; a >= 0; a--) {
				if (chessBoard[a][x] == null ) {
					list.add(new int[] { x, a });
				}
				else if(chessBoard[a][x].color == piece.color){
					list.add(new int[] { x, a });
				}
				else{
					break;
				}
			}
			// vertical down
			for (int a = y + 1; a < CHESS_SIZE; a++) {
				if (chessBoard[a][x] == null ) {
					list.add(new int[] { x, a });
				}
				else if(chessBoard[a][x].color == piece.color){
					list.add(new int[] { x, a });
					break;
				}
				else{
					break;
				}
			}
			// horihontal left
			for (int a = x - 1; a >= 0; a--) {
				if (chessBoard[y][a] == null ) {
					list.add(new int[] { a, y });
				}
				else if(chessBoard[y][a].color == piece.color){
					list.add(new int[] { a, y });
					break;
				}
				else{
					break;
				}
			}
			// horizontal right
			for (int a = x + 1; a < CHESS_SIZE; a++) {
				if (chessBoard[y][a] == null ) {
					list.add(new int[] { a, y });
				}
				else if(chessBoard[y][a].color == piece.color){
					list.add(new int[] { a, y });
					break;
				}
				else{
					break;
				}
			}
		}

		return list;
	}

	private static boolean captureQueen(PiecePosition piece, int x, int y,boolean color) {

		if (piece.getPiece().equalsIgnoreCase(Q)) {
			// vertical up
			for (int a = y - 1; a >= 0; a--) {
				if (chessBoard[a][x] != null ) {
					if ( chessBoard[a][x].color==color && chessBoard[a][x].getPiece().equalsIgnoreCase(Q)) {
						return true;
					}
					else{
						break;
					}
				}
			}
			// vertical down
			for (int a = y + 1; a < CHESS_SIZE; a++) {
				if (chessBoard[a][x] != null ) {
					if (chessBoard[a][x].color ==color && chessBoard[a][x].getPiece().equalsIgnoreCase(Q)) {
						return true;
					}
					else{
						break;
					}
				}
			}
			// horihontal left
			for (int a = x - 1; a >= 0; a--) {
				if (chessBoard[y][a] != null ) {
					if (chessBoard[y][a].color==color && chessBoard[y][a].getPiece().equalsIgnoreCase(Q)) {
						return true;
					}
					else{
						break;
					}
				}
			}
			// horizontal right
			for (int a = x + 1; a < CHESS_SIZE; a++) {
				if (chessBoard[y][a] != null ) {
					if (chessBoard[y][a].color==color && chessBoard[y][a].getPiece().equalsIgnoreCase(Q)) {
						return true;
					}
					else{
						break;
					}
				}
			}
			// top-left a->x b->y
			for (int a = x - 1, b = y - 1; a >= 0 && b >= 0; a--, b--) {
				if (chessBoard[b][a] != null ) {
					if (chessBoard[b][a].color==color && chessBoard[b][a].getPiece().equalsIgnoreCase(Q)) {
						return true;
					}
					else{
						break;
					}
				}
			}
			// top-right a->x b->y
			for (int a = x + 1, b = y - 1; a < CHESS_SIZE && b >= 0; a++, b--) {
				if (chessBoard[b][a] != null ) {
					if (chessBoard[b][a].color==color && chessBoard[b][a].getPiece().equalsIgnoreCase(Q)) {
						return true;
					}
					else{
						break;
					}
				}
			}
			// bottom-left a->x b->y
			for (int a = x - 1, b = y + 1; a >= 0 && b < CHESS_SIZE; a--, b++) {
				if (chessBoard[b][a] != null) {
					if (chessBoard[b][a].color==color && chessBoard[b][a].getPiece().equalsIgnoreCase(Q)) {
						return true;
					}
					else{
						break;
					}
				}
			}
			// bottom-right a->x b->y
			for (int a = x + 1, b = y + 1; a < CHESS_SIZE && b < CHESS_SIZE; a++, b++) {
				if (chessBoard[b][a] != null ) {
					if (chessBoard[b][a].color==color && chessBoard[b][a].getPiece().equalsIgnoreCase(Q)) {
						return true;
					}
					else{
						break;
					}
				}
			}

		} else if (piece.getPiece().equalsIgnoreCase(N)) {
			// left a->x b->y
			int a, b;
			a = x - 2;
			if (a >= 0) {
				b = y - 1;
				if (b >= 0) {
					if (chessBoard[b][a] != null && chessBoard[b][a].color==color) {
						if (chessBoard[b][a].getPiece().equalsIgnoreCase(Q)) {
							return true;
						}
					}
					// list.add(new int[]{a,b});
				}
				b = y + 1;
				if (b < CHESS_SIZE) {
					if (chessBoard[b][a] != null && chessBoard[b][a].color==color) {
						if (chessBoard[b][a].getPiece().equalsIgnoreCase(Q)) {
							return true;
						}
					}
					// list.add(new int[]{a,b});
				}
			}
			// right
			a = x + 2;
			if (a < CHESS_SIZE) {
				b = y - 1;
				if (b >= 0) {
					if (chessBoard[b][a] != null && chessBoard[b][a].color==color) {
						if (chessBoard[b][a].getPiece().equalsIgnoreCase(Q)) {
							return true;
						}
					}
					// list.add(new int[]{a,b});
				}
				b = y + 1;
				if (b < CHESS_SIZE) {
					if (chessBoard[b][a] != null && chessBoard[b][a].color==color) {
						if (chessBoard[b][a].getPiece().equalsIgnoreCase(Q)) {
							return true;
						}
					}
					// list.add(new int[]{a,b});
				}
			}
			// top
			b = y - 2;
			if (b >= 0) {
				a = x - 1;
				if (a >= 0) {
					if (chessBoard[b][a] != null && chessBoard[b][a].color==color) {
						if (chessBoard[b][a].getPiece().equalsIgnoreCase(Q)) {
							return true;
						}
					}
					// list.add(new int[]{a,b});
				}
				a = x + 1;
				if (a < CHESS_SIZE) {
					if (chessBoard[b][a] != null && chessBoard[b][a].color==color) {
						if (chessBoard[b][a].getPiece().equalsIgnoreCase(Q)) {
							return true;
						}
					}
					// list.add(new int[]{a,b});
				}
			}
			// bottom
			b = y + 2;
			if (b < CHESS_SIZE) {
				a = x - 1;
				if (a >= 0) {
					if (chessBoard[b][a] != null && chessBoard[b][a].color==color) {
						if (chessBoard[b][a].getPiece().equalsIgnoreCase(Q)) {
							return true;
						}
					}
					// list.add(new int[]{a,b});
				}
				a = x + 1;
				if (a < CHESS_SIZE) {
					if (chessBoard[b][a] != null && chessBoard[b][a].color==color) {
						if (chessBoard[b][a].getPiece().equalsIgnoreCase(Q)) {
							return true;
						}
					}
					// list.add(new int[]{a,b});
				}
			}

		} else if (piece.getPiece().equalsIgnoreCase(B)) {
			// top-left a->x b->y
			for (int a = x - 1, b = y - 1; a >= 0 && b >= 0; a--, b--) {
				if (chessBoard[b][a] != null ) {
					if (chessBoard[b][a].color==color && chessBoard[b][a].getPiece().equalsIgnoreCase(Q)) {
						return true;
					}
					else{
						break;
					}
				}
				// list.add(new int[]{a,b});
			}
			// top-right a->x b->y
			for (int a = x + 1, b = y - 1; a < CHESS_SIZE && b >= 0; a++, b--) {
				if (chessBoard[b][a] != null ) {
					if (chessBoard[b][a].color==color && chessBoard[b][a].getPiece().equalsIgnoreCase(Q)) {
						return true;
					}
					else{
						break;
					}
				}
				// list.add(new int[]{a,b});
			}
			// bottom-left a->x b->y
			for (int a = x - 1, b = y + 1; a >= 0 && b < CHESS_SIZE; a--, b++) {
				if (chessBoard[b][a] != null ) {
					if (chessBoard[b][a].color==color && chessBoard[b][a].getPiece().equalsIgnoreCase(Q)) {
						return true;
					}
					else{
						break;
					}
				}
				// list.add(new int[]{a,b});
			}
			// bottom-right a->x b->y
			for (int a = x + 1, b = y + 1; a < CHESS_SIZE && b < CHESS_SIZE; a++, b++) {
				if (chessBoard[b][a] != null ) {
					if (chessBoard[b][a].color==color && chessBoard[b][a].getPiece().equalsIgnoreCase(Q)) {
						return true;
					}
					else{
						break;
					}
				}
				// list.add(new int[]{a,b});
			}
		} else if (piece.getPiece().equalsIgnoreCase(R)) {
			// vertical up
			for (int a = y - 1; a >= 0; a--) {
				if (chessBoard[a][x] != null ) {
					if (chessBoard[a][x].color==color && chessBoard[a][x].getPiece().equalsIgnoreCase(Q)) {
						return true;
					}
					else{
						break;
					}
				}
			}
			// vertical down
			for (int a = y + 1; a < CHESS_SIZE; a++) {
				if (chessBoard[a][x] != null ) {
					if (chessBoard[a][x].color==color && chessBoard[a][x].getPiece().equalsIgnoreCase(Q)) {
						return true;
					}
					else{
						break;
					}
				}
			}
			// horihontal left
			for (int a = x - 1; a >= 0; a--) {
				if (chessBoard[y][a] != null ) {
					if (chessBoard[y][a].color==color && chessBoard[y][a].getPiece().equalsIgnoreCase(Q)) {
						return true;
					}
					else{
						break;
					}
				}
			}
			// horizontal right
			for (int a = x + 1; a < CHESS_SIZE; a++) {
				if (chessBoard[y][a] != null ) {
					if (chessBoard[y][a].color==color && chessBoard[y][a].getPiece().equalsIgnoreCase(Q)) {
						return true;
					}
					else{
						break;
					}
				}
			}
		}

		return false;
	}

	private static boolean isSafe() {

		for (int i = 0; i < CHESS_SIZE; i++) {
			for (int j = 0; j < CHESS_SIZE; j++) {
				if (chessBoard[i][j] != null) {
					if (chessBoard[i][j].color == BLACK) {
						if(captureQueen(chessBoard[i][j], j, i, WHITE)){
							return false;
						}
					}
				}
			}
		}
		return true;
	}

}

class PiecePosition {
	boolean color;
	String piece;

	public PiecePosition(boolean black, String piece) {
		super();
		this.color = black;
		this.piece = piece;
	}

	public boolean isBlack() {
		return color;
	}

	public void setBlack(boolean black) {
		this.color = black;
	}

	public String getPiece() {
		return piece;
	}

	public void setPiece(String piece) {
		this.piece = piece;
	}

}
