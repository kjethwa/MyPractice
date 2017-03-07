package main;

import java.util.HashSet;
import java.util.Set;

public class TripletSum {

    public static void main(String[] args) {
        int[] data = new int[]{2,2,0,0,2,2,2};
        findTriplets(data, data.length);
    }

    public static void findTriplets(int arr[], int n)
    {
        boolean found = false;
     
        for (int i=0; i<n-1; i++)
        {
            // Find all pairs with sum equals to
            // "-arr[i]"
            Set<Integer> s = new HashSet<Integer>();
            for (int j=i+1; j<n; j++)
            {
                int x = -(arr[i] + arr[j]);
                if (s.contains(x))
                {
                    System.out.println(x + " "+ arr[i] +" "+ arr[j]);
                    found = true;
                }
                else
                    s.add(arr[j]);
            }
        }
     
        if (found == false)
            System.out.println(" No Triplet Found");
    }
}

