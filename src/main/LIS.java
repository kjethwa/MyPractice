package main;

/* Dynamic Programming Java implementation of LIS problem */
 
class LIS
{
    /* lis() returns the length of the longest increasing
       subsequence in arr[] of size n */
    static int lis(int arr[],int n)
    {
          int lis[] = new int[n];
          int i,j,max = 0;
 
          /* Initialize LIS values for all indexes */
           for ( i = 0; i < n; i++ )
              lis[i] = 1;
 
           /* Compute optimized LIS values in bottom up manner */
           for ( i = 1; i < n; i++ )
              for ( j = 0; j < i; j++ ) 
                         if ( arr[i] > arr[j] && lis[i] < lis[j] + 1)
                    lis[i] = lis[j] + 1;
 
           /* Pick maximum of all LIS values */
           for ( i = 0; i < n; i++ ) 
              if ( max < lis[i] )
                 max = lis[i];
 
            return max;
    }
 
    public static void main(String args[])
    {
        int arr[] = { 10, 22, 9, 33, 21, 50, 41, 60};
            int n = arr.length;
            System.out.println("Length of lis is " + lis( arr, n ) + "\n" );
            
            System.out.println(longestIncreasingSubsequence("abcbcbcd"));
            
    }
    
    public static String longestIncreasingSubsequence(String input1) {
        int dp[] = new int[input1.length()];
        int i,j,max = 0;
        StringBuilder str = new StringBuilder();

         for ( i = 0; i < input1.length(); i++ )
            dp[i] = 1;

         for ( i = 1; i < input1.length(); i++ )
            for ( j = 0; j < i; j++ ) 
                  if (input1.charAt(i) >= input1.charAt(j) && dp[i] < dp[j]+1)
                      dp[i] = dp[j] + 1;

         for ( i = 0; i < input1.length(); i++ ) {
            if ( max < dp[i] ) {
                 max = dp[i];
                 if (i + 1 < input1.length() && max == dp[i+1] && input1.charAt(i+1) < input1.charAt(i)) {
                     str.append(input1.charAt(i+1));
                     i++;
                 } else {
                     str.append(input1.charAt(i)); 
                 }
            }
         }
         return str.toString();
    }
}