package main;
import java.io.*;
import java.util.*;
public class CandidateCode 
{ 
    static char[] exp;
	static int pos;
	static int[] lookup = new int[26];
	static int state;
	static int varcount;
	static CandidateCode c = new CandidateCode();
	
	public static void main(String [] a){
		System.out.println(iteration("NANpp"));
	}
	
	static boolean yes(char type) {
		return (state&(1<<lookup[type-'a']))!=0;
	}
    public static String iteration(String input1)
    {
            exp = input1.toCharArray();
			lookup = new int[26];
			Arrays.fill(lookup,-1);
			pos = 0;
			varcount=0;
			Exp full = parse();
			//System.out.println("Full type is "+full.type);
			//System.out.println("Full left is "+full.left.type);
			for (state=(1<<varcount)-1; state>=0; state--) {
				//System.out.println("Trying state: "+state);
				if (!full.yes()) {
					return "No";
				}
			}
			return "Yes";
    }
    static Exp parse() {
		Exp ret = c.new Exp();
		ret.type=exp[pos++];
		switch (ret.type) {
			case 'A':
			case 'O':
			case 'I':
			case 'F':
				ret.left=parse();
				ret.right=parse();
				break;
			case 'N':
				ret.left=parse();
				break;
			default:
				if (lookup[ret.type-'a']==-1) {
					lookup[ret.type-'a']=varcount++;
				}
		}
		return ret;
	}
	
	
	class Exp {
	char type;
	Exp left, right;
	boolean yes() {
		switch (type) {
			case 'A': return left.yes() && right.yes();
			case 'O': return left.yes() || right.yes();
			case 'I': return !left.yes() || right.yes();
			case 'F': return left.yes() == right.yes();
			case 'N': return !left.yes();
			default:
			return CandidateCode.yes(type);
		}
	}
}
}
 