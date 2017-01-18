package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.StringTokenizer;

public class MinimumGlassFinder {

    static int N;
    static Data[] data;
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        N = Integer.parseInt(sc.nextLine());
        Data tempDate;
        MinimumGlassFinder minimumGlassFinder = new MinimumGlassFinder();
        data = new Data[N];
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        for(int i=0;i<N;i++){
            tempDate = minimumGlassFinder.new Data();
            StringTokenizer st = new StringTokenizer(sc.nextLine(),"#");
            try {
                String a = st.nextToken();
                tempDate.startDate = DATE_FORMAT.parse(a+":00");
                tempDate.endDate = DATE_FORMAT.parse(st.nextToken()+":00");
            }
            catch (ParseException e) {
                System.out.println("-1");
                return ;
            }
            if(tempDate.startDate.getTime()>=tempDate.endDate.getTime()){
                System.out.println("-1");
                return ;
            }
            data[i] = tempDate;
        }
        int count =0;
        while(!isAllConsidered()){
            Data min = getMinimumStartGuest();
            min.considered = true;
            Data nextMin = min;
            while(true){
                nextMin = getNextMinStartGuest(nextMin);
                if(nextMin==null){
                    break ; 
                }
                else{
                    nextMin.considered=true;
                }
            }
            count++;
        }
        if(count==0)
            System.out.println("-1");
        else{
            System.out.println(count);
        }
        
    }
    
    private static Data getNextMinStartGuest(Data min) {
        Data nextMin = null;
        for(int i=0;i<N;i++){
            if(data[i].considered==false && data[i].startDate.getTime()>min.endDate.getTime()){
                if(nextMin== null)
                    nextMin = data[i];
                else{
                    if(data[i].startDate.getTime()<nextMin.startDate.getTime()){
                        nextMin = data[i];
                    }
                }
            }
        }
        return nextMin;
    }

    private static Data getMinimumStartGuest() {
        Data min = null;
        for(int i=0;i<N;i++){
            if(data[i].considered==false){
                if(min == null){
                    min = data[i];
                }
                else{
                    if(data[i].startDate.getTime()<min.startDate.getTime()){
                        min = data[i];
                    }
                }
            }
        }
        return min;
    }

    private static boolean isAllConsidered() {
       for(int i=0;i<N;i++){
           if(data[i].considered==false)
               return false;
       }
       
       return true;
    }

    class Data{
        Date startDate;
        Date endDate;
        boolean considered = false;
    }

}

