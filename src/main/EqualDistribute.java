package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EqualDistribute {
    static int count=0;

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        int T = 0,N;
        int[] data=null;
        
        
        
        //Scanner sc = new Scanner(System.in);
       // T = sc.nextInt();
        
        //------------------
        
        BufferedReader br =null;
        try {
            br = new BufferedReader(new FileReader("inputs.txt"));
            T= Integer.parseInt(br.readLine());
        }
        catch (NumberFormatException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
        //-------------------
        
        
        for(int k = 0;k<T;k++){
            count=0;
            
            //------------------
            
            try {
                //br = new BufferedReader(new FileReader("inputs.txt"));
                //T= Integer.parseInt(br.readLine());
                N = Integer.parseInt(br.readLine());
                
                data = new int[N];
                
                String[] strData = br.readLine().split(" ");
                for(int i = 0;i<N;i++){
                    data[i] = Integer.parseInt(strData[i]);
                }
            }
            catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            //-----------------------
           /* N = sc.nextInt();
            data = new int[N];
            for (int i = 0; i < N; i++) {
                data[i] = sc.nextInt();
            }
            */
            int initMin=data[0];
            for(int i=1;i<data.length;i++){
                if(data[i]<initMin)
                    initMin = data[i];
            }
            
            while (!isAllEqual(data)) {
                int[] smallestNumber = findIndexOfTwoSmallestNumber(data);
                addDifferenceOfTwoSmallestNumberToAllOtherThenSeconSmallestNumber(smallestNumber, data);
               // System.out.println(iii++);
            }
            count=0;
            int M=data[0]-initMin;
            
            if (M >= 5) {
                count = count + (int)(M/5);
                M = M%5;
            }
            if (M >= 2) {
                count = count + (int)(M/2);
                M = M%2;
            }
            if (M >= 1) {
                M--;
                count++;
            }
            
            System.out.println(count);
        }
    }

    private static void addDifferenceOfTwoSmallestNumberToAllOtherThenSeconSmallestNumber(int[] smallestNumber, int[] data) {
        int difference = data[smallestNumber[1]] - data[smallestNumber[0]];
        
        for(int i=0;i<data.length;i++){
            if(i!=smallestNumber[1])
                data[i]+=difference;
        }
        
        if(difference==1 || difference == 2 || difference == 5){
            count++;
        }
        else {

            if (difference >= 5) {
                count = count + (int)(difference/5);
                difference = difference%5;
            }
            if (difference >= 2) {
                count = count + (int)(difference/2);
                difference = difference%2;
            }
            if (difference >= 1) {
                difference--;
                count++;
            }
        }
    }

    private static int[] findIndexOfTwoSmallestNumber(int[] data) {
        
        int first = Integer.MAX_VALUE,second = Integer.MAX_VALUE; 
        int[] index = new int[2];
        
        for(int i=0;i<data.length;i++){
            if(data[i]<first){
                
                second = first;
                index[1] = index[0];
                
                first = data[i];
                index[0] = i;
                
                
            }
            else if(data[i]<second && data[i]!=first){
                second = data[i];
                index[1] = i;
            }
        }
        return index;
    }

    private static boolean isAllEqual(int[] data) {
        for (int i = 1; i < data.length; i++) {
            if (data[i] != data[i - 1])
                return false;
        }
        return true;
    }

}

