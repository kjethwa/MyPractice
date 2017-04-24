package main;

import java.util.HashMap;
import java.util.Map;

public class BehindEnemyLines {

    public static Map<Character, Integer> lookup;
    public static Map<Character, Integer> templookup;
    public static int count;
    
    public static void main(String[] args) {
        System.out.println(appearanceCount(4,11,"cAda","AbrAcadAbRa"));
    }
    
    public static int appearanceCount(int input1, int input2, String input3, String input4) {
        lookup = new HashMap<Character, Integer>();

        for (int i = 0; i < input1; i++) {
            if (lookup.get(input3.charAt(i)) == null) {
                lookup.put(input3.charAt(i), 1);
            }
            else {
                lookup.put(input3.charAt(i), lookup.get(input3.charAt(i)) + 1);
            }
        }
        templookup = new HashMap<Character, Integer>(lookup);
        
        for (int j = 0; j < input2-input1; j++) {
            for (int i = j; i < (j + input1); i++) {
                if (removeChar(input4.charAt(i))) {
                    if (templookup.isEmpty())
                        count++;
                }
                else {
                    break;
                }
            }
            templookup = new HashMap<Character, Integer>(lookup);
        }
        
        return count;
    }

    private static boolean removeChar(char key) {
        
        if(templookup.get(key)==null)
            return false;
        if(templookup.get(key)==1){
            templookup.remove(key);
            return true;
        }
        templookup.put(key,templookup.get(key)-1);
        
        return true;
    }
}

