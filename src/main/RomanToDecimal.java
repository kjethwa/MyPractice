package main;

import java.util.Scanner;
import java.util.Stack;

public class RomanToDecimal {
    static public class EvaluateString
    {
        public static int evaluate(String expression) throws Exception
        {
            char[] tokens = expression.toCharArray();
     
             // Stack for numbers: 'values'
            Stack<Integer> values = new Stack<Integer>();
     
            // Stack for Operators: 'ops'
            Stack<Character> ops = new Stack<Character>();
     
            for (int i = 0; i < tokens.length; i++)
            {
                 // Current token is a whitespace, skip it
                if (tokens[i] == ' ')
                    continue;
     
                // Current token is a number, push it to stack for numbers
                if (tokens[i] == 'I' || tokens[i] == 'V' || tokens[i] == 'X' || tokens[i] == 'L' || tokens[i] == 'C' || tokens[i] == 'D' || tokens[i] == 'M')
                {
                    StringBuffer sbuf = new StringBuffer();
                    // There may be more than one digits in number
                    while (i < tokens.length && (tokens[i] == 'I' || tokens[i] == 'V' || tokens[i] == 'X' || tokens[i] == 'L' || tokens[i] == 'C' || tokens[i] == 'D' || tokens[i] == 'M'))
                        sbuf.append(tokens[i++]);
                    values.push(romanToDecimal(sbuf.toString()));
                }
     
                // Current token is an opening brace, push it to 'ops'
                else if (tokens[i] == '(')
                    ops.push(tokens[i]);
     
                // Closing brace encountered, solve entire brace
                else if (tokens[i] == ')')
                {
                    while (ops.peek() != '(')
                      values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                    ops.pop();
                }
     
                // Current token is an operator.
                else if (tokens[i] == '+' || tokens[i] == '-' ||
                         tokens[i] == '*' || tokens[i] == '/')
                {
                    // While top of 'ops' has same or greater precedence to current
                    // token, which is an operator. Apply operator on top of 'ops'
                    // to top two elements in values stack
                    while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
                      values.push(applyOp(ops.pop(), values.pop(), values.pop()));
     
                    // Push current token to 'ops'.
                    ops.push(tokens[i]);
                }
            }
     
            // Entire expression has been parsed a10 + 2 * 6t this point, apply remaining
            // ops to remaining values
            while (!ops.empty())
                values.push(applyOp(ops.pop(), values.pop(), values.pop()));
     
            // Top of 'values' contains result, return it
            return values.pop();
        }
     
        // Returns true if 'op2' has higher or same precedence as 'op1',
        // otherwise returns false.
        public static boolean hasPrecedence(char op1, char op2)
        {
            if (op2 == '(' || op2 == ')')
                return false;
            if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
                return false;
            else
                return true;
        }
     
        // A utility method to apply an operator 'op' on operands 'a' 
        // and 'b'. Return the result.
        public static int applyOp(char op, int b, int a)
        {
            switch (op)
            {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0)
                    throw new
                    UnsupportedOperationException("Cannot divide by zero");
                return a / b;
            }
            return 0;
        }
    }
  
    public static int romanToDecimal(java.lang.String romanNumber) throws Exception {
        int decimal = 0;
        int lastNumber = 0;
        int Icount=0;
        int Xcount=0;
        String romanNumeral = romanNumber.toUpperCase();
        /* operation to be performed on upper cases even if user 
           enters roman values in lower case chars */
        for (int x = romanNumeral.length() - 1; x >= 0 ; x--) {
            char convertToDecimal = romanNumeral.charAt(x);

            switch (convertToDecimal) {
                case 'M':
                    decimal = processDecimal(1000, lastNumber, decimal);
                    lastNumber = 1000;
                    Xcount=0;Icount=0;
                    break;

                case 'D':
                    decimal = processDecimal(500, lastNumber, decimal);
                    lastNumber = 500;
                    Xcount=0;Icount=0;
                    break;

                case 'C':
                    decimal = processDecimal(100, lastNumber, decimal);
                    lastNumber = 100;
                    Xcount=0;Icount=0;
                    break;

                case 'L':
                    decimal = processDecimal(50, lastNumber, decimal);
                    lastNumber = 50;
                    Xcount=0;Icount=0;
                    break;

                case 'X':
                    decimal = processDecimal(10, lastNumber, decimal);
                    lastNumber = 10;
                    Xcount++;
                    if(Xcount>3)
                        throw new Exception();
                    break;

                case 'V':
                    if(lastNumber==5)
                        throw new Exception();
                    if(lastNumber==10)
                        throw new Exception();
                    decimal = processDecimal(5, lastNumber, decimal);
                    lastNumber = 5;
                    Xcount=0;Icount=0;
                    break;

                case 'I':
                    decimal = processDecimal(1, lastNumber, decimal);
                    lastNumber = 1;
                    Icount++;
                    if(Icount>3)
                        throw new Exception();
                    break;
            }
        }
        return decimal;
    }

    public static int processDecimal(int decimal, int lastNumber, int lastDecimal) {
        if (lastNumber > decimal) {
            return lastDecimal - decimal;
        } else {
            return lastDecimal + decimal;
        }
    }
    
    public static void main(String[] a) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
//        try {
//            System.out.println(romanToDecimal(input));
//        }
//        catch (Exception e1) {
//            // TODO Auto-generated catch block
//            e1.printStackTrace();
//        }
        try {
            System.out.println(EvaluateString.evaluate(input));
        }
        catch (Exception e) {
            System.out.println("invalid");
        }
    }
}

