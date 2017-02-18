package main;

import java.util.Scanner;

public class QueensAttack {

	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int rQueen = in.nextInt();
        int cQueen = in.nextInt();
		int count = 0, vertivalTop = n - rQueen, verticalBottom = rQueen - 1, horizontalLeft = cQueen - 1, horizontalright = n
				- cQueen,temp;
		int topRight = Math.min(vertivalTop, horizontalright)
				,topLeft = Math.min(vertivalTop, horizontalLeft)
				,bottomRight = Math.min(verticalBottom, horizontalright)
				,bottomLeft = Math.min(verticalBottom, horizontalLeft);
        
        boolean[][] grid = new boolean[n+1][n+1];
        for(int a0 = 0; a0 < k; a0++){
            int rObstacle = in.nextInt();
            int cObstacle = in.nextInt();
            grid[rObstacle][cObstacle] = true;
            
            if(rObstacle==rQueen){
            	temp = Math.abs(cObstacle-cQueen)-1;
            	if(cObstacle<cQueen){
            		if(temp< horizontalLeft){
            			horizontalLeft = temp; 
            		}
            	}
            	else{
            		if(temp< horizontalright){
            			horizontalright = temp; 
            		}
            	}
            }
            
            if(cObstacle==cQueen){
            	temp = Math.abs(rObstacle-rQueen)-1;
            	if(rObstacle<rQueen){
            		if(temp< verticalBottom){
            			verticalBottom = temp; 
            		}
            	}
            	else{
            		if(temp< vertivalTop){
            			vertivalTop = temp; 
            		}
            	}
            }
            
            if(Math.abs(rObstacle-rQueen) == Math.abs(cObstacle-cQueen)){
            	temp = Math.abs(rObstacle-rQueen)-1;
            	if(rQueen - rObstacle<0){
            		if(cQueen - cObstacle<0){
            			if(temp <topRight)
            				topRight = temp;
            		}
            		else{
            			if(temp <topLeft)
            				topLeft = temp;
            		}
            			
            	}
            	else{
            		if(cQueen - cObstacle<0){
            			if(temp <bottomRight)
            				bottomRight = temp;
            		}
            		else{
            			if(temp <bottomLeft)
            				bottomLeft = temp;
            		}
            	}
            }
            
            
        }
        count=verticalBottom+vertivalTop+horizontalLeft+horizontalright+topLeft+topRight+bottomLeft+bottomRight;
        System.out.println(count);
    }

}
