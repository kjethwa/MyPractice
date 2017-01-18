package main;

public class SecondLargestNumber {

    public static void main(String[] args) {
       int largest=0,secondLargest=0;
        int[] data = new int[]{-14554,-78,-2,-7,-4,-45,-2,-7,-578,-6};
        if(data[0]>data[1]){
            largest = data[0];
            secondLargest = data[1];
        }
        else{
            largest = data[1];
            secondLargest = data[0];
        }
        for(int i=2;i<data.length;i++){
            if(data[i]>largest){
                secondLargest = largest;
                largest = data[i];
            }
            else if(data[i]!=largest && data[i]>secondLargest){
                secondLargest = data[i];
            }
        }
        System.out.println(secondLargest);
        System.out.println(largest);
    }

}

