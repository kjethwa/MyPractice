package main;

public class QuickSort {

    static int K = 8;
    static int[] data;
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        data = new int[]{-8968,4,2,0,-4,-5,-43,5,-9333333}; 
        quickSort(0,data.length-1);
        System.out.println(data);
    }
    
    private static void quickSort(int l, int h) {
        if(l<=h){
            int a = data[l];
            int i=l+1,j=h;
            int temp;
            while (i <= j) {
                for(;i<=j &&data[i]<a;i++);
                for(;j>=0 &&data[j]>a;j--);
                
                if(i<j){
                    temp = data[i];
                    data[i] = data[j];
                    data[j] = temp;
                }
            }
            if(j>0){
                temp = data[j];
                data[j] = data[l];
                data[l] = temp;
            }
            
            if(K==j || K-1==j)
                return ; 
            else if(K < j)
                quickSort(l,j-1);
            else if(K>j)
                quickSort(j+1,h);
        }
    }
}

