package main;

public class EditMinimumString {

    static String str1="asdfertghjk";
    static String str2="asdfghjk";
    
    static int[][] Cost = new int[str1.length()+1][str2.length()+1];
    
    public static void main(String[] args) {
        
//        for(int i=0;i<Cost.length;i++)
//            Arrays.fill(Cost[i],-1);
        
        for(int i=0;i<=str1.length();i++)
            for(int j=0;j<=str2.length();j++)
            {
                if(i==0)
                    Cost[i][j] = j;
                else if(j==0)
                    Cost[i][j] = i;
                else if(str1.charAt(i-1)==str2.charAt(j-1))
                    Cost[i][j] = Cost[i-1][j-1];
                else{
                    Cost[i][j] = 1 + min(Cost[i-1][j], Cost[i][j-1], Cost[i-1][j-1]);
                }
            }
        System.out.println("Bottom Up := "+Cost[str1.length()][str2.length()]);
        System.out.println("Recursion := " + EDIT(str1.length(), str2.length()));
    }
    private static int EDIT(int m, int n) {
        if(m==0)
            return n;
        
        if(n==0)
            return m;
        
        if(str1.charAt(m-1)==str2.charAt(n-1))
            return EDIT(m-1, n-1);
        
        return 1 + min(EDIT(m - 1, n - 1), EDIT(m - 1, n), EDIT(m, n - 1));
    }
    private static int min(int edit, int edit2, int edit3) {
        return min(min(edit,edit2),edit3);        
    }
    private static int min(int a, int b) {
        return  a<b?a:b;
    }
}

