package main;

public class MaxiPathSum {

	public static int[] data;
	public static int[] solution;
	
	public static void main(String[] args) {
		
		data = new int[]{2,3,1,4,6,5};		
		solution = new int[data.length];
		
		solution[0] = data[0];		
		for (int i = 2; i <= data.length; i++) {
			int max = findAllDivisible(i);		
			solution[i-1] = max +data[i-1];
		}
		for(int i=0;i<solution.length;i++){
			System.out.print(solution[i]+" ");
		}
	}

	private static int findAllDivisible(int number) {
		int max = 0;
		
		for(int i=1;i<=Math.sqrt(number)+1 && i<number;i++){
			if(number%i==0){
				if (number / i == i){
					if(solution[i-1]>max)
						max = solution[i-1];
				}
				else {
					if(solution[i-1]>max)
						max = solution[i-1];
					if(solution[number / i -1]>max)
						max = solution[number / i -1];
				}
			}
		}
		
		return max;	
	}	
}
