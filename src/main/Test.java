package main;

public class Test {

	public int area(int a,int b){
		System.out.println("int");
		return a*b;
	}
	public float area(float a,float b){
		System.out.println("float");
		return a*b;
	}
	public double area(double a,double b){
		System.out.println("double");
		return a*b;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test a = new Test();
		a.area(1, 2.2);
		a.area(1F, 2.2);
		a.area(1F, 2);
		int [] aa = new int[4];
		aa[4] = 8;
	}

}
