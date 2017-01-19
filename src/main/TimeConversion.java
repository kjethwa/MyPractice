package main;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class TimeConversion {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String time = in.next();
        SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm:ssa");
        Date date = null;
        try {
            date = (Date) parseFormat.parse(time);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(displayFormat.format(date));   
    }
}

