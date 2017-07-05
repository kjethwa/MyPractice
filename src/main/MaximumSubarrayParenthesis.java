package main;

import java.util.Scanner;
import java.util.Stack;

public class MaximumSubarrayParenthesis {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] data = new int[N];
        Stack<Integer> stack = new Stack<Integer>();
        int result = 0,temp;
        for (int i = 0; i < N; i++)
            data[i] = sc.nextInt();

        stack.push(-1);
        for (int i = 0; i < N; i++) {
            if(data[i]>0)
                stack.push(i);
            else{
                temp = stack.pop();
                if(!stack.isEmpty() && data[temp]==(-data[i])){
                    if((i - stack.peek())>result)
                        result = Math.max(result,i - stack.peek());
                }
                else
                    stack.push(i);
            }
        }
        
        System.out.println(result);
        //findMaxLen(")()())");
    }
    /*
    public static int findMaxLen(String str)
    {
        int n = str.length();
     
        // Create a stack and push -1 as initial index to it.
        Stack<Integer> stk = new Stack<Integer>();
        stk.push(-1);
     
        // Initialize result
        int result = 0;
     
        // Traverse all characters of given string
        for (int i=0; i<n; i++)
        {
            // If opening bracket, push index of it
            if (str.charAt(i) == '(')
              stk.push(i);
     
            else // If closing bracket, i.e.,str[i] = ')'
            {
                // Pop the previous opening bracket's index
                stk.pop();
     
                // Check if this length formed with base of
                // current valid substring is more than max 
                // so far
                if (!stk.empty())
                    result = Math.max(result, i - stk.peek());
     
                // If stack is empty. push current index as 
                // base for next valid substring (if any)
                else stk.push(i);
            }
        }
     
        return result;
    }*/
}
