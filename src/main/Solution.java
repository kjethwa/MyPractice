package main;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int EXPECTED_COUNT = n/4;
        String s = sc.next();
        
        Map<Character,Integer> charCount = new HashMap<Character,Integer>();
        charCount.put('A', 0);
        charCount.put('G', 0);
        charCount.put('T', 0);
        charCount.put('C', 0);
        
        for (int i = 0; i < n; i++) {
           charCount.put(s.charAt(i),charCount.get(s.charAt(i))+1);            
        }
        
        char[] stringInChar = s.toCharArray();
        
        int minLength=0;
        Map<Character,Integer> charToBeRemoved = new HashMap<Character,Integer >();
        Set<Character> characterSet = charCount.keySet();
        for (Character character : characterSet) {
            if(charCount.get(character)<EXPECTED_COUNT){
                minLength+=EXPECTED_COUNT-charCount.get(character);
            }
            else if(charCount.get(character)>EXPECTED_COUNT){
                charToBeRemoved.put(character,charCount.get(character)-EXPECTED_COUNT);
            }
        }
        if(charToBeRemoved.isEmpty()){
            System.out.println(minLength);
            return;
        }
        Map<Character,Integer> tempCharToBeRemoved = new HashMap<Character,Integer>(charToBeRemoved);
		Set<Character> charSet = tempCharToBeRemoved.keySet();

		for (Character character : charSet) {
			tempCharToBeRemoved.put(character, 0);
		}
        
        int solution=n,j=0,i=0;
        
        while(!charToBeRemoved.containsKey(stringInChar[i]))
        	i++;
        
        for(;i<=n-minLength&&j<n;){
	        	
        		if(charToBeRemoved.containsKey(stringInChar[j])){
            		tempCharToBeRemoved.put(stringInChar[j],tempCharToBeRemoved.get(stringInChar[j])+1);
            	}
        		if(isSubsetSolution(charToBeRemoved,tempCharToBeRemoved)){
        			if(j-i+1<solution)
        				solution = j-i+1;
        			i = removeLeftChar(i,j,charToBeRemoved,tempCharToBeRemoved,stringInChar,solution);
        			
        			while(isSubsetSolution(charToBeRemoved, tempCharToBeRemoved)){
        				tempCharToBeRemoved.put(stringInChar[j], tempCharToBeRemoved.get(stringInChar[j]) - 1);
        				j--;
        				while(!tempCharToBeRemoved.containsKey(stringInChar[j])){
        					j--;
        				}
        			}
        		}
        		j++;
        }
        
        System.out.println(solution); 
        
    }

	

	private static int removeLeftChar(int i,int j,
			Map<Character, Integer> charToBeRemoved,
			Map<Character, Integer> tempCharToBeRemoved, char[] stringInChar,int solution) {
		
		
		tempCharToBeRemoved.put(stringInChar[i], tempCharToBeRemoved.get(stringInChar[i]) - 1);
		i++;
		while(!tempCharToBeRemoved.containsKey(stringInChar[i])){
			i++;
		}
		
		
		return i;
	}



	private static boolean isSubsetSolution(Map<Character,Integer> charToBeRemoved,Map<Character,Integer> tempCharToBeRemoved  ) {
		
		Set<Character> tempCharSet = tempCharToBeRemoved.keySet();
		Set<Character> charSet = charToBeRemoved.keySet();
		
		for (Character character : charSet) {
			if(!tempCharSet.contains(character)){
				return false;
			}
			if(tempCharToBeRemoved.get(character)<charToBeRemoved.get(character)){
				return false;
			}
		}
		
		return true;
	}
    
}