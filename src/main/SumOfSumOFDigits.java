package main;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class SumOfSumOFDigits {
    public static void main(String args[] ) throws Exception {
        /*
         * Read input from stdin and provide input before running
         * Use either of these methods for input

        //BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int N = Integer.parseInt(line);
         */
        //Scanner
        Scanner sc = new Scanner(System.in);
        String[] NQ = sc.nextLine().split(" ");
        int N = Integer.parseInt(NQ[0]);
        int Q = Integer.parseInt(NQ[1]);
        int[] data = new int[N+1];
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        String strData[] = sc.nextLine().split(" ");
        for (int i = 1; i <= N; i++) {
            
            data[i]  = Integer.parseInt(sumOfDigit(strData[i-1]));
            if(map.get(data[i])==null){
                map.put(data[i],1);
            }
            else{
                map.put(data[i],map.get(data[i])+1);
            }
        }
        
        String[] QK; 
        for(int i=0;i<Q;i++){
            QK = sc.nextLine().split(" ");
            if(Integer.parseInt(QK[0])==1){
                int K = Integer.parseInt(QK[1]);
                int sum=0;
                for(int k=9;k>=0&&K>0;k--){
                    
                        if(map.get(k)!=null){
                            if(map.get(k)>=K){
                              sum+=k*K;
                              K=0;
                            }
                            else{
                                sum+=k*map.get(k);
                                K = K-map.get(k);
                            }
                        }
                    
                }
                System.out.println(sum);
            }
            else{
                int K = Integer.parseInt(QK[1]);
                int sum=0;
                for(int k=0;k<=9&&K>0;k++){
                    
                    
                        if(map.get(k)!=null){
                            if(map.get(k)>=K){
                              sum+=k*K;
                              K=0;
                            }
                            else{
                                sum+=k*map.get(k);
                                K = K-map.get(k);
                            }
                        }
                    
                }
                System.out.println(sum);
            }
        }

        
    }
    private static String sumOfDigit(String number){
        if(number.length()==1)
            return number;
        else{
            int sum = 0;
            for(int i=0;i<number.length();i++){
                sum+=number.charAt(i)-'0';
            }
            return sumOfDigit(sum+"");
        }
    }
}
