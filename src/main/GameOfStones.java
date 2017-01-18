package main;

import java.util.Scanner;

public class GameOfStones {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		GameOfStones gameOfStones = new GameOfStones();
		
		for(int i=0;i<T;i++){
			int N = sc.nextInt();
			if(gameOfStones.makeFirstMove(N)){
				System.out.println("First");
			}
			else{
				System.out.println("Second");
			}
		}

	}

	private boolean makeFirstMove(int N) {
		if(N>1){
			for(int i=1;i<=3;i++){
				if(i==1 && N>=2){
					N-=2;
					if(!makeFirstMove(N)){
						return true;
					}
					N+=2;
				}
				if(i==2 && N>=3){
					N-=3;
					if(!makeFirstMove(N)){
						return true;
					}
					N+=3;
				}
				if(i==3 && N>=5){
					N-=5;
					if(!makeFirstMove(N)){
						return true;
					}
					N+=5;
				}
			}
		}
		return false;
	}

	private boolean makeSecondMove(int N) {
		
		if(N>1){
			for(int i=1;i<=3;i++){
				if(i==1 && N>=2){
					N-=2;
					if(!makeFirstMove(N)){
						return true;
					}
					N+=2;
				}
				if(i==2 && N>=3){
					N-=3;
					if(!makeFirstMove(N)){
						return true;
					}
					N+=3;
				}
				if(i==3 && N>=5){
					N-=5;
					if(!makeFirstMove(N)){
						return true;
					}
					N+=5;
				}
			}
		}
		return false;
	}

}
