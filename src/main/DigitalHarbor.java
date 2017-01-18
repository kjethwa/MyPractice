package main;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class DigitalHarbor {
    
    
    static int size;
    static int[][] firstMatrix = new int[size][size];
    static int[][] secondMatrix = new int[size][size];

    public static void main(String[] args) {
        // TODO Auto-generated method stub
         System.out.println(Mathematician(3,new String[]{"11#3#44","12#26#13","21#33#27"},new
         String[]{"33#44#11","3#13#12","21#26#27"}));
//        System.out.println(Mathematician(
//                2,
//                new String[] {
//                        "1#1", "1#1" },
//                new String[] {
//                        "1#1", "1#2" }));
        
//        System.out.println(Mathematician(
//                2,
//                new String[] {
//                        "1#1", "1#1" },
//                new String[] {
//                        "1#1", "1#1" }));

    }

    public static String Mathematician(int input1, String[] input2, String[] input3) {
        try {
            size = input1;
            if (size > 0) {
                firstMatrix = new int[size][size];
                secondMatrix = new int[size][size];

                if(!validateInputs(input2, input3))
                    return "invalid";

                for(int i=0;i<size;i++){
                    rotateDown(firstMatrix, i);
                    if(checkRows()){
                        return "yes";
                    }
                    else{
                        rotateUp(firstMatrix, i);
                    }
                }
                
                for(int i=0;i<size;i++){
                    rotateDown(secondMatrix, i);
                    if(checkRows()){
                        return "yes";
                    }
                    else{
                        rotateUp(secondMatrix, i);
                    }
                }
            }

            return "no";
        }
        catch (Exception e) {
            return "invalid";
        }
    }

    /**
     * @param input2
     * @param input3
     */
    private static boolean validateInputs(String[] input2, String[] input3) {
        if (input2.length != size || input3.length != size)
            return false;
        for (int i = 0; i < size; i++) {
            StringTokenizer st = new StringTokenizer(input2[i], "#");
            if (st.countTokens() != size) {
                return false;
            }
            for (int j = 0; j < size; j++) {
                firstMatrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < size; i++) {
            StringTokenizer st = new StringTokenizer(input3[i], "#");
            if (st.countTokens() != size) {
                return false;
            }
            for (int j = 0; j < size; j++) {
                secondMatrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        return true;
    }

    /**
     * 
     */
    private static boolean checkRows() {
        int secondIndex;
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < size; i++) {
            list.clear();
            for (int j = 0; j < size; j++) {
                list.add(secondMatrix[i][j]);
            }
            int k = 0, m, end;
            boolean found = false;
            for (k = 0; k < size && !found; k++) {
                m = k;
                end = size;
                secondIndex = 0;
                found = false;

                for (; m < end;) {
                    if (firstMatrix[i][m] != list.get(secondIndex)) {
                        break;
                    }
                    else if (secondIndex == size - 1) {
                        found = true;
                        break;
                    }

                    m++;
                    secondIndex++;
                    if (m == size) {
                        m = m % size;
                        end = k;
                    }
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }
    
    private static void rotateUp(int[][] matrix,int column){
        int tempA = matrix[size-1][column], tempB;
        for (int i = size-2; i >=0; i--) {
            tempB = matrix[i][column];
            matrix[i][column] = tempA;
            tempA = tempB;
        }
        matrix[size-1][column] = tempA;
    }
    private static void rotateDown(int[][] matrix,int column){
        int tempA = matrix[0][column], tempB;
        for (int i = 1; i < size; i++) {
            tempB = matrix[i][column];
            matrix[i][column] = tempA;
            tempA = tempB;
        }
        matrix[0][column] = tempA;
    }
}
