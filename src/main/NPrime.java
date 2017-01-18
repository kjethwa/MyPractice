package main;

public class NPrime {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int N = 100;
        boolean[] allPrime = new boolean[N];
        
        for(int i=0;i<N;i++)
            allPrime[i]=true;
            
        for(int i=2;i<N;i++){
            if(allPrime[i]==true){
                for(int j=i*2;j<N;j=j+i)
                    allPrime[j]=false;
            }
        }
        for(int i=2;i<N;i++)
            if(allPrime[i])
                System.out.print(i + " ");
        
    }

}

