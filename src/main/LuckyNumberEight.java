package main;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class LuckyNumberEight {

    public static void main(String[] args) {
    	
    	
    	
    	
    	
        Scanner in = new Scanner(System.in);
        int n = 200000;
        //int n = in.nextInt();
        StringBuilder number = new StringBuilder("0123456789");
        //String number = in.next();
        StringBuilder tempNumber = new StringBuilder("0123456789");
        
        for(int i =1;i<20000;i++){
        	number.append(tempNumber);
        }
        
//        for(int i =1;i<=1024;i++){
//			System.out.println((long)(Math.pow(2, i) % 1000000007l));
//        }
        
        long[] lookup = new long[100];
        long ZeroZero = 0,ZeroFour=0,ZeroEight=0,power=0;
        long ppower =(long) Math.pow(2, 1000)%1000000007l;
        long count=0;
        int temp;
        
        for(int i=0;i<n;i++){
            temp = Integer.parseInt(""+number.charAt(i));
            switch(temp){
                case 0:
                    ZeroZero+=lookup[0];
                    lookup[20]+=lookup[2];
                    lookup[40]+=lookup[4];
                    lookup[60]+=lookup[6];
                    lookup[80]+=lookup[8];
                    break;
                case 2:
                    lookup[12]+=lookup[1];
                    lookup[52]+=lookup[5];
                    lookup[92]+=lookup[9];
                    lookup[32]+=lookup[3];
                    lookup[72]+=lookup[7];
                    break;
                case 4:
                    ZeroFour+=lookup[0];
                    lookup[44]+=lookup[4];
                    lookup[84]+=lookup[8];
                    lookup[24]+=lookup[2];
                    lookup[64]+=lookup[6];
                    break;
                case 6:
                    lookup[36]+=lookup[3];
                    lookup[76]+=lookup[7];
                    lookup[16]+=lookup[1];
                    lookup[56]+=lookup[5];
                    lookup[96]+=lookup[9];
                    break;
                case 8:
                    ZeroEight+=lookup[0];
                    lookup[28]+=lookup[2];
                    lookup[68]+=lookup[6];
                    lookup[48]+=lookup[4];
                    lookup[88]+=lookup[8];
                    break;
                default:
                    break;
            }
            
            lookup[temp]++;
        }
        
		count = (lookup[0] + lookup[8] + ZeroEight + ZeroZero + lookup[40]
				+ lookup[80] + lookup[32] + lookup[72] + lookup[24]
				+ lookup[16] + lookup[56] + lookup[96] + lookup[48]
				+ lookup[88] + lookup[64]) % 1000000007l;
        
        
        for(int i=0;i<n;i++){
            temp = Integer.parseInt(""+number.charAt(i));
            
            
            
            lookup[temp]--;
            
            switch(temp){
                case 0:
                    ZeroEight-=lookup[8];
                    ZeroFour-=lookup[4];
                    ZeroZero-=lookup[0];
                    break;
                case 1:
                    lookup[12]-= lookup[2];
                    lookup[16]-= lookup[6];
                    break;
                case 2:
                    lookup[20]-= lookup[0];
                    lookup[28]-= lookup[8];
                    lookup[24]-= lookup[4];
                    break;
                case 3:
                    lookup[36]-= lookup[6];
                    lookup[32]-= lookup[2];
                    break;  
                case 4:
                    lookup[44]-= lookup[4];
                    lookup[40]-= lookup[0];
                    lookup[48]-= lookup[8];
                    break;
                case 5:
                    lookup[52]-= lookup[2];
                    lookup[56]-= lookup[6];
                    break;    
                case 6:
                    lookup[60]-= lookup[0];
                    lookup[68]-= lookup[8];
                    lookup[64]-= lookup[4];
                    break;
                case 7:
                    lookup[76]-= lookup[6];
                    lookup[72]-= lookup[2];
                    break;
                case 8:
                    lookup[84]-= lookup[4];
                    lookup[80]-= lookup[0];
                    lookup[88]-= lookup[8];
                    break;    
                case 9:
                    lookup[92]-= lookup[2];
                    lookup[96]-= lookup[6];
                    break;
                default:
                    break;
            }
            
            power = mod(i,2,1000000007l);
            	
            
            if(temp%2!=0){
                count = (long)((count + ((ZeroFour*(power%1000000007l))%1000000007l))%1000000007l);
                count = (long)((count + ((lookup[12]*(power%1000000007l))%1000000007l))%1000000007l);
                count = (long)((count + ((lookup[20]*(power%1000000007l))%1000000007l))%1000000007l);
                count = (long)((count + ((lookup[28]*(power%1000000007l))%1000000007l))%1000000007l);
                
                count = (long)((count + ((lookup[36]*(power%1000000007l))%1000000007l))%1000000007l);
                
                count = (long)((count + ((lookup[44]*(power%1000000007l))%1000000007l))%1000000007l);
                
                count = (long)((count + ((lookup[52]*(power%1000000007l))%1000000007l))%1000000007l);
                
                count = (long)((count + ((lookup[60]*(power%1000000007l))%1000000007l))%1000000007l);
                
                count = (long)((count + ((lookup[68]*(power%1000000007l))%1000000007l))%1000000007l);
                
                count = (long)((count + ((lookup[76]*(power%1000000007l))%1000000007l))%1000000007l);
                
                count = (long)((count + ((lookup[84]*(power%1000000007l))%1000000007l))%1000000007l);
                
                count = (long)((count + ((lookup[92]*(power%1000000007l))%1000000007l))%1000000007l);
                
            }
            else{
            	
            	
                count = (long)((count + ((ZeroEight*(power%1000000007l))%1000000007l))%1000000007l);
                
                count = (long)((count + ((ZeroZero*(power%1000000007l))%1000000007l))%1000000007l);
                
                count = (long)((count + ((lookup[16]*(power%1000000007l))%1000000007l))%1000000007l);
                
                count = (long)((count + ((lookup[24]*(power%1000000007l))%1000000007l))%1000000007l);
                
                count = (long)((count + ((lookup[32]*(power%1000000007l))%1000000007l))%1000000007l);
                
                count = (long)((count + ((lookup[40]*(power%1000000007l))%1000000007l))%1000000007l);
                
                count = (long)((count + ((lookup[48]*(power%1000000007l))%1000000007l))%1000000007l);
                
                count = (long)((count + ((lookup[56]*(power%1000000007l))%1000000007l))%1000000007l);
                
                count = (long)((count + ((lookup[64]*(power%1000000007l))%1000000007l))%1000000007l);
                
                count = (long)((count + ((lookup[72]*(power%1000000007l))%1000000007l))%1000000007l);
                
                count = (long)((count + ((lookup[80]*(power%1000000007l))%1000000007l))%1000000007l);
                
                count = (long)((count + ((lookup[88]*(power%1000000007l))%1000000007l))%1000000007l);
                
                count = (long)((count + ((lookup[96]*(power%1000000007l))%1000000007l))%1000000007l);
                
            }   
            
        }
        System.out.println(count);
    }
    
	static long mod(int p, int n, long MOD) {
		if (p == 0)
			return 1;
		long q = mod(p / 2, n, MOD); // recursive call
		if (p % 2 == 0)
			return (q * q) % MOD;
		else
			return (((q * n) % MOD) * q) % MOD;
	}
}
