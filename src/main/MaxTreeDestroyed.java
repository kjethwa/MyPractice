package main;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MaxTreeDestroyed {

    public static boolean[][] data;
    public static int row;
    public static int col;
    public static void main(String[] args) {
       //System.out.println(maxTreeDestroyed(6, 7, 14, "((2,1),(6,6),(4,2),(2,5),(2,6),(2,7),(3,4),(6,1),(6,2),(2,3),(6,3),(6,4),(6,5),(6,7))"));
        System.out.println(maxTreeDestroyed(6, 7, 4, "((5,1),(4,3),(3,5),(2,7))"));
    }
    
    public static int maxTreeDestroyed(int input1,int input2,int input3,String input4)
    {
        row = input1;
        col = input2;
        try {
            data = new boolean[input1+1][input2+1];
            List<int[]> listOfPoints = getListOfPoints(input4);
            int noDestroyedPlants = input3;
            if(input3!=listOfPoints.size())
                new Exception();
            int maxDestroyedPlants=0,temp;
            for(int i=0;i<noDestroyedPlants;i++){
                for(int j=i+1;j<noDestroyedPlants;j++){
                    temp = calculatePathNDestroyedPlant(listOfPoints.get(i),listOfPoints.get(j));
                    if(temp>maxDestroyedPlants)
                        maxDestroyedPlants = temp;
                }
            }
            return maxDestroyedPlants;
        }
        catch(Exception e){
            return -1;
        }
    }

    private static int calculatePathNDestroyedPlant(int[] one, int[] two) {
        
        int dif, count = 2, min, max;
        if (one[0] == two[0]) {
            dif = Math.abs(one[1] - two[1]);
            if (one[1] < two[1]) {
                min = one[1];
                max = two[1];
            }
            else {
                min = two[1];
                max = one[1];
            }
            while (inGrid(one[0], min - dif)) {
                if (data[one[0]][min - dif])
                    count++;
                else
                    return 0;
                min = min - dif;
            }
            while (inGrid(one[0], max + dif)) {
                if (data[one[0]][max + dif])
                    count++;
                else
                    return 0;
                max = max + dif;
            }
        }
        else if (one[1] == two[1]) {
            dif = Math.abs(one[0] - two[0]);
            if (one[0] < two[0]) {
                min = one[0];
                max = two[0];
            }
            else {
                min = two[0];
                max = one[0];
            }
            while (inGrid(min - dif, one[1])) {
                if (data[min - dif][one[1]])
                    count++;
                else
                    return 0;
                min = min - dif;
            }
            while (inGrid(max + dif, one[1])) {
                if (data[max + dif][one[1]])
                    count++;
                else
                    return 0;
                max = max + dif;
            }
        }
        else{
            
            if (one[0] < two[0]) {
                min = one[0];
                max = two[0];
                dif = two[1] - one[1];
                int minY = one[1];
                while (inGrid(min - Math.abs(dif), minY + dif)) {
                    if (data[min - Math.abs(dif)][minY + dif])
                        count++;
                    else
                        return 0;
                    min = min - Math.abs(dif);
                    minY = minY + dif;
                }
                minY = two[1];
                while (inGrid(max + Math.abs(dif), minY + dif)) {
                    if (data[max + Math.abs(dif)][minY + dif])
                        count++;
                    else
                        return 0;
                    max = max + dif;
                    minY = minY + dif;
                }
                
            }
            else {
                min = two[0];
                max = one[0];
                dif = one[1] - two[1];
                int minY = two[1];
                
                while (inGrid(min - Math.abs(dif), minY + dif)) {
                    if (data[min - Math.abs(dif)][minY + dif])
                        count++;
                    else
                        return 0;
                    min = min - Math.abs(dif);
                    minY = minY + dif;
                }
                minY = one[1];
                while (inGrid(max + Math.abs(dif), minY + dif)) {
                    if (data[max + Math.abs(dif)][minY + dif])
                        count++;
                    else
                        return 0;
                    max = max + dif;
                    minY = minY + dif;
                }
            }
            
            
        }
        
        return count;
    }

    private static List<int[]> getListOfPoints(String input4) throws Exception {
        String string = input4,temp;
        List<int[]> listOfPoints = new ArrayList<int[]>();
        if(string.charAt(0)!='(' || string.charAt(string.length()-1)!=')')
            throw new Exception();
        
        StringTokenizer st;
        int start=1,end,r,c;
        while(start<=string.length()-6){
            end = start + 4;
            if(string.charAt(start)!='(' || string.charAt(end)!=')')
                throw new Exception();
            temp = string.substring(start+1, end);
            st = new StringTokenizer(temp,",");
            if(st.countTokens()!=2)
                throw new Exception();
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            if(!inGrid(r, c))
                throw new Exception();
            data[r][c]=true;
            listOfPoints.add(new int[]{r,c});
            start = start+6;
        }
        return listOfPoints;
    }
    
    private static boolean inGrid(int r,int c){
        return r>=1 && r<=row && c>=1 && c<=col;
    }

}

