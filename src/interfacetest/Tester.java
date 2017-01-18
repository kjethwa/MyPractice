package interfacetest;

import interfacepackage.Interface1;
import interfacepackage.Interface2;

public class Tester implements Interface1,Interface2 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new Tester().display("kalpesh");
    }

    @Override
    public void display(String a) {
        // TODO Auto-generated method stub
        System.out.println("A="+a);
    }

}

