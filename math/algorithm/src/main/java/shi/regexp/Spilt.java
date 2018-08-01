package shi.regexp;

import java.util.Arrays;

public class Spilt {
	public static void main(String[] args) {
		String x = "1100,323.12,,432,23,23,";
		String [] strs= x.split(",",-1);
		System.out.println(strs.length);
		System.out.println(Arrays.toString(strs));
	}
	
	
	
}
