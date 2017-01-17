package main;

import java.util.*;

public class ChessboardGame {
	
	static int count=0;
	private static Scanner sc;
	int SIZE;

	public static void main(String[] args) {
		sc = new Scanner(System.in);
		int T = sc.nextInt();
		Coin[] coins;
		Coin tempCoin;
		ChessboardGame chessboardGame = new ChessboardGame();
		for (int i = 0; i < T; i++) {
			/*int K = sc.nextInt();*/
			int K = 1;
			coins = new Coin[K];
			for (int j = 0; j < K; j++) {
				tempCoin = chessboardGame.new Coin();
				tempCoin.X = sc.nextInt();
				tempCoin.Y = sc.nextInt();
				tempCoin.isDead = false;
				coins[j] = tempCoin;
			}
			chessboardGame.SIZE = coins.length;
			chessboardGame.calculateMoves(coins);
			//chessboardGame.display(coins);
			if (chessboardGame.isFirstPlayerWinning(coins)) {
				//chessboardGame.display(coins);
				System.out.println("Count="+count);
				System.out.println("First");
			} else {
				System.out.println("Second");
			}
		}
	}

	private boolean isFirstPlayerWinning(Coin[] coins) {
		
		List<Move> movesFirst = getAllMoves(coins);
		Move preMoveFirst=null;
		if(movesFirst.isEmpty()){
			return false;
		}
		
		for(Move moveFirst : movesFirst){
			if(preMoveFirst!=null){
				makeMove(coins, preMoveFirst);
			}
			preMoveFirst = getPreviousMove(coins,moveFirst);
			count++;
			//System.out.println("After first move");
			makeMove(coins,moveFirst);
			//display(coins);
			
			if(!isFirstPlayerWinning(coins)){
				return true;
			}
		}
		//System.out.println("Nooooooooooo!");
		return false;
	}

	private boolean makeSecondMove(Coin[] coins) {
		List<Move> movesSecond = getAllMoves(coins);
		Move preMoveSecond = null;
		if(movesSecond.isEmpty()){
			return false;
		}
		
		for(Move moveSecond : movesSecond){
			if(preMoveSecond!=null){
				makeMove(coins, preMoveSecond);
			}
			preMoveSecond = getPreviousMove(coins,moveSecond);
			makeMove(coins,moveSecond);
			count++;
			//System.out.println("After second move");
			//display(coins);
			
			if(!isFirstPlayerWinning(coins)){
				return true;
			}
			
		}
		//System.out.println("Yesssssssss!");
		return false;		
	}
	
	private void calculateMoves(Coin[] coins) {
		
		List<Move> moves = null;
		Move tempMove =null;
		for (int i = 0; i < SIZE; i++) {
			moves = new ArrayList<Move>();
			for (int j = 1; j <= 4; j++) {
				tempMove = getMoveOfCoin(coins[i], j);
				if (tempMove != null) {
					tempMove.CoinIndex = i;
					moves.add(tempMove);
				}
			}
			if(moves.isEmpty())
				coins[i].isDead = true;
			coins[i].moves = moves;
		}
	}
	
	private void calculateMoves(Coin coin, int i) {
		List<Move> moves = new ArrayList<Move>();
		Move tempMove = null;

		for (int j = 1; j <= 4; j++) {
			tempMove = getMoveOfCoin(coin, j);
			if (tempMove != null) {
				tempMove.CoinIndex = i;
				moves.add(tempMove);
			}
		}
		coin.moves = moves;

	}
	
	private boolean isDeadCoin(Coin coin){
		
		if(!((coin.X == 1 ||coin.X == 2 ) && (coin.Y==1 || coin.Y==2))){
			return false;
		}
		return true;
	}
	
	private void display(Coin[] coins){

		boolean flag;
		System.out.println();
		for(int i=1;i<=15;i++){
			for(int j=1;j<=15;j++){
				flag=false;
				for(int k=0;k<SIZE;k++){
					if(coins[k].X==j && coins[k].Y==i){
						System.out.print("C ");
						flag = true;
						break;
					}
				}
				if(!flag)
					System.out.print(". ");
			}
			System.out.println();
		}
	}
	
	private void makeMove(Coin[] coins, Move move) {
		coins[move.CoinIndex].X = move.X;
		coins[move.CoinIndex].Y = move.Y;
		coins[move.CoinIndex].isDead = isDeadCoin(coins[move.CoinIndex]);
		if(!coins[move.CoinIndex].isDead)
			calculateMoves(coins[move.CoinIndex],move.CoinIndex);
	}

	private Move getPreviousMove(Coin[] coins, Move move) {
		
		Move preMove = new Move();
		preMove.CoinIndex = move.CoinIndex;
		preMove.X = coins[move.CoinIndex].X;
		preMove.Y = coins[move.CoinIndex].Y;

		return preMove;
	}

	private List<Move> getAllMoves(Coin[] coins) {
		
		List<Move> moves = new ArrayList<Move>();
		for(int i=0;i<SIZE;i++){
			if(!coins[i].isDead){
				moves.addAll(new ArrayList<Move>(coins[i].moves));
			}
		}
		
		return moves;
	}
	
	private Move getMoveOfCoin(Coin coin, int moveNumber) {
		Move move = null;
		switch (moveNumber) {
		case 1:
			move = new Move();
			move.X = coin.X - 2;
			move.Y = coin.Y + 1;

			if (move.X <=0 || move.Y > 15) {
				move = null;
			}
			break;
		case 2:
			move = new Move();
			move.X = coin.X - 2;
			move.Y = coin.Y - 1;

			if (move.X <=0 || move.Y <= 0) {
				move = null;
			}
			break;
		case 3:
			move = new Move();
			move.X = coin.X + 1;
			move.Y = coin.Y - 2;

			if (move.X > 15 || move.Y <= 0) {
				move = null;
			}
			break;
		case 4:
			move = new Move();
			move.X = coin.X - 1;
			move.Y = coin.Y - 2;

			if (move.X <= 0 || move.Y <= 0) {
				move = null;
			}
			break;

		default:
			break;
		}
		return move;
	}

	class Coin {
		int X;
		int Y;
		boolean isDead;
		List<Move> moves;
	}

	class Move {
		int CoinIndex;
		int X;
		int Y;
	}
}