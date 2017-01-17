package main;

import java.util.StringTokenizer;

public class ArmyHelicopter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 //new String[]{"X#0#0#0#X#0","X#0#0#0#X#X","X#0#0#0#X#X","X#0#X#0#0#X","X#0#X#0#0#X"}
		//System.out.println(landingPosition(new int[]{6,5}, new String[]{"X#0#0#0#X#0","X#0#0#0#X#X","X#0#0#0#X#X","X#0#X#0#0#X"}));
	}

	public static void main(char[] args) {
		// TODO Auto-generated method stub
		 //new String[]{"X#0#0#0#X#0","X#0#0#0#X#X","X#0#0#0#X#X","X#0#X#0#0#X","X#0#X#0#0#X"}
		//System.out.println(landingPosition(new int[]{6,5}, new String[]{"X#0#0#0#X#0","X#0#0#0#X#X","X#0#0#0#X#X","X#0#X#0#0#X"}));
	}
	private int landingPosition(int[] input1,String[] input2)
    {
		if(input2.length!=input1[1]){
			return -1;
		}
		
		String[][] data = new String[input1[1]][input1[0]];
		int size = 0,endX,endY,maxSize=0;
		
		for(int i=0;i<input2.length;i++){
			StringTokenizer st = new StringTokenizer(input2[i],"#");
			if(st.countTokens()!=input1[0]){
				return -1;
			}
			for(int j=0;j<input1[0];j++){
				data[i][j] = st.nextToken();
			}
		}
		
		for(int i=0;i<input1[1];i++){
			loop : for(int j=0;j<input1[0];j++){
				if(!data[i][j].equalsIgnoreCase("X")){
					size=0;
					endX = j;endY = i;
					while(endX<input1[0] && endY<input1[1]){
						int topX = j,topY=i;
						while(topX<endX &&topY<endY ){
							if(data[endY][topX++].equalsIgnoreCase("X")){
								continue loop;
							}
							if(data[topY++][endY].equalsIgnoreCase("X")){
								continue loop;
							}
						}
						size++;
						endX++;
						endY++;
					}
					if(size>maxSize){
						maxSize=size;
					}
					
				}
			}
		}
		
		return maxSize;
    }
}

class A extends ArmyHelicopter{

	public int landingPosition(int[] input1, String[] input2) {
		// TODO Auto-generated method stub
		//return super.landingPosition(input1, input2);
		return 1;
	}
	
}
